package implementacoes;

import java.util.ArrayList;
import java.util.List;

import br.com.microlins.interfaces.IFila;

public class IFilaImplementacao<E> implements IFila<E> {
	
	List<E> fila = new ArrayList<>();

	@Override
	public E pegarPrimeiro() {
		E primeiro;
		primeiro = consultarPrimeiro();
		if(primeiro!= null)
			fila.remove(primeiro);
		return primeiro;
	}

	@Override
	public E consultarPrimeiro() {
		if(fila != null && !fila.isEmpty() )
			return fila.get(0);
		else
			return null;
	}

	@Override
	public void adicionar(E parametro) {
		fila.add(parametro);
	}

	@Override
	public void remover(E parametro) {
		fila.remove(parametro);
	}

	@Override
	public int quantidadeItens() {
		return fila.size();
	}

	@Override
	public E elementoNaPosicao(int posicao) {
		return fila.get(posicao);
	}
	
	
}
