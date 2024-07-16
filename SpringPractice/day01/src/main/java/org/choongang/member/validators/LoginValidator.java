package org.choongang.member.validators;

import lombok.RequiredArgsConstructor;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class LoginValidator implements Validator {

    private final MemberMapper mapper;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestJoin.class);
    } // RequestJoin으로 유입되는 자료형을 한정한다.

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return; // 에러날시 함수 즉시 종료
        }

        RequestJoin form = (RequestJoin) target;
        String email = form.getEmail();
        String password = form.getPassword();

        if(StringUtils.hasText(email)){
            Member member = mapper.get(email);
            if(member == null) {
                errors.reject("Check.emailPassword");
            } // 필드를정하면 어디가 맞는지 알기 때문에 필드를 정의해야하는 rejectValue를 사용하지 않고 reject를 사용한다.

            if(member != null && StringUtils.hasText(password) && !BCrypt.checkpw(password, member.getPassword())){
                errors.reject("Check.emailPassword");
            }
        }
    }
}











