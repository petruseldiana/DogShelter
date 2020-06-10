package com.ps.shelter.service;

import com.ps.shelter.dto.UserDTO;
import com.ps.shelter.dto.UserUpdateDTO;
import com.ps.shelter.entity.User;

import java.text.ParseException;
import java.util.List;

public interface UserService {

    String test();

    UserDTO save(UserDTO userDTO);

    void delete(Long id);

    void update(UserUpdateDTO userUpdateDTO, Long id);

    UserDTO findById(Long id);

    List<UserDTO> findAll();

    UserDTO login(String username, String password);

    UserDTO forgotPassword(String username, String email, String newPassword);
}
