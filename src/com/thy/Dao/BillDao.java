package com.thy.Dao;

import com.thy.Bean.Bills;

import java.util.List;

public interface BillDao {
    List<Bills> selectList(String title, String pid, String pay);

    List<Bills> selectAll();

    int deleteById(int id);

    Bills selectSingle(int id);

    int updateBill(Bills bl);

    int selectBname(String tName);

    int insertBill(Bills bill);
}
