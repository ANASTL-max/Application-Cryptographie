/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Importation;


import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author anas
 */
public class RSAfin  {
    //Implémentaion de la substitution par Cesar
     public static final char [] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
                              'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
  
  public int [] Codagenum (String tabc){
        int[] codenumm = new int[tabc.length()]; 
        char [] tab = tabc.toCharArray();
        for(int i =0;i<tab.length;i++){
            if(tab[i]==' ')
                codenumm[i]=' ';// pour les espaces
            else codenumm[i]=(tab[i]-'A'+1)%26;
        }
        return codenumm;
    }
    // Cryptage du texte clair
    public char [] Cryptage (int []tab,int cle){
          char[] codechm = new char[tab.length];
        int[] tab1 = new int[tab.length];
        for(int i=0;i<tab.length;i++){
            if(tab[i]!=' ')
            {   tab1[i]=(tab[i]+cle)%26;
                if(tab1[i]==0) codechm[i]='Z'; // en affecte a tab1 le code 25 par ce que 26%26 done 0 pour un clé de 17
                else codechm[i]=alphabet[(tab1[i]-1)%26];   // cle 10 pour T OUI O = 15 Y = 25 mais dans alphabet Y = 24
            }
                else  codechm[i]=' ';
        }
        
        return codechm;
    }
    // Decryptage de texte chifre
    public char [] Decryptage (int []tab,int cle,char [] proz){
        char[] codechm = new char[tab.length];
        int[] tab1 = new int[tab.length];
        for(int i=0;i<tab.length;i++){
            if(proz[i]==' ' )
                     codechm[i]=' ';
            else { tab1[i]=(tab[i]+(26-cle))%26;
             if(tab1[i]==0) codechm[i]='Z';
                  else
                   codechm[i]=alphabet[(tab1[i]-1)%26]; // cle 10 pour T OUI O = 15 Y = 25 mais dans alphabet Y = 24
            }  
        }
        return codechm;
    }
         // cryptage/ Décryptage de la méthode RSA
    public static int pgcd(int e, int key) {
        int res;
        do {
            res = e % key;
            e = key;//décalage pour réaliser euclide
            key = res;//décalage pour réaliser euclide
        } while (key != 0);//tant que le reste est non nul
        return e;
    }

 public static int inverse1(int a,int q){
         int p=0;
        for(int i=1;i<q;i=i+2){
            if((a*i)%q==1) { p=i; break;}
    }
        return p;
     }

    public int rsa_cleE(int Q) {
        int e = 0;
        Random a = new Random();
        for (int i = 1; i < Q; i++) {
            e = a.nextInt(Q);
            if (pgcd(e, Q) == 1) {
                break;
            }
        }
        return e;
    }

    public int rsa_cleD(int e, int Q, int n) {

        int d = inverse1(e, Q) % n;
        return d;
    }

    public BigInteger[] cry_rsa(int[] tab, int cleE, int cleN) {
        String c = String.valueOf(cleN);
        BigInteger n = new BigInteger(c);

        BigInteger[] d = new BigInteger[tab.length];
        for (int i = 0; i < tab.length; i++) {
            d[i] = BigInteger(tab[i]);
        }
        for (int i = 0; i < tab.length; i++) {
            d[i] = d[i].pow(cleE);
            d[i] = d[i].mod(n);
        }
        return d;
    }

    public char[] Decry_rsa(BigInteger[] tab, int cleD, int cleN) {
        char[] codechm = new char[tab.length];
        String c = String.valueOf(cleN);
        BigInteger n = new BigInteger(c);
        BigInteger[] d = new BigInteger[tab.length];
        for (int i = 0; i < tab.length; i++) {
            d[i] = tab[i];
        }
        int[] j = new int[tab.length];
        for (int i = 0; i < tab.length; i++) {
            d[i] = d[i].pow(cleD);
            d[i] = d[i].mod(n);
            j[i] = d[i].intValue();
            

            if (j[i] == 0) {
                codechm[i] = 'Z';
            } else if (j[i] == 32) {
                codechm[i] = ' ';
            } else {
                codechm[i] = alphabet[j[i] - 1];
            }
        }

        return codechm;
    }

    private BigInteger BigInteger(int i) {
        String a = String.valueOf(i);
        BigInteger d = new BigInteger(a);
        return d;
    }
}
