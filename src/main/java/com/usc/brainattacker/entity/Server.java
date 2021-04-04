package com.usc.brainattacker.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private static List<BattleRoom> battleRoomList = Collections.synchronizedList(new ArrayList<BattleRoom>());
    public static Server server = new Server();

    public Server(){
    }

    public BattleRoom startBattleRoom(User user){
        BattleRoom br = new BattleRoom(user);
        battleRoomList.add(br);
        return br;
    }

    public BattleRoom getLatestBattleRoom(User user){
        if(!battleRoomList.isEmpty()){
            BattleRoom br = battleRoomList.get(battleRoomList.size()-1);
            if(br.stillValid()){
                br.addUser(user);
                return br;
            }
        }
        
        return startBattleRoom(user);
        
    }
}
