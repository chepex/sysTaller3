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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "presupuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Presupuesto.findAll", query = "SELECT p FROM Presupuesto p"),
    @NamedQuery(name = "Presupuesto.findByIdpresupuesto", query = "SELECT p FROM Presupuesto p WHERE p.idpresupuesto = :idpresupuesto"),
    @NamedQuery(name = "Presupuesto.findByCantidad", query = "SELECT p FROM Presupuesto p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Presupuesto.findByOrden", query = "SELECT p FROM Presupuesto p WHERE p.ordenTrabajoidOrdenTrabajo1.idOrdenTrabajo = :orden  "),
    @NamedQuery(name = "Presupuesto.findByOrdenRepuesto", query = "SELECT p FROM Presupuesto p WHERE p.ordenTrabajoidOrdenTrabajo1.idOrdenTrabajo = :orden   and p.repuestoidRepuesto.categoriaidCategoria.idCategoria in (1,2)"),    
    @NamedQuery(name = "Presupuesto.findByOrdenTrabajoFuera", query = "SELECT p FROM Presupuesto p WHERE p.ordenTrabajoidOrdenTrabajo1.idOrdenTrabajo = :orden   and p.repuestoidRepuesto.categoriaidCategoria.idCategoria in ( 3)"),        
    @NamedQuery(name = "Presupuesto.findByPrecio", query = "SELECT p FROM Presupuesto p WHERE p.precio = :precio"),
    @NamedQuery(name = "Presupuesto.findByIva", query = "SELECT p FROM Presupuesto p WHERE p.iva = :iva"),
    @NamedQuery(name = "Presupuesto.findByFactura", query = "SELECT p FROM Presupuesto p WHERE p.factura = :factura")
   })
public class Presupuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpresupuesto")
    private Integer idpresupuesto;
 
    @Column(name = "cantidad")
    private String cantidad;
    
    @Column(name = "precio")
    private BigDecimal precio;
 
    @Column(name = "iva")
    private BigDecimal iva;
 
    @Column(name = "factura")
    private String factura;
 
    @JoinColumn(name = "TipoPago_idTipoPago", referencedColumnName = "idTipoPago")
    @ManyToOne(optional = false)
    private TipoPago tipoPagoidTipoPago;
    @JoinColumn(name = "Repuesto_idRepuesto", referencedColumnName = "idRepuesto")
    @ManyToOne(optional = false)
    private Repuesto repuestoidRepuesto;
    @JoinColumn(name = "Proveedor_idProveedor", referencedColumnName = "idProveedor")
    @ManyToOne(optional = false)
    private Proveedor proveedoridProveedor;
    @JoinColumn(name = "Orden_Trabajo_idOrden_Trabajo", referencedColumnName = "idOrden_Trabajo")
    @ManyToOne(optional = false)
    private OrdenTrabajo ordenTrabajoidOrdenTrabajo1;

    public Presupuesto() {
    }

    public Presupuesto(Integer idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

 

    public Integer getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(Integer idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

 

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

 

    public TipoPago getTipoPagoidTipoPago() {
        return tipoPagoidTipoPago;
    }

    public void setTipoPagoidTipoPago(TipoPago tipoPagoidTipoPago) {
        this.tipoPagoidTipoPago = tipoPagoidTipoPago;
    }

    public Repuesto getRepuestoidRepuesto() {
        return repuestoidRepuesto;
    }

    public void setRepuestoidRepuesto(Repuesto repuestoidRepuesto) {
        this.repuestoidRepuesto = repuestoidRepuesto;
    }

    public Proveedor getProveedoridProveedor() {
        return proveedoridProveedor;
    }

    public void setProveedoridProveedor(Proveedor proveedoridProveedor) {
        this.proveedoridProveedor = proveedoridProveedor;
    }

    public OrdenTrabajo getOrdenTrabajoidOrdenTrabajo1() {
        return ordenTrabajoidOrdenTrabajo1;
    }

    public void setOrdenTrabajoidOrdenTrabajo1(OrdenTrabajo ordenTrabajoidOrdenTrabajo1) {
        this.ordenTrabajoidOrdenTrabajo1 = ordenTrabajoidOrdenTrabajo1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpresupuesto != null ? idpresupuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Presupuesto)) {
            return false;
        }
        Presupuesto other = (Presupuesto) object;
        if ((this.idpresupuesto == null && other.idpresupuesto != null) || (this.idpresupuesto != null && !this.idpresupuesto.equals(other.idpresupuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Presupuesto[ idpresupuesto=" + idpresupuesto + " ]";
    }
    
}
