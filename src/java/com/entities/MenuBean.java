
package com.entities;
  
 
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

@ManagedBean(name = "MenuBean")
@ViewScoped
public class MenuBean implements Serializable {
    @EJB
    private RolMenuFacade rolMenuFacade; 
    
    @EJB
    private UsuarioFacade usuarioFacade;     
    
 
    
       
    private MenuModel menus;

    
    public MenuModel generarMenus() {
        
         HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
           String usuario="";
          try{
                   usuario = (String) session.getAttribute("SSUSUARIO") ;	      
                  }catch(Exception ex){
                    usuario = "";   
                  }
          
        
        MenuModel vmodulos =  new DefaultMenuModel();
      
            
            
        DefaultSubMenu firstSubmenu = null;        
        DefaultMenuItem item = new DefaultMenuItem();
        item.setValue("Home");
        item.setOutcome("/index");
        item.setIcon("ui-icon-home");
        vmodulos.addElement(item);  
        
          if(usuario!=null){
              
              System.out.println("usuario--->"+usuario);
              System.out.println("usuario--->"+usuario);
              System.out.println("usuario--->"+usuario);
            List<Usuario> usu = usuarioFacade.findByNombre(usuario);
            if(!usu.isEmpty()){
         int mas= 1;
      
            List<RolMenu> lstRolesXMenus = rolMenuFacade.findByRol(usu.get(0).getRolidRol());
            System.out.println("roles--->"+lstRolesXMenus);
            for(RolMenu rm :lstRolesXMenus) {
                String existeMenu= "";
                if (rm.getIdmenu().getCorrelativo() == 0) {
                    firstSubmenu = new DefaultSubMenu(rm.getIdmenu().getNombre());
                    List<MenuElement> lm = vmodulos.getElements();
                    if(!lm.isEmpty()){
                        for(MenuElement m : lm){
                            if(m == firstSubmenu ){
                                
                               existeMenu= "si" ;
                            }
                        }
                    }
                    if(!existeMenu.equals("si")){
                        
                        vmodulos.addElement(firstSubmenu);
                    }
                    
                } else {
                    try{
                    
                    
                    item = new DefaultMenuItem(rm.getIdmenu().getNombre());
                    item.setId(FacesContext.getCurrentInstance().getViewRoot().createUniqueId());
                    item.setOutcome(rm.getIdmenu().getDestino());
                    firstSubmenu.addElement(item);
                    }catch(Exception ex){
                        
                    
                    }
                }
            }
        }
        }
      
      
        vmodulos.generateUniqueIds();
        return vmodulos;
    }
    
    public MenuModel getMenus() {
        menus = generarMenus();
        return menus;
    }

    public void setMenus(MenuModel menus) {
        this.menus = menus;
    }
    
} 
