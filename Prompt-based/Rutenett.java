
// Definerer klassen Rutenett med tilhorende variabler som initialiseres i konstr.
// samt oppretter en todimensional array som har til hensikt aa ta vare paa rad og kolonne-verdier

class Rutenett{

    int antRader;
    int antKolonner;
    Celle[][] rutene;

    public Rutenett(int r, int k){

        antRader = r;
        antKolonner = k;

        rutene = new Celle[r][k];
    }

    // Metode for aa opprette en celle, ved bruk av rad og kolonne som parametere
    // Bruker Math for aa kunne utnytte sannsynlighetsfunksjonen for aa finne 1/3

    public void lagCelle(int rad, int kol){

        Celle enCelle = new Celle();

        if (Math.random() <= 0.3333){
            enCelle.settLevende();
        }
        rutene[rad][kol] = enCelle;
    }

    // Metode for aa fylle rutenettet med tilfeldige celler ved en nostet for-lokke

    public void fyllMedTilfeldigeCeller(){

        for (int i = 0; i < antRader; i++){
            for (int j = 0; j < antKolonner; j++){
                lagCelle(i, j);
            }
        }        
    }

    // Metode for aa hente ut en celle ut ifra parameterene rad og kolonne
    // Denne er viktig for aa kunne manipulere og bruke celle-klassen til de videre klassene

    public Celle hentCelle(int rad, int kolonne){
        if (rad < 0 || kolonne < 0){
            return null;

        } else if (rad > (antRader -1) || kolonne > (antKolonner -1)){
            return null;
        }
        else{
            return rutene[rad][kolonne];
        }
    }

    // Metode som printer ut det fullstendige rutenettet med "." og "O"

    public void tegnRutenett(){

        for (int i = 0; i < antRader; i++){
            System.out.println("\n");
            for (int j = 0; j < antKolonner; j++){
                System.out.print(rutene[i][j].hentStatusTegn());
            }
        }
    }

    // Metode som lager 8 unike naboer, med ulik kolonne og rad-verdi, som
    // skal ta for seg alle mulige naboer til en celle.
    // Oppretter også en celle med de initiale verdiene til kolonne og rad
    // som skal ta for seg cellen vi skal jobbe med for aa sette naboer


    public void settNaboer(int rad, int kolonne){

        Celle nabo1 = hentCelle(rad - 1, kolonne - 1);
        Celle nabo2 = hentCelle(rad - 1, kolonne);
        Celle nabo3 = hentCelle(rad - 1, kolonne + 1);
        Celle nabo4 = hentCelle(rad, kolonne - 1);
        Celle nabo5 = hentCelle(rad, kolonne + 1);
        Celle nabo6 = hentCelle(rad + 1, kolonne - 1);
        Celle nabo7 = hentCelle(rad + 1, kolonne);
        Celle nabo8 = hentCelle(rad + 1, kolonne + 1);
        Celle denneCellen = hentCelle(rad, kolonne);

    // Lager if-sjekker for aa sjekke hvorvidt naboene faktisk er celler
    // eller om de er utenfor "rekkevidde". Dersom naboene != null, altsaa
    // at de er celler av klassen Celle, saa blir disse lagt til som naboer.
    // Dersom en nabo = null, saa er den ikke innenfor rutenettet, og skal
    // ikke paavirke cellens oppdatering, og blir ikke lagt til som en nabo.

        if (nabo1 != null){
            denneCellen.leggTilNabo(nabo1);
        }
        if (nabo2 != null){
            denneCellen.leggTilNabo(nabo2);
        }
        if (nabo3 != null){
            denneCellen.leggTilNabo(nabo3);
        }
        if (nabo4 != null){
            denneCellen.leggTilNabo(nabo4);
        }
        if (nabo5 != null){
            denneCellen.leggTilNabo(nabo5);
        }
        if (nabo6 != null){
            denneCellen.leggTilNabo(nabo6);
        }
        if (nabo7 != null){
            denneCellen.leggTilNabo(nabo7);
        }
        if (nabo8 != null){
            denneCellen.leggTilNabo(nabo8);
        }
    }

    // Metode som kobler sammen hver enkelt celle via en for-lokke og bruk av settNaboer 

    public void kobleAlleCeller(){

        
        for (int i = 0; i < antRader; i++){
            for (int j = 0; j < antKolonner; j++){

                this.settNaboer(i, j);
            }
        }
    }

    // Metode som teller antall levende celler i rutenettet
    
    public int antallLevende(){

        int teller = 0;

        for (int i = 0; i < antRader; i++){
            for (int j = 0; j < antKolonner; j++){

                if (rutene[i][j].erLevende()){
                    teller++;
                }
            }
        }
        return teller;
    }
}