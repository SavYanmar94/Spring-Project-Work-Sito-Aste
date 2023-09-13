package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.User;

public interface UserDao extends CrudRepository<User, Integer>{

	User findByNickname(String nickname);
	User findByNicknameAndPassword(String nickname, String password);
	User findByAuthToken(String authToken);
}
