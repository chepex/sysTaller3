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
public class RepuestoFacade extends AbstractFacade<Repuesto> {
    @PersistenceContext(unitName = "sysTaller3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RepuestoFacade() {
        super(Repuesto.class);
    }
    
    public List<Repuesto> findByNombreCodigo(String  nombre) {
        TypedQuery<Repuesto> q = null;
     
             q = em.createNamedQuery("Repuesto.findByNombre",Repuesto.class)               
                .setParameter("nombre", "%"+nombre+"%");
              
        return q.getResultList();
    }  
    
    public List<Repuesto> findByNombreCodigoRepuesto(String  nombre) {
        TypedQuery<Repuesto> q = null;
     
             q = em.createNamedQuery("Repuesto.findByNombreCodigoRepuesto",Repuesto.class)               
                .setParameter("nombre", "%"+nombre+"%");
              
        return q.getResultList();
    }      
    
    public List<Repuesto> findByNombreCodigoFuera(String  nombre) {
        TypedQuery<Repuesto> q = null;
     
             q = em.createNamedQuery("Repuesto.findByNombreCodigoFuera",Repuesto.class)               
                .setParameter("nombre", "%"+nombre+"%");
              
        return q.getResultList();
    }      
    
    
    
}
