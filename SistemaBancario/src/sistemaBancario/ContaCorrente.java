package sistemaBancario;

public class ContaCorrente extends Conta {
	private double limiteChequeEspecial;
	private double saldoCC;
	private double usoCheque;

	public ContaCorrente(int numConta, Pessoa titular, Banco banco, double saldoInicialCC,
			double limiteChequeEspecial) {
		super(numConta, titular, banco);
		this.saldoCC = saldoInicialCC;
		this.limiteChequeEspecial = limiteChequeEspecial;
		this.usoCheque = 0.0;
	}

	public double getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}

	public void setLimiteChequeEspecial(double limiteChequeEspecial) {
		this.limiteChequeEspecial = limiteChequeEspecial;
	}

	public double getSaldoCC() {
		return saldoCC;
	}

	public void setSaldoCC(double saldoCC) {
		this.saldoCC = saldoCC;
	}

	public double getUsoCheque() {
		return usoCheque;
	}

	public void setUsoCheque(double usoCheque) {
		this.usoCheque = usoCheque;
	}

	public void usarChequeEspecial(double valor) {
		if (valor > 0 && (usoCheque + valor) <= limiteChequeEspecial) {
			saldoCC -= valor;
			usoCheque += valor;
			operacoes.add("Uso do Cheque Especial: " + valor);
		} else {
			System.out.println("Valor excede o limite do cheque especial.");
		}
	}

	@Override
	public void sacar(double valor) {
		if (valor <= 0) {
			System.out.println("Valor inválido para saque.");
			return;
		}

		if (valor > saldoCC) {
			double excedente = valor - saldoCC;
			double chequeEspecialDisponivel = limiteChequeEspecial - usoCheque;

			if (excedente <= chequeEspecialDisponivel) {
				usoCheque += excedente;
				saldoCC = 0;
				System.out.println("Saque de " + valor + " realizado com uso de " + excedente + " do cheque especial.");
			} else {
				System.out.println("Saldo e cheque especial insuficientes para o saque de " + valor + ".");
			}
		} else {
			saldoCC -= valor;
			System.out.println("Saque de " + valor + " realizado com sucesso.");
		}
	}

	@Override
	public void depositar(double valor) {
		if (valor <= 0) {
			System.out.println("Valor de depósito inválido.");
			return;
		}

		if (usoCheque > 0) {
			if (valor >= usoCheque) {
				saldoCC += (valor - usoCheque);
				usoCheque = 0;
			} else {
				usoCheque -= valor;
			}
		} else {
			saldoCC += valor;
		}
		operacoes.add("Depósito: " + valor);
	}

	@Override
	public double getSaldoAtualizado() {
		System.out.println("Valor total da Conta Corrente: " + saldoCC);
		System.out.println("Uso do Cheque Especial: " + usoCheque);
		return saldoCC;
	}

	public double getSaldoDisponivelParaTransferencia() {
		return saldoCC + limiteChequeEspecial - usoCheque;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContaCorrente [limiteChequeEspecial=");
		builder.append(limiteChequeEspecial);
		builder.append(", saldoCC=");
		builder.append(saldoCC);
		builder.append(", usoCheque=");
		builder.append(usoCheque);
		builder.append("]");
		return builder.toString();
	}

}
