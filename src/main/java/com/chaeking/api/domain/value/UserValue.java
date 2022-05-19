package com.chaeking.api.domain.value;

import com.chaeking.api.domain.entity.User;
import com.chaeking.api.domain.enumerate.Sex;
import com.chaeking.api.util.DateTimeUtils;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public final class UserValue {
    public final static class Req {
        public record Creation(
                @Schema(description = "이메일") @NotBlank @Email String email,
                @Schema(description = "비밀번호") @NotBlank String password,
                @Schema(description = "이름") @NotBlank String name,
                @Schema(description = "생년월일(yyyy-MM-dd)") @NotBlank @PastOrPresent LocalDate birthDate,
                @Schema(description = "성별") @Pattern(regexp = "^[MF]$") Sex sex) { }
    }

    public final static class Res {
        public record Detail(String email, String name, LocalDate birthDate, Sex sex) {
            public final static Detail of(User u) {
                return new Detail(u.getEmail(),
                        u.getName(),
                        u.getBirthDate(),
                        u.getSex());
            }
        }
    }
}
