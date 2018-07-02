package com.prashant.data.mapper;

import com.prashant.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataMapper {

    public static List<User> transform(List<com.prashant.restapi.model.User> userEntities) {
        List<User> userList = null;

        if (userEntities != null) {
            userList = new ArrayList<>();
            for (com.prashant.restapi.model.User user : userEntities) {
                User tmpUser = transform(user);
                if (tmpUser != null) {
                    userList.add(tmpUser);
                }
            }
        }

        return userList;
    }

    public static User transform(com.prashant.restapi.model.User userEntity) {
        User user = null;

        if (userEntity != null) {
            user = new User();
            user.id = userEntity.id;
            user.title = userEntity.title;
            user.body = userEntity. body;
        }

        return user;
    }

}