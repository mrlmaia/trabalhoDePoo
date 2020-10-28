package trabalhodepoo;

import java.util.Scanner;

/**
 *
 * @author mrlmaia and yasminclelia
 */
public class Main {

    static Scanner scn = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        int opcao;
        do {
            System.out.println("Informe a opção desejada:");
            System.out.println("0- SAIR");
            System.out.println("1- Cadastrar disciplina");
            System.out.println("2- Excluir disciplina");
            System.out.println("3- Listar disciplinas");
            System.out.println("4- Cadastrar aluno");
            System.out.println("5- Editar aluno");
            System.out.println("6- Detalhar aluno");
            System.out.println("7- Listar alunos");
            System.out.println("8- Matricular aluno");

            opcao = scn.nextInt();

            Aluno aluno;
            Disciplina disciplina;
            long codigo;
            switch (opcao) {
                case 1:
                    disciplina = lerDisciplina();
                    boolean existe = sistema.nomeDaDisciplinaExiste(disciplina.getNome());
                    if (existe) {
                        System.err.println("Uma disciplina com nome " + disciplina.getNome() + " já foi cadastrada");
                    } else {
                        // tenta fazer a inserção da disciplina
                        boolean r = sistema.inserirDisciplina(disciplina);
                        if (r) {
                            System.out.println("Disciplina cadastrada com sucesso");
                        } else {
                            System.err.println("Erro ao cadastrar disciplina");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Informe o código da disciplina que voce deseja excluir");
                    codigo = scn.nextLong();
                    boolean r = sistema.removerDisciplina(codigo);
                    if (r) {
                        System.out.println("Disciplina removida com sucesso");
                    } else {
                        System.err.println("Erro ao excluir disciplina");
                    }
                    break;
                case 3:
                    if (sistema.getNumeroDeDisciplinas() > 0) {
                        listarDisciplinas(sistema.getDisciplinas());
                    } else {
                        System.err.println("Nenhuma disciplina cadastrada");
                    }
                    break;
                case 4:
                    aluno = lerAluno();

                    if (sistema.alunoExiste(aluno.getNome(), aluno.getNomeDaMae())) {
                        System.err.println("Um aluno com nome " + aluno.getNome() + " e nome da mãe "
                                + aluno.getNomeDaMae() + " já foi cadastrado");
                    } else {
                        // tenta fazer a inserção da disciplina
                        boolean inserirAluno = sistema.inserirAluno(aluno);
                        if (inserirAluno) {
                            System.out.println("Aluno cadastrado com sucesso");
                        } else {
                            System.err.println("Erro ao cadastrar aluno");
                        }
                    }
                    break;
                // case 5:
                // System.out.println("Digite o código do aluno que você deseja alterar");
                // codigo = scn.nextLong();
                // Aluno alunoParaEditar = sistema.buscarAluno(codigo);
                // if (alunoParaEditar == null){
                // System.err.println("Nenhum aluno encontrado com o código: "+ codigo);
                // } else {
                // alunoParaEditar = editarAluno(alunoParaEditar, sistema);
                // if(sistema.alterarAluno(alunoParaEditar)){
                // System.out.println("Aluno editado com sucesso");
                // } else {
                // System.err.println("Erro ao editar aluno");
                // }
                // }
                // break;
                case 6:
                    System.out.println("Digite o código do aluno que você deseja detalhar");
                    codigo = scn.nextLong();
                    Aluno alunoParaExibir = sistema.buscarAluno(codigo);
                    if (alunoParaExibir == null) {
                        System.err.println("Aluno com o código " + codigo + " não encontrado");
                    } else {
                        detalharAluno(alunoParaExibir);
                    }
                    break;
                case 7:
                    listarAlunos(sistema.getAlunos());
                    break;
                case 8:
                    System.out.println("Digite o código do aluno: ");
                    codigo = scn.nextInt();
                    aluno = sistema.buscarAluno(codigo);

                    System.out.println("Digite o código da disciplina");
                    codigo = scn.nextInt();
                    disciplina = sistema.buscarDisciplina(codigo);

                    if (!sistema.alunoEstaMatriculado(aluno, disciplina)) {
                        if (sistema.matricularAluno(aluno, disciplina)) {
                            System.out.println("Aluno matriculado com sucesso");
                        } else {
                            System.out.println("Não foi possível matricular o aluno " + aluno.getNome() + " código "
                                    + aluno.getCodigo() + " na disciplina de  " + disciplina.getNome() + " código "
                                    + disciplina.getCodigo());
                        }
                    } else {
                        System.out.println(
                                "Aluno " + aluno.getNome() + " código " + aluno.getCodigo() + " já está matriculado em "
                                        + disciplina.getNome() + " código " + disciplina.getCodigo());
                    }

                    break;
                default:
                    break;
            }

        } while (opcao != 0);
    }

    static Disciplina lerDisciplina() {
        Disciplina disciplina = new Disciplina();
        System.out.print("Nome: ");
        disciplina.setNome(scn.next());
        System.out.print("Ano: ");
        disciplina.setAno(scn.nextInt());
        System.out.print("Numero de Vagas: ");
        disciplina.setNumeroDeVagas(scn.nextInt());
        System.out.print("Nome Professor: ");
        disciplina.setNomeDoProfessor(scn.next());

        return disciplina;
    }

    static void listarDisciplinas(Disciplina[] disciplinas) {
        if (disciplinas != null) {
            System.out.printf("%s %-16s %-12s%8s\n", "Cod", "Nome", "Alunos Mat.", "Nome Prof");
            for (int i = 0; i < disciplinas.length; i++) {
                if (disciplinas[i] != null)
                    System.out.printf("%03d %-16.16s %-12d%-8s\n", disciplinas[i].getCodigo(), disciplinas[i].getNome(),
                            disciplinas[i].getNumeroDeAlunosMatriculados(), disciplinas[i].getNomeDoProfessor());

            }
        }
    }

    private static Aluno lerAluno() {
        Aluno aluno = new Aluno();
        System.out.print("Nome: ");
        aluno.setNome(scn.next());
        System.out.print("Nome da mãe: ");
        aluno.setNomeDaMae(scn.next());
        System.out.print("Nome do pai: ");
        aluno.setNomeDoPai(scn.next());
        System.out.print("Endereço: ");
        aluno.setEndereço(scn.next());
        return aluno;
    }

    private static Aluno editarAluno(Aluno alunoParaEditar, Sistema sistema) {
        Aluno aluno = new Aluno();

        int opcao;
        do {
            System.out.println("Informe os dados que deseja editar do aluno.");
            System.out.println("0 - Sair");
            System.out.println("1 - Nome");
            System.out.println("2 - Nome da mãe");
            System.out.println("3 - Nome do pai");
            System.out.println("4 - Endereço");

            opcao = scn.nextInt();

            switch (opcao) {
                case 0:
                    return aluno;
                case 1:
                    System.out.println("Informe o nome do aluno. (" + aluno.getNome() + ")");
                    String nome = scn.next();
                    aluno.setNome(nome);
                    break;
                case 2:
                    System.out.println("Informe o nome da mãe. (" + aluno.getNomeDaMae() + ")");
                    String nomeDaMae = scn.next();
                    aluno.setNomeDaMae(nomeDaMae);
                case 3:
                    System.out.println("Informe o nome do pai. (" + aluno.getNomeDoPai() + ")");
                    String nomeDoPai = scn.next();
                    aluno.setNomeDoPai(nomeDoPai);
                    break;
                case 4:
                    System.out.println("Informe um endereço. (" + aluno.getEndereço() + ")");
                    String endereco = scn.next();
                    aluno.setEndereço(endereco);
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
        return aluno;
    }

    private static void listarAlunos(Aluno[] alunos) {
        if (alunos != null) {
            System.out.printf("%s %-18s %-12s\n", "Cod", "Nome", "Nome mãe.");
            for (Aluno aluno : alunos) {
                if (aluno != null) {
                    System.out.printf("%s %-18s %-12s\n", aluno.getCodigo(), aluno.getNome(), aluno.getNomeDaMae());
                }
            }
        }
    }

    private static void detalharAluno(Aluno aluno) {
        aluno.getCodigo();
        aluno.getNomeDaMae();
        aluno.getNomeDoPai();
        aluno.getDisciplinasMatriculadas();
        aluno.getNotasNasDisciplinas();
        aluno.getEndereço();

        if (aluno != null) {
            System.out.println("Código: " + aluno.getCodigo());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Nome da mãe: " + aluno.getNomeDaMae());
            System.out.println("Nome do pai: " + aluno.getNomeDoPai());
            System.out.println("Endereço: " + aluno.getEndereço());
            for (int i = 0; i < aluno.getDisciplinasMatriculadas().length; i++) {
                if (aluno.getDisciplinasMatriculadas()[i] != null) {
                    System.out.println("Nota na disciplina: " + aluno.getDisciplinasMatriculadas()[i].getNome() + ": "
                            + aluno.getNotasNasDisciplinas()[i]);
                }
            }
        }

    }

}
