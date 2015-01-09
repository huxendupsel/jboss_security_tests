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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.huxendupsel.remote.PreDestroyTestSFSBRemote;

/**
 * @author Lau
 * @date 09.01.2015
 */
public class TestPreDestroySFSB {
    
    enum PARAMETER {
        URL;
    }
    
    private static Map<PARAMETER, String> runParameter;
    
    /**
     * @param runParameter2
     */
    public TestPreDestroySFSB(Map<PARAMETER, String> runParameter) {
        TestPreDestroySFSB.runParameter = runParameter;
    }
    
    /**
     * @param args
     * @throws NamingException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws NamingException,
            InterruptedException {
        
        Map<PARAMETER, String> map = processRunParameter(args);
        
        TestPreDestroySFSB main = new TestPreDestroySFSB(map);
        
        PreDestroyTestSFSBRemote statefulBean = main.lookupSFSB();
        statefulBean.invokeBeanFromClient();
        
        // uncomment to test the remove call in SFSB
        // Thread.sleep(5000);
        // statefulBean.remove();
    }
    
    public PreDestroyTestSFSBRemote lookupSFSB() throws NamingException {
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
        final String beanName = "PreDestroyTestSFSB";
        final String viewClassName = PreDestroyTestSFSBRemote.class.getName();
        
        return (PreDestroyTestSFSBRemote) context.lookup("" + appName + "/"
                + moduleName + "/" + distinctName + "/" + beanName + "!"
                + viewClassName);
    }
    
    /**
     * Processing RunParameter if any
     * 
     * @param args
     *            run parameter
     * @return map of registered parameter
     */
    private static Map<PARAMETER, String> processRunParameter(String[] args) {
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
        return map;
    }
}
