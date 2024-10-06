
// Definerer klassen Celle med tilhørende variabler, samt initialiserer disse
// i konstruktoren

class Celle{

    int teller;
    boolean levende;
    Celle[] naboer;
    int antNaboer;
    int antLevendeNaboer;
    
    public Celle(){

        teller = 0;
        levende = false;
        naboer = new Celle [8];
        antNaboer = 0;
        antLevendeNaboer = 0;
    }

    // Metode som gjor den boolske variabelen levende = false

    public void settDoed(){
        levende = false;
    }

    // Metode som gjor den boolske variabelen levende = true

    public void settLevende(){
        levende = true;
    }

    // Metode for aa returnere verdien til variabelen levende

    public boolean erLevende(){
        return levende;
    }

    // Metode som returnerer utskriftstegnet til en celle, avhengig av dod eller levende

    public char hentStatusTegn(){
        if (levende){
            return 'O';
        } else {
            return '.';
        }
    }

    // Metode som legger Celler til som naboer til en celle

    public void leggTilNabo(Celle nabo){
        if(teller < 8){
            naboer[teller] = nabo;
            teller++;
            antNaboer++;
        }
    }

    // Metode som teller antall levende naboer til en celle, ved bruk av "i" som indeksering

    public void tellLevendeNaboer(){

        antLevendeNaboer = 0;

        for (int i = 0; i < 8; i++){
            if (naboer[i] != null) {
                if (naboer[i].erLevende()){
                antLevendeNaboer++;
                }
            }
            
        }
    }

    // Metode for aa oppdatere status (dod/levende) til en celle, avhengig av ant levende naboer

    public void oppdaterStatus(){

        if (this.erLevende()){
            if(antLevendeNaboer < 2){
                this.settDoed();
            } 
            else if (antLevendeNaboer > 3){
                this.settDoed();
            }
        }

        else{
            if(antLevendeNaboer == 3) {
                this.settLevende();
            }
        }
    }
}