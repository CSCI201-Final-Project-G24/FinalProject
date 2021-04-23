package com.usc.brainattacker.service;

import com.usc.brainattacker.entity.*;

import java.util.ArrayList;

public interface UserService {
	public ArrayList<String> visitorList = new ArrayList<>();
	int getToken(User user);
	boolean add(User user);
	boolean authenticate(String username, String password);
	Statistic statistics(int token);
	int createBattle(int token, int roomNumber);
	int findBattle(int token, int roomNumber);
	int goBattle(int token);
	RequestRoom checkRoom(int token, int roomNumber);
	void updateStat(String username, boolean win);
	Visitor addVisitor();
	String usernameGet(int token);
}
