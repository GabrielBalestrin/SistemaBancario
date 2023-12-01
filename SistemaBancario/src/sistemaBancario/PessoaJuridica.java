package sistemaBancario;

public class PessoaJuridica extends Pessoa {

	private String cnpj;

	public PessoaJuridica(String nome, String endereco, String telefone, Banco banco, String cnpj) {
		super(nome, endereco, telefone, banco);
		setCnpj(cnpj);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public boolean validarCNPJ() {
		String cnpjNumeros = cnpj.replaceAll("[^\\d]", "");
		if (cnpjNumeros.length() != 14) {
			return false;
		}
		int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		int soma = 0;
		for (int i = 0; i < 12; i++) {
			soma += (cnpjNumeros.charAt(i) - '0') * pesoCNPJ[i + 1];
		}
		int digito1 = soma % 11 < 2 ? 0 : 11 - (soma % 11);
		soma = 0;
		for (int i = 0; i < 13; i++) {
			soma += (cnpjNumeros.charAt(i) - '0') * pesoCNPJ[i];
		}
		int digito2 = soma % 11 < 2 ? 0 : 11 - (soma % 11);
		return digito1 == cnpjNumeros.charAt(12) - '0' && digito2 == cnpjNumeros.charAt(13) - '0';
	}

}