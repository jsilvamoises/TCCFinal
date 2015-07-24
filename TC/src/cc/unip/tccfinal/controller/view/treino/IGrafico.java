/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.controller.view.treino;

import cc.unip.tccfinal.rede.InterfaceTreinoRede;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;


/**
 *
 * @author Moisés
 */
public class IGrafico {
    private Treino treino;
    private List<DadosGraficoErrAce> dados = new ArrayList<>();
    private PieChart chartErrosAcertos;
    private HBox box;
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    InterfaceTreinoRede iTreino;
    public IGrafico(InterfaceTreinoRede iTreino, Treino treino) {
        this.iTreino = iTreino;
        this.treino = treino;
    }
    
    public IGrafico build(){
        box = new HBox();
        box.setPrefHeight(200);
        chartErrosAcertos = new PieChart();
        chartErrosAcertos.setLegendSide(Side.LEFT);
        chartErrosAcertos.setTitle("Resultado de verificação");
        box.getChildren().add(chartErrosAcertos);
        dados.add(new DadosGraficoErrAce("Erros", iTreino.getAcertoClassificacao()));
        dados.add(new DadosGraficoErrAce("Acertos", iTreino.getErroClassificacao()));
        setPieValue();
        return this;
    }

    public InterfaceTreinoRede getiTreino() {
        return iTreino;
    }
    public void clearPieValues(){
        dados.clear();
    }
    public void setPieValue(){
        pieChartData.clear();
        for(DadosGraficoErrAce d:dados){
            pieChartData.add(new PieChart.Data(d.getNome(), d.getValor()));
        }
        
        chartErrosAcertos.setData(pieChartData);
    }


    public HBox getBox() {
        return box;
    }
    public void addItem(DadosGraficoErrAce d){
        dados.add(d);
    }
    
    public void addItem(DadosGraficoErrAce... d){
        for(DadosGraficoErrAce dg:d){
            dados.add(dg);
        }
        setPieValue();
        
    }
    
//    private void startUpdateGraficoThread() {
//        TimerTask update = new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(() -> {
//                    //gerarGrafico();
//                });
//            }
//        };
//        new Timer().scheduleAtFixedRate(update, 0, 500);
//    }
    
    public void gerarGrafico() {
        treino.getBoxEsquerdo().getSliderPorcentagemAmostra().adjustValue(iTreino.getPorcentTreino());
        iTreino.setPorcentagemTreinamento(30);
        DadosGraficoErrAce dg1,dg2 = null;// =  new DadosGraficoErrAce();
        if (iTreino.getErroClassificacao() > 0 || iTreino.getAcertoClassificacao() > 0) {
            dg1 = new DadosGraficoErrAce("Erros", iTreino.getErroClassificacao());
            dg2 = new DadosGraficoErrAce("Acertos", iTreino.getAcertoClassificacao());
            this.clearPieValues();
            this.addItem(dg1, dg2);
        }

    }
    
    
}
