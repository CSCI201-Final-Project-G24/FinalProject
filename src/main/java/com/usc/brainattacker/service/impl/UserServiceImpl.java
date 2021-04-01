package com.usc.brainattacker.service.impl;

import com.usc.brainattacker.entity.Statistic;
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

	@Override
	public boolean authenticate(String username, String password) {
		String validPassword = userMapper.getUserPassword(username);
		return password == validPassword;
	}

	@Override
	public Statistic statistics(User user){
		
		int winNumber = userMapper.getUserWinNumber(user.getUsername());
		int gamenumber = userMapper.getUserGameNumber(user.getUsername());
		Statistic retu = new Statistic(winNumber, gamenumber);
		return retu;
	}
}
