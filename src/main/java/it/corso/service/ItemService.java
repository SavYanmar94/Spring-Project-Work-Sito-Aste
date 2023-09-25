package it.corso.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dto.ItemDto;
import it.corso.model.Item;

public interface ItemService {
	
	//registrazione di un item
	ObjectNode itemRegistration(Item item, String token);
	
	//solo per la modifica stato item, venduto o no
	ObjectNode itemUpdate(Item item, String token);
	
	//ritiro oggetto
	ObjectNode itemRemoval(int id, String token);
	
	List<ItemDto> getItems();
	


}
