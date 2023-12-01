package sistemaBancario;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
	private List<Conta> contas;
	private String nome;
	private String endereco;
	private String telefone;
	private Banco banco;

	public Pessoa(String nome, String endereco, String telefone, Banco banco) {
		setNome(nome);
		setEndereco(endereco);
		setTelefone(telefone);
		setBanco(banco);
		this.contas = new ArrayList<>();
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	// methods
	public void adicionarConta(Conta conta) {
		if (!contas.contains(conta)) {
			contas.add(conta);
		} else {
			System.out.println("A conta já está associada a essa pessoa.");
		}
	}

	public void calcularSaldoTotal() {
		double saldoTotal = 0;
		for (Conta conta : contas) {
			saldoTotal += conta.getSaldoAtualizado();
		}
		System.out.println("Saldo total do cliente " + getNome() + ": " + saldoTotal);
	}

	public void mostrarOperacoesTodasContas() {
		System.out.println("Histórico de operações para todas as contas do cliente " + getNome() + ":");
		for (Conta conta : contas) {
			System.out.println("\nHistórico de operações para a conta " + conta.getNumConta() + ":");
			conta.mostrarOperacoes();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pessoa [contas=");
		builder.append(contas);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", endereco=");
		builder.append(endereco);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append(", banco=");
		builder.append(banco);
		builder.append("]");
		return builder.toString();
	}

}
