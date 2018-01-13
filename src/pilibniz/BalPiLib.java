/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilibniz;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public class BalPiLib extends Thread {

    AtomicInteger ticket;
    double val = 0;

    public BalPiLib(AtomicInteger ticket) {
        this.ticket = ticket;
    }

    public void run() {
        int y;
        while ((y = ticket.getAndDecrement()) >= 0) {
            double x = 2 * y + 1;
            if (y % 2 == 0) {
                val += 1 / x;
            } else {
                val -= 1 / x;
            }
        }
    }
    public static double piLin(int itera){
        AtomicInteger tick = new AtomicInteger(itera);
        int p = Runtime.getRuntime().availableProcessors();
        BalPiLib t[] = new BalPiLib[p];
        
        for (int i = 0; i < t.length; i++) {
            t[i] = new BalPiLib(tick);
            t[i].start();
        }
        double pi =0;
        for (BalPiLib thr: t) {
            try {
                thr.join();
                pi +=thr.val;
            } catch (InterruptedException ex) {
                Logger.getLogger(BalPiLib.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 4.00*pi;
    }
    public static void main(String[] args) {
        System.out.println(""+ piLin(100000000));
    }

}
