package sistemaBancario;

public class ContaPoupanca extends Conta {

	private double taxaRendimento;
	private double TotalPoupanca;

	public ContaPoupanca(int numConta, Pessoa titular, Banco banco, double saldoInicialPoupanca,
			double taxaRendimento) {
		super(numConta, titular, banco);
		this.taxaRendimento = taxaRendimento;
		this.TotalPoupanca = saldoInicialPoupanca;
	}

	public double getTaxaRendimento() {
		return taxaRendimento;
	}

	public void setTaxaRendimento(double taxaRendimento) {
		this.taxaRendimento = taxaRendimento;
	}

	public double getTotalPoupanca() {
		return TotalPoupanca;
	}

	public void setTotalPoupanca(double totalPoupanca) {
		TotalPoupanca = totalPoupanca;
	}

	public void aplicarRendimento() {
		double rendimento = TotalPoupanca * taxaRendimento;
		TotalPoupanca += rendimento;
		operacoes.add("Rendimento aplicado: " + rendimento);
	}

	@Override
	public void depositar(double valor) {
		if (valor > 0) {
			TotalPoupanca += valor;
			operacoes.add("Depósito: " + valor);
		} else {
			System.out.println("Valor de depósito inválido.");
		}
	}

	@Override
	public void sacar(double valor) {
		if (valor > 0 && TotalPoupanca >= valor) {
			TotalPoupanca -= valor;
			operacoes.add("Saque: " + valor);
		} else {
			System.out.println("Saldo insuficiente para o saque.");
		}
	}

	@Override
	public double getSaldoAtualizado() {
		System.out.println("Valor total da Conta Poupança: " + TotalPoupanca);
		return TotalPoupanca;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContaPoupanca [taxaRendimento=");
		builder.append(taxaRendimento);
		builder.append(", TotalPoupanca=");
		builder.append(TotalPoupanca);
		builder.append("]");
		return builder.toString();
	}

}
