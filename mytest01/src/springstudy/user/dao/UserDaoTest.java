package springstudy.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springstudy.user.domain.User;

public class UserDaoTest {

	@Test
	public void addAndGet() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		
		assertThat(dao.getCount(), is(0));
		
		User user = new User();
		user.setId("uuin99");
		user.setName("오정훈");
		user.setPassword("ojhk7615");
		
		dao.add(user);
		
		assertThat(dao.getCount(), is(1));

		User user2 = dao.get(user.getId());
		
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
		
	}

	public static void main(String[] args) {
		JUnitCore.main("springstudy.user.dao.UserDaoTest");
	}
}
