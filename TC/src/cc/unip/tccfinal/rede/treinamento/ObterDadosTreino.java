/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.rede.treinamento;

import cc.unip.tccfinal.controller.EquipamentoController;
import cc.unip.tccfinal.rede.RedeMLPExe;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Moisés
 */
public class ObterDadosTreino {

    private final EquipamentoController controller;
    private List<Object[]> objetos = new ArrayList<>();
    private final List<Object[]> objetosParaTreinamento = new ArrayList<>();
    private double porcentagemParaTreinamento = 10.0;

    private DecimalFormat formato = new DecimalFormat("#0.000");
    private double MATRIZ_DADOS[][];
    private double RESULTADO_ESPERADO[];
    private static final double BIAS = 1.0;
    private int totalColunas = 0;
    private RedeMLPExe rede;

    // -------------------------------------------------------------------------
    public ObterDadosTreino() {
        this.controller = new EquipamentoController();
        rede = RedeMLPExe.getInstance();
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
        double sizeListaMaster = objetos.size();
        System.out.println(objetos.size());
        double totalAmostras = ((sizeListaMaster / 100) * porcentagemParaTreinamento) - 1;
        System.out.println("Sera separado" + totalAmostras);
        Random random = new Random();
        if (totalAmostras > 0) {
            do {
                int indice = random.nextInt((int) objetos.size());
                // System.out.println("Indice selecionado:: " + indice);
                objetosParaTreinamento.add(objetos.get(indice));
                objetos.remove(indice);

            } while (objetosParaTreinamento.size() < totalAmostras);
        }

        System.out.println("Foram selecionado para treinamento " + objetosParaTreinamento.size() + " objetos");
        System.out.println("O Total de " + objetosParaTreinamento.size());
        return this;
    }

    /**
     * <h1>Construçao de objeto</h1>
     * <b>Caso não seja definido a porcentagem de amostra, será usado padrão
     * 10%</b>
     * @return 
     */
    public ObterDadosTreino build() {
        obterDadosBanco();
        separarAmostraParaTreinamento();
        gerarMatrizTransposta();
        return this;
    }

    public static void main(String[] args) {
        new ObterDadosTreino()
                .setPorcentagemParaTreinamento(100)
                .build();
        System.exit(0);
    }

    
    public void gerarMatrizTransposta() {
        // E.id.idEquipamento, E.id.valorSensorReferencia/100 ,E.id.statusEquipamento
        MATRIZ_DADOS = new double[3][objetosParaTreinamento.size()];
        RESULTADO_ESPERADO = new double[objetosParaTreinamento.size()]; // DEFINE O TAMANHO DO VETOR DE RESULTADOS

        double[] vetor1 = new double[objetosParaTreinamento.size()];//EQUIP
        double[] vetor2 = new double[objetosParaTreinamento.size()];// VALOR SENSOR
        double[] vetor3 = new double[objetosParaTreinamento.size()];// BIAS
        double[] vetor4 = new double[objetosParaTreinamento.size()];// RES ESPERADO

        for (int i = 0; i < objetosParaTreinamento.size(); i++) {
            Object[] obj = objetosParaTreinamento.get(i);
            vetor1[i] = (double) obj[1];
            vetor2[i] = (double) obj[2];
            vetor3[i] = (double) (double) 1.0;
            vetor4[i] = Double.parseDouble(obj[3].toString()) ;

        }

        MATRIZ_DADOS[0] = vetor1;
        MATRIZ_DADOS[1] = vetor2;
        MATRIZ_DADOS[2] = vetor3;
        RESULTADO_ESPERADO = vetor4;
    }

    public double[][] getMATRIZ_DADOS() {
        return MATRIZ_DADOS;
    }

    public double[] getRESULTADO_ESPERADO() {
        return RESULTADO_ESPERADO;
    }

}
