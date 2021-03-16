package in.edac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.edac.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
