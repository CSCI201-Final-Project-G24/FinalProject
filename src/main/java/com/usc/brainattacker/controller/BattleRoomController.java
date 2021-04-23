package com.usc.brainattacker.controller;

import com.usc.brainattacker.entity.BattleRoom;
import com.usc.brainattacker.entity.Server;
import com.usc.brainattacker.entity.User;
import com.usc.brainattacker.service.UserService;
import com.usc.brainattacker.util.MessageConstant;
import com.usc.brainattacker.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/battleroom")
public class BattleRoomController {
    @Autowired
    UserService userService;

    @PostMapping("/requestScore")
    public Result getScore(@RequestBody int token, @RequestBody int roomNumber){
        try {
            BattleRoom br = Server.server.getBattleroom(roomNumber);
            String username = userService.usernameGet(token);
            int result = br.findOpponentScore(username);
            return new Result(true, MessageConstant.ADD_ROLE_LIST_SUCCESS,result);
        } catch (Exception ex){
            ex.printStackTrace();
            return new Result(false, MessageConstant.ADD_ROLE_LIST_FAIL);
        }

    }

    @PostMapping("/winCondition")
    public Result winCondition(@RequestBody int token, @RequestBody int roomNumber){
        try {
            boolean condition;
            BattleRoom br = Server.server.getBattleroom(roomNumber);
            String username = userService.usernameGet(token);
            if(username == br.getP1Name()) condition = br.getP2Condition();
            else condition = br.getP1Condition();
            return new Result(true, MessageConstant.GET_PERMISSION_LIST_SUCCESS, condition);
        } catch (Exception ex){
            ex.printStackTrace();
            return new Result(false, MessageConstant.GET_PERMISSION_LIST_FAIL);
        }
    }

    @PostMapping("/addPoints")
    public Result addPoint(@RequestBody int token, @RequestBody int roomNumber){
        try{
            BattleRoom br = Server.server.getBattleroom(roomNumber);
            String username = userService.usernameGet(token);
            br.addPointForPlayer(username);
            return new Result(true, MessageConstant.ADD_ROLE_LIST_SUCCESS);
        } catch (Exception ex){
            ex.printStackTrace();
            return new Result(false, MessageConstant.ADD_ROLE_LIST_FAIL);
        }
    }


}
