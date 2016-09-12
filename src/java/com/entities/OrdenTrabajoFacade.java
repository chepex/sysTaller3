/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
 

/**
 *
 * @author Desarrollo
 */
@Stateless
public class OrdenTrabajoFacade extends AbstractFacade<OrdenTrabajo> {
    @PersistenceContext(unitName = "sysTaller3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenTrabajoFacade() {
        super(OrdenTrabajo.class);
    }
    
  

    public Integer findById( ) {
       int vid=0;
        try{
        vid=  em.createQuery(" Select MAX(o.idOrdenTrabajo)+1 id FROM OrdenTrabajo o ", Integer.class).getSingleResult();
        }catch(Exception ex){
        vid= 1;
        }
              
        return vid;
    }         
    
}
