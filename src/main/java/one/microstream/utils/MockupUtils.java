package one.microstream.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import one.microstream.domain.Product;


public class MockupUtils
{
	@SuppressWarnings("unchecked")
	public static List<Product> loadMockupData()
	{
		ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
		Optional<URL> productsURL = loader.getResource("mockup/products.json");
		
		JSONParser bookParser = new JSONParser();
		
		try
		{
			FileReader productsReader = new FileReader(new File(productsURL.get().getFile()));
			
			// Read JSON file
			Object productJSON = bookParser.parse(productsReader);
			JSONArray productList = (JSONArray)productJSON;
			// Iterate over employee array
			
			List<Product> products = (List<Product>)productList.stream().map(p -> parseProduct((JSONObject)p)).collect(Collectors.toList());
			
			return products;
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}
	
	private static Product parseProduct(JSONObject jsonProduct)
	{
		Product p = new Product();
		
		p.setName((String)jsonProduct.get("name"));
		p.setDescription((String)jsonProduct.get("description"));
		p.setCategoryId(Math.toIntExact((long)jsonProduct.get("categoryId")));
		Double price = (Double)jsonProduct.get("unitPrice");
		p.setUnitPrice(new BigDecimal(price));
		Double weight = (Double)jsonProduct.get("unitWeight");
		p.setUnitWeight(weight);
		p.setUnitsInStock(Math.toIntExact((long)jsonProduct.get("unitsInStock")));
				
		return p;
	}
}
