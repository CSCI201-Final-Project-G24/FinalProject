package com.usc.brainattacker.service;

import com.usc.brainattacker.entity.BattleRoom;
import com.usc.brainattacker.entity.RequestRoom;
import com.usc.brainattacker.entity.Statistic;
import com.usc.brainattacker.entity.User;

public interface UserService {
	int getToken(User user);
	boolean add(User user);
	boolean authenticate(String username, String password);
	Statistic statistics(int token);
	int createBattle(int token, int roomNumber);
	int findBattle(int token, int roomNumber);
	int goBattle(int token);
	RequestRoom checkRoom(int token, int roomNumber);
	void updateStat(String username, boolean win);
}
