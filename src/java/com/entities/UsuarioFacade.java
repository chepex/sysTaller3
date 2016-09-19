/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Desarrollo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "sysTaller3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public List<Usuario> findByNombre(String nombre ) {
        TypedQuery<Usuario> q = null;
      List<Usuario> lusuario = null;
             q = em.createNamedQuery("Usuario.findByNombre",Usuario.class)               
                .setParameter("nombre",nombre);               
              try{
             lusuario =  q.getResultList();
              }catch(Exception ex){
                  System.out.println("error->>>");
                  System.out.println("error->>>");
                  System.out.println("error->>>");
                  System.out.println("error->>>");
                  System.out.println("error->>>"+ex.getMessage());
              return lusuario;
              }
        return lusuario;
    }      
    
}
