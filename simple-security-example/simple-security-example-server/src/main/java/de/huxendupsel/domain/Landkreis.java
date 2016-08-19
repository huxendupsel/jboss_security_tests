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
@Table(name = "LANDKREIS")
@SequenceGenerator(name = "LANDKREIS_SEQUENCE", sequenceName = "LANDKREIS_SEQ")
@NamedQueries(value = {
        @NamedQuery(name = Landkreis.GET_LANDKREISE, query = "SELECT e FROM Landkreis e", cacheable = true),
        @NamedQuery(name = Landkreis.GET_SYSTEM_LANDKREISE, query = "SELECT e FROM Landkreis e where e.schuelerdatenUeberSystem is true", cacheable = false) })
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Landkreis extends Entity {
    
    private static final long  serialVersionUID                   = 8263077599264748345L;
    
    public static final String GET_LANDKREISE                     = "Landkreis.getLandkreise";
    
    public static final String GET_SYSTEM_LANDKREISE              = "Landkreis.getLandkreiseSystem";
    
    public static final String PROPERTY_BEZEICHNUNG               = "bezeichnung";
    
    public static final String PROPERTY_KUERZEL                   = "kuerzel";
    
    public static final String PROPERTY_BUNDESLAND                = "bundesland";
    
    public static final String PROPERTY_SCHULERDATEN_UEBER_SYSTEM = "schuelerdatenUeberSystem";
    
    public static final String PROPERTY_KENNUNG                   = "kennung";
    
    private String             bezeichnung;
    
    private String             kuerzel;
    
    private Bundesland         bundesland;
    
    private boolean            schuelerdatenUeberSystem           = false;
    
    private String             kennung;
    
    /**
     * @return the id
     */
    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "LANDKREIS_SEQUENCE")
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
    
    public String getKuerzel() {
        return kuerzel;
    }
    
    public void setKuerzel(String kuerzel) {
        String oldValue = getKuerzel();
        this.kuerzel = kuerzel;
        // firePropertyChange(PROPERTY_KUERZEL, oldValue, kuerzel);
        
    }
    
    @ManyToOne
    @JoinColumn(name = "bundesland_id")
    // @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region =
    // "de.vaberlin.abovu.basisdaten.domain.Bundesland")
    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    public Bundesland getBundesland() {
        return bundesland;
    }
    
    public void setBundesland(Bundesland bundesland) {
        Bundesland oldValue = getBundesland();
        this.bundesland = bundesland;
        // firePropertyChange(PROPERTY_BUNDESLAND, oldValue, bundesland);
        
    }
    
    public boolean isSchuelerdatenUeberSystem() {
        return schuelerdatenUeberSystem;
    }
    
    public void setSchuelerdatenUeberSystem(boolean schuelerdatenUeberSystem) {
        if (schuelerdatenUeberSystem == isSchuelerdatenUeberSystem()) {
            return;
        }
        this.schuelerdatenUeberSystem = schuelerdatenUeberSystem;
        // firePropertyChange(PROPERTY_SCHULERDATEN_UEBER_SYSTEM,
        // !schuelerdatenUeberSystem, schuelerdatenUeberSystem);
    }
    
    public String getKennung() {
        return kennung;
    }
    
    public void setKennung(String kennung) {
        String oldValue = getKennung();
        this.kennung = kennung;
        // firePropertyChange(PROPERTY_KENNUNG, oldValue, kennung);
    }
    
    @Override
    protected boolean equalsTo(Entity anotherEntity) {
        Landkreis other = (Landkreis) anotherEntity;
        if (bezeichnung == null) {
            if (other.bezeichnung != null) {
                return false;
            }
        } else if (!bezeichnung.equals(other.bezeichnung)) {
            return false;
        }
        if (bundesland == null) {
            if (other.bundesland != null) {
                return false;
            }
        } else if (!bundesland.equals(other.bundesland)) {
            return false;
        }
        if (kennung == null) {
            if (other.kennung != null) {
                return false;
            }
        } else if (!kennung.equals(other.kennung)) {
            return false;
        }
        if (kuerzel == null) {
            if (other.kuerzel != null) {
                return false;
            }
        } else if (!kuerzel.equals(other.kuerzel)) {
            return false;
        }
        // if (schuelerdatenUeberSystem != other.schuelerdatenUeberSystem) {
        // return false;
        // }
        return true;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    protected int hashCode(int prime, int result) {
        result = prime * result
                + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
        result = prime * result
                + ((bundesland == null) ? 0 : bundesland.hashCode());
        result = prime * result + ((kennung == null) ? 0 : kennung.hashCode());
        result = prime * result + ((kuerzel == null) ? 0 : kuerzel.hashCode());
        result = prime * result + (schuelerdatenUeberSystem ? 1231 : 1237);
        return result;
    }
    
    public static Landkreis createInstance() {
        Landkreis ldk = new Landkreis();
        ldk.setBundesland(Bundesland.createInstance());
        return ldk;
    }
}
