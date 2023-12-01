package sistemaBancario;

public class PessoaFisica extends Pessoa {

	private String cpf;

	public PessoaFisica(String nome, String endereco, String telefone, Banco banco, String cpf) {
		super(nome, endereco, telefone, banco);
		setCpf(cpf);

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
