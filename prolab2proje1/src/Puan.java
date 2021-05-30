
public class Puan extends Oyuncu {
    private Oyuncu oyuncu;
    
	
	

	public Puan(Oyuncu oyuncu) {
		this.oyuncu = oyuncu;
		this.oyuncu.setSkor(20);
	}

    @Override
    public int PuaniGoster() {
        return super.PuaniGoster(); 
    }

}
