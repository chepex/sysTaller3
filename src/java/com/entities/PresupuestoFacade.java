/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Desarrollo
 */
@Stateless
public class PresupuestoFacade extends AbstractFacade<Presupuesto> {
    @PersistenceContext(unitName = "sysTaller3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PresupuestoFacade() {
        super(Presupuesto.class);
    }
    
    public Integer findById( ) {
          int vid;
        try{
          vid=   em.createQuery(" Select MAX(p.idpresupuesto )+1 id FROM Presupuesto p ", Integer.class).getSingleResult();
        }catch(Exception ex){
             vid =1;
        }
        
        return vid;
    }      
    
}
