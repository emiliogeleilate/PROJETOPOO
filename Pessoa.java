package src;
import java.util.ArrayList;

public abstract class Pessoa {
    protected String cpf;
    protected String nome;
    protected int idade;

    public Pessoa(String cpf, String nome, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public abstract boolean inserir();
    public abstract boolean editar();
    public abstract ArrayList<?> listar();
    public abstract Pessoa consultar(int id);
    public abstract void mostrar();

    @Override
    public String toString() {
        return "CPF: " + cpf + ", Nome: " + nome + ", Idade: " + idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
