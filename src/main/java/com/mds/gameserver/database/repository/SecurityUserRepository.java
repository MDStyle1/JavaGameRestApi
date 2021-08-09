package com.mds.gameserver.database.repository;

import com.mds.gameserver.database.data.SecurityUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityUserRepository extends JpaRepository<SecurityUserDetail,Long> {
    SecurityUserDetail findByName(String name);
}
