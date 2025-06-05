package src;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ator extends Pessoa {
    private int registroProfissional;

    public Ator(String cpf, String nome, int idade, int registroProfissional) {
        super(cpf, nome, idade);
        this.registroProfissional = registroProfissional;
    }

    public static Ator criarPorScanner(Scanner sc) {
        try {
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            if (cpf.isEmpty()) {
                System.out.println("CPF não pode estar em branco.");
                return null;
            }

            System.out.print("Nome: ");
            String nome = sc.nextLine();
            if (nome.isEmpty()) {
                System.out.println("Nome não pode estar em branco.");
                return null;
            }

            System.out.print("Idade: ");
            int idade = Integer.parseInt(sc.nextLine());

            System.out.print("Registro Profissional: ");
            int registro = Integer.parseInt(sc.nextLine());

            return new Ator(cpf, nome, idade, registro);
        } catch (NumberFormatException e) {
            System.out.println("Erro: entrada numérica inválida.");
            return null;
        }
    }

    @Override
    public boolean inserir() {
        try {
            File bd = new File("bd/atores.txt");
            if (!bd.exists()) {
                bd.getParentFile().mkdirs();
                bd.createNewFile();
            }

            ArrayList<Ator> lista = listar();
            for (Ator a : lista) {
                if (a.getCpf().equalsIgnoreCase(this.cpf)) {
                    System.out.println("Ator já cadastrado.");
                    return false;
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(bd, true));
            writer.write(this.toString());
            writer.newLine();
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao inserir ator: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean editar() {
        return true;
    }

    @Override
    public ArrayList<Ator> listar() {
        ArrayList<Ator> lista = new ArrayList<>();
        try {
            FileReader fr = new FileReader("bd/atores.txt");
            BufferedReader br = new BufferedReader(fr);
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    Ator a = new Ator(
                        dados[0],
                        dados[1],
                        Integer.parseInt(dados[2]),
                        Integer.parseInt(dados[3])
                    );
                    lista.add(a);
                }
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao listar atores: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Ator consultar(int id) {
        ArrayList<Ator> lista = listar();
        if (id >= 0 && id < lista.size()) {
            return lista.get(id);
        } else {
            System.out.println("ID de ator inválido.");
            return null;
        }
    }

    @Override
    public void mostrar() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return cpf + ";" + nome + ";" + idade + ";" + registroProfissional;
    }

    public int getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(int registroProfissional) {
        this.registroProfissional = registroProfissional;
    }
}