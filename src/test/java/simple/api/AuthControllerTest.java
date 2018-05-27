package simple.api;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@RunWith(SpringRestPactRunner.class)
@Provider("simple-auth-api")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@PactFolder("/Users/will/src/simple/web/target/pacts")
public class AuthControllerTest {
    @MockBean
    AuthService authService;

    @TestTarget
    public final Target target = new HttpTarget(8081);

    @State("test login")
    public void returnSuccessfulLogin() {
        when(authService.login("paul", "secret")).thenReturn(new User(123, "paul", "Paul", "Williams"));
    }
}