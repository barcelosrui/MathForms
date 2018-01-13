/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbp;

/**
 *
 * @author Rui Barcelos
 */
public class BBPSeq {
    public static double calculatePI(int itera){
        double pi =0;
        for (int i = 0; i < itera; i++) {
            double exp1 = Math.pow(1.0/16.0, i);
            double exp2 = 4.0/((8.0*i)+1);
            double exp3 = 2.0/((8.0*i)+4);
            double exp4 = 1.0/((8.0*i)+5);
            double exp5 = 1.0/((8.0*i)+6);
            pi += exp1*(exp2-exp3-exp4-exp5);
        }
        return pi;
    }
    public static void main(String[] args) {
        System.out.println("PI BBP Sequencial "+ calculatePI(1000000));
    }
}
