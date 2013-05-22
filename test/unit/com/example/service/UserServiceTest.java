package unit.com.example.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

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

	@Test
	public void testLoginWithoutUserIdAndPasswordRetrieveFalse() {
		assertFalse(service.canLogin("", ""));
		verifyZeroInteractions(dao);
	}

	@Test
	public void testLoginWithUserIdAndPasswordCallsDao() {
		service.canLogin("pepito", "mipass");
		verify(dao).isValidPassword((User) any(), anyString());
	}

	@Test
	public void testLoginWithUserIdAndPasswordCallsDaoWithCorrectUser() {
		User user = new User();
		user.setUserId("pepito");
		user.setName("Pepe");
		user.setEmail("pepe@micorreo.es");
		when(dao.findById("pepito")).thenReturn(user);
		service.canLogin("pepito", "mipass");
		verify(dao).isValidPassword(user, "mipass");
	}

	@Test
	public void testLoginWithUserIdAndPasswordCallsDaoAndReturnSameValueThanDao() {
		User user = new User();
		user.setUserId("pepito");
		user.setName("Pepe");
		user.setEmail("pepe@micorreo.es");
		when(dao.findById("pepito")).thenReturn(user);
		when(dao.isValidPassword(user, "mipass")).thenReturn(true);
		assertTrue(service.canLogin("pepito", "mipass"));
		assertFalse(service.canLogin("pepito", "mipass2"));
	}
}
