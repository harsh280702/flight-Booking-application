package com.BookingApplication.UserApi.repostiry;

import com.BookingApplication.UserApi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepostiry extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityByUserName(String userName);
    boolean existsByEmail(String email);


    UserEntity findByEmail(String email);

    Optional<UserEntity> findByUserName(String userNamer);

}
