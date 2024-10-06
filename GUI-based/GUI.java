
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

    // Klassen GUI som tar for seg alt det visuelle

class GUI{

    Controller controller;
    JButton[][] rutenett = new JButton[10][10];
    JLabel antLevende;
    int antallLevende = 0;
    JSlider slider;
    boolean iBruk = false;
    int teller = 0;

    public GUI(Controller controller){

        this.controller = controller;

        // setter standard-utseende

        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch(Exception e){
            System.exit(9);
        }

        // Lager vinduet

        JFrame vindu = new JFrame("Game Of Life");
        vindu.setSize(1000,700);
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindu.setLocationRelativeTo(null);
        
        // Lager ovre hovedpanel

        JPanel ovreMain = new JPanel();
        ovreMain.setLayout(new BorderLayout());
        ovreMain.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        ovreMain.setPreferredSize(new Dimension(230,130));
        vindu.add(ovreMain, BorderLayout.NORTH);

        // Lager panelet som skal ta for seg "menyen"

        JPanel ovrePanel = new JPanel();
        ovrePanel.setLayout(new GridLayout(1,4,20,5));
        ovrePanel.setPreferredSize(new Dimension(200,100));
        ovreMain.add(ovrePanel, BorderLayout.SOUTH);

        // Lager litt space med et tomt panel

        JPanel empty = new JPanel();
        empty.setPreferredSize(new Dimension(30,30));
        ovreMain.add(empty, BorderLayout.NORTH);
        
        // Fastslaar en generell font

        Font font = new Font("Arial", Font.PLAIN, 30);

        JLabel antLevendeTekst = new JLabel("Antall levende:");
        antLevendeTekst.setFont(new Font("Arial", Font.PLAIN, 20));
        antLevendeTekst.setHorizontalAlignment(JLabel.CENTER);
        antLevendeTekst.setVerticalAlignment(JLabel.CENTER);
        ovrePanel.add(antLevendeTekst);

        // JLabel som tar for seg hvor mange celler 

        antLevende = new JLabel("0");
        antLevende.setFont(font);
        antLevende.setPreferredSize(new Dimension(50,50));
        antLevende.setHorizontalAlignment(JLabel.CENTER);
        antLevende.setVerticalAlignment(JLabel.CENTER);
        ovrePanel.add(antLevende);

        // ActionListener som skal utfores naar startknappen benyttes
        // forst saa sjekkes det om brukeren har trykket paa startknappen
        // tidligere, eller at brukeren ikke har satt noen celler til aa leve, isaafall gjor startknappen ingenting.
        // dersom ingen av disse oppfylles, saa brukes controller sine metoder til aa opprette og koble sammen alle cellene
        // i et rutenett gjennom modell-klassen, speilet i forhold til hvilke celler brukeren har satt til aa vaere levende. 
        // Deretter saa bruker den verdien fra slideren til aa bestemme i hvilken hastighet programmet skal oppdateres i.

        class Start implements ActionListener{
            public void actionPerformed(ActionEvent e){

                if(hentAntallLevende() == 0 || iBruk){
                    return;
                }
                controller.lagCeller();
                controller.kobleAlleCeller();
                if(slider.getValue() == 0){
                    controller.opprettTraad(50);
                } else{
                    controller.opprettTraad(slider.getValue() * 100);
                }
                controller.startTraad();
                iBruk = true;
            }
        }
        JButton start = new JButton("Start");
        start.setFont(font);
        start.setHorizontalAlignment(JButton.CENTER);
        start.setHorizontalAlignment(JButton.CENTER);
        start.addActionListener(new Start());
        ovrePanel.add(start);

        class Avslutt implements ActionListener{
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        }
        JButton avslutt = new JButton("Avslutt");
        avslutt.setFont(font);
        avslutt.setHorizontalAlignment(JButton.CENTER);
        avslutt.setVerticalAlignment(JButton.CENTER);
        avslutt.addActionListener(new Avslutt());
        ovrePanel.add(avslutt);

        // slideren som brukes til aa bestemme sovetiden til traaden

        slider = new JSlider(0,20,10);
        JLabel sliderText = new JLabel("");
        slider.setPreferredSize(new Dimension(400,200));
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(1);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(5);
        slider.setPaintLabels(true);
        ovrePanel.add(slider, BorderLayout.SOUTH);

        // Panelet som skal ta for seg rutenettet

        JPanel hovedPanel = new JPanel();
        hovedPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        hovedPanel.setLayout(new GridLayout(10,10,1,1));
        hovedPanel.setPreferredSize(new Dimension(700,500));
        hovedPanel.setBackground(Color.WHITE);
        vindu.add(hovedPanel, BorderLayout.SOUTH);

        // Actionlistener som gjor at brukeren kan sette en celle til dod eller levende

        class SettLevende implements ActionListener{
            JButton knapp;
            
            public SettLevende(JButton knapp){
                this.knapp = knapp;
            }
            public void actionPerformed(ActionEvent e){
                
                if(iBruk){
                    return;
                }
                if(knapp.getText().equals("")){
                    knapp.setText("❈");
                    oek();
                    settAntallLevende(hentAntallLevende());

                } else{
                    knapp.setText("");
                    mink();
                    settAntallLevende(hentAntallLevende());
                }
                
            }
        }

        // Lager alle rutene/knappene i rutenettet

        for(int x = 9; x >= 0; x--){
            for(int y = 0; y <= 9; y++){
                JButton rute;
                if(Math.random() <= 0.3333){
                    rute = new JButton("❈");
                    oek();
                    settAntallLevende(hentAntallLevende());
                } else{
                    rute = new JButton("");
                }
                rute.setFont(new Font("Arial", Font.PLAIN, 35));
                rute.setHorizontalAlignment(JButton.CENTER);
                rute.setVerticalAlignment(JButton.CENTER);
                rute.addActionListener(new SettLevende(rute));
                rutenett[x][y] = rute;
                hovedPanel.add(rute);
            }
        }

        vindu.setVisible(true);
    }

    // metode for aa hente ut rutenettet

    public JButton[][] hentRutenett(){
        return rutenett;
    }

    // metode for aa endre antallet levende

    public void settAntallLevende(int ant){
        antLevende.setText(Integer.toString(ant));
    }

    // metoder for aa manipulere antall levende

    public void oek(){
        antallLevende++;
    }
    public void mink(){
        antallLevende--;
    }
    public int hentAntallLevende(){
        return antallLevende;
    }
}