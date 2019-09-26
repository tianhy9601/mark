package com.thy.Dao.impl;

import JDBCUtils.BaseDao;
import com.thy.Bean.Bills;
import com.thy.Dao.BillDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl extends BaseDao implements BillDao {
    @Override
    public List<Bills> selectList(String title, String pid, String pay) {

        List<Bills> list=new ArrayList<>();
        StringBuffer sb=new StringBuffer("select id,title,providerName,money,pay,time from bills b inner join providers p on b.pid=p.providerId where 1=1 ");
        if(pay!=null&&!pay.equals("")){
            sb.append(" and pay = "+pay+"");
        }
        if (title!=null&&!title.equals("")){
            sb.append(" and title like '%"+title+"%'");
        }
        if (!pid.equals("-1")){
            sb.append(" and b.pid ="+pid+"");
        }
        ResultSet rs=myQuery(null,sb.toString());
        try {
            while(rs.next()){

                int id=rs.getInt(1);
                String titles=rs.getString(2);
                String pname=rs.getString(3);
                double money=rs.getDouble(4);
                int pays=rs.getInt(5);
                String date=rs.getString(6);

                Bills bl=new Bills(id,titles,money,pays,date,pname);
                System.out.println(bl);
                list.add(bl);

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
    public List<Bills> selectAll() {
        List<Bills> lists=new ArrayList<>();
        String sql="select id,title,providerName,money,pay,time,pid from bills b inner join providers p on b.pid=p.providerId";
        ResultSet rs=myQuery(null,sql);
        try {
            while (rs.next()){
                String providerName=rs.getString(3);
                int pid=rs.getInt(7);
                Bills bills=new Bills();
                bills.setProviderName(providerName);
                bills.setPid(pid);
                lists.add(bills);
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
        return lists;
    }

    @Override
    public int deleteById(int id) {
        Object[] obj={id};
        return myExecUpdate(obj,"delete from bills where id=?");
    }

    @Override
    public Bills selectSingle(int id) {
        String sql="select title,unit,number,money,providerName,pay,pid from bills b inner join providers p on b.pid=p.providerId where id=?";
        Object[] obj={id};
        ResultSet rs=myQuery(obj,sql);
        Bills bl=new Bills();
        try {
            while (rs.next()){
                String title=rs.getString(1);
                String unit=rs.getString(2);
                int number=rs.getInt(3);
                double money=rs.getDouble(4);
                String providerName=rs.getString(5);
                int pay=rs.getInt(6);
                int pid=rs.getInt(7);
                bl.setId(id);
                bl.setTitle(title);
                bl.setUnit(unit);
                bl.setNumber(number);
                bl.setMoney(money);
                bl.setProviderName(providerName);
                bl.setPay(pay);
                bl.setPid(pid);
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
        return bl;
    }

    @Override
    public int updateBill(Bills bl) {

        System.out.println(bl);
        String sql2="update bills set title=?,unit=?,number=?,money=?,pay=?,pid=? where id=?";
        Object[] obj2={bl.getTitle(),bl.getUnit(),bl.getNumber(),bl.getMoney(),bl.getPay(),bl.getPid(),bl.getId()};
        return myExecUpdate(obj2,sql2);
    }

    @Override
    public int selectBname(String tName) {

        String sql="select * from bills where title=?";
        Object[] obj={tName};
        ResultSet rs=myQuery(obj,sql);
        try {
            if (rs.next()){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            myClos();
        }


        return 0;
    }

    @Override
    public int insertBill(Bills bill) {

        String sql="insert into bills values(null,?,?,?,?,?,?,now())";
        Object[] obj={bill.getTitle(),bill.getUnit(),bill.getNumber(),bill.getMoney(),bill.getPid(),bill.getPay()};

        return myExecUpdate(obj,sql);
    }
}
