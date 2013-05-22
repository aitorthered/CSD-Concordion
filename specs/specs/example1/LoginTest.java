package specs.example1;

import static org.mockito.Mockito.mock;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import com.example.dao.UserDao;
import com.example.service.UserService;

/* Run this class as a JUnit test. */

@RunWith(ConcordionRunner.class)
public class LoginTest {

	UserDao dao = mock(UserDao.class);
	UserService service = new UserService(dao);

	@Before
	public void setUp() {

	}

	public String canLogin(String userId, String pass) {
		boolean canLogin = service.canLogin(userId, pass);
		return canLogin ? "SI" : "NO";
	}

	public void addUser(String userId, String name, String email, String pass) {
		service.addUser(userId, name, email, pass);

	}
}
