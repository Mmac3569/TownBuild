package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Hlavní tøída - ovládá celou hru
 * @author matej
 */
public class TownBuild extends JFrame implements ActionListener, WindowListener, MouseListener, KeyListener {
    
    public static GUI.TownBuildGUI GUI = new GUI.TownBuildGUI();
    
    public static Path settings = Path.of("penize.txt");
    public static FileWriter Pwriter;
    public static FileWriter Wwriter;
    public static Path world = Path.of("world.txt");
    
    public static Timer vydelavani = new Timer();
    public static TimerTask vydelani = new Background();
    public static Timer odpocitavani = new Timer();
    public static TimerTask odpocitani = new Odpocet();
    
    public static long caszavreni;
    public static long casted = System.currentTimeMillis() / 1000;
    
    public static String obsahsouboru;
    public static String mince_str;
    public static String poklada;
    public static String save;
    
    public static long mince;
    public static int pocetdomu;
    public static int pocetcest;
    public static int pocetuhelnychdolu;
    public static int polozeneuhelnedoly;
    public static int placeInt;
    public static int zadani;
    public static long offlinereward;
    
    public static boolean vobchode = false;
    public static boolean vinventari = false;
    public static boolean vmeste = false;
    public static boolean pokladani = false;
    public static boolean starthry = false;
    
    public static String[] pole = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    public static String[] obsah;
    public static String[] cas_str = {"0", "0", "0", "0", "0", "0"};
    public static char[] zadani1 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '+', 'ì', 'š', 'è', 'ø', 'ž', 'ý', 'á', 'í', 'é', ';', '=', '´', 'ú', ')', 'ù', '§', '¨', ',', '.', '-', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7' ,'8', '9', '0', '°', '%', '¡', '/', '(', '"', '!', '?', ':', '_', '\'', '|', '€', 'ð', '[', ']', '³', '£', '#', '&', '@', '{', '}', '~', '¡', '^', '¢', '°', '²', '`', 'ÿ', '´', '½', '¨', '¸', '÷', '×', '$', 'ß', '¤', '<', '>', '*'};
    
    /**
     * Zjistï¿½ informace o stavu hry
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void FileReader() throws FileNotFoundException, IOException {
        obsahsouboru = Files.readString(settings);
        obsah = obsahsouboru.split("[ =\n]+");
        mince = Integer.parseInt(obsah[1]);
        pocetdomu = Integer.parseInt(obsah[3]);
        pocetcest = Integer.parseInt(obsah[5]);
        pocetuhelnychdolu = Integer.parseInt(obsah[7]);
        polozeneuhelnedoly = Integer.parseInt(obsah[9]);
        caszavreni = Integer.parseInt(obsah[10]);
        obsahsouboru = Files.readString(world);
        obsah = obsahsouboru.split("#");
        for (int i = 0; i < 140;) {
            pole[i] = obsah[i];
            i++;
        }
    }
    
    /**
     * Zapï¿½e do souborï¿½ informace o stavu hry napï¿½ï¿½klad kolik mï¿½ hrï¿½ï¿½ penï¿½z a staveb ï¿½i postavenï¿½ mï¿½sto
     * @throws IOException
     */
    public static void exit() throws IOException {
        try {
            Pwriter = new FileWriter("penize.txt");
            Pwriter.write("mince = "+mince+"\n");
            Pwriter.write("Domy = "+pocetdomu+"\n");
            Pwriter.write("Cesty = "+pocetcest+"\n");
            Pwriter.write("Uhelnedoly = "+pocetuhelnychdolu+"\n");
            Pwriter.write("polozeneUhelnedoly = "+polozeneuhelnedoly+"\n");
            casted = System.currentTimeMillis() /1000;
            Pwriter.write(Long.toString(casted));
            Pwriter.close();
            Wwriter = new FileWriter("world.txt");
            for (int i = 0; i < 140;) {
                Wwriter.write(pole[i]+"#");
                i++;
            }
            Wwriter.close();
        }
        catch (IOException e) {
            Logger.getLogger(TownBuild.class.getName()).log(Level.SEVERE, null, e);
        }
        System.exit(0);
    }
    
    /**
     * Nastavï¿½ okno
     * @param args
     */
    public static void main(String[] args) {
        GUI.InitWindow();
        GUI.InitLoadingGUI();
    }
    
    /**
     * Pï¿½idï¿½lï¿½ parametry vï¿½em pouï¿½ï¿½vanï¿½m objektï¿½m aneb naï¿½te hru
     */
    public static void Nacteni() {
        SwingUtilities.invokeLater(() -> {
            try{
                FileReader();
            }
            catch (FileNotFoundException e) {
                try {
                    throw e;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TownBuild.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(TownBuild.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            GUI.InitMainGUI();
            
            casted = System.currentTimeMillis() /1000;
            offlinereward = ((casted - caszavreni) / 20 * 100 * polozeneuhelnedoly);
            mince = mince+offlinereward;
            System.out.println("Zatï¿½m co jsi zde nebil, tvoje stavby ti vynesly "+Long.toString(offlinereward)+" mincï¿½");
            JOptionPane.showMessageDialog(GUI.nacitacipozadi, "Zatï¿½m co jsi zde nebil, tvoje stavby ti vynesly "+Long.toString(offlinereward)+" mincï¿½", "Vï¿½tej zpï¿½t!", JOptionPane.PLAIN_MESSAGE, new ImageIcon("mince.png"));
            vydelavani.schedule(vydelani, 1000, 20000);
            GUI.mrizkainit();
        });
    }
    
    /**
     * Zjistï¿½ mï¿½sto kliknutï¿½
     * @param o
     * @return pozemekx?y?(JButton)
     */
    public JButton getPlace(Object o) {
        if (o.equals(GUI.pozemekx0y0)) {
            placeInt = 0; return GUI.pozemekx0y0;
        }
        if (o.equals(GUI.pozemekx1y0)) {
            placeInt = 1; return GUI.pozemekx1y0;
        }
        if (o.equals(GUI.pozemekx2y0)) {
            placeInt = 2; return GUI.pozemekx2y0;
        }
        if (o.equals(GUI.pozemekx3y0)) {
            placeInt = 3; return GUI.pozemekx3y0;
        }
        if (o.equals(GUI.pozemekx4y0)) {
            placeInt = 4; return GUI.pozemekx4y0;
        }
        if (o.equals(GUI.pozemekx5y0)) {
            placeInt = 5; return GUI.pozemekx5y0;
        }
        if (o.equals(GUI.pozemekx6y0)) {
            placeInt = 6; return GUI.pozemekx6y0;
        }
        if (o.equals(GUI.pozemekx7y0)) {
            placeInt = 7; return GUI.pozemekx7y0;
        }
        if (o.equals(GUI.pozemekx8y0)) {
            placeInt = 8; return GUI.pozemekx8y0;
        }
        if (o.equals(GUI.pozemekx9y0)) {
            placeInt = 9; return GUI.pozemekx9y0;
        }
        if (o.equals(GUI.pozemekx0y1)) {
            placeInt = 10; return GUI.pozemekx0y1;
        }
        if (o.equals(GUI.pozemekx1y1)) {
            placeInt = 11; return GUI.pozemekx1y1;
        }
        if (o.equals(GUI.pozemekx2y1)) {
            placeInt = 12; return GUI.pozemekx2y1;
        }
        if (o.equals(GUI.pozemekx3y1)) {
            placeInt = 13; return GUI.pozemekx3y1;
        }
        if (o.equals(GUI.pozemekx4y1)) {
            placeInt = 14; return GUI.pozemekx4y1;
        }
        if (o.equals(GUI.pozemekx5y1)) {
            placeInt = 15; return GUI.pozemekx5y1;
        }
        if (o.equals(GUI.pozemekx6y1)) {
            placeInt = 16; return GUI.pozemekx6y1;
        }
        if (o.equals(GUI.pozemekx7y1)) {
            placeInt = 17; return GUI.pozemekx7y1;
        }
        if (o.equals(GUI.pozemekx8y1)) {
            placeInt = 18; return GUI.pozemekx8y1;
        }
        if (o.equals(GUI.pozemekx9y1)) {
            placeInt = 19; return GUI.pozemekx9y1;
        }
        if (o.equals(GUI.pozemekx0y2)) {
            placeInt = 20; return GUI.pozemekx0y2;
        }
        if (o.equals(GUI.pozemekx1y2)) {
            placeInt = 21; return GUI.pozemekx1y2;
        }
        if (o.equals(GUI.pozemekx2y2)) {
            placeInt = 22; return GUI.pozemekx2y2;
        }
        if (o.equals(GUI.pozemekx3y2)) {
            placeInt = 23; return GUI.pozemekx3y2;
        }
        if (o.equals(GUI.pozemekx4y2)) {
            placeInt = 24; return GUI.pozemekx4y2;
        }
        if (o.equals(GUI.pozemekx5y2)) {
            placeInt = 25; return GUI.pozemekx5y2;
        }
        if (o.equals(GUI.pozemekx6y2)) {
            placeInt = 26; return GUI.pozemekx6y2;
        }
        if (o.equals(GUI.pozemekx7y2)) {
            placeInt = 27; return GUI.pozemekx7y2;
        }
        if (o.equals(GUI.pozemekx8y2)) {
            placeInt = 28; return GUI.pozemekx8y2;
        }
        if (o.equals(GUI.pozemekx9y2)) {
            placeInt = 29; return GUI.pozemekx9y2;
        }
        if (o.equals(GUI.pozemekx0y3)) {
            placeInt = 30; return GUI.pozemekx0y3;
        }
        if (o.equals(GUI.pozemekx1y3)) {
            placeInt = 31; return GUI.pozemekx1y3;
        }
        if (o.equals(GUI.pozemekx2y3)) {
            placeInt = 32; return GUI.pozemekx2y3;
        }
        if (o.equals(GUI.pozemekx3y3)) {
            placeInt = 33; return GUI.pozemekx3y3;
        }
        if (o.equals(GUI.pozemekx4y3)) {
            placeInt = 34; return GUI.pozemekx4y3;
        }
        if (o.equals(GUI.pozemekx5y3)) {
            placeInt = 35; return GUI.pozemekx5y3;
        }
        if (o.equals(GUI.pozemekx6y3)) {
            placeInt = 36; return GUI.pozemekx6y3;
        }
        if (o.equals(GUI.pozemekx7y3)) {
            placeInt = 37; return GUI.pozemekx7y3;
        }
        if (o.equals(GUI.pozemekx8y3)) {
            placeInt = 38; return GUI.pozemekx8y3;
        }
        if (o.equals(GUI.pozemekx9y3)) {
            placeInt = 39; return GUI.pozemekx9y3;
        }
        if (o.equals(GUI.pozemekx0y4)) {
            placeInt = 40; return GUI.pozemekx0y4;
        }
        if (o.equals(GUI.pozemekx1y4)) {
            placeInt = 41; return GUI.pozemekx1y4;
        }
        if (o.equals(GUI.pozemekx2y4)) {
            placeInt = 42; return GUI.pozemekx2y4;
        }
        if (o.equals(GUI.pozemekx3y4)) {
            placeInt = 43; return GUI.pozemekx3y4;
        }
        if (o.equals(GUI.pozemekx4y4)) {
            placeInt = 44; return GUI.pozemekx4y4;
        }
        if (o.equals(GUI.pozemekx5y4)) {
            placeInt = 45; return GUI.pozemekx5y4;
        }
        if (o.equals(GUI.pozemekx6y4)) {
            placeInt = 46; return GUI.pozemekx6y4;
        }
        if (o.equals(GUI.pozemekx7y4)) {
            placeInt = 47; return GUI.pozemekx7y4;
        }
        if (o.equals(GUI.pozemekx8y4)) {
            placeInt = 48; return GUI.pozemekx8y4;
        }
        if (o.equals(GUI.pozemekx9y4)) {
            placeInt = 49; return GUI.pozemekx9y4;
        }
        if (o.equals(GUI.pozemekx0y5)) {
            placeInt = 50; return GUI.pozemekx0y5;
        }
        if (o.equals(GUI.pozemekx1y5)) {
            placeInt = 51; return GUI.pozemekx1y5;
        }
        if (o.equals(GUI.pozemekx2y5)) {
            placeInt = 52; return GUI.pozemekx2y5;
        }
        if (o.equals(GUI.pozemekx3y5)) {
            placeInt = 53; return GUI.pozemekx3y5;
        }
        if (o.equals(GUI.pozemekx4y5)) {
            placeInt = 54; return GUI.pozemekx4y5;
        }
        if (o.equals(GUI.pozemekx5y5)) {
            placeInt = 55; return GUI.pozemekx5y5;
        }
        if (o.equals(GUI.pozemekx6y5)) {
            placeInt = 56; return GUI.pozemekx6y5;
        }
        if (o.equals(GUI.pozemekx7y5)) {
            placeInt = 57; return GUI.pozemekx7y5;
        }
        if (o.equals(GUI.pozemekx8y5)) {
            placeInt = 58; return GUI.pozemekx8y5;
        }
        if (o.equals(GUI.pozemekx9y5)) {
            placeInt = 59; return GUI.pozemekx9y5;
        }
        if (o.equals(GUI.pozemekx0y6)) {
            placeInt = 60; return GUI.pozemekx0y6;
        }
        if (o.equals(GUI.pozemekx1y6)) {
            placeInt = 61; return GUI.pozemekx1y6;
        }
        if (o.equals(GUI.pozemekx2y6)) {
            placeInt = 62; return GUI.pozemekx2y6;
        }
        if (o.equals(GUI.pozemekx3y6)) {
            placeInt = 63; return GUI.pozemekx3y6;
        }
        if (o.equals(GUI.pozemekx4y6)) {
            placeInt = 64; return GUI.pozemekx4y6;
        }
        if (o.equals(GUI.pozemekx5y6)) {
            placeInt = 65; return GUI.pozemekx5y6;
        }
        if (o.equals(GUI.pozemekx6y6)) {
            placeInt = 66; return GUI.pozemekx6y6;
        }
        if (o.equals(GUI.pozemekx7y6)) {
            placeInt = 67; return GUI.pozemekx7y6;
        }
        if (o.equals(GUI.pozemekx8y6)) {
            placeInt = 68; return GUI.pozemekx8y6;
        }
        if (o.equals(GUI.pozemekx9y6)) {
            placeInt = 69; return GUI.pozemekx9y6;
        }
        if (o.equals(GUI.pozemekx0y7)) {
            placeInt = 70; return GUI.pozemekx0y7;
        }
        if (o.equals(GUI.pozemekx1y7)) {
            placeInt = 71; return GUI.pozemekx1y7;
        }
        if (o.equals(GUI.pozemekx2y7)) {
            placeInt = 72; return GUI.pozemekx2y7;
        }
        if (o.equals(GUI.pozemekx3y7)) {
            placeInt = 73; return GUI.pozemekx3y7;
        }
        if (o.equals(GUI.pozemekx4y7)) {
            placeInt = 74; return GUI.pozemekx4y7;
        }
        if (o.equals(GUI.pozemekx5y7)) {
            placeInt = 75; return GUI.pozemekx5y7;
        }
        if (o.equals(GUI.pozemekx6y7)) {
            placeInt = 76; return GUI.pozemekx6y7;
        }
        if (o.equals(GUI.pozemekx7y7)) {
            placeInt = 77; return GUI.pozemekx7y7;
        }
        if (o.equals(GUI.pozemekx8y7)) {
            placeInt = 78; return GUI.pozemekx8y7;
        }
        if (o.equals(GUI.pozemekx9y7)) {
            placeInt = 79; return GUI.pozemekx9y7;
        }
        if (o.equals(GUI.pozemekx0y8)) {
            placeInt = 80; return GUI.pozemekx0y8;
        }
        if (o.equals(GUI.pozemekx1y8)) {
            placeInt = 81; return GUI.pozemekx1y8;
        }
        if (o.equals(GUI.pozemekx2y8)) {
            placeInt = 82; return GUI.pozemekx2y8;
        }
        if (o.equals(GUI.pozemekx3y8)) {
            placeInt = 83; return GUI.pozemekx3y8;
        }
        if (o.equals(GUI.pozemekx4y8)) {
            placeInt = 84; return GUI.pozemekx4y8;
        }
        if (o.equals(GUI.pozemekx5y8)) {
            placeInt = 85; return GUI.pozemekx5y8;
        }
        if (o.equals(GUI.pozemekx6y8)) {
            placeInt = 86; return GUI.pozemekx6y8;
        }
        if (o.equals(GUI.pozemekx7y8)) {
            placeInt = 87; return GUI.pozemekx7y8;
        }
        if (o.equals(GUI.pozemekx8y8)) {
            placeInt = 88; return GUI.pozemekx8y8;
        }
        if (o.equals(GUI.pozemekx9y8)) {
            placeInt = 89; return GUI.pozemekx9y8;
        }
        if (o.equals(GUI.pozemekx0y9)) {
            placeInt = 90; return GUI.pozemekx0y9;
        }
        if (o.equals(GUI.pozemekx1y9)) {
            placeInt = 91; return GUI.pozemekx1y9;
        }
        if (o.equals(GUI.pozemekx2y9)) {
            placeInt = 92; return GUI.pozemekx2y9;
        }
        if (o.equals(GUI.pozemekx3y9)) {
            placeInt = 93; return GUI.pozemekx3y9;
        }
        if (o.equals(GUI.pozemekx4y9)) {
            placeInt = 94; return GUI.pozemekx4y9;
        }
        if (o.equals(GUI.pozemekx5y9)) {
            placeInt = 95; return GUI.pozemekx5y9;
        }
        if (o.equals(GUI.pozemekx6y9)) {
            placeInt = 96; return GUI.pozemekx6y9;
        }
        if (o.equals(GUI.pozemekx7y9)) {
            placeInt = 97; return GUI.pozemekx7y9;
        }
        if (o.equals(GUI.pozemekx8y9)) {
            placeInt = 98; return GUI.pozemekx8y9;
        }
        if (o.equals(GUI.pozemekx9y9)) {
            placeInt = 99; return GUI.pozemekx9y9;
        }
        if (o.equals(GUI.pozemekx0y10)) {
            placeInt = 100; return GUI.pozemekx0y10;
        }
        if (o.equals(GUI.pozemekx1y10)) {
            placeInt = 101; return GUI.pozemekx1y10;
        }
        if (o.equals(GUI.pozemekx2y10)) {
            placeInt = 102; return GUI.pozemekx2y10;
        }
        if (o.equals(GUI.pozemekx3y10)) {
            placeInt = 103; return GUI.pozemekx3y10;
        }
        if (o.equals(GUI.pozemekx4y10)) {
            placeInt = 104; return GUI.pozemekx4y10;
        }
        if (o.equals(GUI.pozemekx5y10)) {
            placeInt = 105; return GUI.pozemekx5y10;
        }
        if (o.equals(GUI.pozemekx6y10)) {
            placeInt = 106; return GUI.pozemekx6y10;
        }
        if (o.equals(GUI.pozemekx7y10)) {
            placeInt = 107; return GUI.pozemekx7y10;
        }
        if (o.equals(GUI.pozemekx8y10)) {
            placeInt = 108; return GUI.pozemekx8y10;
        }
        if (o.equals(GUI.pozemekx9y10)) {
            placeInt = 109; return GUI.pozemekx9y10;
        }
        if (o.equals(GUI.pozemekx0y11)) {
            placeInt = 110; return GUI.pozemekx0y11;
        }
        if (o.equals(GUI.pozemekx1y11)) {
            placeInt = 111; return GUI.pozemekx1y11;
        }
        if (o.equals(GUI.pozemekx2y11)) {
            placeInt = 112; return GUI.pozemekx2y11;
        }
        if (o.equals(GUI.pozemekx3y11)) {
            placeInt = 113; return GUI.pozemekx3y11;
        }
        if (o.equals(GUI.pozemekx4y11)) {
            placeInt = 114; return GUI.pozemekx4y11;
        }
        if (o.equals(GUI.pozemekx5y11)) {
            placeInt = 115; return GUI.pozemekx5y11;
        }
        if (o.equals(GUI.pozemekx6y11)) {
            placeInt = 116; return GUI.pozemekx6y11;
        }
        if (o.equals(GUI.pozemekx7y11)) {
            placeInt = 117; return GUI.pozemekx7y11;
        }
        if (o.equals(GUI.pozemekx8y11)) {
            placeInt = 118; return GUI.pozemekx8y11;
        }
        if (o.equals(GUI.pozemekx9y11)) {
            placeInt = 119; return GUI.pozemekx9y11;
        }
        if (o.equals(GUI.pozemekx0y12)) {
            placeInt = 120; return GUI.pozemekx0y12;
        }
        if (o.equals(GUI.pozemekx1y12)) {
            placeInt = 121; return GUI.pozemekx1y12;
        }
        if (o.equals(GUI.pozemekx2y12)) {
            placeInt = 122; return GUI.pozemekx2y12;
        }
        if (o.equals(GUI.pozemekx3y12)) {
            placeInt = 123; return GUI.pozemekx3y12;
        }
        if (o.equals(GUI.pozemekx4y12)) {
            placeInt = 124; return GUI.pozemekx4y12;
        }
        if (o.equals(GUI.pozemekx5y12)) {
            placeInt = 125; return GUI.pozemekx5y12;
        }
        if (o.equals(GUI.pozemekx6y12)) {
            placeInt = 126; return GUI.pozemekx6y12;
        }
        if (o.equals(GUI.pozemekx7y12)) {
            placeInt = 127; return GUI.pozemekx7y12;
        }
        if (o.equals(GUI.pozemekx8y12)) {
            placeInt = 128; return GUI.pozemekx8y12;
        }
        if (o.equals(GUI.pozemekx9y12)) {
            placeInt = 129; return GUI.pozemekx9y12;
        }
        if (o.equals(GUI.pozemekx0y13)) {
            placeInt = 130; return GUI.pozemekx0y13;
        }
        if (o.equals(GUI.pozemekx1y13)) {
            placeInt = 131; return GUI.pozemekx1y13;
        }
        if (o.equals(GUI.pozemekx2y13)) {
            placeInt = 132; return GUI.pozemekx2y13;
        }
        if (o.equals(GUI.pozemekx3y13)) {
            placeInt = 133; return GUI.pozemekx3y13;
        }
        if (o.equals(GUI.pozemekx4y13)) {
            placeInt = 134; return GUI.pozemekx4y13;
        }
        if (o.equals(GUI.pozemekx5y13)) {
            placeInt = 135; return GUI.pozemekx5y13;
        }
        if (o.equals(GUI.pozemekx6y13)) {
            placeInt = 136; return GUI.pozemekx6y13;
        }
        if (o.equals(GUI.pozemekx7y13)) {
            placeInt = 137; return GUI.pozemekx7y13;
        }
        if (o.equals(GUI.pozemekx8y13)) {
            placeInt = 138; return GUI.pozemekx8y13;
        }
        if (o.equals(GUI.pozemekx9y13)) {
            placeInt = 139; return GUI.pozemekx9y13;
        }
        return null;
    }
    
    /**
     * Zjistï¿½ ikonu
     * @param value
     * @return vyslednaikona(ImageIcon)
     */
    public static ImageIcon getIcon(int value) {
        if (pokladani == true) {
            if (null != poklada) switch (poklada) {
                case "Cesta": {
                    if (pocetcest-1 >= 0) {
                        pocetcest--;
                        GUI.vyslednaIkona = GUI.pozemekCesta;
                        save = "1";
                    }
                    else {
                        System.out.println("Doï¿½li ti cesty!\nPokud chceï¿½ poloï¿½it dalï¿½ï¿½ cestu, zajdi do obchodu.");
                        GUI.vyslednaIkona = GUI.obrazekpozemku;
                    }
                }
                case "Dum": {
                    if (pocetdomu-1 >= 0) {
                        pocetdomu--;
                        GUI.vyslednaIkona = GUI.pozemekDum;
                        save = "2";
                    }
                    else {
                        System.out.println("Doï¿½li ti cesty!\nPokud chceï¿½ poloï¿½it dalï¿½ï¿½ cestu, zajdi do obchodu.");
                        GUI.vyslednaIkona = GUI.obrazekpozemku;
                    }
                }
                case "Uhelny dul": {
                    if (pocetuhelnychdolu-1 >= 0) {
                        pocetuhelnychdolu--;
                        polozeneuhelnedoly++;
                        GUI.vyslednaIkona = GUI.pozemekUhelnydul;
                        save = "3";
                    }
                    else {
                        System.out.println("Doï¿½li ti cesty!\nPokud chceï¿½ poloï¿½it dalï¿½ï¿½ cestu, zajdi do obchodu.");
                        GUI.vyslednaIkona = GUI.obrazekpozemku;
                    }
                }
                default: {
                }
            }
        }
        else if (vmeste == true) {
            if (null != pole[placeInt]) switch (pole[placeInt]) {
                case "1":
                    pocetcest++;
                    GUI.vyslednaIkona = GUI.obrazekpozemku;
                    save = "0";
                    break;
                case "2":
                    pocetdomu++;
                    GUI.vyslednaIkona = GUI.obrazekpozemku;
                    save = "0";
                    break;
                case "3":
                    pocetuhelnychdolu++;
                    polozeneuhelnedoly--;
                    GUI.vyslednaIkona = GUI.obrazekpozemku;
                    save = "0";
                    break;
                default:
                    break;
            }
            System.out.println("objekt odstranï¿½n");
        }
        else {
            if (value == 1) {
                GUI.vyslednaIkona = GUI.pozemekCesta;
            }
            else if (value == 2) {
                GUI.vyslednaIkona = GUI.pozemekDum;
            }
            else if (value == 3) {
                GUI.vyslednaIkona = GUI.pozemekUhelnydul;
            }
            else {
                GUI.vyslednaIkona = GUI.obrazekpozemku;
            }
        }
        return GUI.vyslednaIkona;
    }
    
    /**
     * Zjistï¿½ ikonu na zï¿½kladï¿½ informacï¿½ v souboru world.txt
     */
    public static void getDefaultIcon() {
        for (int i = 0; i < 140;) {
            if (i == 0) {
                GUI.pozemekx0y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 1) {
                GUI.pozemekx1y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 2) {
                GUI.pozemekx2y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 3) {
                GUI.pozemekx3y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 4) {
                GUI.pozemekx4y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 5) {
                GUI.pozemekx5y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 6) {
                GUI.pozemekx6y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 7) {
                GUI.pozemekx7y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 8) {
                GUI.pozemekx8y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 9) {
                GUI.pozemekx9y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 10) {
                GUI.pozemekx0y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 11) {
                GUI.pozemekx1y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 12) {
                GUI.pozemekx2y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 13) {
                GUI.pozemekx3y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 14) {
                GUI.pozemekx4y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 15) {
                GUI.pozemekx5y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 16) {
                GUI.pozemekx6y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 17) {
                GUI.pozemekx7y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 18) {
                GUI.pozemekx8y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 19) {
                GUI.pozemekx9y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 20) {
                GUI.pozemekx0y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 21) {
                GUI.pozemekx1y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 22) {
                GUI.pozemekx2y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 23) {
                GUI.pozemekx3y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 24) {
                GUI.pozemekx4y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 25) {
                GUI.pozemekx5y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 26) {
                GUI.pozemekx6y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 27) {
                GUI.pozemekx7y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 28) {
                GUI.pozemekx8y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 29) {
                GUI.pozemekx9y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 30) {
                GUI.pozemekx0y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 31) {
                GUI.pozemekx1y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 32) {
                GUI.pozemekx2y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 33) {
                GUI.pozemekx3y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 34) {
                GUI.pozemekx4y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 35) {
                GUI.pozemekx5y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 36) {
                GUI.pozemekx6y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 37) {
                GUI.pozemekx7y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 38) {
                GUI.pozemekx8y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 39) {
                GUI.pozemekx9y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 40) {
                GUI.pozemekx0y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 41) {
                GUI.pozemekx1y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 42) {
                GUI.pozemekx2y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 43) {
                GUI.pozemekx3y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 44) {
                GUI.pozemekx4y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 45) {
                GUI.pozemekx5y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 46) {
                GUI.pozemekx6y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 47) {
                GUI.pozemekx7y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 48) {
                GUI.pozemekx8y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 49) {
                GUI.pozemekx9y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 50) {
                GUI.pozemekx0y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 51) {
                GUI.pozemekx1y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 52) {
                GUI.pozemekx2y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 53) {
                GUI.pozemekx3y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 54) {
                GUI.pozemekx4y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 55) {
                GUI.pozemekx5y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 56) {
                GUI.pozemekx6y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 57) {
                GUI.pozemekx7y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 58) {
                GUI.pozemekx8y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 59) {
                GUI.pozemekx9y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 60) {
                GUI.pozemekx0y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 61) {
                GUI.pozemekx1y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 62) {
                GUI.pozemekx2y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 63) {
                GUI.pozemekx3y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 64) {
                GUI.pozemekx4y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 65) {
                GUI.pozemekx5y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 66) {
                GUI.pozemekx6y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 67) {
                GUI.pozemekx7y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 68) {
                GUI.pozemekx8y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 69) {
                GUI.pozemekx9y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 70) {
                GUI.pozemekx0y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 71) {
                GUI.pozemekx1y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 72) {
                GUI.pozemekx2y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 73) {
                GUI.pozemekx3y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 74) {
                GUI.pozemekx4y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 75) {
                GUI.pozemekx5y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 76) {
                GUI.pozemekx6y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 77) {
                GUI.pozemekx7y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 78) {
                GUI.pozemekx8y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 79) {
                GUI.pozemekx9y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 80) {
                GUI.pozemekx0y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 81) {
                GUI.pozemekx1y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 82) {
                GUI.pozemekx2y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 83) {
                GUI.pozemekx3y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 84) {
                GUI.pozemekx4y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 85) {
                GUI.pozemekx5y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 86) {
                GUI.pozemekx6y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 87) {
                GUI.pozemekx7y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 88) {
                GUI.pozemekx8y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 89) {
                GUI.pozemekx9y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 90) {
                GUI.pozemekx0y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 91) {
                GUI.pozemekx1y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 92) {
                GUI.pozemekx2y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 93) {
                GUI.pozemekx3y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 94) {
                GUI.pozemekx4y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 95) {
                GUI.pozemekx5y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 96) {
                GUI.pozemekx6y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 97) {
                GUI.pozemekx7y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 98) {
                GUI.pozemekx8y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 99) {
                GUI.pozemekx9y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 100) {
                GUI.pozemekx0y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 101) {
                GUI.pozemekx1y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 102) {
                GUI.pozemekx2y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 103) {
                GUI.pozemekx3y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 104) {
                GUI.pozemekx4y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 105) {
                GUI.pozemekx5y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 106) {
                GUI.pozemekx6y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 107) {
                GUI.pozemekx7y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 108) {
                GUI.pozemekx8y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 109) {
                GUI.pozemekx9y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 110) {
                GUI.pozemekx0y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 111) {
                GUI.pozemekx1y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 112) {
                GUI.pozemekx2y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 113) {
                GUI.pozemekx3y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 114) {
                GUI.pozemekx4y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 115) {
                GUI.pozemekx5y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 116) {
                GUI.pozemekx6y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 117) {
                GUI.pozemekx7y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 118) {
                GUI.pozemekx8y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 119) {
                GUI.pozemekx9y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 120) {
                GUI.pozemekx0y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 121) {
                GUI.pozemekx1y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 122) {
                GUI.pozemekx2y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 123) {
                GUI.pozemekx3y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 124) {
                GUI.pozemekx4y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 125) {
                GUI.pozemekx5y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 126) {
                GUI.pozemekx6y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 127) {
                GUI.pozemekx7y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 128) {
                GUI.pozemekx8y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 129) {
                GUI.pozemekx9y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 130) {
                GUI.pozemekx0y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 131) {
                GUI.pozemekx1y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 132) {
                GUI.pozemekx2y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 133) {
                GUI.pozemekx3y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 134) {
                GUI.pozemekx4y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 135) {
                GUI.pozemekx5y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 136) {
                GUI.pozemekx6y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 137) {
                GUI.pozemekx7y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 138) {
                GUI.pozemekx8y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 139) {
                GUI.pozemekx9y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            i++;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            if (e.getSource().equals(GUI.obchod)) {
                if (vobchode == false) {
                    vobchode = true;
                    vinventari = false;
                    vmeste = false;
                    pokladani = false;
                    GUI.listveci.setVisible(false);
                    GUI.ukazatelpenez.setVisible(true);
                    GUI.buypanel.setVisible(true);
                    GUI.mrizka.setVisible(false);
                    GUI.okno.remove(GUI.listveci);
                    GUI.okno.remove(GUI.mrizka);
                    GUI.okno.add(GUI.buypanel);
                    GUI.okno.getContentPane().add(GUI.ukazatelpenez, BorderLayout.NORTH);
                    GUI.buypanel.add(GUI.koupitdum);
                    GUI.buypanel.add(GUI.koupitdum);
                    GUI.buypanel.add(GUI.koupitdum);
                    GUI.buypanel.add(GUI.koupitdum);
                    GUI.buypanel.setLayout(new GridLayout(2, 5));
                }
                else {
                    System.out.println("Uï¿½ jsi v obchodï¿½!");
                    JOptionPane.showMessageDialog(rootPane, "Uï¿½ jsi v obchodï¿½!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource().equals(GUI.hrat)) {
                GUI.okno.remove(GUI.hrat);
                GUI.okno.remove(GUI.nacitacipozadi);
                GUI.okno.setVisible(false);
                GUI.okno.setVisible(true);
                vobchode = true;
                Nacteni();
            }
            else if (e.getSource().equals(GUI.inventar)) {
                if (vinventari == false) {
                    GUI.ukazatelpenez.setVisible(false);
                    GUI.buypanel.setVisible(false);
                    GUI.listveci.setVisible(true);
                    GUI.mrizka.setVisible(false);
                    GUI.okno.remove(GUI.buypanel);
                    GUI.okno.remove(GUI.ukazatelpenez);
                    GUI.okno.remove(GUI.mrizka);
                    GUI.okno.add(GUI.listveci);
                    GUI.invDum.setText("Domy: "+Integer.toString(pocetdomu));
                    GUI.invCesta.setText("Cesty: "+Integer.toString(pocetcest));
                    GUI.invUhelnydul.setText("Uhelnï¿½ doly: "+Integer.toString(pocetuhelnychdolu));
                    vobchode = false;
                    vinventari = true;
                    vmeste = false;
                    pokladani = false;
                }
                else {
                    System.out.println("Uï¿½ jsi v invetï¿½ï¿½i!");
                    JOptionPane.showMessageDialog(rootPane, "Uï¿½ jsi v inventï¿½ï¿½i!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource().equals(GUI.koupitdum)) {
                if (mince-1000 >= 0) {
                    mince = mince-1000;
                    pocetdomu++;
                    System.out.println("Dï¿½m zakoupen\nNinï¿½ mï¿½ "+pocetdomu+" domï¿½\n");
                    mince_str = Long.toString(mince);
                    GUI.ukazatelpenez.setText(mince_str);
                }
                else {
                    System.out.println("Vypadï¿½ to, ï¿½e ti doï¿½li penï¿½ze\n");
                    JOptionPane.showMessageDialog(rootPane, "Vypadï¿½ to, ï¿½e ti doï¿½li penï¿½ze", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource().equals(GUI.koupitcestu)) {
                if (mince-100 >= 0) {
                    mince = mince-100;
                    pocetcest++;
                    System.out.println("Cesta zakoupena\nNinï¿½ mï¿½ "+pocetcest+" cest\n");
                    mince_str = Long.toString(mince);
                    GUI.ukazatelpenez.setText(mince_str);
                }
                else {
                    System.out.println("Vypadï¿½ to, ï¿½e ti doï¿½li penï¿½ze\n");
                    JOptionPane.showMessageDialog(rootPane, "Vypadï¿½ to, ï¿½e ti doï¿½li penï¿½ze", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource().equals(GUI.koupituhelnydul)) {
                if (mince-5000 >= 0) {
                    mince = mince-5000;
                    pocetuhelnychdolu++;
                    System.out.println("Uhelnï¿½ dï¿½l zakoupen\nNinï¿½ mï¿½ "+pocetuhelnychdolu+" uhelnï¿½ch dolï¿½\n");
                    mince_str = Long.toString(mince);
                    GUI.ukazatelpenez.setText(mince_str);
                }
                else {
                    System.out.println("Vypadï¿½ to, ï¿½e ti doï¿½li penï¿½ze\n");
                    JOptionPane.showMessageDialog(rootPane, "Vypadï¿½ to, ï¿½e ti doï¿½li penï¿½ze", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource().equals(GUI.mesto)) {
                if (vmeste == false) {
                    GUI.buypanel.setVisible(false);
                    GUI.listveci.setVisible(false);
                    GUI.ukazatelpenez.setVisible(false);
                    GUI.mrizka.setVisible(true);
                    GUI.okno.remove(GUI.listveci);
                    GUI.okno.remove(GUI.buypanel);
                    GUI.okno.remove(GUI.ukazatelpenez);
                    GUI.okno.add(GUI.mrizka);
                    vobchode = false;
                    vinventari = false;
                    vmeste = true;
                    pokladani = false;
                }
                else {
                    System.out.println("Uï¿½ jsi v mï¿½stï¿½!");
                    JOptionPane.showMessageDialog(rootPane, "Uï¿½ jsi v mï¿½stï¿½!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource().equals(GUI.nastaveni)) {
                GUI.buypanel.setVisible(false);
                GUI.listveci.setVisible(false);
                GUI.ukazatelpenez.setVisible(false);
                GUI.mrizka.setVisible(false);
                GUI.okno.remove(GUI.listveci);
                GUI.okno.remove(GUI.buypanel);
                GUI.okno.remove(GUI.ukazatelpenez);
                GUI.okno.remove(GUI.mrizka);
                JOptionPane.showMessageDialog(rootPane, GUI.infooapce, "O aplikaci", JOptionPane.PLAIN_MESSAGE, null);
                vobchode = false;
                vinventari = false;
                vmeste = false;
                pokladani = false;
            }
            else if (e.getSource().equals(GUI.ziskatpenize)) {
                GUI.buypanel.setVisible(false);
                GUI.listveci.setVisible(false);
                GUI.ukazatelpenez.setVisible(false);
                GUI.mrizka.setVisible(false);
                GUI.jaksepise.setVisible(true);
                GUI.okno.remove(GUI.listveci);
                GUI.okno.remove(GUI.buypanel);
                GUI.okno.remove(GUI.ukazatelpenez);
                GUI.okno.remove(GUI.mrizka);
                GUI.okno.add(GUI.jaksepise);
                vobchode = false;
                vinventari = false;
                vmeste = false;
                pokladani = false;
            }
            else if (e.getSource().equals(GUI.jaksepise)) {
                GUI.okno.remove(GUI.jaksepise);
                GUI.okno.add(GUI.hernipole);
                GUI.napis.setVisible(true);
                GUI.jaksepise.setVisible(false);
                GUI.hernipole.setVisible(true);
                GUI.ukazatelpenez.setVisible(true);
                GUI.hernipole.add(GUI.ukazatelpenez);
                GUI.ukazatelpenez.setBounds(0, 0, 700, 50);
                starthry = true;
                odpocitavani.schedule(odpocitani, 1000, 1000);
            }
            else {
                if (pokladani == true) {
                    Object objekt = e.getSource();
                    GUI.place = getPlace(objekt);
                    GUI.place.setIcon(getIcon(0));
                    pole[placeInt] = save;
                }
                else if (pokladani == false) {
                    Object objekt = e.getSource();
                    GUI.place = getPlace(objekt);
                    GUI.place.setIcon(getIcon(0));
                    pole[placeInt] = save;
                }
            }
            
        });
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (e.getSource().equals(GUI.okno)){
            try {
                exit();
            } catch (IOException ex) {
                Logger.getLogger(TownBuild.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int tip = JOptionPane.INFORMATION_MESSAGE;
            if (e.getComponent().equals(GUI.invCesta)) {
                System.out.println("Cena: 100\nVï¿½nos: 0 mincï¿½/min\nVlastnï¿½no: "+pocetcest+"\nPotï¿½ebnï¿½ mï¿½sto: 1 kostiï¿½ka\nKategorie: Cesty\nLevel: 0\nSpeciï¿½lnï¿½ funkce:\n    +Lidi mï¿½ï¿½ou chodit pï¿½es");
                String[] text = {"Cena: 100","Vï¿½nos: 0 mincï¿½/min","Vlastnï¿½no: "+pocetcest,"Potï¿½ebnï¿½ mï¿½sto: 1 kostiï¿½ka","Kategorie: Cesty","Level: 0","Speciï¿½lnï¿½ funkce:","    +Lidi mï¿½ï¿½ou chodit pï¿½es"};
                JOptionPane.showMessageDialog(rootPane, text, "Cesta", tip, GUI.obrazekcesty);
            }
            else if (e.getComponent().equals(GUI.invDum)) {
                System.out.println("Cena: 1000\nVï¿½nos: 0 mincï¿½/min\nVlastnï¿½no: "+pocetdomu+"\nPotï¿½ebnï¿½ mï¿½sto: 1 kostiï¿½ka\nKategorie: Obydlï¿½\nLevel: 0\nSpeciï¿½lnï¿½ funkce:\n    +Slouï¿½ï¿½ k ubytovï¿½nï¿½ lidï¿½");
                String[] text = {"Cena: 1000","Vï¿½nos: 0 mincï¿½/min","Vlastnï¿½no: "+pocetdomu,"Potï¿½ebnï¿½ mï¿½sto: 1 kostiï¿½ka","Kategorie: Obydlï¿½","Level: 0","Speciï¿½lnï¿½ funkce:","    +Slouï¿½ï¿½ k ubytovï¿½nï¿½ lidï¿½"};
                JOptionPane.showMessageDialog(rootPane, text, "Dï¿½m", tip, GUI.obrazekdomu);
            }
            else if (e.getComponent().equals(GUI.invUhelnydul)) {
                System.out.println("Cena: 5000\nVï¿½nos: 100 mincï¿½/min\nVlastnï¿½no: "+pocetuhelnychdolu+"\nPotï¿½ebnï¿½ mï¿½sto: 1 kostiï¿½ka\nKategorie: Doly\nLevel: 0\nSpeciï¿½lnï¿½ funkce:\n    +Mï¿½ï¿½e mï¿½t zamï¿½stnance");
                String[] text = {"Cena: 5000","Vï¿½nos: 100 mincï¿½/min","Vlastnï¿½no: "+pocetuhelnychdolu,"Potï¿½ebnï¿½ mï¿½sto: 1 kostiï¿½ka","Kategorie: Doly","Level: 0","Speciï¿½lnï¿½ funkce:","    +Mï¿½ï¿½e mï¿½t zamï¿½stnance"};
                JOptionPane.showMessageDialog(rootPane, text, "Uhelnï¿½ dï¿½l", tip, GUI.obrazekuhelnehodolu);
            }
        }
        else if (e.getButton() == 3) {
            GUI.listveci.setVisible(false);
            GUI.buypanel.setVisible(false);
            GUI.ukazatelpenez.setVisible(false);
            GUI.okno.remove(GUI.listveci);
            GUI.okno.remove(GUI.buypanel);
            GUI.okno.remove(GUI.ukazatelpenez);
            GUI.okno.add(GUI.mrizka);
            GUI.mrizka.setVisible(true);
            vobchode = false;
            vinventari = false;
            pokladani = true;
            if (e.getComponent().equals(GUI.invCesta)) {
                poklada = "Cesta";
            }
            else if (e.getComponent().equals(GUI.invDum)) {
                poklada = "Dum";
            }
            else if (e.getComponent().equals(GUI.invUhelnydul)) {
                poklada = "Uhelny dul";
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char klavesa = e.getKeyChar();
        if (klavesa == zadani1[zadani]) {
            mince = mince + 5;
            GUI.ukazatelpenez.setText(Long.toString(mince));
            Rendering.zadano = false;
            GUI.odpocet.setValue(0);
            Rendering.progress = 0;
        }
        else {
            
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}

class Background extends TimerTask {
    int i = 5;
    @Override
    public void run() {
        System.out.println("mince +"+100*TownBuild.polozeneuhelnedoly);
        TownBuild.mince = TownBuild.mince + 100 * TownBuild.polozeneuhelnedoly;
        TownBuild.mince_str = Long.toString(TownBuild.mince);
        TownBuild.GUI.ukazatelpenez.setText(TownBuild.mince_str);
    }
    
}
class Odpocet extends TimerTask {
    
    int i = 6;
    
    Timer odpocitavani = new Timer();
    TimerTask odpocitani = new Rendering();
    
    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            if (i > 0) {
                if (i == 6) {
                    System.out.println(Integer.toString(i));
                    TownBuild.GUI.napis.setText(Integer.toString(i));
                    TownBuild.GUI.napis.setText("Hra zaï¿½ne za:");
                    i--;
                }
                else {
                    System.out.println(Integer.toString(i));
                    TownBuild.GUI.napis.setText(Integer.toString(i));
                    i--;
                }
            }
            else if (i == 0) {
                TownBuild.GUI.napis.setText("start!");
                TownBuild.GUI.okno.requestFocusInWindow();
                odpocitavani.schedule(odpocitani, 1000, 1);
                i--;
            }
        });
    }
}
class Rendering extends TimerTask {
    public static int progress = 1;
    Random r = new Random();
    public static boolean zadano = false;
    int[] nextlevel = {25, 35, 46, 72, 82, 92, 105, 115, 125};
    int pokrok = 0;
    int speed = 1;
    int level = 1;
    int cekani;
    
    @Override
    public void run() {
        if (cekani <= 0) {
            TownBuild.GUI.odpocet.setValue(progress);
            progress = progress + speed;
            
            if (zadano == false) {
                pokrok++;
                if (pokrok == 30) {
                    if (speed == 3) {
                        pokrok = 0;
                        speed = 1;
                        level++;
                        cekani = 2000;
                        zadano = false;
                        TownBuild.GUI.napis.setText("level " + Integer.toString(level));
                    }
                    else {
                        pokrok = 0;
                        speed++;
                        cekani = 2000;
                        zadano = false;
                        TownBuild.GUI.napis.setText("rychlost " + Integer.toString(speed));
                    }
                }
                else {
                    TownBuild.zadani = r.nextInt(nextlevel[level - 1]);
                    System.out.println(TownBuild.zadani1[25]);
                    TownBuild.GUI.napis.setText(Character.toString(TownBuild.zadani1[TownBuild.zadani]));
                    zadano = true;
                }
            }
            else if (progress == 5100) {
                try {
                    int i = r.nextInt(9);
                    if (i == 0) {
                        TownBuild.GUI.napis.setText("Game ower");
                    }
                    else{
                        TownBuild.GUI.napis.setText("Game over");
                    }
                    Thread.sleep(2000);
                    TownBuild.exit();
                } catch (IOException ex) {
                    Logger.getLogger(Odpocet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Rendering.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else {
            cekani--;
        }
    }
}