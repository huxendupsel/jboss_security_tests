jboss_security_tests
====================

###First Purpose:

   Remote call from a client to a secured stateless session bean. Further testing local calls between secured stateless beans, stateful session beans and singleton startup beans.  
   Be aware that the __RunAs annotation defines the ROLE not the user__ for which the securtiy identity is propagated. For further information see http://docs.oracle.com/javaee/6/tutorial/doc/bnbyl.html#bnbzb

###Second Purpose:

   Demonstrate the authorization issue invoking methods of a secured bean from the preDestroy method of a stateful session bean.  
   PreDestroy annotated methods execute in an unspecified transaction and securtiy context. So it's necessary to find a way to regain a sufficient security context.
   
###Configuration:

   * standalone.xml needs a security domain **_internal_**. 
   * server needs to setup a user **_testuser_** with the password **_password!1_**
      So at then end  the application-users.properties should include the  testuser and encrypted password and the 
      > testuser=encryptedPassword
      
      and the application-roles.properties should include the assignment of user and role (securitydomain)
      > testuser=internal