package com.template.template.controller;

import com.template.template.dto.RequestUserDto;
import com.template.template.dto.ResponseUserDto;
import com.template.template.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<ResponseUserDto>> getUsers(){
        return ResponseEntity.ok().body(this.userService.findAll());
    }
    @PostMapping
    public ResponseEntity<ResponseUserDto> createUser(@RequestBody RequestUserDto userDTO){
        return ResponseEntity.ok().body(this.userService.save(userDTO));
    }

}
