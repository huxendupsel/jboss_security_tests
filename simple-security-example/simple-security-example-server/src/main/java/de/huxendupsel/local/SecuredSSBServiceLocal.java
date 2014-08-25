package de.huxendupsel.local;

import javax.ejb.Local;

@Local
public interface SecuredSSBServiceLocal {

	void sayHello();

}
