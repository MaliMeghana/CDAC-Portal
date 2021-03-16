package in.edac.dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;

import in.edac.model.Student;

@Repository
public class StudentDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	public boolean isStudentPresent(long studid , String fname) {
		return (Long)entityManager
				.createQuery("select count(s) from Student s where s.studId=:stud_id or s.studFname=:fname")
                .setParameter("stud_id", studid) 
                .setParameter("fname", fname)
	            .getSingleResult()>=1 ? true:false;   
	}
	public Student getStudent(Student std) {
		
		return (Student)entityManager
				.createQuery("select s from Student s where s.studId=:studid and s.batch=:batchid or s.studFname=:fname")
				.setParameter("batchid", std.getBatch())
				.setParameter("studid", std.getStudId())
				.setParameter("fname", std.getStudFname())
				.getSingleResult();
		
	}
	
	public List<Student> getStudents(Student std){
		
		List<Student> list=entityManager
				.createNativeQuery("select * from Student s where s.studId=:studid and s.batch=:batchid")
				.setParameter("batchid", std.getBatch())
				.getResultList();
		return list;
	}
}
