/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Importation;

/**
 *
 * @author anas
 */
public class Vignere  {
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

     // cryptage/ Décryptage de la méthode Vignère
    public int [] Codagenumv (char [] tab){
        int[] codenumm = new int[tab.length];
        for(int i =0;i<tab.length;i++){
            if(tab[i]==' ')
                codenumm[i]=' ';// pour les espaces
            else codenumm[i]=(tab[i]-'A')%26;
        }
        return codenumm;
    }
    public char [] Cryptagev (int []tab,int [] cle){
        int[] tab1 = new int[tab.length];
        char[] codechm = new char[tab.length];
        for(int i=0;i<tab.length;i++){
            if(tab[i]!=' ')
            {
                tab1[i]=(tab[i]+cle[i%(cle.length)])%26;
                if(tab1[i]==0) codechm[i]='Z';
                else codechm[i]=alphabet[(tab1[i]-1)%26];  
            }
                else  codechm[i]=' ';
        }
        return codechm;
    }
    public char [] Decryptage (int []tab,int [] cle){
        int[] tab1 = new int[tab.length];
        char[] codechm = new char[tab.length];
        for(int i=0;i<tab.length;i++){
            if(tab[i]!=' ')
            {
                tab1[i]=(tab[i]-cle[i%(cle.length)])%26;
                if(tab1[i]<0){tab1[i]=26+tab1[i]; }
                if(tab1[i]==0) codechm[i]='Z';
                else codechm[i]=alphabet[(tab1[i]-1)%26];  
            }
                else  codechm[i]=' ';
        }
        return codechm;
    }
}
