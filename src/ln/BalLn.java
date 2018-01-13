/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ln;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public class BalLn extends Thread {

    double x;
    double n;
    AtomicInteger ticket;
    double value = 0;
    
    public BalLn(double x, AtomicInteger ticket) {
        this.x = x;
        this.ticket = ticket;
    }

    public static double calculateLn(double n, int itera){
        int p = Runtime.getRuntime().availableProcessors();
        BalLn t[] = new BalLn[p];
        
        AtomicInteger tick = new AtomicInteger(itera);
        
        for (int i = 0; i < t.length; i++) {
            t[i] = new BalLn(n,tick);
            t[i].start();
        }
        double ln = 0;
        for (int i = 0; i < t.length; i++) {
            try {
                t[i].join();
                ln += t[i].value;
            } catch (InterruptedException ex) {
                Logger.getLogger(BalLn.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return 2.0*ln;
    }
    
    public void run() {
        int y;
        while ((y=ticket.getAndDecrement())>0) {
            value += (Math.pow(((x - 1) / (x + 1)), (2.0 * y) - 1) / ((2.0 * y) - 1));
        }
    }
    public static void main(String[] args) {
        System.out.println(""+calculateLn(2.0,10));
    }

}
