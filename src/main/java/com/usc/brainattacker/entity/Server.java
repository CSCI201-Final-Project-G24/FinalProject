package com.usc.brainattacker.entity;

import java.util.*;

public class Server {
    private static int battleRoomNumber = 0;
    private static Map<Integer, BattleRoom> battleRoomList = Collections.synchronizedMap(new HashMap<Integer, BattleRoom>());
    public static Server server = new Server();

    public Server(){
    }

    private BattleRoom startBattleRoom(User user){
        while(battleRoomList.containsKey(battleRoomNumber)){
            battleRoomNumber++;
        }
        BattleRoom br = new BattleRoom(user, battleRoomNumber);
        battleRoomList.putIfAbsent(battleRoomNumber++, br);
        return br;
    }

    // will test that this room number does not exist
    private BattleRoom startSpecificBattleRoom(User user, int roomNumber){
        BattleRoom br = new BattleRoom(user, roomNumber);
        battleRoomList.putIfAbsent(roomNumber, br);
        return br;
    }

    // if want to go to specific room number, call this function
    public BattleRoom findBattleRoom(User user, int roomNumber){
        if(!battleRoomList.isEmpty()) {
            if(battleRoomList.containsKey(roomNumber)){
                BattleRoom br = battleRoomList.get(roomNumber);
                if(br.stillValid()){
                    br.addUser(user);
                    return br;
                }else return null;

            }
        }return startSpecificBattleRoom(user, roomNumber);
    }

    // if just want to battle call this
    public BattleRoom getABattleRoom(User user){
        if(!battleRoomList.isEmpty()){
            for (BattleRoom br : battleRoomList.values())
                if(br.stillValid()){
                    br.addUser(user);
                    return br;
                }
        }
        
        return startBattleRoom(user);
        
    }
}
