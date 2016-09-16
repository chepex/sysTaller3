/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desarrollo
 */
@Entity
@Table(name = "compania")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compania.findAll", query = "SELECT c FROM Compania c"),
    @NamedQuery(name = "Compania.findByIdCompania", query = "SELECT c FROM Compania c WHERE c.idCompania = :idCompania"),
    @NamedQuery(name = "Compania.findByNombre", query = "SELECT c FROM Compania c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Compania.findByTelefono", query = "SELECT c FROM Compania c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Compania.findByCorreo", query = "SELECT c FROM Compania c WHERE c.correo = :correo"),
    @NamedQuery(name = "Compania.findByDireccion", query = "SELECT c FROM Compania c WHERE c.direccion = :direccion")})
public class Compania implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tasa_iva")
    private BigDecimal tasaIva;
    @Column(name = "nit")
    private Integer nit;
    @Column(name = "num_registro_iva")
    private Integer numRegistroIva;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCompania")
    private Integer idCompania;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "direccion")
    private String direccion;

    public Compania() {
    }

    public Compania(Integer idCompania) {
        this.idCompania = idCompania;
    }

    public Compania(Integer idCompania, String nombre, String telefono, String correo, String direccion) {
        this.idCompania = idCompania;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Integer getIdCompania() {
        return idCompania;
    }

    public void setIdCompania(Integer idCompania) {
        this.idCompania = idCompania;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompania != null ? idCompania.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compania)) {
            return false;
        }
        Compania other = (Compania) object;
        if ((this.idCompania == null && other.idCompania != null) || (this.idCompania != null && !this.idCompania.equals(other.idCompania))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Compania[ idCompania=" + idCompania + " ]";
    }

    public BigDecimal getTasaIva() {
        return tasaIva;
    }

    public void setTasaIva(BigDecimal tasaIva) {
        this.tasaIva = tasaIva;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public Integer getNumRegistroIva() {
        return numRegistroIva;
    }

    public void setNumRegistroIva(Integer numRegistroIva) {
        this.numRegistroIva = numRegistroIva;
    }
    
}
