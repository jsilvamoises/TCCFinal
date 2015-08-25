/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.model;

import java.io.Serializable;

/**
 *
 * @author Moisés
 */
public class StatusEquipamento implements Serializable {
    private byte statusIluminacao;
    private byte statusAquecedor;
    private byte statusArcondicionado;
    private byte statusUmidificador;

    public StatusEquipamento() {
    }

    public byte getStatusIluminacao() {
        return statusIluminacao;
    }

    public StatusEquipamento setStatusIluminacao(byte statusIluminacao) {
        this.statusIluminacao = statusIluminacao;
        return this;
    }

    public byte getStatusAquecedor() {
        return statusAquecedor;
    }

    public StatusEquipamento setStatusAquecedor(byte statusAquecedor) {
        this.statusAquecedor = statusAquecedor;
        return this;
    }

    public byte getStatusArcondicionado() {
        return statusArcondicionado;
    }

    public StatusEquipamento setStatusArcondicionado(byte statusArcondicionado) {
        this.statusArcondicionado = statusArcondicionado;
        return this;
    }

    public byte getStatusUmidificador() {
        return statusUmidificador;
    }

    public StatusEquipamento setStatusUmidificador(byte statusUmidificador) {
        this.statusUmidificador = statusUmidificador;
        return this;
    }
    
    
    
}
