import java.util.ArrayList;

public class UtilitairePaireChaineEntier {

    public static int indicePourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int i = 0;
        while (i < listePaires.size() && !listePaires.get(i).getChaine().equals(chaine)) { // Parcours de listePaires jusqu'à ce que la condition soit vérifiée
            i++;
        }
        if (i == listePaires.size()) { // Le fait que i soit en dehors des limites de la liste implique que la chaine n'a pas été trouvée. Le deuxième return en dessous n'est pas exécuté (pas besoin du mot-clé else)
            return -1;
        }
        return i;

    }

    public static int entierPourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int i = 0;
        while (i < listePaires.size() && !listePaires.get(i).getChaine().equals(chaine)) { // Parcours de listePaires jusqu'à ce que la condition soit vérifiée
            i++;
        }
        if (i == listePaires.size()) { // Le fait que i soit en dehors des limites de la liste implique que la chaine n'a pas été trouvée. Le deuxième return en dessous n'est pas exécuté (pas besoin du mot-clé else)
            return 0;
        }
        return listePaires.get(i).getEntier();
    }

    public static String chaineMax(ArrayList<PaireChaineEntier> listePaires) {
        // Initialisation du maximum temporaire au premier entier
        int max = listePaires.get(0).getEntier();
        int j = 0;
        int currentEntier;

        // Si un entier dans la liste est plus grand, il le remplacera, et ainsi de suite :
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
        // Moyenne simple des entiers de listePaires
        float s = 0.0f;
        for (int i = 0; i < listePaires.size(); i++) {
            s += listePaires.get(i).getEntier();
        }
        return s / listePaires.size();
    }
}
