/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.controller.view.treino;

/**
 *
 * @author Moisés
 */
public class DadosGraficoErrAce{
        private String nome;
        private int valor;

        public DadosGraficoErrAce(String nome, int valor) {
            this.nome = nome;
            this.valor = valor;
        }

        public DadosGraficoErrAce() {
        }
        
        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getValor() {
            return valor;
        }

        public void setValor(int valor) {
            this.valor = valor;
        }
        
    }