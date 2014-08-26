/**
 * Copyright (c) 2007 - 2013 Verkehrsautomatisierung Berlin GmbH. All Rights Reserved.
 *
 * This software is the proprietary information of Verkehrsautomatisierung
 * Berlin GmbH. Use is subject to license terms.
 *
 */
package de.huxendupsel.local;

import javax.ejb.Local;

/**
 * @author Lau
 * @date 25.08.2014
 */
@Local
public interface SecuredSessionBeanLocal {

	void sayHello();

}
