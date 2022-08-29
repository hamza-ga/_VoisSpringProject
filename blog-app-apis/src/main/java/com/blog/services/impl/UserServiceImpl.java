package com.blog.services.impl;

import com.blog.config.AppConstants;
import com.blog.entities.Role;
import com.blog.entities.User;
import com.blog.exeptions.ResourceNotFoundExeption;
import com.blog.payloads.UserDto;
import com.blog.repositories.RoleRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    //no longer working XXXXXXXXXXXXXXXXXXXXXXXXX
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    //no longer working XXXXXXXXXXXXXXXXXXXXXXXX
    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExeption("User","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setRoles(userDto.getRoles());
        User updatedUser = this.userRepository.save(user);
        return this.userToDto(updatedUser);

    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExeption("User","id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExeption("User","id",userId));
        System.out.println("##################################################################");
        System.out.println(user);
        System.out.println("##################################################################");
        this.userRepository.delete(user);
    }

    @Override
    public UserDto registerNewUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);

        //encode the new password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        //role
        Role role =this.roleRepository.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);
        User newUser = this.userRepository.save(user);

        return this.modelMapper.map(newUser,UserDto.class);
    }

    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class); //new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class); //new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
