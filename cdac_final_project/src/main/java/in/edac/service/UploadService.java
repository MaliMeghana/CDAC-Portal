package in.edac.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.edac.exception.ResourceNotFoundException;
import in.edac.model.Batch;
import in.edac.model.Module;
import in.edac.model.ModuleResult;
import in.edac.model.Student;
import in.edac.repository.ModuleRepository;
import in.edac.repository.StudentRepository;
import in.edac.repository.BatchRepository;


@Service
public class UploadService {
	
	@Autowired
	private StudentRepository studentRepository;

	
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private BatchRepository batchRepository;
	
	public List<ModuleResult> upload(MultipartFile file, int module_id,int batch_id) throws Exception {

		Path tempDir = Files.createTempDirectory("");

		File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
		
		file.transferTo(tempFile);
		
		String fileNames = file.getOriginalFilename();

	    DataFormatter formatter = new DataFormatter();


	    Workbook workbook = WorkbookFactory.create(tempFile);

	    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

	    Sheet sheet = workbook.getSheetAt(0);

	    int headerRowNum = sheet.getFirstRowNum();

	    Map<Integer, String> colHeaders = new HashMap<Integer, String>();
	    Row row = sheet.getRow(headerRowNum);
	    for (Cell cell : row) {
	        int colIdx = cell.getColumnIndex();
	        String value = formatter.formatCellValue(cell, evaluator);
	        colHeaders.put(colIdx, value);
	    }

	    List<ModuleResult> content = new ArrayList<>();
	    for (int r = headerRowNum + 1; r <= sheet.getLastRowNum(); r++) {
	        row = sheet.getRow(r);
	        if (row == null)
	        	continue; //SKIP, don't bother creating empty stuff!
	        ModuleResult result = new ModuleResult();
	        for (Map.Entry<Integer, String> entry : colHeaders.entrySet()) {
	            int colIdx = entry.getKey();
	            Cell cell = row.getCell(colIdx);
	            if (cell != null) {
	                String cellValue = formatter.formatCellValue(cell, evaluator);
	                switch(entry.getValue()) {
	                  
	                     case "student": {

	                 		Student std1=studentRepository.findById(Long.parseLong(cellValue))
	                 				.orElseThrow(()-> new ResourceNotFoundException("Student not exists with this id :"+cellValue));	                    	
	                        System.out.println(std1); 
	                 		result.setStudent(std1);
	                          break;
	                     }	                    
	                     case "lab": {
	                          result.setLab(Integer.parseInt(cellValue));
	                           break;
	                     }
	                     case "maxLab": {
	                          result.setMaxLab(Integer.parseInt(cellValue));
	                           break;
	                     }
	                     case "mcq": {
	                          result.setMcq(Integer.parseInt(cellValue));
	                           break;
	                     }
	                     case "maxMcq": {
	                          result.setMaxMcq(Integer.parseInt(cellValue));
	                           break;
	                     }
	                     case "total": {
	                          result.setTotal(Integer.parseInt(cellValue));
	                           break;
	                     }
	                     case "maxTotal": {
	                          result.setMaxTotal(Integer.parseInt(cellValue));
	                           break;
	                     }
	                }
	                Module module=moduleRepository.findById((module_id))
             				.orElseThrow(()-> new ResourceNotFoundException("module not exists with this id :"+cellValue));	                    	  
                  result.setModule(module);
                  
                  Batch batch=batchRepository.findById(batch_id)
           				.orElseThrow(()-> new ResourceNotFoundException("batch not exists with this id :"+cellValue));	                    	  
                result.setBatch(batch);
	                //alternatively use if-else block with regex matching or some other technique to map your headers to JPA entity fields
	            }
	        }
	        content.add(result);
	   
	}
	    return content;
	}

}
