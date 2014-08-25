package de.huxendupsel.remote;

import javax.ejb.Remote;

@Remote
public interface SecuredSessionBeanRemote {

	void sayHelloService();

	void sayHello();

	void sayHelloToEverybody();

	void sayHelloSSBService();

	void sayHelloSFSBService();

}
