package sistemaBancario;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta {

	private int numConta;
	private double saldoTotal;
	private Pessoa titular;
	protected List<String> operacoes;
	private Banco banco;

	public Conta(int numConta, double saldoTotal, Pessoa titular, Banco banco) {
		setNumConta(numConta);
		setSaldoTotal(saldoTotal);
		setTitular(titular);
		setBanco(banco);
		this.operacoes = new ArrayList<>();
	}

	public Conta(int numConta, Pessoa titular, Banco banco) {
		setNumConta(numConta);
		setTitular(titular);
		setBanco(banco);
		this.operacoes = new ArrayList<>();
	}

	public int getNumConta() {
		return numConta;
	}

	public void setNumConta(int numConta) {
		this.numConta = numConta;
	}

	public double getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}

	public Pessoa getTitular() {
		return titular;
	}

	public void setTitular(Pessoa titular) {
		this.titular = titular;
	}

	public List<String> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(List<String> operacoes) {
		this.operacoes = operacoes;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	// MÉTODOS
	public void transferirInterno(Conta contaDestino, double valor) {
		if (!this.banco.equals(contaDestino.getBanco())) {
			System.out.println("Transferência inválida. As contas não estão no mesmo banco.");
			return;
		}

		if (this instanceof ContaCorrente && contaDestino instanceof ContaCorrente) {
			ContaCorrente contaOrigemCC = (ContaCorrente) this;
			ContaCorrente contaDestinoCC = (ContaCorrente) contaDestino;

			if (valor > 0 && contaOrigemCC.getSaldoCC() >= valor) {
				contaOrigemCC.setSaldoCC(contaOrigemCC.getSaldoCC() - valor);
				contaDestinoCC.setSaldoCC(contaDestinoCC.getSaldoCC() + valor);
				this.operacoes
						.add("Transferência interna enviada: " + valor + " para conta " + contaDestino.getNumConta());
			} else {
				System.out.println("Transferência inválida ou saldo insuficiente.");
			}
		} else {
	
			System.out.println("Operação permitida apenas entre contas correntes.");
		}
	}

	public void depositar(double valor) {
		if (valor > 0) {
			saldoTotal += valor;
			operacoes.add("Depósito: " + valor);
		} else {
			System.out.println("Valor de depósito inválido.");
		}
	}

	public void sacar(double valor) {
		if (valor > 0 && saldoTotal >= valor) {
			saldoTotal -= valor;
			operacoes.add("Saque: " + valor);
		} else {
			System.out.println("Saldo insuficiente ou valor inválido.");
		}
	}

	public void mostrarOperacoes() {
		for (String operacao : operacoes) {
			System.out.println(operacao);
		}
	}

	public abstract double getSaldoAtualizado();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Conta [numConta=");
		builder.append(numConta);
		builder.append(", saldoTotal=");
		builder.append(saldoTotal);
		builder.append(", titular=");
		builder.append(titular);
		builder.append(", operacoes=");
		builder.append(operacoes);
		builder.append(", banco=");
		builder.append(banco);
		builder.append("]");
		return builder.toString();
	}
}
