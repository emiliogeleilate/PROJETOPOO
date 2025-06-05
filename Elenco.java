package src;
import java.io.*;
import java.util.ArrayList;

public class Elenco  {
    private int idElenco;
    private Ator ator;
    private Filme filme;
    private boolean atorPrincipal;

    public Elenco(int idElenco, Ator ator, Filme filme, boolean atorPrincipal){
        this.idElenco = idElenco;
        this.ator = ator;
        this.filme = filme;
        this. atorPrincipal = atorPrincipal;
    }

    public boolean inserir() {
        try {
                File file = new File (bd/elencos.txt);                
                if(!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
        writer.write(this.toString());
        writer.newLine();
        writer.close();
    }
}
