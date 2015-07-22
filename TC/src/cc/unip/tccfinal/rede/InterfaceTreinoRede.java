/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.rede;

import cc.unip.tccfinal.rede.treinamento.ObterDadosTreino;

/**
 *
 * @author MOISES
 */
public class InterfaceTreinoRede {

    private double[][] CONJUNTO_TREINAMENTO;
    private double[] VALORES_ESPERADOS;
    private int porcentTreino = 10;
    private int nrNeuroniosPrimeiraCamada = 3;
    private int nrNeuroniosEntrada = 3;
    private RedeNeuralMLP rede;
    private double taxaAprendizado = 0.30, erroMinimo = 0.03;
    private int numeroMaximoEpocas = 10000000;
    private int epocas = 0;
    private static InterfaceTreinoRede instance;

    public static InterfaceTreinoRede getInstance() {
        return instance == null ? instance = new InterfaceTreinoRede() : instance;
    }
    ObterDadosTreino tr;

    private InterfaceTreinoRede() {
    }
    /*#########################################################################
     ##########  RECUPERA DODOS DO BANCO E RETORNA CONJUNTO TREINO #############
     #########################################################################*/

    public InterfaceTreinoRede prepararDados() {
        tr = new ObterDadosTreino().setPorcentagemParaTreinamento(porcentTreino).build();
        CONJUNTO_TREINAMENTO = tr.getMATRIZ_DADOS();
        VALORES_ESPERADOS = tr.getRESULTADO_ESPERADO();
        return this;
    }

    // -------------------------------------------------------------------------

    public InterfaceTreinoRede build() {
        

        return this;
    }

    // --------------- TREINA A REDE COM OS DADOS OBTIDO DO BANCO --------------

    public void treinar() {
        System.out.println("#######################################################################");
        System.out.println("Porcentagem de Treino                 :: " + porcentTreino);
        System.out.println("Total de neuronios entrada            :: " + nrNeuroniosEntrada);
        System.out.println("Total de neuronios primeira camada    :: " + nrNeuroniosPrimeiraCamada);
        System.out.println("Taxa de aprendizado                   :: " + taxaAprendizado);
        System.out.println("Erro Mínimo                           :: " + erroMinimo);
        System.out.println("Numero Máximo de épocas               :: " + numeroMaximoEpocas);
        System.out.println("Total de objetos pra treino           :: " + CONJUNTO_TREINAMENTO.length);
        System.out.println("#######################################################################");
        // ------------------------------------------------------------------------------------------
        rede = new RedeNeuralMLP(nrNeuroniosPrimeiraCamada, nrNeuroniosEntrada);
        rede.setNrNeuroniosEntrada(nrNeuroniosEntrada);
        rede.setNrNeuroniosPrimeiraCamada(nrNeuroniosPrimeiraCamada);
        rede.setFatorAdaptacao(taxaAprendizado);
        rede.setErroMinimo(erroMinimo);
        rede.setNumeroMaximoEpocas(numeroMaximoEpocas);
        // ------------------------------------------------------------------------------------------
        rede.treinar(CONJUNTO_TREINAMENTO, VALORES_ESPERADOS);
    }

    // ----------- CLASSIFICA UMA AMOSTRA RECEBIDA E RETORNA 0 OU 1 ------------

    public int classificar(double[] amostra) {
        return rede.classificar(amostra);
    }

    // --------- ATERA A PORCENTAGEM DE DADOS OBTIDOS A SEREM TREINADOS --------

    public InterfaceTreinoRede setPorcentagemTreinamento(int porcentagemTreinamento) {
        this.porcentTreino = porcentagemTreinamento;
        return this;
    }

    // -------- ALTERA A QUANTIDADE DE NEURONIO PRIMEIRA CAMADA ----------------

    public InterfaceTreinoRede setNrNeuroniosPrimeiraCamada(int nrNeuroniosPrimeiraCamada) {
        this.nrNeuroniosPrimeiraCamada = nrNeuroniosPrimeiraCamada;
        return this;
    }

    // -------- ALTERA A QUANTIDADE DE NEURONIOS ENTRADA -----------------------

    public InterfaceTreinoRede setNrNeuroniosEntrada(int nrNeuroniosEntrada) {
        this.nrNeuroniosEntrada = nrNeuroniosEntrada;
        return this;
    }

    // ------- ALTERA O FATOR DE ADAPTAÇÃO PADRAO ------------------------------

    public InterfaceTreinoRede setFatorAdaptacao(double fatorAdptacao) {
        this.taxaAprendizado = fatorAdptacao;
        return this;
    }

    // ------- ALTERA O ERRO MÁXIMO --------------------------------------------

    public InterfaceTreinoRede setErroMinimo(double erroMaximo) {
        this.erroMinimo = erroMaximo;
        return this;
    }

    //-------- ALTERA O NUMERO MÁXIMO DE EPOCAS --------------------------------

    public InterfaceTreinoRede setNumeroMaximoEpocas(int numeroMaximoEpocas) {
        this.numeroMaximoEpocas = numeroMaximoEpocas;
        return this;
    }
   // --------  RETORNA O TOTAL DE VEZES QUE O LAÇO FOI EXECUTADO --------------
    public int getEpocas() {
        return epocas;
    }

}
