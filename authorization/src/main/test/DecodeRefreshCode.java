import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.util.JsonParserFactory;

import java.util.Map;

/**
 * @author wangkang
 * @date 2019/4/3 0003
 */
public class DecodeRefreshCode {

    public static void main(String[] args) {
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3JkZXIiXSwidXNlcl9uYW1lIjoiZGVtbyIsInNjb3BlIjpbInNlbGVjdCJdLCJhdGkiOiJjZTY0M2ZmZS0xODY2LTQ5YzEtODkxNi04YWJhZjRhOTVjZjYiLCJleHAiOjE1NTQ1NDcyMTQsImF1dGhvcml0aWVzIjpbInVzZXIiXSwianRpIjoiYzQwN2Q5ZmMtZTVjMS00MzI3LThjNDYtM2Q5ZWUyMzE4ZjFhIiwidXJsIjoiaHR0cDovL3d3dy56aGVuZ3RvbmdpdC5jb20vIiwiY2xpZW50X2lkIjoiY2VudGVyIn0.Ogmv-MZJ9EYg4_lM2e3bJ9uoMvJq8D7v6YBchirkni0WAsbKJrZBicP6tomAnY8JHZ4mvY3-OJt6WWUkDTG2CYK6VEGZiRVBM9kRNtXUpfJ-0hQOKOVCc3w5Qo8nEFqHi5C-WPidDd49q0Od66KE-qbybYRtOoyP4TIHUio5e_Qx-lXztAPIRHwUe832sLtuiAmUTANDBL51XS5DIHkGlLp3CiJMMl8VWMbBgtfhEcsGc3H0wrdT4LmgkGMJho2Y6AEyXBx1iEiof6XBj_yd8YLWCT999oMowVlYZJsyw4ny8eVhqESx31lyTxXpH5VNYdP0hbvLtnODpVsHgfvDHQ";
        getCalms(token);
    }

    private static void getCalms(String token) {
        Map<String, Object> claims = JsonParserFactory.create()
                .parseMap(JwtHelper.decode(token).getClaims());
        System.out.println(claims);
    }
}
