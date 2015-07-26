/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.controller;

import cc.unip.tccfinal.model.Sensor;
import cc.unip.tccfinal.serialport.Arduino;
import cc.unip.tccfinal.serialport.JavaSerialPort;
import cc.unip.tccfinal.util.CacheLeitura;
import cc.unip.tccfinal.util.CommandCode;
import eu.hansolo.enzo.lcd.Lcd;
import eu.hansolo.enzo.lcd.LcdBuilder;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import cc.unip.tccfinal.util.IBackGround;
import cc.unip.tccfinal.util.SystemInfo;
import eu.hansolo.enzo.gauge.OneEightyGauge;
import eu.hansolo.enzo.gauge.OneEightyGaugeBuilder;
import java.util.Calendar;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;

/**
 * FXML Controller class
 *
 * @author Moisés
 */
public class MonitorController implements Initializable {

    @FXML
    private VBox boxDireito;
    @FXML
    private VBox boxEsquerdo;
    @FXML
    private ToggleButton tbSalvarDados;

    @FXML
    private TableColumn<?, ?> tcStatusAquecedor;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private TableColumn<?, ?> tcLuminosidade;

    @FXML
    private TableColumn<?, ?> tcStatusArcondicionado;

    @FXML
    private ToggleButton tbIniciarLeituraPorta;

    @FXML
    private TableColumn<?, ?> tcTemperatura;

    @FXML
    private ToggleButton tbIluminacao;

    @FXML
    private ToggleButton tbAquecedor;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private TableColumn<?, ?> tcStatusUmidificador;

    @FXML
    private ToggleButton tbAutomatico;

    @FXML
    private ToggleButton tbArcondicionado;

    @FXML
    private TableColumn<?, ?> tcStatusIuminacao;

    @FXML
    private LineChart<?, ?> chart;

    @FXML
    private ToggleButton tbUmidificador;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<String, Number> tcUmidade;
    // ========================================================================
    private boolean estaLendoPorta, estaAutomatico, estaSalvandoNoBanco, estaOnAquecedor, estaOnArcondicionado, estaOnIluminacao, estaOnUmidificado;
    private Arduino arduino;
    private List<Sensor> dados = new ArrayList<>();

    XYChart.Series temperatura;
    XYChart.Series umidade;// = new XYChart.Series();
    XYChart.Series luminosidade;// = new XYChart.Series();

    SimpleDateFormat horaFormatada = new SimpleDateFormat("HH:mm:ss");

    private Lcd lcdTemperatura, lcdUmidade, lcdLuminosidade;
    private Background backDefaut = IBackGround.BACKGROUND_RED;
    private OneEightyGauge gauge;
    private long lastTimerCall;

    private void criarLCDs() {
        GridPane grid = new GridPane();
        // -----------------------------------------------------------------------------------------------
        lcdTemperatura = LcdBuilder.create()
                .prefWidth(1280).prefHeight(400).lcdDesign(Lcd.LcdDesign.FLAT_CLOUDS)
                .foregroundShadowVisible(true).crystalOverlayVisible(true).decimals(3)
                .animationDurationInMs(1500).minMeasuredValueDecimals(2).minMeasuredValueVisible(true)
                .maxMeasuredValueDecimals(2).maxMeasuredValueVisible(true).formerValueVisible(true)
                .threshold(26).thresholdVisible(true).trendVisible(true).numberSystemVisible(false)
                .valueFont(Lcd.LcdFont.LCD).animated(true).title("Temperatura").unit("ºC")
                .unitVisible(true).build();
        // -----------------------------------------------------------------------------------------------
        lcdLuminosidade = LcdBuilder.create()
                .prefWidth(1280).prefHeight(400).lcdDesign(Lcd.LcdDesign.FLAT_CLOUDS)
                .foregroundShadowVisible(true).crystalOverlayVisible(true).decimals(3)
                .animationDurationInMs(1500).minMeasuredValueDecimals(2).minMeasuredValueVisible(true)
                .maxMeasuredValueDecimals(2).maxMeasuredValueVisible(true).formerValueVisible(true)
                .threshold(26).thresholdVisible(true).trendVisible(true).numberSystemVisible(false)
                .valueFont(Lcd.LcdFont.LCD).animated(true).title("Luminosidade").unit("")
                .unitVisible(true).build();
        // -----------------------------------------------------------------------------------------------
        lcdUmidade = LcdBuilder.create()
                .prefWidth(1280).prefHeight(400).lcdDesign(Lcd.LcdDesign.FLAT_CLOUDS)
                .foregroundShadowVisible(true).crystalOverlayVisible(true).decimals(3)
                .animationDurationInMs(1500).minMeasuredValueDecimals(2).minMeasuredValueVisible(true)
                .maxMeasuredValueDecimals(2).maxMeasuredValueVisible(true).formerValueVisible(true)
                .threshold(26).thresholdVisible(true).trendVisible(true).numberSystemVisible(false)
                .valueFont(Lcd.LcdFont.LCD).animated(true).title("Umidade").unit("")
                .unitVisible(true).build();
        // -----------------------------------------------------------------------------------------------
        grid.add(lcdTemperatura, 0, 0);
        grid.add(lcdLuminosidade, 0, 1);
        grid.add(lcdUmidade, 0, 2);
        grid.setPrefHeight(300);
        boxDireito.getChildren().add(grid);
    }

    private void criarGaugeMemoria() {
        
        AnimationTimer timer;
        gauge = OneEightyGaugeBuilder.create()
                .animated(true).title("Memória").unit("MB")
                .maxValue(SystemInfo.getTotalMemoryInMB())
                .dynamicBarColor(true)
                .stops(new Stop(0.00, Color.BLUE),
                        new Stop(0.25, Color.CYAN),
                        new Stop(0.500, Color.LIME),
                        new Stop(0.75, Color.YELLOW),
                        new Stop(1.00, Color.RED))
                .build();
        boxEsquerdo.getChildren().add(gauge);

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now > lastTimerCall + 2_000_000_000l) {
                    gauge.setValue(SystemInfo.getUsedMemoryInMB());
                    gauge.setMaxValue(SystemInfo.getTotalMemoryInMB());                    
                    lastTimerCall = now;
                   
                }
            }
        };
        timer.start();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configBackgroundToogleButtons(tbIniciarLeituraPorta, tbAquecedor, tbArcondicionado, tbAutomatico, tbIluminacao, tbSalvarDados, tbUmidificador);
        setBackGroundToogleButtonsDisabled(tbIniciarLeituraPorta, tbAquecedor, tbArcondicionado, tbAutomatico, tbIluminacao, tbSalvarDados, tbUmidificador);
        tbIniciarLeituraPorta.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            estaLendoPorta = newValue;
            if (estaLendoPorta) {
                lerPortasArduino();
                startUpdateGraficoThread();
            } else {
                arduino.pararDeReceberDados();
            }

        });

        tbAquecedor.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            estaOnAquecedor = newValue;
            if (estaOnAquecedor) {
                arduino.write(CommandCode.LIGAR_AQUECEDOR);
            } else {
                arduino.write(CommandCode.DESLIGAR_AQUECEDOR);
            }
        });
        tbArcondicionado.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            estaOnArcondicionado = newValue;
            if (estaOnArcondicionado) {
                arduino.write(CommandCode.LIGAR_AR_CONDICIONADO);
            } else {
                arduino.write(CommandCode.DESLIGAR_AR_CONDICIONADO);
            }
        });
        tbAutomatico.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            estaAutomatico = newValue;
            if (estaAutomatico) {

            } else {

            }
        });
        tbIluminacao.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            estaOnIluminacao = newValue;
            if (estaOnIluminacao) {
                System.out.println(estaOnIluminacao);
                arduino.write(CommandCode.LIGAR_ILUMINACAO);
                System.out.println(CommandCode.LIGAR_ILUMINACAO);
            } else {
                arduino.write(CommandCode.DESLIGAR_ILUMINACAO);
                System.out.println(CommandCode.DESLIGAR_ILUMINACAO);
            }
        });
        tbSalvarDados.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            estaSalvandoNoBanco = newValue;
            if (estaSalvandoNoBanco) {
                arduino.salvarDadosNoBanco();
            } else {
                arduino.naoSalvarDadosNoBanco();
            }
        });
        tbUmidificador.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            estaOnUmidificado = newValue;
            if (estaOnUmidificado) {
                arduino.write(CommandCode.LIGAR_UMIDIFICADOR);
            } else {
                arduino.write(CommandCode.DESLIGAR_UMIDIFICADOR);
            }
        });
        dados = CacheLeitura.getInstance().getDados();
        criarLCDs();
        startGerenciadorBotoesThread();
        criarGaugeMemoria();
        

    }

    private void setBackGroundToogleButtonsDisabled(ToggleButton... tb) {
        for (ToggleButton t : tb) {
            t.setBackground(IBackGround.BACKGROUND_DARKCIAN);
            t.setText("OFF");
        }
    }

    private void setBackGroundToogleButtonsEnabled(ToggleButton... tb) {
        for (ToggleButton t : tb) {
            t.setBackground(backDefaut);
            t.setText("ON");
        }
    }

    private void configBackgroundToogleButtons(ToggleButton... tb) {
        for (ToggleButton t : tb) {
            t.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    setBackGroundToogleButtonsEnabled(t);
                } else {
                    setBackGroundToogleButtonsDisabled(t);
                }
            });
        }
    }

    private boolean lerPortasArduino() {

        List<String> choises = JavaSerialPort.getInstance().getPortas();
        if (!choises.isEmpty()) {
            ChoiceDialog<String> dialog = new ChoiceDialog<>(choises.get(0), choises);

            dialog.setTitle("Seleção de portas disponíveis");
            dialog.setHeaderText("Portas encontrada!!!");
            dialog.setContentText("Selecione a porta desejada!!!");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                arduino = new Arduino(result.get());
                arduino.lerPorta();
            }
            return true;
        } else {
            //desabilitarBotoes();
            new Alert(Alert.AlertType.INFORMATION, "Não foi possível detectar uma dispositivo com comunição serial puglado a esse PC!!", ButtonType.OK).show();
            return false;
        }

    }

    /*CHART*/
    private void startUpdateGraficoThread() {
        TimerTask update = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (estaLendoPorta) {
                        gerarGrafico();
                    }

                });
            }
        };
        new Timer().scheduleAtFixedRate(update, 0, 1000);
    }

    private void gerarGrafico() {
        if (dados.size() > 0) {
            chart.getData().clear();

            xAxis.setLabel("Sensores");
            chart.setTitle("Monitoramento de ambiente");

            XYChart.Series temperatura = new XYChart.Series();
            temperatura.setName("Temperatura");

            XYChart.Series umidade = new XYChart.Series();
            umidade.setName("Umidade");

            XYChart.Series luminosidade = new XYChart.Series();
            luminosidade.setName("Luminosidade");

            try {
                dados.stream().forEach((dd) -> {
                    try {
                        temperatura.getData().add(new XYChart.Data(horaFormatada.format(dd.getHoraAmostra()), dd.getTemperatura()));
                        umidade.getData().add(new XYChart.Data(horaFormatada.format(dd.getHoraAmostra()), dd.getUmidade()));
                        luminosidade.getData().add(new XYChart.Data(horaFormatada.format(dd.getHoraAmostra()), dd.getLuminosidade()));

                    } catch (Exception e) {
                        System.err.println(e);
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }
            chart.getData().addAll(temperatura, umidade, luminosidade);
            // --------------------------------------------------------------------------------------------------
            Sensor s = CacheLeitura.getInstance().getUltimoDadoRecebidoSensor();
            lcdTemperatura.setValue(s.getTemperatura());
            lcdLuminosidade.setValue(s.getLuminosidade());
            lcdUmidade.setValue(s.getUmidade());

        }
    }

    private void startGerenciadorBotoesThread() {
        TimerTask update = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    piscarBotoes();
                    if (backDefaut.equals(IBackGround.BACKGROUND_RED)) {
                        backDefaut = IBackGround.BACKGROUND_DARKCIAN;
                    } else {
                        backDefaut = IBackGround.BACKGROUND_RED;
                    }
                });
            }
        };
        new Timer().scheduleAtFixedRate(update, 0, 1000);
    }

    private void piscarBotoes() {
        if (estaLendoPorta) {
            alterarBackgraund(tbIniciarLeituraPorta, IBackGround.BACKGROUND_RED, IBackGround.BACKGROUND_DARKCIAN);
        }
        //EXECUTA OPERAÇÕES CASO O PROCESSAMENTO ESTEJA NO MODO AUTOMATICO
        if (estaAutomatico) {
            alterarBackgraund(tbAutomatico, IBackGround.BACKGROUND_RED, IBackGround.BACKGROUND_DARKCIAN);
        }
        //EXECUTA OPERAÇOES CASO ESTEJA SENDO FEITO COLETA DE AMOSTRAS
        if (estaSalvandoNoBanco) {
            alterarBackgraund(tbSalvarDados, IBackGround.BACKGROUND_RED, IBackGround.BACKGROUND_DARKCIAN);
        }
        //PISCA O BOTÃO BTN AQUECEDOR CASO ESTEJA ESTEJA LIGADO
        if (estaOnAquecedor) {
            alterarBackgraund(tbAquecedor, IBackGround.BACKGROUND_RED, IBackGround.BACKGROUND_DARKCIAN);
        }
        // PISCA O BOTÃO BTN ALARME INCENCIO CASO ESTEJA ON
        if (estaOnArcondicionado) {
            alterarBackgraund(tbArcondicionado, IBackGround.BACKGROUND_RED, IBackGround.BACKGROUND_DARKCIAN);
        }
        // PISCA O BOTÃO BTN ARCONDICIONADO CASO ESTAJA LIGADO
        if (estaOnIluminacao) {
            alterarBackgraund(tbIluminacao, IBackGround.BACKGROUND_RED, IBackGround.BACKGROUND_DARKCIAN);
        }
        // PISCA O BOTÃO BTN ALARME MAGNETISMO CASO ESTEJA ON
        if (estaOnUmidificado) {
            alterarBackgraund(tbUmidificador, IBackGround.BACKGROUND_RED, IBackGround.BACKGROUND_DARKCIAN);
        }
    }

    /*
     * #########################################################################
     * #################### ALTERA O BACKGROUND DOS BOTÕES #####################
     * #########################################################################
     */
    private void alterarBackgraund(ToggleButton botão, Background b1, Background b2) {
        if (botão.getBackground().equals(b1)) {
            botão.setBackground(b2);
        } else {
            botão.setBackground(b1);
        }

    }

}
