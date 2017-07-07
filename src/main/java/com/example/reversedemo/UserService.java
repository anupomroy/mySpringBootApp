package com.example.reversedemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<UserModel> getAllUsers(){
		List<UserModel> users= new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public void addUser(UserModel user){		
		userRepository.save(user);
	}
	
	public UserModel getUser(String id){
		UserModel user=userRepository.findOne(id);
		return user;
	}
	
	public void updateUser(UserModel user, String id){
		userRepository.save(user);
	}

	public void deleteUser(String id) {
		userRepository.delete(id);
		
	}

}
