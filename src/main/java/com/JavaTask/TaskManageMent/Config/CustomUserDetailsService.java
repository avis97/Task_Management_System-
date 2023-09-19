package com.JavaTask.TaskManageMent.Config;

import com.JavaTask.TaskManageMent.Entitys.User;
import com.JavaTask.TaskManageMent.Repository.UserRepository;
import com.JavaTask.TaskManageMent.RequestDto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user;
        try{
            user=userRepository.findByUserName(username).get();
        }catch (Exception e){
            throw new UsernameNotFoundException("User Not Found");
        }
        return user;
    }
}
