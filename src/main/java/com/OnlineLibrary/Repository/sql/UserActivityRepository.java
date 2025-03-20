package com.OnlineLibrary.Repository.sql;

import com.OnlineLibrary.Entities.sql.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
}