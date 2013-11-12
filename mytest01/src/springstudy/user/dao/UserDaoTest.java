package springstudy.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import springstudy.user.domain.User;

public class UserDaoTest {

	@Test
	public void addAndGet() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		
		assertThat(dao.getCount(), is(0));
		
		User user1 = new User("uuin99", "오정훈", "ojhk7615");
		User user2 = new User("leegw", "이길원", "springno01");
		
		dao.add(user1);
		dao.add(user2);
		
		assertThat(dao.getCount(), is(2));

		User userget1 = dao.get(user1.getId());
		
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));
		
		User userget2 = dao.get(user2.getId());
		
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
	}
	
	@Test
	public void count() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		User user1 = new User("uuin99", "오정훈", "ojhk7615");
		User user2 = new User("leegw", "이길원", "springno01");
		User user3 = new User("bumjin", "박범진", "springno02");
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getIserFailure() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
	
	public static void main(String[] args) {
		JUnitCore.main("springstudy.user.dao.UserDaoTest");
	}
}
