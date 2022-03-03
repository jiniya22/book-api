package com.chaeking.api.controller.v1;

import com.chaeking.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chaeking.api.domain.dto.data.UserDto;
import com.chaeking.api.domain.dto.response.BaseResponse;

@Tag(name = "user", description = "사용자")
@RestController
@RequestMapping("/v1/users")
public record UserController(UserService userService) {

    @Operation(summary = "사용자 등록(= 회원 가입)")
    @PostMapping("")
    public BaseResponse save(@RequestBody UserDto req) {
        BaseResponse res = userService.save(req);
        return res;
    }
}
