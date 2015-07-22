/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.util;

import cc.unip.tccfinal.model.Sensor;
import cc.unip.tccfinal.model.StatusEquipamento;
import com.google.gson.Gson;

/**
 *
 * @author MOISES
 */
public class GSONConverter {

    public enum ObjectType {

        SENSOR,
        STATUS_EQUIPAMENTO
    }
    private Sensor sensor;
   

    private static GSONConverter instance;

    private GSONConverter() {
    }

    /**
     * Cria apenas uma instancia da classe
     *
     * @return
     */
    public static GSONConverter getInstance() {
        return instance == null ? instance = new GSONConverter() : instance;
    }

    public void transformToObject(String valor, ObjectType tipo) {
        Gson gsom = new Gson();

        try {

            switch (tipo) {
                case SENSOR:
                    System.out.println(">> Convertendo JSON para Ojeto Java [ SENSOR ]");
                    System.out.println(valor);
                    try {
                        sensor = gsom.fromJson(valor, Sensor.class);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    break;
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(sensor!=null){
            despachar(sensor);
        }
         
//        if (sensor != null ) {
//            sensor.setStatusEquipamentos(statusEquipamento);
//            despachar(sensor, statusEquipamento);
//        }

    }

    private void despachar(Sensor sensor) {
        
        CacheLeitura.getInstance().processar(sensor);
        
    }

}
