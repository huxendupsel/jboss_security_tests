/**
 * Copyright (c) 2007 - 2009 Verkehrsautomatisierung Berlin GmbH. All Rights Reserved.
 *
 * This software is the proprietary information of Verkehrsautomatisierung
 * Berlin GmbH. Use is subject to license terms.
 *
 */
package de.huxendupsel.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Diese Klasse ist fuer
 * 
 * @author Doenmez
 * 
 */
@javax.persistence.Entity
@Table(name = "BUNDESLAND")
@SequenceGenerator(name = "BUNDESLAND_SEQUENCE", sequenceName = "BUNDESLAND_SEQ")
@NamedQueries(value = { @NamedQuery(name = Bundesland.GET_BUNDESLAENDER, query = "SELECT e FROM Bundesland e", cacheable = true) })
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Bundesland extends Entity {
    
    private static final long  serialVersionUID     = -3689010691209234500L;
    
    public static final String GET_BUNDESLAENDER    = "Bundesland.getBundeslaender";
    
    public static final String PROPERTY_BEZEICHNUNG = "bezeichnung";
    
    public static final String PROPERTY_CODE        = "code";
    
    private String             bezeichnung;
    
    private String             code;
    
    /**
     * @return the id
     */
    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "BUNDESLAND_SEQUENCE")
    public long getId() {
        return id;
    }
    
    /**
     * @param id
     *            the id to set
     */
    @Override
    public void setId(long id) {
        this.id = id;
    }
    
    public String getBezeichnung() {
        return bezeichnung;
    }
    
    public void setBezeichnung(String bezeichnung) {
        String oldValue = getBezeichnung();
        this.bezeichnung = bezeichnung;
        // firePropertyChange(PROPERTY_BEZEICHNUNG, oldValue, bezeichnung);
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        String oldValue = getCode();
        this.code = code;
        // firePropertyChange(PROPERTY_CODE, oldValue, code);
        
    }
    
    @Override
    protected boolean equalsTo(Entity anotherEntity) {
        Bundesland other = (Bundesland) anotherEntity;
        if (bezeichnung == null) {
            if (other.bezeichnung != null) {
                return false;
            }
        } else if (!bezeichnung.equals(other.bezeichnung)) {
            return false;
        }
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode(int prime, int result) {
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result
                + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
        return result;
    }
    
    public static Bundesland createInstance() {
        return new Bundesland();
    }
}
