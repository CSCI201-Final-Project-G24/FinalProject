package com.usc.brainattacker.service.impl;

import com.usc.brainattacker.entity.*;
import com.usc.brainattacker.mapper.UserMapper;
import com.usc.brainattacker.service.QuestionService;
import com.usc.brainattacker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.RequestingUserName;
import java.util.ArrayList;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {


	public ArrayList<String> visitorList = new ArrayList<String>();

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private QuestionService questionService;


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
			userMapper.addProfile(uid);
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
		if(token < 0) return null;
		String username = userMapper.getUsername(token);
		int occupied = userMapper.ifOccupied(username);
		if(occupied == 0) return null;
		int winNumber = userMapper.getUserWinNumber(token);
		int gameNumber = userMapper.getUserGameNumber(token);
		return new Statistic(winNumber, gameNumber, token);
	}

	@Override
	public void updateStat(String username, boolean win) {
		if(visitorList.contains(username)) return;
		int uid = userMapper.getUserID(username);
		int winNumber = userMapper.getUserWinNumber(uid);
		int gameNumber = userMapper.getUserGameNumber(uid);
		gameNumber++;
		if(win) winNumber++;
		userMapper.updateProfile(uid, winNumber, gameNumber);
	}

	@Override
	public int createBattle(int token, int roomNumber) {
		String username = usernameGet(token);
		if(roomNumber != -1){ // specific roomnumber
			if(Server.server.roomCreated(roomNumber)) return -1;
			BattleRoom br = Server.server.findBattleRoom(username, roomNumber, true);
			return br.getRoomNumber();
		}else {
			BattleRoom br = Server.server.startBattleRoom(username,true);
			return br.getRoomNumber();
		}
	}

	@Override
	public int findBattle(int token, int roomNumber){
		String username = usernameGet(token);
		BattleRoom br = Server.server.findBattleRoom(username, roomNumber, false);
		if(br == null) return -1;
		return br.getRoomNumber();
	}

	@Override
	public int goBattle(int token){
		String username = usernameGet(token);
		BattleRoom br = Server.server.getABattleRoom(username);
		return br.getRoomNumber();
	}

	@Override
	public RequestRoom checkRoom(int token, int roomNumber) {
		BattleRoom br =  Server.server.getRoom(roomNumber);
		if(br.stillValid()){// not full yet
			return null;
		}// if full start the thread
		RequestRoom packge = null;
		String username = usernameGet(token);
		if(!br.gameStart){ // not yet starter
			br.start();
			String opponent = br.findOpponent(username);
			Question[] questions = questionService.returnStruct();
			packge = new RequestRoom(roomNumber,opponent, questions);
			br.packge = packge;
		}else{ // if started
			packge = br.packge;
			String opponent = br.findOpponent(username);
			packge.setOpponent(opponent);
		}
		return packge;
	}

	@Override
	public Visitor addVisitor() {
		int size = visitorList.size();
		int token = -(size + 1);
		Random rand = new Random();
		int random = rand.nextInt(100000);
		String visitor = "" + random;
		while(visitorList.contains(visitor)){
			random = rand.nextInt(100000);
			visitor = "" + random;
		}
		visitorList.add(visitor);
		return new Visitor(token, visitor);
	}

	@Override
	public String usernameGet(int token) {
		if(token < 0){//a visitor
			return visitorList.get(-token - 1);
		}else return userMapper.getUsername(token);
	}
}
