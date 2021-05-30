
import java.awt.Image;
import javax.swing.ImageIcon;


public class Oyuncu extends Karakter {
private int skor;

    public Oyuncu(int ID, String Ad, String Tur, int x, int y,int skor) {
        super(ID, Ad, Tur, x, y);
        this.skor=skor;
        
        
    }
public int PuaniGoster() {
		return skor;
	}
    

    public Oyuncu() {
    }
   

    public int getSkor() {
        return skor;
    }

    public void setSkor(int Skor) {
        this.skor = Skor;
    }
    
    
}
