/**
 * Copyright (c) 2007 - 2013 Verkehrsautomatisierung Berlin GmbH. All Rights Reserved.
 *
 * This software is the proprietary information of Verkehrsautomatisierung
 * Berlin GmbH. Use is subject to license terms.
 *
 */
package de.huxendupsel.client;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJBAccessException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.huxendupsel.remote.SecuredSessionBeanRemote;

/**
 * @author Lau
 * @date 22.08.2014
 */
public class Main {

	enum PARAMETER {
		URL;
	}

	private static Map<PARAMETER, String> runParameter;

	/**
	 * @param runParameter2
	 */
	public Main(Map<PARAMETER, String> runParameter) {
		Main.runParameter = runParameter;
	}

	/**
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException {

		Map<PARAMETER, String> map = new HashMap<>();
		if (args != null) {
			for (String paramPair : args) {
				String value = paramPair.substring(paramPair.indexOf("=") + 1);
				String name = paramPair.substring(0, paramPair.indexOf("="));
				System.out.println("parameterName:" + name
						+ ", parametervalue:" + value);
				try {
					map.put(PARAMETER.valueOf(name.toUpperCase()), value);
				} catch (IllegalArgumentException | NullPointerException e) {
					e.printStackTrace();
					System.out.println("Wrong startparameter for Programm!");
					JDialog jDialog = new JDialog();
					jDialog.setSize(500, 200);
					jDialog.setModal(true);
					jDialog.setTitle("Wrong Startparameter");
					JPanel contentPane = new JPanel(new BorderLayout());
					contentPane
							.add(new JLabel(
									"The Startparameter is not set-up correctly pleas check it."),
									BorderLayout.CENTER);
					jDialog.setContentPane(contentPane);
					// jDialog.pack();
					jDialog.setLocationRelativeTo(null);
					jDialog.requestFocus();
					jDialog.setVisible(true);
					System.exit(-1);
				}

			}
		}

		Main main = new Main(map);
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
		prop.put("jboss.naming.client.ejb.context", true);
		//
		prop.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		//
		prop.put(
				Context.PROVIDER_URL,
				"remote://"
						+ (runParameter.get(PARAMETER.URL) != null ? runParameter
								.get(PARAMETER.URL) : "localhost:4447"));

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
