package com.ps.shelter.service;

import com.ps.shelter.entity.User;

public interface ContextHolder {

    void setCurrentUser(User user);

    User getCurrentUser();
}
