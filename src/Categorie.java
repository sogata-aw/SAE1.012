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

    public void initLexique(String type) {
        // initialisation du lexique de la catégorie à partir du contenu d'un fichier texte
        try {
            lexique = new ArrayList<>();
            Scanner scanner = new Scanner(new FileInputStream(type + '/' + nom + ".txt"));
            String currentLine, currentWord;
            int i, currentWeight;

            // Parcours du fichier de lexique correspondant au nom de la catégorie de l'objet actuel
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                i = 0;
                while (currentLine.charAt(i) != ':') {
                    i++;
                }
                currentWord = currentLine.substring(0, i);
                currentWeight = Integer.parseInt(currentLine.substring(i + 1));
                lexique.add(new PaireChaineEntier(currentWord, currentWeight));
                UtilitairePaireChaineEntier.triBullesAmeliore(lexique);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int score(Depeche d) {
        // calcul du score d'une dépêche pour la catégorie
        int score = 0;
        for (int i = 0; i < d.getMots().size(); i++) { // Parcours complet des mots de la dépêche
            score += UtilitairePaireChaineEntier.entierPourChaine(lexique, d.getMots().get(i)); // Pour chaque mot, on ajoute le poids associé dans le lexique. Si le mot n'est pas dans le lexique, la méthode renvoie 0 et le score n'est donc pas incrémenté (donc aucun besoin de vérifier le résultat)
        }
        return score;
    }
}
