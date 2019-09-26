package com.thy.Service;

import com.thy.Bean.User;

import java.sql.ResultSet;
import java.util.List;

public interface UserService {
    ResultSet selectUser(User user);

    int updatePassword(int id, String newPassword);

    List<User> selectUserInfo();

    User selectUserSingle(int id);

    int updateInfo(User user);

    int deleteById(long id);

    int addUser(User user);

    List<User> selectUserByName(String uname);

    boolean checkName(String userName);
}
