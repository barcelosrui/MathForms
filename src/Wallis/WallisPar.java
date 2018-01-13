/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wallis;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public class WallisPar extends Thread {

    int ini, fin;
    double piParallel = 1;

    public WallisPar(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }

    public void run() {
        for (int i = ini; i < fin; i++) {
            piParallel *= (((2.0 * i) / ((2.0 * i) - 1)) * ((2.0 * i) / ((2.0 * i) + 1)));
        }
    }

    public static double calculatePi(int itera) {
        int p = Runtime.getRuntime().availableProcessors();
        WallisPar t[] = new WallisPar[p];

        int dim = itera / p;
        for (int i = 0; i < t.length; i++) {
            t[i] = new WallisPar((dim*i)+1, (dim*(i+1))+1);
            t[i].start();
        }
        double pi = 1;
        for (WallisPar thr : t) {
            try {
                thr.join(); 
                pi *= thr.piParallel;
            } catch (InterruptedException ex) {
                Logger.getLogger(WallisPar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 2.0*pi;
    }
    public static void main(String[] args) {
        System.out.println("PI Paralell: "+ calculatePi(100000));
    }
}
