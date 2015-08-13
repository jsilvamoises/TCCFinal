/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml;

import cc.unip.tccfinal.fxml.model.IdEquipamento;

/**
 *
 * @author Moisés
 */
public enum EnumEquipamentos {
    AQUECEDOR(IdEquipamento.ID_AQUECEDOR),
    ILUMINACAO(IdEquipamento.ID_ILUMINACAO),
    AR_CONDICIONADO(IdEquipamento.ID_AR_CONDICIONADO),
    UMIDIFICADOR(IdEquipamento.ID_UMIDIFICADOR);
    
    private double valor;

    private EnumEquipamentos(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
    
    
    
}
