package com.usc.brainattacker.controller;


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
			userService.add(user);
			return new Result(true, MessageConstant.ADD_USER_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, MessageConstant.ADD_USER_FAIL);
		}
	}
}
