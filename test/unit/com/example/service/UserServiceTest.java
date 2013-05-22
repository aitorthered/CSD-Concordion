package unit.com.example.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserService;

public class UserServiceTest {
	UserDao dao = mock(UserDao.class);
	UserService service = new UserService(dao);

	@Test
	public void testAddUser() {
		assertEquals(true,
				service.addUser("pepito", "Pepe", "pepe@micorreo.es", "mipass"));
	}

	@Test
	public void testAddUserCallsDaoWithAnyUser() {
		service.addUser("pepito", "Pepe", "pepe@micorreo.es", "mipass");
		verify(dao).insert((User) any());
	}

	@Test
	public void testAddUserCallsDaoWithSpecificUser() {
		service.addUser("pepito", "Pepe", "pepe@micorreo.es", "mipass");
		User user = new User() {
			@Override
			public boolean equals(Object obj) {
				return this.getUserId() == ((User) obj).getUserId();
			}
		};
		user.setName("Pepe");
		user.setUserId("pepito");
		user.setEmail("pepe@micorreo.es");
		verify(dao).insert(user);
	}
}
