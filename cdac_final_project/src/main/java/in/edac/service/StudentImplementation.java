package in.edac.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.edac.dao.StudentDao;

import in.edac.model.Student;



@Service
@Transactional
public class StudentImplementation implements StudentService{

	@Autowired
	private StudentDao studentDao;

	@Override
	public Student getStudent(Student std) {
		
		if(studentDao.isStudentPresent(std.getStudId(), std.getStudFname()))
		{
			Student stdStudent=studentDao.getStudent(std);				
			return stdStudent;
		}
		return null;
		
	}
	
	
}
