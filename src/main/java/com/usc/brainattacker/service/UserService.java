package com.usc.brainattacker.service;

import com.usc.brainattacker.entity.Statistic;
import com.usc.brainattacker.entity.User;

public interface UserService {
	void add(User user);
	boolean authenticate(String username, String password);
	Statistic statistics(User user);
}
