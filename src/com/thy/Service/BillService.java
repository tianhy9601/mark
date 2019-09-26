package com.thy.Service;

import com.thy.Bean.Bills;

import java.util.List;

public interface BillService {
    List<Bills> selectList(String title, String pid, String pay);

    List<Bills> selectAll();

    int deleteById(int id);

    Bills selectSingle(int id);

    int updateBill(Bills bl);

    int selectBname(String tName);

    int insertBill(Bills bill);
}
