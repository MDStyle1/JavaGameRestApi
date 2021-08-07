package com.mds.gameserver.views;

import java.util.List;
import java.util.Map;

import com.mds.gameserver.database.data.UserData;

public class Scores {
    public List<PlayerInfo> list;

    public List<PlayerInfo> getList() {
        return list;
    }

    public void setList(List<PlayerInfo> list) {
        this.list = list;
    }
}
