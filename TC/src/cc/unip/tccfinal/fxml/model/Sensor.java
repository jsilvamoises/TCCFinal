/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;

/**
 *
 * @author Moisés
 */
@Embeddable
public class Sensor implements Serializable {
    private Date horaAmostra;
    private double temperatura;
    private double umidade;
    private double luminosidade;
    //private StatusEquipamento statusEquipamentos;
    private byte statusIluminacao;
    private byte statusAquecedor;
    private byte statusArcondicionado;
    private byte statusUmidificador;
    private byte statusGeral;
    
  
    

    public Date getHoraAmostra() {
        return horaAmostra;
    }

    public Sensor setHoraAmostra(Date horaAmostra) {
        this.horaAmostra = horaAmostra;
        return this;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public Sensor setTemperatura(double temperatura) {
        this.temperatura = temperatura;
        return this;
    }

    public double getUmidade() {
        return umidade;
    }

    public Sensor setUmidade(double umidade) {
        this.umidade = umidade;
        return this;
    }

    public double getLuminosidade() {
        return luminosidade;
    }

    public Sensor setLuminosidade(double luminosidade) {
        this.luminosidade = luminosidade;
        return this;
    }

//    public StatusEquipamento getStatusEquipamentos() {
//        return statusEquipamentos;
//    }
//
//    public Sensor setStatusEquipamentos(StatusEquipamento statusEquipamentos) {
//        this.statusEquipamentos = statusEquipamentos;
//        return this;
//    }

    public byte getStatusIluminacao() {
        return statusIluminacao;
    }

    public Sensor setStatusIluminacao(byte statusIluminacao) {
        this.statusIluminacao = statusIluminacao;
        return this;
    }

    public byte getStatusAquecedor() {
        return statusAquecedor;
    }

    public Sensor setStatusAquecedor(byte statusAquecedor) {
        this.statusAquecedor = statusAquecedor;
        return this;
    }

    public byte getStatusArcondicionado() {
        return statusArcondicionado;
    }

    public Sensor setStatusArcondicionado(byte statusArcondicionado) {
        this.statusArcondicionado = statusArcondicionado;
        return this;
    }

    public byte getStatusUmidificador() {
        return statusUmidificador;
    }

    public Sensor setStatusUmidificador(byte statusUmidificador) {
        this.statusUmidificador = statusUmidificador;
        return this;
    }

    public byte getStatusGeral() {
        return statusGeral;
    }

    public Sensor setStatusGeral(byte statusGeral) {
        this.statusGeral = statusGeral;
        return this;
    }

    @Override
    public String toString() {
        return "Sensor{" + "horaAmostra=" + horaAmostra + ", temperatura=" + temperatura + ", umidade=" + umidade + ", luminosidade=" + luminosidade + ", statusIluminacao=" + statusIluminacao + ", statusAquecedor=" + statusAquecedor + ", statusArcondicionado=" + statusArcondicionado + ", statusUmidificador=" + statusUmidificador + ", statusGeral=" + statusGeral + '}';
    }
    
    
     
    
    
}
