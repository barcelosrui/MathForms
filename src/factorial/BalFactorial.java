/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorial;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public class BalFactorial extends Thread {
    AtomicInteger factorial;
    long fact = 1;
    
    public BalFactorial(AtomicInteger factorial) {
        this.factorial = factorial;
    }
    public void run(){
        int y;
        while((y=factorial.getAndDecrement()) > 0){
            fact *= y;
        }
    }
    public static long calculateFactorial(int f){
        int procs = Runtime.getRuntime().availableProcessors();
        BalFactorial t[] = new BalFactorial[procs];
        AtomicInteger fact = new AtomicInteger(f);
        
        for (int i = 0; i < t.length; i++) {
            t[i] = new BalFactorial(fact);
            t[i].start();
        }
        long factorial = 1;
        for (int i = 0; i < t.length; i++) {
            try {
                t[i].join();
                factorial *= t[i].fact;
            } catch (InterruptedException ex) {
                Logger.getLogger(BalFactorial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return factorial;
    }
    public static void main(String[] args) {
        int x = 0;
        System.out.println(x+"! = "+ calculateFactorial(x) );
    }
    
}
