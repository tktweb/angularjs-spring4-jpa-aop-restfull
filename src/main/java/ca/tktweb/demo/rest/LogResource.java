package ca.tktweb.demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.tktweb.demo.model.LogEntity;

@RestController
@RequestMapping("/log")
public class LogResource extends AbstractResources<LogEntity>{


	@Override
	public Class<LogEntity> getClazz() {
		return LogEntity.class;
	}
	
}
