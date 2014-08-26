package de.huxendupsel.local;

import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.logging.Logger;

/**
 * Session Bean implementation class SecuredSSBService
 */
@Stateless
@RunAs("internal")
@RolesAllowed({ "internal" })
@SecurityDomain(value = "internal")
public class SecuredSSBService implements SecuredSSBServiceLocal {

	private final static Logger LOGGER = Logger
			.getLogger(SecuredSSBService.class);

	@Override
	public void sayHello() {
		LOGGER.info("SecuredSSBService.sayHello() - Hello from SSB secured service bean.");
	}

}
