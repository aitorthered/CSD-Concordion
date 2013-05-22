package specs.example1;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import com.example.service.UserService;

/* Run this class as a JUnit test. */

@RunWith(ConcordionRunner.class)
public class LoginTest {

	UserService service = new UserService();

	@Before
	public void setUp() {

	}

	public String canLogin(String userId, String pass) {

		boolean canLogin = service.canLogin(userId, pass);
		return canLogin ? "SI" : "NO";
	}

	public void addUser(String userId, String name, String email, String pass) {
		boolean userAdded = service.addUser(userId, name, email, pass);

	}
}
