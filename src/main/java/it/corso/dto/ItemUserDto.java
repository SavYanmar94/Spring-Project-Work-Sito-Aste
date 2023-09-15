package it.corso.dto;

import java.util.ArrayList;
import java.util.List;

import it.corso.model.Item;

public class ItemUserDto 
{
	    private ItemDto item;
	    private UserDto user;
	    private List<Item> items = new ArrayList<>();
	    

	    
		public List<Item> getItems() {
			return items;
		}
		public void setItems(List<Item> items) {
			this.items = items;
		}
		public ItemDto getItem() {
			return item;
		}
		public void setItem(ItemDto item) {
			this.item = item;
		}
		public UserDto getUser() {
			return user;
		}
		public void setUser(UserDto user) {
			this.user = user;
		}
	    
	    
}
