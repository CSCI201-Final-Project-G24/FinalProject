package com.usc.brainattacker.service.impl;

import com.usc.brainattacker.entity.BattleRoom;
import com.usc.brainattacker.entity.Server;
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
	public Statistic statistics(User user){
		int occupied = userMapper.ifOccupied(user.getUsername());
		if(occupied == 0) return null;
		int uid = userMapper.getUserID(user.getUsername());
		int winNumber = userMapper.getUserWinNumber(uid);
		int gameNumber = userMapper.getUserGameNumber(uid);
		return new Statistic(winNumber, gameNumber);
	}

	@Override
	public BattleRoom findBattle(User user, int roomNumber){
		BattleRoom br = Server.server.findBattleRoom(user, roomNumber);
		return br;
	}

	@Override
	public BattleRoom goBattle(User user){
		BattleRoom br = Server.server.getABattleRoom(user);
		return br;
	}

}
