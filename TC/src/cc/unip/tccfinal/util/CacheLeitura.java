/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.util;

import cc.unip.tccfinal.controller.EquipamentoController;
import cc.unip.tccfinal.controller.IdEquipamento;
import cc.unip.tccfinal.model.Equipamento;
import cc.unip.tccfinal.model.EquipamentoId;
import cc.unip.tccfinal.model.Sensor;
import cc.unip.tccfinal.model.StatusEquipamento;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Moisés
 */
public class CacheLeitura {

    private boolean isSaving = false;
    private List<Sensor> dados = new ArrayList<>();
    private Sensor ultimoDadoRecebidoSensor;
    private StatusEquipamento ultimoEstadoEquipamento;
    private static CacheLeitura instance;
    private EquipamentoController controller;

    public static CacheLeitura getInstance() {
        return instance == null ? instance = new CacheLeitura() : instance;
    }

    private CacheLeitura() {
        this.ultimoEstadoEquipamento = new StatusEquipamento();
        //this.ultimoDadoRecebidoSensor = new Sensor();
        this.controller = new EquipamentoController();
    }

    public void processar(Sensor sensor) {
        sensor.setHoraAmostra(Calendar.getInstance().getTime());
        dados.add(0, sensor);//Adiciona o dado a primeira posição
        ultimoDadoRecebidoSensor = sensor;

        ultimoEstadoEquipamento.setStatusAquecedor(sensor.getStatusAquecedor())
                .setStatusArcondicionado(sensor.getStatusArcondicionado())
                .setStatusIluminacao(sensor.getStatusIluminacao())
                .setStatusUmidificador(sensor.getStatusIluminacao());

        //REMOVE ITENS MAIS VELHO DA LISTA
        if (dados.size() > 20) {
            System.out.println(">>> Removendo o objeto mais antigo da lista [20º]");
            dados.remove(20);
        }
        //Verifica se é para salvar amostras no banco
        if (isSaving) {
            //SALVA REGISTRO DO AR CONDICIONADO NO BANCO
            Equipamento arCondicionado = new Equipamento()
                    .setId(new EquipamentoId()
                            .setIdEquipamento(IdEquipamento.ID_AR_CONDICIONADO)
                            .setStatusEquipamento(sensor.getStatusArcondicionado())
                            .setValorSensorReferencia(sensor.getTemperatura())
                    ).setDataAmostra(new Date());
            //SALVA O REGISTRO DO AQUECEDOR NO BANCO
            Equipamento aquecedor = new Equipamento()
                    .setId(new EquipamentoId()
                            .setIdEquipamento(IdEquipamento.ID_AQUECEDOR)
                            .setStatusEquipamento(sensor.getStatusAquecedor())
                            .setValorSensorReferencia(sensor.getTemperatura())
                    ).setDataAmostra(new Date());
            //SALVA O REGISTRO DO UMIDIFICADOR NO BANCO
            Equipamento umidificador = new Equipamento()
                    .setId(new EquipamentoId()
                            .setIdEquipamento(IdEquipamento.ID_UMIDIFICADOR)
                            .setStatusEquipamento(sensor.getStatusUmidificador())
                            .setValorSensorReferencia(sensor.getUmidade())
                    ).setDataAmostra(new Date());
            //SALVA O REGISTRO DA ILUMINAÇÃO NO BANCO
            Equipamento iluminacao = new Equipamento()
                    .setId(new EquipamentoId()
                            .setIdEquipamento(IdEquipamento.ID_ILUMINACAO)
                            .setStatusEquipamento(sensor.getStatusIluminacao())
                            .setValorSensorReferencia(sensor.getLuminosidade())
                    ).setDataAmostra(new Date());
            //SALVA TODOS NO BANCO DE DADOS
            controller.saveAll(arCondicionado, aquecedor, umidificador, iluminacao);
        }

    }

    public boolean isIsSaving() {
        return isSaving;
    }

    public void setIsSaving(boolean isSaving) {
        this.isSaving = isSaving;
    }

    public List<Sensor> getDados() {
        return dados;
    }

    public void setDados(List<Sensor> dados) {
        this.dados = dados;
    }

    public Sensor getUltimoDadoRecebidoSensor() {
        return ultimoDadoRecebidoSensor;
    }

    public void setUltimoDadoRecebidoSensor(Sensor ultimoDadoRecebidoSensor) {
        this.ultimoDadoRecebidoSensor = ultimoDadoRecebidoSensor;
    }

    public StatusEquipamento getUltimoEstadoEquipamento() {
        return ultimoEstadoEquipamento;
    }

    public void setUltimoEstadoEquipamento(StatusEquipamento ultimoEstadoEquipamento) {
        this.ultimoEstadoEquipamento = ultimoEstadoEquipamento;
    }

}
