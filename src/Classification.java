import java.io.*;
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

    public static void classementDepeches(ArrayList<Depeche> depeches, ArrayList<Categorie> categories, String nomFichier) {
        String guessedCategorie, trueCategorie;
        PaireChaineEntier currentPaire;
        ArrayList<PaireChaineEntier> scoreDepeches;
        ArrayList<PaireChaineEntier> bonnesReponses = new ArrayList<>(categories.size());

        for (int j = 0; j < categories.size(); j++) {
            bonnesReponses.add(new PaireChaineEntier(categories.get(j).getNom(), 0));
        }

        try {
            FileWriter file = new FileWriter(nomFichier);
            for (int i = 0; i < depeches.size(); i++) {
                scoreDepeches = new ArrayList<>();
                for (int j = 0; j < categories.size(); j++) {
                    scoreDepeches.add(new PaireChaineEntier(categories.get(j).getNom(),
                            categories.get(j).score(depeches.get(i))));
                }
                guessedCategorie = UtilitairePaireChaineEntier.chaineMax(scoreDepeches);
                file.write(depeches.get(i).getId() + ':' + guessedCategorie + '\n');

                trueCategorie = depeches.get(i).getCategorie();
                if (guessedCategorie.equals(trueCategorie)) {
                    currentPaire = bonnesReponses.get(UtilitairePaireChaineEntier.indicePourChaine(bonnesReponses, trueCategorie));
                    currentPaire.setEntier(currentPaire.getEntier() + 1);
                }
            }
            
            for (int i = 0; i < bonnesReponses.size(); i++) {
                file.write(bonnesReponses.get(i).getChaine() + " : " + bonnesReponses.get(i).getEntier() + "%\n");
            }
            file.write("MOYENNE : " + UtilitairePaireChaineEntier.moyenne(bonnesReponses) + '%');
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<PaireChaineEntier> initDico(ArrayList<Depeche> depeches, String categorie) {
        ArrayList<PaireChaineEntier> dicoMots = new ArrayList<>(); // Dictionnaire renvoyé à la fin
        String currentWord;
        boolean motAbsent;
        for (int i = 0; i < depeches.size(); i++) {
            // Parcours de toutes les dépêches
            if (depeches.get(i).getCategorie().equals(categorie)) {
                // Si la dépêche n°i+1 est dans la catégorie recherchée, on ajoute les mots de cette dépêche suivant s'ils sont déjà dans le dictionnaire ou pas :
                for (int j = 0; j < depeches.get(i).getMots().size(); j++) {
                    // Parcours de tous les mots de cette dépêche
                    motAbsent = true;
                    currentWord = depeches.get(i).getMots().get(j);
                    for (int k = 0; k < dicoMots.size(); k++) {
                        // Si un mot est présent à un moment donné, motAbsent prendra la valeur false
                        if (dicoMots.get(k).getChaine().equals(currentWord)) {
                            motAbsent = false;
                        }
                    }
                    // Si le mot n'a pas été trouvé, motAbsent vaut true et on peut l'ajouter
                    if (motAbsent) {
                        dicoMots.add(new PaireChaineEntier(currentWord, 0));
                    }
                }
            }
        }
        return dicoMots;
    }

    public static void calculScores(ArrayList<Depeche> depeches, String categorie, ArrayList<PaireChaineEntier> dictionnaire) {
        String currentWord;
        for (int i = 0; i < depeches.size(); i++) {
            // Parcours de toutes les dépêches
            for (int j = 0; j < depeches.get(i).getMots().size(); j++) {
                // Pour chaque dépêche, parcours de tous ses mots
                currentWord = depeches.get(i).getMots().get(j); // Variable permettant de référencer le mot concerné par le tour de boucle actuel
                for (int k = 0; k < dictionnaire.size(); k++) {
                    // Parcours des éléments du dictionnaire donné en argument pour chercher si le mot actuel (currentWord) est le même qu'un des mots du dictionnaire :
                    if (dictionnaire.get(k).getChaine().equals(currentWord)) {
                        // Si c'est le cas, on vérifie si la catégorie est la bonne
                        if (depeches.get(i).getCategorie().equals(categorie)) {
                            // Si la catégorie est la bonne, on veut donner un meilleur score à cet élément :
                            dictionnaire.get(k).setEntier(dictionnaire.get(k).getEntier() + 1);
                        } else {
                            // Sinon, c'est l'inverse :
                            dictionnaire.get(k).setEntier(dictionnaire.get(k).getEntier() - 1);
                        }
                    }
                }
            }
        }
    }

    public static int poidsPourScore(int score) {
        return 0;
    }

    public static void generationLexique(ArrayList<Depeche> depeches, String categorie, String nomFichier) {}

    public static void afficherListeDepeches(ArrayList<Depeche> depeches) {
        for (int i = 0; i < depeches.size(); i++) {
            depeches.get(i).afficher();
        }
    }

    public static void afficherListeGenerique(ArrayList liste) {
        for (int i = 0; i < liste.size(); i++) {
            System.out.println(liste.get(i));
        }
    }

    public static void afficherLexique(Categorie categorie) {
        System.out.println("Contenu du lexique \"" + categorie.getNom() + "\" :\n");
        afficherListeGenerique(categorie.getLexique());
    }

    public static void testEntierPourChaine(Categorie categorie) {
        Scanner s = new Scanner(System.in);
        System.out.print("\nSaisissez un mot du lexique " + categorie.getNom() + " : ");
        int resultat = UtilitairePaireChaineEntier.entierPourChaine(categorie.getLexique(), s.nextLine());

        if (resultat == 0) {
            System.out.println("Il n'y a pas de mots correspondant à celui-ci dans le lexique.");
        } else {
            System.out.println("Le poids associé à ce mot est : " + resultat);
        }
    }

    public static void testScoreMaximal(ArrayList<Categorie> categories, Depeche depeche) {
        ArrayList<PaireChaineEntier> scores = new ArrayList<>(5);
        scores.add(new PaireChaineEntier("ENVIRONNEMENT-SCIENCES", categories.get(0).score(depeche)));
        scores.add(new PaireChaineEntier("CULTURE", categories.get(1).score(depeche)));
        scores.add(new PaireChaineEntier("ECONOMIE", categories.get(2).score(depeche)));
        scores.add(new PaireChaineEntier("POLITIQUE", categories.get(3).score(depeche)));
        scores.add(new PaireChaineEntier("SPORTS", categories.get(4).score(depeche)));

        System.out.println("Catégorie avec le score maximal pour la dépêche actuelle : " + UtilitairePaireChaineEntier.chaineMax(scores));
    }

    public static void testInitDico(ArrayList<Depeche> depeches, String categorieCible, int filtreMinimum) {
        
        ArrayList<PaireChaineEntier> dico = initDico(depeches, categorieCible);
        calculScores(depeches, categorieCible, dico);
        System.out.println("Liste des mots dont le score est plus grand que " + filtreMinimum + " pour la catégorie " + categorieCible + " :");
        for (int i = 0; i < dico.size(); i++) {
            if (dico.get(i).getEntier() > filtreMinimum) {
                System.out.println(dico.get(i));
            }
        }
    }

    public static void main(String[] args) {

        // Chargement des dépêches en mémoire
        ArrayList<Depeche> depeches = lectureDepeches("depeches.txt");
        ArrayList<Depeche> depechesTest = lectureDepeches("test.txt");
        
        // Création des objets catégories
        Categorie environnement_sciences = new Categorie("ENVIRONNEMENT-SCIENCES");
        Categorie culture = new Categorie("CULTURE");
        Categorie economie = new Categorie("ECONOMIE");
        Categorie politique = new Categorie("POLITIQUE");
        Categorie sports = new Categorie("SPORTS");

        // Chargement des lexiques en mémoire pour chaque catégorie
        environnement_sciences.initLexique();
        culture.initLexique();
        economie.initLexique();
        politique.initLexique();
        sports.initLexique();

        // Variable utilitaire qui regroupe toutes les catégories
        ArrayList<Categorie> categories = new ArrayList<>(
            Arrays.asList(
                environnement_sciences,
                culture,
                economie,
                politique,
                sports
            )
        );

        // Ecriture du résultat des classements dans les fichiers :
        classementDepeches(depeches, categories, "classement.txt");
        classementDepeches(depechesTest, categories, "classementTest.txt");



        // Test de initDico
        testInitDico(depeches, "CULTURE", 0);
    }
}
