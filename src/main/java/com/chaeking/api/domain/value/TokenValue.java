package com.chaeking.api.domain.value;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class TokenValue {

    @Builder
    @Schema(name = "TokenVerify")
    public record Verify(Long uid, boolean success) {}


    @Schema(name = "Token")
    public record Token(
            String accessToken,
            String refreshToken) { }
}
