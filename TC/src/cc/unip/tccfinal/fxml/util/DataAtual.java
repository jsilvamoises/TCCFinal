/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.util;

import java.util.Calendar;

/**
 *
 * @author Moises
 */
public class DataAtual {
    
    public static int getDia(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    
    public static int getMes(){
        return Calendar.getInstance().get(Calendar.MONTH);
    }
    
    public static int getAno(){
        return Calendar.getInstance().get(Calendar.YEAR)-2000;
    }
    
    public static int getHora(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
    
    public static int getMinuto(){
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
    
    
    
    public static void main(String[] args) {
        System.out.println(DataAtual.getAno());
        System.out.println(DataAtual.getMes());
        System.out.println(DataAtual.getDia());
        System.out.println(DataAtual.getHora());
        System.out.println(DataAtual.getMinuto());
        //System.out.println(DataAtual.getAno());
    }
    
}
