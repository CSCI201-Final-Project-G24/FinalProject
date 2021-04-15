package com.usc.brainattacker;

import com.usc.brainattacker.controller.UserController;
import com.usc.brainattacker.entity.User;
import com.usc.brainattacker.vo.Result;

public class UserTests {
    UserController usercontroller;

    public boolean testAddExisted(){
        User user = new User("Yuxuan Guo","12345678");
        Result result = usercontroller.add(user);
        return result.isFlag() == false;
    }

    public boolean testGetStatisticFromNull(){
        int token = 2222;
        Result result = usercontroller.statistics(token);
        return result.isFlag() == false;
    }

    public boolean testGetAuthenticatedFromNull(){
        User user = new User("invalid username","12345678");
        Result result = usercontroller.authenticate(user);
        return result.isFlag() == false;
    }
}
