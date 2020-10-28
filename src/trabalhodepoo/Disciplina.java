/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhodepoo;

public class Disciplina {
    private long codigo;
    private String nome;
    private String nomeDoProfessor;
    private int ano;
    private int numeroDeVagas;
    private int numeroDeAlunosMatriculados;

    public Disciplina(long codigo, String nome, String nomeDoProfessor, int ano,int numeroDeVagas, int numeroDeAlunosMatriculados) {
        this.codigo = codigo;
        this.nome = nome;
        this.nomeDoProfessor = nomeDoProfessor;
        this.numeroDeVagas = numeroDeVagas;
        this.numeroDeAlunosMatriculados = numeroDeAlunosMatriculados;
        this.ano = ano;
    }
    public Disciplina(){}
    
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDoProfessor() {
        return nomeDoProfessor;
    }

    public void setNomeDoProfessor(String nomeDoProfessor) {
        this.nomeDoProfessor = nomeDoProfessor;
    }

    public int getNumeroDeVagas() {
        return numeroDeVagas;
    }

    public void setNumeroDeVagas(int numeroDeVagas) {
        this.numeroDeVagas = numeroDeVagas;
    }

    public int getNumeroDeAlunosMatriculados() {
        return numeroDeAlunosMatriculados;
    }

    public void setNumeroDeAlunosMatriculados(int numeroDeAlunosMatriculados) {
        this.numeroDeAlunosMatriculados = numeroDeAlunosMatriculados;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
