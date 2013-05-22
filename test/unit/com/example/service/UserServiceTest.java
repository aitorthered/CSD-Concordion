package unit.com.example.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InOrder;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserService;

public class UserServiceTest {
	private static final String USER_PASS = "mipass";
	private static final String USER_EMAIL = "pepe@micorreo.es";
	private static final String USER_NAME = "Pepe";
	private static final String USER_USER_ID = "pepito";
	UserDao dao = mock(UserDao.class);
	UserService service = new UserService(dao);

	@Test
	public void testAddUserCallsDaoWithAnyUser() {
		service.addUser(USER_USER_ID, USER_NAME, USER_EMAIL, USER_PASS);
		verify(dao).insert((User) any());
	}

	@Test
	public void testAddUserCallsDaoWithSpecificUser() {
		service.addUser(USER_USER_ID, USER_NAME, USER_EMAIL, USER_PASS);
		User user = createUser();
		verify(dao).insert(user);
	}

	@Test
	public void testAddUserCallsDaoCallsPassword() {
		service.addUser(USER_USER_ID, USER_NAME, USER_EMAIL, USER_PASS);
		verify(dao).setPassword((User) any(), anyString());
	}

	@Test
	public void testAddUserCallsDaoXCallsPasswordWithSpecificUser() {
		service.addUser(USER_USER_ID, USER_NAME, USER_EMAIL, USER_PASS);
		User user = createUser();
		verify(dao).setPassword(user, USER_PASS);
	}

	@Test
	public void testAddUserCallsDaoXCallsPasswordInTheCorrectOrder() {
		service.addUser(USER_USER_ID, USER_NAME, USER_EMAIL, USER_PASS);
		User user = createUser();

		InOrder inOrder = inOrder(dao, dao);
		inOrder.verify(dao).insert(user);
		inOrder.verify(dao).setPassword(user, USER_PASS);
	}

	@Test
	public void testLoginWithoutUserIdAndPasswordRetrieveFalse() {
		assertFalse(service.canLogin("", ""));
		verifyZeroInteractions(dao);
	}

	@Test
	public void testLoginWithUserIdAndPasswordCallsDao() {
		User user = createUser();
		when(dao.findById(USER_USER_ID)).thenReturn(user);
		service.canLogin(USER_USER_ID, USER_PASS);
		verify(dao).isValidPassword((User) any(), anyString());
	}

	@Test
	public void testLoginWithUserIdAndPasswordCallsDaoWithCorrectUser() {
		User user = createUser();
		when(dao.findById(USER_USER_ID)).thenReturn(user);
		service.canLogin(USER_USER_ID, USER_PASS);
		verify(dao).isValidPassword(user, USER_PASS);
	}

	private User createUser() {
		User user = new User();
		user.setUserId(USER_USER_ID);
		user.setName(USER_NAME);
		user.setEmail(USER_EMAIL);
		return user;
	}

	@Test
	public void testLoginWithUserIdAndPasswordCallsDaoAndReturnSameValueThanDao() {
		User user = createUser();
		when(dao.findById(USER_USER_ID)).thenReturn(user);
		when(dao.isValidPassword(user, USER_PASS)).thenReturn(true);
		assertTrue(service.canLogin(USER_USER_ID, USER_PASS));
		assertFalse(service.canLogin(USER_USER_ID, "mipass2"));
	}

	@Test
	public void testWhenDaoReturnsUserNotExistIsValidPasswordIsNotCall() {
		when(dao.findById(USER_USER_ID)).thenReturn(null);
		assertFalse(service.canLogin(USER_USER_ID, USER_PASS));
		verify(dao, times(0)).isValidPassword(((User) any()), anyString());
	}
}
