
// Definerer klassen Verden med tilhorende variabler, initierer disse i konstr.
// samt kaller paa metodene som fyller rutenettet, og kobler cellene sammen.

class Verden{

    int genNr;
    Rutenett rutenett;
    

    public Verden(int rader, int kolonner){

        rutenett = new Rutenett(rader, kolonner);
        genNr = 0;

        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
    }

    // Metode som tegner rutenettet, samt skriver ut gen.nr og ant levende celler

    public void tegn(){

        rutenett.tegnRutenett();
        System.out.println("\n\nGenerasjon nummer: " + genNr);
        System.out.println("Antall levende celler: " + rutenett.antallLevende() + "\n");
    }

    // Metode som forst gaar igjennom alle cellene og teller deres levende naboer
    // i en nostet for-lokke, for deretter aa oppdatere statusene til cellene i en 
    // separat for-lokke. Separat for at alle cellene skal faa sin status för 
    // de oppdateres, slik at ikke en celles nye status paavirker en annens gamle status.
    
    public void oppdatering(){

        for (int i = 0; i < rutenett.antRader; i++){
            for (int j = 0; j < rutenett.antKolonner; j++){

                Celle enCelle = rutenett.hentCelle(i, j);
                enCelle.tellLevendeNaboer();
            }
        }

        for (int i = 0; i < rutenett.antRader; i++){
            for (int j = 0; j < rutenett.antKolonner; j++){

                Celle celle = rutenett.hentCelle(i, j);
                celle.oppdaterStatus();
            }
        }
        genNr++;
    }
}