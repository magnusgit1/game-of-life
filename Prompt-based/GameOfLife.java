
// Definerer klassen GameOfLife, som oppretter variabler, leser inn 
// input fra bruker gjennom en Scanner, gjor disse verdiene om til int,
// for deretter aa tegne rutenettet utifra disse inputene

import java.util.Scanner;

class GameOfLife{

    public static void main(String[] args){

        Scanner brukerInput = new Scanner(System.in);

        String hent;
        int rader;
        int kolonner;

        System.out.println("Oppgi antall rader: ");
        hent = brukerInput.nextLine();
        rader = Integer.parseInt(hent);

        System.out.println("Oppgi antall kolonner: ");
        hent = brukerInput.nextLine();
        kolonner = Integer.parseInt(hent);

        Verden verden = new Verden(rader, kolonner);
        verden.tegn();

        System.out.println("Tast enter for ny generasjon: ");
        hent = brukerInput.nextLine();

        // Bruker her en while lokke som gjor det mulig for bruker aa kun taste enter
        // dersom det onskes aa lage/skrive ut en ny generasjon av celler. Bruker den 
        // samme variabelen som tidligere "hent" til aa holde paa brukerens input.
        
        while (hent == ""){

            verden.oppdatering();
            verden.tegn();

            System.out.println("Tast enter for ny generasjon // tast et tegn for aa avslutte: ");
            hent = brukerInput.nextLine();
        }
    }
}