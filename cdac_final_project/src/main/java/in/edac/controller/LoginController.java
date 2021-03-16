package in.edac.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.edac.model.User;
import in.edac.service.LoginService;

@RestController
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public long getUser(@RequestBody User user) {
//		User user=new User();
//		user.setUserId(userId);
//		user.setPassword(password);
		System.out.println(user);
		long user1=loginService.login(user);
		return user1;
	}
}
