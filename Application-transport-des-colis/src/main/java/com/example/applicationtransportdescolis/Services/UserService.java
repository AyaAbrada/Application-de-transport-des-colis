package com.example.applicationtransportdescolis.Services;
import com.example.applicationtransportdescolis.Entities.Role;
import com.example.applicationtransportdescolis.Entities.User;
import com.example.applicationtransportdescolis.Repositories.UserRepositorie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepositorie userRepositorie;

    public UserService(UserRepositorie userRepositorie) {
        this.userRepositorie = userRepositorie;
    }

    public List<User> allUser() {
        return userRepositorie.findAll();
    }

}