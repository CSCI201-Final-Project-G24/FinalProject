package com.usc.brainattacker.controller;

import com.usc.brainattacker.entity.BattleRoom;
import com.usc.brainattacker.entity.Server;
import com.usc.brainattacker.entity.User;
import com.usc.brainattacker.util.MessageConstant;
import com.usc.brainattacker.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/battleroom")
public class BattleRoomController {
    @PostMapping("/requestScore")
    public Result getScore(@RequestBody String username, @RequestBody int roomnum){
        try {
            BattleRoom br = Server.server.getBattleroom(roomnum);
            int result = br.findOpponentScore(username);
            return new Result(true, MessageConstant.ADD_ROLE_LIST_SUCCESS,result);
        } catch (Exception ex){
            ex.printStackTrace();
            return new Result(false, MessageConstant.ADD_ROLE_LIST_FAIL);
        }

    }

    @PostMapping("/winCondition")
    public Result winCondition(@RequestBody String username, @RequestBody int roomnum){
        try {
            boolean condition;
            BattleRoom br = Server.server.getBattleroom(roomnum);
            if(username == br.getP1Name()) condition = br.getP2Condition();
            else condition = br.getP1Condition();
            return new Result(true, MessageConstant.GET_PERMISSION_LIST_SUCCESS, condition);
        } catch (Exception ex){
            ex.printStackTrace();
            return new Result(false, MessageConstant.GET_PERMISSION_LIST_FAIL);
        }
    }


}
