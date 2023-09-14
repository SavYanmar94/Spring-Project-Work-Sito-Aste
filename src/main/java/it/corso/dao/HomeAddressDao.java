package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.HomeAddress;

public interface HomeAddressDao extends CrudRepository<HomeAddress, Integer> {

}
