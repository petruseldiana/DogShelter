package com.ps.shelter.controller;

import com.ps.shelter.dto.UserDTO;
import com.ps.shelter.dto.UserUpdateDTO;
import com.ps.shelter.service.ShoppingBasketService;
import com.ps.shelter.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api
@RequestMapping(value = "/user")
public class UserRestController {

    private final UserService userService;
    private final ShoppingBasketService shoppingBasketService;

    @Autowired
    public UserRestController(UserService userService, ShoppingBasketService shoppingBasketService) {
        this.userService = userService;
        this.shoppingBasketService = shoppingBasketService;
    }

    @GetMapping("/test")
    public String test() {
        return userService.test();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void save(@RequestBody @Valid UserDTO user){

        userService.save(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id) {

        userService.delete(id);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid UserUpdateDTO userUpdateDTO, @RequestParam Long id) {

        userService.update(userUpdateDTO, id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserDTO> list() {
        return userService.findAll();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public UserDTO login(@RequestParam String username, @RequestParam String password){

        return userService.login(username,password);
    }

    @RequestMapping(value = "/reset",method = RequestMethod.GET)
    public UserDTO forgotPassword(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String newPassword1,
                               @RequestParam String newPassword2){

        if(newPassword1.equals(newPassword2)){

            return userService.forgotPassword(username,email, newPassword1);
        }else{

            throw new EntityNotFoundException("Password 1 has to be equals to password 2");
        }

    }
}

//ultima a fost o ersoare de versiune de hibernate la aia cu 5.2.17 sau cat era, da, aia
//cand am vazut ca  apornit cu bootrun, a insemnat ca run din intellij are o problema - run din consola - sau din drapta a mers
// astea de obicie se rezolva cu golire de cache , reset la proiect, si verificare de versiuni
//verificare de versiuni: tu ai un gradle specificat si de proiect
// (pentru portabilitate, ca toti cei care lucreaza pe proiect sa ruleze pe aceeasi versiune)
//si ai si un gradle instalat. aici coincid...an
//yway, probleme naspa de configurare pe care le intampina lumea cand deschide un proiect
//am inteles, mersi mult de tot
//cu mre drag, si spor!
// mersii
