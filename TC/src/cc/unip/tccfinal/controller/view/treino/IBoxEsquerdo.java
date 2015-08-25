///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cc.unip.tccfinal.controller.view.treino;
//
//import cc.unip.tccfinal.rede.InterfaceTreinoRede;
//import cc.unip.tccfinal.util.Icon32;
//import javafx.beans.value.ObservableValue;
//import javafx.event.ActionEvent;
//import javafx.geometry.Insets;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.Slider;
//import javafx.scene.control.Tooltip;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//
///**
// *
// * @author Moisés
// */
//public class IBoxEsquerdo {
//
//    InterfaceTreinoRede iTreino;
//    private Treino treino;
//    private VBox boxEsquerdo;
//    private Label lblPorcentagemAmostra, lblNrNeuronioEntrada, lblNrNeuronioSegCamada, lblFatorAdptacao, lblErroMinimo, lblNumMaxEpoca;
//    private Slider sliderPorcentagemAmostra, sliderNrNeuronioEntrada, sliderNrNeuroniosPrimeiraCamada, slederFatorAdaptacao, sliderErroMinimo, sliderNumMaxEpoca;
//    int valorScrollEntrada, valorScrollPrimeiraCamada;
//    private int valorPorcentagemAmostra;
//    private double fatorAdptacao, erroMinimo;
//    int numeroMaximoEpocas;
//    Text textoInfo;
//    Button btnRestaurarDefault;
//
//    public IBoxEsquerdo(InterfaceTreinoRede iTreino, Treino treino) {
//        this.iTreino = iTreino;
//        this.treino = treino;
//    }
//
//    public IBoxEsquerdo build() {
//
//        boxEsquerdo = new VBox();
//        boxEsquerdo.setPrefWidth(300);
//        boxEsquerdo.setPadding(new Insets(10));
//
//        textoInfo = new Text();
//        textoInfo.setFill(Color.RED);
//        textoInfo.setFont(new Font(20));
//
//        lblPorcentagemAmostra = new Label("Porcentagem Amostra ", new ImageView(new Image(Icon32.getInstance().ICON_AR_COND_WHITE())));
//        lblNrNeuronioEntrada = new Label("Nº Neuronio Entrada", new ImageView(new Image(Icon32.getInstance().ICON_ADD_DATABASE())));
//        lblNrNeuronioSegCamada = new Label("Nº Neuronio 2º Camada", new ImageView(new Image(Icon32.getInstance().ICON_ADD_DATABASE())));
//        lblFatorAdptacao = new Label("Fator de Adaptacao", new ImageView(new Image(Icon32.getInstance().ICON_ADD_DATABASE())));
//        lblErroMinimo = new Label("Erro Minimo", new ImageView(new Image(Icon32.getInstance().ICON_ADD_DATABASE())));
//        lblNumMaxEpoca = new Label("Máximo de Épocas", new ImageView(new Image(Icon32.getInstance().ICON_ADD_DATABASE())));
//
//        btnRestaurarDefault = new Button("Default");
//        btnRestaurarDefault.setOnAction((ActionEvent event) -> {
//            restaurarValorDefault();
//        });
//
//        sliderNrNeuronioEntrada = new Slider(3, 10, 3);
//        sliderNrNeuronioEntrada.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//            valorScrollEntrada = newValue.intValue();
//
//            updateLabelInfo("Qt Neuro. Entrada:: ", valorScrollEntrada);
//            //System.out.println(valorScrollEntrada);
//        });
//
//        sliderNrNeuroniosPrimeiraCamada = new Slider(3, 10, 3);
//        sliderNrNeuroniosPrimeiraCamada.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//            valorScrollPrimeiraCamada = newValue.intValue();
//
//            updateLabelInfo("Qt Neuro. Camada 1:: ", valorScrollPrimeiraCamada);
//            //System.out.println(valorScrollSegCamada);
//        });
//
//        sliderPorcentagemAmostra = new Slider(1, 100, 30);
//        sliderPorcentagemAmostra.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//            valorPorcentagemAmostra = newValue.intValue();
//
//            updateLabelInfo("Porcentagem para Treino:: ", valorPorcentagemAmostra);
//            //System.out.println(valorPorcentagemAmostra);
//        });
//
//        slederFatorAdaptacao = new Slider(0.30, 1.0, 0.30);
//        slederFatorAdaptacao.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//            fatorAdptacao = newValue.doubleValue();
//
//            updateLabelInfo("Fator Adaptacao:: ", fatorAdptacao);
//            //System.out.println(fatorAdptacao);
//        });
//
//        sliderErroMinimo = new Slider(0.03, 1.0, 0.03);
//        sliderErroMinimo.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//            erroMinimo = newValue.doubleValue();
//            atualizarValoresParaAnalise();
//            updateLabelInfo("Erro Mínimo:: ", erroMinimo);
//            //System.out.println(erroMinimo);
//        });
//
//        sliderNumMaxEpoca = new Slider(1, 100000, 10000000);
//        sliderNumMaxEpoca.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//            numeroMaximoEpocas = newValue.intValue();
//            atualizarValoresParaAnalise();
//            sliderNumMaxEpoca.tooltipProperty().setValue(new Tooltip("" + numeroMaximoEpocas));
//            updateLabelInfo("Max Épocas:: ", numeroMaximoEpocas);
//            //System.out.println(numeroMaximoEpocas);
//        });
//        configSlider(sliderNrNeuronioEntrada, sliderNrNeuroniosPrimeiraCamada, sliderPorcentagemAmostra, slederFatorAdaptacao, sliderErroMinimo, sliderNumMaxEpoca);
//        boxEsquerdo.getChildren().addAll(
//                lblPorcentagemAmostra, sliderPorcentagemAmostra,
//                lblNrNeuronioEntrada, sliderNrNeuronioEntrada,
//                lblNrNeuronioSegCamada, sliderNrNeuroniosPrimeiraCamada,
//                lblErroMinimo, sliderErroMinimo,
//                lblFatorAdptacao, slederFatorAdaptacao,
//                lblNumMaxEpoca, sliderNumMaxEpoca,
//                textoInfo, btnRestaurarDefault
//        );
//        restaurarValorDefault();
//        return this;
//    }
//
//    public void atualizarValoresParaAnalise() {
//        iTreino.setNumeroMaximoEpocas(numeroMaximoEpocas);
//        iTreino.setErroMinimo(erroMinimo);
//        iTreino.setFatorAdaptacao(fatorAdptacao);
//        iTreino.setPorcentagemTreinamento(valorPorcentagemAmostra);
//        iTreino.setNrNeuroniosEntrada(valorScrollEntrada);
//        iTreino.setNrNeuroniosPrimeiraCamada(valorScrollPrimeiraCamada);
//    }
//
//    private void updateLabelInfo(String msg, Number value) {
//        String mensagem = msg.concat(String.valueOf(value));
//        textoInfo.setText(mensagem.substring(0, mensagem.length() > 29 ? 29 : mensagem.length()));
//
//    }
//
//    private void restaurarValorDefault() {
//        fatorAdptacao = 0.30;
//        slederFatorAdaptacao.adjustValue(fatorAdptacao);
//        //iTreino.setFatorAdaptacao(fatorAdptacao);
//
//        numeroMaximoEpocas = 100000;
//        sliderNumMaxEpoca.adjustValue(numeroMaximoEpocas);
//       // iTreino.setNumeroMaximoEpocas(numeroMaximoEpocas);
//
//        erroMinimo = 0.01;
//        sliderErroMinimo.adjustValue(erroMinimo);
//       // iTreino.setErroMinimo(erroMinimo);
//
//        valorPorcentagemAmostra = 30;
//        sliderPorcentagemAmostra.adjustValue(valorPorcentagemAmostra);
//       // iTreino.setPorcentagemTreinamento(valorPorcentagemAmostra);
//
//        valorScrollEntrada = 3;
//        sliderNrNeuronioEntrada.adjustValue(valorScrollEntrada);
//       // iTreino.setNrNeuroniosEntrada(valorScrollEntrada);
//
//        valorScrollPrimeiraCamada = 3;
//        sliderNrNeuroniosPrimeiraCamada.adjustValue(valorScrollPrimeiraCamada);
//       // iTreino.setNrNeuroniosPrimeiraCamada(valorScrollPrimeiraCamada);
//        atualizarValoresParaAnalise();
//    }
///* copiado*/
//    private void configSlider(Slider... s) {
//        for (Slider sl : s) {
//
//            sl.setShowTickLabels(true);
//            sl.setShowTickMarks(true);
//            sl.setMajorTickUnit(sl.getMax() / 2);
//            sl.setMinorTickCount(5);
//            sl.setBlockIncrement(sl.getMax() / 10);
//            sl.setBlockIncrement(sl.getMax() / 100);
//        }
//    }
//   
//
//    public VBox getBoxEsquerdo() {
//        return boxEsquerdo;
//    }
//
//    public Slider getSliderPorcentagemAmostra() {
//        return sliderPorcentagemAmostra;
//    }
//
//    public Label getLblPorcentagemAmostra() {
//        return lblPorcentagemAmostra;
//    }
//
//    
//
//}
