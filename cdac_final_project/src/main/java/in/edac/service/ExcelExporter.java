package in.edac.service;


import java.io.IOException;
import java.util.List;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.edac.dao.LoginDao;

import in.edac.model.ModuleResult;



@Service
public class ExcelExporter {
	
//	@Autowired
//	private ModuleRepository moduleRepository;
	
	@Autowired
    private LoginDao loginDao;
	
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ModuleResult> listUsers;
    

     
    public ExcelExporter(List<ModuleResult> listUsers) {
        this.listUsers = listUsers;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        
        sheet = workbook.createSheet("Users");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "ID", style);  
        createCell(row, 1, "studId", style);
        createCell(row, 2, "ModuleName", style);
        createCell(row, 3, "MaxLab", style);    
        createCell(row, 4, "MaxMcq", style);
        createCell(row, 5, "MaxTotal", style);
        createCell(row, 6, "Lab", style);
        createCell(row, 7, "Mcq", style);
        createCell(row, 8, "Total", style);
      
    
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        
        //LoginDao loginDao = new LoginDao();
     
        
        for (ModuleResult result : listUsers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            
         System.out.println(result.getModule().getModuleId());
           System.out.println("log dao "+loginDao);
            
           String moduleName =  result.getModule().getModuleName();
                    
           
            createCell(row, columnCount++, result.getResultId(), style);
            createCell(row, columnCount++, Long.valueOf(result.getStudent().getStudId()).toString(), style);
            createCell(row, columnCount++, moduleName, style);
            createCell(row, columnCount++, result.getMaxLab(), style);
            createCell(row, columnCount++, result.getMaxMcq(), style);
            createCell(row, columnCount++, result.getMaxTotal(), style);
            createCell(row, columnCount++, result.getLab(), style);
            createCell(row, columnCount++, result.getMcq(), style);
            createCell(row, columnCount++, result.getTotal(), style);
           
            System.out.println(result.getTotal());
            System.out.println(Long.valueOf(result.getStudent().getStudId()).toString());
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
