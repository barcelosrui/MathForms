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
public class ParLn extends Thread {

    int ini;
    int fin;
    double val;
    double ln = 0;

    public ParLn(int ini, int fin, double val) {
        this.ini = ini;
        this.fin = fin;
        this.val = val;
    }

    public static double calculateLn(int itera, double val) throws Exception{
        int p = Runtime.getRuntime().availableProcessors();
        ParLn t[] = new ParLn[p];
        int dim = itera/p;
        for (int i = 0; i < t.length; i++) {
            t[i] = new ParLn(dim*(i), dim*(i+1), val);
            t[i].start();
        }
        double value = 0;
        for (int i = 0; i < t.length; i++) {
            t[i].join();
            value += t[i].ln;
        }
        return 2*value;
    }
    
    public void run() {
        for (int i = ini + 1; i < fin + 1; i++) {
            ln += (Math.pow(((val - 1) / (val + 1)), (2.0 * i) - 1) / ((2.0 * i) - 1));
        }
    }
    public static void main(String[] args) throws Exception {
        double x = 2.0;
        System.out.println("LN("+x+") = "+calculateLn(1000,x));
    }
}
