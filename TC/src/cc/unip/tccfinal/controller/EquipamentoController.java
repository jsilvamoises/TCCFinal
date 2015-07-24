/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.controller;

import cc.unip.tccfinal.model.Equipamento;
import cc.unip.tccfinal.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Moisés
 */
public class EquipamentoController {

    public boolean save(Equipamento e) {
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

    public boolean saveAll(Equipamento... e) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        try {
            for (Equipamento eq : e) {
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

    public List<Object[]> listaParaTreinamento() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        Query query;
        List<Object[]> objetos = new ArrayList<>();
        try {
            query = session.createQuery("SELECT DISTINCT CONCAT(E.id.idEquipamento, E.id.valorSensorReferencia ,E.id.statusEquipamento) AS Chave, E.id.idEquipamento, E.id.valorSensorReferencia/100 ,E.id.statusEquipamento FROM Equipamento E");
            System.out.println(query.list().size());
            return query.list();
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }finally{
            session.getTransaction().commit();
        }
    }
    
    
    public List<Double> listKeyEquipamento(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        Query query;
        //List<Object[]> objetos = new ArrayList<>();
        try {
            query = session.createQuery("SELECT DISTINCT (E.id.idEquipamento) AS Chave FROM Equipamento E");
            //System.out.println(query.list().size());
            return query.list();
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }finally{
            session.getTransaction().commit();
        }
        
    }
    
    
    public static void main(String[] args) {
        new EquipamentoController().listaParaTreinamento();
    }
}
