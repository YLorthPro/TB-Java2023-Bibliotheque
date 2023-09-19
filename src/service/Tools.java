package service;

import java.util.Scanner;

public class Tools {

    public static int demanderNB(int min, int max){

        Scanner sc = new Scanner (System.in);
        int nbUser=0;
        boolean entreeOK;

        do {
            try {
                nbUser = Integer.parseInt(sc.nextLine());
                if((nbUser>min-1)&&(nbUser<max+1)){
                    entreeOK=true;
                }else{
                    System.out.println("Ce nombre n'est pas compris entre " + min + " et " + max);
                    entreeOK=false;
                }

            } catch(NumberFormatException e){
                System.out.println("Ce n'est pas un nombre. Réessayez!");
                entreeOK=false;
            }
        }while(!entreeOK);
        return nbUser;
    }

    public static boolean ouiOuNon(){
        //fonction oui ou non
        Scanner sc = new Scanner (System.in);
        boolean choixU=false, repeat;
        String temp;

        do{
            System.out.println("Oui (O) ou non(N)");
            temp= sc.nextLine();
            if((((temp.charAt(0))==('o'))||((temp.charAt(0))==('O')))&&(temp.length()==1)){
                choixU=true;
                repeat=false;
            }else if((((temp.charAt(0))==('n'))||((temp.charAt(0))==('N')))&&(temp.length()==1)){
                repeat=false;
            }else{
                System.out.println("Choix invalide");
                repeat=true;
            }
        }while (repeat);
        return choixU;
    }

}
