/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.unip.tccfinal.fxml.auxiliar;

/**
 *
 * @author Moisés
 */
public class DadosGraficoBarras {
    private int laco;
    private int acerto;
    private int erro;

    public DadosGraficoBarras(int laco, int acerto, int erro) {
        this.laco = laco;
        this.acerto = acerto;
        this.erro = erro;
    }
    
    public int getLaco() {
        return laco;
    }

    public void setLaco(int laco) {
        this.laco = laco;
    }

    public int getAcerto() {
        return acerto;
    }

    public void setAcerto(int acerto) {
        this.acerto = acerto;
    }

    public int getErro() {
        return erro;
    }

    public void setErro(int erro) {
        this.erro = erro;
    }
    
}
