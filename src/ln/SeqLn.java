/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ln;

/**
 *
 * @author Rui Barcelos
 */
public class SeqLn extends Thread {

    public static double seqLn(double x, int n) {
        double v = 0;
        for (int i = 1; i < n + 1; i++) {
            v += (Math.pow(((x - 1)/(x+1)), (2.0*i)-1) / ((2.0 * i)-1));

        }
        return 2.0*v;
    }

    public static void main(String[] args) {
        double x = 3.0;
        System.out.println("LN(" + x + ") = " + seqLn(x, 1000));
    }
}
