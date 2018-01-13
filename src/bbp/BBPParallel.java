/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public class BBPParallel extends Thread {
    int ini, fin;
    double piPartilhado = 0;
    public BBPParallel(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }
    
    public static double calculatePi(int itera){
        int p = Runtime.getRuntime().availableProcessors();
        BBPParallel t[] = new BBPParallel[p];
        int dim = itera/p;
        
        for (int i = 0; i < t.length; i++) {
            t[i] = new BBPParallel(dim*i, dim*(i+1));
            t[i].start();
        }
        double pi = 0;
        for(BBPParallel thr:t){
            try {
                thr.join();
                pi += thr.piPartilhado;
            } catch (InterruptedException ex) {
                Logger.getLogger(BBPParallel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pi;
    }
    
    public void run(){
        for (int i = ini; i < fin; i++) {
            double exp1 = Math.pow(1.0/16.0, i);
            double exp2 = 4.0/((8.0*i)+1);
            double exp3 = 2.0/((8.0*i)+4);
            double exp4 = 1.0/((8.0*i)+5);
            double exp5 = 1.0/((8.0*i)+6);
            piPartilhado += exp1*(exp2-exp3-exp4-exp5);
        }
    }
    public static void main(String[] args) {
        System.out.println("PI BBP PARALELIZADO: "+ calculatePi(100000));
    }
}
