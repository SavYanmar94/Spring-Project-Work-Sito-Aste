package it.corso.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Item;
import it.corso.model.Offer;

public interface OfferDao extends CrudRepository<Offer, Integer>{

	List<Offer> findAllByItemAndState(Item associatedItem, String string);

	List<Offer> findByItemId(int itemId);

}
