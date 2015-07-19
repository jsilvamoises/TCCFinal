/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.model;

import java.util.Date;

/**
 *
 * @author Moisés
 */
public class Sensor {
    private Date horaAmostra;
    private double temperatura;
    private double umidade;
    private double luminosidade;

    public Date getHoraAmostra() {
        return horaAmostra;
    }

    public void setHoraAmostra(Date horaAmostra) {
        this.horaAmostra = horaAmostra;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getUmidade() {
        return umidade;
    }

    public void setUmidade(double umidade) {
        this.umidade = umidade;
    }

    public double getLuminosidade() {
        return luminosidade;
    }

    public void setLuminosidade(double luminosidade) {
        this.luminosidade = luminosidade;
    }
    
    
}
