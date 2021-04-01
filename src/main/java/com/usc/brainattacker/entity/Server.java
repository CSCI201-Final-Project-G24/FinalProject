package com.usc.brainattacker.entity;

import java.util.Collections;
import java.util.List;

public class Server {

    private static List<BattleRoom> battleRoomList = Collections.synchronizedList(new ArrayList<BattleRoom>);
    public BattleRoom startBattleRoom(){
        BattleRoom br = new BattleRoom();
        return br;
    }
}
