package de.huxendupsel.local;

import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.Stateful;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.logging.Logger;

/**
 * Session Bean implementation class SecuredSFSBService
 */
@Stateful
// use role not user info for runas
@RunAs("internal")
@RolesAllowed({ "internal" })
@SecurityDomain(value = "internal")
public class SecuredSFSBService implements SecuredSFSBServiceLocal {

	private final static Logger LOGGER = Logger
			.getLogger(SecuredSFSBService.class);

	@Override
	public void sayHello() {
		LOGGER.info("SecuredSFSBService.sayHello() - Hello from SFSB secured service bean.");
	}

}
