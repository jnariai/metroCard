package com.jnariai.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jnariai.user.dto.CreateUserDto;
import com.jnariai.user.dto.UserDTO;

@Validated
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable @NotNull String id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid CreateUserDto userDto) {
        return userService.createUser(userDto);
    }
}
