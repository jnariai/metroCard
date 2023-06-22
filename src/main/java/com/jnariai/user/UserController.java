package com.jnariai.user;

import com.jnariai.user.dto.CreateUserDto;
import com.jnariai.user.dto.UserDTO;
import com.jnariai.user.dto.UserPasswordDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping ("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable @NotNull String id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid CreateUserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<String> uppdateUserPassword(@PathVariable @NotNull String id, @RequestBody @Valid UserPasswordDTO userPasswordDTO) {
        try {
            userService.updateUserPassword(id, userPasswordDTO);
            return ResponseEntity.ok("Password updated succesfully");
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
