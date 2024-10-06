

// klassen Model som har Celle og Rutenett med nodvendige metoder
// Modellen skal fungere som en ekstern kopi av GUI'en sitt rutenett, og den skal
// behandle all beregning, sammensetning og konfigurasjon til en celle og et rutenett.
// Controller skal dermed viderefore disse resultatene/infoen til det visuelle som blir fremstilt for brukeren gjennom guien

class Model{


    // en metode som skal gi Controller tilgang paa et rutenett av klassen Rutenett
    
    public Rutenett lagRutenett(int x, int y){
        Rutenett rutenett = new Rutenett(x,y);
        return rutenett;
    }

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

    class Rutenett{
        int antRader;
        int antKolonner;
        Celle[][] rutene;

        public Rutenett(int r, int k){

            antRader = r;
            antKolonner = k;

            rutene = new Celle[r][k];
        }

        public Celle lagCelle(int rad, int kol){

        Celle enCelle = new Celle();
        rutene[rad][kol] = enCelle;
        return enCelle;
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

        // Metode som lager 8 unike naboer, med ulik kolonne og rad-verdi, som
        // skal ta for seg alle mulige naboer til en celle.
        // Oppretter også en celle med de initiale verdiene til kolonne og rad
        // som skal ta for seg cellen vi skal jobbe med for aa sette naboer


        public void settNaboer(int rad, int kolonne){

            Celle[] naboer = new Celle[9];

            Celle nabo1 = hentCelle(rad - 1, kolonne - 1);
            naboer[0] = nabo1;

            Celle nabo2 = hentCelle(rad - 1, kolonne);
            naboer[1] = nabo2;

            Celle nabo3 = hentCelle(rad - 1, kolonne + 1);
            naboer[2] = nabo3;

            Celle nabo4 = hentCelle(rad, kolonne - 1);
            naboer[3] = nabo4;

            Celle nabo5 = hentCelle(rad, kolonne + 1);
            naboer[5] = nabo5;

            Celle nabo6 = hentCelle(rad + 1, kolonne - 1);
            naboer[6] = nabo6;

            Celle nabo7 = hentCelle(rad + 1, kolonne);
            naboer[7] = nabo7;

            Celle nabo8 = hentCelle(rad + 1, kolonne + 1);
            naboer[8] = nabo8;

            Celle denneCellen = hentCelle(rad, kolonne);


        // Lager if-sjekker for aa sjekke hvorvidt naboene faktisk er celler
        // eller om de er utenfor "rekkevidde". Dersom naboene != null, altsaa
        // at de er celler av klassen Celle, saa blir disse lagt til som naboer.
        // Dersom en nabo = null, saa er den ikke innenfor rutenettet, og skal
        // ikke paavirke cellens oppdatering, og blir ikke lagt til som en nabo.

            for (int i = 0; i < naboer.length-1; i++){
                if(naboer[i] != null){
                    denneCellen.leggTilNabo(naboer[i]);
                }
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
}

        