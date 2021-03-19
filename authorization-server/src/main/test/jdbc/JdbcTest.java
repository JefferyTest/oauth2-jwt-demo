package jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

public class JdbcTest {
    public static void main(String[] args) {

        JdbcTemplate jdbcTemplate = getJdbcTemplate2();
        System.out.println(jdbcTemplate.queryForMap("select 1 from dual"));
        //jdbcTemplate.update("alter user 'root'@'localhost' identified with mysql_native_password by '123456';");
    }

    private static JdbcTemplate getJdbcTemplate() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3308/mysql?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true" +
                "&serverTimezone=Asia/Shanghai&verifyServerCertificate=false&useSSL=false&allowPublicKeyRetrieval=true");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        return new JdbcTemplate(new HikariDataSource(hikariConfig));
    }

    private static JdbcTemplate getJdbcTemplate2() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setPort(3308);
        dataSource.setURL("jdbc:mysql://127.0.0.1:3308/mysql");
        return new JdbcTemplate(dataSource);
    }
}
