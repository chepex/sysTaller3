package com.entities;

import com.entities.util.JsfUtil;
import com.entities.util.JsfUtil.PersistAction;
import java.io.IOException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
 
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;

@ManagedBean(name = "ordenTrabajoController")
@SessionScoped
public class OrdenTrabajoController implements Serializable {

    @EJB
    private com.entities.OrdenTrabajoFacade ejbFacade; 
   @EJB
    private com.entities.EstadoOrdenFacade estadoOrdenFacade;     
    @EJB
    private com.entities.VehiculoFacade vehiculoFacade;    
    @EJB
    private com.entities.PresupuestoFacade presupuestoFacade;  
    @EJB
    private com.entities.UsuarioFacade usuarioFacade;      
    @EJB
    private com.entities.SB_Reportes reportes;      
    
    
      
    private List<OrdenTrabajo> items = null;
    private OrdenTrabajo selected;
    private List<Vehiculo> lvehiculo=new ArrayList<>();
       
  
    private List<Presupuesto> lpresupuesto=new ArrayList<>();
    private List<Presupuesto> listTrabajo=new ArrayList<>();    
  
    private Repuesto repuesto;
    private String cantidad;
    private BigDecimal precio;
    private String factura;
    private Proveedor proveedor;
    private TipoPago tpago;
    private Repuesto repuesto2;
    private String cantidad2;
    private BigDecimal precio2;
    private String factura2;
    private Proveedor proveedor2;
    private TipoPago tpago2;    
    private int idDetalle;
    private int idDetalle2;    
    private String vbuscar;
    
    
    public OrdenTrabajoController() {
    }

    public Repuesto getRepuesto2() {
        return repuesto2;
    }

    public void setRepuesto2(Repuesto repuesto2) {
        this.repuesto2 = repuesto2;
    }

    public String getCantidad2() {
        return cantidad2;
    }

    public void setCantidad2(String cantidad2) {
        this.cantidad2 = cantidad2;
    }

    public BigDecimal getPrecio2() {
        return precio2;
    }

    public void setPrecio2(BigDecimal precio2) {
        this.precio2 = precio2;
    }

    public String getFactura2() {
        return factura2;
    }

    public void setFactura2(String factura2) {
        this.factura2 = factura2;
    }

    public Proveedor getProveedor2() {
        return proveedor2;
    }

    public void setProveedor2(Proveedor proveedor2) {
        this.proveedor2 = proveedor2;
    }

    public TipoPago getTpago2() {
        return tpago2;
    }

    public void setTpago2(TipoPago tpago2) {
        this.tpago2 = tpago2;
    }

    public int getIdDetalle2() {
        return idDetalle2;
    }

    public void setIdDetalle2(int idDetalle2) {
        this.idDetalle2 = idDetalle2;
    }

    
    
    
    public List<Presupuesto> getListTrabajo() {
        return listTrabajo;
    }

    public void setListTrabajo(List<Presupuesto> listTrabajo) {
        this.listTrabajo = listTrabajo;
    }

   
    

    
    
    public String getVbuscar() {
        return vbuscar;
    }

    public void setVbuscar(String vbuscar) {
        this.vbuscar = vbuscar;
    }
    
    

    public TipoPago getTpago() {
        return tpago;
    }

    public void setTpago(TipoPago tpago) {
        this.tpago = tpago;
    }

    
    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    
    
    

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

   

    
    
    public List<Presupuesto> getLpresupuesto() {
        return lpresupuesto;
    }

    public void setLpresupuesto(List<Presupuesto> lpresupuesto) {
        this.lpresupuesto = lpresupuesto;
    }
 

    public List<Vehiculo> getLvehiculo() {
        return lvehiculo;
    }

    public void setLvehiculo(List<Vehiculo> lvehiculo) {
        this.lvehiculo = lvehiculo;
    }

    
    
    public OrdenTrabajo getSelected() {
        return selected;
    }

    public void setSelected(OrdenTrabajo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        this.selected.setIdOrdenTrabajo(0);
    }

    private OrdenTrabajoFacade getFacade() {
        return ejbFacade;
    }

    public OrdenTrabajo prepareCreate() {
        selected = new OrdenTrabajo();
        initializeEmbeddableKey();
        this.selected.setTotalGravado(new BigDecimal("0"));
        this.selected.setTotalIva(new BigDecimal("0"));
        this.selected.setTotalManoObra(new BigDecimal("0"));
        this.selected.setTotalRespuesto(new BigDecimal("0"));
        this.selected.setTotalTrabajoExterno(new BigDecimal("0"));
        EstadoOrden est =  estadoOrdenFacade.find(1);
        selected.setEstadoOrdenidEstadoOrden(est);
        idDetalle = 0;
        idDetalle2 = 0;    
        lpresupuesto=new ArrayList<>();
        listTrabajo=new ArrayList<>();
        return selected;
    }

    public String  create() {
       
        
        
         String username = JsfUtil.nombreUsuario();
        List<Usuario> lu=  usuarioFacade.findByNombre(username);
          Usuario usuario = null;
         if(!lu.isEmpty()){
             usuario = lu.get(0);
         }else{
              JsfUtil.addErrorMessage("El usuario :"+username +" no esta registrado");
             
            return "error" ;
         }
        
       
        this.selected.setUsuarioidUsuario(usuario); 
      //  this.selected.setEstadoOrdenidEstadoOrden(estado);
        
        int vid= this.ejbFacade.findById();
        
       if(this.selected.getIdOrdenTrabajo()==0){
                this.selected.setIdOrdenTrabajo( vid);
       }
        System.out.println("id-->"+this.selected.getIdOrdenTrabajo());
       
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OrdenTrabajoCreated"));
          
          if(!lpresupuesto.isEmpty()){
            for(Presupuesto p : this.lpresupuesto){
                int vid2= this.presupuestoFacade.findById();
                if(p.getIdpresupuesto()==0){
                    p.setIdpresupuesto(vid2);
                }       
                System.out.println("id-->"+this.selected.getIdOrdenTrabajo());
                p.setOrdenTrabajoidOrdenTrabajo1(selected);
                try{
                 presupuestoFacade.edit(p);
                 
                }catch(Exception ex){
                    System.out.println("---->"+ex.getMessage());
                }
          }
        }
          
          
          if(!listTrabajo.isEmpty()){
            for(Presupuesto p : this.listTrabajo){
                int vid2= this.presupuestoFacade.findById();
                if(p.getIdpresupuesto()==0){
                    p.setIdpresupuesto(vid2);
                }       
                System.out.println("id-->"+this.selected.getIdOrdenTrabajo());
                p.setOrdenTrabajoidOrdenTrabajo1(selected);
                try{
                 presupuestoFacade.edit(p);
                 
                }catch(Exception ex){
                    System.out.println("---->"+ex.getMessage());
                }
          }
        }          
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
        return "ok";
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OrdenTrabajoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OrdenTrabajoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<OrdenTrabajo> getItems() {
       
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<OrdenTrabajo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<OrdenTrabajo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = OrdenTrabajo.class)
    public static class OrdenTrabajoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrdenTrabajoController controller = (OrdenTrabajoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ordenTrabajoController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof OrdenTrabajo) {
                OrdenTrabajo o = (OrdenTrabajo) object;
                return getStringKey(o.getIdOrdenTrabajo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), OrdenTrabajo.class.getName()});
                return null;
            }
        }

    }
    
     public void asignarCliente(Cliente cliente){
        if(cliente!=null){
            this.selected.setClienteidCliente(cliente); 
            llenarVehiculo();
        }
        
    }
    
    public void asignarVehiculo(Vehiculo vehiculo){
        if(vehiculo!=null){
            this.selected.setVehiculoidvehiculo(vehiculo);             
        }
      
        
    }    
    
    public void llenarVehiculo(){
        
        if(selected.getClienteidCliente()!=null){
           
            this.lvehiculo=  vehiculoFacade.findByCliente(this.selected.getClienteidCliente());
        }
       
    }
    
    public void limpiar2(){
        
        this.selected = null;
          this.lvehiculo.clear();
          this.lvehiculo = new ArrayList<>();
          listTrabajo =  new ArrayList<>();
          this.lpresupuesto=  new ArrayList<>();
    }    
    
    public void agregarRepuesto(){
        idDetalle= idDetalle+1;
        Presupuesto presupuesto = new Presupuesto(idDetalle);
        presupuesto.setIdpresupuesto(0);
        presupuesto.setCantidad(cantidad);
        presupuesto.setFactura(factura);
        presupuesto.setRepuestoidRepuesto(repuesto);
        presupuesto.setProveedoridProveedor(proveedor);    
        
        BigDecimal vcantidad  = new BigDecimal(cantidad);
        presupuesto.setPrecio(precio);
        presupuesto.setTipoPagoidTipoPago(tpago);
        BigDecimal total = new BigDecimal("0");
        total = presupuesto.getPrecio().multiply(vcantidad);
        presupuesto.setIva(vcantidad.multiply( precio).multiply( new BigDecimal(".13")));
        presupuesto.setOrdenTrabajoidOrdenTrabajo1(selected);
         
        lpresupuesto.add(presupuesto);
        this.selected.setTotalIva(this.selected.getTotalIva().add(presupuesto.getIva()));
        if(this.repuesto.getCategoriaidCategoria().getIdCategoria()==1){
            this.selected.setTotalRespuesto(this.selected.getTotalRespuesto().add(total));
        }
        if(this.repuesto.getCategoriaidCategoria().getIdCategoria()==2){
            this.selected.setTotalManoObra(this.selected.getTotalManoObra().add(total));
        }        
        
        BigDecimal total2 = new BigDecimal("0");
        total2 =selected.getTotalManoObra().add(selected.getTotalRespuesto()).add(selected.getTotalTrabajoExterno()).add(selected.getTotalIva());

        this.selected.setTotalGravado(total2);
        JsfUtil.addSuccessMessage("Repuesto agregado correctamente");
    }
    
    
    public void agregarTrabajoFuera(){
        idDetalle2= idDetalle2+1;
        Presupuesto presupuesto = new Presupuesto(idDetalle2);
        presupuesto.setIdpresupuesto(0);
        presupuesto.setCantidad(cantidad2);
        presupuesto.setFactura(factura2);
        presupuesto.setRepuestoidRepuesto(repuesto2);
        presupuesto.setProveedoridProveedor(proveedor2);    
        BigDecimal vcantidad  = new BigDecimal(cantidad2);
        presupuesto.setPrecio(precio2);
        presupuesto.setTipoPagoidTipoPago(tpago2);
        BigDecimal total = new BigDecimal("0");
        total = presupuesto.getPrecio().multiply(vcantidad);
        presupuesto.setIva(vcantidad.multiply( precio2).multiply( new BigDecimal(".13")));
        presupuesto.setOrdenTrabajoidOrdenTrabajo1(selected);
         
        this.listTrabajo.add(presupuesto);
        this.selected.setTotalIva(this.selected.getTotalIva().add(presupuesto.getIva()));
        this.selected.setTotalTrabajoExterno(this.selected.getTotalTrabajoExterno().add(total));
        BigDecimal total2 = new BigDecimal("0");
        total2 =selected.getTotalManoObra().add(selected.getTotalRespuesto()).add(selected.getTotalTrabajoExterno()).add(selected.getTotalIva());

        this.selected.setTotalGravado(total2);
        JsfUtil.addSuccessMessage("Repuesto agregado correctamente");
    }    
    
    
    

    public void buscarOrden(){    
        
        if(JsfUtil.isNum(vbuscar)){           
            int val = Integer.valueOf(vbuscar);
            this.items = this.ejbFacade.findByOrden( val );
        }else{
            this.items = this.ejbFacade.findByEmpresaNombre(this.vbuscar);
        }
        
    } 
    
    public void selecionar(){
        this.lpresupuesto = presupuestoFacade.findByOrdenRepuesto(selected );
        this.listTrabajo = presupuestoFacade.findByOrdenTrabajoFuera(selected );
        this.lvehiculo=  vehiculoFacade.findByCliente(this.selected.getClienteidCliente());
    }    
    
    public void validar(){ 
        String msg ="ok";
        if(this.selected.getClienteidCliente()==null){
            msg ="Selecione un cliente";
        }
        if(this.selected.getVehiculoidvehiculo()==null){
            msg ="Selecione un vehiculo";
        }  
        if(this.selected.getFechaEntrega()==null){
            msg ="Fecha de entrega no valida";
        }          
        if(this.selected.getGarantiaHasta()==null){
            msg ="Fecha de garantia no valida";
        } 
        if(this.selected.getTipoPagoidTipoPago()==null){
            msg ="Tipo de pago no valido";
        }          
        if(!msg.equals("ok")){
           JsfUtil.addErrorMessage(msg);
        }else{
            this.create();
        }
    }
    
     public String reporteOrdenTrabajo() throws NamingException, SQLException, JRException, IOException{         
         try{
            HashMap params = new HashMap();  
            params.put("ORDEN",selected.getIdOrdenTrabajo() );         
            reportes.GenerarReporte("/ordenTrabajo/ordenTrabajo.jasper", params);
         }catch(Exception ex){         
              JsfUtil.addErrorMessage("error"+ex);
         }
        
        return "";           
    }      
     
    public void cambiarEstado(String estado) {
        
        if(estado!=null){
            int esta = Integer.valueOf(estado);
        
            System.out.println("esttado"+esta);
            if(selected!=null){
                EstadoOrden est =  this.estadoOrdenFacade.find(esta);
                this.selected.setEstadoOrdenidEstadoOrden(est);
                this.ejbFacade.edit(selected);
                   JsfUtil.addSuccessMessage("cambio de estado satisfactorio");
            }
        }
    
    }
}
