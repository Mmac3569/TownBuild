import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author matej
 */
public class TownBuild extends JFrame implements ActionListener, WindowListener, MouseListener, KeyListener {
    static JFrame okno = new JFrame("Town Build");
    
    static JPanel buypanel = new JPanel();
    static JPanel moznosti = new JPanel();
    static JPanel listveci = new JPanel();
    static JPanel mrizka = new JPanel();
    static JLabel hernipole = new JLabel();
    
    final static ImageIcon obrazeknacitacihopozadi = new ImageIcon("mesto.png");
    final static ImageIcon obrazekcesty = new ImageIcon("cesta.png");
    final static ImageIcon obrazekdomu = new ImageIcon("dum.png");
    final static ImageIcon obrazekuhelnehodolu = new ImageIcon("uhelnydul.png");
    final static ImageIcon obrazekpozemku = new ImageIcon("trava.png");
    final static ImageIcon pozemekCesta = new ImageIcon("cesta-pozemek.png");
    final static ImageIcon pozemekDum = new ImageIcon("dum-pozemek.png");
    final static ImageIcon pozemekUhelnydul = new ImageIcon("uhelnydul-pozemek.png");
    static ImageIcon vyslednaIkona;
    
    static JButton hrat = new JButton("Hrát");
    static JButton koupitcestu = new JButton("koupit");
    static JButton koupitdum = new JButton("koupit");
    static JButton koupituhelnydul = new JButton("koupit");
    static JButton obchod = new JButton("Obchod");
    static JButton mesto = new JButton("Mìsto");
    static JButton inventar = new JButton("Inventáø");
    static JButton nastaveni = new JButton("Nastavení");
    static JButton ziskatpenize = new JButton("Získat peníze");
    static JButton jaksepise = new JButton("Jak se píše");
    static JButton place;
    
    static JButton pozemekx0y0 = new JButton(obrazekpozemku);
    static JButton pozemekx1y0 = new JButton(obrazekpozemku);
    static JButton pozemekx2y0 = new JButton(obrazekpozemku);
    static JButton pozemekx3y0 = new JButton(obrazekpozemku);
    static JButton pozemekx4y0 = new JButton(obrazekpozemku);
    static JButton pozemekx5y0 = new JButton(obrazekpozemku);
    static JButton pozemekx6y0 = new JButton(obrazekpozemku);
    static JButton pozemekx7y0 = new JButton(obrazekpozemku);
    static JButton pozemekx8y0 = new JButton(obrazekpozemku);
    static JButton pozemekx9y0 = new JButton(obrazekpozemku);
    static JButton pozemekx0y1 = new JButton(obrazekpozemku);
    static JButton pozemekx1y1 = new JButton(obrazekpozemku);
    static JButton pozemekx2y1 = new JButton(obrazekpozemku);
    static JButton pozemekx3y1 = new JButton(obrazekpozemku);
    static JButton pozemekx4y1 = new JButton(obrazekpozemku);
    static JButton pozemekx5y1 = new JButton(obrazekpozemku);
    static JButton pozemekx6y1 = new JButton(obrazekpozemku);
    static JButton pozemekx7y1 = new JButton(obrazekpozemku);
    static JButton pozemekx8y1 = new JButton(obrazekpozemku);
    static JButton pozemekx9y1 = new JButton(obrazekpozemku);
    static JButton pozemekx0y2 = new JButton(obrazekpozemku);
    static JButton pozemekx1y2 = new JButton(obrazekpozemku);
    static JButton pozemekx2y2 = new JButton(obrazekpozemku);
    static JButton pozemekx3y2 = new JButton(obrazekpozemku);
    static JButton pozemekx4y2 = new JButton(obrazekpozemku);
    static JButton pozemekx5y2 = new JButton(obrazekpozemku);
    static JButton pozemekx6y2 = new JButton(obrazekpozemku);
    static JButton pozemekx7y2 = new JButton(obrazekpozemku);
    static JButton pozemekx8y2 = new JButton(obrazekpozemku);
    static JButton pozemekx9y2 = new JButton(obrazekpozemku);
    static JButton pozemekx0y3 = new JButton(obrazekpozemku);
    static JButton pozemekx1y3 = new JButton(obrazekpozemku);
    static JButton pozemekx2y3 = new JButton(obrazekpozemku);
    static JButton pozemekx3y3 = new JButton(obrazekpozemku);
    static JButton pozemekx4y3 = new JButton(obrazekpozemku);
    static JButton pozemekx5y3 = new JButton(obrazekpozemku);
    static JButton pozemekx6y3 = new JButton(obrazekpozemku);
    static JButton pozemekx7y3 = new JButton(obrazekpozemku);
    static JButton pozemekx8y3 = new JButton(obrazekpozemku);
    static JButton pozemekx9y3 = new JButton(obrazekpozemku);
    static JButton pozemekx0y4 = new JButton(obrazekpozemku);
    static JButton pozemekx1y4 = new JButton(obrazekpozemku);
    static JButton pozemekx2y4 = new JButton(obrazekpozemku);
    static JButton pozemekx3y4 = new JButton(obrazekpozemku);
    static JButton pozemekx4y4 = new JButton(obrazekpozemku);
    static JButton pozemekx5y4 = new JButton(obrazekpozemku);
    static JButton pozemekx6y4 = new JButton(obrazekpozemku);
    static JButton pozemekx7y4 = new JButton(obrazekpozemku);
    static JButton pozemekx8y4 = new JButton(obrazekpozemku);
    static JButton pozemekx9y4 = new JButton(obrazekpozemku);
    static JButton pozemekx0y5 = new JButton(obrazekpozemku);
    static JButton pozemekx1y5 = new JButton(obrazekpozemku);
    static JButton pozemekx2y5 = new JButton(obrazekpozemku);
    static JButton pozemekx3y5 = new JButton(obrazekpozemku);
    static JButton pozemekx4y5 = new JButton(obrazekpozemku);
    static JButton pozemekx5y5 = new JButton(obrazekpozemku);
    static JButton pozemekx6y5 = new JButton(obrazekpozemku);
    static JButton pozemekx7y5 = new JButton(obrazekpozemku);
    static JButton pozemekx8y5 = new JButton(obrazekpozemku);
    static JButton pozemekx9y5 = new JButton(obrazekpozemku);
    static JButton pozemekx0y6 = new JButton(obrazekpozemku);
    static JButton pozemekx1y6 = new JButton(obrazekpozemku);
    static JButton pozemekx2y6 = new JButton(obrazekpozemku);
    static JButton pozemekx3y6 = new JButton(obrazekpozemku);
    static JButton pozemekx4y6 = new JButton(obrazekpozemku);
    static JButton pozemekx5y6 = new JButton(obrazekpozemku);
    static JButton pozemekx6y6 = new JButton(obrazekpozemku);
    static JButton pozemekx7y6 = new JButton(obrazekpozemku);
    static JButton pozemekx8y6 = new JButton(obrazekpozemku);
    static JButton pozemekx9y6 = new JButton(obrazekpozemku);
    static JButton pozemekx0y7 = new JButton(obrazekpozemku);
    static JButton pozemekx1y7 = new JButton(obrazekpozemku);
    static JButton pozemekx2y7 = new JButton(obrazekpozemku);
    static JButton pozemekx3y7 = new JButton(obrazekpozemku);
    static JButton pozemekx4y7 = new JButton(obrazekpozemku);
    static JButton pozemekx5y7 = new JButton(obrazekpozemku);
    static JButton pozemekx6y7 = new JButton(obrazekpozemku);
    static JButton pozemekx7y7 = new JButton(obrazekpozemku);
    static JButton pozemekx8y7 = new JButton(obrazekpozemku);
    static JButton pozemekx9y7 = new JButton(obrazekpozemku);
    static JButton pozemekx0y8 = new JButton(obrazekpozemku);
    static JButton pozemekx1y8 = new JButton(obrazekpozemku);
    static JButton pozemekx2y8 = new JButton(obrazekpozemku);
    static JButton pozemekx3y8 = new JButton(obrazekpozemku);
    static JButton pozemekx4y8 = new JButton(obrazekpozemku);
    static JButton pozemekx5y8 = new JButton(obrazekpozemku);
    static JButton pozemekx6y8 = new JButton(obrazekpozemku);
    static JButton pozemekx7y8 = new JButton(obrazekpozemku);
    static JButton pozemekx8y8 = new JButton(obrazekpozemku);
    static JButton pozemekx9y8 = new JButton(obrazekpozemku);
    static JButton pozemekx0y9 = new JButton(obrazekpozemku);
    static JButton pozemekx1y9 = new JButton(obrazekpozemku);
    static JButton pozemekx2y9 = new JButton(obrazekpozemku);
    static JButton pozemekx3y9 = new JButton(obrazekpozemku);
    static JButton pozemekx4y9 = new JButton(obrazekpozemku);
    static JButton pozemekx5y9 = new JButton(obrazekpozemku);
    static JButton pozemekx6y9 = new JButton(obrazekpozemku);
    static JButton pozemekx7y9 = new JButton(obrazekpozemku);
    static JButton pozemekx8y9 = new JButton(obrazekpozemku);
    static JButton pozemekx9y9 = new JButton(obrazekpozemku);
    static JButton pozemekx0y10 = new JButton(obrazekpozemku);
    static JButton pozemekx1y10 = new JButton(obrazekpozemku);
    static JButton pozemekx2y10 = new JButton(obrazekpozemku);
    static JButton pozemekx3y10 = new JButton(obrazekpozemku);
    static JButton pozemekx4y10 = new JButton(obrazekpozemku);
    static JButton pozemekx5y10 = new JButton(obrazekpozemku);
    static JButton pozemekx6y10 = new JButton(obrazekpozemku);
    static JButton pozemekx7y10 = new JButton(obrazekpozemku);
    static JButton pozemekx8y10 = new JButton(obrazekpozemku);
    static JButton pozemekx9y10 = new JButton(obrazekpozemku);
    static JButton pozemekx0y11 = new JButton(obrazekpozemku);
    static JButton pozemekx1y11 = new JButton(obrazekpozemku);
    static JButton pozemekx2y11 = new JButton(obrazekpozemku);
    static JButton pozemekx3y11 = new JButton(obrazekpozemku);
    static JButton pozemekx4y11 = new JButton(obrazekpozemku);
    static JButton pozemekx5y11 = new JButton(obrazekpozemku);
    static JButton pozemekx6y11 = new JButton(obrazekpozemku);
    static JButton pozemekx7y11 = new JButton(obrazekpozemku);
    static JButton pozemekx8y11 = new JButton(obrazekpozemku);
    static JButton pozemekx9y11 = new JButton(obrazekpozemku);
    static JButton pozemekx0y12 = new JButton(obrazekpozemku);
    static JButton pozemekx1y12 = new JButton(obrazekpozemku);
    static JButton pozemekx2y12 = new JButton(obrazekpozemku);
    static JButton pozemekx3y12 = new JButton(obrazekpozemku);
    static JButton pozemekx4y12 = new JButton(obrazekpozemku);
    static JButton pozemekx5y12 = new JButton(obrazekpozemku);
    static JButton pozemekx6y12 = new JButton(obrazekpozemku);
    static JButton pozemekx7y12 = new JButton(obrazekpozemku);
    static JButton pozemekx8y12 = new JButton(obrazekpozemku);
    static JButton pozemekx9y12 = new JButton(obrazekpozemku);
    static JButton pozemekx0y13 = new JButton(obrazekpozemku);
    static JButton pozemekx1y13 = new JButton(obrazekpozemku);
    static JButton pozemekx2y13 = new JButton(obrazekpozemku);
    static JButton pozemekx3y13 = new JButton(obrazekpozemku);
    static JButton pozemekx4y13 = new JButton(obrazekpozemku);
    static JButton pozemekx5y13 = new JButton(obrazekpozemku);
    static JButton pozemekx6y13 = new JButton(obrazekpozemku);
    static JButton pozemekx7y13 = new JButton(obrazekpozemku);
    static JButton pozemekx8y13 = new JButton(obrazekpozemku);
    static JButton pozemekx9y13 = new JButton(obrazekpozemku);
    
    static JLabel cesta = new JLabel(obrazekcesty);
    static JLabel dum = new JLabel(obrazekdomu);
    static JLabel uhelnydul = new JLabel(obrazekuhelnehodolu);
    static JLabel nacitacipozadi = new JLabel(obrazeknacitacihopozadi);
    static String[] infooapce = {"---------","Autoøi","---------","Vytvoøil: Matìj Máca","\n","------------------------------","Zkopírované obrázky","------------------------------","    Dùm: depositphotos.com","    Uhelný dùl: iconspng.com","    Pozemek: cz.wallpapers-fenix.eu","    Naèítací pozadí: starovekyegypt.net","    Ikona: ÈT edu(edu.ceskatelevize.cz)","    Ikona mince: depositphotos.com"};
    static JLabel verze = new JLabel("verze 1.2 beta");
    static JLabel napis = new JLabel("Hra zaène za:");
    static JLabel ukazatelpenez;
    static JLabel invDum;
    static JLabel invCesta;
    static JLabel invUhelnydul;
    
    static JProgressBar odpocet = new JProgressBar(0, 5000);
    
    static Path settings = Path.of("penize.txt");
    static FileWriter Pwriter;
    static FileWriter Wwriter;
    static Path world = Path.of("world.txt");
    
    static Timer vydelavani = new Timer();
    static TimerTask vydelani = new Background();
    static Timer odpocitavani = new Timer();
    static TimerTask odpocitani = new Odpocet();
    
    static long caszavreni;
    static long casted = System.currentTimeMillis() / 1000;
    
    static String obsahsouboru;
    static String mince_str;
    static String poklada;
    static String save;
    
    static long mince;
    static int pocetdomu;
    static int pocetcest;
    static int pocetuhelnychdolu;
    static int polozeneuhelnedoly;
    static int placeInt;
    static int zadani;
    static long offlinereward;
    
    static boolean vobchode = false;
    static boolean vinventari = false;
    static boolean vmeste = false;
    static boolean pokladani = false;
    static boolean starthry = false;
    
    static String[] pole = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    static String[] obsah;
    static String[] cas_str = {"0", "0", "0", "0", "0", "0"};
    static char[] zadani1 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '+', 'ì', 'š', 'è', 'ø', 'ž', 'ý', 'á', 'í', 'é', ';', '=', '´', 'ú', ')', 'ù', '§', '¨', ',', '.', '-', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7' ,'8', '9', '0', '°', '%', '¡', '/', '(', '"', '!', '?', ':', '_', '\'', '|', '€', 'ð', '[', ']', '³', '£', '#', '&', '@', '{', '}', '~', '¡', '^', '¢', '°', '²', '`', 'ÿ', '´', '½', '¨', '¸', '÷', '×', '$', 'ß', '¤', '<', '>', '*'};
    
    /**
     * Zjistí informace o stavu hry
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
     * Zapíše do souborù informace o stavu hry napøíklad kolik má hráè penìz a staveb èi postavené mìsto
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
     * Nastaví okno
     * @param args
     */
    public static void main(String[] args) {
        okno.setTitle("Town build");
        okno.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        okno.addWindowListener(new TownBuild());
        okno.setVisible(true);
        okno.setLocation(500, 100);
        okno.setIconImage(new ImageIcon("ikona_new.png").getImage());
        NacitaciObrazovka();
    }
    
    /**
     * Vykreslí naèítací obrazovku
     */
    public static void NacitaciObrazovka() {
        SwingUtilities.invokeLater(() -> {
            okno.setSize(700, 500);
            hrat.setVisible(true);
            okno.add(hrat);
            hrat.addActionListener(new TownBuild());
            hrat.setFocusable(false);
            hrat.setBorder(BorderFactory.createEtchedBorder());
            okno.add(nacitacipozadi);
            nacitacipozadi.setSize(700, 500);
            hrat.setLocation(350, 250);
            hrat.setEnabled(true);
            nacitacipozadi.add(hrat);
            nacitacipozadi.add(verze);
            hrat.setBackground(Color.YELLOW);
            hrat.setSize(100, 50);
            hrat.setFont(new Font("Consolas", Font.BOLD, 30));
            verze.setBounds(560, 430, 200, 20);
            verze.setFont(new Font("Consolas", Font.BOLD, 20));
            verze.setVisible(true);
        });
    }
    
    /**
     * Pøidìlí parametry všem používaným objektùm aneb naète hru
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
            
            okno.add(moznosti, BorderLayout.PAGE_END);
            okno.getContentPane().setBackground(Color.GREEN);
            moznosti.setBackground(Color.green);
            moznosti.add(obchod);
            moznosti.add(mesto);
            moznosti.add(inventar);
            moznosti.add(nastaveni);
            moznosti.add(ziskatpenize);
        
            mesto.setVisible(true);
            mesto.setFont(new Font("Consolas", Font.BOLD, 20));
            mesto.setEnabled(true);
            mesto.setBackground(Color.yellow);
            mesto.setFocusable(true);
            mesto.setBorder(BorderFactory.createEmptyBorder());
            mesto.addActionListener(new TownBuild());
            
            obchod.setVisible(true);
            obchod.setFont(new Font("Consolas", Font.BOLD, 20));
            obchod.setEnabled(true);
            obchod.setBackground(Color.yellow);
            obchod.setFocusable(true);
            obchod.setBorder(BorderFactory.createEmptyBorder());
            obchod.addActionListener(new TownBuild());
            
            inventar.setVisible(true);
            inventar.setFont(new Font("Consolas", Font.BOLD, 20));
            inventar.setEnabled(true);
            inventar.setBackground(Color.yellow);
            inventar.setFocusable(true);
            inventar.setBorder(BorderFactory.createEmptyBorder());
            inventar.addActionListener(new TownBuild());
            
            nastaveni.setVisible(true);
            nastaveni.setFont(new Font("Consolas", Font.BOLD, 20));
            nastaveni.setEnabled(true);
            nastaveni.setBackground(Color.yellow);
            nastaveni.setFocusable(true);
            nastaveni.setBorder(BorderFactory.createEmptyBorder());
            nastaveni.addActionListener(new TownBuild());
            
            ziskatpenize.setVisible(true);
            ziskatpenize.setFont(new Font("Consolas", Font.BOLD, 20));
            ziskatpenize.setEnabled(true);
            ziskatpenize.setBackground(Color.yellow);
            ziskatpenize.setFocusable(true);
            ziskatpenize.setBorder(BorderFactory.createEmptyBorder());
            ziskatpenize.addActionListener(new TownBuild());
            
            mince_str = Long.toString(mince);
            ukazatelpenez = new JLabel(mince_str);
            ukazatelpenez.setFont(new Font("Consolas", Font.BOLD, 30));
            ukazatelpenez.setBackground(null);
            ukazatelpenez.setBorder(BorderFactory.createEmptyBorder());
            ukazatelpenez.setVisible(true);
            ukazatelpenez.setIcon(new ImageIcon("mince.png"));
            
            koupitcestu.setBorder(BorderFactory.createEtchedBorder());
            koupitcestu.setFocusable(false);
            koupitcestu.setVisible(true);
            koupitcestu.setBackground(Color.yellow);
            koupitcestu.setEnabled(true);
            koupitcestu.setFont(new Font("Consolas", Font.BOLD, 20));
            koupitcestu.addActionListener(new TownBuild());
                
            koupitdum.setBorder(BorderFactory.createEtchedBorder());
            koupitdum.setFocusable(false);
            koupitdum.setVisible(true);
            koupitdum.setBackground(Color.yellow);
            koupitdum.setEnabled(true);
            koupitdum.setFont(new Font("Consolas", Font.BOLD, 20));
            koupitdum.addActionListener(new TownBuild());
                
            koupituhelnydul.setBorder(BorderFactory.createEtchedBorder());
            koupituhelnydul.setFocusable(false);
            koupituhelnydul.setVisible(true);
            koupituhelnydul.setBackground(Color.yellow);
            koupituhelnydul.setEnabled(true);
            koupituhelnydul.setFont(new Font("Consolas", Font.BOLD, 20));
            koupituhelnydul.addActionListener(new TownBuild());
                
            buypanel.setVisible(true);
            buypanel.add(uhelnydul, BorderLayout.NORTH);
            buypanel.add(cesta, BorderLayout.NORTH);
            buypanel.add(dum, BorderLayout.NORTH);
            buypanel.add(koupituhelnydul, BorderLayout.SOUTH);
            buypanel.add(koupitcestu, BorderLayout.SOUTH);
            buypanel.add(koupitdum, BorderLayout.SOUTH);
            buypanel.setBackground(Color.GREEN);
            buypanel.setLayout(new GridLayout(2, 3));
            
            okno.getContentPane().add(ukazatelpenez, BorderLayout.NORTH);
            okno.add(buypanel);
            
            invUhelnydul = new JLabel("Uhelnédoly:"+Integer.toString(pocetuhelnychdolu));
            invCesta = new JLabel("Cesty:"+Integer.toString(pocetcest));
            invDum = new JLabel("Domy:"+Integer.toString(+pocetdomu));
            invUhelnydul.addMouseListener(new TownBuild());
            invCesta.addMouseListener(new TownBuild());
            invDum.addMouseListener(new TownBuild());
            listveci.setVisible(false);
            listveci.add(invUhelnydul);
            listveci.add(invDum);
            listveci.add(invCesta);
            listveci.setBackground(Color.green);
            listveci.setLayout(new GridLayout(3, 1));
            
            jaksepise.setBorder(BorderFactory.createEtchedBorder());
            jaksepise.setFocusable(false);
            jaksepise.setBackground(Color.yellow);
            jaksepise.setEnabled(true);
            jaksepise.setFont(new Font("Consolas", Font.BOLD, 20));
            jaksepise.addActionListener(new TownBuild());
            jaksepise.setBounds(690,490,100,50);
            
            napis.setFont(new Font("Consolas", Font.BOLD, 50));
            napis.setBounds(0, 150, 700, 60);
            okno.setFocusable(true);
            okno.addKeyListener(new TownBuild());
            
            odpocet.setForeground(Color.YELLOW);
            odpocet.setBackground(Color.red);
            odpocet.setValue(0);
            odpocet.setStringPainted(false);
            odpocet.setBounds(50, 350 , 600, 50);
            odpocet.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
            
            hernipole.setBackground(Color.GREEN);
            hernipole.add(odpocet);
            hernipole.add(napis);
            
            casted = System.currentTimeMillis() /1000;
            offlinereward = ((casted - caszavreni) / 20 * 100 * polozeneuhelnedoly);
            mince = mince+offlinereward;
            System.out.println("Zatím co jsi zde nebil, tvoje stavby ti vynesly "+Long.toString(offlinereward)+" mincí");
            JOptionPane.showMessageDialog(nacitacipozadi, "Zatím co jsi zde nebil, tvoje stavby ti vynesly "+Long.toString(offlinereward)+" mincí", "Výtej zpìt!", JOptionPane.PLAIN_MESSAGE, new ImageIcon("mince.png"));
            vydelavani.schedule(vydelani, 1000, 20000);
            mrizkainit();
        });
    }
    
    /**
     * Zjistí místo kliknutí
     * @param o
     * @return pozemekx?y?(JButton)
     */
    public JButton getPlace(Object o) {
        if (o.equals(pozemekx0y0)) {
            placeInt = 0; return pozemekx0y0;
        }
        if (o.equals(pozemekx1y0)) {
            placeInt = 1; return pozemekx1y0;
        }
        if (o.equals(pozemekx2y0)) {
            placeInt = 2; return pozemekx2y0;
        }
        if (o.equals(pozemekx3y0)) {
            placeInt = 3; return pozemekx3y0;
        }
        if (o.equals(pozemekx4y0)) {
            placeInt = 4; return pozemekx4y0;
        }
        if (o.equals(pozemekx5y0)) {
            placeInt = 5; return pozemekx5y0;
        }
        if (o.equals(pozemekx6y0)) {
            placeInt = 6; return pozemekx6y0;
        }
        if (o.equals(pozemekx7y0)) {
            placeInt = 7; return pozemekx7y0;
        }
        if (o.equals(pozemekx8y0)) {
            placeInt = 8; return pozemekx8y0;
        }
        if (o.equals(pozemekx9y0)) {
            placeInt = 9; return pozemekx9y0;
        }
        if (o.equals(pozemekx0y1)) {
            placeInt = 10; return pozemekx0y1;
        }
        if (o.equals(pozemekx1y1)) {
            placeInt = 11; return pozemekx1y1;
        }
        if (o.equals(pozemekx2y1)) {
            placeInt = 12; return pozemekx2y1;
        }
        if (o.equals(pozemekx3y1)) {
            placeInt = 13; return pozemekx3y1;
        }
        if (o.equals(pozemekx4y1)) {
            placeInt = 14; return pozemekx4y1;
        }
        if (o.equals(pozemekx5y1)) {
            placeInt = 15; return pozemekx5y1;
        }
        if (o.equals(pozemekx6y1)) {
            placeInt = 16; return pozemekx6y1;
        }
        if (o.equals(pozemekx7y1)) {
            placeInt = 17; return pozemekx7y1;
        }
        if (o.equals(pozemekx8y1)) {
            placeInt = 18; return pozemekx8y1;
        }
        if (o.equals(pozemekx9y1)) {
            placeInt = 19; return pozemekx9y1;
        }
        if (o.equals(pozemekx0y2)) {
            placeInt = 20; return pozemekx0y2;
        }
        if (o.equals(pozemekx1y2)) {
            placeInt = 21; return pozemekx1y2;
        }
        if (o.equals(pozemekx2y2)) {
            placeInt = 22; return pozemekx2y2;
        }
        if (o.equals(pozemekx3y2)) {
            placeInt = 23; return pozemekx3y2;
        }
        if (o.equals(pozemekx4y2)) {
            placeInt = 24; return pozemekx4y2;
        }
        if (o.equals(pozemekx5y2)) {
            placeInt = 25; return pozemekx5y2;
        }
        if (o.equals(pozemekx6y2)) {
            placeInt = 26; return pozemekx6y2;
        }
        if (o.equals(pozemekx7y2)) {
            placeInt = 27; return pozemekx7y2;
        }
        if (o.equals(pozemekx8y2)) {
            placeInt = 28; return pozemekx8y2;
        }
        if (o.equals(pozemekx9y2)) {
            placeInt = 29; return pozemekx9y2;
        }
        if (o.equals(pozemekx0y3)) {
            placeInt = 30; return pozemekx0y3;
        }
        if (o.equals(pozemekx1y3)) {
            placeInt = 31; return pozemekx1y3;
        }
        if (o.equals(pozemekx2y3)) {
            placeInt = 32; return pozemekx2y3;
        }
        if (o.equals(pozemekx3y3)) {
            placeInt = 33; return pozemekx3y3;
        }
        if (o.equals(pozemekx4y3)) {
            placeInt = 34; return pozemekx4y3;
        }
        if (o.equals(pozemekx5y3)) {
            placeInt = 35; return pozemekx5y3;
        }
        if (o.equals(pozemekx6y3)) {
            placeInt = 36; return pozemekx6y3;
        }
        if (o.equals(pozemekx7y3)) {
            placeInt = 37; return pozemekx7y3;
        }
        if (o.equals(pozemekx8y3)) {
            placeInt = 38; return pozemekx8y3;
        }
        if (o.equals(pozemekx9y3)) {
            placeInt = 39; return pozemekx9y3;
        }
        if (o.equals(pozemekx0y4)) {
            placeInt = 40; return pozemekx0y4;
        }
        if (o.equals(pozemekx1y4)) {
            placeInt = 41; return pozemekx1y4;
        }
        if (o.equals(pozemekx2y4)) {
            placeInt = 42; return pozemekx2y4;
        }
        if (o.equals(pozemekx3y4)) {
            placeInt = 43; return pozemekx3y4;
        }
        if (o.equals(pozemekx4y4)) {
            placeInt = 44; return pozemekx4y4;
        }
        if (o.equals(pozemekx5y4)) {
            placeInt = 45; return pozemekx5y4;
        }
        if (o.equals(pozemekx6y4)) {
            placeInt = 46; return pozemekx6y4;
        }
        if (o.equals(pozemekx7y4)) {
            placeInt = 47; return pozemekx7y4;
        }
        if (o.equals(pozemekx8y4)) {
            placeInt = 48; return pozemekx8y4;
        }
        if (o.equals(pozemekx9y4)) {
            placeInt = 49; return pozemekx9y4;
        }
        if (o.equals(pozemekx0y5)) {
            placeInt = 50; return pozemekx0y5;
        }
        if (o.equals(pozemekx1y5)) {
            placeInt = 51; return pozemekx1y5;
        }
        if (o.equals(pozemekx2y5)) {
            placeInt = 52; return pozemekx2y5;
        }
        if (o.equals(pozemekx3y5)) {
            placeInt = 53; return pozemekx3y5;
        }
        if (o.equals(pozemekx4y5)) {
            placeInt = 54; return pozemekx4y5;
        }
        if (o.equals(pozemekx5y5)) {
            placeInt = 55; return pozemekx5y5;
        }
        if (o.equals(pozemekx6y5)) {
            placeInt = 56; return pozemekx6y5;
        }
        if (o.equals(pozemekx7y5)) {
            placeInt = 57; return pozemekx7y5;
        }
        if (o.equals(pozemekx8y5)) {
            placeInt = 58; return pozemekx8y5;
        }
        if (o.equals(pozemekx9y5)) {
            placeInt = 59; return pozemekx9y5;
        }
        if (o.equals(pozemekx0y6)) {
            placeInt = 60; return pozemekx0y6;
        }
        if (o.equals(pozemekx1y6)) {
            placeInt = 61; return pozemekx1y6;
        }
        if (o.equals(pozemekx2y6)) {
            placeInt = 62; return pozemekx2y6;
        }
        if (o.equals(pozemekx3y6)) {
            placeInt = 63; return pozemekx3y6;
        }
        if (o.equals(pozemekx4y6)) {
            placeInt = 64; return pozemekx4y6;
        }
        if (o.equals(pozemekx5y6)) {
            placeInt = 65; return pozemekx5y6;
        }
        if (o.equals(pozemekx6y6)) {
            placeInt = 66; return pozemekx6y6;
        }
        if (o.equals(pozemekx7y6)) {
            placeInt = 67; return pozemekx7y6;
        }
        if (o.equals(pozemekx8y6)) {
            placeInt = 68; return pozemekx8y6;
        }
        if (o.equals(pozemekx9y6)) {
            placeInt = 69; return pozemekx9y6;
        }
        if (o.equals(pozemekx0y7)) {
            placeInt = 70; return pozemekx0y7;
        }
        if (o.equals(pozemekx1y7)) {
            placeInt = 71; return pozemekx1y7;
        }
        if (o.equals(pozemekx2y7)) {
            placeInt = 72; return pozemekx2y7;
        }
        if (o.equals(pozemekx3y7)) {
            placeInt = 73; return pozemekx3y7;
        }
        if (o.equals(pozemekx4y7)) {
            placeInt = 74; return pozemekx4y7;
        }
        if (o.equals(pozemekx5y7)) {
            placeInt = 75; return pozemekx5y7;
        }
        if (o.equals(pozemekx6y7)) {
            placeInt = 76; return pozemekx6y7;
        }
        if (o.equals(pozemekx7y7)) {
            placeInt = 77; return pozemekx7y7;
        }
        if (o.equals(pozemekx8y7)) {
            placeInt = 78; return pozemekx8y7;
        }
        if (o.equals(pozemekx9y7)) {
            placeInt = 79; return pozemekx9y7;
        }
        if (o.equals(pozemekx0y8)) {
            placeInt = 80; return pozemekx0y8;
        }
        if (o.equals(pozemekx1y8)) {
            placeInt = 81; return pozemekx1y8;
        }
        if (o.equals(pozemekx2y8)) {
            placeInt = 82; return pozemekx2y8;
        }
        if (o.equals(pozemekx3y8)) {
            placeInt = 83; return pozemekx3y8;
        }
        if (o.equals(pozemekx4y8)) {
            placeInt = 84; return pozemekx4y8;
        }
        if (o.equals(pozemekx5y8)) {
            placeInt = 85; return pozemekx5y8;
        }
        if (o.equals(pozemekx6y8)) {
            placeInt = 86; return pozemekx6y8;
        }
        if (o.equals(pozemekx7y8)) {
            placeInt = 87; return pozemekx7y8;
        }
        if (o.equals(pozemekx8y8)) {
            placeInt = 88; return pozemekx8y8;
        }
        if (o.equals(pozemekx9y8)) {
            placeInt = 89; return pozemekx9y8;
        }
        if (o.equals(pozemekx0y9)) {
            placeInt = 90; return pozemekx0y9;
        }
        if (o.equals(pozemekx1y9)) {
            placeInt = 91; return pozemekx1y9;
        }
        if (o.equals(pozemekx2y9)) {
            placeInt = 92; return pozemekx2y9;
        }
        if (o.equals(pozemekx3y9)) {
            placeInt = 93; return pozemekx3y9;
        }
        if (o.equals(pozemekx4y9)) {
            placeInt = 94; return pozemekx4y9;
        }
        if (o.equals(pozemekx5y9)) {
            placeInt = 95; return pozemekx5y9;
        }
        if (o.equals(pozemekx6y9)) {
            placeInt = 96; return pozemekx6y9;
        }
        if (o.equals(pozemekx7y9)) {
            placeInt = 97; return pozemekx7y9;
        }
        if (o.equals(pozemekx8y9)) {
            placeInt = 98; return pozemekx8y9;
        }
        if (o.equals(pozemekx9y9)) {
            placeInt = 99; return pozemekx9y9;
        }
        if (o.equals(pozemekx0y10)) {
            placeInt = 100; return pozemekx0y10;
        }
        if (o.equals(pozemekx1y10)) {
            placeInt = 101; return pozemekx1y10;
        }
        if (o.equals(pozemekx2y10)) {
            placeInt = 102; return pozemekx2y10;
        }
        if (o.equals(pozemekx3y10)) {
            placeInt = 103; return pozemekx3y10;
        }
        if (o.equals(pozemekx4y10)) {
            placeInt = 104; return pozemekx4y10;
        }
        if (o.equals(pozemekx5y10)) {
            placeInt = 105; return pozemekx5y10;
        }
        if (o.equals(pozemekx6y10)) {
            placeInt = 106; return pozemekx6y10;
        }
        if (o.equals(pozemekx7y10)) {
            placeInt = 107; return pozemekx7y10;
        }
        if (o.equals(pozemekx8y10)) {
            placeInt = 108; return pozemekx8y10;
        }
        if (o.equals(pozemekx9y10)) {
            placeInt = 109; return pozemekx9y10;
        }
        if (o.equals(pozemekx0y11)) {
            placeInt = 110; return pozemekx0y11;
        }
        if (o.equals(pozemekx1y11)) {
            placeInt = 111; return pozemekx1y11;
        }
        if (o.equals(pozemekx2y11)) {
            placeInt = 112; return pozemekx2y11;
        }
        if (o.equals(pozemekx3y11)) {
            placeInt = 113; return pozemekx3y11;
        }
        if (o.equals(pozemekx4y11)) {
            placeInt = 114; return pozemekx4y11;
        }
        if (o.equals(pozemekx5y11)) {
            placeInt = 115; return pozemekx5y11;
        }
        if (o.equals(pozemekx6y11)) {
            placeInt = 116; return pozemekx6y11;
        }
        if (o.equals(pozemekx7y11)) {
            placeInt = 117; return pozemekx7y11;
        }
        if (o.equals(pozemekx8y11)) {
            placeInt = 118; return pozemekx8y11;
        }
        if (o.equals(pozemekx9y11)) {
            placeInt = 119; return pozemekx9y11;
        }
        if (o.equals(pozemekx0y12)) {
            placeInt = 120; return pozemekx0y12;
        }
        if (o.equals(pozemekx1y12)) {
            placeInt = 121; return pozemekx1y12;
        }
        if (o.equals(pozemekx2y12)) {
            placeInt = 122; return pozemekx2y12;
        }
        if (o.equals(pozemekx3y12)) {
            placeInt = 123; return pozemekx3y12;
        }
        if (o.equals(pozemekx4y12)) {
            placeInt = 124; return pozemekx4y12;
        }
        if (o.equals(pozemekx5y12)) {
            placeInt = 125; return pozemekx5y12;
        }
        if (o.equals(pozemekx6y12)) {
            placeInt = 126; return pozemekx6y12;
        }
        if (o.equals(pozemekx7y12)) {
            placeInt = 127; return pozemekx7y12;
        }
        if (o.equals(pozemekx8y12)) {
            placeInt = 128; return pozemekx8y12;
        }
        if (o.equals(pozemekx9y12)) {
            placeInt = 129; return pozemekx9y12;
        }
        if (o.equals(pozemekx0y13)) {
            placeInt = 130; return pozemekx0y13;
        }
        if (o.equals(pozemekx1y13)) {
            placeInt = 131; return pozemekx1y13;
        }
        if (o.equals(pozemekx2y13)) {
            placeInt = 132; return pozemekx2y13;
        }
        if (o.equals(pozemekx3y13)) {
            placeInt = 133; return pozemekx3y13;
        }
        if (o.equals(pozemekx4y13)) {
            placeInt = 134; return pozemekx4y13;
        }
        if (o.equals(pozemekx5y13)) {
            placeInt = 135; return pozemekx5y13;
        }
        if (o.equals(pozemekx6y13)) {
            placeInt = 136; return pozemekx6y13;
        }
        if (o.equals(pozemekx7y13)) {
            placeInt = 137; return pozemekx7y13;
        }
        if (o.equals(pozemekx8y13)) {
            placeInt = 138; return pozemekx8y13;
        }
        if (o.equals(pozemekx9y13)) {
            placeInt = 139; return pozemekx9y13;
        }
        return null;
    }
    
    /**
     * Zjistí ikonu
     * @param value
     * @return vyslednaikona(ImageIcon)
     */
    public static ImageIcon getIcon(int value) {
        if (pokladani == true) {
            if (null != poklada) switch (poklada) {
                case "Cesta" -> {
                    if (pocetcest-1 >= 0) {
                        pocetcest--;
                        vyslednaIkona = pozemekCesta;
                        save = "1";
                    }
                    else {
                        System.out.println("Došli ti cesty!\nPokud chceš položit další cestu, zajdi do obchodu.");
                        vyslednaIkona = obrazekpozemku;
                    }
                }
                case "Dum" -> {
                    if (pocetdomu-1 >= 0) {
                        pocetdomu--;
                        vyslednaIkona = pozemekDum;
                        save = "2";
                    }
                    else {
                        System.out.println("Došli ti cesty!\nPokud chceš položit další cestu, zajdi do obchodu.");
                        vyslednaIkona = obrazekpozemku;
                    }
                }
                case "Uhelny dul" -> {
                    if (pocetuhelnychdolu-1 >= 0) {
                        pocetuhelnychdolu--;
                        polozeneuhelnedoly++;
                        vyslednaIkona = pozemekUhelnydul;
                        save = "3";
                    }
                    else {
                        System.out.println("Došli ti cesty!\nPokud chceš položit další cestu, zajdi do obchodu.");
                        vyslednaIkona = obrazekpozemku;
                    }
                }
                default -> {
                }
            }
        }
        else if (vmeste == true) {
            if (null != pole[placeInt]) switch (pole[placeInt]) {
                case "1":
                    pocetcest++;
                    vyslednaIkona = obrazekpozemku;
                    save = "0";
                    break;
                case "2":
                    pocetdomu++;
                    vyslednaIkona = obrazekpozemku;
                    save = "0";
                    break;
                case "3":
                    pocetuhelnychdolu++;
                    polozeneuhelnedoly--;
                    vyslednaIkona = obrazekpozemku;
                    save = "0";
                    break;
                default:
                    break;
            }
            System.out.println("objekt odstranìn");
        }
        else {
            if (value == 1) {
                vyslednaIkona = pozemekCesta;
            }
            else if (value == 2) {
                vyslednaIkona = pozemekDum;
            }
            else if (value == 3) {
                vyslednaIkona = pozemekUhelnydul;
            }
            else {
                vyslednaIkona = obrazekpozemku;
            }
        }
        return vyslednaIkona;
    }
    
    /**
     * Zjistí ikonu na základì informací v souboru world.txt
     */
    public static void getDefaultIcon() {
        for (int i = 0; i < 140;) {
            if (i == 0) {
                pozemekx0y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 1) {
                pozemekx1y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 2) {
                pozemekx2y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 3) {
                pozemekx3y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 4) {
                pozemekx4y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 5) {
                pozemekx5y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 6) {
                pozemekx6y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 7) {
                pozemekx7y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 8) {
                pozemekx8y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 9) {
                pozemekx9y0.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 10) {
                pozemekx0y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 11) {
                pozemekx1y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 12) {
                pozemekx2y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 13) {
                pozemekx3y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 14) {
                pozemekx4y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 15) {
                pozemekx5y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 16) {
                pozemekx6y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 17) {
                pozemekx7y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 18) {
                pozemekx8y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 19) {
                pozemekx9y1.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 20) {
                pozemekx0y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 21) {
                pozemekx1y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 22) {
                pozemekx2y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 23) {
                pozemekx3y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 24) {
                pozemekx4y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 25) {
                pozemekx5y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 26) {
                pozemekx6y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 27) {
                pozemekx7y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 28) {
                pozemekx8y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 29) {
                pozemekx9y2.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 30) {
                pozemekx0y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 31) {
                pozemekx1y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 32) {
                pozemekx2y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 33) {
                pozemekx3y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 34) {
                pozemekx4y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 35) {
                pozemekx5y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 36) {
                pozemekx6y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 37) {
                pozemekx7y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 38) {
                pozemekx8y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 39) {
                pozemekx9y3.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 40) {
                pozemekx0y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 41) {
                pozemekx1y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 42) {
                pozemekx2y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 43) {
                pozemekx3y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 44) {
                pozemekx4y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 45) {
                pozemekx5y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 46) {
                pozemekx6y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 47) {
                pozemekx7y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 48) {
                pozemekx8y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 49) {
                pozemekx9y4.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 50) {
                pozemekx0y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 51) {
                pozemekx1y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 52) {
                pozemekx2y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 53) {
                pozemekx3y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 54) {
                pozemekx4y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 55) {
                pozemekx5y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 56) {
                pozemekx6y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 57) {
                pozemekx7y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 58) {
                pozemekx8y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 59) {
                pozemekx9y5.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 60) {
                pozemekx0y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 61) {
                pozemekx1y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 62) {
                pozemekx2y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 63) {
                pozemekx3y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 64) {
                pozemekx4y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 65) {
                pozemekx5y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 66) {
                pozemekx6y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 67) {
                pozemekx7y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 68) {
                pozemekx8y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 69) {
                pozemekx9y6.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 70) {
                pozemekx0y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 71) {
                pozemekx1y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 72) {
                pozemekx2y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 73) {
                pozemekx3y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 74) {
                pozemekx4y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 75) {
                pozemekx5y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 76) {
                pozemekx6y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 77) {
                pozemekx7y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 78) {
                pozemekx8y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 79) {
                pozemekx9y7.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 80) {
                pozemekx0y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 81) {
                pozemekx1y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 82) {
                pozemekx2y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 83) {
                pozemekx3y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 84) {
                pozemekx4y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 85) {
                pozemekx5y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 86) {
                pozemekx6y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 87) {
                pozemekx7y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 88) {
                pozemekx8y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 89) {
                pozemekx9y8.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 90) {
                pozemekx0y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 91) {
                pozemekx1y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 92) {
                pozemekx2y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 93) {
                pozemekx3y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 94) {
                pozemekx4y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 95) {
                pozemekx5y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 96) {
                pozemekx6y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 97) {
                pozemekx7y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 98) {
                pozemekx8y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 99) {
                pozemekx9y9.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 100) {
                pozemekx0y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 101) {
                pozemekx1y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 102) {
                pozemekx2y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 103) {
                pozemekx3y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 104) {
                pozemekx4y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 105) {
                pozemekx5y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 106) {
                pozemekx6y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 107) {
                pozemekx7y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 108) {
                pozemekx8y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 109) {
                pozemekx9y10.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 110) {
                pozemekx0y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 111) {
                pozemekx1y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 112) {
                pozemekx2y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 113) {
                pozemekx3y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 114) {
                pozemekx4y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 115) {
                pozemekx5y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 116) {
                pozemekx6y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 117) {
                pozemekx7y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 118) {
                pozemekx8y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 119) {
                pozemekx9y11.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 120) {
                pozemekx0y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 121) {
                pozemekx1y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 122) {
                pozemekx2y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 123) {
                pozemekx3y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 124) {
                pozemekx4y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 125) {
                pozemekx5y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 126) {
                pozemekx6y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 127) {
                pozemekx7y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 128) {
                pozemekx8y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 129) {
                pozemekx9y12.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 130) {
                pozemekx0y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 131) {
                pozemekx1y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 132) {
                pozemekx2y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 133) {
                pozemekx3y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 134) {
                pozemekx4y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 135) {
                pozemekx5y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 136) {
                pozemekx6y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 137) {
                pozemekx7y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 138) {
                pozemekx8y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            if (i == 139) {
                pozemekx9y13.setIcon(getIcon(Integer.parseInt(pole[i])));
            }
            i++;
        }
    }
    
    //definuje møížku
    public static void mrizkainit() {
        pozemekx0y0.addActionListener(new TownBuild());
        pozemekx1y0.addActionListener(new TownBuild());
        pozemekx2y0.addActionListener(new TownBuild());
        pozemekx3y0.addActionListener(new TownBuild());
        pozemekx4y0.addActionListener(new TownBuild());
        pozemekx5y0.addActionListener(new TownBuild());
        pozemekx6y0.addActionListener(new TownBuild());
        pozemekx7y0.addActionListener(new TownBuild());
        pozemekx8y0.addActionListener(new TownBuild());
        pozemekx9y0.addActionListener(new TownBuild());
        pozemekx0y1.addActionListener(new TownBuild());
        pozemekx1y1.addActionListener(new TownBuild());
        pozemekx2y1.addActionListener(new TownBuild());
        pozemekx3y1.addActionListener(new TownBuild());
        pozemekx4y1.addActionListener(new TownBuild());
        pozemekx5y1.addActionListener(new TownBuild());
        pozemekx6y1.addActionListener(new TownBuild());
        pozemekx7y1.addActionListener(new TownBuild());
        pozemekx8y1.addActionListener(new TownBuild());
        pozemekx9y1.addActionListener(new TownBuild());
        pozemekx0y2.addActionListener(new TownBuild());
        pozemekx1y2.addActionListener(new TownBuild());
        pozemekx2y2.addActionListener(new TownBuild());
        pozemekx3y2.addActionListener(new TownBuild());
        pozemekx4y2.addActionListener(new TownBuild());
        pozemekx5y2.addActionListener(new TownBuild());
        pozemekx6y2.addActionListener(new TownBuild());
        pozemekx7y2.addActionListener(new TownBuild());
        pozemekx8y2.addActionListener(new TownBuild());
        pozemekx9y2.addActionListener(new TownBuild());
        pozemekx0y3.addActionListener(new TownBuild());
        pozemekx1y3.addActionListener(new TownBuild());
        pozemekx2y3.addActionListener(new TownBuild());
        pozemekx3y3.addActionListener(new TownBuild());
        pozemekx4y3.addActionListener(new TownBuild());
        pozemekx5y3.addActionListener(new TownBuild());
        pozemekx6y3.addActionListener(new TownBuild());
        pozemekx7y3.addActionListener(new TownBuild());
        pozemekx8y3.addActionListener(new TownBuild());
        pozemekx9y3.addActionListener(new TownBuild());
        pozemekx0y4.addActionListener(new TownBuild());
        pozemekx1y4.addActionListener(new TownBuild());
        pozemekx2y4.addActionListener(new TownBuild());
        pozemekx3y4.addActionListener(new TownBuild());
        pozemekx4y4.addActionListener(new TownBuild());
        pozemekx5y4.addActionListener(new TownBuild());
        pozemekx6y4.addActionListener(new TownBuild());
        pozemekx7y4.addActionListener(new TownBuild());
        pozemekx8y4.addActionListener(new TownBuild());
        pozemekx9y4.addActionListener(new TownBuild());
        pozemekx0y5.addActionListener(new TownBuild());
        pozemekx1y5.addActionListener(new TownBuild());
        pozemekx2y5.addActionListener(new TownBuild());
        pozemekx3y5.addActionListener(new TownBuild());
        pozemekx4y5.addActionListener(new TownBuild());
        pozemekx5y5.addActionListener(new TownBuild());
        pozemekx6y5.addActionListener(new TownBuild());
        pozemekx7y5.addActionListener(new TownBuild());
        pozemekx8y5.addActionListener(new TownBuild());
        pozemekx9y5.addActionListener(new TownBuild());
        pozemekx0y6.addActionListener(new TownBuild());
        pozemekx1y6.addActionListener(new TownBuild());
        pozemekx2y6.addActionListener(new TownBuild());
        pozemekx3y6.addActionListener(new TownBuild());
        pozemekx4y6.addActionListener(new TownBuild());
        pozemekx5y6.addActionListener(new TownBuild());
        pozemekx6y6.addActionListener(new TownBuild());
        pozemekx7y6.addActionListener(new TownBuild());
        pozemekx8y6.addActionListener(new TownBuild());
        pozemekx9y6.addActionListener(new TownBuild());
        pozemekx0y7.addActionListener(new TownBuild());
        pozemekx1y7.addActionListener(new TownBuild());
        pozemekx2y7.addActionListener(new TownBuild());
        pozemekx3y7.addActionListener(new TownBuild());
        pozemekx4y7.addActionListener(new TownBuild());
        pozemekx5y7.addActionListener(new TownBuild());
        pozemekx6y7.addActionListener(new TownBuild());
        pozemekx7y7.addActionListener(new TownBuild());
        pozemekx8y7.addActionListener(new TownBuild());
        pozemekx9y7.addActionListener(new TownBuild());
        pozemekx0y8.addActionListener(new TownBuild());
        pozemekx1y8.addActionListener(new TownBuild());
        pozemekx2y8.addActionListener(new TownBuild());
        pozemekx3y8.addActionListener(new TownBuild());
        pozemekx4y8.addActionListener(new TownBuild());
        pozemekx5y8.addActionListener(new TownBuild());
        pozemekx6y8.addActionListener(new TownBuild());
        pozemekx7y8.addActionListener(new TownBuild());
        pozemekx8y8.addActionListener(new TownBuild());
        pozemekx9y8.addActionListener(new TownBuild());
        pozemekx0y9.addActionListener(new TownBuild());
        pozemekx1y9.addActionListener(new TownBuild());
        pozemekx2y9.addActionListener(new TownBuild());
        pozemekx3y9.addActionListener(new TownBuild());
        pozemekx4y9.addActionListener(new TownBuild());
        pozemekx5y9.addActionListener(new TownBuild());
        pozemekx6y9.addActionListener(new TownBuild());
        pozemekx7y9.addActionListener(new TownBuild());
        pozemekx8y9.addActionListener(new TownBuild());
        pozemekx9y9.addActionListener(new TownBuild());
        pozemekx0y10.addActionListener(new TownBuild());
        pozemekx1y10.addActionListener(new TownBuild());
        pozemekx2y10.addActionListener(new TownBuild());
        pozemekx3y10.addActionListener(new TownBuild());
        pozemekx4y10.addActionListener(new TownBuild());
        pozemekx5y10.addActionListener(new TownBuild());
        pozemekx6y10.addActionListener(new TownBuild());
        pozemekx7y10.addActionListener(new TownBuild());
        pozemekx8y10.addActionListener(new TownBuild());
        pozemekx9y10.addActionListener(new TownBuild());
        pozemekx0y11.addActionListener(new TownBuild());
        pozemekx1y11.addActionListener(new TownBuild());
        pozemekx2y11.addActionListener(new TownBuild());
        pozemekx3y11.addActionListener(new TownBuild());
        pozemekx4y11.addActionListener(new TownBuild());
        pozemekx5y11.addActionListener(new TownBuild());
        pozemekx6y11.addActionListener(new TownBuild());
        pozemekx7y11.addActionListener(new TownBuild());
        pozemekx8y11.addActionListener(new TownBuild());
        pozemekx9y11.addActionListener(new TownBuild());
        pozemekx0y12.addActionListener(new TownBuild());
        pozemekx1y12.addActionListener(new TownBuild());
        pozemekx2y12.addActionListener(new TownBuild());
        pozemekx3y12.addActionListener(new TownBuild());
        pozemekx4y12.addActionListener(new TownBuild());
        pozemekx5y12.addActionListener(new TownBuild());
        pozemekx6y12.addActionListener(new TownBuild());
        pozemekx7y12.addActionListener(new TownBuild());
        pozemekx8y12.addActionListener(new TownBuild());
        pozemekx9y12.addActionListener(new TownBuild());
        pozemekx0y13.addActionListener(new TownBuild());
        pozemekx1y13.addActionListener(new TownBuild());
        pozemekx2y13.addActionListener(new TownBuild());
        pozemekx3y13.addActionListener(new TownBuild());
        pozemekx4y13.addActionListener(new TownBuild());
        pozemekx5y13.addActionListener(new TownBuild());
        pozemekx6y13.addActionListener(new TownBuild());
        pozemekx7y13.addActionListener(new TownBuild());
        pozemekx8y13.addActionListener(new TownBuild());
        pozemekx9y13.addActionListener(new TownBuild());
        mrizka.add(pozemekx0y0);
        mrizka.add(pozemekx1y0);
        mrizka.add(pozemekx2y0);
        mrizka.add(pozemekx3y0);
        mrizka.add(pozemekx4y0);
        mrizka.add(pozemekx5y0);
        mrizka.add(pozemekx6y0);
        mrizka.add(pozemekx7y0);
        mrizka.add(pozemekx8y0);
        mrizka.add(pozemekx9y0);
        mrizka.add(pozemekx0y1);
        mrizka.add(pozemekx1y1);
        mrizka.add(pozemekx2y1);
        mrizka.add(pozemekx3y1);
        mrizka.add(pozemekx4y1);
        mrizka.add(pozemekx5y1);
        mrizka.add(pozemekx6y1);
        mrizka.add(pozemekx7y1);
        mrizka.add(pozemekx8y1);
        mrizka.add(pozemekx9y1);
        mrizka.add(pozemekx0y2);
        mrizka.add(pozemekx1y2);
        mrizka.add(pozemekx2y2);
        mrizka.add(pozemekx3y2);
        mrizka.add(pozemekx4y2);
        mrizka.add(pozemekx5y2);
        mrizka.add(pozemekx6y2);
        mrizka.add(pozemekx7y2);
        mrizka.add(pozemekx8y2);
        mrizka.add(pozemekx9y2);
        mrizka.add(pozemekx0y3);
        mrizka.add(pozemekx1y3);
        mrizka.add(pozemekx2y3);
        mrizka.add(pozemekx3y3);
        mrizka.add(pozemekx4y3);
        mrizka.add(pozemekx5y3);
        mrizka.add(pozemekx6y3);
        mrizka.add(pozemekx7y3);
        mrizka.add(pozemekx8y3);
        mrizka.add(pozemekx9y3);
        mrizka.add(pozemekx0y4);
        mrizka.add(pozemekx1y4);
        mrizka.add(pozemekx2y4);
        mrizka.add(pozemekx3y4);
        mrizka.add(pozemekx4y4);
        mrizka.add(pozemekx5y4);
        mrizka.add(pozemekx6y4);
        mrizka.add(pozemekx7y4);
        mrizka.add(pozemekx8y4);
        mrizka.add(pozemekx9y4);
        mrizka.add(pozemekx0y5);
        mrizka.add(pozemekx1y5);
        mrizka.add(pozemekx2y5);
        mrizka.add(pozemekx3y5);
        mrizka.add(pozemekx4y5);
        mrizka.add(pozemekx5y5);
        mrizka.add(pozemekx6y5);
        mrizka.add(pozemekx7y5);
        mrizka.add(pozemekx8y5);
        mrizka.add(pozemekx9y5);
        mrizka.add(pozemekx0y6);
        mrizka.add(pozemekx1y6);
        mrizka.add(pozemekx2y6);
        mrizka.add(pozemekx3y6);
        mrizka.add(pozemekx4y6);
        mrizka.add(pozemekx5y6);
        mrizka.add(pozemekx6y6);
        mrizka.add(pozemekx7y6);
        mrizka.add(pozemekx8y6);
        mrizka.add(pozemekx9y6);
        mrizka.add(pozemekx0y7);
        mrizka.add(pozemekx1y7);
        mrizka.add(pozemekx2y7);
        mrizka.add(pozemekx3y7);
        mrizka.add(pozemekx4y7);
        mrizka.add(pozemekx5y7);
        mrizka.add(pozemekx6y7);
        mrizka.add(pozemekx7y7);
        mrizka.add(pozemekx8y7);
        mrizka.add(pozemekx9y7);
        mrizka.add(pozemekx0y8);
        mrizka.add(pozemekx1y8);
        mrizka.add(pozemekx2y8);
        mrizka.add(pozemekx3y8);
        mrizka.add(pozemekx4y8);
        mrizka.add(pozemekx5y8);
        mrizka.add(pozemekx6y8);
        mrizka.add(pozemekx7y8);
        mrizka.add(pozemekx8y8);
        mrizka.add(pozemekx9y8);
        mrizka.add(pozemekx0y9);
        mrizka.add(pozemekx1y9);
        mrizka.add(pozemekx2y9);
        mrizka.add(pozemekx3y9);
        mrizka.add(pozemekx4y9);
        mrizka.add(pozemekx5y9);
        mrizka.add(pozemekx6y9);
        mrizka.add(pozemekx7y9);
        mrizka.add(pozemekx8y9);
        mrizka.add(pozemekx9y9);
        mrizka.add(pozemekx0y10);
        mrizka.add(pozemekx1y10);
        mrizka.add(pozemekx2y10);
        mrizka.add(pozemekx3y10);
        mrizka.add(pozemekx4y10);
        mrizka.add(pozemekx5y10);
        mrizka.add(pozemekx6y10);
        mrizka.add(pozemekx7y10);
        mrizka.add(pozemekx8y10);
        mrizka.add(pozemekx9y10);
        mrizka.add(pozemekx0y11);
        mrizka.add(pozemekx1y11);
        mrizka.add(pozemekx2y11);
        mrizka.add(pozemekx3y11);
        mrizka.add(pozemekx4y11);
        mrizka.add(pozemekx5y11);
        mrizka.add(pozemekx6y11);
        mrizka.add(pozemekx7y11);
        mrizka.add(pozemekx8y11);
        mrizka.add(pozemekx9y11);
        mrizka.add(pozemekx0y12);
        mrizka.add(pozemekx1y12);
        mrizka.add(pozemekx2y12);
        mrizka.add(pozemekx3y12);
        mrizka.add(pozemekx4y12);
        mrizka.add(pozemekx5y12);
        mrizka.add(pozemekx6y12);
        mrizka.add(pozemekx7y12);
        mrizka.add(pozemekx8y12);
        mrizka.add(pozemekx9y12);
        mrizka.add(pozemekx0y13);
        mrizka.add(pozemekx1y13);
        mrizka.add(pozemekx2y13);
        mrizka.add(pozemekx3y13);
        mrizka.add(pozemekx4y13);
        mrizka.add(pozemekx5y13);
        mrizka.add(pozemekx6y13);
        mrizka.add(pozemekx7y13);
        mrizka.add(pozemekx8y13);
        mrizka.add(pozemekx9y13);
        mrizka.setLayout(new GridLayout(10, 14));      
        mrizka.setBackground(Color.green);
        getDefaultIcon();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            if (e.getSource().equals(obchod)) {
                if (vobchode == false) {
                    vobchode = true;
                    vinventari = false;
                    vmeste = false;
                    pokladani = false;
                    listveci.setVisible(false);
                    ukazatelpenez.setVisible(true);
                    buypanel.setVisible(true);
                    mrizka.setVisible(false);
                    okno.remove(listveci);
                    okno.remove(mrizka);
                    okno.add(buypanel);
                    okno.getContentPane().add(ukazatelpenez, BorderLayout.NORTH);
                    buypanel.add(koupitdum);
                    buypanel.add(koupitdum);
                    buypanel.add(koupitdum);
                    buypanel.add(koupitdum);
                    buypanel.setLayout(new GridLayout(2, 5));
                }
                else {
                    System.out.println("Už jsi v obchodì!");
                    JOptionPane.showMessageDialog(rootPane, "Už jsi v obchodì!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource().equals(hrat)) {
                okno.remove(hrat);
                okno.remove(nacitacipozadi);
                okno.setVisible(false);
                okno.setVisible(true);
                vobchode = true;
                Nacteni();
            }
            else if (e.getSource().equals(inventar)) {
                if (vinventari == false) {
                    ukazatelpenez.setVisible(false);
                    buypanel.setVisible(false);
                    listveci.setVisible(true);
                    mrizka.setVisible(false);
                    okno.remove(buypanel);
                    okno.remove(ukazatelpenez);
                    okno.remove(mrizka);
                    okno.add(listveci);
                    invDum.setText("Domy: "+Integer.toString(pocetdomu));
                    invCesta.setText("Cesty: "+Integer.toString(pocetcest));
                    invUhelnydul.setText("Uhelné doly: "+Integer.toString(pocetuhelnychdolu));
                    vobchode = false;
                    vinventari = true;
                    vmeste = false;
                    pokladani = false;
                }
                else {
                    System.out.println("Už jsi v invetáøi!");
                    JOptionPane.showMessageDialog(rootPane, "Už jsi v inventáøi!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource().equals(koupitdum)) {
                if (mince-1000 >= 0) {
                    mince = mince-1000;
                    pocetdomu++;
                    System.out.println("Dùm zakoupen\nNiní máš "+pocetdomu+" domù\n");
                    mince_str = Long.toString(mince);
                    ukazatelpenez.setText(mince_str);
                }
                else {
                    System.out.println("Vypadá to, že ti došli peníze\n");
                    JOptionPane.showMessageDialog(rootPane, "Vypadá to, že ti došli peníze", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource().equals(koupitcestu)) {
                if (mince-100 >= 0) {
                    mince = mince-100;
                    pocetcest++;
                    System.out.println("Cesta zakoupena\nNiní máš "+pocetcest+" cest\n");
                    mince_str = Long.toString(mince);
                    ukazatelpenez.setText(mince_str);
                }
                else {
                    System.out.println("Vypadá to, že ti došli peníze\n");
                    JOptionPane.showMessageDialog(rootPane, "Vypadá to, že ti došli peníze", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource().equals(koupituhelnydul)) {
                if (mince-5000 >= 0) {
                    mince = mince-5000;
                    pocetuhelnychdolu++;
                    System.out.println("Uhelný dùl zakoupen\nNiní máš "+pocetuhelnychdolu+" uhelných dolù\n");
                    mince_str = Long.toString(mince);
                    ukazatelpenez.setText(mince_str);
                }
                else {
                    System.out.println("Vypadá to, že ti došli peníze\n");
                    JOptionPane.showMessageDialog(rootPane, "Vypadá to, že ti došli peníze", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource().equals(mesto)) {
                if (vmeste == false) {
                    buypanel.setVisible(false);
                    listveci.setVisible(false);
                    ukazatelpenez.setVisible(false);
                    mrizka.setVisible(true);
                    okno.remove(listveci);
                    okno.remove(buypanel);
                    okno.remove(ukazatelpenez);
                    okno.add(mrizka);
                    vobchode = false;
                    vinventari = false;
                    vmeste = true;
                    pokladani = false;
                }
                else {
                    System.out.println("Už jsi v mìstì!");
                    JOptionPane.showMessageDialog(rootPane, "Už jsi v mìstì!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getSource().equals(nastaveni)) {
                buypanel.setVisible(false);
                listveci.setVisible(false);
                ukazatelpenez.setVisible(false);
                mrizka.setVisible(false);
                okno.remove(listveci);
                okno.remove(buypanel);
                okno.remove(ukazatelpenez);
                okno.remove(mrizka);
                JOptionPane.showMessageDialog(rootPane, infooapce, "O aplikaci", JOptionPane.PLAIN_MESSAGE, null);
                vobchode = false;
                vinventari = false;
                vmeste = false;
                pokladani = false;
            }
            else if (e.getSource().equals(ziskatpenize)) {
                buypanel.setVisible(false);
                listveci.setVisible(false);
                ukazatelpenez.setVisible(false);
                mrizka.setVisible(false);
                jaksepise.setVisible(true);
                okno.remove(listveci);
                okno.remove(buypanel);
                okno.remove(ukazatelpenez);
                okno.remove(mrizka);
                okno.add(jaksepise);
                vobchode = false;
                vinventari = false;
                vmeste = false;
                pokladani = false;
            }
            else if (e.getSource().equals(jaksepise)) {
                okno.remove(jaksepise);
                okno.add(hernipole);
                napis.setVisible(true);
                jaksepise.setVisible(false);
                hernipole.setVisible(true);
                ukazatelpenez.setVisible(true);
                hernipole.add(ukazatelpenez);
                ukazatelpenez.setBounds(0, 0, 700, 50);
                starthry = true;
                odpocitavani.schedule(odpocitani, 1000, 1000);
            }
            else {
                if (pokladani == true) {
                    Object objekt = e.getSource();
                    place = getPlace(objekt);
                    place.setIcon(getIcon(0));
                    pole[placeInt] = save;
                }
                else if (pokladani == false) {
                    Object objekt = e.getSource();
                    place = getPlace(objekt);
                    place.setIcon(getIcon(0));
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
        if (e.getSource().equals(okno)){
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
            if (e.getComponent().equals(invCesta)) {
                System.out.println("Cena: 100\nVýnos: 0 mincí/min\nVlastnìno: "+pocetcest+"\nPotøebné místo: 1 kostièka\nKategorie: Cesty\nLevel: 0\nSpeciální funkce:\n    +Lidi mùžou chodit pøes");
                String[] text = {"Cena: 100","Výnos: 0 mincí/min","Vlastnìno: "+pocetcest,"Potøebné místo: 1 kostièka","Kategorie: Cesty","Level: 0","Speciální funkce:","    +Lidi mùžou chodit pøes"};
                JOptionPane.showMessageDialog(rootPane, text, "Cesta", tip, obrazekcesty);
            }
            else if (e.getComponent().equals(invDum)) {
                System.out.println("Cena: 1000\nVýnos: 0 mincí/min\nVlastnìno: "+pocetdomu+"\nPotøebné místo: 1 kostièka\nKategorie: Obydlí\nLevel: 0\nSpeciální funkce:\n    +Slouží k ubytování lidí");
                String[] text = {"Cena: 1000","Výnos: 0 mincí/min","Vlastnìno: "+pocetdomu,"Potøebné místo: 1 kostièka","Kategorie: Obydlí","Level: 0","Speciální funkce:","    +Slouží k ubytování lidí"};
                JOptionPane.showMessageDialog(rootPane, text, "Dùm", tip, obrazekdomu);
            }
            else if (e.getComponent().equals(invUhelnydul)) {
                System.out.println("Cena: 5000\nVýnos: 100 mincí/min\nVlastnìno: "+pocetuhelnychdolu+"\nPotøebné místo: 1 kostièka\nKategorie: Doly\nLevel: 0\nSpeciální funkce:\n    +Mùže mít zamìstnance");
                String[] text = {"Cena: 5000","Výnos: 100 mincí/min","Vlastnìno: "+pocetuhelnychdolu,"Potøebné místo: 1 kostièka","Kategorie: Doly","Level: 0","Speciální funkce:","    +Mùže mít zamìstnance"};
                JOptionPane.showMessageDialog(rootPane, text, "Uhelný dùl", tip, obrazekuhelnehodolu);
            }
        }
        else if (e.getButton() == 3) {
            listveci.setVisible(false);
            buypanel.setVisible(false);
            ukazatelpenez.setVisible(false);
            okno.remove(listveci);
            okno.remove(buypanel);
            okno.remove(ukazatelpenez);
            okno.add(mrizka);
            mrizka.setVisible(true);
            vobchode = false;
            vinventari = false;
            pokladani = true;
            if (e.getComponent().equals(invCesta)) {
                poklada = "Cesta";
            }
            else if (e.getComponent().equals(invDum)) {
                poklada = "Dum";
            }
            else if (e.getComponent().equals(invUhelnydul)) {
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
            ukazatelpenez.setText(Long.toString(mince));
            Rendering.zadano = false;
            odpocet.setValue(0);
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
        TownBuild.ukazatelpenez.setText(TownBuild.mince_str);
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
                    TownBuild.napis.setText(Integer.toString(i));
                    TownBuild.napis.setText("Hra zaène za:");
                    i--;
                }
                else {
                    System.out.println(Integer.toString(i));
                    TownBuild.napis.setText(Integer.toString(i));
                    i--;
                }
            }
            else if (i == 0) {
                TownBuild.napis.setText("start!");
                TownBuild.okno.requestFocusInWindow();
                odpocitavani.schedule(odpocitani, 1000, 1);
                i--;
            }
        });
    }
}
class Rendering extends TimerTask {
    static int progress = 1;
    Random r = new Random();
    static boolean zadano = false;
    int[] nextlevel = {25, 35, 46, 72, 82, 92, 105, 115, 125};
    int pokrok = 0;
    int speed = 1;
    int level = 1;
    int cekani;
    
    @Override
    public void run() {
        if (cekani <= 0) {
            TownBuild.odpocet.setValue(progress);
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
                        TownBuild.napis.setText("level " + Integer.toString(level));
                    }
                    else {
                        pokrok = 0;
                        speed++;
                        cekani = 2000;
                        zadano = false;
                        TownBuild.napis.setText("rychlost " + Integer.toString(speed));
                    }
                }
                else {
                    TownBuild.zadani = r.nextInt(nextlevel[level - 1]);
                    System.out.println(TownBuild.zadani1[25]);
                    TownBuild.napis.setText(Character.toString(TownBuild.zadani1[TownBuild.zadani]));
                    zadano = true;
                }
            }
            else if (progress == 5100) {
                try {
                    int i = r.nextInt(9);
                    if (i == 0) {
                        TownBuild.napis.setText("Game ower");
                    }
                    else{
                        TownBuild.napis.setText("Game over");
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