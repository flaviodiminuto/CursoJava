package aula20190323;

import br.com.microlins.Atividade;
import br.com.microlins.AtividadePessoa;
import br.com.microlins.Pessoa;

import java.text.ParseException;
import java.util.*;

public class Principal {
    private static final Scanner scan =new Scanner(System.in);
    private static final int SAIR = 3;
    private static AtividadePessoa atividadePessoas = new AtividadePessoa();


    public static void main(String[] args) {
        int idCategoria;
        Locale.setDefault(new Locale("pt", "BR"));

        atividadePessoas.getAtividades().add(new Atividade(new Pessoa(),Calendar.getInstance().getTime(),Calendar.getInstance().getTime()  ,"correr"));
        atividadePessoas.getAtividades().add(new Atividade(new Pessoa(),Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),"passear"));
        atividadePessoas.getAtividades().add(new Atividade(new Pessoa(),Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),"academia"));
        atividadePessoas.getAtividades().add(new Atividade(new Pessoa(),Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),"estudar"));

        atividadePessoas.getPessoas().add(new Pessoa("Flavio",30,60,2));
        atividadePessoas.getPessoas().add(new Pessoa("Sheila",27,50,1.8));
        atividadePessoas.getPessoas().add(new Pessoa("Carlos",30,20,1.6));

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
                                               .append(" Nao existe"));
            }
        } while (idCategoria != SAIR);
    }

    private static int exibeMenu() {
        final int sair = 3;
        int acao;
        boolean valido;
        do {
            valido = true;
            escreveCabecalho("Menu Inicial");
            System.out.println("Informe a categoria");
            System.out.println("1- Pessoa");
            System.out.println("2- Atividade");
            System.out.println(sair + "- Sair");
            acao = lerNumeroInteiro();

            switch (acao) {
                case 1:
                    selecionaOperacaoPessoa();
                    break;
                case 2:
                    selecionaOperacaoAtividade();
                    break;
                case sair:
                    valido = true;
                    break;
                default:
                    System.out.println("Opcao " + acao + " nao e valida!\n");
                    valido = false;
            }
        } while (!valido);
        return acao;
    }

    private static void selecionaOperacaoPessoa() {
        final int voltar = 4;
        int operacaoCategoriaPessoa;
        do {
            escreveCabecalho("Categoria Pessoa");
            System.out.println("OPERACOES");
            System.out.println("1- Cadastrar");
            System.out.println("2- Exibir dados");
            System.out.println("3- Calcular IMC");
            System.out.println(voltar + "- Voltar");
            operacaoCategoriaPessoa = lerNumeroInteiro();
            switch (operacaoCategoriaPessoa) {
                case 1:
                    operacaoCadastrarPessoa();
                    break;
                case 2:
                    if (atividadePessoas.getPessoas().size() > 0)
                        operacaoExibirDadosPessoa();
                    else
                        solicitarCadastroPessoa();
                    break;
                case 3:
                    if (atividadePessoas.getPessoas().size() > 0)
                        operacaoCalculaIMCPessoa();
                    else
                        solicitarCadastroPessoa();
                    break;
                case voltar:
                    break;
                default:
                    System.out.println("Operacao " + operacaoCategoriaPessoa + " nao e valida");
            }
        } while (operacaoCategoriaPessoa != voltar);
    }

    private static Pessoa solicitarCadastroPessoa() {
        System.out.println("Por favor, cadastre ao menos uma pessoa");
        return operacaoCadastrarPessoa();
    }

    private static Pessoa operacaoCadastrarPessoa() {
        String nome;
        int idade;
        Double peso, altura;

        escreveCabecalho("Cadastrando Pessoa");
        System.out.println("Informe o nome: ");
        nome = lerTexto();
        System.out.println("Informe a idade: ");
        idade = lerNumeroInteiro();
        System.out.println("Informe o peso: ");
        peso = lerNumeroReal();
        System.out.println("Informe a altura: ");
        altura = lerNumeroReal();

        Pessoa pessoa =  new Pessoa(nome, idade, peso, altura);
        atividadePessoas.getPessoas().add(pessoa);
        return pessoa;
    }

    private static Date lerHora() {
        int hora, minuto;
        Calendar data = Calendar.getInstance();

        System.out.println("Informe a hora: ");
        hora = scan.nextInt();
        System.out.println("Informe Minuto(s):");
        minuto = scan.nextInt();
        data.set(Calendar.HOUR, hora);
        data.set(Calendar.MINUTE,minuto);

        return data.getTime();
    }
    private static int lerNumeroInteiro() {
            return scan.nextInt();
    }

    private static Double lerNumeroReal() {
        return scan.nextDouble();
    }

    private static String lerTexto() {
        scan.nextLine();// Consume newline left-over
       return scan.nextLine();
    }

    private static void operacaoExibirDadosPessoa() {
        Pessoa pessoa = selecionaPessoa();
        if(pessoa != null)
            System.out.println(pessoa.exibirDados());
    }

    private static void operacaoCalculaIMCPessoa() {
        Pessoa pessoa = selecionaPessoa();
        if(pessoa != null)
        System.out.println(new StringBuilder()
                .append("Nome: ")
                .append(pessoa.getNome())
                .append("\tIMC: ")
                .append(pessoa.calculaImc()));
    }

    private static Pessoa selecionaPessoa() {
        int indicePessoa;
        if (atividadePessoas.getPessoas().isEmpty()) {
            solicitarCadastroPessoa();
            return null;
        }
        do {
            escreveCabecalho("Pessoas");
            for (Pessoa pessoa : atividadePessoas.getPessoas()) {
                System.out.println(String.format("Pessoa: %d%n%s", atividadePessoas.getPessoas().indexOf(pessoa), pessoa.getNome()));
                System.out.println("------------------------------------");
            }
            System.out.println("Indique o numero de uma Pessoa da Lista acima: ");
            indicePessoa = lerNumeroInteiro();
        } while (indicePessoa < 0 || indicePessoa > atividadePessoas.getPessoas().size());
        return atividadePessoas.getPessoas().get(indicePessoa);
    }

    private static void selecionaOperacaoAtividade() {
        int opt;
        final int voltar = 9;
        Atividade atividade;
        do {
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

            opt = lerNumeroInteiro();

            switch (opt) {
                case 1:
                    atividade = adicionaAtividade();
                    if (atividade == null){
                        System.out.println("Falha ao adicionar atividade");
                        continue;}
                    else
                        atividadePessoas.getAtividades().add(atividade);
                    if (atividade.getPessoa() != null && !atividadePessoas.getPessoas().contains(atividade.getPessoa())) {
                        atividadePessoas.getPessoas().add(atividade.getPessoa());
                    }
                    break;
                case 2:
                    addPessoaDaAtividade(selecionaAtividade());
                    break;
                case 3:
                    consultarAtividadesPessoa();
                    break;
                case 4:
                    removerAtividadePessoa();
                    break;
                case 5:
                    System.out.println(String.format("Total de atividades registradas: %02d",consultarTotalAtividades()));
                    break;
                case 6:
                    System.out.println(new StringBuilder()
                            .append("Ultima atividade adicionada\n")
                            .append(atividadePessoas.getAtividades().get(atividadePessoas.getAtividades().size()-1)));
                    break;
                case 7:
                    atividadePessoas.getAtividades().clear();
                    break;
                case 8:
                    System.out.println(new StringBuilder()
                            .append("Ultima atividade realizada\n")
                            .append(ultimaAtividadeRealizada().resumoAtividade()));
                    break;
                case voltar:
                    break;
                default:
                    System.out.println("Opcao nao reconhecida");
            }
        } while (opt != voltar);
    }

    private static Atividade ultimaAtividadeRealizada() {
        Atividade ultima = atividadePessoas.getAtividades().get(0);
        for(Atividade atividade : atividadePessoas.getAtividades()){
            if(atividade.getHoraFim().after( ultima.getHoraFim())) ultima = atividade;
        }
        return ultima;
    }

    private static int consultarTotalAtividades() {
        int totalAtividades = atividadePessoas.getAtividades().size();
        return totalAtividades;
    }

    private static boolean removerAtividadePessoa() {
        return atividadePessoas.getAtividades().remove(selecionaAtividade());
    }

    private static void consultarAtividadesPessoa() {
        Pessoa pessoa = selecionaPessoa();
        int quantidadeAtividades = 0;
        System.out.println("Informe a pessoa que deseja consutar as atividades");
        for(Atividade atividade : atividadePessoas.getAtividades()){
            if(atividade.getPessoa().equals(pessoa)) {
                System.out.println(atividade.resumoAtividade()+"\n");
                quantidadeAtividades++;
            }
        }
        if(quantidadeAtividades>0)
            System.out.println(String.format("%s tem %02d atividades registradas ",pessoa.getNome(),quantidadeAtividades));
        else
            System.out.println("Nao ha atividades registradas para esta pessoa");
    }

    private static Atividade selecionaAtividade() {
        int indice;
        for(int i = 0; i<atividadePessoas.getAtividades().size(); i++){
            System.out.println("Atividade: "+i+"\n"+atividadePessoas.getAtividades().get(i).resumoAtividade());
        }
        System.out.println("Selecione o numero da atividade que deseja");
        indice = scan.nextInt();
        return atividadePessoas.getAtividades().get(indice);
    }

    private static Atividade adicionaAtividade() {
        Atividade novaAtividade;
        Date horaInicio;
        Date horaFim;
        String descricao;
        int opt;

        System.out.println("Informe a descricao da atividade");
        descricao = lerTexto();

        System.out.println("Informe o horario de inicio da atividade");
        horaInicio = lerHora();

        System.out.println("Informe o horario de termino da atividade");
        horaFim = lerHora();

        novaAtividade = new Atividade(new Pessoa(), horaInicio, horaFim, descricao);

        System.out.println("Deseja associar atividade a uma pessoa?");
        System.out.println("1- Sim");
        System.out.println("2- Nao");
        opt = lerNumeroInteiro();

        if(opt ==1) {
            addPessoaDaAtividade(novaAtividade);
            System.out.println("Atividade adicionada");
        }else{
                System.out.println("Opcao nao reconhecida");
                atividadePessoas.getAtividades().add(novaAtividade);
                System.out.println("Atividade salva sem adicionar pessoa");
        }
        return novaAtividade;
    }

    private static void addPessoaDaAtividade(Atividade atividade) {
        if(atividadePessoas.getPessoas().isEmpty()) {
            addPessoaDaAtividade(new Atividade(solicitarCadastroPessoa(),atividade.getHoraInicio(),atividade.getHoraFim(),atividade.getDescricao()));
            System.out.println("nao ha pessoa cadastrada para adicionar a atiidade");
        }
        else {
            atividade.setPessoa(selecionaPessoa());
        }
    }

    private static void errorMessage(String mensagem) {
        System.out.println("\nFalha\n" + mensagem);
    }


    private static void escreveCabecalho(String titulo) {
        System.out.println("------------------------------------");
        System.out.println("\t" + titulo);
        System.out.println("------------------------------------");
    }
}
