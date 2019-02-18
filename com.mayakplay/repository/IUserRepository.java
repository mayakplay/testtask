package com.mayakplay.repository;

import com.mayakplay.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Created by Mayakplay on 17.02.2019.
 */
public interface IUserRepository extends CrudRepository<User, Integer> {

    List<User> findUserByName(String name);

    List<User> findUsersByIsOnline(boolean isOnline);

    User findUserById(int id);

}
