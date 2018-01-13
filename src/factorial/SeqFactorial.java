/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorial;

/**
 *
 * @author Rui Barcelos
 */
public class SeqFactorial {
    public static long factorial(int num){
        long fact = 0;
        for (int i = 0; i < num; i++) {
            if(fact == 0){
                fact += num;
            }else{
                fact *= i;
            }
        }
        return fact;
    } 
    public static void main(String[] args) {
        System.out.println("Factorial(n!): "+factorial(9));
    }
}
