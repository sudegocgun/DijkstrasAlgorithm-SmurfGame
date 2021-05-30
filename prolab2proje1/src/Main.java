import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.MAX_VALUE;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.StringTokenizer;
import javax.swing.SwingUtilities;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

    static int sayac = 0;
    static String dusman1 = null;
    static String dusman2 = null;
    static int ilerle = 0;
    static List<Integer> path;

    public static void main(String[] args) throws IOException {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        File file = new File("harita.txt");

        FileReader fReader = new FileReader(file);
        String line;

        BufferedReader bReader = new BufferedReader(fReader);
        Gargamel gargamel = new Gargamel();
        Azman azman = new Azman();
        int oku = 0;
        line = bReader.readLine();
        while (oku != 2) {
            if (sayac == 0) {
                if (line.contains("Gargamel")) {
                    sayac++;
                    System.out.println("gargamel bulundu");

                    dusman1 = "Gargamel";
                    if (line.contains("Kapi:A")) {
                        gargamel.setID(1);
                        gargamel.setTur("dusman");
                        gargamel.setAd("Gargamel");
                        gargamel.setX(3);
                        gargamel.setY(0);
                    } else if (line.contains("Kapi:B")) {
                        gargamel.setID(1);
                        gargamel.setTur("dusman");
                        gargamel.setAd("Gargamel");
                        gargamel.setX(10);
                        gargamel.setY(0);
                    } else if (line.contains("Kapi:C")) {
                        //    Gargamel gargamel = new Gargamel(1,"Gargamel","dusman",0,5); 
                        gargamel.setID(1);
                        gargamel.setTur("dusman");
                        gargamel.setAd("Gargamel");
                        gargamel.setX(0);
                        gargamel.setY(5);
                    } else {
                        gargamel.setID(1);
                        gargamel.setTur("dusman");
                        gargamel.setAd("Gargamel");
                        gargamel.setX(3);
                        gargamel.setY(10);

                    }

                } else {
                    if (line.contains("Azman")) {
                        sayac++;
                        dusman1 = "Azman";
                        System.out.println("azman bulundu");
                        if (line.contains("Kapi:A")) {
                            azman.setID(2);
                            azman.setTur("dusman");
                            azman.setAd("Azman");
                            azman.setX(3);
                            azman.setY(0);

                        } else if (line.contains("Kapi:B")) {
                            azman.setID(2);
                            azman.setTur("dusman");
                            azman.setAd("Azman");
                            azman.setX(10);
                            azman.setY(0);
                        } else if (line.contains("Kapi:C")) {
                            azman.setID(2);
                            azman.setTur("dusman");
                            azman.setAd("Azman");
                            azman.setX(0);
                            azman.setY(5);
                        } else {
                            azman.setID(1);
                            azman.setTur("dusman");
                            azman.setAd("Azman");
                            azman.setX(3);
                            azman.setY(10);

                        }

                    }

                }
            } else {
                if (line.contains("Azman")) {
                    sayac++;
                    dusman2 = "Azman";
                    System.out.println("azman bulundu");
                    if (line.contains("Kapi:A")) {
                        azman.setID(2);
                        azman.setTur("dusman");
                        azman.setAd("Azman");
                        azman.setX(3);
                        azman.setY(0);

                    } else if (line.contains("Kapi:B")) {
                        azman.setID(2);
                        azman.setTur("dusman");
                        azman.setAd("Azman");
                        azman.setX(10);
                        azman.setY(0);
                    } else if (line.contains("Kapi:C")) {
                        azman.setID(2);
                        azman.setTur("dusman");
                        azman.setAd("Azman");
                        azman.setX(0);
                        azman.setY(5);
                    } else {
                        azman.setID(1);
                        azman.setTur("dusman");
                        azman.setAd("Azman");
                        azman.setX(3);
                        azman.setY(10);
                    }

                } else if (line.contains("Gargamel")) {
                    sayac++;
                    dusman2 = "Gargamel";
                    System.out.println("gargamel bulundu");
                    if (line.contains("Kapi:A")) {
                        gargamel.setID(2);
                        gargamel.setTur("dusman");
                        gargamel.setAd("Gargamel");
                        gargamel.setX(3);
                        gargamel.setY(0);

                    } else if (line.contains("Kapi:B")) {
                        gargamel.setID(2);
                        gargamel.setTur("dusman");
                        gargamel.setAd("Gargamel");
                        gargamel.setX(10);
                        gargamel.setY(0);
                    } else if (line.contains("Kapi:C")) {
                        gargamel.setID(2);
                        gargamel.setTur("dusman");
                        gargamel.setAd("Gargamel");
                        gargamel.setX(0);
                        gargamel.setY(5);
                    } else {
                        gargamel.setID(1);
                        gargamel.setTur("dusman");
                        gargamel.setAd("Gargamel");
                        gargamel.setX(3);
                        gargamel.setY(10);
                    }
                }
            }
            oku++;
            line = bReader.readLine();
        }
        bReader.close();

        int[][] maze2 = new int[11][13];
        bReader = new BufferedReader(new FileReader(file));
        int k = 1;
        int i = 0, j = 0;
        String satir;
        int a;
        satir = bReader.readLine();

        while (k != 3) {
            k++;
            satir = bReader.readLine();
        }

        while (satir != null) {
            StringTokenizer t = new StringTokenizer(satir);

            while (t.hasMoreTokens()) {

                for (j = 0; j < 13; j++) {
                    a = Integer.parseInt(t.nextToken());
                    maze2[i][j] = a;

                }

            }

            i++;

            satir = bReader.readLine();
        }

        int p, t;
        for (p = 0; p < 11; p++) {
            for (t = 0; t < 13; t++) {
                System.out.print(maze2[p][t]);

            }
            System.out.println(" ");
        }

        Dugum[] dugumliste = new Dugum[920];

        int q = 0;
        while (q < 920) {
            dugumliste[q] = null;
            for (int s = 0; s < 11; s++) {

                for (int c = 0; c < 13; c++) {

                    if (maze2[s][c] == 1) {
                        if (c / 10 >= 1) {
                            int deger = s * 100 + c;
                            String sdeger = String.valueOf(deger);
                            dugumliste[s * 100 + c] = new Dugum(sdeger, s, c);

                        } else {
                            int deger = s * 10 + c;
                            String sdeger = String.valueOf(deger);
                            dugumliste[s * 10 + c] = new Dugum(sdeger, s, c);
                        }

                    }
                    q++;
                }

            }
            q++;
        }
        JFrame jf = new JFrame();
        GirisEkrani girisekrani = new GirisEkrani();
        ImageIcon gozlukluresim = new ImageIcon("C:\\Users\\sude yağmur\\Desktop\\prolab2proje1\\src\\image\\gozlukluu-removebg-preview.png");
        ImageIcon tembelresim = new ImageIcon("C:\\Users\\sude yağmur\\Desktop\\prolab2proje1\\src\\image\\tembelll-removebg-preview.png");

        JButton gozluklusirin = new JButton();
        JButton tembelsirin = new JButton();
        gozluklusirin.setIcon(gozlukluresim);
        tembelsirin.setIcon(tembelresim);

        jf.add(tembelsirin);
        jf.add(gozluklusirin);

        jf.add(girisekrani);
        jf.setTitle("SİRİNLER OYUNU");
        jf.setLocation(270, 60);
        jf.setSize(1280, 960);

        tembelsirin.setBounds(900, 600, 200, 200);
        gozluklusirin.setBounds(200, 600, 200, 200);

        jf.setVisible(true);

        gozluklusirin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Oyuncu oyuncu = new GozlukluSirin(1, "Gozluklu Sirin", "Oyuncu", 5, 6, 20);
                ilerle = 2;
                if (sayac == 1) {
                    if (dusman1.contains("Gargamel")) {

                        Maze maze = new Maze(maze2, gargamel.getAd(), gargamel.getX(), gargamel.getY(), dugumliste, ilerle, "gozluklu", oyuncu, label);
                        maze.setVisible(true);
                        maze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        panel.add(label);
                        maze.add(panel);
                        label.setVisible(true);
                        label.setText("Kullanıcının skoru: " + oyuncu.getSkor());
                        Font f1 = new Font("TimesRoman", Font.BOLD, 20);
                        label.setFont(f1);
                        label.setForeground(Color.blue);

                    } else {
                        Maze maze = new Maze(maze2, azman.getAd(), azman.getX(), azman.getY(), dugumliste, ilerle, "gozluklu", oyuncu, label);
                        maze.setVisible(true);
                        maze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        panel.add(label);
                        maze.add(panel);
                        label.setVisible(true);
                        label.setText("Kullanıcının skoru: " + oyuncu.getSkor());
                        Font f1 = new Font("TimesRoman", Font.BOLD, 20);
                        label.setFont(f1);
                        label.setForeground(Color.blue);

                    }

                } else {
                    Maze maze = new Maze(maze2, gargamel.getAd(), azman.getAd(), gargamel.getX(), gargamel.getY(), azman.getX(), azman.getY(), dugumliste, ilerle, "gozluklu", oyuncu, label);
                    maze.setVisible(true);
                    maze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    panel.add(label);
                    maze.add(panel);
                    label.setVisible(true);
                    label.setText("Kullanıcının skoru: " + oyuncu.getSkor());
                    Font f1 = new Font("TimesRoman", Font.BOLD, 20);
                    label.setFont(f1);
                    label.setForeground(Color.blue);

                }

                jf.setVisible(false);

            }

        });

        tembelsirin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Oyuncu oyuncu = new TembelSirin(1, "Tembel Sirin", "Oyuncu", 5, 6, 20);
                ilerle = 1;
                if (sayac == 1) {
                    if (dusman1.contains("Gargamel")) {
                        Maze maze = new Maze(maze2, gargamel.getAd(), gargamel.getX(), gargamel.getY(), dugumliste, ilerle, "tembel", oyuncu, label);
                        maze.setVisible(true);
                        panel.add(label);
                        maze.add(panel);
                        label.setVisible(true);
                        label.setText("Kullanıcının skoru: " + oyuncu.getSkor());
                        Font f1 = new Font("TimesRoman", Font.BOLD, 20);
                        label.setFont(f1);
                        label.setForeground(Color.blue);

                    } else {
                        Maze maze = new Maze(maze2, azman.getAd(), azman.getX(), azman.getY(), dugumliste, ilerle, "tembel", oyuncu, label);
                        maze.setVisible(true);
                        panel.add(label);
                        maze.add(panel);
                        label.setVisible(true);
                        label.setText("Kullanıcının skoru: " + oyuncu.getSkor());
                        Font f1 = new Font("TimesRoman", Font.BOLD, 20);
                        label.setFont(f1);
                        label.setForeground(Color.blue);

                    }

                } else {
                    Maze maze = new Maze(maze2, gargamel.getAd(), azman.getAd(), gargamel.getX(), gargamel.getY(), azman.getX(), azman.getY(), dugumliste, ilerle, "tembel", oyuncu, label);
                    maze.setVisible(true);
                    maze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    panel.add(label);
                    maze.add(panel);
                    label.setVisible(true);
                    label.setText("Kullanıcının skoru: " + oyuncu.getSkor());
                    Font f1 = new Font("TimesRoman", Font.BOLD, 20);
                    label.setFont(f1);
                    label.setForeground(Color.blue);

                }

                jf.setVisible(false);

            }

        });

        dugumliste[10].KomsuDugumEkle(new KomsuDugum(10, dugumliste[10], dugumliste[110]));

        dugumliste[3].KomsuDugumEkle(new KomsuDugum(10, dugumliste[3], dugumliste[13]));

        dugumliste[11].KomsuDugumEkle(new KomsuDugum(10, dugumliste[11], dugumliste[12]));
        dugumliste[11].KomsuDugumEkle(new KomsuDugum(10, dugumliste[11], dugumliste[21]));

        dugumliste[12].KomsuDugumEkle(new KomsuDugum(10, dugumliste[12], dugumliste[11]));
        dugumliste[12].KomsuDugumEkle(new KomsuDugum(10, dugumliste[12], dugumliste[13]));

        dugumliste[13].KomsuDugumEkle(new KomsuDugum(10, dugumliste[13], dugumliste[12]));
        dugumliste[13].KomsuDugumEkle(new KomsuDugum(10, dugumliste[13], dugumliste[23]));
        dugumliste[13].KomsuDugumEkle(new KomsuDugum(10, dugumliste[13], dugumliste[14]));

        dugumliste[14].KomsuDugumEkle(new KomsuDugum(10, dugumliste[14], dugumliste[13]));
        dugumliste[14].KomsuDugumEkle(new KomsuDugum(10, dugumliste[14], dugumliste[24]));

        dugumliste[16].KomsuDugumEkle(new KomsuDugum(10, dugumliste[16], dugumliste[26]));
        dugumliste[16].KomsuDugumEkle(new KomsuDugum(10, dugumliste[16], dugumliste[17]));

        dugumliste[17].KomsuDugumEkle(new KomsuDugum(10, dugumliste[17], dugumliste[16]));
        dugumliste[17].KomsuDugumEkle(new KomsuDugum(10, dugumliste[17], dugumliste[27]));
        dugumliste[17].KomsuDugumEkle(new KomsuDugum(10, dugumliste[17], dugumliste[18]));

        dugumliste[18].KomsuDugumEkle(new KomsuDugum(10, dugumliste[18], dugumliste[17]));
        dugumliste[18].KomsuDugumEkle(new KomsuDugum(10, dugumliste[18], dugumliste[19]));
        dugumliste[18].KomsuDugumEkle(new KomsuDugum(10, dugumliste[18], dugumliste[28]));

        dugumliste[19].KomsuDugumEkle(new KomsuDugum(10, dugumliste[19], dugumliste[18]));
        dugumliste[19].KomsuDugumEkle(new KomsuDugum(10, dugumliste[19], dugumliste[110]));

        dugumliste[110].KomsuDugumEkle(new KomsuDugum(10, dugumliste[110], dugumliste[19]));
        dugumliste[110].KomsuDugumEkle(new KomsuDugum(10, dugumliste[110], dugumliste[111]));

        dugumliste[111].KomsuDugumEkle(new KomsuDugum(10, dugumliste[111], dugumliste[110]));
        dugumliste[111].KomsuDugumEkle(new KomsuDugum(10, dugumliste[111], dugumliste[211]));

        dugumliste[21].KomsuDugumEkle(new KomsuDugum(10, dugumliste[21], dugumliste[11]));
        dugumliste[21].KomsuDugumEkle(new KomsuDugum(10, dugumliste[21], dugumliste[31]));

        dugumliste[23].KomsuDugumEkle(new KomsuDugum(10, dugumliste[23], dugumliste[24]));
        dugumliste[23].KomsuDugumEkle(new KomsuDugum(10, dugumliste[23], dugumliste[33]));
        dugumliste[23].KomsuDugumEkle(new KomsuDugum(10, dugumliste[23], dugumliste[13]));

        dugumliste[24].KomsuDugumEkle(new KomsuDugum(10, dugumliste[24], dugumliste[14]));
        dugumliste[24].KomsuDugumEkle(new KomsuDugum(10, dugumliste[24], dugumliste[25]));
        dugumliste[24].KomsuDugumEkle(new KomsuDugum(10, dugumliste[24], dugumliste[23]));
        dugumliste[24].KomsuDugumEkle(new KomsuDugum(10, dugumliste[24], dugumliste[34]));

        dugumliste[25].KomsuDugumEkle(new KomsuDugum(10, dugumliste[25], dugumliste[24]));
        dugumliste[25].KomsuDugumEkle(new KomsuDugum(10, dugumliste[25], dugumliste[26]));

        dugumliste[26].KomsuDugumEkle(new KomsuDugum(10, dugumliste[26], dugumliste[25]));
        dugumliste[26].KomsuDugumEkle(new KomsuDugum(10, dugumliste[26], dugumliste[36]));
        dugumliste[26].KomsuDugumEkle(new KomsuDugum(10, dugumliste[26], dugumliste[16]));
        dugumliste[26].KomsuDugumEkle(new KomsuDugum(10, dugumliste[26], dugumliste[27]));

        dugumliste[27].KomsuDugumEkle(new KomsuDugum(10, dugumliste[27], dugumliste[17]));
        dugumliste[27].KomsuDugumEkle(new KomsuDugum(10, dugumliste[27], dugumliste[26]));
        dugumliste[27].KomsuDugumEkle(new KomsuDugum(10, dugumliste[27], dugumliste[28]));

        dugumliste[28].KomsuDugumEkle(new KomsuDugum(10, dugumliste[28], dugumliste[18]));
        dugumliste[28].KomsuDugumEkle(new KomsuDugum(10, dugumliste[28], dugumliste[27]));
        dugumliste[28].KomsuDugumEkle(new KomsuDugum(10, dugumliste[28], dugumliste[38]));

        dugumliste[211].KomsuDugumEkle(new KomsuDugum(10, dugumliste[211], dugumliste[111]));
        dugumliste[211].KomsuDugumEkle(new KomsuDugum(10, dugumliste[211], dugumliste[311]));

        dugumliste[31].KomsuDugumEkle(new KomsuDugum(10, dugumliste[31], dugumliste[21]));
        dugumliste[31].KomsuDugumEkle(new KomsuDugum(10, dugumliste[31], dugumliste[32]));
        dugumliste[31].KomsuDugumEkle(new KomsuDugum(10, dugumliste[31], dugumliste[41]));

        dugumliste[32].KomsuDugumEkle(new KomsuDugum(10, dugumliste[32], dugumliste[31]));
        dugumliste[32].KomsuDugumEkle(new KomsuDugum(10, dugumliste[32], dugumliste[33]));

        dugumliste[33].KomsuDugumEkle(new KomsuDugum(10, dugumliste[33], dugumliste[23]));
        dugumliste[33].KomsuDugumEkle(new KomsuDugum(10, dugumliste[33], dugumliste[32]));
        dugumliste[33].KomsuDugumEkle(new KomsuDugum(10, dugumliste[33], dugumliste[34]));
        dugumliste[33].KomsuDugumEkle(new KomsuDugum(10, dugumliste[33], dugumliste[43]));

        dugumliste[34].KomsuDugumEkle(new KomsuDugum(10, dugumliste[34], dugumliste[24]));
        dugumliste[34].KomsuDugumEkle(new KomsuDugum(10, dugumliste[34], dugumliste[33]));

        dugumliste[36].KomsuDugumEkle(new KomsuDugum(10, dugumliste[36], dugumliste[26]));
        dugumliste[36].KomsuDugumEkle(new KomsuDugum(10, dugumliste[36], dugumliste[46]));

        dugumliste[38].KomsuDugumEkle(new KomsuDugum(10, dugumliste[38], dugumliste[28]));
        dugumliste[38].KomsuDugumEkle(new KomsuDugum(10, dugumliste[38], dugumliste[39]));
        dugumliste[38].KomsuDugumEkle(new KomsuDugum(10, dugumliste[38], dugumliste[48]));

        dugumliste[39].KomsuDugumEkle(new KomsuDugum(10, dugumliste[39], dugumliste[38]));

        dugumliste[311].KomsuDugumEkle(new KomsuDugum(10, dugumliste[311], dugumliste[411]));
        dugumliste[311].KomsuDugumEkle(new KomsuDugum(10, dugumliste[311], dugumliste[211]));

        dugumliste[41].KomsuDugumEkle(new KomsuDugum(10, dugumliste[41], dugumliste[31]));
        dugumliste[41].KomsuDugumEkle(new KomsuDugum(10, dugumliste[41], dugumliste[51]));

        dugumliste[43].KomsuDugumEkle(new KomsuDugum(10, dugumliste[43], dugumliste[33]));
        dugumliste[43].KomsuDugumEkle(new KomsuDugum(10, dugumliste[43], dugumliste[53]));

        dugumliste[46].KomsuDugumEkle(new KomsuDugum(10, dugumliste[46], dugumliste[36]));
        dugumliste[46].KomsuDugumEkle(new KomsuDugum(10, dugumliste[46], dugumliste[56]));

        dugumliste[48].KomsuDugumEkle(new KomsuDugum(10, dugumliste[48], dugumliste[38]));
        dugumliste[48].KomsuDugumEkle(new KomsuDugum(10, dugumliste[48], dugumliste[58]));

        dugumliste[411].KomsuDugumEkle(new KomsuDugum(10, dugumliste[411], dugumliste[311]));
        dugumliste[411].KomsuDugumEkle(new KomsuDugum(10, dugumliste[411], dugumliste[511]));

        dugumliste[50].KomsuDugumEkle(new KomsuDugum(10, dugumliste[50], dugumliste[51]));

        dugumliste[51].KomsuDugumEkle(new KomsuDugum(10, dugumliste[51], dugumliste[41]));
        dugumliste[51].KomsuDugumEkle(new KomsuDugum(10, dugumliste[51], dugumliste[61]));
        dugumliste[51].KomsuDugumEkle(new KomsuDugum(10, dugumliste[51], dugumliste[50]));

        dugumliste[53].KomsuDugumEkle(new KomsuDugum(10, dugumliste[53], dugumliste[43]));
        dugumliste[53].KomsuDugumEkle(new KomsuDugum(10, dugumliste[53], dugumliste[54]));

        dugumliste[54].KomsuDugumEkle(new KomsuDugum(10, dugumliste[54], dugumliste[53]));
        dugumliste[54].KomsuDugumEkle(new KomsuDugum(10, dugumliste[54], dugumliste[64]));
        dugumliste[54].KomsuDugumEkle(new KomsuDugum(10, dugumliste[54], dugumliste[55]));

        dugumliste[55].KomsuDugumEkle(new KomsuDugum(10, dugumliste[55], dugumliste[54]));
        dugumliste[55].KomsuDugumEkle(new KomsuDugum(10, dugumliste[55], dugumliste[56]));
        dugumliste[55].KomsuDugumEkle(new KomsuDugum(10, dugumliste[55], dugumliste[65]));

        dugumliste[56].KomsuDugumEkle(new KomsuDugum(10, dugumliste[56], dugumliste[55]));
        dugumliste[56].KomsuDugumEkle(new KomsuDugum(10, dugumliste[56], dugumliste[46]));
        dugumliste[56].KomsuDugumEkle(new KomsuDugum(10, dugumliste[56], dugumliste[66]));

        dugumliste[58].KomsuDugumEkle(new KomsuDugum(10, dugumliste[58], dugumliste[48]));
        dugumliste[58].KomsuDugumEkle(new KomsuDugum(10, dugumliste[58], dugumliste[68]));

        dugumliste[510].KomsuDugumEkle(new KomsuDugum(10, dugumliste[510], dugumliste[511]));
        dugumliste[510].KomsuDugumEkle(new KomsuDugum(10, dugumliste[510], dugumliste[610]));

        dugumliste[511].KomsuDugumEkle(new KomsuDugum(10, dugumliste[511], dugumliste[411]));
        dugumliste[511].KomsuDugumEkle(new KomsuDugum(10, dugumliste[511], dugumliste[611]));
        dugumliste[511].KomsuDugumEkle(new KomsuDugum(10, dugumliste[511], dugumliste[510]));

        dugumliste[61].KomsuDugumEkle(new KomsuDugum(10, dugumliste[61], dugumliste[51]));
        dugumliste[61].KomsuDugumEkle(new KomsuDugum(10, dugumliste[61], dugumliste[71]));

        dugumliste[64].KomsuDugumEkle(new KomsuDugum(10, dugumliste[64], dugumliste[54]));
        dugumliste[64].KomsuDugumEkle(new KomsuDugum(10, dugumliste[64], dugumliste[65]));
        dugumliste[64].KomsuDugumEkle(new KomsuDugum(10, dugumliste[64], dugumliste[74]));

        dugumliste[65].KomsuDugumEkle(new KomsuDugum(10, dugumliste[65], dugumliste[64]));
        dugumliste[65].KomsuDugumEkle(new KomsuDugum(10, dugumliste[65], dugumliste[55]));
        dugumliste[65].KomsuDugumEkle(new KomsuDugum(10, dugumliste[65], dugumliste[66]));
        dugumliste[65].KomsuDugumEkle(new KomsuDugum(10, dugumliste[65], dugumliste[75]));

        dugumliste[66].KomsuDugumEkle(new KomsuDugum(10, dugumliste[66], dugumliste[65]));
        dugumliste[66].KomsuDugumEkle(new KomsuDugum(10, dugumliste[66], dugumliste[56]));
        dugumliste[66].KomsuDugumEkle(new KomsuDugum(10, dugumliste[66], dugumliste[76]));

        dugumliste[68].KomsuDugumEkle(new KomsuDugum(10, dugumliste[68], dugumliste[58]));
        dugumliste[68].KomsuDugumEkle(new KomsuDugum(10, dugumliste[68], dugumliste[69]));
        dugumliste[68].KomsuDugumEkle(new KomsuDugum(10, dugumliste[68], dugumliste[78]));

        dugumliste[69].KomsuDugumEkle(new KomsuDugum(10, dugumliste[69], dugumliste[68]));
        dugumliste[69].KomsuDugumEkle(new KomsuDugum(10, dugumliste[69], dugumliste[79]));
        dugumliste[69].KomsuDugumEkle(new KomsuDugum(10, dugumliste[69], dugumliste[610]));

        dugumliste[610].KomsuDugumEkle(new KomsuDugum(10, dugumliste[610], dugumliste[69]));
        dugumliste[610].KomsuDugumEkle(new KomsuDugum(10, dugumliste[610], dugumliste[510]));
        dugumliste[610].KomsuDugumEkle(new KomsuDugum(10, dugumliste[610], dugumliste[710]));

        dugumliste[611].KomsuDugumEkle(new KomsuDugum(10, dugumliste[611], dugumliste[511]));
        dugumliste[611].KomsuDugumEkle(new KomsuDugum(10, dugumliste[611], dugumliste[711]));
        dugumliste[611].KomsuDugumEkle(new KomsuDugum(10, dugumliste[611], dugumliste[610]));

        dugumliste[71].KomsuDugumEkle(new KomsuDugum(10, dugumliste[71], dugumliste[61]));
        dugumliste[71].KomsuDugumEkle(new KomsuDugum(10, dugumliste[71], dugumliste[81]));

        dugumliste[73].KomsuDugumEkle(new KomsuDugum(10, dugumliste[73], dugumliste[74]));
        dugumliste[73].KomsuDugumEkle(new KomsuDugum(10, dugumliste[73], dugumliste[83]));

        dugumliste[74].KomsuDugumEkle(new KomsuDugum(10, dugumliste[74], dugumliste[73]));
        dugumliste[74].KomsuDugumEkle(new KomsuDugum(10, dugumliste[74], dugumliste[64]));
        dugumliste[74].KomsuDugumEkle(new KomsuDugum(10, dugumliste[74], dugumliste[75]));

        dugumliste[75].KomsuDugumEkle(new KomsuDugum(10, dugumliste[75], dugumliste[74]));
        dugumliste[75].KomsuDugumEkle(new KomsuDugum(10, dugumliste[75], dugumliste[65]));
        dugumliste[75].KomsuDugumEkle(new KomsuDugum(10, dugumliste[75], dugumliste[76]));

        dugumliste[76].KomsuDugumEkle(new KomsuDugum(10, dugumliste[76], dugumliste[75]));
        dugumliste[76].KomsuDugumEkle(new KomsuDugum(10, dugumliste[76], dugumliste[66]));
        dugumliste[76].KomsuDugumEkle(new KomsuDugum(10, dugumliste[76], dugumliste[77]));

        dugumliste[77].KomsuDugumEkle(new KomsuDugum(10, dugumliste[77], dugumliste[76]));
        dugumliste[77].KomsuDugumEkle(new KomsuDugum(10, dugumliste[77], dugumliste[78]));

        dugumliste[78].KomsuDugumEkle(new KomsuDugum(10, dugumliste[78], dugumliste[77]));
        dugumliste[78].KomsuDugumEkle(new KomsuDugum(10, dugumliste[78], dugumliste[79]));
        dugumliste[78].KomsuDugumEkle(new KomsuDugum(10, dugumliste[78], dugumliste[68]));

        dugumliste[79].KomsuDugumEkle(new KomsuDugum(10, dugumliste[79], dugumliste[78]));
        dugumliste[79].KomsuDugumEkle(new KomsuDugum(10, dugumliste[79], dugumliste[710]));
        dugumliste[79].KomsuDugumEkle(new KomsuDugum(10, dugumliste[79], dugumliste[69]));
        dugumliste[79].KomsuDugumEkle(new KomsuDugum(10, dugumliste[79], dugumliste[89]));

        dugumliste[710].KomsuDugumEkle(new KomsuDugum(10, dugumliste[710], dugumliste[79]));
        dugumliste[710].KomsuDugumEkle(new KomsuDugum(10, dugumliste[710], dugumliste[610]));
        dugumliste[710].KomsuDugumEkle(new KomsuDugum(10, dugumliste[710], dugumliste[711]));
        dugumliste[710].KomsuDugumEkle(new KomsuDugum(10, dugumliste[710], dugumliste[810]));

        dugumliste[711].KomsuDugumEkle(new KomsuDugum(10, dugumliste[711], dugumliste[611]));
        dugumliste[711].KomsuDugumEkle(new KomsuDugum(10, dugumliste[711], dugumliste[811]));
        dugumliste[711].KomsuDugumEkle(new KomsuDugum(10, dugumliste[711], dugumliste[710]));
        dugumliste[711].KomsuDugumEkle(new KomsuDugum(10, dugumliste[711], dugumliste[712]));
        
        dugumliste[712].KomsuDugumEkle(new KomsuDugum(10, dugumliste[712], dugumliste[711]));

        dugumliste[81].KomsuDugumEkle(new KomsuDugum(10, dugumliste[81], dugumliste[71]));
        dugumliste[81].KomsuDugumEkle(new KomsuDugum(10, dugumliste[81], dugumliste[91]));

        dugumliste[83].KomsuDugumEkle(new KomsuDugum(10, dugumliste[83], dugumliste[73]));
        dugumliste[83].KomsuDugumEkle(new KomsuDugum(10, dugumliste[83], dugumliste[93]));

        dugumliste[89].KomsuDugumEkle(new KomsuDugum(10, dugumliste[89], dugumliste[79]));
        dugumliste[89].KomsuDugumEkle(new KomsuDugum(10, dugumliste[89], dugumliste[810]));
        dugumliste[89].KomsuDugumEkle(new KomsuDugum(10, dugumliste[89], dugumliste[99]));

        dugumliste[810].KomsuDugumEkle(new KomsuDugum(10, dugumliste[810], dugumliste[89]));
        dugumliste[810].KomsuDugumEkle(new KomsuDugum(10, dugumliste[810], dugumliste[710]));
        dugumliste[810].KomsuDugumEkle(new KomsuDugum(10, dugumliste[810], dugumliste[811]));
        dugumliste[810].KomsuDugumEkle(new KomsuDugum(10, dugumliste[810], dugumliste[910]));

        dugumliste[811].KomsuDugumEkle(new KomsuDugum(10, dugumliste[811], dugumliste[711]));
        dugumliste[811].KomsuDugumEkle(new KomsuDugum(10, dugumliste[811], dugumliste[810]));
        dugumliste[811].KomsuDugumEkle(new KomsuDugum(10, dugumliste[811], dugumliste[911]));

        dugumliste[91].KomsuDugumEkle(new KomsuDugum(10, dugumliste[91], dugumliste[81]));
        dugumliste[91].KomsuDugumEkle(new KomsuDugum(10, dugumliste[91], dugumliste[92]));

        dugumliste[92].KomsuDugumEkle(new KomsuDugum(10, dugumliste[92], dugumliste[91]));
        dugumliste[92].KomsuDugumEkle(new KomsuDugum(10, dugumliste[92], dugumliste[93]));

        dugumliste[93].KomsuDugumEkle(new KomsuDugum(10, dugumliste[93], dugumliste[92]));
        dugumliste[93].KomsuDugumEkle(new KomsuDugum(10, dugumliste[93], dugumliste[83]));
        dugumliste[93].KomsuDugumEkle(new KomsuDugum(10, dugumliste[93], dugumliste[94]));
        dugumliste[93].KomsuDugumEkle(new KomsuDugum(10, dugumliste[93], dugumliste[103]));

        dugumliste[94].KomsuDugumEkle(new KomsuDugum(10, dugumliste[94], dugumliste[93]));
        dugumliste[94].KomsuDugumEkle(new KomsuDugum(10, dugumliste[94], dugumliste[95]));

        dugumliste[95].KomsuDugumEkle(new KomsuDugum(10, dugumliste[95], dugumliste[94]));
        dugumliste[95].KomsuDugumEkle(new KomsuDugum(10, dugumliste[95], dugumliste[96]));

        dugumliste[96].KomsuDugumEkle(new KomsuDugum(10, dugumliste[96], dugumliste[95]));
        dugumliste[96].KomsuDugumEkle(new KomsuDugum(10, dugumliste[96], dugumliste[97]));

        dugumliste[97].KomsuDugumEkle(new KomsuDugum(10, dugumliste[97], dugumliste[96]));
        dugumliste[97].KomsuDugumEkle(new KomsuDugum(10, dugumliste[97], dugumliste[98]));

        dugumliste[98].KomsuDugumEkle(new KomsuDugum(10, dugumliste[98], dugumliste[97]));
        dugumliste[98].KomsuDugumEkle(new KomsuDugum(10, dugumliste[98], dugumliste[99]));

        dugumliste[99].KomsuDugumEkle(new KomsuDugum(10, dugumliste[99], dugumliste[98]));
        dugumliste[99].KomsuDugumEkle(new KomsuDugum(10, dugumliste[99], dugumliste[89]));
        dugumliste[99].KomsuDugumEkle(new KomsuDugum(10, dugumliste[99], dugumliste[910]));

        dugumliste[910].KomsuDugumEkle(new KomsuDugum(10, dugumliste[910], dugumliste[99]));
        dugumliste[910].KomsuDugumEkle(new KomsuDugum(10, dugumliste[910], dugumliste[810]));
        dugumliste[910].KomsuDugumEkle(new KomsuDugum(10, dugumliste[910], dugumliste[911]));

        dugumliste[911].KomsuDugumEkle(new KomsuDugum(10, dugumliste[911], dugumliste[811]));
        dugumliste[911].KomsuDugumEkle(new KomsuDugum(10, dugumliste[911], dugumliste[910]));

        dugumliste[103].KomsuDugumEkle(new KomsuDugum(10, dugumliste[103], dugumliste[93]));

 
    }

}