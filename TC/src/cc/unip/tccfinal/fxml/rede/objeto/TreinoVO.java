package cc.unip.tccfinal.fxml.rede.objeto;

public class TreinoVO {

	private Double entradasUltimaCamada[];
	private Double[] entradas, gradientes;
	private Double fatorAdaptacao;

	public TreinoVO(Double[] entradas, Double[] entradasUltimaCamada, Double[] gradientes, Double fatorAdaptacao) {
		this.entradasUltimaCamada = entradasUltimaCamada;
		this.entradas = entradas;
		this.gradientes = gradientes;
		this.fatorAdaptacao = fatorAdaptacao;
	}

	public Double[] getEntradasUltimaCamada() {
		return entradasUltimaCamada;
	}

	public Double[] getEntradas() {
		return entradas;
	}

	public Double[] getGradientes() {
		return gradientes;
	}

	public Double getFatorAdaptacao() {
		return fatorAdaptacao;
	}

}