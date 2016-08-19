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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "STRASSE")
@SequenceGenerator(name = "STRASSE_SEQUENCE", sequenceName = "STRASSE_SEQ")
@NamedQueries(value = {
        @NamedQuery(name = Strasse.GET_STRASSEN, query = "SELECT e FROM Strasse e", cacheable = true),
        @NamedQuery(name = Strasse.GET_STRASSEN_OF_ORT, query = "SELECT e FROM Strasse e where e.ort=:ort", cacheable = true) })
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Strasse extends Entity {
    
    private static final long  serialVersionUID     = -4958902422012231800L;
    
    public static final String GET_STRASSEN         = "Strasse.getStrassen";
    
    public static final String GET_STRASSEN_OF_ORT  = "Strasse.getStrassenOfOrt";
    
    public static final String PROPERTY_BEZEICHNUNG = "bezeichnung";
    
    public static final String PROPERTY_ORT         = "ort";
    
    private String             bezeichnung;
    
    private Ort                ort;
    
    public Strasse() {
    }
    
    /**
     * @return the id
     */
    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "STRASSE_SEQUENCE")
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
    
    /**
     * 
     * @return the ort
     */
    @ManyToOne(targetEntity = Ort.class)
    @JoinColumn(name = "ort_id")
    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    public Ort getOrt() {
        return ort;
    }
    
    /**
     * @param ort
     *            the ort to set
     */
    public void setOrt(Ort ort) {
        Ort oldValue = getOrt();
        this.ort = ort;
        // firePropertyChange(PROPERTY_ORT, oldValue, ort);
    }
    
    @Override
    public int hashCode(int prime, int result) {
        result = prime * result
                + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
        result = prime * result + ((ort == null) ? 0 : ort.hashCode());
        return result;
    }
    
    @Override
    public boolean equalsTo(Entity obj) {
        Strasse other = (Strasse) obj;
        if (bezeichnung == null) {
            if (other.bezeichnung != null) {
                return false;
            }
        } else if (!bezeichnung.equals(other.bezeichnung)) {
            return false;
        }
        if (ort == null) {
            if (other.ort != null) {
                return false;
            }
        } else if (!ort.equals(other.ort)) {
            return false;
        }
        return true;
    }
    
    public static Strasse createInstance() {
        Strasse strasse = new Strasse();
        strasse.setOrt(Ort.createInstance());
        return strasse;
    }
    
}
