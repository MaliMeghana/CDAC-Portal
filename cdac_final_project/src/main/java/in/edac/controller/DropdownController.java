package in.edac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.edac.model.Batch;
import in.edac.model.Module;
import in.edac.repository.BatchRepository;
import in.edac.repository.ModuleRepository;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/dropdown")
public class DropdownController {

	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private BatchRepository batchRepository;
	
	@GetMapping("/modules")
	public List<Module> getModules(){
		return moduleRepository.findAll();
	}
	
	@GetMapping("/batchs")
	public List<Batch> getBatches(){
		return batchRepository.findAll();
	}
}
