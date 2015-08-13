/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.controller.view.monitor;

import cc.unip.tccfinal.fxml.model.Sensor;
import cc.unip.tccfinal.serialport.Arduino;
import cc.unip.tccfinal.util.CacheLeitura;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Moisés
 */
public class ArduinoTableView {

    private TableView tableView;
    private TableColumn colunaTemperatura;
    private TableColumn colunaUmidade;
    private TableColumn colunaLuminosidade;
    private TableColumn colunaStatusAquecedor;
    private TableColumn colunaStatusArcondicionado;
    private TableColumn colunaStatusUmidificador;
    private TableColumn colunaStatusIluminacao;

    private final Arduino arduino;
    private ObservableList<TableDados> obDados;
    private List<Sensor> dados = new ArrayList<>();

    public ArduinoTableView(Arduino arduino) {
        this.arduino = arduino;
    }

    public ArduinoTableView build() {
        obDados = FXCollections.observableArrayList();
        colunaTemperatura = new TableColumn("Temperatura");
        colunaLuminosidade = new TableColumn("Luminosidade");
        colunaUmidade = new TableColumn("Umidade");
        colunaStatusAquecedor = new TableColumn("ST Aquecedor");
        colunaStatusArcondicionado = new TableColumn("ST Ar Condicionado");
        colunaStatusUmidificador = new TableColumn("ST Umidificador");
        colunaStatusIluminacao = new TableColumn("ST Iluminacao");
        // ---------------------------------------------------------------------
        colunaTemperatura.setCellValueFactory(new PropertyValueFactory("temperatura"));
        colunaLuminosidade.setCellValueFactory(new PropertyValueFactory("luminosidade"));
        colunaUmidade.setCellValueFactory(new PropertyValueFactory("umidade"));
        // ---------------------------------------------------------------------
        colunaStatusAquecedor.setCellValueFactory(new PropertyValueFactory("statusAquecedor"));
        colunaStatusArcondicionado.setCellValueFactory(new PropertyValueFactory("statusArcondicionado"));
        colunaStatusUmidificador.setCellValueFactory(new PropertyValueFactory("statusUmidificador"));
        colunaStatusIluminacao.setCellValueFactory(new PropertyValueFactory("statusIluminacao"));
        // ---------------------------------------------------------------------
        tableView = new TableView();
        TableColumn[] tb = {colunaTemperatura,colunaLuminosidade,colunaUmidade,colunaStatusAquecedor,colunaStatusArcondicionado,colunaStatusIluminacao,colunaStatusUmidificador};
        //tableView.getColumns().addAll(colunaTemperatura,colunaLuminosidade,colunaUmidade,colunaStatusAquecedor,colunaStatusArcondicionado,colunaStatusIluminacao,colunaStatusUmidificador);
        adicionarColunas(tb);
        redimensionarColunas(tb);
        startUpdateTableThread();
        return this;
    }
    private void adicionarColunas(TableColumn... t){
        for(TableColumn tc:t){
            tableView.getColumns().add(tc);
        }
    }
    private void redimensionarColunas(TableColumn... t){
        for(TableColumn tc:t){
            tc.setPrefWidth(120);
        }
    }
    public TableView getTableView() {
        return tableView;
    }

    public ArduinoTableView setTableView(TableView tableView) {
        this.tableView = tableView;
        return this;
    }

    public TableColumn getColunaTemperatura() {
        return colunaTemperatura;
    }

    public ArduinoTableView setColunaTemperatura(TableColumn colunaTemperatura) {
        this.colunaTemperatura = colunaTemperatura;
        return this;
    }

    public TableColumn getColunaUmidade() {
        return colunaUmidade;

    }

    public ArduinoTableView setColunaUmidade(TableColumn colunaUmidade) {
        this.colunaUmidade = colunaUmidade;
        return this;
    }

    public TableColumn getColunaLuminosidade() {
        return colunaLuminosidade;

    }

    public ArduinoTableView setColunaLuminosidade(TableColumn colunaLuminosidade) {
        this.colunaLuminosidade = colunaLuminosidade;
        return this;
    }

    private void startUpdateTableThread() {
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

    private void preencherTabela() {
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
                tableView.setItems(obDados);
            } catch (Exception e) {
            }
        }
    }

 }
