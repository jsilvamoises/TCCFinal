/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.controller;

import cc.unip.tccfinal.fxml.model.TableDados;
import cc.unip.tccfinal.fxml.EnumEquipamentos;
import cc.unip.tccfinal.fxml.main.Treino;
import cc.unip.tccfinal.fxml.model.Sensor;
import cc.unip.tccfinal.fxml.rede.InterfaceTreinoRede;
import cc.unip.tccfinal.fxml.serialport.Arduino;
import cc.unip.tccfinal.fxml.serialport.JavaSerialPort;
import cc.unip.tccfinal.fxml.util.CacheLeitura;
import cc.unip.tccfinal.fxml.util.CommandCode;
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
import cc.unip.tccfinal.fxml.util.IBackGround;
import cc.unip.tccfinal.fxml.util.SystemInfo;
import eu.hansolo.enzo.gauge.OneEightyGauge;
import eu.hansolo.enzo.gauge.OneEightyGaugeBuilder;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Moisés
 */
public class MonitorController implements Initializable {

    @FXML
    private Button btSubmeter;
    @FXML
    private Text lblResultado;
    @FXML
    private ComboBox<?> cbEquipamento;
    @FXML
    private TextField tfValorSensor;
    @FXML
    private VBox boxDireito;
    @FXML
    private VBox boxEsquerdo;
    @FXML
    private ToggleButton tbSalvarDados;
    @FXML
    private TableColumn tcStatusAquecedor;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private TableColumn tcLuminosidade;
    @FXML
    private TableColumn tcStatusArcondicionado;
    @FXML
    private ToggleButton tbIniciarLeituraPorta;
    @FXML
    private TableColumn tcTemperatura;
    @FXML
    private ToggleButton tbIluminacao;
    @FXML
    private ToggleButton tbAquecedor;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private TableColumn tcStatusUmidificador;
    @FXML
    private ToggleButton tbAutomatico;
    @FXML
    private ToggleButton tbArcondicionado;
    @FXML
    private TableColumn tcStatusIuminacao;
    @FXML
    private LineChart<?, ?> chart;
    @FXML
    private ToggleButton tbUmidificador;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<String, Number> tcUmidade;
    @FXML
    private Button btnTreinar;
    // ========================================================================
    private boolean estaLendoPorta, estaAutomatico, estaSalvandoNoBanco, estaOnAquecedor, estaOnArcondicionado, estaOnIluminacao, estaOnUmidificado;
    private Arduino arduino;
    private List<Sensor> dados = new ArrayList<>();
    private Sensor sensor;
    double idEquipamento, valorSensor;
    XYChart.Series temperatura;
    XYChart.Series umidade;// = new XYChart.Series();
    XYChart.Series luminosidade;// = new XYChart.Series();

    SimpleDateFormat horaFormatada = new SimpleDateFormat("HH:mm:ss");

    private Lcd lcdTemperatura, lcdUmidade, lcdLuminosidade;
    private Background backDefaut = IBackGround.BACKGROUND_RED;
    private OneEightyGauge gauge;
    private long lastTimerCall;

    private ObservableList<TableDados> obDados;

    private InterfaceTreinoRede itr;
    private static final double BIAS = 1.0;
    /**
     * Cria os LCDs de monitoramento de coleta de dados
     */
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
    /**
     * Inicializa o combobox
     */
    private void inicializarComboBox() {
        ObservableList options = FXCollections.observableArrayList();
        ControllerEnumIdEquipamentos controller = new ControllerEnumIdEquipamentos();
        
        for (EnumEquipamentos id : controller.getIds()) {
            options.add(id.getValor());
            System.out.println(id.name());
        }
        
        options.sorted();
        cbEquipamento.setItems(options);
        cbEquipamento.valueProperty().addListener((ObservableValue<? extends Object> observable, Object oldValue, Object newValue) -> {
            idEquipamento = (double) newValue;
        });
    }
    /**
     * Cria gauge que irá monitorar o uso de memória.
     */
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
                itr = InterfaceTreinoRede.getInstance();
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

        btSubmeter.setOnMouseClicked((MouseEvent event) -> {
            double analizar[] = new double[3];
            //idEquipamento, valorSensor;
            if (idEquipamento > 0) {
                analizar[0] = idEquipamento;
                analizar[1] = Double.parseDouble(tfValorSensor.getText()) / 100;
                analizar[2] = (double) 1.0;//
            }
            lblResultado.setText("Res:: " + InterfaceTreinoRede.getInstance().classificar(analizar));
            System.out.println("Clicou né");
        });

        btnTreinar.setOnMouseClicked((MouseEvent event) -> {
            new Treino().start(new Stage());
        });
        //itr = InterfaceTreinoRede.getInstance();
        dados = CacheLeitura.getInstance().getDados();
        inicializarComboBox();
        criarLCDs();
        startGerenciadorBotoesThread();
        criarGaugeMemoria();
        startProcessarAutomatico();// INICIA VERIFICAÇÃO SE É PARA PROCESSAR OS DADOS VINDOS DO SENSOR
        startUpdateTableThread();
    }
    /**
     * Altera o background de tooglebutton
     * @param tb 
     */
    private void setBackGroundToogleButtonsDisabled(ToggleButton... tb) {
        for (ToggleButton t : tb) {
            t.setBackground(IBackGround.BACKGROUND_DARKCIAN);
            t.setText("OFF");
        }
    }
    /**
     * Altera o background de tooglebutton
     * @param tb 
     */
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
    // -------------------------------------------------------------------------
    /**
     * Inicia o processo de leitura de portas que o arduino está enviado dados
     * @return 
     */
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
    // -------------------------------------------------------------------------
    /**
     * Inicia a thread que irá atualizar a interfaçe como os dados do gráfico
     */
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
    // -------------------------------------------------------------------------
    /**
     * Gera o gráfico com os utimos dados coletados
     */
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
    // -------------------------------------------------------------------------
    /**
     * Inicia a thread que irá ficar analisando se precisa alterar a cor dos bo
     * tões
     */
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
    // -------------------------------------------------------------------------
    /**
     * Altera a cor dos botões que estão ativos no momento, 
     */
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

    private void startProcessarAutomatico() {
        TimerTask update = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    processarAutomatico();
                });
            }
        };
        new Timer().scheduleAtFixedRate(update, 0, 1000);
    }

    // -------------------------------------------------------------------------
    /* 0.1 ILUMINACAO
     * 0.2 AR CONDICIONADO
     * 0.3 AQUECEDOR
     * 0.4 UMIDIFICADOR
     */
    /**
     * Inicia o processamento automático
     */
    private void processarAutomatico() {
        if (estaAutomatico) {
            if (!itr.isIsTreinada()) {

            } else {
                sensor = CacheLeitura.getInstance().getUltimoDadoRecebidoSensor();
                double idSensor[] = {0.1, 0.2, 0.3, 0.4};
                double valoresColetados[] = {sensor.getLuminosidade() / 100, sensor.getTemperatura() / 100, sensor.getTemperatura() / 100, sensor.getUmidade() / 100};
                for (int i = 0; i < 4; i++) {
                    double teste[] = {idSensor[i], valoresColetados[i], BIAS};
                    System.out.println(teste[0] + "," + teste[1] + "," + teste[2]);
                    setEstadoEquipamento(teste, i + 1);
                }
            }
        }
    }

    // -------------------------------------------------------------------------
    /**
     * Modifica os estados dos equipamentos em tempo de execução de acordo com
     * valores retornados do processamento automatico
     */
    private void setEstadoEquipamento(double[] valores, int idEquipamento) {
        switch (idEquipamento) {
            case 1: //ILUMINAÇÃO
                if (itr.classificar(valores) == 1) {
                    estaOnIluminacao = true;
                    arduino.write(101);
                    tbIluminacao.setBackground(backDefaut);
                    tbIluminacao.setText("ON");
                } else {
                    estaOnIluminacao = false;
                    arduino.write(100);
                    tbIluminacao.setBackground(IBackGround.BACKGROUND_DARKCIAN);
                    tbIluminacao.setText("OFF");
                }
                break;
            case 2: //AR CONDICIONADO ATIVA / DESTIVA
                if (itr.classificar(valores) == 1) {
                    estaOnArcondicionado = true;
                    arduino.write(131);
                    tbArcondicionado.setBackground(backDefaut);
                    tbArcondicionado.setText("ON");
                } else {
                    estaOnArcondicionado = false;
                    arduino.write(130);
                    tbArcondicionado.setBackground(IBackGround.BACKGROUND_DARKCIAN);
                    tbArcondicionado.setText("OFF");
                }
                break;
            case 3: //AQUECEDOR ATIVA / DESATIVA
                if (itr.classificar(valores) == 1) {
                    estaOnAquecedor = true;
                    arduino.write(121);
                    tbAquecedor.setBackground(backDefaut);
                    tbAquecedor.setText("ON");
                } else {
                    estaOnAquecedor = false;
                    arduino.write(120);
                    tbAquecedor.setBackground(IBackGround.BACKGROUND_DARKCIAN);
                    tbAquecedor.setText("OFF");
                }
                break;
            case 4: // UMIDIFICADOR ATIVA / DESATIVA
                if (itr.classificar(valores) == 1) {
                    estaOnUmidificado = true;
                    arduino.write(111);
                    tbUmidificador.setBackground(backDefaut);
                    tbUmidificador.setText("ON");
                } else {
                    estaOnUmidificado = false;
                    arduino.write(110);
                    tbUmidificador.setBackground(IBackGround.BACKGROUND_DARKCIAN);
                    tbUmidificador.setText("OFF");
                }
                break;
        }
    }

    // -------------------------------------------------------------------------
    /**
     * Altera a cor do backgroud dos botões ativados
     */
    private void alterarBackgraund(ToggleButton botão, Background b1, Background b2) {
        if (botão.getBackground().equals(b1)) {
            botão.setBackground(b2);
        } else {
            botão.setBackground(b1);
        }
    }

    // -------------------------------------------------------------------------

    /**
     * Inicia o Thread que ficará atualizando a tabela
     */

    private void startUpdateTableThread() {
        configTable();
        TimerTask update = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        preencherTabela();
                    }
                });
            }
        };
        new Timer().scheduleAtFixedRate(update, 0, 1000);
    }

    // -------------------------------------------------------------------------

    /**
     * Preenche a tabela com os ultimos dados coletados
     */

    private void preencherTabela() {
        //obDados = FXCollections.observableArrayList();
        dados = CacheLeitura.getInstance().getDados();
        if (!dados.isEmpty()) {
            obDados.clear();
            try {
                dados.stream().forEach((dado) -> {
                    obDados.add(new TableDados()
                            .setLuminosidade(dado.getLuminosidade())
                            .setTemperatura(dado.getTemperatura())
                            .setUmidade(dado.getUmidade())
                            .setStatusAquecedor(dado.getStatusAquecedor())
                            .setStatusArcondicionado(dado.getStatusArcondicionado())
                            .setStatusIluminacao(dado.getStatusIluminacao())
                            .setStatusUmidificador(dado.getStatusUmidificador())
                    );//Fim Add Lista
                });
                table.setItems(obDados);
            } catch (Exception e) {
            }
        }
    }

    // -------------------------------------------------------------------------

    /**
     * Congigura as colunas da Tabela
     */

    public void configTable() {
        obDados = FXCollections.observableArrayList();
        // ---------------------------------------------------------------------
        tcTemperatura.setCellValueFactory(new PropertyValueFactory("temperatura"));
        tcLuminosidade.setCellValueFactory(new PropertyValueFactory("luminosidade"));
        tcUmidade.setCellValueFactory(new PropertyValueFactory("umidade"));
        // ---------------------------------------------------------------------
        tcStatusAquecedor.setCellValueFactory(new PropertyValueFactory("statusAquecedor"));
        tcStatusArcondicionado.setCellValueFactory(new PropertyValueFactory("statusArcondicionado"));
        tcStatusUmidificador.setCellValueFactory(new PropertyValueFactory("statusUmidificador"));
        tcStatusIuminacao.setCellValueFactory(new PropertyValueFactory("statusIluminacao"));

    }

}
