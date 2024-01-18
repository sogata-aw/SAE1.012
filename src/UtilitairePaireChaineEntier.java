import java.util.ArrayList;

public class UtilitairePaireChaineEntier {

    public static int indicePourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int i = 0;
        int resultat = -1;
        while (i < listePaires.size() && resultat == -1 && listePaires.get(i).getChaine().compareTo(chaine) <= 0) {
            if (listePaires.get(i).getChaine().equals(chaine)) {
                resultat = i;
            }
            i++;
        }
        return resultat;

    }

    public static int entierPourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int resultat = 0;
        int i = 0;
        while (i < listePaires.size() && resultat == 0 && listePaires.get(i).getChaine().compareTo(chaine) <= 0) {
            if (listePaires.get(i).getChaine().equals(chaine)) {
                resultat = listePaires.get(i).getEntier();
            }
            i++;
        }
        return resultat;
    }

    public static String chaineMax(ArrayList<PaireChaineEntier> listePaires) {
        int max = listePaires.get(0).getEntier();
        int j = 0;
        int currentEntier;
        for (int i = 1; i < listePaires.size(); i++) {
            currentEntier = listePaires.get(i).getEntier();
            if (max < currentEntier) {
                max = currentEntier;
                j = i;
            }
        }
        return listePaires.get(j).getChaine();
    }

    public static float moyenne(ArrayList<PaireChaineEntier> listePaires) {
        float s = 0.0f;
        for (int i = 0; i < listePaires.size(); i++) {
            s += listePaires.get(i).getEntier();
        }
        return s / listePaires.size();
    }

    private static void triBullesAmeliore(ArrayList<PaireChaineEntier> listePaires) {
        int j;
        int i = 0;
        boolean onAPermute = true;
        while (onAPermute) {
            j = listePaires.size() - 1;
            onAPermute = false;
            while (j > i) {
                if (listePaires.get(j).compareTo(listePaires.get(j - 1)) == -1) {
                    PaireChaineEntier temporaire = listePaires.get(j);
                    listePaires.set(j, listePaires.get(j - 1));
                    listePaires.set(j - 1, temporaire);
                    onAPermute = true;
                }
                j = j - 1;

                i = i + 1;

            }
        }
    }
}
