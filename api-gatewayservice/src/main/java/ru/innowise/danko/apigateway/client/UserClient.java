package ru.innowise.danko.apigateway.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innowise.danko.apigateway.dto.UserDto;
import ru.innowise.danko.apigateway.dto.UserRoleDto;
import ru.innowise.danko.apigateway.util.interceptor.FeignConfig;

import java.security.Principal;

@FeignClient(name = "userService",
        url = "${ext-service.userService.baseUrl}",configuration = FeignConfig.class)
public interface UserClient {

    @PostMapping(path = "/registration")
    public UserDto register(@RequestBody UserDto user);

    @PostMapping(path = "/roles")
    public ResponseEntity<UserRoleDto> addRole(@RequestBody UserRoleDto userRoleDto,
                                               @Autowired Principal principal);

    @PostMapping(path = "{userId}/roles/{roleId}")
    public ResponseEntity<UserDto> addRoleToUser(@PathVariable Long userId,
                                                 @PathVariable Long roleId, @Autowired Principal principal);
    @PostMapping(path = "/upload-avatar")
    public ResponseEntity<UserDto> uploadUserAvatar(@RequestPart MultipartFile multipartFile,
                                                    @RequestParam Long userId);

    @GetMapping("/download-avatar/{id}")
    public ResponseEntity<ByteArrayResource> downloadUserAvatar(@PathVariable String id);
}
