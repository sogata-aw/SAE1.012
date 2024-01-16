import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Classification {

    private static ArrayList<Depeche> lectureDepeches(String nomFichier) {
        // creation d'un tableau de dépêches
        ArrayList<Depeche> depeches = new ArrayList<>();
        try {
            // lecture du fichier d'entrée
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String id = ligne.substring(3);
                ligne = scanner.nextLine();
                String date = ligne.substring(3);
                ligne = scanner.nextLine();
                String categorie = ligne.substring(3);
                ligne = scanner.nextLine();
                String lignes = ligne.substring(3);
                while (scanner.hasNextLine() && !ligne.equals("")) {
                    ligne = scanner.nextLine();
                    if (!ligne.equals("")) {
                        lignes = lignes + '\n' + ligne;
                    }
                }
                Depeche uneDepeche = new Depeche(id, date, categorie, lignes);
                depeches.add(uneDepeche);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return depeches;
    }

    public static void classementDepeches(ArrayList<Depeche> depeches, ArrayList<Categorie> categories,
            String nomFichier) {
    }

    public static ArrayList<PaireChaineEntier> initDico(ArrayList<Depeche> depeches, String categorie) {
        ArrayList<PaireChaineEntier> resultat = new ArrayList<>();
        return resultat;

    }

    public static void calculScores(ArrayList<Depeche> depeches, String categorie,
            ArrayList<PaireChaineEntier> dictionnaire) {
    }

    public static int poidsPourScore(int score) {
        return 0;
    }

    public static void generationLexique(ArrayList<Depeche> depeches, String categorie, String nomFichier) {

    }

    public static void main(String[] args) {

        // Chargement des dépêches en mémoire
        System.out.println("chargement des dépêches");
        ArrayList<Depeche> depeches = lectureDepeches("./depeches.txt");
        Categorie environnement_sciences = new Categorie("ENVIRONNEMENT-SCIENCES");
        Categorie culture = new Categorie("CULTURE");
        Categorie economie = new Categorie("ECONOMIE");
        Categorie politique = new Categorie("POLITIQUE");
        Categorie sports = new Categorie("SPORTS");
        ArrayList<Categorie> categories = new ArrayList<>(
                Arrays.asList(environnement_sciences, culture, economie, politique, sports));
        
        // Affichage des dépêches
        // for (int i = 0; i < depeches.size(); i++) {
        //     depeches.get(i).afficher();
        // }

        // Test de initLexique
        culture.initLexique("CULTURE.TXT");
        ArrayList<PaireChaineEntier> lexiqueCulture = culture.getLexique();
        System.out.println("Contenu du lexique \"culture\" :\n");
        for (int i = 0; i < lexiqueCulture.size(); i++) {
            System.out.println(lexiqueCulture.get(i));
        }

        // Test de entierPourChaine
        Scanner s = new Scanner(System.in);
        System.out.print("\nSaisissez un mot du lexique culture : ");
        int resultat = UtilitairePaireChaineEntier.entierPourChaine(lexiqueCulture, s.nextLine());
        if (resultat == 0) {
            System.out.println("Il n'y a pas de mots correspondant à celui-ci dans le lexique.");
        } else {
            System.out.println("Le poids associé à ce mot est : " + resultat);
        }

        // Test de entierPourChaine
        // Scanner s = new Scanner(System.in);
        // System.out.print("\nSaisissez un mot du lexique culture : ");
        // int resultat = UtilitairePaireChaineEntier.entierPourChaine(lexiqueCulture, s.nextLine());
        // if (resultat == 0) {
        //     System.out.println("Il n'y a pas de mots correspondant à celui-ci dans le lexique.");
        // } else {
        //     System.out.println("Le poids associé à ce mot est : " + resultat);
        // }

        System.out.println(depeches.get(10).getMots());
        System.out.println(depeches.get(10).getContenu());
    }
}
