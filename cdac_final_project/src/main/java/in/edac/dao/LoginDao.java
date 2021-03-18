package in.edac.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;

import in.edac.model.Student;
import in.edac.model.User;




@Repository
public class LoginDao extends GenericDao{

	@PersistenceContext
	private EntityManager entityManager;
	public boolean isUserPresent(long userid) {
		return (Long)entityManager
				.createQuery("select count(u) from User u where u.userId=:userId")
                .setParameter("userId", userid)   	
	            .getSingleResult()==1 ? true:false;   
	}
	
	public User fetch(User user) {
		return   (User)entityManager
				.createQuery("select u from User u where u.userId=:userId and u.password=:password")
                .setParameter("userId", user.getUserId())   
                .setParameter("password", user.getPassword()) 
	            .getSingleResult();   
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> fetchResultId(long stud_id) {
		System.out.println("fetch"+stud_id);
		Student std = new Student();
		std.setStudId(stud_id);
		
		return   (List<Integer>)entityManager
				.createQuery("select r.resultId from ModuleResult r where r.student=:stud_id")
                .setParameter("stud_id", std)
	            .getResultList(); 
	}
	public String fetchModuleName(int mod_id) {
		System.out.println("fetch mod"+mod_id);	
		return   (String)entityManager
				.createQuery("select m.moduleName from Module m where m.moduleId=:mod_id")
                .setParameter("mod_id", mod_id)
	            .getSingleResult(); 
	}
}
