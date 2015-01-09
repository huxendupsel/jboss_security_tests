package de.huxendupsel.remote;

import javax.ejb.Remote;

@Remote
public interface PreDestroyTestSFSBRemote {
    
    void invokeBeanFromClient();
    
    void remove();
}
