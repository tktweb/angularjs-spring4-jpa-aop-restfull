 package ca.tktweb.demo.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.tktweb.demo.aop.Loggable;
import ca.tktweb.demo.model.UserEntity;

@Transactional
public abstract class AbstractResources<T> {
	
	private static Logger log = LoggerFactory.getLogger(AbstractResources.class);;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public abstract Class<T> getClazz();
	
	
	@Loggable	
	@RequestMapping(value="get/{key}", 
			method = RequestMethod.GET,
			headers="Accept=application/json")
    public @ResponseBody T get(@PathVariable String key) {
		T result = null;
		TypedQuery<T> q = entityManager.createQuery("select c from " + getClazz().getSimpleName()
				+ "  c WHERE c.key = :key ", getClazz());
		try{
			result = (T) q.setParameter("key", Long.valueOf(key)).getSingleResult();
		}catch(NoResultException e){		
			log.info("no entity found for class : " + getClazz() + " - with key : "  + key );
		}
		
		return result;
		
    }
	
	@RequestMapping(value="list", 
			method = RequestMethod.GET,
			headers="Accept=application/json")
	public List<T> list(){
		
		List<T> result = null;
		TypedQuery<T>  q = entityManager.createQuery("select c from " + getClazz().getSimpleName() + "  c ", getClazz());
		try{
			result =  q.getResultList();
		}catch(NoResultException e){		
			log.info("all list no entity found for class : " + getClazz());
		}
		return result;
	}
	
	@Loggable	
	@RequestMapping(value="create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public void  create(@RequestBody UserEntity obj) {
		try {
			 entityManager.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Loggable	
	@RequestMapping(value="update",  method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	public T update(@RequestBody T obj ){
		try {
			T result = entityManager.merge(obj);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Loggable	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	public String delete(@RequestBody T obj ) {
		try {
			log.info("delete entity : " + obj);
			obj = entityManager.merge(obj);
			entityManager.remove(obj);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
