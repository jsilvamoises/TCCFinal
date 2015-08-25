/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.util;

import cc.unip.tccfinal.fxml.controller.EquipamentoController;
import cc.unip.tccfinal.fxml.model.IdEquipamento;
import cc.unip.tccfinal.fxml.model.Equipamento;
//import cc.unip.tccfinal.fxml.model.EquipamentoId;
import cc.unip.tccfinal.fxml.model.Sensor;
import cc.unip.tccfinal.fxml.model.StatusEquipamento;
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
            Calendar calendar = Calendar.getInstance();
            int ano = calendar.get(Calendar.YEAR);
            int diaAno = calendar.get(Calendar.DAY_OF_YEAR);
            byte mes = (byte) calendar.get(Calendar.MONTH);
            byte diaMes  = (byte) calendar.get(Calendar.DAY_OF_MONTH);
            byte diaSemana = (byte) calendar.get(Calendar.DAY_OF_WEEK);
            byte hora = (byte) calendar.get(Calendar.HOUR_OF_DAY);
            byte minuto = (byte) calendar.get(Calendar.MINUTE);
            
            //SALVA REGISTRO DO AR CONDICIONADO NO BANCO
            Equipamento arCondicionado = new Equipamento()
                    .setIdEquipamento(IdEquipamento.ID_AR_CONDICIONADO)
                    .setStatusEquipamento(sensor.getStatusArcondicionado())
                    .setValorSensorReferencia(sensor.getTemperatura())
                    .setNomeEquipamento("ARCONDICIONADO")
                    //.setAnoAmostra(ano)
                    //.setDiaAno(diaAno)
                    //.setMesAmostra(mes)
                   // .setDiaAmostra(diaMes)
                    //.setDiaSemana(diaSemana)
                   // .setHoraAmostra1(hora)
                   // .setMinutoAmostra1(minuto)
                    
                    .setDataAmostra(new Date());
                    //                    .setId(new EquipamentoId()
            //                            .setIdEquipamento(IdEquipamento.ID_AR_CONDICIONADO)
            //                            .setStatusEquipamento(sensor.getStatusArcondicionado())
            //                            .setValorSensorReferencia(sensor.getTemperatura())
            //                    ).

            //SALVA O REGISTRO DO AQUECEDOR NO BANCO
            Equipamento aquecedor = new Equipamento()
                    .setIdEquipamento(IdEquipamento.ID_AQUECEDOR)
                    .setStatusEquipamento(sensor.getStatusAquecedor())
                    .setValorSensorReferencia(sensor.getTemperatura())
                    .setNomeEquipamento("AQUECEDOR")
                    //.setAnoAmostra(ano)
                   // .setDiaAno(diaAno)
                   // .setMesAmostra(mes)
                   // .setDiaAmostra(diaMes)
                  //  .setDiaSemana(diaSemana)
                  //  .setHoraAmostra1(hora)
                  //  .setMinutoAmostra1(minuto)
                    
                    .setDataAmostra(new Date());
//                    .setId(new EquipamentoId()
//                            .setIdEquipamento(IdEquipamento.ID_AQUECEDOR)
//                            .setStatusEquipamento(sensor.getStatusAquecedor())
//                            .setValorSensorReferencia(sensor.getTemperatura())
//                    ).setDataAmostra(new Date());
            //SALVA O REGISTRO DO UMIDIFICADOR NO BANCO
            
            Equipamento umidificador = new Equipamento()
                    .setIdEquipamento(IdEquipamento.ID_UMIDIFICADOR)
                    .setStatusEquipamento(sensor.getStatusUmidificador())
                    .setValorSensorReferencia(sensor.getUmidade())
                    .setNomeEquipamento("UMIDIFICADOS")
                   // .setAnoAmostra(ano)
                  //  .setDiaAno(diaAno)
                  //  .setMesAmostra(mes)
                  //  .setDiaAmostra(diaMes)
                  //  .setDiaSemana(diaSemana)
                  //  .setHoraAmostra1(hora)
                  //  .setMinutoAmostra1(minuto)
                    
                    .setDataAmostra(new Date());

//                    .setId(new EquipamentoId()
            //                            .setIdEquipamento(IdEquipamento.ID_UMIDIFICADOR)
            //                            .setStatusEquipamento(sensor.getStatusUmidificador())
            //                            .setValorSensorReferencia(sensor.getUmidade())
            //                    ).setDataAmostra(new Date());
            //SALVA O REGISTRO DA ILUMINAÇÃO NO BANCO
            Equipamento iluminacao = new Equipamento()
                    .setIdEquipamento(IdEquipamento.ID_ILUMINACAO)
                    .setStatusEquipamento(sensor.getStatusIluminacao())
                    .setValorSensorReferencia(sensor.getLuminosidade())
                    .setNomeEquipamento("ILUMINACAO")
                   // .setAnoAmostra(ano)
                  //  .setDiaAno(diaAno)
                   // .setMesAmostra(mes)
                  //  .setDiaAmostra(diaMes)
                  //  .setDiaSemana(diaSemana)
                  //  .setHoraAmostra1(hora)
                  //  .setMinutoAmostra1(minuto)
                    .setDataAmostra(new Date());

//                    .setId(new EquipamentoId()
            //                            .setIdEquipamento(IdEquipamento.ID_ILUMINACAO)
            //                            .setStatusEquipamento(sensor.getStatusIluminacao())
            //                            .setValorSensorReferencia(sensor.getLuminosidade())
            //                    ).setDataAmostra(new Date());
            //SALVA TODOS NO BANCO DE DADOS
            controller.saveAll(iluminacao,arCondicionado, aquecedor, umidificador);
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
