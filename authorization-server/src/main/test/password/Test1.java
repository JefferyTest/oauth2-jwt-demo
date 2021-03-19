package password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class Test1 {

    public static void main(String[] args) {
        String salt = KeyGenerators.string().generateKey();
        System.out.println(salt);
        Encryptors.stronger("password", salt);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("123456");
        System.out.println(result);
        System.out.println(encoder.matches("123", result));
    }
}
