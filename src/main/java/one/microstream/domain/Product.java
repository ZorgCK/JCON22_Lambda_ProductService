package one.microstream.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


public class Product
{
	private final String	uuid	= UUID.randomUUID().toString();
	private String			name;
	private String			description;
	private int				categoryId;
	private BigDecimal		unitPrice;
	private double			unitWeight;
	private int				unitsInStock;
	
	private List<String>	imageURLs;
	
	public Product()
	{
		
	}
	
	public Product(
		String name,
		String description,
		int categoryId,
		BigDecimal unitPrice,
		double unitWeight,
		int unitsInStock)
	{
		super();
		this.name = name;
		this.description = description;
		this.categoryId = categoryId;
		this.unitPrice = unitPrice;
		this.unitWeight = unitWeight;
		this.unitsInStock = unitsInStock;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public int getCategoryId()
	{
		return categoryId;
	}
	
	public void setCategoryId(int categoryId)
	{
		this.categoryId = categoryId;
	}
	
	public BigDecimal getUnitPrice()
	{
		return unitPrice;
	}
	
	public void setUnitPrice(BigDecimal unitPrice)
	{
		this.unitPrice = unitPrice;
	}
	
	public double getUnitWeight()
	{
		return unitWeight;
	}
	
	public void setUnitWeight(double unitWeight)
	{
		this.unitWeight = unitWeight;
	}
	
	public int getUnitsInStock()
	{
		return unitsInStock;
	}
	
	public void setUnitsInStock(int unitsInStock)
	{
		this.unitsInStock = unitsInStock;
	}
	
	public List<String> getImageURLs()
	{
		return imageURLs;
	}
	
	public void setImageURLs(List<String> imageURLs)
	{
		this.imageURLs = imageURLs;
	}
	
	public String getUuid()
	{
		return uuid;
	}
	
}
