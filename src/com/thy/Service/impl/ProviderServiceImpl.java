package com.thy.Service.impl;

import com.thy.Bean.Providers;
import com.thy.Bean.User;
import com.thy.Dao.ProviderDao;
import com.thy.Dao.impl.ProviderDaoImpl;
import com.thy.Service.ProviderService;

import java.util.List;

public class ProviderServiceImpl implements ProviderService {
    ProviderDao ud=new ProviderDaoImpl();
    @Override
    public List<Providers> selectList(String pname) {

        return ud.selectList(pname);
    }

    @Override
    public Providers selectById(int id) {
        return ud.selectById(id);
    }

    @Override
    public int updateProvider(Providers pds) {
        return ud.updateProvider(pds);
    }

    @Override
    public int deleteById(int id) {
        return ud.deleteById(id);
    }

    @Override
    public int addProvider(Providers pds) {
        return ud.addProvider(pds);
    }

    @Override
    public List<Providers> selectName() {
        return ud.selectName();
    }
}
