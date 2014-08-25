package de.huxendupsel.local;

import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

/**
 * Session Bean implementation class SecuredServiceBean
 */
@Startup
@Singleton
@RunAs("testuser")
@RolesAllowed({ "internal" })
public class SecuredServiceBean implements SecuredServiceBeanLocal {

	private final static Logger LOGGER = Logger
			.getLogger(SecuredServiceBean.class);

	@Override
	public void sayHello() {
		LOGGER.info("SecuredServiceBean.sayHello() - Hello from secured service bean.");
	}
}
