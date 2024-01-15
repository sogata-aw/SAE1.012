import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Categorie {

    private String nom; // le nom de la catégorie p.ex : sport, politique,...
    private ArrayList<PaireChaineEntier> lexique; // le lexique de la catégorie

    // constructeur
    public Categorie(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<PaireChaineEntier> getLexique() {
        return lexique;
    }

    // initialisation du lexique de la catégorie à partir du contenu d'un fichier
    // texte
    public void initLexique(String nomFichier) {
        try {
            lexique = new ArrayList<>();
            Scanner scanner = new Scanner(new FileInputStream(nomFichier));
            String currentLine, currentWord;
            int i, currentWeight;

            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                i = 0;
                while (currentLine.charAt(i) != ':') {
                    i++;
                }
                currentWord = currentLine.substring(0, i);
                currentWeight = Integer.parseInt(currentLine.substring(i + 1));
                lexique.add(new PaireChaineEntier(currentWord, currentWeight));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // calcul du score d'une dépêche pour la catégorie
    public int score(Depeche d) {
        return 0;
    }

}
