package com.entities;

import com.entities.util.JsfUtil;
import com.entities.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "rolMenuController")
@SessionScoped
public class RolMenuController implements Serializable {

    @EJB
    private com.entities.RolMenuFacade ejbFacade;
    @EJB
    private com.entities.MenuFacade menuFacade;    
    @EJB
    private com.entities.RolFacade rolFacade;    
    private DualListModel<String> privilegios;    
    private List<RolMenu> items = null;
    private List<Rol> lrol = null;    
    private RolMenu selected;
    private Rol  selectedRol;

    public RolMenuController() {
    }

    public Rol getSelectedRol() {
        return selectedRol;
    }

    public void setSelectedRol(Rol selectedRol) {
        this.selectedRol = selectedRol;
    }

    
    public List<Rol> getLrol() {
        if(lrol==null){
            lrol= rolFacade.findAll();
        }
        return lrol;
    }

    public void setLrol(List<Rol> lrol) {
        this.lrol = lrol;
    }

    
    public DualListModel<String> getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(DualListModel<String> privilegios) {
        this.privilegios = privilegios;
    }

    
    public RolMenu getSelected() {
        return selected;
    }

    public void setSelected(RolMenu selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setIdrolMenu(0);
    }

    private RolMenuFacade getFacade() {
        return ejbFacade;
    }

    public RolMenu prepareCreate() {
        selected = new RolMenu();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        
        
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RolMenuCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RolMenuUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RolMenuDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<RolMenu> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
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

    public List<RolMenu> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<RolMenu> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = RolMenu.class)
    public static class RolMenuControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RolMenuController controller = (RolMenuController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rolMenuController");
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
            if (object instanceof RolMenu) {
                RolMenu o = (RolMenu) object;
                return getStringKey(o.getIdrolMenu());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RolMenu.class.getName()});
                return null;
            }
        }

    }
    public List<String> llenarViejo(){
        List<String> lrm = new ArrayList<>();
        List<Menu> lmenu = this.menuFacade.findAll();
        for(Menu m:lmenu){   
            lrm.add(m.getNombre());
        }        
        return lrm;       
    }
    
    public List<String> llenarNuevo(){
        List<String> lrm = new ArrayList<>();
        List<RolMenu> lmenu = this.ejbFacade.findByRol( this.selectedRol);
        for(RolMenu m:lmenu){   
            lrm.add(m.getIdmenu().getNombre());
        }        
        return lrm;       
    }    
    
    public void consultar(){
        
        List<String> viejo = llenarViejo();
        List<String> nuevo =llenarNuevo() ;         
        privilegios = new DualListModel<String>(viejo, nuevo);
    
    }
    
    public void actualizarRol(){
       
        try{
        
        
        Iterator<String> crunchifyIterator =  privilegios.getTarget().listIterator();
                int i =0;
                borrarRol();
		while (crunchifyIterator.hasNext()) {
                    RolMenu rm = new RolMenu(0);
                    rm.setActivo(Boolean.TRUE);
                    rm.setIdrol(selectedRol);
                    List<Menu> m = menuFacade.findByNombre(crunchifyIterator.next());
                    rm.setIdmenu(m.get(0));
                    this.ejbFacade.edit(rm);
                    i++;
		}
                
                FacesMessage msg = new FacesMessage();
                msg.setSeverity(FacesMessage.SEVERITY_INFO);
                msg.setSummary("Privilegios guardados correctamente");
                msg.setDetail( "total:"+i);

                FacesContext.getCurrentInstance().addMessage(null, msg);
        }catch(Exception ex){
            JsfUtil.addErrorMessage("Surgio un error grave-->"+ex.getMessage());
        }        
        
        
    
    }
    
    public void borrarRol(){
        
        try{
            List<RolMenu> lmenu = this.ejbFacade.findByRol(selectedRol);
            for( RolMenu rolm: lmenu){
                this.ejbFacade.remove(rolm);
            }    
        }catch(Exception ex){
              JsfUtil.addErrorMessage("Surgio un error grave-->"+ex.getMessage());
        }
        
    
    }

}
