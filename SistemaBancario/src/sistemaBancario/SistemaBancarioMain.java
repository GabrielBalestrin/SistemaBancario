package sistemaBancario;

public class SistemaBancarioMain {
	public static void main(String[] args) {

		// Criando bancos
		Banco bancoDoBrasil = new Banco("Banco do Brasil");
		Banco bancoSantander = new Banco("Banco Santander");

		
		// Criando clientes
		PessoaFisica clienteBB = new PessoaFisica("João Silva", "Rua A, 123", "11 99999-1111", bancoDoBrasil,"123.456.789-01");
		PessoaFisica clienteSantander = new PessoaFisica("Maria Souza", "Rua B, 456", "11 88888-2222", bancoSantander,"987.654.321-09");

		
		// Criando contas para os clientes
		ContaCorrente contaBB = new ContaCorrente(1001, clienteBB, bancoDoBrasil, 5000.0, 500.0);
		ContaCorrente contaSantander = new ContaCorrente(2002, clienteSantander, bancoSantander, 3000.0, 300.0);

		
		
		// Adicionando clientes e contas aos bancos
		bancoDoBrasil.adicionarCliente(clienteBB);
		bancoSantander.adicionarCliente(clienteSantander);
		clienteBB.adicionarConta(contaBB);
		clienteSantander.adicionarConta(contaSantander);

		
		
		// Exibindo informações iniciais das contas
		System.out.println("Saldo inicial da conta de João no Banco do Brasil: " + contaBB.getSaldoAtualizado());
		System.out.println("Saldo inicial da conta de Maria no Banco Santander: " + contaSantander.getSaldoAtualizado());

		
		// Realizando uma transferência entre contas de bancos diferentes
		System.out.println("\nRealizando transferência do Banco do Brasil para o Banco Santander...");
		Banco.transferirEntreBancos(bancoDoBrasil, 1001, bancoSantander, 2002, 1000.0);

		
		// Exibindo informações após a transferência
		System.out.println("Saldo da conta de João no Banco do Brasil após transferência: " + contaBB.getSaldoAtualizado());
		System.out.println("Saldo da conta de Maria no Banco Santander após receber transferência: "+ contaSantander.getSaldoAtualizado());

		
		// Criando terceiro cliente no Banco do Brasil
		PessoaFisica clienteBB2 = new PessoaFisica("Carlos Pereira", "Rua C, 789", "11 77777-3333", bancoDoBrasil,"321.654.987-01");

		
		// Criando conta para o terceiro cliente
		ContaCorrente contaBB2 = new ContaCorrente(1003, clienteBB2, bancoDoBrasil, 2000.0, 300.0);
		

		// Adicionando o terceiro cliente e conta ao Banco do Brasil
		bancoDoBrasil.adicionarCliente(clienteBB2);
		clienteBB2.adicionarConta(contaBB2);

		
		
		// Realizando uma transferência interna dentro do Banco do Brasil
		System.out.println("\nRealizando transferência interna no Banco do Brasil...");
		contaBB.transferirInterno(contaBB2, 500.0);

		
		// Exibindo informações após a transferência interna
		System.out.println("Saldo da conta de João no Banco do Brasil após transferência interna: "+ contaBB.getSaldoAtualizado());
		System.out.println("Saldo da conta de Carlos no Banco do Brasil após receber transferência: "+ contaBB2.getSaldoAtualizado());

		
		// Criando uma pessoa jurídica
		PessoaJuridica empresaXYZ = new PessoaJuridica("Empresa XYZ", "Av. Empresarial, 1000", "11 66666-4444",bancoDoBrasil, "84.459.905/0001-14");

		
		
		// Criando conta para a pessoa jurídica
		ContaCorrente contaEmpresaXYZ = new ContaCorrente(1004, empresaXYZ, bancoDoBrasil, 10000.0, 1000.0);

		
		
		// Adicionando a pessoa jurídica e a conta ao Banco do Brasil
		bancoDoBrasil.adicionarCliente(empresaXYZ);
		empresaXYZ.adicionarConta(contaEmpresaXYZ);
		System.out.println("Validação do CNPJ: " + (empresaXYZ.validarCNPJ() ? "Válido" : "Inválido"));
		
		
		// Realizando um depósito
		System.out.println("\nRealizando depósito na conta da Empresa XYZ...");
		contaEmpresaXYZ.depositar(2000.0);
		System.out.println("Saldo após depósito: " + contaEmpresaXYZ.getSaldoAtualizado());
		
		

		// Realizando um saque
		System.out.println("\nRealizando saque na conta da Empresa XYZ...");
		contaEmpresaXYZ.sacar(1500.0);
		System.out.println("Saldo após saque: " + contaEmpresaXYZ.getSaldoAtualizado());

		System.out.println("\nRealizando saque que utiliza o cheque especial na conta de João...");
		contaBB.sacar(4000.0); // Saque maior que o saldo atual, mas dentro do limite do cheque especial
		System.out.println("Saldo da conta de João após saque com cheque especial: " + contaBB.getSaldoAtualizado());
		System.out.println("Uso do Cheque Especial na conta de João: " + contaBB.getUsoCheque());
		
		System.out.println("\nDepositando valor para cobrir o uso do cheque especial na conta de João...");
		contaBB.depositar(1000.0); // Deposito para cobrir parte ou todo o uso do cheque especial
		System.out.println("Saldo da conta de João após depósito: " + contaBB.getSaldoAtualizado());
		System.out.println("Uso do Cheque Especial na conta de João após depósito: " + contaBB.getUsoCheque());

		
		// Criando conta sem cheque especial
		ContaCorrente contaSemCheque = new ContaCorrente(1005, clienteBB2, bancoDoBrasil, 100.0, 0.0);
		bancoDoBrasil.adicionarCliente(clienteBB2);
		clienteBB2.adicionarConta(contaSemCheque);

		
		System.out.println("\nTentativa de saque em conta sem cheque especial e saldo insuficiente...");
		contaSemCheque.sacar(200.0); // Saque maior que o saldo disponível e sem cheque especial

		
		// Primeiro, faça um saque que utilize parte do cheque especial
		System.out.println("\nRealizando saque que utiliza parte do cheque especial...");
		contaBB.sacar(4500.0); // Saque que utiliza parte do cheque especial

		System.out.println("\nTentativa de saque que excede o limite do cheque especial...");
		contaBB.sacar(6000.0); // Tentativa de saque que excede o saldo e o limite do cheque especial

		// Em seguida, faça um depósito que cubra parte do cheque especial
		System.out.println("\nRealizando depósito para cobrir parte do uso do cheque especial...");
		contaBB.depositar(300.0); // Depósito para cobrir parte do cheque especial
		System.out.println("Saldo da conta de João após depósito: " + contaBB.getSaldoAtualizado());
		System.out.println("Uso do Cheque Especial na conta de João após depósito: " + contaBB.getUsoCheque());

		
		// Calcular saldo dos clientes
		clienteBB.calcularSaldoTotal();
		clienteSantander.calcularSaldoTotal();
		clienteBB2.calcularSaldoTotal();
		empresaXYZ.calcularSaldoTotal();
		
		
	    // Criando um cliente
        PessoaFisica cliente4 = new PessoaFisica("Ana Silva", "Rua B, 789", "11 99999-9999",bancoDoBrasil, "222.333.444-55");

        // Criando uma conta com saldo pequeno
        ContaCorrente conta4 = new ContaCorrente(1010, cliente4, bancoDoBrasil, 100.0, 0.0); // Saldo de R$ 100
        
        bancoDoBrasil.adicionarCliente(cliente4);
        cliente4.adicionarConta(conta4);
     // Tentativa de saque maior que o saldo
        System.out.println("Tentando sacar R$ 200...");
        
        // Tentativa de saque maior que o saldo
        System.out.println("Tentando sacar R$ 200...");
        conta4.sacar(200.00); // Tentando sacar R$ 200

        // Mostrar saldo atual da conta
        System.out.println("Saldo atual da conta: " + conta4.getSaldoAtualizado());
        cliente4.calcularSaldoTotal();
	}
}
