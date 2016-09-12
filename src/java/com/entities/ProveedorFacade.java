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
public class ProveedorFacade extends AbstractFacade<Proveedor> {
    @PersistenceContext(unitName = "sysTaller3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorFacade() {
        super(Proveedor.class);
    }
    
    public List<Proveedor> findByNombreCodigo(String  nombre) {
        TypedQuery<Proveedor> q = null;
     
             q = em.createNamedQuery("Proveedor.findByNombre",Proveedor.class)               
                .setParameter("nombre", "%"+nombre+"%");
              
        return q.getResultList();
    }      
    
}
