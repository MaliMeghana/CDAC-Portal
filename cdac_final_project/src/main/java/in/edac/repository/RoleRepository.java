package in.edac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.edac.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
