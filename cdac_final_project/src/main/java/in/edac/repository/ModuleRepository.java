package in.edac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.edac.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer> {

}
