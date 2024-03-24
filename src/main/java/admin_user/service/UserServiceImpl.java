package admin_user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import admin_user.dto.UserDto;
import admin_user.model.Result;
import admin_user.model.User;
import admin_user.repositories.ResultRepository;
import admin_user.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ResultRepository resultRepository;
	
	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getRole(), userDto.getFullname(), userDto.getRollnumber(), userDto.getSection(), userDto.getSubject());
		
		if(user.getRole().equals("STUDENT")) {	
			String email = user.getEmail();
			String section = user.getSection();
			Result result = new Result(email,section);
			resultRepository.save(result);
		}
		return userRepository.save(user);
	}

	/*@Override
	public User saveOrUpdate(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(rollnum);
		user.setSection(section);
		
		Result result = resultRepository.findByRollnumber(rollnum)
		return null;
	}*/

	
	/*
	 * public User findEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		return null;
	}
	*/
	
	// passwordEncoder.encode(userDto.getPassword());
	
}
