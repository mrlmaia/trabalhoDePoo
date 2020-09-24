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
        Disciplina[] disciplinas = new Disciplina[10];
        int numeroDeDisciplinas = 0;
        int opcao;
        do {
            System.out.println("Informe a opção desejada:");
            System.out.println("0- SAIR");
            System.out.println("1- Cadastrar disciplina");
            System.out.println("2- Excluir disciplina");
            System.out.println("3- Listar disciplinas");
            opcao = scn.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarDisciplina(disciplinas, numeroDeDisciplinas);
                    break;
                case 2:
                    removerDisciplina(disciplinas);
                    break;
                case 3:
                    listarDisciplinas(disciplinas);
                    break;
                case 4:
                    break;
                default:
                    break;
            }

        } while (opcao != 0);
    }

    private static void cadastrarDisciplina(Disciplina[] disciplinas, int numeroDeDisciplinas) {
        System.out.println("CADASTRAR DISCIPLINA");
        Disciplina disciplina = lerDisciplina();
        boolean existe = nomeExiste(disciplinas, disciplina);
        if (existe) {
            System.out.println("Uma disciplina com nome " + disciplina.nome + " já foi cadastrada");
        } else {
            // tenta fazer a inserção da disciplina
            boolean r = inserir(disciplinas, disciplina);
            if (r) {
                System.out.println("Disciplina cadastrada com sucesso");
                numeroDeDisciplinas++;
            } // aumenta o tamanho do vetor e tenta inseri de novo
            else if (numeroDeDisciplinas == disciplinas.length) {
                disciplinas = aumentarVetor(disciplinas);

                // tenta fazer a inserção novamente
                boolean r2 = inserir(disciplinas, disciplina);
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

    static boolean nomeExiste(Disciplina[] disciplinas, Disciplina disciplina) {
        if (disciplinas != null && disciplina != null) {
            for (int i = 0; i < disciplinas.length; i++) {
                if (disciplinas[i] != null && disciplina.nome == disciplinas[i].nome) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    static boolean inserir(Disciplina[] discsiplinas, Disciplina disciplina) {
        if (discsiplinas != null && disciplina != null) {
            for (int i = 0; i < discsiplinas.length; i++) {
                if (discsiplinas[i] == null) {
                    discsiplinas[i] = disciplina;
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public static Disciplina[] aumentarVetor(Disciplina[] disciplinas) {
        Disciplina[] aux = new Disciplina[2 * disciplinas.length];
        for (int i = 0; i < disciplinas.length; i++) {
            aux[i] = disciplinas[i];
        }
        disciplinas = aux;
        return disciplinas;
    }

    static void listarDisciplinas(Disciplina[] discs) {
        if (discs != null) {
            if (discs.length == 0) {
                System.out.println("Nenhhuma disciplina cadastrada");
            } else {
                System.out.println("Codigo\tNome\tNr Vagas\tProfessor");
                for (int i = 0; i < discs.length; i++) {
                    if (discs[i] != null) {
                        System.out.println(discs[i].codigo + 
                                "\t" + discs[i].nome + 
                                "\t" + discs[i].numeroDeVagas + 
                                "\t" + discs[i].nomeDoProfessor);
                    }
                }
            }
        }
    }

    static void removerDisciplina(Disciplina[] discs) {
        System.out.println("Not");
    }

}
