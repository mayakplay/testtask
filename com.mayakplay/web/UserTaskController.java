package com.mayakplay.web;

import com.mayakplay.domain.Task;
import com.mayakplay.domain.User;
import com.mayakplay.service.ITaskService;
import com.mayakplay.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by Mayakplay on 17.02.2019.
 */
@RestController
public class UserTaskController {

    private final IUserService userService;
    private final ITaskService taskService;

    @Autowired
    public UserTaskController(ITaskService taskService, IUserService userService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/onlineusers")
    public List<User> getOnlineUsers() {
        return this.userService.getOnlineUsers();
    }

    @GetMapping("/adduser")
    public User applyUser(@RequestParam String name) {
        return userService.apply(new User(name, false));
    }

    @GetMapping("/setonline")
    public User setUserOnline(@RequestParam int userId, @RequestParam boolean isOnline) {
        User userById = userService.getById(userId);

        if (userById != null) {
            userById.setOnline(isOnline);

            if (!isOnline) distributeUserTasks(userById);
        }

        return userService.apply(userById);
    }

    @GetMapping("/settask")
    public Task setTaskUser(@RequestParam int taskId, @RequestParam int userId) {

        User user = userService.getById(userId);
        Task task = taskService.getById(taskId);

        task.setOwner(user);

        return taskService.apply(task);

    }

    public void distributeUserTasks(User offlineUser) {

        List<Task> userTasks = taskService.getByUser(offlineUser);
        List<User> onlineUsers = userService.getOnlineUsers();

        if (onlineUsers.size() != 0) {
            Queue<Task> taskQueue = new LinkedList<>(userTasks);

            onlineUsers.forEach(user -> user.setTaskCount(taskService.getCountByOwner(user)));


            while (!taskQueue.isEmpty()) {
                Task poll = taskQueue.poll();

                User user = onlineUsers.stream().min(Comparator.comparing(User::getTaskCount)).get();

                poll.setOwner(user);
                user.setTaskCount(user.getTaskCount() + 1);

                taskService.apply(poll);
            }
        }

    }

    @GetMapping("/random")
    public void random() {
        for (int i = 0; i < 10; i++) {
            taskService.apply(new Task("test" + i, false, null));
            userService.apply(new User("user" + i, true));
        }
    }

    @GetMapping("/addtask")
    public Task applyTask(@RequestParam String description) {
        return taskService.apply(new Task(description, false, null));
    }

    @GetMapping("/finishtask")
    public Task finishTask(@RequestParam int taskId) {
        Task byId = taskService.getById(taskId);
        byId.setFinished(true);

        return taskService.apply(byId);
    }

    @GetMapping("/")
    public String print() {

        StringBuilder s = new StringBuilder();

        List<Task> tasks = taskService.getAll();
        List<User> users = userService.getAll();

        for (Task task : tasks) {
            s.append(task.toString()).append("</br>");
        }

        s.append("</br>");

        for (User user : users) {
            s.append(user.toString()).append("</br>");
        }

        return s.toString();
    }

}
