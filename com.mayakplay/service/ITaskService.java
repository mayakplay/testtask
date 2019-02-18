package com.mayakplay.service;

import com.mayakplay.domain.Task;
import com.mayakplay.domain.User;

import java.util.List;

/**
 * Created by Mayakplay on 17.02.2019.
 */
public interface ITaskService {

    Task apply(Task task);

    List<Task> getAll();

    List<Task> getByUser(User user);

    Task getById(int id);

    int getCountByOwner(User user);

}
