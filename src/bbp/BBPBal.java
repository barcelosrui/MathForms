/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbp;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public class BBPBal extends Thread{
    AtomicInteger ticket;
    double piPartilhado;
    public BBPBal(AtomicInteger ticket) {
        this.ticket = ticket;
    }
    
    @Override
    public void run(){
        int y;
        while((y = ticket.getAndDecrement())>=0){
            double exp1 = Math.pow(1.0/16.0, y);
            double exp2 = 4.0/((8.0*y)+1);
            double exp3 = 2.0/((8.0*y)+4);
            double exp4 = 1.0/((8.0*y)+5);
            double exp5 = 1.0/((8.0*y)+6);
            piPartilhado += exp1*(exp2-exp3-exp4-exp5);
        }
    }
    public static double calculatePi(int itera){
        int proc = Runtime.getRuntime().availableProcessors();
        
      BBPBal thr[] =new BBPBal[proc];
      
      AtomicInteger tick = new AtomicInteger(itera);
      
        for (int i = 0; i < thr.length; i++) {
            thr[i] = new BBPBal(tick);
            thr[i].start();
        }
        double pi =0;
        for(BBPBal t: thr){
            try {
                t.join();
                pi += t.piPartilhado;
            } catch (InterruptedException ex) {
                Logger.getLogger(BBPBal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pi;
    }
    public static void main(String[] args) {
        System.out.println("PI BBP BALANCIADO:" + calculatePi(100000000));
    }
}
