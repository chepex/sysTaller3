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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v"),
    @NamedQuery(name = "Vehiculo.findByIdvehiculo", query = "SELECT v FROM Vehiculo v WHERE v.idvehiculo = :idvehiculo"),
    @NamedQuery(name = "Vehiculo.findByAnio", query = "SELECT v FROM Vehiculo v WHERE v.anio = :anio"),
    @NamedQuery(name = "Vehiculo.findByCliente", query = "SELECT v FROM Vehiculo v WHERE v.clienteidCliente.idCliente = :cliente"),    
    @NamedQuery(name = "Vehiculo.findByNumPlaca", query = "SELECT v FROM Vehiculo v WHERE v.numPlaca = :numPlaca"),
    @NamedQuery(name = "Vehiculo.findByNumMotor", query = "SELECT v FROM Vehiculo v WHERE v.numMotor = :numMotor")})
public class Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvehiculo")
    private Integer idvehiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "anio")
    private String anio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "num_placa")
    private String numPlaca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "num_motor")
    private String numMotor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculoidvehiculo")
    private List<OrdenTrabajo> ordenTrabajoList;
    @JoinColumn(name = "TipoVehiculo_idTipoVehiculo", referencedColumnName = "idTipoVehiculo")
    @ManyToOne(optional = false)
    private TipoVehiculo tipoVehiculoidTipoVehiculo;
    @JoinColumn(name = "Modelo_idModelo", referencedColumnName = "idModelo")
    @ManyToOne(optional = false)
    private Modelo modeloidModelo;
    @JoinColumn(name = "Marca_idMarca", referencedColumnName = "idMarca")
    @ManyToOne(optional = false)
    private Marca marcaidMarca;
    @JoinColumn(name = "Cliente_idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Cliente clienteidCliente;

    public Vehiculo() {
    }

    public Vehiculo(Integer idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public Vehiculo(Integer idvehiculo, String anio, String numPlaca, String numMotor) {
        this.idvehiculo = idvehiculo;
        this.anio = anio;
        this.numPlaca = numPlaca;
        this.numMotor = numMotor;
    }

    public Integer getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(Integer idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getNumPlaca() {
        return numPlaca;
    }

    public void setNumPlaca(String numPlaca) {
        this.numPlaca = numPlaca;
    }

    public String getNumMotor() {
        return numMotor;
    }

    public void setNumMotor(String numMotor) {
        this.numMotor = numMotor;
    }

    @XmlTransient
    public List<OrdenTrabajo> getOrdenTrabajoList() {
        return ordenTrabajoList;
    }

    public void setOrdenTrabajoList(List<OrdenTrabajo> ordenTrabajoList) {
        this.ordenTrabajoList = ordenTrabajoList;
    }

    public TipoVehiculo getTipoVehiculoidTipoVehiculo() {
        return tipoVehiculoidTipoVehiculo;
    }

    public void setTipoVehiculoidTipoVehiculo(TipoVehiculo tipoVehiculoidTipoVehiculo) {
        this.tipoVehiculoidTipoVehiculo = tipoVehiculoidTipoVehiculo;
    }

    public Modelo getModeloidModelo() {
        return modeloidModelo;
    }

    public void setModeloidModelo(Modelo modeloidModelo) {
        this.modeloidModelo = modeloidModelo;
    }

    public Marca getMarcaidMarca() {
        return marcaidMarca;
    }

    public void setMarcaidMarca(Marca marcaidMarca) {
        this.marcaidMarca = marcaidMarca;
    }

    public Cliente getClienteidCliente() {
        return clienteidCliente;
    }

    public void setClienteidCliente(Cliente clienteidCliente) {
        this.clienteidCliente = clienteidCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvehiculo != null ? idvehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.idvehiculo == null && other.idvehiculo != null) || (this.idvehiculo != null && !this.idvehiculo.equals(other.idvehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Vehiculo[ idvehiculo=" + idvehiculo + " ]";
    }
    
}
