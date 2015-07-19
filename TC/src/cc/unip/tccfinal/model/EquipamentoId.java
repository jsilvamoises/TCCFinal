/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Moisés
 */
@Embeddable
public class EquipamentoId  implements Serializable{
    private double idEquipamento;
    private byte statusEquipamento;
    private double valorSensorReferencia;

    public double getIdEquipamento() {
        return idEquipamento;
    }

    public EquipamentoId setIdEquipamento(double idEquipamento) {
        this.idEquipamento = idEquipamento;
         return this;
    }

    public byte getStatusEquipamento() {
        return statusEquipamento;
    }

    public EquipamentoId setStatusEquipamento(byte statusEquipamento) {
        this.statusEquipamento = statusEquipamento;
         return this;
    }

    public double getValorSensorReferencia() {
        return valorSensorReferencia;
    }

    public EquipamentoId setValorSensorReferencia(double valorSensorReferencia) {
        this.valorSensorReferencia = valorSensorReferencia;
        return this;
    }
    
    
}
