package in.edac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.edac.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
