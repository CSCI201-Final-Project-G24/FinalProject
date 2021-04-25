package com.usc.brainattacker.controller;


import com.usc.brainattacker.entity.BattleRoom;
import com.usc.brainattacker.entity.RequestRoom;
import com.usc.brainattacker.entity.Statistic;
import com.usc.brainattacker.entity.User;
import com.usc.brainattacker.service.UserService;
import com.usc.brainattacker.util.MessageConstant;
import com.usc.brainattacker.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * 用户操作
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;


	@PostMapping("/add")
	public Result add(@RequestBody User user) {
		try {
			boolean valid = userService.add(user);
			int token = userService.getToken(user);
			if(valid)return new Result(true, MessageConstant.ADD_USER_SUCCESS, token);
			else return new Result(false, MessageConstant.ADD_USER_FAIL);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, MessageConstant.ADD_USER_FAIL);
		}
	}

	@PostMapping("/authenticate")
	public Result authenticate(@RequestBody User user) {
		try {
			if(user.getUsername().equals("") && user.getPassword().equals("")){
				return new Result(true, "visitor login", userService.addVisitor());
			}
			boolean authenticated = userService.authenticate(user.getUsername(), user.getPassword());
			if(authenticated){
				int token = userService.getToken(user);
				return new Result(true, MessageConstant.QUERY_USER_SUCCESS, token);
			}else{
				return new Result(false, "Invalid Login");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, MessageConstant.QUERY_USER_FAIL);
		}
	}

	@PostMapping("/statistics")
	public Result statistics(@RequestParam int token) {
		try {
			Statistic statistic = userService.statistics(token);
			if(statistic == null) return new Result(false, "user does not exist");
			return new Result(true, MessageConstant.QUERY_USER_SUCCESS, statistic);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, MessageConstant.QUERY_USER_FAIL);
		}
	}

	@PostMapping("/findBattle")
	public Result findBattle(@RequestParam String type, @RequestParam int token, @RequestParam int roomNumber) {
		try {
			if(type.equals("waiting")){
				RequestRoom request = userService.checkRoom(token,roomNumber);
				return new Result(true, "success", request);
			}else if(type.equals("create")){
				roomNumber = userService.createBattle(token,roomNumber);
				if(roomNumber == -1) return new Result(false, "This room is already created or occupied");
				else return new Result(true, MessageConstant.QUERY_USER_SUCCESS, roomNumber);
			}else if(type.equals("join")) {
				if (roomNumber == -1) roomNumber = userService.goBattle(token);
				else roomNumber = userService.findBattle(token,roomNumber);
				if (roomNumber == -1) return new Result(false, "This room is already full");
				return new Result(true, MessageConstant.QUERY_USER_SUCCESS, roomNumber);
			}else{ // undefined type
				return new Result (false, "Wrong Type");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, MessageConstant.QUERY_USER_FAIL);
		}
	}

	/*
	@PostMapping("updataStats")
	public Result updateStats(@RequestParam int token, @RequestParam boolean win){
		try{
			String username = userService.usernameGet(token);
			userService.updateStat(username,win);
			return new Result(true,"ok");
		}catch(Exception e){
			e.printStackTrace();
			return new Result(false,"bad");
		}
	}*/
	/*
	@PostMapping("/goBattle")
	public Result goBattle(@RequestBody User user) {
		try {
			int roomNumber = userService.goBattle(user);
			return new Result(true, MessageConstant.QUERY_USER_SUCCESS, roomNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, MessageConstant.QUERY_USER_FAIL);
		}
	}
*/
}
// opponent name return "" or zhende, question object 
// waiting de shihou zheyang
// 