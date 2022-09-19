package com.Ropvatika.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.Ropvatika.common.entity.Role;
import com.Ropvatika.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	@Test
	public void testCreateNewUserWithonerole() {
		Role roleAdmin=entityManager.find(Role.class,1);
		User usermansi=new User("mansi@gmail.com","mansi2020","mansi","chaudhari");
		usermansi.addRole(roleAdmin);
		User saveduser=repo.save(usermansi);
		assertThat(saveduser.getId()).isGreaterThan(0);
		
	}
	
	 @Test
	  public void testCreateNewUserWithtworoles() {
		  User Usershabnam=new User("shabnam@gmail.com","sha@123","shabnam","shahin");
		  Role roleEditor=new Role(3);
		  Role roleAssistant=new Role(5);
		  Usershabnam.addRole(roleEditor);
		  Usershabnam.addRole(roleAssistant);
		  User saveduser=repo.save(Usershabnam);
		  assertThat(saveduser.getId()).isGreaterThan(0);
	 }
	 
	 @Test
	 public void testListAllUser() {
		 Iterable<User> listusers=repo.findAll();
		 listusers.forEach(user ->System.out.println(user));
		 
	 }
	 @Test
	 public void testuserById() {
		User usermansi=repo.findById(1).get(); 
		System.out.println(usermansi);
		assertThat(usermansi).isNotNull();
	 }
	   
	 @Test
	 public void testUpdateUserDetails() {
		 User usermansi=repo.findById(1).get(); 
		 usermansi.setEnabled(true);
		 usermansi.setEmail("mansi123@gmail.com");
		 
		 repo.save(usermansi);
		 
	 }
	 @Test
	 public void testUpdateUserRole() {
		 User usershabnam=repo.findById(2).get(); 
		 Role roleEditor=new Role(3);
		 Role roleSalesperson=new Role(2);
		 usershabnam.getRoles().remove(roleEditor);
		 usershabnam.addRole(roleSalesperson);
		 repo.save(usershabnam);
		  
	 }
	 @Test
	 public void testDeleteUserRole() {
		 Integer userId=2;
		 repo.deleteById(userId);
		 repo.findById(userId);
	 }
	 

}
