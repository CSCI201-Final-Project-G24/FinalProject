package com.usc.brainattacker.entity;

import java.util.*;

public class Server {
    private static int battleRoomNumber = 0;
    private static Map<Integer, BattleRoom> battleRoomList = Collections.synchronizedMap(new HashMap<Integer, BattleRoom>());
    public static Server server = new Server();

    public Server(){
    }

    public BattleRoom startBattleRoom(String username){
        while(battleRoomList.containsKey(battleRoomNumber)){
            battleRoomNumber++;
        }
        BattleRoom br = new BattleRoom(username, battleRoomNumber);
        battleRoomList.putIfAbsent(battleRoomNumber++, br);
        return br;
    }

    // will test that this room number does not exist
    private BattleRoom startSpecificBattleRoom(String username, int roomNumber){
        BattleRoom br = new BattleRoom(username, roomNumber);
        battleRoomList.putIfAbsent(roomNumber, br);
        return br;
    }

    public boolean roomCreated(int roomNumber){
        return battleRoomList.containsKey(roomNumber);
    }

    public BattleRoom getRoom(int roomNumber){
        if(roomCreated(roomNumber)){
            return battleRoomList.get(roomNumber);
        }else return null;
    }

    // if want to go to specific room number, call this function, both create of join is ok
    public BattleRoom findBattleRoom(String username, int roomNumber){
        if(!battleRoomList.isEmpty()) {
            if(battleRoomList.containsKey(roomNumber)){
                BattleRoom br = battleRoomList.get(roomNumber);
                if(br.stillValid()){
                    br.addUser(username);
                    return br;
                }else return null;

            }
        }return startSpecificBattleRoom(username, roomNumber);
    }

    // if just want to battle call this
    public BattleRoom getABattleRoom(String username){
        if(!battleRoomList.isEmpty()){
            for (BattleRoom br : battleRoomList.values())
                if(br.stillValid()){
                    br.addUser(username);
                    return br;
                }
        }
        
        return startBattleRoom(username);
        
    }
}
