package com.thy.Dao.impl;

import JDBCUtils.BaseDao;
import com.thy.Bean.User;
import com.thy.Dao.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public ResultSet selectUser(User user) {

        String sql="select * from user where userName=? and userpassword=?";
        Object[] obj={user.getUserName(),user.getUserpassword()};
        ResultSet rs = myQuery(obj, sql);

        return rs;
    }

    @Override
    public int updatePassword(int id, String newPassword) {

        String sql="update user set userpassword=? where id=?";
        Object[] obj={newPassword,id};



        return myExecUpdate(obj,sql);
    }

    @Override
    public List<User> selectUserInfo() {

        List<User> list=new ArrayList<>();

        String sql="select * from user";

        ResultSet rs = myQuery(null, sql);
        try {
            while(rs.next()){

                Long id=rs.getLong(1);
                String username=rs.getString(2);
                String password=rs.getString(3);
                String sex=rs.getString(4);
                String birthday=rs.getString(5);
                String userphone=rs.getString(6);
                String userAddress=rs.getString(7);
                String userlei=rs.getString(8);

                User user=new User(id,username,password,sex,birthday,userphone,userAddress,userlei);
                list.add(user);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println(list.size());
                rs.close();
                myClos();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    public User selectUserSingle(int id) {

        String sql="select * from user where id=?";
        Object[] obj={id};
        User user=null;

        ResultSet rs=myQuery(obj,sql);
        try {
            while(rs.next()){
                Long ids=rs.getLong(1);
                String username=rs.getString(2);
                String password=rs.getString(3);
                String sex=rs.getString(4);
                String birthday=rs.getString(5);
                String userphone=rs.getString(6);
                String userAddress=rs.getString(7);
                String userlei=rs.getString(8);
                user=new User(ids,username,password,sex,birthday,userphone,userAddress,userlei);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                myClos();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public int updateInfo(User user) {

        String sql="update user set userName=?,sex=?,birthday=?,userphone=?,userAddress=?,userlei=? where id=?";
        Object[] obj={user.getUserName(),user.getSex(),user.getBirthday(),user.getUserphone(),user.getUserAddress(),user.getUserlei(),user.getId()};

        return myExecUpdate(obj,sql);
    }

    @Override
    public int deleteById(long id) {

        String sql="delete from user where id=?";
        Object[] obj={id};

        return myExecUpdate(obj,sql);
    }

    @Override
    public int addUser(User user) {

        String sql="insert into user values(null,?,?,?,?,?,?,?)";
        Object[] obj={user.getUserName(),user.getUserpassword(),user.getSex(),user.getBirthday(),user.getUserphone(),user.getUserAddress(),user.getUserlei()};

        return myExecUpdate(obj,sql);
    }

    @Override
    public List<User> selectUserByName(String uname) {

        String sql="select * from user where userName like ?";
        Object[] obj={"%"+uname+"%"};
        List<User> list=new ArrayList<>();
        ResultSet rs=myQuery(obj,sql);
        try {
            while (rs.next()){
                Long id=rs.getLong(1);
                String username=rs.getString(2);
                String password=rs.getString(3);
                String sex=rs.getString(4);
                String birthday=rs.getString(5);
                String userphone=rs.getString(6);
                String userAddress=rs.getString(7);
                String userlei=rs.getString(8);

                User user=new User(id,username,password,sex,birthday,userphone,userAddress,userlei);
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                myClos();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    @Override
    public boolean checkName(String userName) {

        String sql="select * from user where userName=?";
        Object[] obj={userName};
        ResultSet rs=myQuery(obj,sql);
        try {
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                myClos();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
