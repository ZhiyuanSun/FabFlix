package data_beans;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {

	private HashMap<Integer, Item> items;
	
	public Cart() 
	{
		items = new HashMap<Integer, Item>();
	}
	
	public Item addItemToCart(Movie movie, int quantity)
	{
		if (items.containsKey(movie.getId()))
		{
			Item existingItem = items.get(movie.getId());
			existingItem.addQuantity(quantity);
			return existingItem;
		}
		else
		{
			Item cartItem = new Item(movie, quantity);
			items.put(movie.getId(), cartItem);
			return cartItem;
		}
	}
	
	public void updateQuantityOfItemInCart(Movie movie, int quantity)
	{
		if (items.containsKey(movie.getId()))
		{
			Item existingItem = items.get(movie.getId());
			existingItem.setQuantity(quantity);
		}
	}
	
	public void removeItemFromCart(Movie movie)
	{
		if (items.containsKey(movie.getId()))
		{
			items.remove(movie.getId());
		}
	}
	
	public void removeAllItemsFromCart()
	{
		items.clear();
	}
	
	public ArrayList<Item> getCartItems()
	{
		return new ArrayList<Item>(items.values());
	}
}
