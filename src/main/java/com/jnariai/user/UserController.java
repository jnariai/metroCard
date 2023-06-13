package com.jnariai.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jnariai.user.dto.CreateUserDto;
import com.jnariai.user.dto.UserDTO;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> list() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable @NotNull String id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid @NotNull CreateUserDto userDto) {
        return userService.create(userDto);
    }
}
