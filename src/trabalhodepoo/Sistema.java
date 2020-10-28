package trabalhodepoo;

public class Sistema {

    private Disciplina[] disciplinas;
    private Aluno[] alunos;
    private int proximoCodigoDisciplina, proximoCodigoAluno;
    private int numeroDeAlunos;
    private int numeroDeDisciplinas;

    public Sistema() {
        disciplinas = new Disciplina[6];
        alunos = new Aluno[6];
        numeroDeAlunos = 0;
        numeroDeDisciplinas = 0;
        init();
        proximoCodigoDisciplina = 6;
        proximoCodigoAluno = 4;
    }

    public Disciplina[] getDisciplinas() {
        return disciplinas;
    }

    public int getNumeroDeDisciplinas() {
        return numeroDeDisciplinas;
    }

    public Aluno[] getAlunos() {
        return alunos;
    }

    public int getNumeroDeAlunos() {
        return numeroDeAlunos;
    }

    public boolean nomeDaDisciplinaExiste(String nome) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina != null && disciplina.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean inserirDisciplina(Disciplina disciplina) {
        if (disciplina == null) {
            return false;
        }
        for (int i = 0; i < this.disciplinas.length; i++) {
            if (disciplinas[i] == null) {
                disciplina.setCodigo(proximoCodigoDisciplina);
                disciplinas[i] = disciplina;
                numeroDeDisciplinas++;
                proximoCodigoDisciplina++;
                return true;
            }
        }
        if (numeroDeDisciplinas == disciplinas.length) {
            disciplinas = aumentarDisciplinas();
            for (int i = 0; i < this.disciplinas.length; i++) {
                if (disciplinas[i] == null) {
                    disciplina.setCodigo(proximoCodigoDisciplina);
                    disciplinas[i] = disciplina;
                    numeroDeDisciplinas++;
                    proximoCodigoDisciplina++;
                    return true;
                }
            }
        }
        return false;
    }

    private Disciplina[] aumentarDisciplinas() {
        Disciplina[] aux = new Disciplina[2 * disciplinas.length];
        // vetor a ser copiado, posicao inicial, valor que vai receber o valor, numero de valores q serao copiados
        System.arraycopy(disciplinas, 0, aux, 0, disciplinas.length);
        return aux;
    }

    public boolean removerDisciplina(long codigo) {
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null && disciplinas[i].getCodigo() == codigo) {
                disciplinas[i] = null;
                numeroDeDisciplinas--;
                return true;
            }
        }
        return false;
    }

    public boolean alunoExiste(String nome, String nomeDaMae) {
        for (Aluno a : alunos) {
            if (a != null && nome.equalsIgnoreCase(a.getNome())
                    && nomeDaMae.equalsIgnoreCase(a.getNomeDaMae())) {
                return true;
            }
        }
        return false;
    }

    public boolean inserirAluno(Aluno aluno) {
        if (aluno == null) {
            return false;
        }

        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] == null) {
                aluno.setCodigo(proximoCodigoAluno);
                alunos[i] = aluno;
                numeroDeAlunos++;
                proximoCodigoAluno++;
                return true;
            }
        }

        if (numeroDeAlunos == alunos.length) {
            alunos = aumentarAlunos();
            for (int i = 0; i < this.alunos.length; i++) {
                if (alunos[i] == null) {
                    aluno.setCodigo(proximoCodigoAluno);
                    alunos[i] = aluno;
                    numeroDeAlunos++;
                    proximoCodigoAluno++;
                    return true;
                }
            }
        }
        return false;
    }

    private Aluno[] aumentarAlunos() {
        Aluno[] aux = new Aluno[2 * alunos.length];
        System.arraycopy(alunos, 0, aux, 0, alunos.length);
        return aux;
    }

    public Aluno buscarAluno(long codigo) {
        for (Aluno aluno : alunos) {
            if (aluno != null && aluno.getCodigo() == codigo) {
                return aluno;
            }
        }
        return null;
    }

    public boolean alterarAluno(Aluno aluno) {
        if (aluno != null) {
            boolean existe = alunoExiste(aluno.getNome(), aluno.getNomeDaMae());
            if (!existe) {
                for (int i = 0; i < alunos.length; i++) {
                    if (alunos[i] != null && alunos[i].getCodigo() == aluno.getCodigo()) {
                        alunos[i].setNome(aluno.getNome());
                        alunos[i].setNomeDaMae(aluno.getNomeDaMae());
                        alunos[i].setNomeDoPai(aluno.getNomeDoPai());
                        alunos[i].setEndereço(aluno.getEndereço());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Disciplina buscarDisciplina(long codigo){
        for (Disciplina disciplina: disciplinas){
            if(disciplina != null && disciplina.getCodigo() == codigo) return  disciplina;
        }
        return null;
    }

    public boolean alunoEstaMatriculado(Aluno aluno, Disciplina disciplina){
        if (aluno == null || disciplina == null) {
            return false;
        }
        for (Disciplina d : aluno.getDisciplinasMatriculadas()) {
            if (d != null) {
                if (d.getCodigo() == disciplina.getCodigo()) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean matricularAluno(Aluno aluno, Disciplina disciplina) {
        if (aluno == null || disciplina == null) {
            return false;
        }

        if (disciplina.getNumeroDeAlunosMatriculados() < disciplina.getNumeroDeVagas()) {
            for (int i = 0; i < aluno.getDisciplinasMatriculadas().length; i++) {
                if (aluno.getDisciplinasMatriculadas()[i] == null) {
                    aluno.getDisciplinasMatriculadas()[i] = disciplina;
                    disciplina.setNumeroDeAlunosMatriculados(disciplina.getNumeroDeAlunosMatriculados()+1);
                    return true;
                }

            }
        }
        return false;
    }

    private void init() {
        Disciplina poo = new Disciplina(1, "poo", "Luciano", 2020, 5, 2);
        disciplinas[0] = poo;

        Disciplina pw2 = new Disciplina(2, "pw2", "Dilão", 2020, 5, 2);
        disciplinas[1] = pw2;

        Disciplina fp = new Disciplina(3, "fp", "Maurilio", 2020, 5, 1);
        disciplinas[2] = fp;

        Disciplina aps = new Disciplina(4, "aps", "Deisymar", 2020, 5, 3);
        disciplinas[3] = aps;

        Disciplina bd = new Disciplina(5, "bd", "Balbino", 2020, 5, 2);
        disciplinas[4] = bd;

        numeroDeDisciplinas = 5;

        Aluno fulano = new Aluno(1, "Fulano", "Fulana", "Fulanao", "Rua dos bobos, n 0");

        Disciplina[] disciplinasAluno01 = fulano.getDisciplinasMatriculadas();
        disciplinasAluno01[0] = poo;
        disciplinasAluno01[1] = pw2;
        disciplinasAluno01[2] = aps;
        disciplinasAluno01[3] = bd;
        fulano.setDisciplinasMatriculadas(disciplinasAluno01);
        alunos[0] = fulano;

        Aluno ciclano = new Aluno(2, "Ciclano", "Ciclana", "Ciclanão", "Rua dos doidos. n 13");

        Disciplina[] disciplinasAluno02 = ciclano.getDisciplinasMatriculadas();
        disciplinasAluno02[0] = fp;
        disciplinasAluno02[1] = aps;
        ciclano.setDisciplinasMatriculadas(disciplinasAluno02);
        alunos[1] = ciclano;

        Aluno beltrano = new Aluno(3, "Beltrano", "Beltrana", "Beltranão", "Rua desconhecida, s/n");
        Disciplina[] disciplinasAluno03 = beltrano.getDisciplinasMatriculadas();
        disciplinasAluno03[0] = poo;
        disciplinasAluno03[1] = pw2;
        disciplinasAluno03[2] = aps;
        disciplinasAluno03[3] = bd;
        beltrano.setDisciplinasMatriculadas(disciplinasAluno03);
        alunos[2] = beltrano;

        numeroDeAlunos = 3;

    }

}
