package com.jnariai.controller;

import com.jnariai.dto.RequestUserDto;
import com.jnariai.dto.ResponseUserDto;
import com.jnariai.entity.User;
import com.jnariai.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(userDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseUserDto> updateUser(@PathVariable String id, @RequestBody RequestUserDto requestUserDto){
        return ResponseEntity.ok(this.userService.update(requestUserDto, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        this.userService.delete(id);
        return ResponseEntity.ok("User " + id + " deleted successfully");
    }


}
