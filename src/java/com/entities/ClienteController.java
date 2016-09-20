package com.entities;

import com.entities.util.JsfUtil;
import com.entities.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
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

@ManagedBean(name = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {

    @EJB
    private com.entities.ClienteFacade ejbFacade;
    @EJB
    private com.entities.VehiculoFacade vehiculoFacade;    
    private List<Cliente> items = null;
    private Cliente selected;
    private Vehiculo selectedvehiculo;
    private List<Vehiculo> lvehiculo =new ArrayList<>();
    private String nombre;
    private int idVehiculo;
  
    
    public ClienteController() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

 
 

    
    public List<Vehiculo> getLvehiculo() {
        return lvehiculo;
    }

    public void setLvehiculo(List<Vehiculo> lvehiculo) {
        this.lvehiculo = lvehiculo;
    }

    
    
    public Vehiculo getSelectedvehiculo() {
        return selectedvehiculo;
    }

    public void setSelectedvehiculo(Vehiculo selectedvehiculo) {
        this.selectedvehiculo = selectedvehiculo;
    }
 
    
    
    public Cliente getSelected() {
        return selected;
    }

    public void setSelected(Cliente selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        this.selected.setIdCliente(0);
    }

    private ClienteFacade getFacade() {
        return ejbFacade;
    }

    public Cliente prepareCreate() {
       
        selected = new Cliente();
        initializeEmbeddableKey();
        idVehiculo = 1;
        selectedvehiculo = new  Vehiculo(idVehiculo);
        lvehiculo =new ArrayList<>();
        return selected;
    }

    public void create() {
        
      
               int vid= this.ejbFacade.findById();
               if(selected.getIdCliente()==0){
                   selected.setIdCliente(vid);
               }
               
               persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ClienteCreated"));
               if (!JsfUtil.isValidationFailed()) {
                   items = null;    // Invalidate list of items to trigger re-query.
               }
               for(Vehiculo v :this.lvehiculo){
                   v.setClienteidCliente(selected);
                   vehiculoFacade.edit(v);
               }        
        
       
    
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ClienteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ClienteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cliente> getItems() {
       
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

    public List<Cliente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Cliente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Cliente.class)
    public static class ClienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClienteController controller = (ClienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "clienteController");
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
            if (object instanceof Cliente) {
                Cliente o = (Cliente) object;
                return getStringKey(o.getIdCliente());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cliente.class.getName()});
                return null;
            }
        }

    }   
    
    public List<Cliente> autoCompleteCliente(String valor) {
       List<Cliente > lcliente = getFacade().findByNombreCodigo(valor);
        System.out.println("buscar-->"+valor    );
       if(!lcliente.isEmpty()){
            for(Cliente c : lcliente){
             System.out.println("lcliente--->"+c);
           }
       }
      
      
        return lcliente;
        
    } 
    
    public void limpiar(){
    selected= null;
    }
    
    public void addVechiculo(){
        idVehiculo =idVehiculo +1;
        selectedvehiculo.setIdvehiculo(idVehiculo);
        this.lvehiculo.add(selectedvehiculo);
        selectedvehiculo = new Vehiculo(0);
    }
    
    public void buscarCliente(){    
        this.items = this.ejbFacade.findByNombreCedulaNit(nombre);
    }
    
    public void selecionar(){
      this.lvehiculo=  vehiculoFacade.findByCliente(selected);
         idVehiculo = 1;
        
      this.selectedvehiculo = new Vehiculo(idVehiculo);
    }
    
    
    public String  validar(){
        String msg ="ok";
        /*validar nombre empresa*/  
        
        List<Cliente> vnombre  = this.ejbFacade.findByNombre(selected.getEmpresa());
        System.out.println(" nombre empresa-->"+selected.getEmpresa());
        System.out.println(" nombre empresa2-->"+vnombre);
        if(!vnombre.isEmpty()){
            msg ="Nombre empresa ya registrado, cliente:"+ vnombre.get(0).getIdCliente();
        }
        List<Cliente> vnit  = this.ejbFacade.findByNit(selected.getNit());
        System.out.println(" vnit-->"+selected.getNit());
        System.out.println(" vnit-->"+vnit);
        if(!vnit.isEmpty()){
            msg ="Nit ya registrado , vnit:"+ vnit.get(0).getIdCliente();
        }   
        List<Cliente> vfijo  = this.ejbFacade.findByTelefonoFijo(selected.getTelefonoFijo());
        System.out.println(" vfijo-->"+selected.getTelefonoFijo());
        System.out.println(" vfijo-->"+vfijo);
        if(!vfijo.isEmpty()){
            msg ="Numero fijo ya registrado , cliente:"+ vfijo.get(0).getIdCliente();
        }   
        List<Cliente> vfiscal  = this.ejbFacade.findByRegistroFiscal(selected.getRegistroFiscal());
        System.out.println(" vfiscal-->"+selected.getRegistroFiscal());
        System.out.println(" vfiscal-->"+vfiscal);
        if(!vfiscal.isEmpty()){
            msg ="Registro fiscal ya registrado , cliente:"+ vfiscal.get(0).getIdCliente();
        }         
        
        /*validar nit*/     
        /*validar tel*/     
        /*validar fijo*/     
        /*validar registro fiscal*/     
        if(msg.equals("ok"))    {
            this.create();
        }else{
            JsfUtil.addErrorMessage(msg);
        }
         return "";
    }

}
