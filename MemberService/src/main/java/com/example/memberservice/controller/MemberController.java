package com.example.memberservice.controller;

import com.example.memberservice.dto.ModifyUserDto;
import com.example.memberservice.dto.RegisterUserDto;
import com.example.memberservice.dto.UserDto;
import com.example.memberservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final UserService userService;

    @PostMapping("/member/users/registration")
    public UserDto registerUser(@RequestBody RegisterUserDto dto){
        return userService.registerUser(dto.getLoginId(), dto.getUserName());
    }

    @PatchMapping("/member/users/{userId}/modify")
    public UserDto modifyUser(@RequestBody ModifyUserDto dto, @PathVariable("userId") Long userId){
        return userService.modifyUser(userId, dto.getUserName());
    }

    @GetMapping("/member/users/{loginId}/login")
    public UserDto modifyUser(@PathVariable("loginId") String loginId){
        return userService.getUser(loginId);
    }
}
