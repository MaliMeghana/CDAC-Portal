package in.edac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.edac.model.Role;
import in.edac.model.User;
import in.edac.repository.RoleRepository;
import in.edac.service.LoginService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping("/")
	public User getUser(@RequestBody User user) {
//		User user=new User();
//		user.setUserId(userId);
//		user.setPassword(password);
		System.out.println(user);
		User user1=loginService.login(user);
		return user1;
	}
	@GetMapping("/role")
	public List<Role> getRole() {
		List<Role> list=roleRepository.findAll();
		return list;
	}
}
