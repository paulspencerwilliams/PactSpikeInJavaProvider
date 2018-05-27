package simple.api;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public User login(String username, String password) {
        if ("Paul".toLowerCase().equals(username.toLowerCase())
                && "Secret".equals(password)) {
            return new User(123, "paul", "Paul", "Williams");
        }
        throw new RuntimeException("Invalid credentials");
    }
}
