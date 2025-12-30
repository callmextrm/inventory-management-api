package com.callmextrm.INVENTORY.controller;

import com.callmextrm.INVENTORY.dto.RoleToUser;
import com.callmextrm.INVENTORY.entity.Users;
import com.callmextrm.INVENTORY.service.UsersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
   private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }


    //Show all users CONTROLLER
    @GetMapping("")
    public List<Users> showAllUsers(){
        return usersService.showUsers();
    }


    //Register Controller
    @PostMapping("")
    public Users register(@RequestBody Users user){
        return usersService.register(user);
    }


    //Update user CONTROLLER
    @PutMapping("/update/{id}")
    public Users updateUser(@PathVariable Long id,@RequestBody Users user){
        return usersService.updateUser(id,user);
    }


    //Update User's Role CONTROLLER
    @PutMapping("/updaterole")
    public void updateUserRole(@RequestBody RoleToUser userRole){
         usersService.updateUserRole(userRole);
    }


    //Link role to user CONTROLLER
    @PostMapping("/roletouser")
    public void roleToUser(@RequestBody RoleToUser roleToUser){
        usersService.roleToUser(roleToUser);
    }

}
