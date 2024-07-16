package org.choongang.member.validators;



import lombok.RequiredArgsConstructor;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.mappers.MemberMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

    private final MemberMapper mapper;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestJoin.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RequestJoin form = (RequestJoin) target;
        String email = form.getEmail();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String userName = form.getUserName();
        boolean agree = form.isAgree();


        //이메일 중복여부 확인
        if(StringUtils.hasText(email)&&mapper.exists(email) !=0L) {
            errors.rejectValue("email", "Duplicated");
        }

        // 비밀번호, 비밀번호 확인 일치 여부
        if(StringUtils.hasText(confirmPassword) && StringUtils.hasText(confirmPassword)
        && !password.equals(confirmPassword)) {
            errors.rejectValue("confirmPassword", "MisMatch");
        }
    }
}
















