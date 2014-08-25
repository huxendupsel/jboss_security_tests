package de.huxendupsel.local;

import javax.ejb.Stateless;

import org.jboss.logging.Logger;

/**
 * Session Bean implementation class SecuredSSBService
 */
@Stateless
public class SecuredSSBService implements SecuredSSBServiceLocal {

	private final static Logger LOGGER = Logger
			.getLogger(SecuredSSBService.class);

	@Override
	public void sayHello() {
		LOGGER.info("SecuredSSBService.sayHello() - Hello from SSB secured service bean.");
	}

}
