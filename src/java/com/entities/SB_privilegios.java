/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Desarrollo
 */
@Stateless
public class SB_privilegios {
    
    @EJB
    private com.entities.UsuarioFacade usuarioFacade;      
    @EJB
    private com.entities.RolMenuFacade rolMenuFacade;          
    
    
    public String  validar(String pagina)  {
        String msg  ="error";
        
         
        String usuario =getUsuario();
        if(usuario!=null){
            List<Usuario> usu = usuarioFacade.findByNombre(usuario);
            if(!usu.isEmpty()){
                List<RolMenu> lrm = rolMenuFacade.findByRolUrl(usu.get(0).getRolidRol(), pagina);
                if(!lrm.isEmpty()){
                    msg = "ok";
                }
            }
        }
      
        return msg;
    }
    
    public String getUsuario(){
        String vuser ="";
        try{
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
             vuser= (String) session.getAttribute("SSUSUARIO");	  
        }catch(Exception ex){
            vuser="";
        }
      return vuser;
        
        
    }
}
