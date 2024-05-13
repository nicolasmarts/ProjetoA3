/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exercicio40;

/**
 *
 * @author 1072322350
 */
import javax.swing.JOptionPane;
public class Exercicio40 {

    public static void main(String[] args) {
            int numeros[] = new int[10];
            numeros[0] = 0;
            numeros[1] = 1;
            numeros[2] = 2;
            numeros[3] = 3;
            numeros[4] = 4;
            numeros[5] = 5;
            numeros[6] = 6;
            numeros[7] = 7;
            numeros[8] = 8;
            numeros[9] = 9;
            
             //vai dar erro, não existe índice 10.
        
        
        try {
        System.out.println("tentando mostrar índice 10:" + numeros[10]);
        }catch (ArrayIndexOutOfBoundsException erro) {// Exception pega todos os erros
        JOptionPane.showMessageDialog(null, "Não existe índice 10!");
        }
    }
}