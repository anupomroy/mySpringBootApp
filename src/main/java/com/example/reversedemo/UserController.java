package com.example.reversedemo;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements ErrorController {

	private static final Logger logger= LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@PostMapping("/adduser")
	public ResponseEntity<UserModel> addUser(@RequestBody UserModel user){
		userService.addUser(user);
		logger.info("*********IN addUser CONTROLLER*********");
		return new ResponseEntity<UserModel>(HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserModel>>  getAllUsers(){//public List<UserModel> getAllUsers()
		List<UserModel> users= userService.getAllUsers();
		//System.out.println(users.forEach(users));
		logger.info("*********IN getAllUsers CONTROLLER*********");
		return new ResponseEntity<List<UserModel>>( users ,HttpStatus.OK);
		// return new ResponseEntity<List<String>>(failedUser, HttpStatus.CREATED);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserModel> getUser(@PathVariable String id){
		logger.info("*********IN getUser CONTROLLER*********");
		return new ResponseEntity<UserModel>( userService.getUser(id) ,HttpStatus.OK);
		//return userService.getUser(id);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user, @PathVariable String id){
		logger.info("*********IN updateUser CONTROLLER*********");
		userService.updateUser(user, id);
		return new ResponseEntity<UserModel>( HttpStatus.OK);		
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<UserModel> deleteUser(@PathVariable String id){
		logger.info("*********IN deleteUser CONTROLLER*********");
		userService.deleteUser(id);
		return new ResponseEntity<UserModel>(HttpStatus.OK);		
	}

	@GetMapping("/test")
	public String test(){
		return "TEST SUCCESS";
	}

	@Override	
	public String getErrorPath(){		
		return null;
	}
	
	@RequestMapping("/error")
	public String error(){
		return "no mapping for this url";
	}

}
/*
 * 
 * JSON for user
 {	
     "id": "111",
	"username": "aaa",
	"password": "aaa111"
}
 {	
     "id": "222",
	"username": "bbb",
	"password": "aaa111"
}
 * */
