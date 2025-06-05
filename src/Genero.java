package src;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Genero {
    private int idGenero;
    private String descGenero;

    public Genero(int idGenero, String descGenero) {
        this.idGenero = idGenero;
        this.descGenero = descGenero;
    }

    public static Genero criarPorScanner(Scanner sc) {
        try {
            System.out.print("Descrição do Gênero: ");
            String descricao = sc.nextLine();
            if (descricao.isEmpty()) return null;

            ArrayList<Genero> lista = listar();
            for (Genero g : lista) {
                if (g.getDescGenero().equalsIgnoreCase(descricao)) {
                    System.out.println("Gênero já existe.");
                    return null;
                }
            }

            int novoId = lista.isEmpty() ? 1 : lista.get(lista.size() - 1).getIdGenero() + 1;
            return new Genero(novoId, descricao);
        } catch (Exception e) {
            System.out.println("Erro ao criar gênero: " + e.getMessage());
            return null;
        }
    }

    public boolean inserir() {
        try {
            File file = new File("bd/generos.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(this.toString());
            writer.newLine();
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao inserir gênero: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Genero> listar() {
        ArrayList<Genero> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("bd/generos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Genero g = new Genero(Integer.parseInt(dados[0]), dados[1]);
                lista.add(g);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar gêneros: " + e.getMessage());
        }
        return lista;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getDescGenero() {
        return descGenero;
    }

    public void setDescGenero(String descGenero) {
        this.descGenero = descGenero;
    }

    @Override
    public String toString() {
        return idGenero + ";" + descGenero;
    }
}