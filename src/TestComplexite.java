import java.util.ArrayList;

public class TestComplexite {
    private ArrayList<PaireChaineEntier> lexique;

    public int score(Depeche d) {
        int score = 0;
        int j;
        String chaine;
        for (int i = 0; i < d.getMots().size(); i++) {
            j = 0;                                      // executed n times in all cases
            chaine = d.getMots().get(i);
            while (j < lexique.size() && !lexique.get(j).getChaine().equals(chaine)) {
                j++;                                    // in the best case, this is executed 1 time
                                                        // in the worst case, it is executed m times
            }
            if (j != lexique.size()) {
                score += lexique.get(j).getEntier();    // in all cases, this is executed 0 or 1 time
            }
        }
        return score;
    }

    public static void calculScoresSub(ArrayList<Depeche> depeches, String categorie,
    ArrayList<PaireChaineEntier> dictionnaire) {
        String currentWord;
        for (int i = 0; i < depeches.size(); i++) {
            for (int j = 0; j < depeches.get(i).getMots().size(); j++) {          // in all cases this is executed n times
                currentWord = depeches.get(i).getMots().get(j);                   // in all cases this is executed m times
                for (int k = 0; k < dictionnaire.size(); k++) {
                    if (dictionnaire.get(k).getChaine().equals(currentWord)) {    // in all cases this is executed l times
                        if (depeches.get(i).getCategorie().equals(categorie)) {   // in the best case this is not executed
                                                                                  // in the worst case this is executed 1 time
                            dictionnaire.get(k).setEntier(dictionnaire.get(k).getEntier() + 1);
                        } else {
                            dictionnaire.get(k).setEntier(dictionnaire.get(k).getEntier() - 1);
                        }
                    }
                }
            }
        }
    }
}
