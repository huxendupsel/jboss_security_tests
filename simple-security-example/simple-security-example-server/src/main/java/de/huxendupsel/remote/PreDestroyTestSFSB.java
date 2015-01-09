package de.huxendupsel.remote;

import java.security.Principal;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.security.auth.callback.CallbackHandler;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.logging.Logger;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.callback.SecurityAssociationHandler;
import org.jboss.security.client.SecurityClient;
import org.jboss.security.client.SecurityClientFactory;

import de.huxendupsel.local.SecuredSSBServiceLocal;

/**
 * Session Bean implementation class PreDestroyTestSFSB
 */
@Stateful
// use role not user info for runas
@RunAs("internal")
@RolesAllowed({ "internal" })
@SecurityDomain(value = "internal")
@StatefulTimeout(unit = TimeUnit.SECONDS, value = 10)
public class PreDestroyTestSFSB implements PreDestroyTestSFSBRemote {
    
    private final static Logger LOGGER = Logger.getLogger(PreDestroyTestSFSB.class);
    
    @EJB
    SecuredSSBServiceLocal      ssb;
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * de.huxendupsel.remote.SecuredSFSBServiceRemote#invokeBeanFromClient()
     */
    @Override
    public void invokeBeanFromClient() {
        LOGGER.info("PreDestroyTestSFSB.invokeBeanFromClient() - invoked");
        ssb.sayHello();
    }
    
    @Override
    @Remove
    public void remove() {
        // do a security context method call
        LOGGER.info("PreDestroyTestSFSB.remove() - invoked");
        if (ssb != null) {
            ssb.sayHello();
        }
    }
    
    @PreDestroy
    public void preDestroy() throws Exception {
        // do a security context method call
        LOGGER.info("PreDestroyTestSFSB.preDestroy() - invoked");
        if (ssb != null) {
            SecurityClient securityClient = getSecurityClientForPreDestroy();
            securityClient.login();
            ssb.sayHello();
        }
    }
    
    private SecurityClient getSecurityClientForPreDestroy() throws Exception {
        SecurityClient security_client = null;
        Principal userPrincipal = new SimplePrincipal("testuser");
        CallbackHandler handler = new SecurityAssociationHandler(userPrincipal,
                "password!1".toCharArray());
        security_client = SecurityClientFactory.getSecurityClient();
        // Use the "client-login" security domain to establish a security
        // context
        security_client.setJAAS("client-login", handler);
        return security_client;
        
    }
    
}
