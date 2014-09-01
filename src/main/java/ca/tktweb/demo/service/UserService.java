package ca.tktweb.demo.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.tktweb.demo.model.RoleEntity;
import ca.tktweb.demo.model.UserEntity;


@Service
public class UserService  {

	private static final Logger log = LoggerFactory.getLogger(UserService.class); 
	
	@PersistenceContext
    protected EntityManager entityManager;
	
	@Transactional
	public void init(){
		
		log.info("init database");
		
		Set<RoleEntity> roles = new HashSet<RoleEntity>();
		
		for (int j = 0 ; j<5;j++){
			RoleEntity roleEntity = new RoleEntity();
			roleEntity.setRoleType("Type"+j);
			
			entityManager.persist(roleEntity);
			
			roles.add(roleEntity);
		}
		
		
		for (int i = 0; i<10 ; i++){
			UserEntity user = new UserEntity();
			user.setMail("mail"+i+"@gmail.com");
			user.setMdp("mdp"+i);
			user.setName("name"+i);
			user.setRoles(roles);
			
			entityManager.persist(user);
		}
	}
	
	
}
