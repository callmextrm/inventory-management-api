package com.callmextrm.INVENTORY.service;

import com.callmextrm.INVENTORY.dto.UserDto.RoleToUser;
import com.callmextrm.INVENTORY.dto.UserDto.UserDto;
import com.callmextrm.INVENTORY.dto.UserDto.UserRegisterDto;
import com.callmextrm.INVENTORY.dto.UserDto.UserUpdateDto;
import com.callmextrm.INVENTORY.entity.Role;
import com.callmextrm.INVENTORY.entity.Users;
import com.callmextrm.INVENTORY.exception.Exceptions.Password;
import com.callmextrm.INVENTORY.exception.Exceptions.ResourceAlreadyFound;
import com.callmextrm.INVENTORY.exception.Exceptions.ResourceNotFound;
import com.callmextrm.INVENTORY.repository.RolesRepo;
import com.callmextrm.INVENTORY.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsersService {
    private final UserRepo userdao;
    private final RolesRepo rolesdao;
    private final PasswordEncoder encoder;
    private UserDto toDto(Users user) {
        Set<String> roles = user.getRoles()
                .stream()
                .map(Role::getRolename)
                .collect(Collectors.toSet());
        return new UserDto(
                user.getId(),
                user.getUsername(),
                roles
        );}

    public UsersService(UserRepo userdao, RolesRepo rolesdao, PasswordEncoder encoder) {
        this.userdao = userdao;
        this.rolesdao = rolesdao;
        this.encoder = encoder;
    }


    //Show Users Service
    public List<UserDto> showUsers() {
        return userdao.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }


    //Register Service
    public UserDto register(UserRegisterDto dto){
        if (userdao.existsByUsername(dto.username())){
            throw new ResourceAlreadyFound("User already registered");
        }
        Users user = new Users();
        user.setUsername(dto.username());
        user.setPassword(encoder.encode(dto.password()));

        {if (dto.password()==null || dto.password().isEmpty()){
            throw new Password("Password required");
        }
            Users saved = userdao.save(user);
        return toDto(saved);}
    }

    //Update Credentiels Service
    public UserDto updateUser(Long id, UserUpdateDto dto){
        Users user = userdao.findById(id).orElseThrow(()-> new ResourceNotFound("User not found"));

        if (userdao.existsByUsername(dto.username()) && !user.getUsername().equals(dto.username())){
            throw new ResourceAlreadyFound("Username already used");

        }
        user.setUsername(dto.username());
        Users saved = userdao.save(user);

        return toDto(saved);



    }

    //Link role to User
    public void roleToUser(RoleToUser dto){
        Users user = userdao.findByUsername(dto.username());
        if (user == null){
            throw new ResourceNotFound("User not found");
        }

        Role role = rolesdao.findByRolename(dto.rolename());
        if (role == null ){
            throw new ResourceNotFound("Role not found");

        }
        user.getRoles().add(role);
        userdao.save(user);
    }

    //Update User's role
    public void updateUserRole(RoleToUser dto){
        Users user = userdao.findByUsername(dto.username());
        Role role = rolesdao.findByRolename(dto.rolename());
        if (user.getRoles().isEmpty()) {
            throw new ResourceNotFound("User has no roles");}

        if (!rolesdao.existsByRolename(dto.rolename())) {
            throw new ResourceNotFound("Role not found: " + dto.rolename());}


        user.getRoles().clear();
        user.getRoles().add(role);
        userdao.save(user);

    }


    //Delete User
    public void deleteUser(Long id){
        userdao.findById(id).orElseThrow(()-> new ResourceNotFound("Username not found"));
        userdao.deleteById(id);
    }


}
