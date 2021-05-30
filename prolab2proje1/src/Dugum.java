import java.util.ArrayList;
import java.util.List;
public class Dugum implements Comparable<Dugum> {
 
 private String name;
 private List<KomsuDugum> adjacenciesList;
 private boolean visited;
 private Dugum predecessor; 
 private double distance = Double.MAX_VALUE;
 private int x,y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
 
 public Dugum(String name,int x,int y) {
 this.name = name;
 this.x=x;
 this.y=y;
 this.adjacenciesList = new ArrayList<>();
 }
 
 public void KomsuDugumEkle(KomsuDugum komsuDugum) {
 this.adjacenciesList.add(komsuDugum);
 }
 
 public String getName() {
 return name;
 }
 
 public void setName(String name) {
 this.name = name;
 }
 
 public List<KomsuDugum> getKomsuListesi() {
 return adjacenciesList;
 }
 
 public void setKomsuListesi(List<KomsuDugum> adjacenciesList) {
 this.adjacenciesList = adjacenciesList;
 }
 
 public boolean isVisited() {
 return visited;
 }
 
 public void setVisited(boolean visited) {
 this.visited = visited;
 }
 
 public Dugum getOncekiDugum() {
 return predecessor;
 }
 
 public void setOncekiDugum(Dugum predecessor) {
 this.predecessor = predecessor;
 }
 
 public double getMesafe() {
 return distance;
 }
 
 public void setMesafe(int distance) {
 this.distance = distance;
 }
 
 @Override
 public String toString() {
 return this.name;
 }
 
 @Override
 public int compareTo(Dugum dıgerDugum) {
 return Double.compare(this.distance, dıgerDugum.getMesafe());
 }
}