package in.edac.service;



import java.util.List;

import javax.transaction.Transactional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import in.edac.model.ModuleResult;
import in.edac.repository.ModuleResultRepository;


 
@Service
@Transactional
public class ExportService {
     
    @Autowired
    private ModuleResultRepository moduleResultRepository;
     
    public List<ModuleResult> listAll() {
    	return moduleResultRepository.findAll();
    }
     
}
