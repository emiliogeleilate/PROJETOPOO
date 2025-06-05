package src;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends Pessoa {
    private String rg;
    private boolean estudante;

    public Cliente(String cpf, String nome, int idade, String rg, boolean estudante) {
        super(cpf, nome, idade);
        this.rg = rg;
        this.estudante = estudante;
    }

    public static Cliente criarPorScanner(Scanner sc) {
        try {
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            if (cpf.isEmpty()) return null;

            System.out.print("Nome: ");
            String nome = sc.nextLine();
            if (nome.isEmpty()) return null;

            System.out.print("Idade: ");
            int idade = Integer.parseInt(sc.nextLine());

            System.out.print("RG: ");
            String rg = sc.nextLine();
            if (rg.isEmpty()) return null;

            System.out.print("É estudante? (true/false): ");
            boolean estudante = Boolean.parseBoolean(sc.nextLine());

            return new Cliente(cpf, nome, idade, rg, estudante);
        } catch (NumberFormatException e) {
            System.out.println("Erro: formato inválido.");
            return null;
        }
    }

    @Override
    public boolean inserir() {
        try {
            File file = new File("bd/clientes.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            ArrayList<Cliente> lista = listar();
            for (Cliente c : lista) {
                if (c.getCpf().equalsIgnoreCase(this.cpf)) {
                    System.out.println("Cliente já cadastrado.");
                    return false;
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(this.toString());
            writer.newLine();
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean editar() {
        return true;
    }

    @Override
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("bd/clientes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 5) {
                    Cliente c = new Cliente(dados[0], dados[1],
                            Integer.parseInt(dados[2]),
                            dados[3], Boolean.parseBoolean(dados[4]));
                    lista.add(c);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Cliente consultar(int id) {
        ArrayList<Cliente> lista = listar();
        if (id >= 0 && id < lista.size()) {
            return lista.get(id);
        } else {
            System.out.println("ID inválido.");
            return null;
        }
    }

    @Override
    public void mostrar() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return cpf + ";" + nome + ";" + idade + ";" + rg + ";" + estudante;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public boolean isEstudante() {
        return estudante;
    }

    public void setEstudante(boolean estudante) {
        this.estudante = estudante;
    }
}