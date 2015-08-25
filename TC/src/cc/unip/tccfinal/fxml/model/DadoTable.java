///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cc.unip.tccfinal.model;
//
//import javafx.beans.property.FloatProperty;
//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleFloatProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//
///**
// *
// * @author MOISES
// */
//public class DadoTable {
//
//    private StringProperty identificador;
//    private FloatProperty temperatura;
//    private FloatProperty umidade;
//    private FloatProperty luminosidade;
//   
//    private IntegerProperty statusIluminacao;
//    private IntegerProperty statusAlarmeIncendio;
//    private IntegerProperty statusAquecedor;
//    private IntegerProperty statusArCondidiconado;
//    private IntegerProperty statusGeral;
//
//    public DadoTable() {
//    }
//    
//    
//    
//    public DadoTable(String identificador, Float temperatura, Float umidade, float luminosidade, byte magnetismo, byte statusIluminacao, byte statusAlarmeIncendio, byte statusAquecedor, byte statusArCondidiconado, byte statusGeral) {
//        //Dados coletados dos sensores
//        this.identificador = new SimpleStringProperty(identificador);
//        this.temperatura = new SimpleFloatProperty(temperatura);
//        this.umidade = new SimpleFloatProperty(umidade);
//        this.luminosidade = new SimpleFloatProperty(luminosidade);        
//        //Dados enviados aos sensores
//        this.statusIluminacao = new SimpleIntegerProperty(statusIluminacao);
//        this.statusAlarmeIncendio = new SimpleIntegerProperty(statusAlarmeIncendio);
//        this.statusAquecedor = new SimpleIntegerProperty(statusAquecedor);
//        this.statusArCondidiconado = new SimpleIntegerProperty(statusArCondidiconado);
//        this.statusGeral = new SimpleIntegerProperty(statusGeral);
//        
//        //
//        
//        
//    }
//
//    public StringProperty identificadorProperty() {
//        return identificador;
//    }
//
//    public FloatProperty temperaturaProperty() {
//        return temperatura;
//    }
//
//    public FloatProperty umidadeProperty() {
//        return umidade;
//    }
//
//    public FloatProperty luminosidadeProperty() {
//        return luminosidade;
//    }
//
//   
//
//    public IntegerProperty statusIluminacaoProperty() {
//        return statusIluminacao;
//    }
//
//    public IntegerProperty statusAlarmeIncendioProperty() {
//        return statusAlarmeIncendio;
//    }
//
//    public IntegerProperty statusAquecedorProperty() {
//        return statusAquecedor;
//    }
//
//    public IntegerProperty statusArCondidiconadoProperty() {
//        return statusArCondidiconado;
//    }
//
//    public IntegerProperty statusGeralProperty() {
//        return statusGeral;
//    }
//    //METODOS SET
//    /*
//    
//        
//        
//                
//        //Dados enviados aos sensores
//        
//        
//        
//        
//        
//    
//    
//    */
//    public DadoTable setIdentificador(String identificador) {
//        this.identificador = new SimpleStringProperty(identificador);
//        return this;
//    }
//
//    public DadoTable setTemperatura(Float temperatura) {
//        this.temperatura = new SimpleFloatProperty(temperatura);
//        return this;
//    }
//
//    public DadoTable setUmidade(Float umidade) {
//        this.umidade = new SimpleFloatProperty(umidade);
//        return this;
//    }
//
//    public DadoTable setLuminosidade(Float luminosidade) {
//        this.luminosidade = new SimpleFloatProperty(luminosidade);
//        return this;
//    }
//
//    public DadoTable setStatusIluminacao(byte statusIluminacao) {
//        this.statusIluminacao = new SimpleIntegerProperty(statusIluminacao);
//        return this;
//    }
//
//    public DadoTable setStatusAlarmeIncendio(byte statusAlarmeIncendio) {
//        this.statusAlarmeIncendio = new SimpleIntegerProperty(statusAlarmeIncendio);
//        return this;
//    }
//
//    public DadoTable setStatusAquecedor(byte statusAquecedor) {
//        this.statusAquecedor = new SimpleIntegerProperty(statusAquecedor);
//        return this;
//    }
//
//    public DadoTable setStatusArCondidiconado(byte statusArCondidiconado) {
//        this.statusArCondidiconado = new SimpleIntegerProperty(statusArCondidiconado);
//        return this;
//    }
//
//    public DadoTable setStatusGeral(byte statusGeral) {
//        this.statusGeral = new SimpleIntegerProperty(statusGeral);
//        return this;
//    }
//    
//    
//    
//
//}
