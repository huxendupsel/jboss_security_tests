package de.huxendupsel.local;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.logging.Logger;

/**
 * Session Bean implementation class SecuredServiceBean
 */
@Startup
@Singleton
@RunAs("internal")
@RolesAllowed({ "internal" })
@SecurityDomain(value = "internal")
public class SecuredServiceBean implements SecuredServiceBeanLocal {

	private final static Logger LOGGER = Logger
			.getLogger(SecuredServiceBean.class);

	@EJB
	SecuredSessionBeanLocal ssbl;

	@Override
	public void sayHello() {
		LOGGER.info("SecuredServiceBean.sayHello() - Hello from secured service bean.");
	}

	@PostConstruct
	public void init() {
		ssbl.sayHello();
	}
}
