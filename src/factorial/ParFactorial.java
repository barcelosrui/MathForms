/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorial;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui Barcelos
 */
public class ParFactorial extends Thread {
    int ini;
    int fin;
    int fact = 1;
    public ParFactorial(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }
    public void run() {
        while (fin > ini) {
            fact *= fin;
            fin --;
        }   
    }
    public static int calculateFactorial(int val) throws Exception {
        int p = Runtime.getRuntime().availableProcessors();
        ParFactorial t[] = new ParFactorial[p];
        int dim = val / p;
        for (int i = 0; i < t.length; i++) {
            t[i] = new ParFactorial(dim * i, dim * (i + 1));
            t[i].start();
        }
        int factorial = 1;
        for (int i = 0; i < t.length; i++) {
            t[i].join();
            factorial *= t[i].fact;
        }
        int resto = val%p;
        int iniFin = val-resto;
        for (int i = iniFin+1; i <= val; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Factorial: " + calculateFactorial(6));
    }
}
