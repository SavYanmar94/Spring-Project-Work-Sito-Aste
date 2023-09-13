package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Item;

public interface ItemDao extends CrudRepository<Item, Integer>{

}
