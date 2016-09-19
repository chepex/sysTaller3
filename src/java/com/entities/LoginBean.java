/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

 
import java.io.IOException; 
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author mmixco
 */

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean {

    private static final long serialVersionUID = 5443351151396868724L;
    private String username;
    private String password;
    private String correo;
    @EJB
    private  UsuarioFacade  usuarioFacade;    
    @EJB
    private  SB_privilegios  sb_privilegios;     
    private String serverIP;
  
 
    

 

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
 
    
    
  
  public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   
     public void login(){       
        try {                            
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            
            
            if(request.getUserPrincipal() == null){
                request.getSession().setMaxInactiveInterval(900);
                System.out.println("usuario*---->"+username.toUpperCase());
                System.out.println("pass*---->"+password.toUpperCase());
                request.login(username.toUpperCase(), password.toUpperCase());
                System.out.println("request.getSession().getMaxInactiveInterval()--->"+request.getSession().getMaxInactiveInterval());
                System.out.println("Principal--->"+request.getUserPrincipal());
                System.out.println("Principal--->"+request.getSession().getLastAccessedTime());
                request.getSession().setAttribute("SSUSUARIO", username.toUpperCase());  
                 FacesContext.getCurrentInstance().getExternalContext().redirect("/sysTaller3/faces/home/inicio.xhtml");    
            
            }else{
               FacesContext.getCurrentInstance().getExternalContext().redirect("/sysTaller3/faces/home/inicio.xhtml");
            }
            
            /*request.login(username.toUpperCase(), password);
            request.login(username.toUpperCase(), pass);*/
            //request.login(username, password);
            //List<Usuario> lu = usuarioFacade.findByUserName(username.toUpperCase(), pass);
           
            /// FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
           // }
            
        } catch (Exception e) {
	           
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Su usuario o password no son correctos "+e.getMessage(), null));
         

        }
    }
     
     public void validatAcceso(String pagina){
         System.out.println("pagina-->"+pagina);    
         System.out.println("pagina-->"+pagina);    
         
     }
     
     
 
    public void logout() throws ServletException, IOException{
        String ip = this.getServerIP();
        System.gc();
        System.out.println(" salir ");
        System.out.println(" salir ");
        System.out.println(" salir -->"+ip);
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }      
        request.logout();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/sysTaller3/faces/login.xhtml");        
    }
    
    public String ssuser () {
      HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
       String vuser ="";
        try
          {		  
              if(session != null){
                  try{
                   vuser = (String) session.getAttribute("SSUSUARIO") ;	      
                  }catch(Exception ex){
                    vuser = "";   
                  }
              }
          }
       catch(NullPointerException e){ 
                vuser = "";  
          return vuser ;	
       }

      return vuser ;	


    }      
    
    public short sscia () {
      HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
       short cia =0;
        try
          {		  
              if(session != null){
                  try{
                   cia = Short.parseShort((String) session.getAttribute("SSCIAVAL")) ;	      
                  }catch(Exception ex){
                    cia = 1;   
                  }
              }
          }
       catch(NullPointerException e){ 
                cia = 1;  
          return cia ;	
       }

      return cia ;	


    }    
    
    
    public Integer getIdUsuario () {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
         Integer id =0;
          try
            {		  
                if(session != null){
                    try{
                     id = Integer.parseInt((String) session.getAttribute("SSIDUSUARIO")) ;	      
                    }catch(Exception ex){
                      id = 1;   
                    }
                }
            }
         catch(NullPointerException e){ 
                  id = 1;  
            return id ;	
         }

        return id ;	


      }    
    
    
         
	
	
	  
    public String getServerIP() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        String port = String.valueOf(request.getLocalPort());
        serverIP = request.getLocalAddr()+":"+port ;
        System.out.println("direccion--->"+serverIP);
        if(serverIP.equals("127.0.0.1:8080")){    
            serverIP= "localhost:8080";
        }else if(serverIP == null){
            serverIP= "192.168.10.223:8080";
        }
        
        return serverIP;
    }	 
    
  
    public boolean  acceso( ){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String viewId = facesContext.getViewRoot().getViewId();
        viewId = viewId.replace(".xhtml", " ");
      
        
        String msg = sb_privilegios.validar(viewId);
        if(msg.equals("ok")){
        return true;
        }
        return false;
    }
    
	
            
}
