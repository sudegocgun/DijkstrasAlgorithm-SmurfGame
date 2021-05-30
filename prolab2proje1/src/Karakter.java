
public class Karakter extends Lokasyon {
    private int ID;
    private String Ad;
    private String Tur;

    public Karakter(int ID, String Ad, String Tur, int x, int y) {
        super(x, y);
        this.ID = ID;
        this.Ad = Ad;
        this.Tur = Tur;
    }
    

    

    public Karakter() {
    }
    
 public void EnKisaYol(){
   
    }
 
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String Ad) {
        this.Ad = Ad;
    }

    public String getTur() {
        return Tur;
    }

    public void setTur(String Tur) {
        this.Tur = Tur;
    }

    
}
