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
    private double valorSensorReferencia;

//    private int anoAmostra;
//    private int diaAno;
//    private byte mesAmostra;
//    private byte diaAmostra;
//    private byte diaSemana;
//    private byte horaAmostra1;
//    private byte minutoAmostra1;
    
    private String nomeEquipamento;

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public Equipamento setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
        return this;
    }
/*
    public int getAnoAmostra() {
        return anoAmostra;
    }

    public Equipamento setAnoAmostra(int anoAmostra) {
        this.anoAmostra = anoAmostra;
        return this;
    }

    public int getDiaAno() {
        return diaAno;
    }

    public Equipamento setDiaAno(int diaAno) {
        this.diaAno = diaAno;
        return this;
    }

    public byte getMesAmostra() {
        return mesAmostra;
    }

    public Equipamento setMesAmostra(byte mesAmostra) {
        this.mesAmostra = mesAmostra;
        return this;
    }

    public byte getDiaAmostra() {
        return diaAmostra;
    }

    public Equipamento setDiaAmostra(byte diaAmostra) {
        this.diaAmostra = diaAmostra;
        return this;
    }

    public byte getDiaSemana() {
        return diaSemana;
    }

    public Equipamento setDiaSemana(byte diaSemana) {
        this.diaSemana = diaSemana;
        return this;
    }

    public byte getHoraAmostra1() {
        return horaAmostra1;
    }

    public Equipamento setHoraAmostra1(byte horaAmostra1) {
        this.horaAmostra1 = horaAmostra1;
        return this;
    }

    public byte getMinutoAmostra1() {
        return minutoAmostra1;
    }

    public Equipamento setMinutoAmostra1(byte minutoAmostra1) {
        this.minutoAmostra1 = minutoAmostra1;
        return this;
    }
*/
    public Equipamento() {
        // id = new EquipamentoId();
    }

//    public EquipamentoId getId() {
//        return id;
//    }
//
//    public Equipamento setId(EquipamentoId id) {
//        this.id = id;
//        return this;
//    }
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

}
