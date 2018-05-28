package simple.api;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
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
@PactBroker(host = "localhost", port = "80")
public class AuthControllerTest {
    @MockBean
    AuthService authService;

    @TestTarget
    public final Target target = new HttpTarget(8081);

    @State("Paul exists with password Secret")
    public void paulExistsWithPasswordSecret() {
        when(authService.login("Paul", "Secret")).thenReturn(new User(123, "paul", "Paul", "Williams"));
        when(authService.login("Paul", "Unconcealed")).thenReturn(null);
    }

    @State("No users exist")
    public void noUsersExist() {
    }
}