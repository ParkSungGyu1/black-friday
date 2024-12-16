package com.example.memberservice.service;

import com.example.memberservice.dto.UserDto;
import com.example.memberservice.entity.UserEntity;
import com.example.memberservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public UserDto registerUser(String loginId, String userName){
        var user = new UserEntity(loginId,userName);
        UserEntity save = userRepository.save(user);
        return new UserDto(
                save.getLoginId(),
                save.getUserName()
        );
    }

    public UserDto modifyUser(Long userId, String userName){
        var user = userRepository.findById(userId).orElseThrow();
        user.modifyUserName(userName);
        UserEntity save = userRepository.save(user);
        return new UserDto(
                save.getLoginId(),
                save.getUserName()
        );
    }

    public UserDto getUser(String loginId){
        var user = userRepository.findByLoginId(loginId).orElseThrow();
        return new UserDto(
                user.getLoginId(),
                user.getUserName()
        );
    }
}
