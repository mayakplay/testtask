package com.mayakplay.repository;

import com.mayakplay.domain.Task;
import com.mayakplay.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mayakplay on 17.02.2019.
 */
public interface ITaskRepository extends CrudRepository<Task, Integer> {

    List<Task> findTasksByOwner(User user);

    Task findTaskById(int id);

    int countByOwner(User user);

}