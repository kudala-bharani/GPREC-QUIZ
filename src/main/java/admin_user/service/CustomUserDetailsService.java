package admin_user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import admin_user.model.User;
import admin_user.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	 @Autowired
	 private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		return new CustomUserDetail(user);
	}
	

	/*public User loadUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);		
		return user;
	}*/
	/*
	public UserDetails loadByRollNumber(String rollNumber) throws UsernameNotFoundException {
        // Implement this method to load user details by roll number
        // Example code:
         UserDetails userDetails = userRepository.findByRollNumber(rollNumber);
         if (userDetails == null) {
             throw new UsernameNotFoundException("User not found");
         }
         return userDetails;
    }*/

}
