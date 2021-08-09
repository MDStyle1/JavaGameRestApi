package com.mds.gameserver.database.repository;

import com.mds.gameserver.database.data.UserScoresData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoresRepository extends JpaRepository<UserScoresData,Long> {
    List<UserScoresData> findAllByName(String name);
}
