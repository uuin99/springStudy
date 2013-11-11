package springstudy.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springstudy.user.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("whiteship");
		user.setName("��⼱");
		user.setPassword("married");
		
		dao.add(user);
		
		System.out.println(user.getId() + " ��� ����");
		
		User user2 = dao.get(user.getId());
		
		if (!user.getName().equals(user2.getName())) {
			System.out.println("�׽�Ʈ ���� (name)");
		} else if (!user.getPassword().equals(user2.getPassword())) {
			System.out.println("�׽�Ʈ ���� (password)");
		} else {
			System.out.println("��ȸ �׽�Ʈ ����");
		}
	}

}
