package aula20190323;

import br.com.microlins.Atividade;
import br.com.microlins.Pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramaPrincipal {
    private static final Scanner scan = new Scanner(System.in);
    private static final int SAIR = 3;
    private static List<Pessoa> pessoas = new ArrayList<>();
    private static List<Atividade> atividades = new ArrayList<>();

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
        int acao;
        boolean valido;
        do {
            valido = true;
            escreveCabecalho("Menu Inicial");
            System.out.println("Informe a categoria");
            System.out.println("1- Pessoa");
            System.out.println("2- Atividade");
            System.out.println("3- Sair");
            acao = scan.nextInt();
            if (acao > 3 || acao < 1) {
                System.out.println("Opcao " + acao + " nao e valida!\n");
                valido = false;
            }
        } while (!valido);
        return acao;
    }

    private static void selecionaOperacaoPessoa() {
        int sair =4;
        int operacaoCategoriaPessoa;
        do {
            escreveCabecalho("Categoria Pessoa");
            System.out.println("OPERACOES");
            System.out.println("1- Cadastrar");
            System.out.println("2- Exibir dados");
            System.out.println("3- Calcular IMC");
            System.out.println(""+sair+"- Voltar");
            operacaoCategoriaPessoa = scan.nextInt();
            switch (operacaoCategoriaPessoa) {
                case 1:
                    pessoas.add(operacaoCadastrarPessoa());
                    break;
                case 2:
                    if(pessoas.size()>0)
                        operacaoExibirDadosPessoa();
                    else
                        solicitarCadastroPessoa();
                    break;
                case 3:
                    if(pessoas.size()>0)
                        operacaoCalculaIMCPessoa();
                    else
                        solicitarCadastroPessoa();
                    break;
                    default:
                        System.out.println("Operacao "+operacaoCategoriaPessoa+" nao e valida");
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
    private static void operacaoCalculaIMCPessoa(){
        Pessoa pessoa = selecionaPessoa();
        System.out.println(new StringBuilder()
                .append("Nome: ")
                .append(pessoa.getNome())
                .append("\tIMC: ")
                .append(pessoa.calculaImc()));
    }

    private static Pessoa selecionaPessoa(){
        int indicePessoa;
        do {
            escreveCabecalho("Pessoas");
            for (Pessoa pessoa : pessoas) {
                System.out.println(String.format("Pessoa: %d%n%s", pessoas.indexOf(pessoa), pessoa.getNome()));
                System.out.println("------------------------------------");
            }
            System.out.println("Indique o numero de uma Pessoa da Lista acima: ");
            indicePessoa = scan.nextInt();
        }while (indicePessoa<0 || indicePessoa>pessoas.size());
        return pessoas.get(indicePessoa);
    }

    private static void categoriaAtividade() {
        //28/03/2019   22:55 continuar aqui
    }

    private static void escreveCabecalho(String titulo){
        System.out.println("------------------------------------");
        System.out.println("\t"+titulo);
        System.out.println("------------------------------------");
    }
}


