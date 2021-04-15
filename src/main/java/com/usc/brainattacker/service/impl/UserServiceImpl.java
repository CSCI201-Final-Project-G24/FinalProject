package com.usc.brainattacker.service.impl;

import com.usc.brainattacker.entity.*;
import com.usc.brainattacker.mapper.UserMapper;
import com.usc.brainattacker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public int getToken(User user) {
		int token = userMapper.getUserID(user.getUsername());
		return token;
	}

	@Override
	public boolean add(User user) {
		int occupied = userMapper.ifOccupied(user.getUsername());
		if(occupied == 0){
			userMapper.add(user);
			int uid = userMapper.getUserID(user.getUsername());
			userMapper.addProfile();
			return true;
		}else return false;

	}

	@Override
	public boolean authenticate(String username, String password) {
		int occupied = userMapper.ifOccupied(username);
		if(occupied == 0) return false;
		String validPassword = userMapper.getUserPassword(username);
		return password.equals(validPassword);
	}

	@Override
	public Statistic statistics(int token){
		String username = userMapper.getUsername(token);
		int occupied = userMapper.ifOccupied(username);
		if(occupied == 0) return null;
		int winNumber = userMapper.getUserWinNumber(token);
		int gameNumber = userMapper.getUserGameNumber(token);
		return new Statistic(winNumber, gameNumber, token);
	}

	@Override
	public int createBattle(int token, int roomNumber) {
		String username = userMapper.getUsername(token);
		if(roomNumber != -1){ // specific roomnumber
			if(Server.server.roomCreated(roomNumber)) return -1;
			BattleRoom br = Server.server.findBattleRoom(username, roomNumber);
			return br.getRoomNumber();
		}else {
			BattleRoom br = Server.server.startBattleRoom(username);
			return br.getRoomNumber();
		}
	}

	@Override
	public int findBattle(int token, int roomNumber){
		String username = userMapper.getUsername(token);
		BattleRoom br = Server.server.findBattleRoom(username, roomNumber);
		if(br == null) return -1;
		return br.getRoomNumber();
	}

	@Override
	public int goBattle(int token){
		String username = userMapper.getUsername(token);
		BattleRoom br = Server.server.getABattleRoom(username);
		return br.getRoomNumber();
	}

	@Override
	public RequestRoom checkRoom(int token, int roomNumber) {
		BattleRoom br =  Server.server.getRoom(roomNumber);
		if(br.stillValid()){// not full yet
			return null;
		}
		String username = userMapper.getUsername(token);
		String opponent = br.findOpponent((username));
		return new RequestRoom(roomNumber,opponent);
	}
}
