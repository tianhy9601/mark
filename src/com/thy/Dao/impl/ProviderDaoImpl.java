package com.thy.Dao.impl;

import JDBCUtils.BaseDao;
import com.thy.Bean.Providers;
import com.thy.Bean.User;
import com.thy.Dao.ProviderDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProviderDaoImpl extends BaseDao implements ProviderDao {
    @Override
    public List<Providers> selectList(String pname) {
        List<Providers> list=new ArrayList<>();
        StringBuffer sb=new StringBuffer("select * from providers where 1=1");
        if(pname!=null&&!pname.equals("")){
            sb.append(" and providerName like '%"+pname+"%' ");
        }
        ResultSet rs=myQuery(null,sb.toString());
        try {
            while(rs.next()){
                int id=rs.getInt(1);
                String providerName=rs.getString(2);
                String providerCard=rs.getString(3);
                String people=rs.getString(4);
                String phone=rs.getString(5);
                String address=rs.getString(6);
                String fax=rs.getString(7);
                String description=rs.getString(8);

                Providers pd=new Providers(id,providerName,providerCard,people,phone,address,fax,description);
                list.add(pd);
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
        return list;
    }

    @Override
    public Providers selectById(int id) {
        Providers providers=null;

        String sql="select * from providers where providerId=?";
        Object[] obj={id};

        ResultSet rs=myQuery(obj,sql);
        try {
            while (rs.next()){
                int ids=rs.getInt(1);
                String providerName=rs.getString(2);
                String people=rs.getString(4);
                String phone=rs.getString(5);
                String address=rs.getString(6);
                String fax=rs.getString(7);
                String description=rs.getString(8);

                providers=new Providers(ids,providerName,people,phone,address,fax,description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                myClos();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return providers;
    }

    @Override
    public int updateProvider(Providers pds) {
        String sql="update providers set providerName=?,people=?,phone=?,address=?,fax=?,description=? where providerId=?";
        Object[] obj={pds.getProviderName(),pds.getPeople(),pds.getPhone(),pds.getAddress(),pds.getFax(),pds.getDescription(),pds.getProviderId()};
        return myExecUpdate(obj,sql);
    }

    @Override
    public int deleteById(int id) {
        Object[] obj1={id};

        ResultSet rs=myQuery(obj1,"select * from bills where pid=?");
        try {
            while(rs.next()){
                String sql2="delete from bills where pid=?";
                myExecUpdate(obj1,sql2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            myClos();
        }
        String sql3="delete from providers where providerId=?";

        Object[] obj2={id};

        return myExecUpdate(obj2,sql3);
    }

    @Override
    public int addProvider(Providers pds) {

        String sql="insert into providers values(null,?,?,?,?,?,?,?)";
        Object[] obj={pds.getProviderName(),pds.getProviderCard(),pds.getPeople(),pds.getPhone(),pds.getAddress(),pds.getFax(),pds.getDescription()};

        return myExecUpdate(obj,sql);
    }

    @Override
    public List<Providers> selectName() {
        List<Providers> list=new ArrayList<>();

        String sql="select providerId,providerName from providers";
        ResultSet rs=myQuery(null,sql);
        try {
            while (rs.next()){
                Providers providers=null;
                int id=rs.getInt(1);
                String providerName=rs.getString(2);
                providers=new Providers(id,providerName);
                list.add(providers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            myClos();
        }


        return list;
    }
}
