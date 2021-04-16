
package com.usc.brainattacker;

import com.usc.brainattacker.controller.UserController;
import com.usc.brainattacker.entity.BattleRoom;
import com.usc.brainattacker.entity.User;
import com.usc.brainattacker.vo.Result;
public class BattleRoomTests {
    BattleRoom battleRoom;
    public void testBattleRoom(String username, int roomNum){
        battleRoom=new BattleRoom(username, roomNum);
    }
    /*
    public void testAddUser(){
        User user2 = new User("invalid username","12345678");
        battleRoom.addUser(user2);
    }*/

}
