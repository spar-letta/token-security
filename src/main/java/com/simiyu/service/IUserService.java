/**
 * 
 */
package com.simiyu.service;

import java.util.Optional;

import com.simiyu.model.User;

/**
 * @author enock
 *
 */
public interface IUserService {

	long saveUser(User user );
	Optional<User> findByUsername(String username);
}
