package com.example.formlogin.service;

import com.example.formlogin.pojo.UserRegistrationPOJO;

public interface UserService {

	public UserRegistrationPOJO saveUser(UserRegistrationPOJO user);

	public void removeSessionMessage();

	public boolean getUser(UserRegistrationPOJO user);
}
