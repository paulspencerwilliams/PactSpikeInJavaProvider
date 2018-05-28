package simple.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            HttpServletRequest request) {
        User loggedInUser = authService.login(username, password);
        return loggedInUser != null ? new ResponseEntity(loggedInUser,HttpStatus.OK) : new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
