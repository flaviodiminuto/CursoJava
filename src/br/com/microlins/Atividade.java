package br.com.microlins;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.microlins.interfaces.IAtividade;

public class Atividade implements IAtividade{

	private Pessoa pessoa;
	private Date horaInicio;
	private Date horaFim;
	private String descricao;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	public Atividade(){}

	public Atividade(Pessoa pessoa, Date horaInicio, Date horaFim, String descricao) {
		this.pessoa = pessoa;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.descricao = descricao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override 
	public String resumoAtividade() {
		String retorno = String.format( "Data in�cio: %s%nDura��o do exerc�cio: %s%nDescri��o da atividade: %s",sdf.format(horaInicio), sdf.format(horaFim), descricao);
		if(this.pessoa != null){
			return retorno;}
		else{
			return "Nome: "+this.pessoa.getNome()+ "\n"+retorno;}
	}
}
