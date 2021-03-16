package in.edac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.edac.model.User;
import in.edac.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Controller("/user")
public class UserAction {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/create")
	public ResponseEntity<User> create(@RequestBody User user){
		try {
			User _user=userRepository.save(new User(user.getPwd(),user.getEmail(),user.getMobile()));
			return new ResponseEntity<>(_user,HttpStatus.CREATED);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.CREATED);
		}
	}
}
