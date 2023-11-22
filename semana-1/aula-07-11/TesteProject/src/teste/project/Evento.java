package teste.project;

public class Evento {
	private int id;
	private String nome;
	private String local;

	public Evento(int id, String nome, String local) {
		super();
		this.id = id;
		this.nome = nome;
		this.local = local;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

}
