package cc.unip.tccfinal.rede;

import java.util.Arrays;
import javafx.scene.control.TextArea;
import resources.background.IBackGround;

public class RedeNeuralMLP {

    private double fatorAdaptacao = 0.30;
    private double erroMinimo = 0.03;
     private int epocas = 0;
     private int numeroMaximoEpocas =10000000; 
    //private static double TAXA_APRENDIZADO = 0.30;

    private double[][] conexoesPrimeiraCamada;
    private double[] conexoesSegundaCamada;
    private int nrNeuroniosPrimeiraCamada;
    private int nrNeuroniosEntrada;
    private TextArea prompt = new TextArea();
    

    public RedeNeuralMLP(int nrNeuroniosPrimeiraCamada, int nrNeuroniosEntrada) {
        this.nrNeuroniosPrimeiraCamada = nrNeuroniosPrimeiraCamada;
        this.nrNeuroniosEntrada = nrNeuroniosEntrada;
        this.inicializarConexoesSinapticasDaRede();
        
        this.prompt.setBackground(IBackGround.BACKGROUND_BLACK);
        this.prompt.getStyleClass().add("prompt");
    }
    
    public TextArea textAreaPromptView(){
        return  this.prompt;
    }

    public void treinar(double[][] conjuntoTreinamento, double[] valoresEsperados) {
        updatePrompt("Iniciando Treino.....");
        
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
        }
        updatePrompt("Treino concluído!!!");
       
    }

    public int classificar(double[] entrada) {
        double y = 0;
        if (epocas > 9999999) {
            System.out.println("Nao foi possivel atingir um ponto de convergencia, verifique os parametros e a estrutura da rede.");
        } else {
            // System.out.printf("Treinamento realizado em %s epocas. \n", epocas);
            double[] saidasPrimeiraCamada = getSaidaClassificacaoPrimeiraCamada(entrada);
            double[] entradaSegundaCamada = getEntradasSegundaCamada(saidasPrimeiraCamada);
            y = propagarSinalPelaSegundaCamada(entradaSegundaCamada);
            System.out.println("Saidax: " + y);
            System.out.println("Saidax: " + Math.round(y));
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

    private void retropropagarErroPelaPrimeiraCamada(double[][] conjuntoTreinamento, double[] entradaSegundaCamada, double gradiente, int i) {
        for (int j = 0; j < entradaSegundaCamada.length - 1; j++) {
            double derivadaFuncaoTransferencia = entradaSegundaCamada[j] * (1.0 - entradaSegundaCamada[j]);
            double sigma = derivadaFuncaoTransferencia * (conexoesSegundaCamada[j] * gradiente);
            for (int k = 0; k < conexoesPrimeiraCamada[j].length; k++) {
                conexoesPrimeiraCamada[j][k] += fatorAdaptacao * sigma * conjuntoTreinamento[k][i];
            }
        }
    }

    private void retropropagarErroPelaSegundaCamada(double[] entradaSegundaCamada, double gradiente) {
        for (int j = 0; j < conexoesSegundaCamada.length; j++) {
            conexoesSegundaCamada[j] += fatorAdaptacao * entradaSegundaCamada[j] * gradiente;
        }
    }

    private double getGradienteDeRetropopagacao(double valorSaida, double erro) {
        return valorSaida * (1 - valorSaida) * erro;
    }

    private double getFuncaoTransferencia(double u) {
        return 1.0 / (1.0 + Math.exp(-u));
    }

    private double calcularErro(double[] valoresEsperados, double valorSaida, int i) {
        return valoresEsperados[i] - valorSaida;
    }

    private void inicializarConexoesSinapticasDaRede() {
        inicializarConexoesDaPrimeiraCamada();
        inicializarConexoesDaSegundaCamada();
    }

    private void inicializarConexoesDaPrimeiraCamada() {
        conexoesPrimeiraCamada = new double[nrNeuroniosPrimeiraCamada][nrNeuroniosEntrada];
        for (int i = 0; i < conexoesPrimeiraCamada.length; i++) {
            for (int j = 0; j < conexoesPrimeiraCamada[i].length; j++) {
                conexoesPrimeiraCamada[i][j] = Math.random();
            }
        }
    }

    private void inicializarConexoesDaSegundaCamada() {
        conexoesSegundaCamada = new double[nrNeuroniosPrimeiraCamada + 1];
        for (int i = 0; i < conexoesSegundaCamada.length; i++) {
            conexoesSegundaCamada[i] = Math.random();
        }
    }

    public void imprimirValoresConexoes() {
        System.out.println("\n Conexoes da primeira camada:");
        for (int i = 0; i < conexoesPrimeiraCamada.length; i++) {
            for (int j = 0; j < conexoesPrimeiraCamada[i].length; j++) {
                System.out.println(conexoesPrimeiraCamada[i][j] + " ");
            }
            System.out.println("\n");
        }

        System.out.println("\n Conexoes da segunda camada:");
        for (int i = 0; i < conexoesSegundaCamada.length; i++) {
            System.out.println(conexoesSegundaCamada[i] + " ");
        }

        System.out.println("\n\n");
    }

    public double[][] getConexoesPrimeiraCamada() {
        return conexoesPrimeiraCamada;
    }

    public void setConexoesPrimeiraCamada(double[][] conexoesPrimeiraCamada) {
        this.conexoesPrimeiraCamada = conexoesPrimeiraCamada;
    }

    public double[] getConexoesSegundaCamada() {
        return conexoesSegundaCamada;
    }

    public void setConexoesSegundaCamada(double[] conexoesSegundaCamada) {
        this.conexoesSegundaCamada = conexoesSegundaCamada;
    }

    public double getFatorAdaptacao() {
        return fatorAdaptacao;
    }

    public RedeNeuralMLP setFatorAdaptacao(double fatorAdaptacao) {
        this.fatorAdaptacao = fatorAdaptacao;
        return this;
    }

    public double getErroMinimo() {
        return erroMinimo;
    }

    public RedeNeuralMLP setErroMinimo(double erroMinimo) {
        this.erroMinimo = erroMinimo;
        return this;
    }

    public int getNrNeuroniosPrimeiraCamada() {
        return nrNeuroniosPrimeiraCamada;
    }

    public RedeNeuralMLP setNrNeuroniosPrimeiraCamada(int nrNeuroniosPrimeiraCamada) {
        this.nrNeuroniosPrimeiraCamada = nrNeuroniosPrimeiraCamada;
        return this;
    }

    public int getNrNeuroniosEntrada() {
        return nrNeuroniosEntrada;
    }

    public RedeNeuralMLP setNrNeuroniosEntrada(int nrNeuroniosEntrada) {
        this.nrNeuroniosEntrada = nrNeuroniosEntrada;
        return this;
    }

    public int getEpocas() {
        return epocas;
    }

    public int getNumeroMaximoEpocas() {
        return numeroMaximoEpocas;
    }

    public RedeNeuralMLP setNumeroMaximoEpocas(int numeroMaximoEpocas) {
        this.numeroMaximoEpocas = numeroMaximoEpocas;
        return this;
    }

    private void updatePrompt(String msg){
        prompt.appendText(msg.concat("\n"));
    }
     
    

}
