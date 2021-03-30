package com.usc.brainattacker.service.impl;

import com.usc.brainattacker.entity.User;
import com.usc.brainattacker.mapper.UserMapper;
import com.usc.brainattacker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void add(User user) {
		userMapper.add(user);
	}
}
