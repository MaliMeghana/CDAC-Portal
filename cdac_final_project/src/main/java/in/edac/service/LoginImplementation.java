package in.edac.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.edac.dao.LoginDao;
import in.edac.model.User;

@Service
@Transactional
public class LoginImplementation implements LoginService{

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public User login(User user) {
		if(loginDao.isUserPresent(user.getUserId())){
			
			User userid=loginDao.fetch(user);			
			return userid;
		}
		return null;
	}

	
}
