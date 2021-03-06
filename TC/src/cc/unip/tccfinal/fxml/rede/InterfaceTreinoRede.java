/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.rede;

import cc.unip.tccfinal.fxml.auxiliar.DadosGraficoBarras;
import cc.unip.tccfinal.fxml.controller.TreinoController;
import cc.unip.tccfinal.fxml.rede.treinamento.ObterDadosTreino;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;

/**
 *
 * @author MOISES
 */
public class InterfaceTreinoRede {

    private static final double BIAS = 1.0;
    private List<Object[]> objetosParaTeste = new ArrayList<>();
   // private double[][] CONJUNTO_TREINAMENTO;
   // private double[] VALORES_ESPERADOS;
    private int porcentTreino = 70;
    private int nrNeuroniosPrimeiraCamada = 3;
    private int nrNeuroniosEntrada = 3;
    private RMLP rede;
    private double taxaAprendizado = 0.30, erroMinimo = 0.03;
    private int numeroMaximoEpocas = 10000000;
    private int epocas = 0;
    private static InterfaceTreinoRede instance;
    private TextArea prompt;
    private int erroClassificacao, acertoClassificacao;
    private int listaTreinoSize, listaVerificacaoSize;
    private List<DadosGraficoBarras> dadosGraficoBarras = new ArrayList<>();
    private boolean isTreinada = false;

    public static InterfaceTreinoRede getInstance() {
        return instance == null ? instance = new InterfaceTreinoRede() : instance;
    }
    ObterDadosTreino tr;
    //  DadosRepository tr;

    private InterfaceTreinoRede() {
        rede = new RMLP(nrNeuroniosPrimeiraCamada, nrNeuroniosEntrada);
        //tr = new ObterDadosTreino().setPorcentagemParaTreinamento(porcentTreino).build();

    }
    /*#########################################################################
     ##########  RECUPERA DODOS DO BANCO E RETORNA CONJUNTO TREINO #############
     #########################################################################*/

    public InterfaceTreinoRede prepararDados() {
        tr = new ObterDadosTreino().setPorcentagemParaTreinamento(porcentTreino).build();
       ///tr = new DadosRepository();
       //  tr.setPorcentagemParaTreinamento(porcentTreino).build();
        /// tr.processar();
       /// CONJUNTO_TREINAMENTO = tr.getMATRIZ_DADOS();
       /// VALORES_ESPERADOS = tr.getRESULTADO_ESPERADO();
        objetosParaTeste = tr.getObjetos();// Retorna os objetos restantes que não foram para treino
        ///listaTreinoSize = tr.getTamanhoListraTreino();
        ///listaVerificacaoSize = tr.getListaParaTeste().size();*/

       
        
        ///CONJUNTO_TREINAMENTO = tr.getMATRIZ_DADOS();
        ///VALORES_ESPERADOS = tr.getRESULTADO_ESPERADO();
        listaTreinoSize = tr.getObjetosParaTreinamento().size();
        listaVerificacaoSize = tr.getObjetos().size();
        return this;
    }

    // -------------------------------------------------------------------------
    public InterfaceTreinoRede build() {

        return this;
    }

    // --------------- TREINA A REDE COM OS DADOS OBTIDO DO BANCO --------------
    public void treinar() {
        dadosGraficoBarras.clear();
        
        // ------------------------------------------------------------------------------------------
        rede.setNrNeuroniosEntrada(nrNeuroniosEntrada);
        rede.setNrNeuroniosPrimeiraCamada(nrNeuroniosPrimeiraCamada);
        rede.setFatorAdaptacao(taxaAprendizado);
        rede.setErroMinimo(erroMinimo);
        rede.setNumeroMaximoEpocas(numeroMaximoEpocas);

        // ------------------------------------------------------------------------------------------
        // if (tr.getObjetosParaTreinamento().size() < 2) {
        //   new Alert(Alert.AlertType.INFORMATION, "Não é possível realizar treino de uma matriz sem dados", ButtonType.CLOSE).showAndWait();
        // } else {
        //rede.treinar(CONJUNTO_TREINAMENTO, VALORES_ESPERADOS);
       /* if (porcentTreino > 70 || porcentTreino < 30) {
         porcentTreino = 35;
         }*/
        
        System.out.println("Porcentagem Treino Atual " + porcentTreino);
        int key = 0;
        // do {
        prepararDados();//Obtem amostra 
        System.out.println("#######################################################################");
        System.out.println("Porcentagem de Treino                 :: " + porcentTreino);
        System.out.println("Total de neuronios entrada            :: " + nrNeuroniosEntrada);
        System.out.println("Total de neuronios primeira camada    :: " + nrNeuroniosPrimeiraCamada);
        System.out.println("Taxa de aprendizado                   :: " + taxaAprendizado);
        System.out.println("Erro Mínimo                           :: " + erroMinimo);
        System.out.println("Numero Máximo de épocas               :: " + numeroMaximoEpocas);
        //System.out.println("Total de objetos pra treino           :: " + tr.getObjetosParaTreinamento().size());
        System.out.println("#######################################################################");

        // porcentTreino = porcentTreino + Math.round(erroClassificacao);
        erroClassificacao = 0;
        acertoClassificacao = 0;
        System.out.println("Porcentagem de treino ajustado para:: " + porcentTreino);
            ///rede.treinar(tr.getMATRIZ_DADOS(), tr.getRESULTADO_ESPERADO());

        //rede.treinar(CONJUNTO_TREINAMENTO, VALORES_ESPERADOS);
        
        rede.treinar(tr.getMATRIZ_DADOS(), tr.getRESULTADO_ESPERADO());

        //  rede.treinar(tr.getMatrizTreinamento(), tr.getResultadoEsperado());
        this.analisarAmostras();
        dadosGraficoBarras.add(new DadosGraficoBarras(++key, acertoClassificacao, erroClassificacao));
        // } while (erroClassificacao > 0 && porcentTreino < 100);
        isTreinada = true;
        System.gc();
        // }
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
        epocas = rede.getEpocas();
        return epocas;
    }

    public TextArea getPrompt() {
        return prompt;
    }

    public int getAcertoClassificacao() {
        return acertoClassificacao;
    }

    public int getErroClassificacao() {
        return erroClassificacao;
    }

    // ------------------------- OBTEM LISTA PARA TESTES -----------------------
    public void analisarAmostras() {
        acertoClassificacao = 0;
        erroClassificacao = 0;
        int res;
        objetosParaTeste = tr.getObjetos();// Retorna os objetos restantes que não foram para treino

        for (int i = 0; i < objetosParaTeste.size(); i++) {
            Object[] obj = objetosParaTeste.get(i);
            // PARAMETRO 0 = ID EQUIPANETO | PARAMENTRO 1 = VALOR DO SENSOR REFERENCIA | PARAMETRO 2 BIAS
            double[] objeto = {};
            res = rede.classificar(new double[]{Double.valueOf(obj[1].toString()), Double.valueOf(obj[2].toString()), BIAS});

            if (res == Integer.valueOf(obj[3].toString())) {
                acertoClassificacao++;
            } else {
                erroClassificacao++;
                //Caso 
                if (TreinoController.aprenderComErros()) {
                    this.treinar();
                }
            }
//            System.out.println("Indice 0:: "+obj[0]);//chave
//            System.out.println("Indice 1:: "+obj[1]);// id equipamento
//            System.out.println("Indice 2:: "+obj[2]);// valor sensor referencia
//            System.out.println("Indice 3:: "+obj[3]);//resultado esperado
            // System.out.println("Indice 4"+obj[4]);
        }

        System.out.println("Erros de classificação:: " + erroClassificacao);
        System.out.println("Acertos de classificação:: " + acertoClassificacao);
    }

    public int getPorcentTreino() {
        return porcentTreino;
    }

    public List<DadosGraficoBarras> getDadosGraficoBarras() {
        return dadosGraficoBarras;
    }

    public int getListaTreinoSize() {
        return listaTreinoSize;
    }

    public int getListaVerificacaoSize() {
        return listaVerificacaoSize;
    }

    public boolean isIsTreinada() {
        return isTreinada;
    }

}
