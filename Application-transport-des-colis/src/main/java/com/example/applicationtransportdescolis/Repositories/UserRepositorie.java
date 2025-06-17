package com.example.applicationtransportdescolis.Repositories;
import com.example.applicationtransportdescolis.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositorie extends JpaRepository<User , Long> {
    User findByUsername(String username);


    @Query("SELECT u FROM User u WHERE u.username = :param")
    User findByUsernameOrEmail(@Param("param") String param);
}

