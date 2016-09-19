/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mmixco
 */
@Stateless
 
public class CompaniaFacade extends AbstractFacade<Compania> {
    @PersistenceContext(unitName = "sysTaller3PU")
    private EntityManager em;
   
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompaniaFacade() {
        super(Compania.class);
    }
    
    public String  install(){
      
        String msg ="ok";
      try{
        Query q1 = em.createNativeQuery(  " delete from presupuesto; ");
        Query q2 = em.createNativeQuery(  " delete from orden_trabajo; ");
        Query q3 = em.createNativeQuery(  " delete from cliente; ");
        Query q4 = em.createNativeQuery(  " delete from presupuesto; ");
        Query q5 = em.createNativeQuery(  " delete from vehiculo; ");
        Query q6 = em.createNativeQuery(  " delete from marca; ");
        Query q7 = em.createNativeQuery(  " delete from modelo; ");
        Query q8 = em.createNativeQuery(  " delete from compania; ");
        Query q9 = em.createNativeQuery(  " delete from proveedor; ");
        Query q10 = em.createNativeQuery(  " delete from repuesto; ");
        Query q11 = em.createNativeQuery(  " delete from categoria; ");
        Query q12 = em.createNativeQuery(  " delete from tipo_pago; ");
        Query q13 = em.createNativeQuery(  " delete from tipo_vehiculo; ");
        Query q14 = em.createNativeQuery(  " delete from usuario; ");
        Query q15 = em.createNativeQuery(  " delete from rol_menu; ");
        Query q16 = em.createNativeQuery(  " delete from menu; ");
        Query q17 = em.createNativeQuery(  " delete from rol; ");
        
        
        
 
      
        q1.executeUpdate();
        q2.executeUpdate();
        q3.executeUpdate();
        q4.executeUpdate();
        q5.executeUpdate();
        q6.executeUpdate();
        q7.executeUpdate();
        q8.executeUpdate();
        q9.executeUpdate();
        q10.executeUpdate();
        q11.executeUpdate();
        q12.executeUpdate();
        q13.executeUpdate();
        q14.executeUpdate();
        q15.executeUpdate();
        q16.executeUpdate();
        q17.executeUpdate();
      
      }catch(Exception ex){
         msg ="eroor:"+ex.getMessage();
      }
      
      return msg;
    }
    
}
