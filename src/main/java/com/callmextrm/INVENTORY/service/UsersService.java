package com.callmextrm.INVENTORY.service;

import com.callmextrm.INVENTORY.dto.RoleToUser;
import com.callmextrm.INVENTORY.entity.Role;
import com.callmextrm.INVENTORY.entity.Users;
import com.callmextrm.INVENTORY.exception.RolesException;
import com.callmextrm.INVENTORY.exception.UserException;
import com.callmextrm.INVENTORY.repository.RolesRepo;
import com.callmextrm.INVENTORY.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsersService {
    private final UserRepo userdao;
    private final RolesRepo rolesdao;
    private final PasswordEncoder encoder;

    public UsersService(UserRepo userdao, RolesRepo rolesdao, PasswordEncoder encoder) {
        this.userdao = userdao;
        this.rolesdao = rolesdao;
        this.encoder = encoder;
    }


    //Show Users Service
    public List<Users> showUsers(){
        return userdao.findAll();
    }


    //Register Service
    public Users register(Users user){
        if (userdao.existsByUsername(user.getUsername())){
            throw new UserException("User already registered");
        }{if (user.getPassword()==null || user.getPassword().isEmpty()){
            throw new UserException("Password required");
        }
        String pw = encoder.encode(user.getPassword());
        user.setPassword(pw);
        return userdao.save(user);}
    }

    //Update Credentiels Service
    public Users updateUser(Long id,Users newuser){
        Users user = userdao.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found"));

        if (userdao.existsByUsername(newuser.getUsername())==true && !user.getUsername().equals(newuser.getUsername())){
            throw new UserException("Username already used");

        }
        user.setUsername(newuser.getUsername());
        if (newuser.getPassword() == null || newuser.getPassword().isEmpty()){
            throw new UserException("Password is required");

    } else user.setPassword(encoder.encode(newuser.getPassword()));
        return userdao.save(user);



    }

    //Link role to User
    public void roleToUser(RoleToUser roleToUser){

        Users user = userdao.findByUsername(roleToUser.getUsername());
        Role role = rolesdao.findByRolename(roleToUser.getRolename());
        user.getRoles().add(role);
    }

    //Update User's role
    public void updateUserRole(RoleToUser roleToUser){
        Users user = userdao.findByUsername(roleToUser.getUsername());
        Role role = rolesdao.findByRolename(roleToUser.getRolename());
        if (user.getRoles().isEmpty()) {
            throw new UserException("User has no roles to replace");}

        if (!rolesdao.existsByRolename(roleToUser.getUsername())) {
            throw new RolesException("Role not found: " + roleToUser.getRolename());}


        user.getRoles().clear();
        user.getRoles().add(role);




    }}
