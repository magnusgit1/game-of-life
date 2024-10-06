import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

    // Klassen controller som tar for seg implementasjonen av modellens metoder, og formidler disse til gui'en

class Controller{

    // Tar inn et gui, en modell, rutenettet til klassen rutenett i modell, og rutenettet i gui'en

    GUI gui;
    Model model;
    JButton[][] rutenett; 
    Model.Rutenett rutenettCelle; 
    Thread traad;

    public Controller(){
        this.gui = new GUI(this);
        this.model = new Model();
        this.rutenett = gui.hentRutenett();
        this.rutenettCelle = model.lagRutenett(10,10);
    }

    // Lager metoder som oppretter og starter traaden, til bruk i gui

    public void opprettTraad(int sovetid){
        traad = new Thread(new Traad(sovetid));
    }

    public void startTraad(){
        traad.start();
    }

    // Klassen Traad som tar hand om selve oppdateringen av "cellene" i gui'en, og gjor dette i sin egen traad

    class Traad implements Runnable{
        int sovetid;
        public Traad(int sovetid){
            this.sovetid = sovetid;
        }

        // traaden sover i tiden som sendes med traad-klassen * 100, utifra hva brukeren velger paa slideren
        // Deretter oppdateres rutenettet i modell, og deretter oppdateres gui-cellene utifra det oppdaterte rutenettet fra modellen

        @Override
        public void run(){
            try{
                while(true){
                    Thread.sleep(sovetid);
                    oppdater();
                    oppdaterGUI();
                    gui.settAntallLevende(hentAntallLevende());
                }
            } catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }

    // metode for aa hente rutenettet til modell-klassen

    public Model.Rutenett hentRutenettCelle(){
        return rutenettCelle;
    }

    // metode som oppretter 100 celler til et rutenett i modellen, sjekker hvorvidt brukeren har gjort en celle 
    // levende eller dod i gui'en sitt rutenett, og "kopierer" deretter det brukeren har gjort inn i rutenettet

    public void lagCeller(){

        for(int x = 9; x >= 0; x--){
            for(int y = 0; y <= 9; y++){
                Model.Celle celle = rutenettCelle.lagCelle(x,y);
                if(rutenett[x][y].getText().equals("")){
                    celle.settDoed();
                } else{
                    celle.settLevende();
                }
            }
        }
    }

    // identisk metode som ble brukt i oblig1, men gjenbruker den slik at gui'en skal faa tilgang paa oppdater()
    // teller levende naboer til alle celler, og oppdaterer deres status deretter

    public void oppdater(){
        
        for (int i = 0; i <= 9; i++){
            for (int j = 0; j <= 9; j++){

                Model.Celle enCelle = rutenettCelle.hentCelle(i, j);
                enCelle.tellLevendeNaboer();
            }
        }

        for (int i = 0; i < rutenettCelle.antRader; i++){
            for (int j = 0; j < rutenettCelle.antKolonner; j++){

                Model.Celle celle = rutenettCelle.hentCelle(i, j);
                celle.oppdaterStatus();
            }
        }
    }

    // Metode som gjenspeiler det som har skjedd i modellens rutenett, og printer dette ut i gui'en sitt rutenett, slik at
    // dette blir synlig for brukeren

    public void oppdaterGUI() throws InterruptedException{
        
        for(int i = 0; i <= 9; i++){
            for(int j = 0; j <= 9; j++){
                if(rutenettCelle.hentCelle(i,j).erLevende()){
                    rutenett[i][j].setText("❈");
                } else{
                    rutenett[i][j].setText("");
                }
            }
        }
    }
    
    // metode som kobler sammen cellene ved programstart, og metode som henter ut antall levende celler

    public void kobleAlleCeller(){
        rutenettCelle.kobleAlleCeller();
    }

    public int hentAntallLevende(){
        return rutenettCelle.antallLevende();
    }
}