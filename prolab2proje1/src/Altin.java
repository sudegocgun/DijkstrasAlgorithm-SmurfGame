
import java.awt.Graphics;


public class Altin extends Obje{
    private int adet;
    private int deger;

    public Altin(int adet, int deger, int x, int y) {
        super(x, y);
        this.adet = adet;
        this.deger = deger;
    }

   

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public int getDeger() {
        return deger;
    }

    public void setDeger(int deger) {
        this.deger = deger;
    }
    
}
