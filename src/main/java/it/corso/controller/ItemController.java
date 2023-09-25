package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.v3.oas.annotations.Parameter;
import it.corso.dto.ItemDto;
import it.corso.model.Item;
import it.corso.service.ItemService;

@RestController
@RequestMapping("/auctions/item")
@CrossOrigin(origins = {"*"})
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	// endpoint #1: registrazione item localhost:8080/auctions/item/reg/{usertoken}
	@PostMapping("/reg/{tkn}")
	public ResponseEntity<ObjectNode> itemRegistration(@RequestBody Item item,
			@PathVariable("tkn") String token)
	{
		ObjectNode response = itemService.itemRegistration(item, token);
		return new ResponseEntity<ObjectNode>(response, HttpStatusCode.valueOf(response.get("code").asInt()));
		
	}
	
	//endpoint #2 :ritiro oggetto in caso non venduto localhost:8080/auctions/item/delete/{id}/{tkn}
	@DeleteMapping("/delete/{id}/{tkn}")
	public ResponseEntity<ObjectNode> itemRemoval(@PathVariable("id") int id,
			@PathVariable("tkn") String token)
	{
		ObjectNode response = itemService.itemRemoval(id, token);
		return new ResponseEntity<ObjectNode>(response, HttpStatusCode.valueOf(response.get("code").asInt()));
	}
	
	//endpoint 4: elenco degli item localhost:8080/auctions/item/get
	@GetMapping("/get")
	public ResponseEntity<List<ItemDto>> getItems() {
			
		List<ItemDto> response = itemService.getItems();
		return new ResponseEntity<List<ItemDto>>(response, HttpStatus.OK);
	}
	
	//endpoint 5: elenco degli item user localhost:8080/auctions/item/get/{tkn}

//	@GetMapping("/get/{tkn}")
//	public ResponseEntity<List<ItemDto>> getItemUser(@Parameter(description = "User Authorization token") @PathVariable("tkn")String token) {
//		List<ItemDto> response = itemService.getItemUser(token);
//		return new ResponseEntity<List<ItemDto>> (response,HttpStatus.OK);
//	}
	
}
