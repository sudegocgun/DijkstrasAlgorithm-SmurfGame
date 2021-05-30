public class KomsuDugum {
 
 private double weight;
 private Dugum startDugum;
 private Dugum HedefDugum;
 
 public KomsuDugum(double weight, Dugum startDugum, Dugum HedefDugum) {
 this.weight = weight;
 this.startDugum = startDugum;
 this.HedefDugum = HedefDugum;
 }
 
 public double getWeight() {
 return weight;
 }
 
 public void setWeight(double weight) {
 this.weight = weight;
 }
 
 public Dugum getStartDugum() {
 return startDugum;
 }
 
 public void setStartDugum(Dugum startDugum) {
 this.startDugum = startDugum;
 }
 
 public Dugum getHedefDugum() {
 return HedefDugum;
 }
 
 public void setHedefDugum(Dugum HedefDugum) {
 this.HedefDugum = HedefDugum;
 }
}