package com.mds.gameserver.service;

import com.mds.gameserver.database.repository.UsersRepository;
import com.mds.gameserver.database.data.UserData;
import com.mds.gameserver.views.PlayerInfo;
import com.mds.gameserver.views.Scores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.management.PlatformLoggingMXBean;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private final UsersRepository usersRepository;
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public void createUsers(UserData user) {
        usersRepository.save(user);
    }
    public void newScore(PlayerInfo playerInfo){
        List<UserData> list= usersRepository.findAllByName(playerInfo.name);
        if(list.isEmpty()){
            usersRepository.save(createUserData(playerInfo));
        } else {
            UserData userData = list.get(0);
            if(playerInfo.getScore()>userData.getScore()){
                usersRepository.deleteById(userData.getId());
                usersRepository.save(createUserData(playerInfo));
            }
        }
    }
    public Scores getTop10(){
        Pageable pageable = PageRequest.of(0, 10).withSort(Sort.by("score").descending());
        Page<UserData> page = usersRepository.findAll(pageable);
        List<PlayerInfo> playerInfoList = new ArrayList<PlayerInfo>();
        for(UserData userData:page.toList()){
            playerInfoList.add(userData.createPlayerInfo());
        }
        Scores scores = new Scores();
        scores.setList(playerInfoList);
        return scores;
    }
    public void deleteScore(){
    }
    private UserData createUserData(PlayerInfo playerInfo){
        UserData userData = new UserData();
        userData.setName(playerInfo.name);
        userData.setScore(playerInfo.score);
        return userData;
    }
}
