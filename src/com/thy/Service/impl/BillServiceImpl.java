package com.thy.Service.impl;

import com.thy.Bean.Bills;
import com.thy.Dao.BillDao;
import com.thy.Dao.impl.BillDaoImpl;
import com.thy.Service.BillService;

import java.util.List;

public class BillServiceImpl implements BillService {
    BillDao bd=new BillDaoImpl();
    @Override
    public List<Bills> selectList(String title, String pid, String pay) {
        return bd.selectList(title,pid,pay);
    }

    @Override
    public List<Bills> selectAll() {
        return bd.selectAll();
    }

    @Override
    public int deleteById(int id) {
        return bd.deleteById(id);
    }

    @Override
    public Bills selectSingle(int id) {
        return bd.selectSingle(id);
    }

    @Override
    public int updateBill(Bills bl) {
        return bd.updateBill(bl);
    }

    @Override
    public int selectBname(String tName) {
        return bd.selectBname(tName);
    }

    @Override
    public int insertBill(Bills bill) {
        return bd.insertBill(bill);
    }
}
