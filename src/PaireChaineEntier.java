public class PaireChaineEntier implements Comparable<PaireChaineEntier>{

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

    @Override
    public int compareTo(PaireChaineEntier o){
        // Ordre (chaine,entier)
        int r=1;
        if(this.getChaine().compareTo(o.getChaine())<0 || this.getChaine().compareTo(o.getChaine())<0 && this.getEntier()
        <o.getEntier()){
            r=-1;
        }else if(this.getChaine().equals(o.getChaine()) && this.getEntier()==o.getEntier()){
            r=0;
        }
        return r;
    }
}
