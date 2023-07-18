package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.List;

public class ModelData {
    private List<User> users;
    private User activeUser;
    public List<User> getUsers(){
        return  users;
    }
    public void setUsers(List<User> users){
        if (users != null){
            this.users = users;
        }
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }
}
