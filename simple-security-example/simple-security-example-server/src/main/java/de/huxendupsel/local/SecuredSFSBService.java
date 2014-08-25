package de.huxendupsel.local;

import javax.ejb.Stateless;

import org.jboss.logging.Logger;

/**
 * Session Bean implementation class SecuredSFSBService
 */
@Stateless
public class SecuredSFSBService implements SecuredSFSBServiceLocal {

	private final static Logger LOGGER = Logger
			.getLogger(SecuredSFSBService.class);

	@Override
	public void sayHello() {
		LOGGER.info("SecuredSFSBService.sayHello() - Hello from SFSB secured service bean.");
	}

}
