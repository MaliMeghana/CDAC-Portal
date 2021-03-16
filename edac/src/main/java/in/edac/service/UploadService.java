package in.edac.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.edac.model.Results;
import in.edac.repository.UploadUtil;



@Service
public class UploadService {
	
	private final UploadUtil uploadUtil;
	
	public UploadService(UploadUtil uploadUtil) {
		this.uploadUtil = uploadUtil;
	}

	public List<Results> upload(MultipartFile file) throws Exception {

		Path tempDir = Files.createTempDirectory("");

		File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
		
		file.transferTo(tempFile);
		
		String fileNames = file.getOriginalFilename();

	    DataFormatter formatter = new DataFormatter();

//	    File file = new File("./Results/" + fileNames);
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

	    List<Results> content = new ArrayList<>();
	    for (int r = headerRowNum + 1; r <= sheet.getLastRowNum(); r++) {
	        row = sheet.getRow(r);
	        if (row == null)
	            continue; //SKIP, don't bother creating empty stuff!
	        Results result = new Results();
	        for (Map.Entry<Integer, String> entry : colHeaders.entrySet()) {
	            int colIdx = entry.getKey();
	            Cell cell = row.getCell(colIdx);
	            if (cell != null) {
	                String cellValue = formatter.formatCellValue(cell, evaluator);
	                switch(entry.getValue()) {
	                     case "id": {
	                          result.setId(Integer.parseInt(cellValue));
	                          break;
	                     }
	                     case "name": {
	                          result.setName(cellValue);
	                           break;
	                     } //example with multiple headers mapping to same field
	                     case "marks": {
	                          result.setMarks(cellValue);
	                           break;
	                     }
	                }
	                //alternatively use if-else block with regex matching or some other technique to map your headers to JPA entity fields
	            }
	        }
	        content.add(result);
	   
	}
	    return content;
	}
}