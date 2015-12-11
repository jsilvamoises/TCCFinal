/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.controller;

//import cc.unip.tccfinal.controller.view.treino.DadosGraficoErrAce;
import cc.unip.tccfinal.fxml.main.Menu;
import cc.unip.tccfinal.fxml.model.DadosGraficoErrAce;
import cc.unip.tccfinal.fxml.rede.InterfaceTreinoRede;
import cc.unip.tccfinal.fxml.rede.RMLP;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Moisés
 */
public class TreinoController implements Initializable {

    

    // VARIAVEIS FXML
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Button btnTreinarRede;
   
    @FXML
    private Label lblNeuroniosEntrada;
    @FXML
    private VBox boxInferior;
    @FXML
    private Label lblNumeroMaximoEpoca;
    @FXML
    private Slider sliderPorcentagemAmostra;
    @FXML
    private Slider sliderErroMinimo;
    @FXML
    private Slider sliderNeuroniosEntrada;
    @FXML
    private Slider sliderNeuroniosSegundaCamada;
    @FXML
    private ToolBar toolBar;
    @FXML
    private Label lblPorcentagemAmostra;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private Slider sliderNumeroMaximoEpoca;
    @FXML
    private Label lblFatorAdaptacao;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Slider sliderFatorAdaptacao;
    @FXML
    private Button btnRestaurarPadrao;
    @FXML
    private Label lblErroMinimo;
    @FXML
    private PieChart pieChart;
    @FXML
    private PieChart pieChartAmostras;
    @FXML
    private VBox boxEsquerdo;
    @FXML
    private Label lblNeuroniosSegundaCamada;    
    @FXML
    private  CheckBox cbAprenderComErros;
    
     @FXML
    private Button btnGerarDados;
    private static boolean aprenderComErro;
    
    
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    private List<DadosGraficoErrAce> dados = new ArrayList<>();
    InterfaceTreinoRede iTreino;

    private int qtdNeuroniosEntrada, qtdNeuroniosSegundaCamada, qtdPorcentagemAmostra, numeroMaximoEpocas;
    private double fatorAdaptacao, erroMinimo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iTreino = InterfaceTreinoRede.getInstance();
        configSliders();//Define o comportamento dos Sliders
        configToolbar();// Configura os itens da Toolbar
        btnRestaurarPadrao.setOnAction((ActionEvent event) -> {
            setDefaultValues();
        });
        
        btnGerarDados.setOnAction((ActionEvent event)->{
            new Menu().start(new Stage());
        });
        
        pieChartAmostras.setTitle("Amostras X Treino");
        barChart.setTitle("Laços executados para Treino");
        

    }
    /*##########################################################################
     ########  SLIDERS CONTROLLER AÇÕES ########################################
     #########################################################################*/

    private void configSliders() {
        sliderErroMinimo.setMin(0.03);
        sliderErroMinimo.setMax(1.0);
        sliderErroMinimo.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            erroMinimo = newValue.doubleValue();
            updateLabel();
        });

        sliderFatorAdaptacao.setMin(0.3);
        sliderFatorAdaptacao.setMax(1.0);
        sliderFatorAdaptacao.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            fatorAdaptacao = newValue.doubleValue();
            updateLabel();
        });

        sliderNeuroniosEntrada.setMin(3);
        sliderNeuroniosEntrada.setMax(10);
        sliderNeuroniosEntrada.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            qtdNeuroniosEntrada = newValue.intValue();
            updateLabel();
        });

        sliderNeuroniosSegundaCamada.setMin(3);
        sliderNeuroniosSegundaCamada.setMax(10);
        sliderNeuroniosSegundaCamada.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            qtdNeuroniosSegundaCamada = newValue.intValue();
            updateLabel();
        });

        sliderNumeroMaximoEpoca.setMin(3);
        sliderNumeroMaximoEpoca.setMax(100000);
        sliderNumeroMaximoEpoca.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            numeroMaximoEpocas = newValue.intValue();
            updateLabel();
        });

        sliderPorcentagemAmostra.setMin(1);
        sliderPorcentagemAmostra.setMax(100);
        sliderPorcentagemAmostra.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            qtdPorcentagemAmostra = newValue.intValue();
            updateLabel();
        });
        
        cbAprenderComErros.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                aprenderComErro = newValue;
            }
        });
        cbAprenderComErros.setVisible(false);
        setDefaultValues();//Aplica valores padrão para o slider
        configSlider(sliderErroMinimo, sliderFatorAdaptacao, sliderNeuroniosEntrada, sliderNeuroniosSegundaCamada, sliderNumeroMaximoEpoca, sliderPorcentagemAmostra);
    }

    /**
     * APLICA VALOR PADRÃO AOS SLIDERS
     */
    private void setDefaultValues() {
        erroMinimo = 0.03;
        fatorAdaptacao = 0.3;
        qtdNeuroniosEntrada = 3;
        qtdNeuroniosSegundaCamada = 3;
        numeroMaximoEpocas = 100000;
        qtdPorcentagemAmostra = 70;
        updateValoresSlider();
    }

    /**
     * ATUALIZA O VALOR DOS SLIDERS
     */
    private void updateValoresSlider() {
        sliderErroMinimo.adjustValue(erroMinimo);
        sliderFatorAdaptacao.adjustValue(fatorAdaptacao);
        sliderNeuroniosEntrada.adjustValue(qtdNeuroniosEntrada);
        sliderNeuroniosSegundaCamada.adjustValue(qtdNeuroniosSegundaCamada);
        sliderNumeroMaximoEpoca.adjustValue(numeroMaximoEpocas);
        sliderPorcentagemAmostra.adjustValue(qtdPorcentagemAmostra);
        updateLabel();
    }

    /**
     * ATUALIZA OS LABELS COM OS VALORES ATUAIS
     */
    private void updateLabel() {
        lblErroMinimo.setText("Erro Mínimo:: " + erroMinimo);
        lblFatorAdaptacao.setText("Fator de Adaptação:: " + fatorAdaptacao);
        lblNeuroniosEntrada.setText("Nº Neurônios Entrada:: " + qtdNeuroniosEntrada);
        lblNeuroniosSegundaCamada.setText("Nº Neurônios 2º Camada:: " + qtdNeuroniosSegundaCamada);
        lblNumeroMaximoEpoca.setText("Número Máximo de Épocas:: " + numeroMaximoEpocas);
        lblPorcentagemAmostra.setText("Porcentagem para Treino:: " + qtdPorcentagemAmostra);
    }
    
    private void atualizarSliderComDadosDoTreino(){
        qtdPorcentagemAmostra = iTreino.getPorcentTreino();
        updateValoresSlider();
    }

    /**
     * Configura os Sliders com valores default
     */
    private void configSlider(Slider... s) {
        for (Slider sl : s) {
            sl.setShowTickLabels(true);
            sl.setShowTickMarks(true);
            sl.setMajorTickUnit(sl.getMax() / 2);
            sl.setMinorTickCount(5);
            sl.setBlockIncrement(sl.getMax() / 10);
            sl.setBlockIncrement(sl.getMax() / 100);
        }
    }
    /*##########################################################################
     ########  TOOLBAR                   ########################################
     #########################################################################*/

    private void configToolbar() {
        btnTreinarRede.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                iTreino.setErroMinimo(erroMinimo)
                        .setFatorAdaptacao(fatorAdaptacao)
                        .setNrNeuroniosEntrada(qtdNeuroniosEntrada)
                        .setNrNeuroniosPrimeiraCamada(qtdNeuroniosSegundaCamada)            
                        .setNumeroMaximoEpocas(numeroMaximoEpocas)
                        .setPorcentagemTreinamento(qtdPorcentagemAmostra)
                        .treinar();
                
                gerarGraficoPizza();
                gerarGraficoBarras();
                gerarGraficoAmostras();
                //configurarGraficos(pieChart, pieChartAmostras);
            }
        });
    }

    //GRAFICOS
    
    private void configurarGraficos(PieChart... chart){
        Label caption = new Label("");
        for(PieChart pc:chart){
            for(PieChart.Data data:pc.getData()){
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()) );
                });
            }
        }
    }
    private void gerarGraficoPizza() {
        dados.clear();
        dados.add(new DadosGraficoErrAce("Erros [ "+ iTreino.getErroClassificacao()+" ]", iTreino.getErroClassificacao()));
        dados.add(new DadosGraficoErrAce("Acertos [ "+iTreino.getAcertoClassificacao()+" ]", iTreino.getAcertoClassificacao()));
        
        pieChart.getData().clear();
        pieChartData.clear();
        for (DadosGraficoErrAce d : dados) {
            pieChartData.add(new PieChart.Data(d.getNome(), d.getValor()));
        }
        pieChart.setData(pieChartData);

    }
    
    private void gerarGraficoBarras(){
        barChart.getData().clear();
      //  for(DadosGraficoBarras dgb:iTreino.getDadosGraficoBarras()){
           
           
            XYChart.Series series = new XYChart.Series();
            //series.setName("Laço "+dgb.getLaco());
            series.setName("Épocas");
           
           // series.getData().add(new XYChart.Data<>("Acertos [ "+dgb.getAcerto()+" ]", dgb.getAcerto()));
          //  series.getData().add(new XYChart.Data<>("Erros [ "+dgb.getErro()+" ]", dgb.getErro()));   
            series.getData().add(new XYChart.Data<>("Num. Max. Épocas "+RMLP.numeroMaximoEpocas, RMLP.numeroMaximoEpocas));
            series.getData().add(new XYChart.Data<>("Épocas "+RMLP.epocas, RMLP.epocas));
            barChart.getData().add(series);
      //  }
        atualizarSliderComDadosDoTreino();       
        
    }
    
    private void gerarGraficoAmostras(){
        pieChartAmostras.getData().clear();
        PieChart.Data amostraTreino = new PieChart.Data("Amostra Treino [ "+iTreino.getListaTreinoSize()+" ]", iTreino.getListaTreinoSize());        
        PieChart.Data amostraTestada = new PieChart.Data("Amostras Testadas [ "+ iTreino.getListaVerificacaoSize()+" ]", iTreino.getListaVerificacaoSize());
        
       pieChartAmostras.getData().addAll(amostraTreino,amostraTestada);
    }
    
    
    public static boolean aprenderComErros(){        
        return aprenderComErro;
    }
}
