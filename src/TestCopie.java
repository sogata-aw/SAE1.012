import java.util.ArrayList;
import java.util.Arrays;

public class TestCopie {

    public static void shallowCopy() {
        ArrayList<PaireChaineEntier> liste1 = new ArrayList<>(
                Arrays.asList(
                        new PaireChaineEntier("test0", 0),
                        new PaireChaineEntier("test1", 1),
                        new PaireChaineEntier("test2", 2),
                        new PaireChaineEntier("test3", 3),
                        new PaireChaineEntier("test4", 4)));

        ArrayList<PaireChaineEntier> liste2 = (ArrayList<PaireChaineEntier>) liste1.clone();
        liste2.get(0).setEntier(4);
        liste2.get(1).setEntier(3);
        liste2.get(2).setEntier(2);
        liste2.get(3).setEntier(1);
        liste2.get(4).setEntier(0);
        liste2.add(new PaireChaineEntier("test5", -1));

        System.out.println("liste1 = " + liste1);
        System.out.println("liste2 = " + liste2);

        System.out.println("Conclusion : ne pas utiliser la méthode clone car elle fait une copie en \"surface\"");
    }

    public static void deepCopy() {
        ArrayList<PaireChaineEntier> liste1 = new ArrayList<>(
                Arrays.asList(
                        new PaireChaineEntier("test0", 0),
                        new PaireChaineEntier("test1", 1),
                        new PaireChaineEntier("test2", 2),
                        new PaireChaineEntier("test3", 3),
                        new PaireChaineEntier("test4", 4)));

        ArrayList<PaireChaineEntier> liste2 = new ArrayList<>();
        for (int i = 0; i < liste1.size(); i++) {
            liste2.add(new PaireChaineEntier(liste1.get(i).getChaine(), liste1.get(i).getEntier()));
        }

        liste2.get(0).setEntier(4);
        liste2.get(1).setEntier(3);
        liste2.get(2).setEntier(2);
        liste2.get(3).setEntier(1);
        liste2.get(4).setEntier(0);
        liste2.add(new PaireChaineEntier("test5", -1));

        System.out.println("liste1 = " + liste1);
        System.out.println("liste2 = " + liste2);

        System.out.println("Conclusion : si on fait une boucle comme ceci, ça fonctionne");
    }

    public static void main(String[] args) {
        System.out.println();
        shallowCopy();
        System.out.println();
        deepCopy();
    }
}
