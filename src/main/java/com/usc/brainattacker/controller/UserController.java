package com.usc.brainattacker.controller;


import com.usc.brainattacker.entity.BattleRoom;
import com.usc.brainattacker.entity.Statistic;
import com.usc.brainattacker.entity.User;
import com.usc.brainattacker.service.UserService;
import com.usc.brainattacker.util.MessageConstant;
import com.usc.brainattacker.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
			if(valid)return new Result(true, MessageConstant.ADD_USER_SUCCESS);
			else return new Result(false, MessageConstant.ADD_USER_FAIL);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, MessageConstant.ADD_USER_FAIL);
		}
	}

	@PostMapping("/authenticate")
	public Result authenticate(@RequestBody User user) {
		try {
			boolean authenticated = userService.authenticate(user.getUsername(), user.getPassword());
			if(authenticated){
				return new Result(true, MessageConstant.QUERY_USER_SUCCESS);
			}else{
				return new Result(false, "Invalid Login");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, MessageConstant.QUERY_USER_FAIL);
		}
	}

	@PostMapping("/statistics")
	public Result statistics(@RequestBody User user) {
		try {
			Statistic statistic = userService.statistics(user);
			if(statistic == null) return new Result(false, "user does not exist");
			return new Result(true, MessageConstant.QUERY_USER_SUCCESS, statistic);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, MessageConstant.QUERY_USER_FAIL);
		}
	}

	@PostMapping("/findBattle")
	public Result findBattle(@RequestBody User user, @RequestParam int roomNumber) {
		try {
			BattleRoom br = userService.findBattle(user, roomNumber);
			if(br == null) return new Result(false, "This room is already full");
			return new Result(true, MessageConstant.QUERY_USER_SUCCESS, br);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, MessageConstant.QUERY_USER_FAIL);
		}
	}

	@PostMapping("/goBattle")
	public Result goBattle(@RequestBody User user) {
		try {
			BattleRoom br = userService.goBattle(user);
			return new Result(true, MessageConstant.QUERY_USER_SUCCESS, br);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, MessageConstant.QUERY_USER_FAIL);
		}
	}

}
