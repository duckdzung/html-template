package com.example.service;

import com.example.dao.MyDao;
import com.example.model.MyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    @Autowired
    private MyDao myDao;

    public void create(MyModel model) {
        myDao.save(model);
    }

    public MyModel read(int id) {
        return myDao.findById(id);
    }

    public void update(MyModel model) {
        myDao.update(model);
    }

    public void delete(int id) {
        myDao.delete(id);
    }

    public List<MyModel> listAll() {
        return myDao.findAll();
    }
}