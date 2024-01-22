package empresaTransporte;

import java.io.Serializable;

public class PontoParada implements Serializable {
	private static final long serialVersionUID = -3985079336354447143L;
	private String codigo;
	private String localizacao;
	private String referencias;

	public PontoParada(String codigo, String localizacao, String referencias) {
		this.codigo = codigo;
		this.localizacao = localizacao;
		this.referencias = referencias;
	}

	public PontoParada() {

	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getReferencias() {
		return referencias;
	}

	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	public void exibirInformacoes() {
		System.out.println("Código PP: " + this.codigo + "\nLocalização: " + this.localizacao + "\nReferência: "
				+ this.referencias + "\n");
	}
}
