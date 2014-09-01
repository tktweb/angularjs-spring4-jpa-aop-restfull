package ca.tktweb.demo.rest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.tktweb.demo.model.UserEntity;
import ca.tktweb.demo.service.UserService;
import ca.tktweb.demo.utils.SessionObject;

@RestController
@RequestMapping("/user")
public class UserResource extends AbstractResources<UserEntity>{

	@Autowired
	private UserService userService;
	
	@PersistenceContext
    protected EntityManager entityManager;
	
	private static final Logger log = LoggerFactory.getLogger(UserResource.class);
	
	@Autowired
    private HttpServletRequest request;
	
	@RequestMapping(value="connect/{key}", 
			method = RequestMethod.GET,
			headers="Accept=application/json")
    public @ResponseBody UserEntity connect(@PathVariable String key) {
		
		log.info("connection of key : " + key);
		
		UserEntity user = super.get(key);
		
		SessionObject sessionObject = (SessionObject)request.getSession().getAttribute(SessionObject.SESSION_KEY);
		if (sessionObject == null){
			sessionObject = new SessionObject();
			request.getSession(true).setAttribute(SessionObject.SESSION_KEY, sessionObject);
		}
		sessionObject.setUser(user);
		return user;
    }
	

	@RequestMapping(value="init", 
			method = RequestMethod.GET,
			headers="Accept=application/json")
	@Transactional
    public @ResponseBody String init( ) {
		userService.init();
		return "Ok";
    }

	@Override
	public Class<UserEntity> getClazz() {
		return UserEntity.class;
	}
	
}
