package com.ps.shelter.mvcController;

import com.ps.shelter.dto.UserDTO;
import com.ps.shelter.entity.User;
import com.ps.shelter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/mvc/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getAll(ModelAndView modelAndView) {

        List<UserDTO> users = userService.findAll();

        modelAndView.addObject("users", users);
        modelAndView.setViewName("user/list");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView showNewUserForm(ModelAndView modelAndView) {

        UserDTO userDTO = new UserDTO();

        modelAndView.addObject("user", userDTO);
        modelAndView.setViewName("user/new");
        return modelAndView;
    }
//
//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
//
//        User user = userService.findById(id);
//
//        model.addAttribute("user", user);
//        return "update-user";
//    }
//
//    @PutMapping("/change/{id}")
//    public String updateUser(@PathVariable("id") Long id, @Valid UserUpdateDTO userUpdateDTO,
//                             BindingResult result, Model model) {
//        if (result.hasErrors()) {
////            userUpdateDTO.setId(id);
//            return "update-user";
//        }
//
//        userService.update(userUpdateDTO, id);
//        model.addAttribute("users", userService.findAll());
//        return "index";
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id, Model model) {
//
//        userService.delete(id);
//        model.addAttribute("users", userService.findAll());
//        return "index";
//    }
//
//    @GetMapping("/list")
//    public ModelAndView getList(ModelAndView mav) {
//        List<UserDTO> users = userService.findAll();
//
//        mav.addObject("users", users);
//        mav.setViewName("user/list");
//        return mav;
//    }
}
