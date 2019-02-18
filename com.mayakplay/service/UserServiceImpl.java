package com.mayakplay.service;

import com.google.common.collect.Lists;
import com.mayakplay.domain.User;
import com.mayakplay.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mayakplay on 17.02.2019.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository repository;

    @Override
    public User apply(User realization) {
        return this.repository.save(realization);
    }

    @Override
    public List<User> getAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public List<User> getOnlineUsers() {
        return Lists.newArrayList(repository.findUsersByIsOnline(true));
    }

    @Override
    public User getById(int id) {
        return repository.findUserById(id);
    }


}