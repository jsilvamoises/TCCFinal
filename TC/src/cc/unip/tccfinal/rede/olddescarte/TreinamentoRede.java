///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cc.unip.tccfinal.rede.treinamento;
//
//import cc.unip.tccfinal.controller.EquipamentoController;
//import cc.unip.tccfinal.rede.RedeMLPExe;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
///**
// *
// * @author Moisés
// */
//public class ObterDadosTreino {
//
//    private final EquipamentoController controller;
//    private List<Object[]> objetos = new ArrayList<>();
//    private final List<Object[]> objetosParaTreinamento = new ArrayList<>();
//    private double porcentagemParaTreinamento = 10.0;
//
//    private DecimalFormat formato = new DecimalFormat("#0.000");
//    private double MATRIZ_DADOS[][];
//    private double RESULTADO_ESPERADO[];
//    private static final double BIAS = 1.0;
//    private int totalColunas = 0;
//    private RedeMLPExe rede;
//
//    // -------------------------------------------------------------------------
//    public ObterDadosTreino() {
//        this.controller = new EquipamentoController();
//        rede = RedeMLPExe.getInstance();
//    }
//
//    // -------------------------------------------------------------------------
//    private ObterDadosTreino obterDadosBanco() {
//        objetos = controller.listaParaTreinamento();
//        return this;
//    }
//
//    // -------------------------------------------------------------------------
//    public double getPorcentagemParaTreinamento() {
//        return porcentagemParaTreinamento;
//    }
//
//    // -------------------------------------------------------------------------
//    public ObterDadosTreino setPorcentagemParaTreinamento(int porcentagemParaTreinamento) {
//        this.porcentagemParaTreinamento = porcentagemParaTreinamento;
//        return this;
//    }
//
//    // -------------------------------------------------------------------------
//    private ObterDadosTreino separarAmostraParaTreinamento() {
//        double sizeListaMaster = objetos.size();
//        System.out.println(objetos.size());
//        double totalAmostras = ((sizeListaMaster / 100) * porcentagemParaTreinamento) - 1;
//        System.out.println("Sera separado" + totalAmostras);
//        Random random = new Random();
//        if (totalAmostras > 0) {
//            do {
//                int indice = random.nextInt((int) objetos.size());
//                // System.out.println("Indice selecionado:: " + indice);
//                objetosParaTreinamento.add(objetos.get(indice));
//                objetos.remove(indice);
//
//            } while (objetosParaTreinamento.size() < totalAmostras);
//        }
//
//        System.out.println("Foram selecionado para treinamento " + objetosParaTreinamento.size() + " objetos");
//        System.out.println("O Total de " + objetosParaTreinamento.size());
//        return this;
//    }
//
//    /**
//     * <h1>Construçao de objeto</h1>
//     * <b>Caso não seja definido a porcentagem de amostra, será usado padrão
//     * 10%</b>
//     */
//    public ObterDadosTreino build() {
//        obterDadosBanco();
//        separarAmostraParaTreinamento();
//        gerarMatrizTransposta();
//        return this;
//    }
//
//    public static void main(String[] args) {
//        new ObterDadosTreino()
//                .setPorcentagemParaTreinamento(100)
//                .build();
//        System.exit(0);
//    }
//
//    @SuppressWarnings("empty-statement")
//    public void gerarMatrizTransposta() {
//        // E.id.idEquipamento, E.id.valorSensorReferencia/100 ,E.id.statusEquipamento
//        MATRIZ_DADOS = new double[3][objetosParaTreinamento.size()];
//        RESULTADO_ESPERADO = new double[objetosParaTreinamento.size()]; // DEFINE O TAMANHO DO VETOR DE RESULTADOS
//
//        double[] vetor1 = new double[objetosParaTreinamento.size()];//EQUIP
//        double[] vetor2 = new double[objetosParaTreinamento.size()];// VALOR SENSOR
//        double[] vetor3 = new double[objetosParaTreinamento.size()];// BIAS
//        double[] vetor4 = new double[objetosParaTreinamento.size()];// RES ESPERADO
//
//        for (int i = 0; i < objetosParaTreinamento.size(); i++) {
//            Object[] obj = objetosParaTreinamento.get(i);
//            vetor1[i] = (double) obj[1];
//            vetor2[i] = (double) obj[2];
//            vetor3[i] = (double) (double) 1.0;
//            vetor4[i] = Double.parseDouble(obj[3].toString()) ;
//
//        }
//
//        MATRIZ_DADOS[0] = vetor1;
//        MATRIZ_DADOS[1] = vetor2;
//        MATRIZ_DADOS[2] = vetor3;
//        RESULTADO_ESPERADO = vetor4;
//
////        for(int i = 0; i < 3; i++){
////              
//////            MATRIZ_DADOS[i][0] = (double) obj[1];
//////            MATRIZ_DADOS[i][1] = (double) obj[2];
//////            MATRIZ_DADOS[i][2] = (double) 1.0;              
////              double v[] ={0,0,0};
////              
////              for(int j=0; j < objetosParaTreinamento.size(); j++){
////                  Object[] obj =objetosParaTreinamento.get(i);
////              }
////              MATRIZ_DADOS[i]=v;
////            
////            RESULTADO_ESPERADO[i] = (double) new Double(obj[3].toString());
////            //for(int j = 0; j< 3; j++){
////                
////            //}
////        }
////        for(int i = 0 ; i < objetosParaTreinamento.size(); i++){
////            Object[] obj =objetosParaTreinamento.get(i);
////            MATRIZ_DADOS[0][i] = (double) obj[1];
////            MATRIZ_DADOS[1][i] = (double) obj[2];
////            MATRIZ_DADOS[2][i] = (double) 1.0;
////            
////            RESULTADO_ESPERADO[i] = (double) new Double(obj[3].toString());
////            System.out.println("********************");
////            System.out.println(MATRIZ_DADOS[0][i]);
////            System.out.println(MATRIZ_DADOS[1][i]);
////            System.out.println(MATRIZ_DADOS[2][i]);
////            System.out.println("#####################");
////            System.out.println(RESULTADO_ESPERADO[i]);
////            
////        }
//        //testar();
//      //  imprimirMatriz();
//        //  imprimirResultadosEsperado();
//        //rede.setCONJUNTO_TREINAMENTO(MATRIZ_DADOS).setRESULTADOS_ESPERADOS(RESULTADO_ESPERADO).treinarRede();
//        //double[] CLASSIFICAR = {1.0, 0.70, 1.0};
//       // System.out.println(rede.classificar(CLASSIFICAR));
//    }
//
//    private void imprimirMatriz() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < objetosParaTreinamento.size(); j++) {
//                System.out.print("| " + formato.format(MATRIZ_DADOS[i][j]) + " |");
//            }
//            System.out.println("\n");
//        }
//    }
//
//    private void imprimirResultadosEsperado() {
//        for (int i = 0; i < objetosParaTreinamento.size(); i++) {
//            System.out.print("| " + formato.format(RESULTADO_ESPERADO[i]) + " |");
//        }
//    }
//
//    public void testar() {
//        RESULTADO_ESPERADO = new double[4];
//        RESULTADO_ESPERADO[0] = 0.0;
//        RESULTADO_ESPERADO[1] = 1.0;
//        RESULTADO_ESPERADO[2] = 1.0;
//        RESULTADO_ESPERADO[3] = 0.0;
//
//        double[][] CONJUNTO_TREINAMENTO
//                = {
//                    {0.0, 0.0, 1.0, 1.0},
//                    {0.0, 1.0, 0.0, 1.0},
//                    {1.0, 1.0, 1.0, 1.0} // bias
//                };
//
//        MATRIZ_DADOS = CONJUNTO_TREINAMENTO;
//    }
//
//    public double[][] getMATRIZ_DADOS() {
//        return MATRIZ_DADOS;
//    }
//
//    public double[] getRESULTADO_ESPERADO() {
//        return RESULTADO_ESPERADO;
//    }
//
//}
