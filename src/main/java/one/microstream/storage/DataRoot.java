
package one.microstream.storage;

import java.util.ArrayList;
import java.util.List;

import one.microstream.domain.Product;


/**
 * MicroStream data root. Create your data model from here.
 * 
 * @see <a href="https://docs.microstream.one/manual/">Reference Manual</a>
 */
public class DataRoot
{
	private List<Product>			products	= new ArrayList<Product>();
	private boolean					firstStart	= true;
	
	public List<Product> getProducts()
	{
		return products;
	}
	
	public void setProducts(final List<Product> products)
	{
		this.products = products;
	}
	
	public boolean isFirstStart()
	{
		return firstStart;
	}
	
	public void setFirstStart(final boolean firstStart)
	{
		this.firstStart = firstStart;
	}
}
