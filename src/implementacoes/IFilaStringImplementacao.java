package implementacoes;

import java.util.ArrayList;
import java.util.List;

import br.com.microlins.interfaces.IFilaString;

public class IFilaStringImplementacao implements IFilaString {

	private List<String> fila = new ArrayList<>(); 
	
	@Override
	public String pegarPrimeiro() {
		String primeiro;
		primeiro = consultarPrimeiro();
		fila.remove(0);
		return primeiro;
	}

	@Override
	public String consultarPrimeiro() {
		if(!fila.isEmpty() && fila!=null)
			return fila.get(0);
		else
			return "";
	}

	@Override
	public void adicionar(String parametro) {
		fila.add(parametro);
	}

	@Override
	public void remover(String parametro) {
		fila.remove(parametro);
	}

	@Override
	public int quantidadeItens() {
		return fila.size();
	}

	@Override
	public String elementoNaPosicao(int posicao) {
		return fila.get(posicao);
	}

}
