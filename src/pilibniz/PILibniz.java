/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilibniz;

/**
 *
 * @author Rui Barcelos
 */
public class PILibniz {
    public static double Libniz(int n){
        double val = 0;
        for (int i = 0; i < n; i++) {
            double x = 2*i+1;
            if(i % 2 == 0){
                val += 1/x;
            }else{
                val -= 1/x;
            }            
        }
        return 4.0*val;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("PI:"+ Libniz(100000000) );
    }
    
}
