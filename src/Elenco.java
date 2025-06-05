import java.io.*;
import java.util.ArrayList;

public class Elenco {
    private int idElenco;
    private Ator ator;
    private Filme filme;
    private boolean atorPrincipal;

    public Elenco(int idElenco, Ator ator, Filme filme, boolean atorPrincipal) {
        this.idElenco = idElenco;
        this.ator = ator;
        this.filme = filme;
        this.atorPrincipal = atorPrincipal;
    }

    public boolean inserir() {
        try {
            File file = new File("bd/elencos.txt");
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
            System.out.println("Erro ao inserir elenco: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Elenco> listar() {
        ArrayList<Elenco> lista = new ArrayList<>();
        try {
            File file = new File("bd/elencos.txt");
            if (!file.exists()) return lista;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                Ator ator = new Ator(dados[1], "nome", 0, 0); // nome, idade e registro simulados
                Filme filme = new Filme(Integer.parseInt(dados[2]), "título", 0, new Genero(1, "genero")); // dados simulados
                boolean principal = Boolean.parseBoolean(dados[3]);
                lista.add(new Elenco(id, ator, filme, principal));
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao listar elencos: " + e.getMessage());
        }
        return lista;
    }

    public void mostrar() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return idElenco + ";" + ator.getCpf() + ";" + filme.getIdFilme() + ";" + atorPrincipal;
    }

    // Getters (se precisar)
    public int getIdElenco() { return idElenco; }
    public Ator getAtor() { return ator; }
    public Filme getFilme() { return filme; }
    public boolean isAtorPrincipal() { return atorPrincipal; }
}
