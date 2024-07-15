package org.choongang.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement// Spring 프레임워크에서 트랜잭션 관리 기능을 활성화하는 애노테이션
@MapperScan("org.choongang")
@EnableJdbcRepositories("org.choongang") //Spring Data JDBC 리포지토리를 활성화
        // 지정된 패키지 내에 있는 리포지토리 인터페이스를 스캔하고, Spring Data JDBC 리포지토리 구현을 자동으로 생성
public class DBConfig extends AbstractJdbcConfiguration {
    // Spring Data JDBC를 사용하여 데이터베이스와의 상호작용을 구성할 때 기본 설정을 제공하는 추상 클래스

    @Bean(destroyMethod = "close") // close 메서드 실행시 종료?
    public DataSource dataSource() {
        DataSource ds = new DataSource();

        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
        ds.setUsername("SPRING");
        ds.setPassword("oracle");

        ds.setInitialSize(2); //데이터베이스 연결 풀을 초기화할 때 생성할 초기 커넥션의 수를 설정
        ds.setMaxActive(10); //데이터베이스 연결 풀에서 동시에 사용할 수 있는 최대 활성 커넥션의 수를 설정
        ds.setTestWhileIdle(true); //커넥션 풀에서 유휴 상태의 커넥션을 테스트할지 여부를 설정
        ds.setMinEvictableIdleTimeMillis(1000 * 60); //유휴 커넥션이 제거되기 전에 유휴 상태로 유지되어야 하는 최소 시간
        ds.setTimeBetweenEvictionRunsMillis(1000 * 5); //유휴 커넥션을 검사하고 제거하는 작업(eviction run) 사이의 시간

        return ds;

    }

    @Bean // 외부에서 가져온건 수동 등록 해줘야함
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());

        return factoryBean.getObject();
    }

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }


}
