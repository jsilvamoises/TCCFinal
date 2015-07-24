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
//public class InterfaceTreinoRede {
//
//    private double[][] CONJUNTO_TREINAMENTO;
//    private double[] VALORES_ESPERADOS;
//    private int porcentTreino=10;
//    private int nrNeuroniosPrimeiraCamada = 3;
//    private int nrNeuroniosEntrada = 3;
//    private RedeNeuralMLP rede;
//    private static InterfaceTreinoRede instance;
//
//    public static InterfaceTreinoRede getInstance() {
//        return instance == null ? instance = new InterfaceTreinoRede() : instance;
//    }
//    ObterDadosTreino tr;
//
//    private InterfaceTreinoRede() {
//    }
//    /*#########################################################################
//    ##########  RECUPERA DODOS DO BANCO E RETORNA CONJUNTO TREINO #############
//    #########################################################################*/
//    public InterfaceTreinoRede prepararDados(int porcentagem) {
//        this.porcentTreino = porcentagem;
//        tr = new ObterDadosTreino().setPorcentagemParaTreinamento(porcentTreino).build();
//        CONJUNTO_TREINAMENTO = tr.getMATRIZ_DADOS();
//        VALORES_ESPERADOS = tr.getRESULTADO_ESPERADO();
//        return this;
//    }
//    // -------------------------------------------------------------------------
//    public InterfaceTreinoRede build() {
//        rede = new RedeNeuralMLP(nrNeuroniosPrimeiraCamada, nrNeuroniosEntrada);
//        return this;
//    }
//    // --------------- TREINA A REDE COM OS DADOS OBTIDO DO BANCO --------------
//    public void treinar() {
//        rede.treinar(CONJUNTO_TREINAMENTO, VALORES_ESPERADOS);
//    }
//    // ----------- CLASSIFICA UMA AMOSTRA RECEBIDA E RETORNA 0 OU 1 ------------
//    public int classificar(double[] amostra) {
//        return rede.classificar(amostra);
//    }
//    // --------- ATERA A PORCENTAGEM DE DADOS OBTIDOS A SEREM TREINADOS --------
//    public InterfaceTreinoRede setPorcentagemTreinamento(int porcentagemTreinamento) {
//        this.porcentTreino = porcentagemTreinamento;
//        return this;
//    }
//    // -------- ALTERA A QUANTIDADE DE NEURONIO PRIMEIRA CAMADA ----------------
//    public InterfaceTreinoRede setNrNeuroniosPrimeiraCamada(int nrNeuroniosPrimeiraCamada) {
//        this.nrNeuroniosPrimeiraCamada = nrNeuroniosPrimeiraCamada;
//        return this;
//    }
//    // -------- ALTERA A QUANTIDADE DE NEURONIOS ENTRADA -----------------------
//    public InterfaceTreinoRede setNrNeuroniosEntrada(int nrNeuroniosEntrada) {
//        this.nrNeuroniosEntrada = nrNeuroniosEntrada;
//        return this;
//    }
//
//}
