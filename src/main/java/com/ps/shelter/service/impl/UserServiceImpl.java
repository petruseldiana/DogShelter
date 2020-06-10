package com.ps.shelter.service.impl;

import com.ps.shelter.dto.ShoppingBasketDTO;
import com.ps.shelter.dto.UserDTO;
import com.ps.shelter.dto.UserUpdateDTO;
import com.ps.shelter.entity.User;
import com.ps.shelter.mapper.NameIdMapper;
import com.ps.shelter.mapper.UserMapper;
import com.ps.shelter.repository.UserRepository;
import com.ps.shelter.service.ShoppingBasketService;
import com.ps.shelter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final NameIdMapper nameIdMapper;
    private final ShoppingBasketService shoppingBasketService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, NameIdMapper nameIdMapper, ShoppingBasketService shoppingBasketService) {

        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.nameIdMapper = nameIdMapper;
        this.shoppingBasketService = shoppingBasketService;
    }

    @Override
    public String test() {
        return "text din service";
    }

    @Override
    public UserDTO save(UserDTO userDTO) {

        userRepository.findUserByUsernameOrEmail(userDTO.getUsername(),userDTO.getEmail())
                .ifPresent( s -> { throw new EntityNotFoundException("User with username " + userDTO.getUsername() + " or email " + userDTO.getEmail() + " already exists"); } );

        userDTO.setPassword(passwordEncoder().encode(userDTO.getPassword()));
        User user = userRepository.save(userMapper.toEntity(userDTO));

        ShoppingBasketDTO shoppingBasketDTO = new ShoppingBasketDTO();
        shoppingBasketDTO.setUser_id(user.getId());
        shoppingBasketService.create(shoppingBasketDTO);

        return userMapper.toDTO(user);
    }

    @Override
    public void delete(Long id) {

        final User user = userRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find user with ID: " + id); } );

        userRepository.delete(user);
    }

    @Override
    public void update(UserUpdateDTO userUpdateDTO, Long id) {

        final User user = userRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find user with ID: " + id); } );

        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setEmail(userUpdateDTO.getEmail());
        user.setUserRole(userUpdateDTO.getUserRole());

        userRepository.save(user);
    }

    @Override
    public UserDTO findById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> findAll() {

        return userRepository.findAll().
                stream().
                map(userMapper::toDTO).
                collect(Collectors.toList());
    }

    @Override
    public UserDTO login(String username, String password) {

        User user = userRepository.findUserByUsername(username);

        if(user != null) {
//            if(Objects.equals(user.getPassword(), password)) {
//
//                return userMapper.toDTO(user);
//            }
            if(passwordEncoder().matches(password,user.getPassword())){
                return userMapper.toDTO(user);
            }
        }

        return null;
    }

    @Override
    public UserDTO forgotPassword(String username, String email, String newPassword) {

        User user = userRepository.findByUsernameAndEmail(username,email);

        if(user != null){

            user.setPassword(newPassword);

            userRepository.save(user);

            return userMapper.toDTO(user);
        }else{

            throw new EntityNotFoundException("Use with username: " + username + " and email: " + email + " was not found");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
