/**
 * Copyright (c) 2007 - 2009 Verkehrsautomatisierung Berlin GmbH. All Rights Reserved.
 *
 * This software is the proprietary information of Verkehrsautomatisierung
 * Berlin GmbH. Use is subject to license terms.
 *
 */
package de.huxendupsel.domain;

import javax.persistence.Transient;

/**
 * Diese Klasse ist fuer
 * 
 * @author doenmez
 * 
 */

@SuppressWarnings("rawtypes")
public abstract class Entity {
    
    private static final long serialVersionUID        = -1716603105315816348L;
    
    protected long            id;
    
    @Transient
    private boolean           isDirty                 = true;
    
    @Transient
    private boolean           changed                 = false;
    
    @Transient
    private boolean           transientEditingMode    = false;
    
    @Transient
    private boolean           transientJmsDeletingFlag;
    
    @Transient
    private boolean           transientCacheable      = true;
    
    @Transient
    private boolean           transientMigrationState = false;
    
    /**
     * Gibt die (Datenbank-)Id der Entity zureck.
     * 
     * @return the id
     */
    public abstract long getId();
    
    /**
     * Setzt die (Datenbank-)Id der Entity.
     * 
     * @param id
     *            the id to set
     */
    public abstract void setId(long id);
    
    /**
     * @param anotherEntity
     * @return
     */
    protected abstract boolean equalsTo(Entity anotherEntity);
    
    protected abstract int hashCode(int prime, int result);
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return hashCode(prime, result);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        boolean result = true;
        Entity other = (Entity) obj;
        if (id > 0 && other.id > 0) {
            result = id == other.id;
        }
        return result && equalsTo(other);
    }
    
    public String getSimpleName() {
        return getClass().getSimpleName();
    }
    
    @Override
    public String toString() {
        
        return " id:" + getId();
    }
    
    public void loadCollection() {
    }
    
}
