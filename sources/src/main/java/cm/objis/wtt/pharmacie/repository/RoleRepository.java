package cm.objis.wtt.pharmacie.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.objis.wtt.pharmacie.domaine.entities.Role;



@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByRole(String role);

}
