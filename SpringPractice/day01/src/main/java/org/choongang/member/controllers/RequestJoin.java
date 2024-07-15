package org.choongang.member.controllers;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestJoin {

    @NotBlank // 필수 항목
    @Email // 형식 이메일
    private String email;

    @NotBlank
    @Size(min=8) // 최소사이즈 8글자
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String userName;

    @AssertTrue // 체크했냐한했냐
    private boolean agree;
}
