package org.choongang.member.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.choongang.member.services.JoinService;
import org.choongang.member.services.LoginService;
import org.choongang.member.validators.JoinValidator;
import org.choongang.member.validators.LoginValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final JoinValidator joinValidator;
    private final JoinService joinService;

    private final LoginValidator loginValidator;
    private final LoginService loginService;

    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form) { // 데이터 가져오는거 커맨드 객체로 설정??
        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors) { // 형식에 맞게 써야함 @Valid
        joinValidator.validate(form, errors);

        if (errors.hasErrors()) {
            return "member/join";
        }

        joinService.process(form);

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute RequestLogin form,
                        @CookieValue(name="savedEmail", required = false) String savedEmail) { //실제 쿠키의 이름
                        // HTTP 요청에 포함된 쿠키 값을 메서드의 파라미터로 받아올 때 사용하는 애노테이션
                        // required=false 일 경우 해당 쿠키가 반드시 존재하지 않아도됨, true인데 없으면 예외 발생

            if(savedEmail != null) {
                form.setSaveEmail(true);
                form.setEmail(savedEmail);
            }
            return "member/login";
    }

    @PostMapping("/login")
    public String loginPs(@Valid RequestLogin form, Errors errors) {
        loginValidator.validate(form,errors);

        if(errors.hasErrors()) {
            return "member/login";
        }

        loginService.process(form);
        return "redirect:/";
    }

}























