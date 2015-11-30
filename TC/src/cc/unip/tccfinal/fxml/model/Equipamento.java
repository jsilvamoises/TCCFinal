/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Moisés
 */
@Entity
@Table(name = "tbl_equipamento")
public class Equipamento implements Serializable {

    //@EmbeddedId
    //private EquipamentoId id;
    
    

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAmostra;
    private double idEquipamento;
    private byte statusEquipamento;
    //@Column(precision = 3)
    private double valorSensorReferencia;
    
    private int ano;
    private int mes;
    private int dia;
    
    private int hora;
    private int minuto;


    
    private String nomeEquipamento;

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public Equipamento setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
        return this;
    }

    public Equipamento() {
        // id = new EquipamentoId();
    }


    public Date getDataAmostra() {
        return dataAmostra;
    }

    public Equipamento setDataAmostra(Date dataAmostra) {
        this.dataAmostra = dataAmostra;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipamento other = (Equipamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public double getIdEquipamento() {
        return idEquipamento;
    }

    public Equipamento setIdEquipamento(double idEquipamento) {
        this.idEquipamento = idEquipamento;
        return this;
    }

    public byte getStatusEquipamento() {
        return statusEquipamento;
    }

    public Equipamento setStatusEquipamento(byte statusEquipamento) {
        this.statusEquipamento = statusEquipamento;
        return this;
    }

    public double getValorSensorReferencia() {
        return valorSensorReferencia;
    }

    public Equipamento setValorSensorReferencia(double valorSensorReferencia) {
        this.valorSensorReferencia = valorSensorReferencia;
        return this;
    }

    public Long getId() {
        return id;
    }

    public int getAno() {
        return ano;
    }

    public Equipamento setAno(int ano) {
        this.ano = ano;
        return this;
    }

    public int getMes() {
        return mes;
    }

    public Equipamento setMes(int mes) {
        this.mes = mes;
        return this;
    }

    public int getDia() {
        return dia;
    }

    public Equipamento setDia(int dia) {
        this.dia = dia;
        return this;
    }

    public int getHora() {
        return hora;
    }

    public Equipamento setHora(int hora) {
        this.hora = hora;
        return this;
    }

    public int getMinuto() {
        return minuto;
    }

    public Equipamento setMinuto(int minuto) {
        this.minuto = minuto;
        return this;
    }

    @Override
    public String toString() {
        return "Equipamento{" + "id=" + id + ", dataAmostra=" + dataAmostra + ", idEquipamento=" + idEquipamento + ", statusEquipamento=" + statusEquipamento + ", valorSensorReferencia=" + valorSensorReferencia + ", ano=" + ano + ", mes=" + mes + ", dia=" + dia + ", hora=" + hora + ", minuto=" + minuto + ", nomeEquipamento=" + nomeEquipamento + '}';
    }
    
    
    
    

}
