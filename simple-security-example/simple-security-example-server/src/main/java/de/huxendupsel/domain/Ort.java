/**
 * Copyright (c) 2007 - 2009 Verkehrsautomatisierung Berlin GmbH. All Rights Reserved.
 *
 * This software is the proprietary information of Verkehrsautomatisierung
 * Berlin GmbH. Use is subject to license terms.
 *
 */
package de.huxendupsel.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Diese Klasse ist fuer
 * 
 * @author Doenmez
 * 
 */
@javax.persistence.Entity
@Table(name = "ORT")
@SequenceGenerator(name = "ORT_SEQUENCE", sequenceName = "ORT_SEQ")
@NamedQueries(value = {
        @NamedQuery(name = Ort.GET_ORTE, query = "FROM Ort e", cacheable = true),
        @NamedQuery(name = Ort.GET_ORTE_BY_NAMEN, query = "FROM Ort e where e.bezeichnung=:bezeichnung and e.ortsteil=:ortsteil and e.plz=:plz", cacheable = true) })
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@org.hibernate.annotations.Table(appliesTo = "ORT", indexes = { @Index(name = "IX_ORT_bezeichnung", columnNames = { "bezeichnung" }) })
public class Ort extends Entity {
    
    private static final long  serialVersionUID     = -593371474809281567L;
    
    public static final String GET_ORTE             = "Ort.getOrte";
    
    public static final String GET_ORTE_BY_NAMEN    = "Ort.getOrteByNamen";
    
    public static final String PROPERTY_BEZEICHNUNG = "bezeichnung";
    
    public static final String PROPERTY_ORTSTEIL    = "ortsteil";
    
    public static final String PROPERTY_LANDKREIS   = "landkreis";
    
    public static final String PROPERTY_STRASSEN    = "strassen";
    
    public static final String PROPERTY_PLZ         = "plz";
    
    private String             bezeichnung;
    
    private String             ortsteil;
    
    private Landkreis          landkreis;
    
    private List<Strasse>      strassen             = new ArrayList<Strasse>();
    
    private String             plz;
    
    public Ort() {
    }
    
    /**
     * @return the id
     */
    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ORT_SEQUENCE")
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
    
    @Override
    public void loadCollection() {
        Hibernate.initialize(getStrassen());
        super.loadCollection();
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
     * @return the plz
     */
    public String getPlz() {
        return plz;
    }
    
    /**
     * @param plz
     *            the plz to set
     */
    public void setPlz(String plz) {
        String oldValue = getPlz();
        this.plz = plz;
        // firePropertyChange(PROPERTY_PLZ, oldValue, plz);
    }
    
    /**
     * @return the the ortsteil
     */
    public String getOrtsteil() {
        return ortsteil;
    }
    
    /**
     * @param ortsteil
     *            the ortsteil to set
     */
    public void setOrtsteil(String ortsteil) {
        String oldValue = getOrtsteil();
        this.ortsteil = ortsteil;
        // firePropertyChange(PROPERTY_ORTSTEIL, oldValue, ortsteil);
        
    }
    
    @ManyToOne(targetEntity = Landkreis.class)
    @JoinColumn(name = "landkreis_id")
    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    public Landkreis getLandkreis() {
        return landkreis;
    }
    
    public void setLandkreis(Landkreis landkreis) {
        Landkreis oldValue = getLandkreis();
        this.landkreis = landkreis;
        // firePropertyChange(PROPERTY_LANDKREIS, oldValue, landkreis);
    }
    
    @OneToMany(mappedBy = "ort", cascade = CascadeType.ALL, targetEntity = Strasse.class)
    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
    @Fetch(FetchMode.SUBSELECT)
    public List<Strasse> getStrassen() {
        return strassen;
    }
    
    public void setStrassen(List<Strasse> strassen) {
        this.strassen = strassen;
        // firePropertyChange(PROPERTY_STRASSEN, null, strassen);
    }
    
    @Override
    public int hashCode(int prime, int result) {
        result = prime * result
                + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
        result = prime * result
                + ((landkreis == null) ? 0 : landkreis.hashCode());
        result = prime * result
                + ((ortsteil == null) ? 0 : ortsteil.hashCode());
        result = prime * result + ((plz == null) ? 0 : plz.hashCode());
        
        // liefert unterschiedliche Hashwerte, auch bei gleichen Objekten, nutzt
        // nicht die hashCode von ArrayList sondern von PersistentBag.class
        // (hibernate-core.jar), die auf die Object.hashCode geht --> hashcode
        // je nach instance unterschiedlich
        // result = prime * result
        // + ((strassen == null) ? 0 : strassen.hashCode());
        return result;
    }
    
    @Override
    public boolean equalsTo(Entity obj) {
        Ort other = (Ort) obj;
        if (bezeichnung == null) {
            if (other.bezeichnung != null) {
                return false;
            }
        } else if (!bezeichnung.equals(other.bezeichnung)) {
            return false;
        }
        if (landkreis == null) {
            if (other.landkreis != null) {
                return false;
            }
        } else if (!landkreis.equals(other.landkreis)) {
            return false;
        }
        if (ortsteil == null) {
            if (other.ortsteil != null) {
                return false;
            }
        } else if (!ortsteil.equals(other.ortsteil)) {
            return false;
        }
        if (plz == null) {
            if (other.plz != null) {
                return false;
            }
        } else if (!plz.equals(other.plz)) {
            return false;
        }
        // if (strassen == null) {
        // if (other.strassen != null) {
        // return false;
        // }
        // } else if (!strassen.equals(other.strassen)) {
        // return false;
        // }
        return true;
    }
    
    public static Ort createInstance() {
        return new Ort();
    }
    
}
