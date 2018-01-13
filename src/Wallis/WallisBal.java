/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wallis;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public class WallisBal extends Thread {

    AtomicInteger ticket;
    double piPartilhado = 1;

    public WallisBal(AtomicInteger ticket) {
        this.ticket = ticket;
    }

    public void run() {
        int y;
        while ((y = ticket.getAndDecrement()) > 0) {
            piPartilhado *= (((2.0 * y) / ((2.0 * y) - 1)) * ((2.0 * y) / ((2.0 * y) + 1)));
        }
    }

    public static double calculatePi(int itera) {
        int p = Runtime.getRuntime().availableProcessors();
        WallisBal t[] = new WallisBal[p];
        AtomicInteger tick = new AtomicInteger(itera);
        for (int i = 0; i < t.length; i++) {
            t[i] = new WallisBal(tick);
            t[i].start();
        }
        double pi = 1;
        for (WallisBal thr : t) {
            try {
                thr.join();
                pi *= thr.piPartilhado;
            } catch (InterruptedException ex) {
                Logger.getLogger(WallisBal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 2.0*pi;
    }
    public static void main(String[] args) {
        System.out.println("PI BALANCIADO: " + calculatePi(100000000));
    }
}
