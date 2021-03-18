package in.edac.controller;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.edac.dao.StudentDao;
import in.edac.model.ModuleResult;
import in.edac.model.Student;
import in.edac.repository.ModuleResultRepository;
import in.edac.service.ExcelExporter;
import in.edac.service.ExportService;
import in.edac.service.StudentService;
import in.edac.service.UploadService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/result")
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ModuleResultRepository moduleResultRepository;
	
	@Autowired
	private ExportService exportService;
	
	@Autowired
	private StudentDao studentDao;
	
	@PostMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile file,int module_id,int batch_id){
		try {
			List<ModuleResult> list=uploadService.upload(file,module_id,batch_id);
			//for(ModuleResult res:list) {
				moduleResultRepository.saveAll(list);
			//}
				for(ModuleResult res:list) {
				   System.out.println(list);
				}
				studentDao.getRank(module_id, batch_id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	@PostMapping("/search-one")
	public ResponseEntity<Student> searchStudent(@RequestBody Student std){
		Student std1=studentService.getStudent(std);
		return ResponseEntity.ok(std1);
	}
	
	@GetMapping("/users/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<ModuleResult> listUsers = exportService.listAll();
        System.out.println(listUsers);
         
        ExcelExporter excelExporter = new ExcelExporter(listUsers);
         
        excelExporter.export(response);    
    }  
}
