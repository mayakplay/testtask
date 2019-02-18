package com.mayakplay.service;

import com.mayakplay.domain.User;

import java.util.List;

/**
 * Created by Mayakplay on 17.02.2019.
 */
public interface IUserService {

    User apply(User user);

    List<User> getAll();

    List<User> getOnlineUsers();

    User getById(int id);

}
