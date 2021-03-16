package in.edac.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.edac.model.Results;
import in.edac.repository.ResultsRepository;
import in.edac.service.UploadService;


@RestController
public class UploadController {
	
	@Autowired
	private ResultsRepository resultsRepository;
	
	@Autowired
	private final UploadService uploadService;
	
	public UploadController(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	@PostMapping("/upload")
	public List<Results> upload(@RequestParam("file") MultipartFile file) throws Exception{
		List<Results> list=uploadService.upload(file);
		for(Results res:list) {
			resultsRepository.save(res);
		}
		return uploadService.upload(file);
		
	}
}