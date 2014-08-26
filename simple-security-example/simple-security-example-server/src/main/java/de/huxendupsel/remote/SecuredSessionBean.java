package de.huxendupsel.remote;

import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.logging.Logger;

import de.huxendupsel.local.SecuredSFSBServiceLocal;
import de.huxendupsel.local.SecuredSSBServiceLocal;
import de.huxendupsel.local.SecuredServiceBeanLocal;
import de.huxendupsel.local.SecuredSessionBeanLocal;

/**
 * Session Bean implementation class SecuredSessionBean
 */
@Stateless
@RunAs("internal")
@RolesAllowed({ "internal" })
@SecurityDomain(value = "internal")
public class SecuredSessionBean implements SecuredSessionBeanRemote,
		SecuredSessionBeanLocal {

	private final static Logger LOGGER = Logger
			.getLogger(SecuredSessionBean.class);

	@EJB
	SecuredServiceBeanLocal service;

	@EJB
	SecuredSFSBServiceLocal sfsbservice;

	@EJB
	SecuredSSBServiceLocal ssbservice;

	@Override
	public void sayHello() {
		LOGGER.info("SecuredSessionBean.sayHello() - hello from secured session bean");
	}

	@Override
	public void sayHelloService() {
		service.sayHello();
	}

	@Override
	public void sayHelloSFSBService() {
		sfsbservice.sayHello();
	}

	@Override
	public void sayHelloSSBService() {
		ssbservice.sayHello();
	}

	@Override
	public void sayHelloToEverybody() {
		LOGGER.debug("SecuredSessionBean.sayHelloToEverybody() - ...");
		sfsbservice.sayHello();
		ssbservice.sayHello();
		service.sayHello();
	}
}
