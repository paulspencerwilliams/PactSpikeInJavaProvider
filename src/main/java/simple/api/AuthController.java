package simple.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/login")
    public User login(@RequestParam(value="username", required = false) String username,
                      @RequestParam(value="password", required = false) String password) {
        if ("Paul".toLowerCase().equals(username.toLowerCase())
                && "Secret".equals(password)) {
            return new User(123, "paul", "Paul", "Williams");
        }
        throw new RuntimeException("Invalid credentials");
    }
}
