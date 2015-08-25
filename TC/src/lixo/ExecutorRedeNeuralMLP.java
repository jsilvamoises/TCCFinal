package lixo;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cc.unip.tccfinal.rede;
//
//import cc.unip.tccfinal.rede.treinamento.ObterDadosTreino;
//
///**
// *
// * @author MOISES
// */
//public class TreinoRede {
//
//    private double[][] CONJUNTO_TREINAMENTO;
//    private double[] VALORES_ESPERADOS = {0.0, 0.0, 1.0, 1.0};
//    private int porcentTreino;
//    private int nrNeuroniosPrimeiraCamada = 3;
//    private int nrNeuroniosEntrada = 3;
//    private RedeNeuralMLP rede;
//    private static TreinoRede instance;
//
//    public static TreinoRede getInstance() {
//        return instance == null ? instance = new TreinoRede() : instance;
//    }
//    ObterDadosTreino tr;
//
//    private TreinoRede() {
//    }
//    /*#########################################################################
//    ##########  RECUPERA DODOS DO BANCO E RETORNA CONJUNTO TREINO #############
//    #########################################################################*/
//    public TreinoRede prepararDados(int porcentagem) {
//        tr = new ObterDadosTreino().setPorcentagemParaTreinamento(50).build();
//        CONJUNTO_TREINAMENTO = tr.getMATRIZ_DADOS();
//        VALORES_ESPERADOS = tr.getRESULTADO_ESPERADO();
//        return this;
//    }
//
//    public TreinoRede build() {
//        rede = new RedeNeuralMLP(nrNeuroniosPrimeiraCamada, nrNeuroniosEntrada);
//        return this;
//    }
//
//    public void treinar() {
//        rede.treinar(CONJUNTO_TREINAMENTO, VALORES_ESPERADOS);
//    }
//
//    public int classificar(double[] amostra) {
//        return rede.classificar(amostra);
//    }
//
//    public TreinoRede setPorcentagemTreinamento(int porcentagemTreinamento) {
//        this.porcentTreino = porcentagemTreinamento;
//        return this;
//    }
//
//    public TreinoRede setNrNeuroniosPrimeiraCamada(int nrNeuroniosPrimeiraCamada) {
//        this.nrNeuroniosPrimeiraCamada = nrNeuroniosPrimeiraCamada;
//        return this;
//    }
//
//    public TreinoRede setNrNeuroniosEntrada(int nrNeuroniosEntrada) {
//        this.nrNeuroniosEntrada = nrNeuroniosEntrada;
//        return this;
//    }
//
//}
