/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.controller.view.treino;

import cc.unip.tccfinal.rede.InterfaceTreinoRede;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToolBar;

/**
 *
 * @author Moisés
 */
public class IToolbar {

    private Button btnTreinarRede, btnFechar;
    private ToolBar toolbar;
    private InterfaceTreinoRede redeNeural;
    public IToolbar() {
        redeNeural = InterfaceTreinoRede.getInstance().setNrNeuroniosEntrada(3).setNrNeuroniosPrimeiraCamada(3).setPorcentagemTreinamento(50).prepararDados().build();
        btnTreinarRede = new Button("Treinar");
        btnFechar = new Button("Fechar");
        toolbar = new ToolBar(btnTreinarRede, btnFechar);
        toolbar.getStyleClass().add("background-app");
    }

    public ToolBar builder() {
        init();
        return toolbar;
    }

    private void init() {
        toolbar.setPrefHeight(70);

        btnTreinarRede.setContentDisplay(ContentDisplay.TOP);
        btnTreinarRede.setOnAction((ActionEvent event) -> {
            redeNeural.treinar();
        });

        btnFechar.setContentDisplay(ContentDisplay.TOP);
        btnFechar.setOnAction((ActionEvent event) -> {
        });

        addClasseBtn(btnFechar, btnTreinarRede);
    }

    private void addClasseBtn(Button... btn) {
        for (Button b : btn) {
            b.getStyleClass().add("btn-top");
            b.setPrefSize(170, 60);
        }
    }

}
