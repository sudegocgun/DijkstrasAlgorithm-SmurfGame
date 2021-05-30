/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sudeyg
 */
public class Mantar extends Obje  {
 private int deger; 

    public Mantar(int deger, int x, int y) {
        super(x, y);
        this.deger = deger;
    }

  

    public int getDeger() {
        return deger;
    }

    public void setDeger(int deger) {
        this.deger = deger;
    }
    
}
