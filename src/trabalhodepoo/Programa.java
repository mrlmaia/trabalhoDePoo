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

        init(disciplinas, alunos);

        int numeroDeDisciplinas = 3;
        int numeroDeAlunos = 2;

        int opcao;
        do {
            System.out.println("Informe a opção desejada:");
            System.out.println("0- SAIR");
            System.out.println("1- Cadastrar disciplina");
            System.out.println("2- Excluir disciplina");
            System.out.println("3- Listar disciplinas");
            System.out.println("4- Cadastrar aluno");
            System.out.println("7- Listar alunos");
            System.out.println("8- Matricular aluno");

            opcao = scn.nextInt();

            Aluno aluno;
            Disciplina disciplina;
            switch (opcao) {
                case 1:
                    disciplina = lerDisciplina();
                    boolean existe = nomeDaDisciplinaExiste(disciplinas, disciplina);
                    if (existe) {
                        System.err.println("Uma disciplina com nome " + disciplina.nome + " já foi cadastrada");
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
                                System.err.println("Erro ao cadastrar disciplina");
                            }
                        } else {
                            System.err.println("Erro ao cadastrar disciplina");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Informe o código da disciplina que voce deseja excluir");
                    int codigo = scn.nextInt();
                    disciplinas = removerDisciplina(disciplinas, alunos, codigo);
                    break;
                case 3:
                    if (numeroDeDisciplinas > 0) {
                        listarDisciplinas(disciplinas);
                    } else {
                        System.err.println("Nenhuma disciplina cadastrada");
                    }
                    break;
                case 4:
                    aluno = lerAluno();

                    if (alunoExiste(alunos, aluno)) {
                        System.err.println("Um aluno com nome " + aluno.nome
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
                case 8:
                    System.out.println("Digite o código do aluno: ");
                    codigo = scn.nextInt();
                    aluno = buscarAluno(alunos, codigo);

                    System.out.println("Digite o código da disciplina");
                    codigo = scn.nextInt();
                    disciplina = buscarDisciplina(disciplinas, codigo);

                    matricularAluno(aluno, disciplina);

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

    static Disciplina[] removerDisciplina(Disciplina[] disciplinas, Aluno[] alunos, int codigo) {
        if (disciplinas != null && alunos != null) {
            for (int i = 0; i < disciplinas.length; i++) {
                if (disciplinas[i] != null && disciplinas[i].codigo == codigo) {
                    disciplinas[i] = null;
                    System.out.println("Disciplina excluída com sucesso");
                }
            }
        }
        return disciplinas;
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

    private static Aluno buscarAluno(Aluno[] alunos, int codigo) {
        if (alunos != null) {
            for (Aluno aluno : alunos) {
                if (aluno != null && aluno.codigo == codigo) {
                    return aluno;
                }
            }
        }
        return null;
    }

    private static Disciplina buscarDisciplina(Disciplina[] disciplinas, int codigo) {
        if (disciplinas != null) {
            for (Disciplina disciplina : disciplinas) {
                if (disciplina != null && disciplina.codigo == codigo) {
                    return disciplina;
                }
            }
        }
        return null;
    }

    private static void matricularAluno(Aluno aluno, Disciplina disciplina) {
        if (aluno != null && disciplina != null) {
            boolean matriculado = false;
            boolean temVagas = false;
            for (Disciplina d : aluno.disciplinasMatriculadas) {
                if (d != null) {
                    if (d.codigo == disciplina.codigo) {
                        matriculado = true;
                        System.err.println("Aluno já matriculado na disciplina");
                    }
                }
            }
            if (disciplina.numeroDeAlunosMatriculados < disciplina.numeroDeVagas) {
                temVagas = true;
            }
            if (!matriculado && temVagas) {
                boolean adicionada = false;
                for (int i = 0; i < aluno.disciplinasMatriculadas.length; i++) {
                    if (aluno.disciplinasMatriculadas[i] == null && !adicionada) {
                        aluno.disciplinasMatriculadas[i] = disciplina;
                        adicionada = true;
                    }

                }

                System.out.println("Disciplinas do aluno " + aluno.codigo);
                for (int i = 0; i < aluno.disciplinasMatriculadas.length; i++) {
                    if (aluno.disciplinasMatriculadas[i] != null) {
                        System.out.println(aluno.disciplinasMatriculadas[i].nome + " " + aluno.disciplinasMatriculadas[i].ano);
                    }
                }
            }
        } else {
            System.err.println("Erro ao matricular aluno");
        }
    }

    private static void init(Disciplina[] disciplinas, Aluno[] alunos) {
        Disciplina poo = new Disciplina();
        poo.codigo = 1;
        poo.nome = "Poo";
        poo.ano = 2021;
        poo.nomeDoProfessor = "Luciano";
        poo.numeroDeVagas = 3;
        disciplinas[0] = poo;

        Disciplina pw2 = new Disciplina();
        pw2.codigo = 2;
        pw2.nome = "Pw";
        pw2.ano = 2021;
        pw2.nomeDoProfessor = "Dilão";
        pw2.numeroDeVagas = 3;
        disciplinas[1] = pw2;

        Disciplina fp = new Disciplina();
        fp.codigo = 3;
        fp.nome = "Fp";
        fp.ano = 2021;
        fp.nomeDoProfessor = "Maurilio";
        fp.numeroDeVagas = 3;
        disciplinas[2] = fp;

        Aluno fulano = new Aluno();
        fulano.codigo = 1;
        fulano.nome = "Fulano";
        fulano.nomeDaMae = "Fulana";
        fulano.nomeDoPai = "Fulanao";
        fulano.endereço = "Lua";
        fulano.disciplinasMatriculadas[0] = poo;
        poo.numeroDeAlunosMatriculados++;
        fulano.disciplinasMatriculadas[1] = pw2;
        pw2.numeroDeAlunosMatriculados++;
        alunos[0] = fulano;

        Aluno ciclano = new Aluno();
        ciclano.codigo = 2;
        ciclano.nome = "ciclano";
        ciclano.nomeDaMae = "Fulana";
        ciclano.nomeDoPai = "Fulanao";
        ciclano.endereço = "Lua";
        ciclano.disciplinasMatriculadas[0] = fp;
        fp.numeroDeAlunosMatriculados++;
        alunos[1] = ciclano;
    }

}
