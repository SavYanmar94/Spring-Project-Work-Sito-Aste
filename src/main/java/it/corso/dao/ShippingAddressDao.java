package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.ShippingAddress;

public interface ShippingAddressDao extends CrudRepository<ShippingAddress, Integer> {

}
