package in.edac.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class GenericDao {

	@PersistenceContext
	protected EntityManager entityManager;
	public <E> E fetch(Class<E> clazz, Object pk) {
		
		E e = entityManager.find(clazz, pk);
		return e;
	
}
}
