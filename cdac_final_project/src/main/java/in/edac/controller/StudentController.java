package in.edac.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import in.edac.dao.LoginDao;
import in.edac.exception.ResourceNotFoundException;
import in.edac.model.ModuleResult;
import in.edac.model.Student;
import in.edac.repository.ModuleResultRepository;
import in.edac.repository.StudentRepository;


@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private ModuleResultRepository moduleResultRepository;
	
     
	@GetMapping("/{stud_id}")
	public ResponseEntity<Student> getStudentById(@PathVariable long stud_id) {
		Student std=studentRepository.findById(stud_id)
				.orElseThrow(()-> new ResourceNotFoundException("Student not exists with this id :"+stud_id));
	    return ResponseEntity.ok(std);
	}
	
	@PutMapping("/{stud_id}")
	public ResponseEntity<Student> updateStudent(@PathVariable long stud_id, @RequestBody Student std){
		
		Student std1=studentRepository.findById(stud_id)
				.orElseThrow(()-> new ResourceNotFoundException("Student not exists with this id :"+stud_id));
		
		std1.setStudFname(std.getStudFname()== null ? std1.getStudFname():std.getStudFname());
		std1.setStudMname(std.getStudMname());
		std1.setStudLname(std.getStudLname()== null ? std1.getStudLname():std.getStudLname());
		std1.setStudEmail(std.getStudEmail()== null ? std1.getStudEmail():std.getStudEmail());
		std1.setStudMobile(std.getStudMobile()== 0 ? std1.getStudMobile():std.getStudMobile());
		std1.setStudDob(std.getStudDob());
		
		std1.setGender(std.getGender()== null ? std1.getGender():std.getGender());
		std1.setCenter(std.getCenter()== null ? std1.getCenter():std.getCenter());
		std1.setState(std.getState());
		std1.setCountry(std.getCountry());
		std1.setStudCity(std.getStudCity());
		
		Student updateEmployee=studentRepository.save(std1);
	    return ResponseEntity.ok(updateEmployee);
	}
	
	@GetMapping("/linedata")
	public String getData(@RequestParam("stud_id") long stud_id) {
		System.out.println(stud_id);
		System.out.println("grp"+loginDao);
		List<Integer> res_id = loginDao.fetchResultId(stud_id);
		

		
		List<ModuleResult> resultData = moduleResultRepository.findAllById(res_id);
		

		
	
      JsonArray modName = new JsonArray();
	  JsonArray totMarks = new JsonArray();
  
	  JsonObject json = new JsonObject();
	  
	  resultData.forEach(data->{
		  modName.add(loginDao.fetchModuleName(data.getModule().getModuleId())); 
		  totMarks.add(data.getTotal());
	  });
	 
  
	  json.add("Modules", modName);
	  json.add("Marks", totMarks);
	  return json.toString();
	
	}


}



