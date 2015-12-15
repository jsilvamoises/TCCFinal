package cc.unip.tccfinal.fxml.rede;

import cc.unip.tccfinal.fxml.controller.TreinoController;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class RMLP {

    private double fatorAdaptacao = 0.30; // índice de deslocamento em direção ao erro aceitável
    private double erroMinimo = 0.03;// Erro aceitável
    public static int epocas = 0; //quantidade de iterações realizadas no treino
    public static int numeroMaximoEpocas = 100000;// Numero máximo de iterações sobre a matriz de treinamento

    private double[][] conexoesPrimeiraCamada;
    private double[] conexoesSegundaCamada;
    private int nrNeuroniosPrimeiraCamada;
    private int nrNeuroniosEntrada;
    

    /*#####################################################################
     ##### CRIA UMA REDE NEURAL COM QUANTIDADES DE NEURONIOS POR CAMADA#####
     #####################################################################*/
    public RMLP(int nrNeuroniosPrimeiraCamada, int nrNeuroniosEntrada) {
        this.nrNeuroniosPrimeiraCamada = nrNeuroniosPrimeiraCamada;
        this.nrNeuroniosEntrada = nrNeuroniosEntrada;
        this.inicializarConexoesSinapticasDaRede();

    }

    
    /*#####################################################################
     ##### PROCESSO DE TREINAMENTO DA REDE COM UMA MATRIZ E VETOR      #####
     #####################################################################*/

    public void treinar(double[][] conjuntoTreinamento, double[] valoresEsperados) {
        

        double erro = 1.0;
        //System.out.println(erroMinimo);
        while ((Math.abs(erro) > erroMinimo) && (epocas < numeroMaximoEpocas)) {
            //System.out.println(erro);

            for (int i = 0; i < conjuntoTreinamento[0].length; i++) {
                double[] entradaSegundaCamada = propagarSinalPelaPrimeiraCamada(conjuntoTreinamento, i);
                double valorSaida = propagarSinalPelaSegundaCamada(entradaSegundaCamada);
                erro = calcularErro(valoresEsperados, valorSaida, i);
                double gradiente = getGradienteDeRetropopagacao(valorSaida, erro);
                aprender(conjuntoTreinamento, entradaSegundaCamada, gradiente, i);
            }
            epocas++;
            atualizarValorLabelEpocas(epocas);
            //System.gc();
        }
       

    }
    
    /*#####################################################################
     ##### CLASSIFICA UM VETOR DE DADOS INSERIDOS NA REDE              #####
     #####################################################################*/

    public int classificar(double[] entrada) {
        double y = 0;
        if (epocas > numeroMaximoEpocas) {
          //  System.out.println("Nao foi possivel atingir um ponto de convergencia, verifique os parametros e a estrutura da rede.");
        } else {
            // System.out.printf("Treinamento realizado em %s epocas. \n", epocas);
            double[] saidasPrimeiraCamada = getSaidaClassificacaoPrimeiraCamada(entrada);
            double[] entradaSegundaCamada = getEntradasSegundaCamada(saidasPrimeiraCamada);
            y = propagarSinalPelaSegundaCamada(entradaSegundaCamada);
           // System.out.println("Saidax: " + y);
           // System.out.println("Saidax: " + Math.round(y));
        }
        return (int) Math.round(y);
    }

    private void aprender(double[][] conjuntoTreinamento, double[] entradaSegundaCamada, double gradiente, int i) {
        retropropagarErroPelaSegundaCamada(entradaSegundaCamada, gradiente);
        retropropagarErroPelaPrimeiraCamada(conjuntoTreinamento, entradaSegundaCamada, gradiente, i);
    }

    private double[] propagarSinalPelaPrimeiraCamada(double[][] conjuntoTreinamento, int i) {
        double[] saidasPrimeiraCamada = getSaidaTreinamentoPrimeiraCamada(conjuntoTreinamento, i);
        return getEntradasSegundaCamada(saidasPrimeiraCamada);
    }

    private double propagarSinalPelaSegundaCamada(double[] entradaSegundaCamada) {
        double u = 0;
        for (int j = 0; j < conexoesSegundaCamada.length; j++) {
            u += entradaSegundaCamada[j] * conexoesSegundaCamada[j];
        }
        return getFuncaoTransferencia(u);
    }

    private double[] getEntradasSegundaCamada(double[] saidasPrimeiraCamada) {
        double[] entradaSegundaCamada = Arrays.copyOf(saidasPrimeiraCamada, saidasPrimeiraCamada.length + 1);
        entradaSegundaCamada[entradaSegundaCamada.length - 1] = 1.0;
        return entradaSegundaCamada;
    }
    /*#####################################################################
     ##### RETORNA SAIDA DO TREINAMENTO DA PRIMEIRA CAMADA             #####
     #####################################################################*/

    private double[] getSaidaTreinamentoPrimeiraCamada(double[][] conjuntoTreinamento, int i) {
        double[] saidasPrimeiraCamada = new double[nrNeuroniosPrimeiraCamada];
        for (int j = 0; j < conexoesPrimeiraCamada.length; j++) {
            double u = 0;
            for (int k = 0; k < conexoesPrimeiraCamada[j].length; k++) {
                u += conjuntoTreinamento[k][i] * conexoesPrimeiraCamada[j][k];
            }
            saidasPrimeiraCamada[j] = getFuncaoTransferencia(u);
        }
        return saidasPrimeiraCamada;
    }
    /*#####################################################################
     ##### RETORNA AS SAIDA DE CLASSIFICAÇÃO DA PRIMEIRA CAMADA        #####
     #####################################################################*/

    private double[] getSaidaClassificacaoPrimeiraCamada(double[] entrada) {
        double[] saidasPrimeiraCamada = new double[nrNeuroniosPrimeiraCamada];
        for (int j = 0; j < conexoesPrimeiraCamada.length; j++) {
            double u = 0;
            for (int k = 0; k < conexoesPrimeiraCamada[j].length; k++) {
                u += entrada[k] * conexoesPrimeiraCamada[j][k];
            }
            saidasPrimeiraCamada[j] = getFuncaoTransferencia(u);
        }
        return saidasPrimeiraCamada;
    }
    /*#####################################################################
     ##### RETROPROPAGA ERRO PELA PRIMEIRA CAMADA                      #####
     #####################################################################*/

    private void retropropagarErroPelaPrimeiraCamada(double[][] conjuntoTreinamento, double[] entradaSegundaCamada, double gradiente, int i) {
        for (int j = 0; j < entradaSegundaCamada.length - 1; j++) {
            double derivadaFuncaoTransferencia = entradaSegundaCamada[j] * (1.0 - entradaSegundaCamada[j]);
            double sigma = derivadaFuncaoTransferencia * (conexoesSegundaCamada[j] * gradiente);
            for (int k = 0; k < conexoesPrimeiraCamada[j].length; k++) {
                conexoesPrimeiraCamada[j][k] += fatorAdaptacao * sigma * conjuntoTreinamento[k][i];
            }
        }
    }
    /*#####################################################################
     ##### RETROPROPAGA ERRO PELA SEGUNDA CAMADA                       #####
     #####################################################################*/

    private void retropropagarErroPelaSegundaCamada(double[] entradaSegundaCamada, double gradiente) {
        for (int j = 0; j < conexoesSegundaCamada.length; j++) {
            conexoesSegundaCamada[j] += fatorAdaptacao * entradaSegundaCamada[j] * gradiente;
        }
    }
    /*#####################################################################
     ##### RETORNA O GRADIENTE DO ERRO                                 #####
     #####################################################################*/

    private double getGradienteDeRetropopagacao(double valorSaida, double erro) {
        return valorSaida * (1 - valorSaida) * erro;
    }
    /*#####################################################################
     ##### PROCESSA A SAIDA DO NEURONIO APLICANDO A FUNÇÃO DE TRANSF.  #####
     #####################################################################*/

    private double getFuncaoTransferencia(double u) {
        return 1.0 / (1.0 + Math.exp(-u));
    }
    /*#####################################################################
     ##### CALCULA A DIFERENÇA ENTRE O VALOR DE SAIDA E O DESEJADO     #####
     #####################################################################*/

    private double calcularErro(double[] valoresEsperados, double valorSaida, int i) {
        return valoresEsperados[i] - valorSaida;
    }
    /*#####################################################################
     ##### INICIALIZA AS CONEXÕES SINÁPTICAS DE CADA CAMADA            #####
     #####################################################################*/

    private void inicializarConexoesSinapticasDaRede() {
        inicializarConexoesDaPrimeiraCamada();
        inicializarConexoesDaSegundaCamada();
    }
    /*#####################################################################
     ##### INICIALIZA OS PESOS DA PRIMEIRA CAMADA COM PESOS ALEATÓRIOS  ####
     #####################################################################*/

    private void inicializarConexoesDaPrimeiraCamada() {
        conexoesPrimeiraCamada = new double[nrNeuroniosPrimeiraCamada][nrNeuroniosEntrada];
        for (int i = 0; i < conexoesPrimeiraCamada.length; i++) {
            for (int j = 0; j < conexoesPrimeiraCamada[i].length; j++) {
                conexoesPrimeiraCamada[i][j] = Math.random();
            }
        }
    }
    /*#####################################################################
     ##### INICIALIZA OS PESOS DA SEGUNDA CAMADA COM PESOS ALEATÓRIOS  #####
     #####################################################################*/

    private void inicializarConexoesDaSegundaCamada() {
        conexoesSegundaCamada = new double[nrNeuroniosPrimeiraCamada + 1];
        for (int i = 0; i < conexoesSegundaCamada.length; i++) {
            conexoesSegundaCamada[i] = Math.random();
        }
    }
    /*#####################################################################
     ##### IMPRIME OS VALORES DOS PESOS DA PRIMEIRA CAMADA             #####
     #####################################################################*/

    public void imprimirValoresConexoes() {
       // System.out.println("\n Conexoes da primeira camada:");
        for (int i = 0; i < conexoesPrimeiraCamada.length; i++) {
            for (int j = 0; j < conexoesPrimeiraCamada[i].length; j++) {
               // System.out.println(conexoesPrimeiraCamada[i][j] + " ");
            }
           // System.out.println("\n");
        }

      //  System.out.println("\n Conexoes da segunda camada:");
        for (int i = 0; i < conexoesSegundaCamada.length; i++) {
           // System.out.println(conexoesSegundaCamada[i] + " ");
        }

      //  System.out.println("\n\n");
    }
    /* RETORNA AS CONEXÕES DA PRIMEIRA CAMADA */

    public double[][] getConexoesPrimeiraCamada() {
        return conexoesPrimeiraCamada;
    }
    /* SETA AS CONECÕES DA PRIMEIRA CAMADA */

    public void setConexoesPrimeiraCamada(double[][] conexoesPrimeiraCamada) {
        this.conexoesPrimeiraCamada = conexoesPrimeiraCamada;
    }
    /* RETORNA AS CONEXÕES DA SEGUNDA CAMADA */

    public double[] getConexoesSegundaCamada() {
        return conexoesSegundaCamada;
    }
    /* SETA AS CONEXÕES DA SEGUNDA CAMADA */

    public void setConexoesSegundaCamada(double[] conexoesSegundaCamada) {
        this.conexoesSegundaCamada = conexoesSegundaCamada;
    }
    /* RETORNA O FATOR DE ADAPTAÇÃO */

    public double getFatorAdaptacao() {
        return fatorAdaptacao;
    }
    /* SETA O VALOR DE ADAPTAÇÃO CASO QUEIRA ALTERAR PELA INTERFACE DE TREINO */

    public RMLP setFatorAdaptacao(double fatorAdaptacao) {
        this.fatorAdaptacao = fatorAdaptacao;
        return this;
    }
    /* RETORNA O ERRO ACEITAVEL */

    public double getErroMinimo() {
        return erroMinimo;
    }
    /* SETA ERRO ACEITAVEL */

    public RMLP setErroMinimo(double erroMinimo) {
        this.erroMinimo = erroMinimo;
        return this;
    }
    /* RETORNA OS NEURONIOS DA PRIMEIRA CAMADA */

    public int getNrNeuroniosPrimeiraCamada() {
        return nrNeuroniosPrimeiraCamada;
    }
    /* DEFINE A QUANTIDADE DE NEURONIOS DA PRIMEIRA CAMADA */

    public RMLP setNrNeuroniosPrimeiraCamada(int nrNeuroniosPrimeiraCamada) {
        this.nrNeuroniosPrimeiraCamada = nrNeuroniosPrimeiraCamada;
        return this;
    }
    /* RETORNA O NUMERO DE NEURONIOS DE ENTRADA */

    public int getNrNeuroniosEntrada() {
        return nrNeuroniosEntrada;
    }
    /* DEFINE QUANTIDADE DE NEURONIOS DE ENTRADA */

    public RMLP setNrNeuroniosEntrada(int nrNeuroniosEntrada) {
        this.nrNeuroniosEntrada = nrNeuroniosEntrada;
        return this;
    }
    /* RETORNA QUANTIDADE DE EPOCAS */

    public int getEpocas() {
        return epocas;
    }
    /* RETORNA O NUMERO MÁXIMO DE EPOCAS */

    public int getNumeroMaximoEpocas() {
        return numeroMaximoEpocas;
    }
    /* ALTERA O NUMERO MAXIMO DE EPOCAS */
    public RMLP setNumeroMaximoEpocas(int numeroMaximoEpocas) {
        this.numeroMaximoEpocas = numeroMaximoEpocas;
        return this;
    }

    private void atualizarValorLabelEpocas(final int x) {
        TimerTask update = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    
                   TreinoController.lblEpocas.setText("Epoca.: "+x);
                  // System.gc();
                });
            }
        };
        
    }

}
