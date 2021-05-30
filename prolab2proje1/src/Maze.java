import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.imageio.ImageIO.read;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Maze extends JFrame {

    JLabel label = new JLabel();
    JFrame jf = new JFrame();
    GirisEkrani girisekrani = new GirisEkrani();

    public BufferedImage gargamel;
    public String gargamelfoto = "C:\\Users\\sude yağmur\\Desktop\\prolab2proje1\\src\\image\\gargamel.png";
    public BufferedImage azman;
    public String azmanfoto = "C:\\Users\\sude yağmur\\Desktop\\prolab2proje1\\src\\image\\azman-removebg-preview.png";
    public BufferedImage gozluklusırın;
    public String gozluklusırınfoto = "C:\\Users\\sude yağmur\\Desktop\\prolab2proje1\\src\\image\\gozlukluu-removebg-preview.png";
    public BufferedImage tembelsırın;
    public String tembelsirinfoto = "C:\\Users\\sude yağmur\\Desktop\\prolab2proje1\\src\\image\\tembelll-removebg-preview.png";
    public BufferedImage sirine;
    public String sirinefoto = "C:\\Users\\sude yağmur\\Desktop\\prolab2proje1\\src\\image\\sirine.png";

    static List<Dugum> enkisa;
    static List<Dugum> enkisa2;
    public Dugum[] dugumliste;
    DijkstraEnkisaYol kisayol = new DijkstraEnkisaYol();
    List<Integer> path;
    Oyuncu oyuncu;
    int[][] maze1 = new int[11][13];
    String dusman1;
    String dusman2;
    String karakter;
    int xkor = 6, ykor = 5, xdusman1 = 0, ydusman1 = 0, xdusman2 = 0, ydusman2 = 0, ilerle = 0;
    int xdusman1baslangıc = 0, xdusman2baslangıc = 0, ydusman1baslangıc = 0, ydusman2baslangıc = 0;
    int xbitis=12,ybitis=7;
    
    public Maze(int[][] maze, String dusman1, int xdusman1, int ydusman1, Dugum[] dugumliste, int ilerle, String karakter, Oyuncu oyuncu, JLabel label) {
        this.maze1 = maze;
        this.dusman1 = dusman1;
        this.xdusman1 = xdusman1;
        this.ydusman1 = ydusman1;
        this.dugumliste = dugumliste;
        this.ilerle = ilerle;
        this.karakter = karakter;
        this.oyuncu = oyuncu;
        this.label = label;
        xdusman1baslangıc = xdusman1;
        ydusman1baslangıc = ydusman1;

        setTitle(" Simple Maze Game ");
        setSize(1280, 960);
        setLocationRelativeTo(null);

        try {
            gargamel = read(new File(gargamelfoto));//
            azman = read(new File(azmanfoto));
            gozluklusırın = read(new File(gozluklusırınfoto));
            tembelsırın = read(new File(tembelsirinfoto));
            sirine = read(new File(sirinefoto));
        } catch (Exception e) {

        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public Maze(int[][] maze, String dusman1, String dusman2, int xdusman1, int ydusman1, int xdusman2, int ydusman2, Dugum[] dugumliste, int ilerle, String karakter, Oyuncu oyuncu, JLabel label) {
        this.maze1 = maze;
        this.dusman1 = dusman1;
        this.dusman2 = dusman2;
        this.xdusman1 = xdusman1;
        this.ydusman1 = ydusman1;
        this.xdusman2 = xdusman2;
        this.ydusman2 = ydusman2;
        this.oyuncu = oyuncu;
        this.label = label;
        xdusman1baslangıc = xdusman1;
        xdusman2baslangıc = xdusman2;
        ydusman1baslangıc = ydusman1;
        ydusman2baslangıc = ydusman2;

        this.dugumliste = dugumliste;
        this.ilerle = ilerle;
        this.karakter = karakter;
        setTitle(" Simple Maze Game ");
        setSize(1280, 960);
        setLocationRelativeTo(null);
        try {
            gargamel = read(new File(gargamelfoto));
            azman = read(new File(azmanfoto));
            gozluklusırın = read(new File(gozluklusırınfoto));
            tembelsırın = read(new File(tembelsirinfoto));
            sirine = read(new File(sirinefoto));
        } catch (Exception e) {

        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(240, 140);
        label.setText("Kullanıcının skoru: " + oyuncu.getSkor());
        for (int row = 0; row < maze1.length; row++) {
            for (int col = 0; col < maze1[0].length; col++) {
                Color color;
                switch (maze1[row][col]) {
                    case 0:
                        color = Color.GRAY;
                        break;
                    default:
                        color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(60 * col, 60 * row, 60, 60);
                g.setColor(Color.BLACK);
                g.drawRect(60 * col, 60 * row, 60, 60);

            }
        }
        // Ana karakter
        g.drawImage(sirine, 13 * 56, 7 * 55, 150, 150, null);
        if (karakter.contains("gozluklu")) {

            g.drawImage(gozluklusırın, xkor * 60, ykor * 60, 60, 60, null);

        } else {

            g.drawImage(tembelsırın, xkor * 60, ykor * 60, 60, 60, null);
        }


        if (dusman1.contains("Gargamel")) {
            g.drawImage(gargamel, xdusman1 * 60, ydusman1 * 60, 60, 60, null);
            Gargamel gargamel = new Gargamel(1, "Gargamel", "dusman", xdusman1, ydusman1);


        } else {
            g.drawImage(azman, xdusman1 * 60, ydusman1 * 60, 60, 60, null);
            Azman azman = new Azman(1, "Azman", "dusman", xdusman1, ydusman1);
        }
        if (dusman2 == null) {
            // System.out.println("dusman2 yok");
        } else if (dusman2.contains("Gargamel")) {
            g.drawImage(gargamel, xdusman2 * 60, ydusman2 * 60, 60, 60, null);
            Gargamel gargamel = new Gargamel(2, "Gargamel", "dusman", xdusman2, ydusman2);
        } else if (dusman2.contains("Azman")) {
            g.drawImage(azman, xdusman2 * 60, ydusman2 * 60, 60, 60, null);
            Azman azman = new Azman(2, "Azman", "dusman", xdusman2, ydusman2);
        }

        if (enkisa == null) {

        } else {

            if (dusman2 == null) {

                if (dusman1.contains("Azman")) {
                    for (int p = 1; p < enkisa.size() - 1; p++) {
                        g.setColor(Color.PINK);
                        g.fillRect(enkisa.get(p).getY() * 60, enkisa.get(p).getX() * 60, 60, 60);
                    }
                } else {

                    for (int p = 1; p < enkisa.size() - 2; p++) {
                        g.setColor(Color.PINK);
                        g.fillRect(enkisa.get(p + 1).getY() * 60, enkisa.get(p + 1).getX() * 60, 60, 60);
                    }
                }

            } else {
                if (dusman1.contains("Azman")) {
                    for (int p = 1; p < enkisa.size() - 1; p++) {
                        g.setColor(Color.PINK);
                        g.fillRect(enkisa.get(p).getY() * 60, enkisa.get(p).getX() * 60, 60, 60);
                    }
                } else {
                    for (int p = 1; p < enkisa.size() - 2; p++) {
                        g.setColor(Color.PINK);
                        g.fillRect(enkisa.get(p + 1).getY() * 60, enkisa.get(p + 1).getX() * 60, 60, 60);
                    }

                }
                if (dusman2.contains("Azman")) {
                    for (int p = 1; p < enkisa2.size() - 1; p++) {
                        g.setColor(Color.cyan);
                        g.fillRect(enkisa2.get(p).getY() * 60, enkisa2.get(p).getX() * 60, 60, 60);
                    }
                } else {
                    for (int p = 1; p < enkisa2.size() - 2; p++) {
                        g.setColor(Color.cyan);
                        g.fillRect(enkisa2.get(p + 1).getY() * 60, enkisa2.get(p + 1).getX() * 60, 60, 60);
                    }
                }
            }
        }
        if(oyuncu.getSkor()<=0){          
           BitisEkraniOver ekran = new BitisEkraniOver();
              JFrame bitis=new JFrame();
              this.setVisible(false);
               bitis.setTitle(" Oyun Bitti ");
       bitis.setSize(1280, 960);
        bitis.setLocationRelativeTo(null);
              bitis.add(ekran);
        bitis.setVisible(true);
        bitis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

         if(xkor==xbitis){
              BitisEkraniWin ekran = new BitisEkraniWin();
              JFrame bitis=new JFrame();
              this.setVisible(false);
               bitis.setTitle(" Oyun Bitti ");
       bitis.setSize(1280, 960);
        bitis.setLocationRelativeTo(null);
              bitis.add(ekran);
        bitis.setVisible(true);
        bitis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
//              JOptionPane.showInputDialog("OYUN BİTTİ");
           
        }
       
      
    }
  @Override
    protected void processKeyEvent(KeyEvent key) {
        if (key.getID() != KeyEvent.KEY_PRESSED) {
            return;
        }
        Gargamel gargamel = new Gargamel();
        Azman azman = new Azman();
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) {

            switch (ilerle) {
                case 1:
                    xkor = xkor + ilerle;
                    if (maze1[ykor][xkor] == 0) {
                        xkor = xkor - ilerle;

                    }
                    break;
                case 2:
                    xkor = xkor + ilerle;
                    if (maze1[ykor][xkor] == 0 || maze1[ykor][xkor - 1] == 0) {
                        xkor = xkor - ilerle;
                    }
                    break;

            }

//karakter dusmanı yakalarsa
            if (dusman2 != null) {

                if (xkor == xdusman1 && ykor == ydusman1) {
                    if (dusman1.contains("Azman")) {
                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                    } else {
                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                    }

                    xdusman1 = xdusman1baslangıc;
                    ydusman1 = ydusman1baslangıc;

                } else if (xkor == xdusman2 && ykor == ydusman2) {
                    xdusman2 = xdusman2baslangıc;
                    ydusman2 = ydusman2baslangıc;
                    if (dusman2.contains("Azman")) {
                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                    } else {
                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                    }
                } else {
                    //Dusman karakteri yakalarsa
                    DijkstraEnkisaYol kisayol = new DijkstraEnkisaYol();

                    if (xdusman1 > 9) {
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 100) + xdusman1], dugumliste);

                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        } else {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);

                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {

                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        }

                    } else {
                        //Dusman karakteri yakalarsa
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 10) + xdusman1], dugumliste);
                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        } else {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        }
                    }

                    if (xdusman2 > 9) {
                        //Dusman karakteri yakalarsa
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman2 * 100) + xdusman2], dugumliste);
                        if (xkor > 9) {
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa2);
                        } else {
                            //Dusman karakteri yakalarsa
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa2);
                        }

                    } else {
                        //Dusman karakteri yakalarsa
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman2 * 10) + xdusman2], dugumliste);
                        if (xkor > 9) {
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa2);
                        } else {
                            //Dusman karakteri yakalarsa
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa2);
                        }
                    }
                }
            } else {
                //karakter dusmanı yakalarsa             

                if (xkor == xdusman1 && ykor == ydusman1) {

                    xdusman1 = xdusman1baslangıc;
                    ydusman1 = ydusman1baslangıc;
                    if (dusman1.contains("Azman")) {
                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                    } else {
                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                    }

                } else {
                    DijkstraEnkisaYol kisayol = new DijkstraEnkisaYol();

                    if (xdusman1 > 9) {
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 100) + xdusman1], dugumliste);
                        //Dusman karakteri yakalarsa
                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        } else {
                            //Dusman karakteri yakalarsa                        
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        }

                    } else {
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 10) + xdusman1], dugumliste);
                        //Dusman karakteri yakalarsa
                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        } else {
                            //Dusman karakteri yakalarsa
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        }
                    }
                }

            }
            //çalışıyor
        }
        if (key.getKeyCode() == KeyEvent.VK_LEFT) {

            switch (ilerle) {
                case 1:
                    xkor = xkor - ilerle;
                    if (maze1[ykor][xkor] == 0) {
                        xkor = xkor + ilerle;

                    }
                    break;
                case 2:
                    xkor = xkor - ilerle;
                    if (maze1[ykor][xkor] == 0 || maze1[ykor][xkor + 1] == 0) {
                        xkor = xkor + ilerle;
                    }
                    break;

            }

            if (dusman2 != null) {

                if (xkor == xdusman1 && ykor == ydusman1) {
                    if (dusman1.contains("Azman")) {
                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                    } else {
                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                    }

                    xdusman1 = xdusman1baslangıc;
                    ydusman1 = ydusman1baslangıc;

                } else if (xkor == xdusman2 && ykor == ydusman2) {
                    xdusman2 = xdusman2baslangıc;
                    ydusman2 = ydusman2baslangıc;
                    if (dusman2.contains("Azman")) {
                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                    } else {
                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                    }
                } else {
                    //Dusman karakteri yakalarsa
                    DijkstraEnkisaYol kisayol = new DijkstraEnkisaYol();

                    if (xdusman1 > 9) {
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 100) + xdusman1], dugumliste);

                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        } else {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);

                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {

                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        }

                    } else {
                        //Dusman karakteri yakalarsa
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 10) + xdusman1], dugumliste);
                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        } else {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        }
                    }

                    if (xdusman2 > 9) {
                        //Dusman karakteri yakalarsa
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman2 * 100) + xdusman2], dugumliste);
                        if (xkor > 9) {
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa2);
                        } else {
                            //Dusman karakteri yakalarsa
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa2);
                        }

                    } else {
                        //Dusman karakteri yakalarsa
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman2 * 10) + xdusman2], dugumliste);
                        if (xkor > 9) {
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa2);
                        } else {
                            //Dusman karakteri yakalarsa
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa2);
                        }
                    }
                }
            } else {
                //karakter dusmanı yakalarsa             

                if (xkor == xdusman1 && ykor == ydusman1) {

                    xdusman1 = xdusman1baslangıc;
                    ydusman1 = ydusman1baslangıc;
                    if (dusman1.contains("Azman")) {
                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                    } else {
                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                    }

                } else {
                    DijkstraEnkisaYol kisayol = new DijkstraEnkisaYol();

                    if (xdusman1 > 9) {
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 100) + xdusman1], dugumliste);
                        //Dusman karakteri yakalarsa
                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        } else {
                            //Dusman karakteri yakalarsa                        
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        }

                    } else {
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 10) + xdusman1], dugumliste);
                        //Dusman karakteri yakalarsa
                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        } else {
                            //Dusman karakteri yakalarsa
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        }
                    }
                }

            }
            //çalışıyor
        }
        if (key.getKeyCode() == KeyEvent.VK_UP) {

            switch (ilerle) {
                case 1:
                    ykor = ykor - ilerle;
                    if (maze1[ykor][xkor] == 0) {
                        ykor = ykor + ilerle;

                    }
                    break;
                case 2:
                    ykor = ykor - ilerle;
                    if (maze1[ykor][xkor] == 0 || maze1[ykor + 1][xkor] == 0) {
                        ykor = ykor + ilerle;
                    }
                    break;

            }

            if (dusman2 != null) {

                if (xkor == xdusman1 && ykor == ydusman1) {
                    if (dusman1.contains("Azman")) {
                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                    } else {
                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                    }

                    xdusman1 = xdusman1baslangıc;
                    ydusman1 = ydusman1baslangıc;

                } else if (xkor == xdusman2 && ykor == ydusman2) {
                    xdusman2 = xdusman2baslangıc;
                    ydusman2 = ydusman2baslangıc;
                    if (dusman2.contains("Azman")) {
                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                    } else {
                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                    }
                } else {
                    //Dusman karakteri yakalarsa
                    DijkstraEnkisaYol kisayol = new DijkstraEnkisaYol();

                    if (xdusman1 > 9) {
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 100) + xdusman1], dugumliste);

                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        } else {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);

                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {

                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        }

                    } else {
                        //Dusman karakteri yakalarsa
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 10) + xdusman1], dugumliste);
                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        } else {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        }
                    }

                    if (xdusman2 > 9) {
                        //Dusman karakteri yakalarsa
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman2 * 100) + xdusman2], dugumliste);
                        if (xkor > 9) {
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa2);
                        } else {
                            //Dusman karakteri yakalarsa
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa2);
                        }

                    } else {
                        //Dusman karakteri yakalarsa
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman2 * 10) + xdusman2], dugumliste);
                        if (xkor > 9) {
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa2);
                        } else {
                            //Dusman karakteri yakalarsa
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa2);
                        }
                    }
                }
            } else {
                //karakter dusmanı yakalarsa             

                if (xkor == xdusman1 && ykor == ydusman1) {

                    xdusman1 = xdusman1baslangıc;
                    ydusman1 = ydusman1baslangıc;
                    if (dusman1.contains("Azman")) {
                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                    } else {
                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                    }

                } else {
                    DijkstraEnkisaYol kisayol = new DijkstraEnkisaYol();

                    if (xdusman1 > 9) {
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 100) + xdusman1], dugumliste);
                        //Dusman karakteri yakalarsa
                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        } else {
                            //Dusman karakteri yakalarsa                        
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        }

                    } else {
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 10) + xdusman1], dugumliste);
                        //Dusman karakteri yakalarsa
                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        } else {
                            //Dusman karakteri yakalarsa
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        }
                    }
                }

            }
            //çalışıyor

        }

        if (key.getKeyCode() == KeyEvent.VK_DOWN) {

            switch (ilerle) {
                case 1:
                    ykor = ykor + ilerle;
                    if (maze1[ykor][xkor] == 0) {
                        ykor = ykor - ilerle;

                    }
                    break;
                case 2:
                    ykor = ykor + ilerle;
                    if (maze1[ykor][xkor] == 0 || maze1[ykor - 1][xkor] == 0) {
                        ykor = ykor - ilerle;
                    }
                    break;

            }

            if (dusman2 != null) {

                if (xkor == xdusman1 && ykor == ydusman1) {
                    if (dusman1.contains("Azman")) {
                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                    } else {
                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                    }

                    xdusman1 = xdusman1baslangıc;
                    ydusman1 = ydusman1baslangıc;

                } else if (xkor == xdusman2 && ykor == ydusman2) {
                    xdusman2 = xdusman2baslangıc;
                    ydusman2 = ydusman2baslangıc;
                    if (dusman2.contains("Azman")) {
                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                    } else {
                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                    }
                } else {
                    //Dusman karakteri yakalarsa
                    DijkstraEnkisaYol kisayol = new DijkstraEnkisaYol();

                    if (xdusman1 > 9) {
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 100) + xdusman1], dugumliste);

                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        } else {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);

                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {

                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        }

                    } else {
                        //Dusman karakteri yakalarsa
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 10) + xdusman1], dugumliste);
                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        } else {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        }
                    }

                    if (xdusman2 > 9) {
                        //Dusman karakteri yakalarsa
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman2 * 100) + xdusman2], dugumliste);
                        if (xkor > 9) {
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa2);
                        } else {
                            //Dusman karakteri yakalarsa
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa2);
                        }

                    } else {
                        //Dusman karakteri yakalarsa
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman2 * 10) + xdusman2], dugumliste);
                        if (xkor > 9) {
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa2);
                        } else {
                            //Dusman karakteri yakalarsa
                            enkisa2 = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman2.contains("Azman")) {
                                xdusman2 = enkisa2.get(0).getY();
                                ydusman2 = enkisa2.get(0).getX();
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa2.size() == 1) {
                                    xdusman2 = enkisa2.get(0).getY();
                                    ydusman2 = enkisa2.get(0).getX();
                                } else {
                                    xdusman2 = enkisa2.get(1).getY();
                                    ydusman2 = enkisa2.get(1).getX();
                                }
                                if (xdusman2 == xkor && ydusman2 == ykor) {
                                    xdusman2 = xdusman2baslangıc;
                                    ydusman2 = ydusman2baslangıc;
                                    if (dusman2.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa2);
                        }
                    }
                }
            } else {
                //karakter dusmanı yakalarsa             

                if (xkor == xdusman1 && ykor == ydusman1) {

                    xdusman1 = xdusman1baslangıc;
                    ydusman1 = ydusman1baslangıc;
                    if (dusman1.contains("Azman")) {
                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                    } else {
                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                    }

                } else {
                    DijkstraEnkisaYol kisayol = new DijkstraEnkisaYol();

                    if (xdusman1 > 9) {
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 100) + xdusman1], dugumliste);
                        //Dusman karakteri yakalarsa
                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        } else {
                            //Dusman karakteri yakalarsa                        
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        }

                    } else {
                        kisayol.KisaYolHesaplama(dugumliste[(ydusman1 * 10) + xdusman1], dugumliste);
                        //Dusman karakteri yakalarsa
                        if (xkor > 9) {
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 100 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }

                            System.out.println(enkisa);
                        } else {
                            //Dusman karakteri yakalarsa
                            enkisa = kisayol.getEnKısaYol(dugumliste[ykor * 10 + xkor]);
                            if (dusman1.contains("Azman")) {
                                xdusman1 = enkisa.get(0).getY();
                                ydusman1 = enkisa.get(0).getX();
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            } else {
                                if (enkisa.size() == 1) {
                                    xdusman1 = enkisa.get(0).getY();
                                    ydusman1 = enkisa.get(0).getX();
                                } else {
                                    xdusman1 = enkisa.get(1).getY();
                                    ydusman1 = enkisa.get(1).getX();
                                }
                                if (xdusman1 == xkor && ydusman1 == ykor) {
                                    xdusman1 = xdusman1baslangıc;
                                    ydusman1 = ydusman1baslangıc;
                                    if (dusman1.contains("Azman")) {
                                        oyuncu.setSkor(oyuncu.getSkor() - 5);
                                    } else {
                                        oyuncu.setSkor(oyuncu.getSkor() - 15);
                                    }
                                }
                            }
                            System.out.println(enkisa);
                        }
                    }
                }

            }
            //çalışıyor

        }
          


        repaint();
    }


}