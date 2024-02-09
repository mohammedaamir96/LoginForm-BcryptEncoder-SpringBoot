package com.example.formlogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.formlogin.pojo.UserRegistrationPOJO;
import com.example.formlogin.repo.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserDetailsServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public boolean getUser(UserRegistrationPOJO user) {
		UserRegistrationPOJO newuser = userRepo.findByUsername(user.getUsername());

		if(newuser != null)
			if(passwordEncoder.matches(user.getPassword(), newuser.getPassword()))
				return true;
		return false;
	}
	
	@Override
	public UserRegistrationPOJO saveUser(UserRegistrationPOJO user) {

		String password=passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRole("ROLE_USER");
		UserRegistrationPOJO newuser = userRepo.save(user);

		return newuser;
	}

	@Override
	public void removeSessionMessage() {

		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");
	}
}
