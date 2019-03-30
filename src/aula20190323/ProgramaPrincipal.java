package aula20190323;

import br.com.microlins.Atividade;
import br.com.microlins.AtividadePessoa;
import br.com.microlins.Pessoa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProgramaPrincipal {
    private static final Scanner scan = new Scanner(System.in);
    private static final int SAIR = 3;
    private static List<Pessoa> pessoas = new ArrayList<>();
    private static List<AtividadePessoa> atividadePessoas = new ArrayList<>();

    public static void main(String[] args) {
        int idCategoria;
        do {
            idCategoria = exibeMenu();
            switch (idCategoria) {
                case 1:
                    selecionaOperacaoPessoa();
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Fechando o programa\nAte logo!");
                    break;
                default:
                    System.out.println(new StringBuffer()
                            .append("A categoria ")
                            .append(idCategoria)
                            .append(" Não existe"));
            }
        } while (idCategoria != SAIR);
    }

    private static int exibeMenu() {
        int sair = 4;
        int acao;
        boolean valido;
        do {
            valido = true;
            escreveCabecalho("Menu Inicial");
            System.out.println("Informe a categoria");
            System.out.println("1- Pessoa");
            System.out.println("2- Atividade");
            System.out.println("3= Atividades por Pessoa");
            System.out.println(sair+"- Sair");
            acao = scan.nextInt();

            switch (acao) {
                case 1: selecionaOperacaoPessoa();
                break;
                case 2: selecionaOperacaoAtividade();
                break;
                default:
                    System.out.println("Opcao " + acao + " nao e valida!\n");
                    valido = false;
            }
        } while (!valido);
        return acao;
    }

    private static void selecionaOperacaoPessoa() {
        int sair = 4;
        int operacaoCategoriaPessoa;
        do {
            escreveCabecalho("Categoria Pessoa");
            System.out.println("OPERACOES");
            System.out.println("1- Cadastrar");
            System.out.println("2- Exibir dados");
            System.out.println("3- Calcular IMC");
            System.out.println(sair + "- Voltar");
            operacaoCategoriaPessoa = scan.nextInt();
            switch (operacaoCategoriaPessoa) {
                case 1:
                    pessoas.add(operacaoCadastrarPessoa());
                    break;
                case 2:
                    if (pessoas.size() > 0)
                        operacaoExibirDadosPessoa();
                    else
                        solicitarCadastroPessoa();
                    break;
                case 3:
                    if (pessoas.size() > 0)
                        operacaoCalculaIMCPessoa();
                    else
                        solicitarCadastroPessoa();
                    break;
                default:
                    System.out.println("Operacao " + operacaoCategoriaPessoa + " nao e valida");
            }
        } while (operacaoCategoriaPessoa != sair);
    }

    private static void solicitarCadastroPessoa() {
        System.out.println("Por favor, cadastre ao menos uma pessoa");
    }

    private static Pessoa operacaoCadastrarPessoa() {
        String nome;
        int idade;
        Double peso, altura;

        escreveCabecalho("Cadastrando Pessoa");
        System.out.println("Informe o nome: ");
        nome = scan.next();
        System.out.println("Informe a idade: ");
        idade = scan.nextInt();
        System.out.println("Informe o peso: ");
        peso = scan.nextDouble();
        System.out.println("Informe a altura: ");
        altura = scan.nextDouble();

        return new Pessoa(nome, idade, peso, altura);
    }

    private static void operacaoExibirDadosPessoa() {
        Pessoa pessoa = selecionaPessoa();
        System.out.println(pessoa.exibirDados());
    }

    private static void operacaoCalculaIMCPessoa() {
        Pessoa pessoa = selecionaPessoa();
        System.out.println(new StringBuilder()
                .append("Nome: ")
                .append(pessoa.getNome())
                .append("\tIMC: ")
                .append(pessoa.calculaImc()));
    }

    private static Pessoa selecionaPessoa() {
        int indicePessoa;
        do {
            escreveCabecalho("Pessoas");
            for (Pessoa pessoa : pessoas) {
                System.out.println(String.format("Pessoa: %d%n%s", pessoas.indexOf(pessoa), pessoa.getNome()));
                System.out.println("------------------------------------");
            }
            System.out.println("Indique o numero de uma Pessoa da Lista acima: ");
            indicePessoa = scan.nextInt();
        } while (indicePessoa < 0 || indicePessoa > pessoas.size());
        return pessoas.get(indicePessoa);
    }

    private static void selecionaOperacaoAtividade() {
        int opt;
        int voltar =9;
        AtividadePessoa atividadePessoa = new AtividadePessoa();
        Pessoa pessoa;
        Atividade atividade;
        do{
        escreveCabecalho("Atividades");
        System.out.println("1- Adicionar Atividade");
        System.out.println("2- Vincular atividade a uma pessoa");
        System.out.println("3- Consultar todas as atividades da pessoa");
        System.out.println("4- remover atividade");
        System.out.println("5- Consultar total de atividades");
        System.out.println("6- Recuperar ultima atividade");
        System.out.println("7- Limpar lista de atividades");
        System.out.println("8- Consultar ultima atividade realizada");
        System.out.println("9- Voltar");

        opt = scan.nextInt();

        switch (opt){
            case 1:
                atividade = adicionaAtividade();
                atividadePessoa.getAtividades().add(atividade);
                if(atividade != null && atividade != null && !atividadePessoa.getPessoas().contains(atividade.getPessoa())){
                   atividadePessoa.getPessoas().add(atividade.getPessoa());
                }
                System.out.println("Atividade adicionada");
                break;

        }
        }while (opt != voltar);
    }

    private static Atividade adicionaAtividade(){
        Atividade novaAtividade;
        Date horaInicio;
        Date horaFim;
        String descricao;
        String data;
        int opt;
        SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yy 00:00:00");

        System.out.println("Informe a descricao da atividade");
        descricao = scan.next();
        System.out.println("Informe a data e hora do Início (Formato 01/01/01 00:00:00)");
        data  = scan.nextLine();
        try {
            //TODO implemetar o retorno da data no formato Date aqui
        }catch (ParseException e){
            errorMessage("Hora de inicio em formato nao reconhecido");
            return null;
        }
        System.out.println("Informe a data e hora de Fim (Formato 01/01/01 00:00:00)");
        data = scan.next();
        try {
            //TODO implemetar o retorno da data no formato Date aqui
        }catch (ParseException  e){
            errorMessage("Hora de fim em formato nao reconhecido");
            return null;
        }

        novaAtividade = new Atividade(new Pessoa(),horaInicio,horaFim,descricao);

        System.out.println("Deseja associar atividade a uma pessoa?");
        System.out.println("1- Sim");
        System.out.println("2- Nao");
        opt = scan.nextInt();

        switch (opt){
            case 1:
                    novaAtividade.setPessoa(selecionaPessoa());
                    break;
                    default:
                        System.out.println("Opcao nao reconhecida");
        }
        System.out.println("Atividade nova adicionada");

        return novaAtividade;
    }

    private static void errorMessage(String mensagem){
        System.out.println( "\nFalha\n"+ mensagem);
    }

    private Date retornaDataDeString (String dataString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("");
        return sdf.parse(dataString);
    }
    private static void escreveCabecalho(String titulo) {
        System.out.println("------------------------------------");
        System.out.println("\t" + titulo);
        System.out.println("------------------------------------");
    }
}


