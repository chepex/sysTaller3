/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Desarrollo
 */
@Entity
@Table(name = "estado_orden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoOrden.findAll", query = "SELECT e FROM EstadoOrden e"),
    @NamedQuery(name = "EstadoOrden.findByIdEstadoOrden", query = "SELECT e FROM EstadoOrden e WHERE e.idEstadoOrden = :idEstadoOrden"),
    @NamedQuery(name = "EstadoOrden.findByNombre", query = "SELECT e FROM EstadoOrden e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadoOrden.findByDescripcion", query = "SELECT e FROM EstadoOrden e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "EstadoOrden.findByActivo", query = "SELECT e FROM EstadoOrden e WHERE e.activo = :activo")})
public class EstadoOrden implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstadoOrden")
    private Integer idEstadoOrden;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "Descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "activo")
    private String activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoOrdenidEstadoOrden")
    private List<OrdenTrabajo> ordenTrabajoList;

    public EstadoOrden() {
    }

    public EstadoOrden(Integer idEstadoOrden) {
        this.idEstadoOrden = idEstadoOrden;
    }

    public EstadoOrden(Integer idEstadoOrden, String nombre) {
        this.idEstadoOrden = idEstadoOrden;
        this.nombre = nombre;
    }

    public Integer getIdEstadoOrden() {
        return idEstadoOrden;
    }

    public void setIdEstadoOrden(Integer idEstadoOrden) {
        this.idEstadoOrden = idEstadoOrden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<OrdenTrabajo> getOrdenTrabajoList() {
        return ordenTrabajoList;
    }

    public void setOrdenTrabajoList(List<OrdenTrabajo> ordenTrabajoList) {
        this.ordenTrabajoList = ordenTrabajoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoOrden != null ? idEstadoOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoOrden)) {
            return false;
        }
        EstadoOrden other = (EstadoOrden) object;
        if ((this.idEstadoOrden == null && other.idEstadoOrden != null) || (this.idEstadoOrden != null && !this.idEstadoOrden.equals(other.idEstadoOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.EstadoOrden[ idEstadoOrden=" + idEstadoOrden + " ]";
    }
    
}
