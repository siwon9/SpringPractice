package org.choongang.member.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id // 엔티티 클래스의 기본 키(primary key)를 지정
    private Long seq;
    private String email;
    private String password;
    private String userName;
    private LocalDateTime regDt;
}
