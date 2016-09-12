/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Desarrollo
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Cliente.findByEmpresa", query = "SELECT c FROM Cliente c WHERE c.empresa = :empresa"),
    @NamedQuery(name = "Cliente.findByTelefonoFijo", query = "SELECT c FROM Cliente c WHERE c.telefonoFijo = :telefonoFijo"),
    @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cliente.findByNit", query = "SELECT c FROM Cliente c WHERE c.nit = :nit"),
    @NamedQuery(name = "Cliente.findByRegistroFiscal", query = "SELECT c FROM Cliente c WHERE c.registroFiscal = :registroFiscal"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre like :nombre"),
    @NamedQuery(name = "Cliente.findByApellido", query = "SELECT c FROM Cliente c WHERE c.apellido = :apellido"),
    @NamedQuery(name = "Cliente.findByCelular", query = "SELECT c FROM Cliente c WHERE c.celular = :celular"),
    @NamedQuery(name = "Cliente.findByCorreo", query = "SELECT c FROM Cliente c WHERE c.correo = :correo"),
    @NamedQuery(name = "Cliente.findByCiudad", query = "SELECT c FROM Cliente c WHERE c.ciudad = :ciudad"),
    @NamedQuery(name = "Cliente.findByDepartamento", query = "SELECT c FROM Cliente c WHERE c.departamento = :departamento")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCliente")
    private Integer idCliente;
    @Size(max = 45)
    @Column(name = "empresa")
    private String empresa;
    @Size(max = 45)
    @Column(name = "telefono_fijo")
    private String telefonoFijo;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "nit")
    private String nit;
    @Size(max = 45)
    @Column(name = "registro_fiscal")
    private String registroFiscal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "celular")
    private String celular;
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ciudad")
    private BigDecimal ciudad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "departamento") 
    private String departamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteidCliente")
    private List<OrdenTrabajo> ordenTrabajoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteidCliente")
    private List<Vehiculo> vehiculoList;

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(Integer idCliente, String nombre, String celular, BigDecimal ciudad, String departamento) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.celular = celular;
        this.ciudad = ciudad;
        this.departamento = departamento;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRegistroFiscal() {
        return registroFiscal;
    }

    public void setRegistroFiscal(String registroFiscal) {
        this.registroFiscal = registroFiscal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public BigDecimal getCiudad() {
        return ciudad;
    }

    public void setCiudad(BigDecimal ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

 

    @XmlTransient
    public List<OrdenTrabajo> getOrdenTrabajoList() {
        return ordenTrabajoList;
    }

    public void setOrdenTrabajoList(List<OrdenTrabajo> ordenTrabajoList) {
        this.ordenTrabajoList = ordenTrabajoList;
    }

    @XmlTransient
    public List<Vehiculo> getVehiculoList() {
        return vehiculoList;
    }

    public void setVehiculoList(List<Vehiculo> vehiculoList) {
        this.vehiculoList = vehiculoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Cliente[ idCliente=" + idCliente + " ]";
    }
    
}
