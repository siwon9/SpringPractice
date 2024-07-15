package org.choongang.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement// 이게뭐냐?
@MapperScan("org.choongang")
public class DBConfig {
}
