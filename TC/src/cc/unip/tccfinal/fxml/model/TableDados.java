/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Moisés
 */
public class TableDados {
    private DoubleProperty temperatura;
    private DoubleProperty umidade;
    private DoubleProperty luminosidade;
    
    private IntegerProperty statusAquecedor;
    private IntegerProperty statusArcondicionado;
    private IntegerProperty statusUmidificador;
    private IntegerProperty statusIluminacao;

    public TableDados() {
    }
    
    public void terminate(){
        
    }

    public DoubleProperty temperaturaProperty() {
        return temperatura;
    }
    public TableDados setTemperatura(Double temperatura) {
        this.temperatura = new SimpleDoubleProperty(temperatura);
        return this;
    }
    // -------------------------------------------------------------------------
    public DoubleProperty umidadeProperty() {
        return umidade;
    }

    public TableDados setUmidade(Double umidade) {
        this.umidade = new SimpleDoubleProperty(umidade);
        return this;
    }
    // -------------------------------------------------------------------------
    public DoubleProperty luminosidadeProperty() {
        return luminosidade;
    }

    public TableDados setLuminosidade(Double luminosidade) {
        this.luminosidade = new SimpleDoubleProperty(luminosidade);
        return this;
    }
    // -------------------------------------------------------------------------
    public IntegerProperty statusAquecedorProperty() {
        return statusAquecedor;
    }

    public TableDados setStatusAquecedor(byte statusAquecedor) {
        this.statusAquecedor = new SimpleIntegerProperty(statusAquecedor);
        return this;
    }
    // -------------------------------------------------------------------------
    public IntegerProperty statusArcondicionadoProperty() {
        return statusArcondicionado;
    }

    public TableDados setStatusArcondicionado(byte statusArcondicionado) {
        this.statusArcondicionado = new SimpleIntegerProperty(statusArcondicionado);
        return this;
    }
    // -------------------------------------------------------------------------
    public IntegerProperty statusUmidificadorProperty() {
        return statusUmidificador;
    }

    public TableDados setStatusUmidificador(byte statusUmidificador) {
        this.statusUmidificador = new SimpleIntegerProperty(statusUmidificador);
        return this;
    }
    // -------------------------------------------------------------------------
    public IntegerProperty statusIluminacaoProperty() {
        return statusIluminacao;
    }

    public TableDados setStatusIluminacao(byte statusIluminacao) {
        this.statusIluminacao = new SimpleIntegerProperty(statusIluminacao);
        return this;
    }
    
    
    
    
    
}
