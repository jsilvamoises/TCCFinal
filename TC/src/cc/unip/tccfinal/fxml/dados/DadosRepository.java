/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.dados;

import cc.unip.tccfinal.fxml.controller.EquipamentoController;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Moises
 */
public class DadosRepository {

    private final Double[] equipamentos;
    private EquipamentoController controller;
    private List<Object[]> listaBanco = new ArrayList<>();
    private List<Object[]> listaTreinamento = new ArrayList<>();
    private List<Object[]> listaParaTeste = new ArrayList<>();

    private double matrizTreinamento[][];
    private double matrizTeste[][];
    private double resultadoEsperado[];
    private static final double BIAS = 1.0;

    private double porcentagemParaTreinamento = 50.0;
    private int tamanhoListraTreino;

    private DecimalFormat formatoDecimal = new DecimalFormat("#0.000");

    public DadosRepository() {
        this.equipamentos = new Double[]{0.1, 0.2, 0.3, 0.4};
        this.controller = new EquipamentoController();
    }
    
    public void processar(){
        obterAmostras();
        gerarMatrizTransposta();
    }
    
    private void gerarMatrizTransposta() {

        // E.id.idEquipamento, E.id.valorSensorReferencia/100 ,E.id.statusEquipamento
        matrizTreinamento = new double[3][listaTreinamento.size()];
        resultadoEsperado = new double[listaTreinamento.size()]; // DEFINE O TAMANHO DO VETOR DE RESULTADOS

        double[] vetor1 = new double[listaTreinamento.size()];//EQUIP
        double[] vetor2 = new double[listaTreinamento.size()];// VALOR SENSOR
        double[] vetor3 = new double[listaTreinamento.size()];// BIAS
        double[] vetor4 = new double[listaTreinamento.size()];// RES ESPERADO
        //MONTA A MATRIZ PARA TREINAMENTO
        for (int i = 0; i < listaTreinamento.size(); i++) {
            Object[] obj = listaTreinamento.get(i);
            vetor1[i] = (double) obj[1];//
            vetor2[i] = (double) obj[2];
            vetor3[i] = (double) (double) 1.0;
            vetor4[i] = Double.parseDouble(obj[3].toString());

        }
        matrizTreinamento[0] = vetor1;
        matrizTreinamento[1] = vetor2;
        matrizTreinamento[2] = vetor3;
        resultadoEsperado = vetor4;

        //-------------------------------
    }

    private void obterAmostras() {
       
        Random random = new Random();
        for (double d:equipamentos) {
            listaBanco = controller.listaParaTreinamento(d);
            
            double b= (listaBanco.size()*porcentagemParaTreinamento)/100;
            
            
            System.out.println("Tamanho da Lista:: "+listaBanco.size());
           
            
            int a = (int) Math.round(b);
            if (a % 2 != 0) {
               a+=1;
            }
            
            
            if(a >0){                
                do{
                   int indice = random.nextInt((int)listaBanco.size());
                    System.err.println("Adicionando objetos para Treinamento");
                   listaTreinamento.add(listaBanco.remove(indice));                   
                }while(listaTreinamento.size() < Math.round(a)); 
            }
            
            while(listaBanco.size()>0){
                    System.err.println("Adicionando dados para lista de Testes!!!!");
                    listaParaTeste.add(listaBanco.remove(0));
                }
        }
        
        imprimirListas();
    }
    
    private void imprimirListas(){
        int i = 0;
        System.out.println("Listra Treinamento");
        listaTreinamento.stream().forEach((o) -> {
            System.out.println(o);
        });
        System.out.println("Listra Para Teste");
        listaParaTeste.stream().forEach((o) -> {  
             Object ab = o;
            System.out.println(ab.toString());
            
        });
    }
    
    
    
    public static void main(String[] args) {
        new DadosRepository().obterAmostras();
        System.exit(0);
    }

    public List<Object[]> getListaParaTeste() {
        return listaParaTeste;
    }   

    public double[][] getMatrizTreinamento() {
        return matrizTreinamento;
    }  

    public double[] getResultadoEsperado() {
        return resultadoEsperado;
    }

    public double getPorcentagemParaTreinamento() {
        return porcentagemParaTreinamento;
    }

    public void setPorcentagemParaTreinamento(double porcentagemParaTreinamento) {
        this.porcentagemParaTreinamento = porcentagemParaTreinamento;
    }

    public int getTamanhoListraTreino() {
        tamanhoListraTreino = listaTreinamento.size();
        return tamanhoListraTreino;
    }

    
    
    
}
