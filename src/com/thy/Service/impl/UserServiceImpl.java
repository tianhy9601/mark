package com.thy.Service.impl;

import com.thy.Bean.User;
import com.thy.Dao.UserDao;
import com.thy.Dao.impl.UserDaoImpl;
import com.thy.Service.UserService;


import java.sql.ResultSet;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao ud=new UserDaoImpl();

    @Override
    public ResultSet selectUser(User user) {
        return ud.selectUser(user);
    }

    @Override
    public int updatePassword(int id, String newPassword) {
        return ud.updatePassword(id,newPassword);
    }

    @Override
    public List<User> selectUserInfo() {
        return ud.selectUserInfo();
    }

    @Override
    public User selectUserSingle(int id) {
        return ud.selectUserSingle(id);
    }

    @Override
    public int updateInfo(User user) {
        return ud.updateInfo(user);
    }

    @Override
    public int deleteById(long id) {
        return ud.deleteById(id);
    }

    @Override
    public int addUser(User user) {
        return ud.addUser(user);
    }

    @Override
    public List<User> selectUserByName(String uname) {
        return ud.selectUserByName(uname);
    }

    @Override
    public boolean checkName(String userName) {
        return ud.checkName(userName);
    }
}
