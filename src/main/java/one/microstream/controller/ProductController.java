package one.microstream.controller;

import java.util.List;
import java.util.Optional;

import io.micronaut.context.annotation.BootstrapContextCompatible;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import one.microstream.domain.Product;
import one.microstream.storage.DB;
import one.microstream.utils.MockupUtils;


@Controller("/products")
public class ProductController
{
	@Get()
	public List<Product> getProducts()
	{
		return DB.root.getProducts();
	}
	
	@Get(value = "/test")
	public String getTest()
	{
		System.out.println("Alles in Ordnung");
		return "Alles in Ordnung";
	}
	
	@Post("/update")
	public HttpResponse<Product> update(@Body Product product)
	{
		Optional<Product> productOptional =
			DB.root.getProducts().stream().filter(p -> p.getUuid().equals(product.getUuid())).findFirst();
		
		if(productOptional.isPresent())
		{
			productOptional.get().setName(product.getName());
			productOptional.get().setDescription(product.getDescription());
			productOptional.get().setCategoryId(product.getCategoryId());
			productOptional.get().setUnitPrice(product.getUnitPrice());
			productOptional.get().setUnitsInStock(product.getUnitsInStock());
			productOptional.get().setUnitWeight(product.getUnitWeight());
			productOptional.get().setImageURLs(product.getImageURLs());
			
			DB.storageManager.store(productOptional.get());
			
			return HttpResponse.ok(productOptional.get());
		}
		
		return HttpResponse.notFound();
	}
	
	@Post(value = "/insert")
	public HttpResponse<Product> insert(@Body Product product)
	{
		Product newProduct = new Product();
		
		newProduct.setName(product.getName());
		newProduct.setDescription(product.getDescription());
		newProduct.setCategoryId(product.getCategoryId());
		newProduct.setUnitPrice(product.getUnitPrice());
		newProduct.setUnitsInStock(product.getUnitsInStock());
		newProduct.setUnitWeight(product.getUnitWeight());
		newProduct.setImageURLs(product.getImageURLs());
		
		DB.root.getProducts().add(newProduct);
		DB.storageManager.store(DB.root.getProducts());
		
		return HttpResponse.ok(newProduct);
	}
	
	@Delete("/delete")
	public HttpResponse<Product> delete(@Nullable @QueryValue String uuid)
	{
		Optional<Product> productOptional =
			DB.root.getProducts().stream().filter(p -> p.getUuid().equals(uuid)).findFirst();
		
		if(productOptional.isPresent())
		{
			Product deleteProduct = productOptional.get();
			
			DB.root.getProducts().remove(deleteProduct);
			DB.storageManager.store(DB.root.getProducts());
			
			HttpResponse.ok("Product has been successfully deleted");
		}
		
		return HttpResponse.notFound();
	}
	
	@Post("/init")
	public MutableHttpResponse<String> init()
	{
		List<Product> allCreatedBooks = MockupUtils.loadMockupData();
		
		DB.root.getProducts().addAll(allCreatedBooks);
		DB.storageManager.store(DB.root.getProducts());
		
		return HttpResponse.ok("Product successfully created!");
	}
}
