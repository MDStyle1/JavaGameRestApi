package com.mds.gameserver.database.repository;

import com.mds.gameserver.database.data.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<UserData,Long> {
    List<UserData> findAllByName(String name);
}
