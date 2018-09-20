package com.lyj;

import com.lyj.springboot.basic.entity.User;
import com.lyj.springboot.basic.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test(){
		User user = userRepository.getByUserName("11");
		System.out.println(user);
	}

	@Test
	public void test2(){
		List<User> users = userRepository.getByUserNameStartingWithAndIdLessThan("3", 5);
		System.out.println(users);
	}

	@Test
	public void test3(){
		List<User> users = userRepository.getByIdInOrPasswordEquals(Arrays.asList(new Integer(1),new Integer(2)), "22");
		System.out.println(users);
	}

	@Test
	public void test4(){
		Integer maxIdUser = userRepository.getConutUserById();
		System.out.println(maxIdUser);
	}

	@Test
	public void test5(){
		User user = userRepository.getUserById(3);
		System.out.println(user);
	}

	@Test
	public void test6(){
		List<User> users = userRepository.getMaxIdUser();
		System.out.println(users);
	}

	@Test
	public void test7(){
		List<User> users = userRepository.getMaxIdUser2(2);
		System.out.println(users);
	}

	@Test
	public void test8(){
		List<User> users = userRepository.getMaxIdUser3(3);
		System.out.println(users);
	}

	@Test
	public void test9(){
		userRepository.updateUser("jdk",3);
	}

	@Test
	public void test10(){
		userRepository.deleteUser(3);
	}

}
