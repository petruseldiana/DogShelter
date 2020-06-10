package com.ps.shelter.service.impl;

import com.ps.shelter.entity.User;
import com.ps.shelter.service.ContextHolder;

public class ContextHolderImpl implements ContextHolder {

    private User currentUser;

    @Override
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }
}
