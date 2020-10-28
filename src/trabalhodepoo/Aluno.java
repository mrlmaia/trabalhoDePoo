/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhodepoo;

public class Aluno {

    private long codigo;
    private String nomeDoPai;
    private String nome;
    private String nomeDaMae;
    private String endereço;
    private Disciplina[] disciplinasMatriculadas;
    private float[] notasNasDisciplinas;

    public Aluno(long codigo, String nome, String nomeDaMae, String nomeDoPai, String endereço) {
        this.codigo = codigo;
        this.nome = nome;
        this.nomeDaMae = nomeDaMae;
        this.nomeDoPai = nomeDoPai;
        this.endereço = endereço;

        disciplinasMatriculadas = new Disciplina[5];
        notasNasDisciplinas = new float[5];

    }

    public Aluno() {
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDaMae() {
        return nomeDaMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
    }

    public String getNomeDoPai() {
        return nomeDoPai;
    }

    public void setNomeDoPai(String nomeDoPai) {
        this.nomeDoPai = nomeDoPai;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public Disciplina[] getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }

    public void setDisciplinasMatriculadas(Disciplina[] disciplinasMatriculadas) {
        this.disciplinasMatriculadas = disciplinasMatriculadas;
    }

    public float[] getNotasNasDisciplinas() {
        return notasNasDisciplinas;
    }

    public void setNotasNasDisciplinas(float[] notasNasDisciplinas) {
        this.notasNasDisciplinas = notasNasDisciplinas;
    }

}
