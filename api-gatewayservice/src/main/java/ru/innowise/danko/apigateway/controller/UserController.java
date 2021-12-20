package ru.innowise.danko.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innowise.danko.apigateway.client.FileClient;
import ru.innowise.danko.apigateway.dto.UserDto;
import ru.innowise.danko.apigateway.dto.UserRoleDto;
import ru.innowise.danko.apigateway.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final FileClient fileClient;

    @Autowired
    public UserController(UserService userService, FileClient fileClient) {
        this.userService = userService;
        this.fileClient = fileClient;
    }

    @PostMapping(path = "/registration")
    public UserDto register(@RequestBody UserDto user) {
        return userService.register(user);
    }

    @PostMapping(path = "/roles")
    public ResponseEntity<UserRoleDto> addRole(@RequestBody UserRoleDto userRoleDto,
                                               @Autowired Principal principal){
        return ResponseEntity.ok(userService.addRole(userRoleDto));
    }

    @PostMapping(path = "{userId}/roles/{roleId}")
    public ResponseEntity<UserDto> addRoleToUser(@PathVariable Long userId,
                              @PathVariable Long roleId, @Autowired Principal principal){
        return ResponseEntity.ok(userService.addUserRoleToUser(userId, roleId));
    }

    @PostMapping(path = "/file-upload")
    public ResponseEntity<UserDto> uploadUserAvatar(@RequestPart MultipartFile multipartFile,
                                                    @RequestParam Long userId){
        UserDto userDto = userService.getUserById(userId);
        userDto.setFileId(fileClient.uploadFile(multipartFile).getBody());
        return ResponseEntity.ok(userService.register(userDto));
    }

    @GetMapping("/download-avatar/{id}")
    public ResponseEntity<ByteArrayResource> downloadUserAvatar(@PathVariable String id){
        return fileClient.downloadFile(id);
    }
}
