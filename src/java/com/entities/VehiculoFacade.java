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
public class VehiculoFacade extends AbstractFacade<Vehiculo> {
    @PersistenceContext(unitName = "sysTaller3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculoFacade() {
        super(Vehiculo.class);
    }
    
    public List<Vehiculo> findByCliente(Cliente  cliente) {
        TypedQuery<Vehiculo> q = null;
     
             q = em.createNamedQuery("Vehiculo.findByCliente",Vehiculo.class)               
                .setParameter("cliente", cliente.getIdCliente());
              
        return q.getResultList();
    }     
    
}
