import static java.lang.Integer.MAX_VALUE;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraEnkisaYol {

    public void KisaYolHesaplama(Dugum BasDugum, Dugum[] dugumliste) {

        for (int l = 0; l < dugumliste.length; l++) {
            if (dugumliste[l] != null) {
                dugumliste[l].setVisited(false);
                dugumliste[l].setMesafe(MAX_VALUE);
                dugumliste[l].setOncekiDugum(null);
            }

        }

        BasDugum.setMesafe(0);
        PriorityQueue<Dugum> priorityQueue = new PriorityQueue<>();//öncelik sıralamalı kuyruk yapısı
        priorityQueue.add(BasDugum);
        BasDugum.setVisited(true);

        while (!priorityQueue.isEmpty()) {
           
            Dugum kaynakDugum = priorityQueue.poll();

            for (KomsuDugum komsuDugum : kaynakDugum.getKomsuListesi()) {

                Dugum v = komsuDugum.getHedefDugum();
                if (!v.isVisited()) {
                    double newMesafe = kaynakDugum.getMesafe() + komsuDugum.getWeight();

                    if (newMesafe < v.getMesafe()) {
                        priorityQueue.remove(v);
                        v.setMesafe((int) newMesafe);
                        v.setOncekiDugum(kaynakDugum);
                        priorityQueue.add(v);
                    }
                }
            }
            kaynakDugum.setVisited(true);
        }

    }

    public List<Dugum> getEnKısaYol(Dugum hedefDugum) {
        List<Dugum> path = new ArrayList<>();

        try {
            for (Dugum dugum = hedefDugum; dugum != null; dugum = dugum.getOncekiDugum()) {
                path.add(dugum);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        int x = path.size();
        path.remove(x - 1);
        Collections.reverse(path);
        return path;

    }

}