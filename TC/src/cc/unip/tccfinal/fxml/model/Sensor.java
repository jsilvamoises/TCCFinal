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
    private double idEquipamento;
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int min;    
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

    public double getIdEquipamento() {
        return idEquipamento;
    }

    public Sensor setIdEquipamento(double idEquipamento) {
        this.idEquipamento = idEquipamento;
         return this;
    }

    public int getDia() {
        return dia;
    }

    public Sensor setDia(int dia) {
        this.dia = dia;
         return this;
    }

    public int getMes() {
        return mes;
    }

    public Sensor setMes(int mes) {
        this.mes = mes;
         return this;
    }

    public int getAno() {
        return ano;
    }

    public Sensor setAno(int ano) {
        this.ano = ano;
         return this;
    }

    public int getHora() {
        return hora;
    }

    public Sensor setHora(int hora) {
        this.hora = hora;
         return this;
    }

    public int getMin() {
        return min;
    }

    public Sensor setMin(int min) {
        this.min = min;
        return this;
    }

    
    
    
     
    
    
}
