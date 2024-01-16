public class PaireChaineEntier {

    private String chaine;
    private int entier;

    public PaireChaineEntier(String chaine, int entier){
        this.chaine = chaine;
        this.entier = entier;
    }

    public String getChaine() {
        return chaine;
    }

    public int getEntier() {
        return entier;
    }

    public void setEntier(int valeur) {
        entier = valeur;
    }

    @Override
    public String toString() {
        return chaine + ':' + entier;
    }
}
