/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "rol_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolMenu.findAll", query = "SELECT r FROM RolMenu r"),
    @NamedQuery(name = "RolMenu.findByIdrolMenu", query = "SELECT r FROM RolMenu r WHERE r.idrolMenu = :idrolMenu"),
    @NamedQuery(name = "RolMenu.findByRol", query = "SELECT r FROM RolMenu r WHERE r.idrol.idRol = :rol"),    
    @NamedQuery(name = "RolMenu.findByActivo", query = "SELECT r FROM RolMenu r WHERE r.activo = :activo")})
public class RolMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrol_menu")
    private Integer idrolMenu;
    @Column(name = "activo")
    private Boolean activo;
    @JoinColumn(name = "idrol", referencedColumnName = "idRol")
    @ManyToOne
    private Rol idrol;
    @JoinColumn(name = "idmenu", referencedColumnName = "idMenu")
    @ManyToOne
    private Menu idmenu;

    public RolMenu() {
    }

    public RolMenu(Integer idrolMenu) {
        this.idrolMenu = idrolMenu;
    }

    public Integer getIdrolMenu() {
        return idrolMenu;
    }

    public void setIdrolMenu(Integer idrolMenu) {
        this.idrolMenu = idrolMenu;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Rol getIdrol() {
        return idrol;
    }

    public void setIdrol(Rol idrol) {
        this.idrol = idrol;
    }

    public Menu getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Menu idmenu) {
        this.idmenu = idmenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrolMenu != null ? idrolMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolMenu)) {
            return false;
        }
        RolMenu other = (RolMenu) object;
        if ((this.idrolMenu == null && other.idrolMenu != null) || (this.idrolMenu != null && !this.idrolMenu.equals(other.idrolMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.RolMenu[ idrolMenu=" + idrolMenu + " ]";
    }
    
}
