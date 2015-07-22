/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.util;

import java.io.InputStream;

/**
 *
 * @author Moisés
 */
public class Icon32 {

    private static Icon32 instance;

    public static Icon32 getInstance() {
        return instance == null ? instance = new Icon32() : instance;
    }
    private static final String CAMINHO = "/resources/images/icon/";

    public InputStream ICON_MONITORING() {
        return getClass().getResourceAsStream(CAMINHO.concat("monitoring-32.png"));
    }

    public InputStream ICON_ADD_DATABASE() {
        return getClass().getResourceAsStream(CAMINHO.concat("Add Database-32.png"));
    }

    public InputStream ICON_STOP() {
        return getClass().getResourceAsStream(CAMINHO.concat("stop32.png"));
    }
    
    public InputStream ICON_ROBOT() {
        return getClass().getResourceAsStream(CAMINHO.concat("Robot-32.png"));
    }
    
    public InputStream ICON_AR_COND_WHITE() {
        return getClass().getResourceAsStream(CAMINHO.concat("Air Conditioner-32-white.png"));
    }
    
    public InputStream ICON_AQUECEDOR_WHITE() {
        return getClass().getResourceAsStream(CAMINHO.concat("Heating Automation-32 -white.png"));
    }
    
    public InputStream ICON_ILUMINACAO_WHITE() {
        return getClass().getResourceAsStream(CAMINHO.concat("Idea Filled-32.png"));
    }
    public InputStream ICON_UMIDIFICADOR_WHITE() {
        return getClass().getResourceAsStream(CAMINHO.concat("Humidity-32.png"));
    }
}
