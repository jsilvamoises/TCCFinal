/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import cc.unip.tccfinal.fxml.model.Equipamento;
import cc.unip.tccfinal.fxml.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

/**
 *
 * @author Moisés
 */
public class Teste {
    public static void main(String[] args) {
        Teste t = new Teste();
        Long valorComQuery = t.maiorExemplo_01();
        Long valorComCriteria = t.maiorExemplo_02();
        System.out.println("IMPRIMINDO MAIOR ID COM QUERY");
        System.out.println("O MAIOR ID É:: "+valorComQuery);
        
        System.out.println("\nIMPRIMINDO MAIOR ID COM CRITERIA");
        System.out.println("O MAIOR ID É:: "+valorComCriteria);
    }
    
    public Long maiorExemplo_01(){
        Long result = null ;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        try {
            Query q = session.createQuery("SELECT MAX(E.id) FROM Equipamento E");
            result = (Long) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            
        }finally{
            session.getTransaction().commit();
        }
        return result;
        
    }
    
    public Long maiorExemplo_02(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        try {
            Criteria criteria = session.createCriteria(Equipamento.class);
            criteria.setProjection(Projections.max("id"));
            return (Long) criteria.uniqueResult();
        } catch (Exception e) {
            return null;
        }finally{
            session.getTransaction().commit();
        }
        
    }
    
//    public int maiorExemplo_03(){
//        
//    }
}
