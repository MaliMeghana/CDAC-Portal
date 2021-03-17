package in.edac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.edac.model.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer> {

}
