///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cc.unip.tccfinal.rede;
//
///**
// *
// * @author MOISES
// */
//public class RedeMLPExe {
//   private double[][] CONJUNTO_TREINAMENTO;
//   private double[] RESULTADOS_ESPERADOS;
//   private double[] VALORES_CLASSIFICAR;
//   private int quantidadequantidadeNeuroniosEntrada;
//   private int quantidadeNeuronioPrimeiraCamada;
//   private RedeNeuralMLP rede;    
//   private static  RedeMLPExe instance; 
//   private boolean isRedeTreinada = false;
//
//    private RedeMLPExe() {
//    }
//   
//   
//   public static RedeMLPExe getInstance(){
//       return instance==null?instance = new RedeMLPExe():instance;
//   }
//       
//
//
//
//	/*private static double[][] CONJUNTO_TREINAMENTO = {
//		{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0},
//		{0.0, 0.5, 0.5, 1.0, 0.0, 0.0, 1.0},
//		{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0},
//		{1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0},
//		{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0},
//		{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0} // bias
//	};*/
//
////	private static double[] VALORES_ESPERADOS = {0.0, 1.0, 1.0, 0.0};
//   //	private static double[][] CONJUNTO_TREINAMENTO = {
////		{0.0, 0.0, 1.0, 1.0},
////		{0.0, 1.0, 0.0, 1.0},
////		{1.0, 1.0, 1.0, 1.0} // bias
////	};
//
//	//private static double[] VALORES_ESPERADOS = {1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0};
//
//	public static void main(String[] args) {
//
//		int nrNeuroniosPrimeiraCamada = 3;
//		int nrNeuroniosEntrada = 3;
//
//		RedeNeuralMLP rede = new RedeNeuralMLP(nrNeuroniosPrimeiraCamada, nrNeuroniosEntrada);
//
//		System.out.println("Teste antes do treinamento: --------------------------------------------------------------------------- \n");
//		RedeMLPExe.imprimirTesteDeClassificacao(rede);
//
//		//rede.treinar(RedeMLPExe.CONJUNTO_TREINAMENTO, RedeMLPExe.VALORES_ESPERADOS);
//
//		System.out.println("\n Teste depois do treinamento: ----------------------------------------------------------------------- \n");
//		RedeMLPExe.imprimirTesteDeClassificacao(rede);
//
//	}
//        
//        public RedeMLPExe treinarRede(){
//            rede = new RedeNeuralMLP(quantidadeNeuronioPrimeiraCamada, quantidadequantidadeNeuroniosEntrada);
//            rede.treinar(CONJUNTO_TREINAMENTO, RESULTADOS_ESPERADOS);
//            isRedeTreinada = true;
//            return this;
//               
//        }
//        
//        public int classificar(double[] conjuntoDados){
//              return rede.classificar(conjuntoDados);
//        }
//
//	private static void imprimirTesteDeClassificacao(RedeNeuralMLP rede) {
//		rede.classificar(new double[] { 0.0, 0.0, 1.0 });
//		rede.classificar(new double[] { 0.0, 1.0, 1.0 });
//		rede.classificar(new double[] { 1.0, 0.0, 1.0 });
//		rede.classificar(new double[] { 1.0, 1.0, 1.0 });
//                
//
//		/*rede.classificar(new double[] {0.0, 0.0, 0.0, 1.0, 0.0, 1.0});
//		rede.classificar(new double[] {1.0, 0.5, 1.0, 1.0, 1.0, 1.0});*/
//	}
//
//    public double[][] getCONJUNTO_TREINAMENTO() {
//        return CONJUNTO_TREINAMENTO;
//    }
//
//    public RedeMLPExe setCONJUNTO_TREINAMENTO(double[][] CONJUNTO_TREINAMENTO) {
//        this.CONJUNTO_TREINAMENTO = CONJUNTO_TREINAMENTO;
//        return this;
//    }
//
//    public double[] getRESULTADOS_ESPERADOS() {
//        return RESULTADOS_ESPERADOS;
//    }
//
//    public RedeMLPExe setRESULTADOS_ESPERADOS(double[] RESULTADOS_ESPERADOS) {
//        this.RESULTADOS_ESPERADOS = RESULTADOS_ESPERADOS;
//        return this;
//    }
//
//    public double[] getVALORES_CLASSIFICAR() {
//        return VALORES_CLASSIFICAR;
//    }
//
//    public RedeMLPExe setVALORES_CLASSIFICAR(double[] VALORES_CLASSIFICAR) {
//        this.VALORES_CLASSIFICAR = VALORES_CLASSIFICAR;
//        return this;
//    }
//
//    public int getQuantidadequantidadeNeuroniosEntrada() {
//        return quantidadequantidadeNeuroniosEntrada;
//    }
//
//    public RedeMLPExe setQuantidadequantidadeNeuroniosEntrada(int quantidadequantidadeNeuroniosEntrada) {
//        this.quantidadequantidadeNeuroniosEntrada = quantidadequantidadeNeuroniosEntrada;
//        return this;
//    }
//
//    public int getQuantidadeNeuronioPrimeiraCamada() {
//        return quantidadeNeuronioPrimeiraCamada;
//    }
//
//    public RedeMLPExe setQuantidadeNeuronioPrimeiraCamada(int quantidadeNeuronioPrimeiraCamada) {
//        this.quantidadeNeuronioPrimeiraCamada = quantidadeNeuronioPrimeiraCamada;
//        return this;
//    }
//
//   
//
//       
//
//}