/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.controller;

import cc.unip.tccfinal.model.Equipamento;
import cc.unip.tccfinal.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Moisés
 */
public class EquipamentoController {
    public boolean save(Equipamento e){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(e);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            System.out.println(ex);
            return false;
        }
                
    }
    
     public boolean saveAll(Equipamento... e){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        try {
            for(Equipamento eq:e){
                session.saveOrUpdate(eq);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            System.out.println(ex);
            return false;
        }
                
    }
}
