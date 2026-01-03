package com.callmextrm.INVENTORY.controller;

import com.callmextrm.INVENTORY.dto.UserDto.RoleToUser;
import com.callmextrm.INVENTORY.dto.UserDto.UserDto;
import com.callmextrm.INVENTORY.dto.UserDto.UserRegisterDto;
import com.callmextrm.INVENTORY.dto.UserDto.UserUpdateDto;
import com.callmextrm.INVENTORY.entity.Users;
import com.callmextrm.INVENTORY.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping
    public ResponseEntity<List<UserDto>> showAllUsers(){
        return ResponseEntity.ok(usersService.showUsers());
    }


    //Register Controller
    @PostMapping
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserRegisterDto user){
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.register(user));
    }





    //Update user CONTROLLER
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser( @PathVariable Long id,@Valid @RequestBody UserUpdateDto user){
        return
                ResponseEntity.ok(usersService.updateUser(id,user));
    }


    //Update User's Role CONTROLLER
    @PutMapping("/updaterole")
    public ResponseEntity<Void> updateUserRole(@RequestBody RoleToUser userRole){
        usersService.updateUserRole(userRole);
        return ResponseEntity.noContent().build();
    }


    //Link role to user CONTROLLER
    @PostMapping("/roletouser")
    public ResponseEntity<Void> roleToUser(@RequestBody RoleToUser roleToUser){
        usersService.roleToUser(roleToUser);
        return ResponseEntity.noContent().build();
    }

    //Delete User
    @DeleteMapping ("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

