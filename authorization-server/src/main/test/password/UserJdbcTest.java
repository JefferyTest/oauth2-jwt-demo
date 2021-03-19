package password;

import com.zt.demo.Oauth2AuthorizationApplication;
import com.zt.demo.user.AuthorizationRole;
import com.zt.demo.user.AuthorizationUser;
import com.zt.demo.user.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Oauth2AuthorizationApplication.class)
public class UserJdbcTest {

    @Autowired
    private UserMapper userDao;

    @Test
    public void contextLoads() {
        AuthorizationUser u1 = new AuthorizationUser();
        u1.setUsername("javaboy");
        u1.setPassword(new BCryptPasswordEncoder().encode("123"));
        u1.setAccountNonExpired(true);
        u1.setAccountNonLocked(true);
        u1.setCredentialsNonExpired(true);
        u1.setEnabled(true);
        List<AuthorizationRole> rs1 = new ArrayList<>();
        AuthorizationRole r1 = new AuthorizationRole();
        r1.setName("admin");
        r1.setNameZh("管理员");
        rs1.add(r1);
        u1.setRoles(rs1);
        userDao.save(u1);
        AuthorizationUser u2 = new AuthorizationUser();
        u2.setUsername("江南一点雨");
        u2.setPassword(new BCryptPasswordEncoder().encode("123"));
        u2.setAccountNonExpired(true);
        u2.setAccountNonLocked(true);
        u2.setCredentialsNonExpired(true);
        u2.setEnabled(true);
        List<AuthorizationRole> rs2 = new ArrayList<>();
        AuthorizationRole r2 = new AuthorizationRole();
        r2.setName("user");
        r2.setNameZh("普通用户");
        rs2.add(r2);
        u2.setRoles(rs2);
        userDao.save(u2);
    }
}