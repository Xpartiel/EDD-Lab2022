package edd.src.Automata;

import java.util.Random;
import edd.src.Estructuras.*;

public class Mondrian extends AC {

    int[][] Maux2 = new int[Imagen.numCells][Imagen.numCells];
    int[][] MauxCopia = new int[Imagen.numCells][Imagen.numCells];
    int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];

    

    /*
     * Metodo que pinta una matriz inicial de Blanco y le da valores aleatorios a las
     * casillas.
     *
     */
    @Override
    public int[][] getAutomata() {
        int aux1; 
        //Inicializo dos matrices en blanco
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = 2;
                MauxCopia[i][j] = 2;
            }
        }
        // Modifico cada valor de la matriz Maux de forma aleatoria. Para empezar con un estado random de colores
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {
                   
                aux1 = (int) (Math.random() * 14); // Random del 0 al 12

                if (aux1 <= 1) {
                    Maux2[i][j] = 4;
                } else if (aux1 > 3 && aux1 <= 5) {
                    Maux2[i][j] = 5; 
                } else if (aux1 > 5 && aux1 <= 7) {
                    Maux2[i][j] = 3; 
                } else if (aux1 > 6 && aux1 <= 8) {
                    Maux2[i][j] = 6; 
                } else {
                    Maux2[10][27]=3;
                    Maux2[27][27]=3;
                    
                    Maux2[10][5]=1;
                    Maux2[i][j] = 2; 
                }
            }
        }
        return Maux2;
    }

    /*
     * Metodo para evolucionar el automata.
     *
     */
    @Override
    public void evoluciona() {

        // Se crea una matriz copia para reemplazar los Valores.
        int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];
        
        // Aqui empieza una evolucion.

        //Creamos una pila y una cola para que te diviertas joven Artista. 
        Pila<Integer> pila = new Pila<Integer>();
        Cola<Integer> cola = new Cola<Integer>();
        //ran1 y ran2 genera randoms enteros para rellenar la pila y la cola
        int ran1,ran2,ran3,ranPosi;
        ran3=(int)(Math.random()*2);
        
      
            ran1= (int)(Math.random()*2+2);
            pila.push(ran1);
            System.out.println(pila.toString()+"\n");
            ran2= (int)(Math.random()*2);
            cola.push(ran2);
            ranPosi=(int)(Math.random()*9);
            ran3=(int)(Math.random()*9);
            ranPosi=(int)(Math.random()*5+1);
            //ranPosi-ran3;
            //

        
        //La matriz Maux2 Contiene el estado actual de la matriz.
        //La matriz CopiaM es una matriz copia de Maux2 donde debes poner los nuevos valores
        // Aqui va tu codigo


        //metodo para crear un juego de la vida o algo asi con von neumann o moore
        for (int i = 0; i < (CopiaM.length); i++) {
            for(int j =0; j < (CopiaM.length); j++){
                //creacion de nuestra propia vecindad

                
                //vecindad de moore
                for (int k=i-1;k<=i+1;k++) {
                    for (int l=j-1;l<=j+1;l++) {
                        
                      
                        //nos aseguramos que la posicion a comparar esta dentro de la matriz
                        if (k>0&&l>0&&k<Maux2.length&&l<Maux2.length&&(k!=i|| l!=j)) {
                            if(i>0&&i<CopiaM.length){
                                Maux2[5][15]=9;
                                if(i%2==0){
                                    if(j==30){
                                        CopiaM[CopiaM.length-1][CopiaM.length-1]=3;
                                        pila.push(14);
                                    }
                                }
                            }
                            if(Maux2[k][l]==4){
                                pila.push(10);
                            }
                            if(Maux2[k][l]==3){
                                pila.push(14);
                            }
                            if(Maux2[k][l]==0){
                                pila.push(11);
                            }

                            if(Maux2[k][l]==1){
                                pila.push(13);
                            }
                            if(Maux2[k][l]==7){
                                pila.push(15);
                            }
                            if(Maux2[k][l]==9){
                                pila.push(16);
                            }


                           
                        }
                    }
                }

                
          //negro blanco azulm cafe amarillo rojo cyan  green dark gray orange
            if(!pila.isEmpty()&&pila.size()>15){
                //negro
           //blanco
                if(pila.pop()==1){
                    CopiaM[i][j]=1;
                }
                //cyan
                if(pila.pop()==6){
                    CopiaM[i][j]=6;
                }
                
                //azulM
                if(pila.pop()==2){
                    CopiaM[i][j]=2;
                }
                //dark gray
                if(pila.pop()==8){
                    CopiaM[i][j]=8;
                } 
                
                if(pila.pop()==0){
                    CopiaM[i][j]=0;
                }
                //cafe   
                if(pila.pop()==3){
                    CopiaM[i][j]=6;
                }
                //amarillo
                if(pila.pop()==4){
                }
                if(pila.pop()==10){
                }
                
                //lluvia
                if(pila.pop()==11){
                   if(i%2==0){
                        CopiaM[i][j]=6;
                        pila.push(13);
                        

                }
                //nubes
                if(pila.pop()==13){
                    if(i>5&&i<CopiaM.length-5){
                        if(j>5&&j<CopiaM.length-5){
                                if(j<10){
                                        
                                    CopiaM[i][j]=1;
                                    CopiaM[i][j-2]=1;
                                    CopiaM[i+2][j-3]=1;
                                    CopiaM[i+2][j-1]=1;
                                    CopiaM[i+1][j-1]=1;
                                    CopiaM[i+1][j]=1;
                                    CopiaM[i-1][j]=1;
                                    CopiaM[i][j-2]=1;
                                    CopiaM[i][j+2]=8;
                                    CopiaM[i-1][j+1]=8;

                                    
                                }
                        }
                    }
                }
                
                //ramas
                if(pila.pop()==14){
                    if(i>3&&i<CopiaM.length-3){
                        if(j>25&&j<CopiaM.length-3){
                            CopiaM[i][j]=7;
                            CopiaM[i-1][j]=7;
                            CopiaM[i][j]=7;
                            CopiaM[i+1][j]=7;
                            CopiaM[i][j-1]=7;
                            CopiaM[i-1][j+1]=7;
                            CopiaM[i-1][j-2]=7;
                            CopiaM[i-2][j-2]=7;
     
                        }
                    }
                }
                //sombra ramas
                if(pila.pop()==15){
                if(i>3&&i<CopiaM.length-3){
                    if(j>25&&j<CopiaM.length-3){
                    CopiaM[i-2][j]=4;
                    CopiaM[i+1][j]=4;
                    CopiaM[i][j-2]=4;
                    CopiaM[i-2][j+2]=4;
                    CopiaM[i-2][j-3]=4;
                    CopiaM[i-3][j-3]=4;
                    }
                }  
                }
                //corazon?
                if(pila.pop()==15){
                    if(i>3&&i<20){
                        if(j>10&&j<25){
                            CopiaM[i-1][j-1]=9;
                            CopiaM[i+1][j-1]=9;

                            CopiaM[i-2][j]=9;
                            CopiaM[i-1][j]=9;
                            CopiaM[i][j]=9;
                            CopiaM[i+1][j]=9;
                            CopiaM[i+2][j]=9;

                            CopiaM[i-1][j+1]=9;
                            CopiaM[i][j+1]=9;
                            CopiaM[i+1][j+1]=9;

                            CopiaM[i][j+2]=9;

                        }
                    }  
                    }

                

                        
                        
                    
                }
            }
                




            }
        }
        //moore o von neumann








    //amungus xd
        //contorno
        for (int i = 0; i < (CopiaM.length); i++) {
            for(int j =0; j < (CopiaM.length); j++){
                if(i>15+ranPosi&&i<25+ranPosi){
                    if (j==10+ranPosi) {
                        CopiaM[i][j]=2;
                    }
                }

                if(i==15+ranPosi||i==25+ranPosi){
                    if (j==11+ranPosi||j==25+ranPosi) {
                        CopiaM[i][j]=2;
                    }
                }

                if(i==14+ranPosi||i==26+ranPosi){
                    if (j==12+ranPosi||j>17+ranPosi&&j<25+ranPosi) {
                        CopiaM[i][j]=2;
                    }
                }
                
                if(i==13+ranPosi||i==14+ranPosi||i>19+ranPosi && i<27+ranPosi){
                    if (j==13+ranPosi) {
                        CopiaM[i][j]=2;
                    }
                }

                if(i==13+ranPosi){
                    if (j==22+ranPosi) {
                        CopiaM[i][j]=2;
                    }
                }

                if(i>17+ranPosi&&i<22+ranPosi){
                    if(j==23+ranPosi){
                        CopiaM[i][j]=2;
                    }
                }
                if(i==17+ranPosi||i==22+ranPosi){
                    if(j==24+ranPosi||j==25+ranPosi){
                        CopiaM[i][j]=2;
                    }
                }

                
                
                if(i==12+ranPosi||i==14+ranPosi){
                    if (j>13+ranPosi&&j<22+ranPosi) {
                        CopiaM[i][j]=2;
                    }
                }

                if(i==16+ranPosi||i==17+ranPosi||i>21+ranPosi&&i<25+ranPosi){
                    if(j==26+ranPosi){
                        CopiaM[i][j]=2;
                    }
                }

                if(i==19+ranPosi||i==27+ranPosi){
                    if (j>13+ranPosi&&j<17+ranPosi) {
                        CopiaM[i][j]=2;
                    }
                }
                
                if(i>19+ranPosi&&i<27+ranPosi){
                    if (j==17+ranPosi) {
                        CopiaM[i][j]=2;
                    }
                }


            }    
        
        }

    //relleno del amugus

        for (int i = 0; i < (CopiaM.length); i++) {
            for(int j =0; j < (CopiaM.length); j++){
                

    //sombras 
        //blanco
            if(i==24+ranPosi||i==25+ranPosi||i==26+ranPosi){
                if (j==14+ranPosi) {
                    CopiaM[i][j]=1;

                }
            }

       //azul claro
            if(i>19+ranPosi&&i<24+ranPosi){
                if (j==14+ranPosi) {
                    CopiaM[i][j]=11;

                }
            }
            if(i>20+ranPosi&&i<27+ranPosi){
                if (j==15+ranPosi) {
                    CopiaM[i][j]=11;

                }
            }
            //azul sombra
            if(i>19+ranPosi&&i<27+ranPosi){
                if (j==16+ranPosi) {
                    CopiaM[i][j]=12;
                    CopiaM[20+ranPosi][15+ranPosi]=12;

                }
            }







        //cuerpo
                if(i>15+ranPosi&&i<25+ranPosi){
                    if (j==11+ranPosi) {
                        CopiaM[i][j]=10;
                       

                    }
                }
                if(i>15+ranPosi&&i<25+ranPosi){
                    if (j==12+ranPosi) {
                        CopiaM[i][j]=10;
                       

                    }
                }

                
                if(i==15+ranPosi||i==25+ranPosi){
                    if (j==12+ranPosi) {
                        CopiaM[i][j]=10;
                    }
                }
                if(i==13+ranPosi||i>14+ranPosi&&i<19+ranPosi){
                    if (j>13+ranPosi&&j<22+ranPosi) {
                        CopiaM[i][j]=10;
                    }

                }
                if(i>14+ranPosi&&i<26+ranPosi){
                    if (j>17+ranPosi&&j<23+ranPosi) {
                        CopiaM[i][j]=10;
                    }

                }
                if(i>14+ranPosi&&i<17+ranPosi){
                    if (j>=23+ranPosi&&j<25+ranPosi) {
                        CopiaM[i][j]=10;
                    }

                }
                if(i>22+ranPosi&&i<25+ranPosi){
                    if (j>=23+ranPosi&&j<26+ranPosi) {
                        CopiaM[i][j]=10;
                    }

                }

                if(i>14+ranPosi&&i<20+ranPosi){
                    if(j==13+ranPosi){
                        CopiaM[i][j]=10;
                    }
                }
                if(i==17+ranPosi||i==22+ranPosi||i==25+ranPosi){
                    if(j==23+ranPosi){
                        CopiaM[i][j]=10;
                    }
                }
                if(i==25+ranPosi){
                    if(j==24+ranPosi){
                        CopiaM[i][j]=10;

                        CopiaM[i-9+ranPosi][j+1+ranPosi]=10;

                    }
                }
                if(i==19+ranPosi){
                    if(j==17+ranPosi){
                    CopiaM[i][j]=10;
                    }
                }

        //rojo sombra

            if(i==15+ranPosi){
                if (j>=12+ranPosi&&j<25+ranPosi) {
                    CopiaM[i][j]=13;

                }
            }
            if(i==16+ranPosi){
                if (j==11+ranPosi) {
                    CopiaM[i][j]=13;

                }
            }
            if(i==13+ranPosi){
                if (j>15+ranPosi&&j<22+ranPosi) {
                    CopiaM[i][j]=13;

                }
            }
            if(i>15+ranPosi&&i<26+ranPosi){
                if (j==22+ranPosi) {
                    CopiaM[i][j]=13;

                }
            }
            if(i==16+ranPosi){
                if (j>=22+ranPosi&&j<26+ranPosi) {
                    CopiaM[i][j]=13;

                }
            }
            
            if(i>22+ranPosi&&i<25+ranPosi){
                if(j>21+ranPosi&&j<26+ranPosi){
                  CopiaM[i][j]=13;

                }  
            }

            //cuadros de sombra roja especificos
                CopiaM[16+ranPosi][25+ranPosi]=13;
                CopiaM[17+ranPosi][23+ranPosi]=13;
                CopiaM[25+ranPosi][24+ranPosi]=13;
                CopiaM[25+ranPosi][23+ranPosi]=13;
                CopiaM[22+ranPosi][23+ranPosi]=13;
            //UwU
            CopiaM[20+ranPosi][18+ranPosi]=9;
            CopiaM[26+ranPosi][18+ranPosi]=9;
            CopiaM[19+ranPosi][17+ranPosi]=14;
            CopiaM[25+ranPosi][17+ranPosi]=14;


            

                

            }
        }





        
    
    

        for (int i = 0; i < Maux2.length; i++) { // Fors que arreglan la matriz a regresar en la copia.
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = CopiaM[i][j];
            }
        }
        // System.out.println("Termine");//SOP que ayuda a saber cuando acaba una
        // evolucion.
    }

    public int[][] getAutomata2() {
        return Maux2;
    }
}



/**
 * for (int j = 0; j < CopiaM.length; j++) {
                        
                    int aux3= (int) Math.random() * 5;

                        if(j%3==0){
                            pila.push(aux3); // Random del 0 a 4
                            CopiaM[i][j]=pila.pop();
                        }else{
                            cola.push((int) Math.random()*5);
                            CopiaM[i][j]=cola.pop();
                        }
                        
                    
                        
                
            
                }
 */