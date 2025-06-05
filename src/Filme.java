
import java.io.*;
import java.util.ArrayList;

public class Filme {
    private int idFilme;
    private String titulo;
    private int duracao;
    private Genero genero;

    public Filme(int idFilme, String titulo, int duracao, Genero genero) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.duracao = duracao;
        this.genero = genero;
    }

    public boolean inserir() {
        try {
            File file = new File("bd/filmes.txt");
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
            System.out.println("Erro ao inserir filme: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Filme> listar() {
        ArrayList<Filme> filmes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("bd/filmes.txt"));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String titulo = dados[1];
                int duracao = Integer.parseInt(dados[2]);
                Genero genero = new Genero(1, dados[3]); // Simulação
                filmes.add(new Filme(id, titulo, duracao, genero));
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao listar filmes: " + e.getMessage());
        }
        return filmes;
    }

    public static Filme consultar(int id) {
        for (Filme f : listar()) {
            if (f.getIdFilme() == id) {
                return f;
            }
        }
        return null;
    }

    public void mostrar() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return idFilme + ";" + titulo + ";" + duracao + ";" + (genero != null ? genero.getDescGenero() : "sem gênero");
    }

    // Getters
    public int getIdFilme() { return idFilme; }
    public String getTitulo() { return titulo; }
    public int getDuracao() { return duracao; }
    public Genero getGenero() { return genero; }
}
