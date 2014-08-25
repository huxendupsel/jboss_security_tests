/**
 * Copyright (c) 2007 - 2013 Verkehrsautomatisierung Berlin GmbH. All Rights Reserved.
 *
 * This software is the proprietary information of Verkehrsautomatisierung
 * Berlin GmbH. Use is subject to license terms.
 *
 */
package de.huxendupsel.client;

import java.util.Properties;

import javax.ejb.EJBAccessException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.huxendupsel.remote.SecuredSessionBeanRemote;

/**
 * @author Lau
 * @date 22.08.2014
 */
public class Main {

	/**
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException {

		Main main = new Main();
		SecuredSessionBeanRemote sessionBean = main.lookup();
		sessionBean.sayHello();
		try {
			sessionBean.sayHelloService();
		} catch (EJBAccessException e) {
			System.out.println("sessionBean.sayHelloService() not working");
		}
		try {
			sessionBean.sayHelloSFSBService();
		} catch (EJBAccessException e) {
			System.out.println("sessionBean.sayHelloSFSBService() not working");
		}
		try {
			sessionBean.sayHelloSSBService();
		} catch (EJBAccessException e) {
			System.out.println("sessionBean.sayHelloSSBService() not working");
		}

		System.out.println("now everybody");
		sessionBean.sayHelloToEverybody();
	}

	public SecuredSessionBeanRemote lookup() throws NamingException {

		Properties prop = new Properties();
		//
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		// prop.put("jboss.naming.client.ejb.context", true);
		//
		prop.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		//
		prop.put(Context.PROVIDER_URL, "remote://localhost:4447");

		// final Context context = new InitialContext(prop);
		final Context context = new InitialContext(prop);

		final String appName = "";
		final String moduleName = "remote";
		final String distinctName = "";
		final String beanName = "SecuredSessionBean";
		final String viewClassName = SecuredSessionBeanRemote.class.getName();

		return (SecuredSessionBeanRemote) context.lookup("" + appName + "/"
				+ moduleName + "/" + distinctName + "/" + beanName + "!"
				+ viewClassName);
	}

}
