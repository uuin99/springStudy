package springstudy.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import springstudy.user.domain.User;

public class UserDaoTest {
	UserDao dao;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() {
		dao = new UserDao();
		DataSource dataSource = new SingleConnectionDataSource(
				"jdbc:mysql://localhost/MySql", "root", "1q2w3e4R", true);
		dao.setDataSource(dataSource);
		
		this.user1 = new User("uuin99", "오정훈", "ojhk7615");
		this.user2 = new User("leegw", "이길원", "springno01");
		this.user3 = new User("bumjin", "박범진", "springno02");
	}
	
	@Test
	public void addAndGet() throws SQLException {
		
		dao.deleteAll();
		
		assertThat(dao.getCount(), is(0));
		
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
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
	
	public static void main(String[] args) {
		JUnitCore.main("springstudy.user.dao.UserDaoTest");
	}
}
