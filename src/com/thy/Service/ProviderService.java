package com.thy.Service;

import com.thy.Bean.Providers;
import com.thy.Bean.User;

import java.util.List;

public interface ProviderService {
    List<Providers> selectList(String pname);

    Providers selectById(int id);

    int updateProvider(Providers pds);

    int deleteById(int id);

    int addProvider(Providers pds);

    List<Providers> selectName();
}
