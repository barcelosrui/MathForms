/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wallis;

/**
 *
 * @author Rui Barcelos
 */
public class WallisSeq {
    public static double calculatePI(int itera){
        double pi = 1;
        for (int i = 1; i < itera; i++) {
            pi *= (((2.0*i)/((2.0*i)-1))*((2.0*i)/((2.0*i)+1)));
        }
        return 2*pi;
    }
    public static void main(String[] args) {
        System.out.println("PI "+ calculatePI(1000000000));
    }
}
