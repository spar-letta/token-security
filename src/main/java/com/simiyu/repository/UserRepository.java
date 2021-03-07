/**
 * 
 */
package com.simiyu.repository;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simiyu.model.User;

/**
 * @author enock
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
}
