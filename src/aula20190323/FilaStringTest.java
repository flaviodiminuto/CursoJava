package aula20190323;

import br.com.microlins.interfaces.IFilaString;
import implementacoes.IFilaStringImplementacao;

public class FilaStringTest {

	public static void main(String[] args) {

		IFilaString fila = new IFilaStringImplementacao(); // substituir o null pela sua implementação

		fila.adicionar("Rafael");
		fila.adicionar("Enrico");
		fila.adicionar("Daniel");
		fila.adicionar("Alessandro");
		fila.adicionar("Roberto");
		fila.adicionar("Renato");

		System.out.println(fila.quantidadeItens() == 6);

		System.out.println(fila.elementoNaPosicao(0).equals("Rafael"));

		System.out.println(fila.pegarPrimeiro().equals("Rafael"));

		System.out.println(fila.pegarPrimeiro().equals("Enrico"));

		fila.remover("Rafael");

		System.out.println(fila.consultarPrimeiro().equals("Daniel"));

		System.out.println(fila.quantidadeItens() == 4);

	}

}
