package unit.com.example.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.InOrder;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserService;

public class UserServiceTest {
	UserDao dao = mock(UserDao.class);
	UserService service = new UserService(dao);

	@Test
	public void testAddUserCallsDaoWithAnyUser() {
		service.addUser("pepito", "Pepe", "pepe@micorreo.es", "mipass");
		verify(dao).insert((User) any());
	}

	@Test
	public void testAddUserCallsDaoWithSpecificUser() {
		service.addUser("pepito", "Pepe", "pepe@micorreo.es", "mipass");
		User user = new User();
		user.setName("Pepe");
		user.setUserId("pepito");
		user.setEmail("pepe@micorreo.es");
		verify(dao).insert(user);
	}

	@Test
	public void testAddUserCallsDaoCallsPassword() {
		service.addUser("pepito", "Pepe", "pepe@micorreo.es", "mipass");
		User user = new User();
		user.setName("Pepe");
		user.setUserId("pepito");
		user.setEmail("pepe@micorreo.es");
		verify(dao).setPassword((User) any(), anyString());
	}

	@Test
	public void testAddUserCallsDaoXCallsPasswordWithSpecificUser() {
		service.addUser("pepito", "Pepe", "pepe@micorreo.es", "mipass");
		User user = new User();
		user.setUserId("pepito");
		user.setName("Pepe");
		user.setEmail("pepe@micorreo.es");
		verify(dao).setPassword(user, "mipass");
	}

	@Test
	public void testAddUserCallsDaoXCallsPasswordInTheCorrectOrder() {
		service.addUser("pepito", "Pepe", "pepe@micorreo.es", "mipass");
		User user = new User();
		user.setUserId("pepito");
		user.setName("Pepe");
		user.setEmail("pepe@micorreo.es");

		InOrder inOrder = inOrder(dao, dao);
		inOrder.verify(dao).insert(user);
		inOrder.verify(dao).setPassword(user, "mipass");
	}

}
