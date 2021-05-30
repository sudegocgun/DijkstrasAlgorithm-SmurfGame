
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class BitisEkraniOver extends JPanel {
      @Override
   protected void paintComponent(Graphics g)
   {
   super.paintComponent(g); 
   Image arkaplan=new ImageIcon("C:\\Users\\sude yağmur\\Desktop\\prolab2proje1\\src\\image\\gameover.jpg").getImage();
   g.drawImage(arkaplan, 0, 0, 1280, 960, null);

   }
}
