/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.rede.treinamento;

import cc.unip.tccfinal.fxml.controller.EquipamentoController;
//import cc.unip.tccfinal.rede.RedeMLPExe;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Moisés
 */
public class ObterDadosTreino {

    private final EquipamentoController controller;
    private List<Object[]> objetos = new ArrayList<>();
    private final List<Object[]> objetosParaTreinamento = new ArrayList<>();
    private double porcentagemParaTreinamento = 30.0;

    private DecimalFormat formato = new DecimalFormat("#0.000");
    private double MATRIZ_DADOS[][];
    private double MATRIZ_TESTES[][];
    private double RESULTADO_ESPERADO[];
    private double RESULTADO_ESPERADO_TESTE[];
    private static final double BIAS = 1.0;
    private int totalColunas = 0;
    private List<Double> listaKey;

    //private RedeMLPExe rede;
    // -------------------------------------------------------------------------
    public ObterDadosTreino() {

        this.controller = new EquipamentoController();
        // rede = RedeMLPExe.getInstance();
    }

    // -------------------------------------------------------------------------
    private ObterDadosTreino obterDadosBanco() {
        objetos = controller.listaParaTreinamento();
        return this;
    }

    // -------------------------------------------------------------------------
    public double getPorcentagemParaTreinamento() {
        return porcentagemParaTreinamento;
    }

    // -------------------------------------------------------------------------
    public ObterDadosTreino setPorcentagemParaTreinamento(int porcentagemParaTreinamento) {
        this.porcentagemParaTreinamento = porcentagemParaTreinamento;
        return this;
    }

    // -------------------------------------------------------------------------
    private ObterDadosTreino separarAmostraParaTreinamento() {
        listaKey = controller.listKeyEquipamento();
        Double teste, teste1;
        Integer on = 0, off = 0;

        double sizeListaMaster = objetos.size();
        System.out.println("Tamanho da amostragem" + objetos.size());
        int totalAmostras = (int) Math.round((sizeListaMaster / 100) * porcentagemParaTreinamento);
        if (totalAmostras % 2 != 0) {
            totalAmostras += 1;
        }
        System.out.println("Sera separado" + totalAmostras + " amostras....");
        Random random = new Random();
        int unidadeAmostra = totalAmostras / listaKey.size();
        /*
         if (totalAmostras > 0) {

         do {
         //int indice = random.nextInt((int) objetos.size());
         objetosParaTreinamento.add(objetos.remove(0));
         } while (objetosParaTreinamento.size() < Math.round(totalAmostras));
         }
         */

        if (totalAmostras > 0) {

            do {
                int indice = random.nextInt((int) objetos.size());
                objetosParaTreinamento.add(objetos.remove(indice));
            } while (objetosParaTreinamento.size() < Math.round(totalAmostras));
        }
        /*
         if (totalAmostras > 0) {
         Object[] o;
         int indice;
         do {
         do {
         indice = random.nextInt((int) objetos.size());
         o = objetos.get(indice);
         } while ((byte) o[3] != 0.0);
         objetosParaTreinamento.add(objetos.remove(indice));
         //==============================================================
         if (totalAmostras > 0) {
         do {
         indice = random.nextInt((int) objetos.size());
         o = objetos.get(indice);
         } while ((byte) o[3] != 1.0);
         objetosParaTreinamento.add(objetos.remove(indice));
         }

         } while (objetosParaTreinamento.size() < Math.round(totalAmostras));
         }
         */

        for (int i = 0; i < objetosParaTreinamento.size(); i++) {
            Object[] obj = objetosParaTreinamento.get(i);
            System.out.println("Inserindo para treino:: " + obj[1] + " com valor:: " + obj[3]);
        }

        System.out.println("Foram selecionado para treinamento " + objetosParaTreinamento.size() + " objetos");
        System.out.println("O Total de " + sizeListaMaster);
        gerarMatrizTransposta();
        return this;
    }

    /**
     * <h1>Construçao de objeto</h1>
     * <b>Caso não seja definido a porcentagem de amostra, será usado padrão
     * 10%</b>
     *
     * @return
     */
    public ObterDadosTreino build() {
        obterDadosBanco();
        separarAmostraParaTreinamento();
        gerarMatrizTransposta();
        return this;
    }

    public void gerarMatrizTransposta() {

        // E.id.idEquipamento, E.id.valorSensorReferencia/100 ,E.id.statusEquipamento
        MATRIZ_DADOS = new double[3][objetosParaTreinamento.size()];
        RESULTADO_ESPERADO = new double[objetosParaTreinamento.size()]; // DEFINE O TAMANHO DO VETOR DE RESULTADOS

        double[] vetor1 = new double[objetosParaTreinamento.size()];//EQUIP
        double[] vetor2 = new double[objetosParaTreinamento.size()];// VALOR SENSOR
        double[] vetor3 = new double[objetosParaTreinamento.size()];// BIAS
        double[] vetor4 = new double[objetosParaTreinamento.size()];// RES ESPERADO
        //MONTA A MATRIZ PARA TREINAMENTO
        for (int i = 0; i < objetosParaTreinamento.size(); i++) {
            Object[] obj = objetosParaTreinamento.get(i);
            vetor1[i] = (double) obj[1];
            vetor2[i] = (double) obj[2];
            vetor3[i] = (double) (double) 1.0;
            vetor4[i] = Double.parseDouble(obj[3].toString());

        }
        MATRIZ_DADOS[0] = vetor1;
        MATRIZ_DADOS[1] = vetor2;
        MATRIZ_DADOS[2] = vetor3;
        RESULTADO_ESPERADO = vetor4;

        //-------------------------------
    }

    public double[][] getMATRIZ_DADOS() {
        return MATRIZ_DADOS;
    }

    public double[] getRESULTADO_ESPERADO() {
        return RESULTADO_ESPERADO;
    }

    public List<Object[]> getObjetos() {
        return objetos;
    }

    public double[][] getMATRIZ_TESTES() {
        return MATRIZ_TESTES;
    }

    public double[] getRESULTADO_ESPERADO_TESTE() {
        return RESULTADO_ESPERADO_TESTE;
    }

    public List<Object[]> getObjetosParaTreinamento() {
        return objetosParaTreinamento;
    }

}
