/**
 * 
 */
package com.simiyu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simiyu.model.Role;

/**
 * @author enock
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query(
			value = "SELECT * FROM role r WHERE r.name = ?1",
			nativeQuery = true
			)
	
	List<Role> findRoleByName(String name);
}
