/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilibniz;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public class ParalellPi extends Thread{

    int ini;
    int fin;
    double value = 0;

    public ParalellPi(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }

    public void run() {
        for (int i = ini; i < fin; i++) {
            double x = 2*i+1;
            if(i % 2 == 0){
                value += 1/x;
            }else{
                value -= 1/x;
            }            
        }
    }
    public static double pi(int itera){
        int procs = Runtime.getRuntime().availableProcessors();
        ParalellPi t[] = new ParalellPi[procs];
        
        int dim = itera/procs;
        
        for (int i = 0; i < t.length; i++) {
            t[i] = new ParalellPi(dim*i, dim*(i+1));
            t[i].start();
        }
        double val = 0;
        for (int i = 0; i < t.length; i++) {
            try {
                t[i].join();
                val += t[i].value;
            } catch (InterruptedException ex) {
                Logger.getLogger(ParalellPi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 4.0*val;   
    }
    public static void main(String[] args) {
        System.out.println("Parallel: "+pi(10000));
    }

}
