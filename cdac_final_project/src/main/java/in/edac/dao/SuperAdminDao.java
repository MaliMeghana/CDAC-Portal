package in.edac.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SuperAdminDao {

	@Autowired
	private EntityManager entityManager;
	
}
