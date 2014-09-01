package ca.tktweb.demo.aop;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ca.tktweb.demo.model.LogEntity;
import ca.tktweb.demo.model.UserEntity;
import ca.tktweb.demo.utils.SessionObject;


@Aspect
@Component
@Transactional
public class LogAOP {

	private static final Logger log = Logger.getLogger(LogAOP.class.getName());
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	@Before("@annotation(ca.tktweb.demo.aop.Loggable)")
	public void logAction(JoinPoint joinPoint){
		log.info("enter in aop : logAction");
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		
		SessionObject sessionObject = (SessionObject)attr.getRequest().getSession().getAttribute(SessionObject.SESSION_KEY);
		
		if (sessionObject == null || sessionObject.getUser() == null){
			log.info("sessionObject is null");
			return;
		}
		
		UserEntity user = sessionObject.getUser();
		
		LogEntity logEntity = new LogEntity();
		
		logEntity.setCodeAction(joinPoint.getTarget().getClass().getSimpleName() + "-" + joinPoint.getSignature().getName() );
		logEntity.setOwner(user);
		
		entityManager.persist(logEntity);
		entityManager.flush();
		
	}
}
