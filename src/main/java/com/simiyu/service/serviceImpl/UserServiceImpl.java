/**
 * 
 */
package com.simiyu.service.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.simiyu.model.User;
import com.simiyu.repository.RoleRepository;
import com.simiyu.repository.UserRepository;
import com.simiyu.service.IUserService;

/**
 * @author enock
 *
 */
@Service
public class UserServiceImpl implements IUserService, UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	// save method
	@Override
	public long saveUser(User user) {
		
		user.setPassword(
					pwdEncoder.encode(user.getPassword())
					);
		user.setRoles(roleRepository.findRoleByName("USER"));
		return userRepository.save(user).getId();
	}

	// get user by username
	@Override
	public Optional<User> findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
	//---------------------------------------------------------//
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = findByUsername(username);
		if(opt.isEmpty()) 
			throw new UsernameNotFoundException("User Not Exist");
		//Read User from Db
		User user = opt.get();
//		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (com.simiyu.model.Role role : user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
		return new org.springframework.security.core.userdetails.User(
				username, 
				user.getPassword(), 
				//grantedAuthorities
				getAuthorities(user)
				);
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
