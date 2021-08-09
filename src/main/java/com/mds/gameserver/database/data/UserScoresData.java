package com.mds.gameserver.database.data;

import com.mds.gameserver.views.ScoresInfo;

import javax.persistence.*;

@Entity
@Table(name = "usersscore")
public class UserScoresData {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public ScoresInfo createPlayerInfo(){
        ScoresInfo scoresInfo = new ScoresInfo();
        scoresInfo.score=score;
        scoresInfo.name=name;
        return scoresInfo;
    }
}
