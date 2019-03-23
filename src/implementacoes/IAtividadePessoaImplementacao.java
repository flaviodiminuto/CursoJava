package implementacoes;

import java.util.ArrayList;
import java.util.List;

import br.com.microlins.Atividade;
import br.com.microlins.Pessoa;
import br.com.microlins.interfaces.IAtividadePessoa;

public class IAtividadePessoaImplementacao implements IAtividadePessoa {

	List<Atividade> atividades = new ArrayList<>();
	@Override
	public boolean gravarAtividade(Atividade atividade) {
		return atividades.add(atividade);
	}

	@Override
	public List<Atividade> recuperarTodasAtividadesPorPessoa(Pessoa pessoa) {
		List<Atividade> atividades = new ArrayList<>();
		for(Atividade atividade : this.atividades) {
			if(atividade.getPessoa().equals(pessoa)) {
				atividades.add(atividade);
			}
		}
		return atividades;
	}

	@Override
	public Atividade recuperarUltimaAtividadePessoa(Pessoa pessoa) {
		for(int i = atividades.size()-1; i>=0; i--) {
			if(atividades.get(i).getPessoa().equals(pessoa))
				return atividades.get(i);
		}
		return null;
	}

	@Override
	public boolean removerAtividade(Atividade atividade) {
		return atividades.remove(atividade);
	}

	@Override
	public int quantidadeAtividadesRealizadasPorPessoa(Pessoa pessoa) {
		int quantidade = 0;
		for (Atividade atividade : atividades) {
			if(atividade.getPessoa().equals(pessoa))
				quantidade++;
		}
		return quantidade;
	}

	@Override
	public int totalAtividades() {
		return atividades.size();
	}

	@Override
	public void limparLista() {
		atividades.clear();
	}

	@Override
	public Atividade consultarUltimaAtividadeRealizada() {
		if(atividades != null && !atividades.isEmpty())
			return atividades.get(atividades.size()-1);
		else
			return null;
	}

}
