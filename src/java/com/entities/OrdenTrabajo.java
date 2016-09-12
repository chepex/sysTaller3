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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "orden_trabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenTrabajo.findAll", query = "SELECT o FROM OrdenTrabajo o"),
    @NamedQuery(name = "OrdenTrabajo.findByIdOrdenTrabajo", query = "SELECT o FROM OrdenTrabajo o WHERE o.idOrdenTrabajo = :idOrdenTrabajo"),
    @NamedQuery(name = "OrdenTrabajo.findByFechaEntrega", query = "SELECT o FROM OrdenTrabajo o WHERE o.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "OrdenTrabajo.findByGarantiaHasta", query = "SELECT o FROM OrdenTrabajo o WHERE o.garantiaHasta = :garantiaHasta"),
    @NamedQuery(name = "OrdenTrabajo.findByAdelanto", query = "SELECT o FROM OrdenTrabajo o WHERE o.adelanto = :adelanto"),
    @NamedQuery(name = "OrdenTrabajo.findBySolicutudCliente", query = "SELECT o FROM OrdenTrabajo o WHERE o.solicutudCliente = :solicutudCliente"),
    @NamedQuery(name = "OrdenTrabajo.findByNotasInternas", query = "SELECT o FROM OrdenTrabajo o WHERE o.notasInternas = :notasInternas"),
    @NamedQuery(name = "OrdenTrabajo.findByNotasCotizacion", query = "SELECT o FROM OrdenTrabajo o WHERE o.notasCotizacion = :notasCotizacion"),
    @NamedQuery(name = "OrdenTrabajo.findByDiagnostico", query = "SELECT o FROM OrdenTrabajo o WHERE o.diagnostico = :diagnostico"),
    @NamedQuery(name = "OrdenTrabajo.findByRepuestos", query = "SELECT o FROM OrdenTrabajo o WHERE o.repuestos = :repuestos"),
    @NamedQuery(name = "OrdenTrabajo.findByMateriales", query = "SELECT o FROM OrdenTrabajo o WHERE o.materiales = :materiales"),
    @NamedQuery(name = "OrdenTrabajo.findByTrabajoExterno", query = "SELECT o FROM OrdenTrabajo o WHERE o.trabajoExterno = :trabajoExterno"),
    @NamedQuery(name = "OrdenTrabajo.findByTotalRespuesto", query = "SELECT o FROM OrdenTrabajo o WHERE o.totalRespuesto = :totalRespuesto"),
    @NamedQuery(name = "OrdenTrabajo.findByTotalManoObra", query = "SELECT o FROM OrdenTrabajo o WHERE o.totalManoObra = :totalManoObra"),
    @NamedQuery(name = "OrdenTrabajo.findByTotalIva", query = "SELECT o FROM OrdenTrabajo o WHERE o.totalIva = :totalIva"),
    @NamedQuery(name = "OrdenTrabajo.findByTotalGravado", query = "SELECT o FROM OrdenTrabajo o WHERE o.totalGravado = :totalGravado")})
public class OrdenTrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOrden_Trabajo")
    private Integer idOrdenTrabajo;    
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Column(name = "garantia_hasta")
    @Temporal(TemporalType.DATE)
    private Date garantiaHasta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "adelanto")
    private BigDecimal adelanto;
    @Size(max = 255)
    @Column(name = "solicutud_cliente")
    private String solicutudCliente;
    @Size(max = 255)
    @Column(name = "notas_internas")
    private String notasInternas;
    @Size(max = 255)
    @Column(name = "notas_cotizacion")
    private String notasCotizacion;
    @Size(max = 255)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Column(name = "repuestos")
    private Boolean repuestos;
    @Column(name = "materiales")
    private Boolean materiales;
    @Column(name = "trabajo_externo")
    private Boolean trabajoExterno;
    @Column(name = "total_respuesto")
    private BigDecimal totalRespuesto;
    @Column(name = "total_trabajo_externo")
    private BigDecimal totalTrabajoExterno;    
    @Column(name = "total_mano_obra")
    private BigDecimal totalManoObra;
    @Column(name = "total_iva")
    private BigDecimal totalIva;
    @Column(name = "total_gravado")
    private BigDecimal totalGravado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenTrabajoidOrdenTrabajo1")
    private List<Presupuesto> presupuestoList;
    @JoinColumn(name = "Vehiculo_idvehiculo", referencedColumnName = "idvehiculo")
    @ManyToOne(optional = false)
    private Vehiculo vehiculoidvehiculo;
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario usuarioidUsuario;
    @JoinColumn(name = "Tipo_Pago_idTipoPago", referencedColumnName = "idTipoPago")
    @ManyToOne(optional = false)
    private TipoPago tipoPagoidTipoPago;
    @JoinColumn(name = "Estado_Orden_idEstadoOrden", referencedColumnName = "idEstadoOrden")
    @ManyToOne(optional = false)
    private EstadoOrden estadoOrdenidEstadoOrden;
    @JoinColumn(name = "Cliente_idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Cliente clienteidCliente;

    public OrdenTrabajo() {
    }

    public BigDecimal getTotalTrabajoExterno() {
        return totalTrabajoExterno;
    }

    public void setTotalTrabajoExterno(BigDecimal totalTrabajoExterno) {
        this.totalTrabajoExterno = totalTrabajoExterno;
    }
    
    

    public OrdenTrabajo(Integer idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public Integer getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }

    public void setIdOrdenTrabajo(Integer idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getGarantiaHasta() {
        return garantiaHasta;
    }

    public void setGarantiaHasta(Date garantiaHasta) {
        this.garantiaHasta = garantiaHasta;
    }

    public BigDecimal getAdelanto() {
        return adelanto;
    }

    public void setAdelanto(BigDecimal adelanto) {
        this.adelanto = adelanto;
    }

    public String getSolicutudCliente() {
        return solicutudCliente;
    }

    public void setSolicutudCliente(String solicutudCliente) {
        this.solicutudCliente = solicutudCliente;
    }

    public String getNotasInternas() {
        return notasInternas;
    }

    public void setNotasInternas(String notasInternas) {
        this.notasInternas = notasInternas;
    }

    public String getNotasCotizacion() {
        return notasCotizacion;
    }

    public void setNotasCotizacion(String notasCotizacion) {
        this.notasCotizacion = notasCotizacion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Boolean getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(Boolean repuestos) {
        this.repuestos = repuestos;
    }

    public Boolean getMateriales() {
        return materiales;
    }

    public void setMateriales(Boolean materiales) {
        this.materiales = materiales;
    }

    public Boolean getTrabajoExterno() {
        return trabajoExterno;
    }

    public void setTrabajoExterno(Boolean trabajoExterno) {
        this.trabajoExterno = trabajoExterno;
    }

    public BigDecimal getTotalRespuesto() {
        return totalRespuesto;
    }

    public void setTotalRespuesto(BigDecimal totalRespuesto) {
        this.totalRespuesto = totalRespuesto;
    }

    public BigDecimal getTotalManoObra() {
        return totalManoObra;
    }

    public void setTotalManoObra(BigDecimal totalManoObra) {
        this.totalManoObra = totalManoObra;
    }

    public BigDecimal getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(BigDecimal totalIva) {
        this.totalIva = totalIva;
    }

    public BigDecimal getTotalGravado() {
        return totalGravado;
    }

    public void setTotalGravado(BigDecimal totalGravado) {
        this.totalGravado = totalGravado;
    }

    @XmlTransient
    public List<Presupuesto> getPresupuestoList() {
        return presupuestoList;
    }

    public void setPresupuestoList(List<Presupuesto> presupuestoList) {
        this.presupuestoList = presupuestoList;
    }

    public Vehiculo getVehiculoidvehiculo() {
        return vehiculoidvehiculo;
    }

    public void setVehiculoidvehiculo(Vehiculo vehiculoidvehiculo) {
        this.vehiculoidvehiculo = vehiculoidvehiculo;
    }

    public Usuario getUsuarioidUsuario() {
        return usuarioidUsuario;
    }

    public void setUsuarioidUsuario(Usuario usuarioidUsuario) {
        this.usuarioidUsuario = usuarioidUsuario;
    }

    public TipoPago getTipoPagoidTipoPago() {
        return tipoPagoidTipoPago;
    }

    public void setTipoPagoidTipoPago(TipoPago tipoPagoidTipoPago) {
        this.tipoPagoidTipoPago = tipoPagoidTipoPago;
    }

    public EstadoOrden getEstadoOrdenidEstadoOrden() {
        return estadoOrdenidEstadoOrden;
    }

    public void setEstadoOrdenidEstadoOrden(EstadoOrden estadoOrdenidEstadoOrden) {
        this.estadoOrdenidEstadoOrden = estadoOrdenidEstadoOrden;
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
        hash += (idOrdenTrabajo != null ? idOrdenTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenTrabajo)) {
            return false;
        }
        OrdenTrabajo other = (OrdenTrabajo) object;
        if ((this.idOrdenTrabajo == null && other.idOrdenTrabajo != null) || (this.idOrdenTrabajo != null && !this.idOrdenTrabajo.equals(other.idOrdenTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.OrdenTrabajo[ idOrdenTrabajo=" + idOrdenTrabajo + " ]";
    }
    
}
