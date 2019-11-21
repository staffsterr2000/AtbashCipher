package com.staffsterr.cipher;

import java.io.*;

public class Main {
    private static String[] alphabet;
    private static String[] alphabetReverse;
    private static String[] alphabetRus;
    private static String[] alphabetRusReverse;
    private static String[] symbols;

    public static String[] encrypt(String[] decryptMessage){                //можно было обойтись одним методом преобразовывания имеющегося текста
        String[] encryptMessage = new String[decryptMessage.length];        //в зашифрованный/расшифрованный, но я сделал для этого два разных метода
        for(int i = 0; i < encryptMessage.length; i++){
//            System.out.println(decryptMessage[i]);
//            System.out.println(findIndex(alphabet, decryptMessage[i]));
//            System.out.println(alphabetReverse[findIndex(alphabet, decryptMessage[i])] + "\n");
            if(findIndex(alphabet, decryptMessage[i]) != -1)
                encryptMessage[i] = alphabetReverse[findIndex(alphabet, decryptMessage[i])];
            else if(findIndex(alphabetRus, decryptMessage[i]) != -1)
                encryptMessage[i] = alphabetRusReverse[findIndex(alphabetRus, decryptMessage[i])];
            else if(findIndex(symbols, decryptMessage[i]) != -1){
                encryptMessage[i] = decryptMessage[i];
            }
            else {
                System.out.println("Symbol \"" + decryptMessage[i] + "\" is unknown!");
                encryptMessage[i] = "*";
            }
        }
        return encryptMessage;
    }

    public static String[] decrypt(String[] encryptMessage){
        String[] decryptMessage = new String[encryptMessage.length];
        for(int i = 0; i < decryptMessage.length; i++){
//            System.out.println(encryptMessage[i]);
//            System.out.println(findIndex(alphabetReverse, encryptMessage[i]));
//            System.out.println(alphabet[findIndex(alphabetReverse, encryptMessage[i])] + "\n");
            if(findIndex(alphabetReverse, encryptMessage[i]) != -1)
                decryptMessage[i] = alphabet[findIndex(alphabetReverse, encryptMessage[i])];
            else if(findIndex(alphabetRusReverse, encryptMessage[i]) != -1)
                decryptMessage[i] = alphabetRus[findIndex(alphabetRusReverse, encryptMessage[i])];
            else if(findIndex(symbols, encryptMessage[i]) != -1) {
                decryptMessage[i] = encryptMessage[i];
            }
            else {
                System.out.println("Symbol \"" + encryptMessage[i] + "\" is unknown!");
                decryptMessage[i] = "*";
            }
        }
        return decryptMessage;
    }

    public static String[] reverse(String[] array){
        String[] arrayReverse = new String[array.length];
        for(int i = 0; i < arrayReverse.length; i++){
            int k = arrayReverse.length - (i + 1);
            arrayReverse[i] = array[k];
        }
        return arrayReverse;
    }

    public static String toString(String[] array){
        String string = "";
        for(String s: array){
            string += s;
        }
        return string;
    }

    public static int findIndex(String[] array, String letter){
        int index = -1;
        for(int i = 0; i < array.length; i++){
            if(array[i].equals(letter)){
                index = i;
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        alphabet = "abcdefghijklmnopqrstuvwxyz".split("");
        alphabetReverse = reverse(alphabet);
        alphabetRus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".split("");
        alphabetRusReverse = reverse(alphabetRus);
        symbols = "0123456789 .,;:+-*/`~!@#№$%^&?_\"\'\\<>(){}[]".split("");

        System.out.println("\\\\English and Russian(not in console) letters, symbols and numbers work!");
        System.out.println("\\\\But upper case doesn't work for now.\n");

        System.out.print("Do you want to Encrypt or Decrypt?(E/D): ");
        String answer = br.readLine();
        boolean isEncrypt = (answer.equalsIgnoreCase("e") || answer.equalsIgnoreCase("е"));
        boolean isDecrypt = (answer.equalsIgnoreCase("d") || answer.equalsIgnoreCase("д"));

        if(isEncrypt){
            System.out.print("Print your message to encrypt: ");
            String inputString = br.readLine();
            String[] input = inputString.split("");
            String[] encryptMessage = encrypt(input);
            System.out.println("Encrypted message: " + toString(encryptMessage));
        }
        else if(isDecrypt){
            System.out.print("Print your message to decrypt: ");
            String inputString = br.readLine();
            String[] input = inputString.split("");
            String[] decryptMessage = decrypt(input);
            System.out.println("Decrypted message: " + toString(decryptMessage));
        }
        else System.out.println("Error!");

        System.out.println("\nPress ENTER to exit...");
        System.in.read();
    }
}

