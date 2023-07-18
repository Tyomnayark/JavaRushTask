package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;
import com.javarush.task.task36.task3608.bean.User;


import java.util.List;

public class UsersView implements View{
    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {
        System.out.println("All users:");
        List<User> users = modelData.getUsers();
        for (User user:users) {
            System.out.println("\t"+user);
        }
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void setController(){

    }
    public void fireEventShowAllUsers(){
        controller.onShowAllUsers();

    }
    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

}
