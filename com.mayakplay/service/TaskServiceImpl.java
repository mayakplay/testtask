package com.mayakplay.service;

import com.google.common.collect.Lists;
import com.mayakplay.domain.Task;
import com.mayakplay.domain.User;
import com.mayakplay.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mayakplay on 17.02.2019.
 */
@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    ITaskRepository repository;

    @Override
    public Task apply(Task task) {
        return repository.save(task);
    }

    @Override
    public List<Task> getAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public List<Task> getByUser(User user) {
        return Lists.newArrayList(repository.findTasksByOwner(user));
    }

    @Override
    public Task getById(int id) {
        return repository.findTaskById(id);
    }

    @Override
    public int getCountByOwner(User user) {
        return repository.countByOwner(user);
    }
}
