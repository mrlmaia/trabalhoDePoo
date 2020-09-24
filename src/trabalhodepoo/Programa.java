package trabalhodepoo;

import java.util.Scanner;

/**
 *
 * @author mrlmaia and yasminclelia
 */
public class Programa {

    static Scanner scn = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Disciplina[] disciplinas = new Disciplina[3];
        Aluno[] alunos = new Aluno[3];

        int numeroDeDisciplinas = 0;
        int numeroDeAlunos = 0;

        int opcao;
        do {
            System.out.println("Informe a opção desejada:");
            System.out.println("0- SAIR");
            System.out.println("1- Cadastrar disciplina");
            System.out.println("2- Excluir disciplina");
            System.out.println("3- Listar disciplinas");
            System.out.println("4- Cadastrar aluno");
            System.out.println("7- Listar alunos");

            opcao = scn.nextInt();

            switch (opcao) {
                case 1:
                    Disciplina disciplina = lerDisciplina();
                    boolean existe = nomeDaDisciplinaExiste(disciplinas, disciplina);
                    if (existe) {
                        System.out.println("Uma disciplina com nome " + disciplina.nome + " já foi cadastrada");
                    } else {
                        // tenta fazer a inserção da disciplina
                        boolean r = inserirDisciplina(disciplinas, disciplina, numeroDeDisciplinas);
                        if (r) {
                            System.out.println("Disciplina cadastrada com sucesso");
                            numeroDeDisciplinas++;
                        } // aumenta o tamanho do vetor e tenta inserir de novo
                        else if (numeroDeDisciplinas == disciplinas.length) {
                            disciplinas = aumentarVetor(disciplinas);

                            // tenta fazer a inserção novamente
                            boolean r2 = inserirDisciplina(disciplinas, disciplina, numeroDeDisciplinas);
                            if (r2) {
                                System.out.println("Disciplina cadastrada com sucesso");
                                numeroDeDisciplinas++;
                            } else {
                                System.out.println("Erro ao cadastrar disciplina");
                            }
                        } else {
                            System.out.println("Erro ao cadastrar disciplina");
                        }
                    }
                    break;
                case 2:
                    removerDisciplina(disciplinas);
                    break;
                case 3:
                    if (numeroDeDisciplinas > 0) {
                        listarDisciplinas(disciplinas);
                    } else {
                        System.out.println("Nenhuma disciplina cadastrada");
                    }
                    break;
                case 4:
                    Aluno aluno = lerAluno();

                    if (alunoExiste(alunos, aluno)) {
                        System.out.println("Um aluno com nome " + aluno.nome
                                + " e nome da mãe " + aluno.nomeDaMae + " já foi cadastrado");
                    } else {
                        // tenta fazer a inserção da disciplina
                        boolean r = inserirAluno(alunos, aluno, numeroDeAlunos);
                        if (r) {
                            System.out.println("Aluno cadastrado com sucesso");
                            numeroDeAlunos++;
                        } // aumenta o tamanho do vetor e tenta inserir de novo
                        else if (numeroDeAlunos == alunos.length) {
                            alunos = aumentarAlunos(alunos);

                            // tenta fazer a inserção novamente
                            boolean r2 = inserirAluno(alunos, aluno, numeroDeAlunos);
                            if (r2) {
                                System.out.println("Aluno cadastrado com sucesso");
                                numeroDeAlunos++;
                            } else {
                                System.out.println("Erro ao cadastrar aluno");
                            }
                        } else {
                            System.out.println("Erro ao cadastrar disciplina");
                        }
                    }
                    break;
                case 7:
                    listarAlunos(alunos);
                    break;
                default:
                    break;
            }

        } while (opcao != 0);
    }

    static Disciplina lerDisciplina() {
        Disciplina disciplina = new Disciplina();
        System.out.print("Nome: ");
        disciplina.nome = scn.next();
        System.out.print("Ano: ");
        disciplina.ano = scn.nextInt();
        System.out.print("Numero de Vagas: ");
        disciplina.numeroDeVagas = scn.nextInt();
        System.out.print("Nome Professor: ");
        disciplina.nomeDoProfessor = scn.next();

        return disciplina;
    }

    static boolean nomeDaDisciplinaExiste(Disciplina[] disciplinas, Disciplina disciplina) {
        if (disciplinas != null && disciplina != null) {
            for (int i = 0; i < disciplinas.length; i++) {
                if (disciplinas[i] != null && disciplinas[i].nome.equalsIgnoreCase(disciplina.nome)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    static boolean inserirDisciplina(Disciplina[] discisiplinas, Disciplina disciplina, int numeroDeDisciplinas) {
        if (discisiplinas != null && disciplina != null) {
            for (int i = 0; i < discisiplinas.length; i++) {
                if (discisiplinas[i] == null) {
                    disciplina.codigo = numeroDeDisciplinas + 1;
                    discisiplinas[i] = disciplina;
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private static Disciplina[] aumentarVetor(Disciplina[] disciplinas) {
        Disciplina[] aux = new Disciplina[2 * disciplinas.length];
        for (int i = 0; i < disciplinas.length; i++) {
            aux[i] = disciplinas[i];
        }
        disciplinas = aux;
        return disciplinas;
    }

    static void listarDisciplinas(Disciplina[] disciplinas) {
        if (disciplinas != null) {
            System.out.println("Codigo\tNome\tVagas\tProfessor");
            for (int i = 0; i < disciplinas.length; i++) {
                if (disciplinas[i] != null) {
                    System.out.println(disciplinas[i].codigo
                            + "\t" + disciplinas[i].nome
                            + "\t" + disciplinas[i].numeroDeVagas
                            + "\t" + disciplinas[i].nomeDoProfessor);
                }
            }

        }
    }

    static void removerDisciplina(Disciplina[] discs) {
        System.out.println("Not");
    }

    private static Aluno lerAluno() {
        Aluno aluno = new Aluno();
        System.out.print("Nome: ");
        aluno.nome = scn.next();
        System.out.print("Nome da mãe: ");
        aluno.nomeDaMae = scn.next();
        System.out.print("Nome do pai: ");
        aluno.nomeDoPai = scn.next();
        System.out.print("Endereço: ");
        aluno.endereço = scn.next();
        return aluno;
    }

    private static boolean alunoExiste(Aluno[] alunos, Aluno aluno) {
        if (alunos != null && aluno != null) {
            for (Aluno a : alunos) {
                if (a != null && aluno.nome.equalsIgnoreCase(a.nome)
                        && aluno.nomeDaMae.equalsIgnoreCase(a.nomeDaMae)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean inserirAluno(Aluno[] alunos, Aluno aluno, int numeroDeAlunos) {
        if (alunos != null && aluno != null) {
            for (int i = 0; i < alunos.length; i++) {
                if (alunos[i] == null) {
                    aluno.codigo = numeroDeAlunos + 1;
                    alunos[i] = aluno;
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private static Aluno[] aumentarAlunos(Aluno[] alunos) {
        Aluno[] aux = new Aluno[2 * alunos.length];
        for (int i = 0; i < alunos.length; i++) {
            aux[i] = alunos[i];
        }
        alunos = aux;
        return alunos;
    }

    private static void listarAlunos(Aluno[] alunos) {
        if (alunos != null) {
            System.out.println("Codigo\tNome\tMãe");
            for (int i = 0; i < alunos.length; i++) {
                if (alunos[i] != null) {
                    System.out.println(alunos[i].codigo
                            + "\t" + alunos[i].nome
                            + "\t" + alunos[i].nomeDaMae);
                }
            }

        }
    }

}
