package com.JavaTask.TaskManageMent.Controller;

import com.JavaTask.TaskManageMent.JwtSecurity.JwtTokenHelper;
import com.JavaTask.TaskManageMent.RequestDto.JwtRequestDto;
import com.JavaTask.TaskManageMent.RequestDto.UserRequestDto;
import com.JavaTask.TaskManageMent.ResponseDto.JwtResponseDto;
import com.JavaTask.TaskManageMent.ResponseDto.UserResponseDto;
import com.JavaTask.TaskManageMent.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController{

    @Autowired
    JwtTokenHelper tokenHelper;
    @Autowired
    UserDetailsService detailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> createToken(@RequestBody JwtRequestDto request) throws Exception {

        authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails= detailsService.loadUserByUsername(request.getUsername());
        String token=tokenHelper.generateToken(userDetails);

        JwtResponseDto response=new JwtResponseDto();
        response.setToken(token);

        return new ResponseEntity<JwtResponseDto>(response, HttpStatus.ACCEPTED);
    }
    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken Token=new
                UsernamePasswordAuthenticationToken(username,password);
        try {
            this.authenticationManager.authenticate(Token);
        }catch(BadCredentialsException e){
            throw new Exception("User Is Disable");
        }
    }
    @PostMapping("/register")
    private ResponseEntity<UserResponseDto> registerNewUser(@RequestBody UserRequestDto userRequestDtos){

        UserResponseDto dtos=userService.registerNewUser(userRequestDtos);

        return new ResponseEntity<UserResponseDto>(dtos,HttpStatus.CREATED);
    }
}
