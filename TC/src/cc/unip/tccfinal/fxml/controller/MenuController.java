/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.controller;

import cc.unip.tccfinal.fxml.util.GeradorDeAmostras;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.hibernate.engine.spi.Status;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class MenuController implements Initializable {

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private TextField tfTotal;

    @FXML
    private Button btnGerar;

    @FXML
    private Button btnExcluir;

    @FXML
    private ProgressBar progressBar;

    private EquipamentoController controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new EquipamentoController();
        // TODO
        progressBar.setVisible(false);
        progressIndicator.setVisible(false);
        btnExcluir.setOnMouseClicked((MouseEvent event) -> {
            int totalReg = controller.deleteAll();
            new Alert(Alert.AlertType.INFORMATION, "Foram excluídos "+totalReg+" registros").showAndWait();
        });

        btnGerar.setOnMouseClicked((MouseEvent event) -> {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    new GeradorDeAmostras(Integer.parseInt(tfTotal.getText()), progressBar).gerarAmostras();
                }
            }).start();

            atualizarProgress();
        });
    }

    private void atualizarProgress() {
        progressBar.setVisible(true);
        progressIndicator.setVisible(true);
        btnGerar.setDisable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!GeradorDeAmostras.concluido) {
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                    }
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                            System.out.println(GeradorDeAmostras.porcentagemConclusao);
                            progressIndicator.setProgress(GeradorDeAmostras.porcentagemConclusao);
                            progressBar.setProgress(GeradorDeAmostras.porcentagemConclusao);
                        }
                    });

                }

                progressBar.setVisible(false);
                progressIndicator.setVisible(false);
                btnGerar.setDisable(false);
                
                progressBar.setProgress(0.0);
                progressIndicator.setProgress(0.0);
                
            }
        }).start();
        

    }

}
