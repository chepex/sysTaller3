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
public class ClienteFacade extends AbstractFacade<Cliente> {
    @PersistenceContext(unitName = "sysTaller3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    public List<Cliente> findByNombreCodigo(String  nombre) {
        TypedQuery<Cliente> q = null;
     
             q = em.createNamedQuery("Cliente.findByNombre",Cliente.class)               
                .setParameter("nombre",  nombre );
              
        return q.getResultList();
    }  
    
    public List<Cliente> findByNombreCedulaNit(String  nombre ) {
        TypedQuery<Cliente> q = null;
     
             q = em.createNamedQuery("Cliente.findByNombreNit",Cliente.class)               
                .setParameter("nombre", "%"+nombre+"%")               
                .setParameter("nit", "%"+nombre+"%");
              
        return q.getResultList();
    }       
    
    public Integer findById( ) {
       int vid=0;
        try{
        vid=  em.createQuery(" Select MAX(c.idCliente)+1 id FROM Cliente c ", Integer.class).getSingleResult();
        }catch(Exception ex){
        vid= 1;
        }
              
        return vid;
    }       
    
    public List<Cliente> findByNombre (String  nombre ) {
        TypedQuery<Cliente> q = null;
     
             q = em.createNamedQuery("Cliente.findByNombre2",Cliente.class)               
                .setParameter("nombre", nombre);               
                
              
        return q.getResultList();
    }      
    
    public List<Cliente> findByNit (String  nit ) {
        TypedQuery<Cliente> q = null;
     
             q = em.createNamedQuery("Cliente.findByNit",Cliente.class)               
                .setParameter("nit", nit);               
                
              
        return q.getResultList();
    }   
    
    public List<Cliente> findByTelefonoFijo (String  telefonoFijo ) {
        TypedQuery<Cliente> q = null;
     
             q = em.createNamedQuery("Cliente.findByTelefonoFijo",Cliente.class)               
                .setParameter("telefonoFijo", telefonoFijo);               
                
              
        return q.getResultList();
    }  
    
    public List<Cliente> findByRegistroFiscal (String  registroFiscal ) {
        TypedQuery<Cliente> q = null;
     
             q = em.createNamedQuery("Cliente.findByRegistroFiscal",Cliente.class)               
                .setParameter("registroFiscal", registroFiscal);               
                
              
        return q.getResultList();
    }     
        
    
}
