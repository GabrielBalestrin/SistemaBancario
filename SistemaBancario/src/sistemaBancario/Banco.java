package sistemaBancario;

import java.util.ArrayList;
import java.util.List;

public class Banco {

	private String nomeBanco;
	private List<Pessoa> clientes;

	// constructor
	public Banco(String nomeBanco) {
		setNomeBanco(nomeBanco);
		this.clientes = new ArrayList<>();
	}

	// getters and setters
	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public List<Pessoa> getClientes() {
		return clientes;
	}

	public void setClientes(List<Pessoa> clientes) {
		this.clientes = clientes;
	}

	// methods
	public static void transferirEntreBancos(Banco bancoOrigem, int numContaOrigem, Banco bancoDestino,
			int numContaDestino, double valor) {
		ContaCorrente contaOrigem = (ContaCorrente) bancoOrigem.buscarConta(numContaOrigem);
		ContaCorrente contaDestino = (ContaCorrente) bancoDestino.buscarConta(numContaDestino);

		if (contaOrigem != null && contaDestino != null) {
			double saldoDisponivel = contaOrigem.getSaldoDisponivelParaTransferencia();
			if (saldoDisponivel >= valor) {
				contaOrigem.sacar(valor);
				contaDestino.depositar(valor);
				System.out.println("Transferência realizada com sucesso entre bancos diferentes!");
			} else {
				System.out.println("Saldo insuficiente para transferência.");
			}
		} else {
			System.out.println("Conta de origem ou destino não encontrada.");
		}
	}

	public void adicionarCliente(Pessoa cliente) {
		cliente.setBanco(this); // Configura o banco do cliente
		this.clientes.add(cliente);
		System.out.println("Cliente adicionado ao " + this.nomeBanco + " com sucesso!");
	}

	public void removerCliente(String nomeCliente) {
		if (clientes.removeIf(c -> c.getNome().equals(nomeCliente))) {
			System.out.println("Cliente removido do " + this.nomeBanco + " com sucesso!");
		} else {
			System.out.println("Cliente não encontrado no " + this.nomeBanco + "!");
		}
	}

	public void buscarCliente(String nomeCliente) {
		for (Pessoa cliente : clientes) {
			if (cliente.getNome().equals(nomeCliente)) {
				System.out.println("Cliente encontrado no " + this.nomeBanco + ": " + cliente);
				return;
			}
		}
		System.out.println("Cliente não encontrado no " + this.nomeBanco + "!");
	}

	public Conta buscarConta(int numConta) {
		for (Pessoa cliente : clientes) {
			for (Conta conta : cliente.getContas()) {
				if (conta.getNumConta() == numConta) {
					System.out.println("Conta encontrada: " + conta);
					return conta;
				}
			}
		}
		System.out.println("Conta não encontrada!");
		return null;
	}

	@Override
	public String toString() {
		return "Banco{" + "nomeBanco='" + nomeBanco + '\'' + '}';
	}
}
