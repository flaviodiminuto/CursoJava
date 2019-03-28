package br.com.microlins;

import br.com.microlins.interfaces.IPessoa;

public class Pessoa implements IPessoa{

	private String nome;
	private int idade;
	private double peso;
	private double altura;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	@Override
	public String exibirDados() {
		return String.format("Nome: %s%nIdade: %s%nPeso: %s%nAltura: %s", nome, idade, peso, altura);
	}

	@Override
	public double calculaImc() {
		if(altura!=0)
			return peso/(altura*altura);
		else
			return 0;
	}

}
