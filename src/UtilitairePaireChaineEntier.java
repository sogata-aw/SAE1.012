import java.util.ArrayList;

public class UtilitairePaireChaineEntier {

    public static int indicePourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
            if (listePaires.get(listePaires.size()-1).getChaine().compareTo(chaine)<0) {
                return -1;
            }
            else
            {
                int inf = 0;
                int sup = listePaires.size() - 1;
                int m;
                while (inf < sup) {
                    m = (inf + sup) / 2;
                    if (listePaires.get(m).getChaine().compareTo(chaine) >= 0) {
                        sup = m;
                    } else {
                        inf = m + 1;
                    }
                }
                if (listePaires.get(sup).getChaine().compareTo(chaine)==0) {
                    return sup;
                } else {
                    return -1;
                }
            }

    }

    public static int entierPourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {

        if (listePaires.get(listePaires.size()-1).getChaine().compareTo(chaine)==-1) {
            return -listePaires.size();
        } else {
            int inf = 0;
            int sup = listePaires.size() - 1;
            int m;
            while (inf < sup) {
                m = (inf + sup) / 2;

                if (listePaires.get(m).getChaine().compareTo(chaine) >= 0) {
                    sup = m;
                } else {
                    inf = m + 1;
                }

            }

            if (listePaires.get(sup).getChaine().compareTo(chaine)==0) {
                return listePaires.get(sup).getEntier();
            } else {
                return 0;
            }
        }
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

    public static void triBullesAmeliore(ArrayList<PaireChaineEntier> listePaires) {
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
            }
                i = i + 1;
        }
    }
}
