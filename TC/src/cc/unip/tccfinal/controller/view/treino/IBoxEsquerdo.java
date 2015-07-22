/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.controller.view.treino;

import cc.unip.tccfinal.rede.InterfaceTreinoRede;
import cc.unip.tccfinal.util.Icon32;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Moisés
 */
public class IBoxEsquerdo {

    InterfaceTreinoRede interfaceTreino;

    private VBox boxEsquerdo;
    private Label lblPorcentagemAmostra, lblNrNeuronioEntrada, lblNrNeuronioSegCamada, lblFatorAdptacao, lblErroMinimo, lblNumMaxEpoca;
    private Slider sliderPorcentagemAmostra, sliderNrNeuronioEntrada, sliderNrNeuroniosPrimeiraCamada, slederFatorAdaptacao, sliderErroMinimo, sliderNumMaxEpoca;
    int valorScrollEntrada, valorScrollPrimeiraCamada, valorPorcentagemAmostra;
    private double fatorAdptacao, erroMinimo;
    int numeroMaximoEpocas;
    Text textoInfo;

    public VBox build() {
        interfaceTreino = InterfaceTreinoRede.getInstance();
        boxEsquerdo = new VBox();
        boxEsquerdo.setPrefWidth(300);
        boxEsquerdo.setPadding(new Insets(10));

        textoInfo = new Text();
        textoInfo.setFill(Color.RED);
        textoInfo.setFont(new Font(20));

        lblPorcentagemAmostra = new Label("Porcentagem Amostra ", new ImageView(new Image(Icon32.getInstance().ICON_AR_COND_WHITE())));
        lblNrNeuronioEntrada = new Label("Nº Neuronio Entrada", new ImageView(new Image(Icon32.getInstance().ICON_ADD_DATABASE())));
        lblNrNeuronioSegCamada = new Label("Nº Neuronio 2º Camada", new ImageView(new Image(Icon32.getInstance().ICON_ADD_DATABASE())));
        lblFatorAdptacao = new Label("Fator de Adaptacao", new ImageView(new Image(Icon32.getInstance().ICON_ADD_DATABASE())));
        lblErroMinimo = new Label("Erro Minimo", new ImageView(new Image(Icon32.getInstance().ICON_ADD_DATABASE())));
        lblNumMaxEpoca = new Label("Máximo de Épocas", new ImageView(new Image(Icon32.getInstance().ICON_ADD_DATABASE())));
        
        sliderNrNeuronioEntrada = new Slider(3, 10, 3);
        sliderNrNeuronioEntrada.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            valorScrollEntrada = newValue.intValue();
            interfaceTreino.setNrNeuroniosEntrada(valorScrollEntrada);
            updateLabelInfo("Qt Neuro. Entrada:: ", valorScrollEntrada);
            //System.out.println(valorScrollEntrada);
        });

        sliderNrNeuroniosPrimeiraCamada = new Slider(3, 10, 3);
        sliderNrNeuroniosPrimeiraCamada.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            valorScrollPrimeiraCamada = newValue.intValue();
            interfaceTreino.setNrNeuroniosPrimeiraCamada(valorScrollPrimeiraCamada);
            updateLabelInfo("Qt Neuro. Camada 1:: ", valorScrollPrimeiraCamada);
            //System.out.println(valorScrollSegCamada);
        });

        sliderPorcentagemAmostra = new Slider(1, 100, 30);
        sliderPorcentagemAmostra.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            valorPorcentagemAmostra = newValue.intValue();
            interfaceTreino.setPorcentagemTreinamento(valorPorcentagemAmostra);
            updateLabelInfo("Porcentagem para Treino:: ", valorPorcentagemAmostra);
            //System.out.println(valorPorcentagemAmostra);
        });

        slederFatorAdaptacao = new Slider(0.30, 1.0, 0.30);
        slederFatorAdaptacao.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            fatorAdptacao = newValue.doubleValue();
            interfaceTreino.setFatorAdaptacao(fatorAdptacao);
            updateLabelInfo("Fator Adaptacao:: ", fatorAdptacao);
            //System.out.println(fatorAdptacao);
        });

        sliderErroMinimo = new Slider(0.03, 1.0, 0.03);
        sliderErroMinimo.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            erroMinimo = newValue.doubleValue();
            interfaceTreino.setErroMinimo(erroMinimo);
            updateLabelInfo("Erro Mínimo:: ", erroMinimo);
            //System.out.println(erroMinimo);
        });

        sliderNumMaxEpoca = new Slider(1, 10000000, 10000000);
        sliderNumMaxEpoca.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            numeroMaximoEpocas = newValue.intValue();
            interfaceTreino.setNumeroMaximoEpocas(numeroMaximoEpocas);
            sliderNumMaxEpoca.tooltipProperty().setValue(new Tooltip("" + numeroMaximoEpocas));
            updateLabelInfo("Max Épocas:: ", numeroMaximoEpocas);
            //System.out.println(numeroMaximoEpocas);
        });
        configSlider(sliderNrNeuronioEntrada, sliderNrNeuroniosPrimeiraCamada, sliderPorcentagemAmostra, slederFatorAdaptacao, sliderErroMinimo, sliderNumMaxEpoca);
        boxEsquerdo.getChildren().addAll(
                lblPorcentagemAmostra, sliderPorcentagemAmostra,
                lblNrNeuronioEntrada, sliderNrNeuronioEntrada,
                lblNrNeuronioSegCamada, sliderNrNeuroniosPrimeiraCamada,
                lblErroMinimo, sliderErroMinimo,
                lblFatorAdptacao, slederFatorAdaptacao,
                lblNumMaxEpoca, sliderNumMaxEpoca,
                textoInfo
        );
        return boxEsquerdo;
    }

    private void updateLabelInfo(String msg, Number value) {
        String mensagem = msg.concat(String.valueOf(value));
        textoInfo.setText(mensagem.substring(0, mensagem.length() > 29 ? 29 : mensagem.length()));

    }

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

}
