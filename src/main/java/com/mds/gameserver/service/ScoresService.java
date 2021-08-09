package com.mds.gameserver.service;

import com.mds.gameserver.database.repository.ScoresRepository;
import com.mds.gameserver.database.data.UserScoresData;
import com.mds.gameserver.views.ScoresInfo;
import com.mds.gameserver.views.Scores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScoresService {
    @Autowired
    private final ScoresRepository scoresRepository;
    public ScoresService(ScoresRepository scoresRepository) {
        this.scoresRepository = scoresRepository;
    }
    public void newScore(ScoresInfo scoresInfo){
        List<UserScoresData> list= scoresRepository.findAllByName(scoresInfo.name);
        if(list.isEmpty()){
            scoresRepository.save(createUserData(scoresInfo));
        } else {
            UserScoresData userScoresData = list.get(0);
            if(scoresInfo.getScore()> userScoresData.getScore()){
                userScoresData.setScore(scoresInfo.score);
                scoresRepository.save(userScoresData);
            }
        }
    }
    public Scores getTop10(){
        Pageable pageable = PageRequest.of(0, 10).withSort(Sort.by("score").descending());
        Page<UserScoresData> page = scoresRepository.findAll(pageable);
        List<ScoresInfo> scoresInfoList = new ArrayList<ScoresInfo>();
        for(UserScoresData userScoresData :page.toList()){
            scoresInfoList.add(userScoresData.createPlayerInfo());
        }
        Scores scores = new Scores();
        scores.setList(scoresInfoList);
        return scores;
    }
    public void deleteScore(){
    }
    private UserScoresData createUserData(ScoresInfo scoresInfo){
        UserScoresData userScoresData = new UserScoresData();
        userScoresData.setName(scoresInfo.name);
        userScoresData.setScore(scoresInfo.score);
        return userScoresData;
    }
}
