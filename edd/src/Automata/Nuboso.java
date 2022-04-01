package edd.src.Automata;

import java.util.Random;
import edd.src.Estructuras.*;

import java.lang.Math;

public class Nuboso extends AC {

    int[][] Maux2 = new int[Imagen.numCells][Imagen.numCells];
    int[][] MauxCopia = new int[Imagen.numCells][Imagen.numCells];
    int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];
    
    private Cola<Integer> colaInicial(){
        Cola<Integer> colaIni = new Cola<Integer>();
        Random aleatorio = new Random(30032022);
        int veces = Maux2.length * Maux2.length;
        for (int i=0; i<veces; i++)
            colaIni.push( aleatorio.nextInt(101) );
        
        return colaIni;
    }

    private static Cola<Integer> interpreta( Cola<Integer> esta ){
        Cola<Integer> nueva = new Cola<Integer>();
        int numero = 0;
        while ( !esta.isEmpty() ) {
            numero = esta.pop();
            if ( numero < 21 ){
                nueva.push(15);
                continue;
            }
            else if ( numero < 41 ){
                nueva.push(16);
                continue;
            }
            else if( numero < 61 ){
                nueva.push(17);
                continue;
            }
            else if( numero < 81 ){
                nueva.push(18);
                continue;
            }
            else{
                nueva.push(19);
            }
        }
        return nueva;
    }

    Cola<Integer> colaColores = interpreta( colaInicial() );

    

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
                Maux2[i][j] = 17;
                MauxCopia[i][j] = 17;
            }
        }
        // Modifico cada valor de la matriz Maux de forma aleatoria. Para empezar con un estado random de colores
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {

                aux1 = 15+ (int) (Math.random() * 14); // Random del 0 al 12

                if (aux1 <= 15+1) {
                    Maux2[i][j] = 18;
                } else if (aux1 > 15+3 && aux1 <= 15+5) {
                    Maux2[i][j] = 16;
                } else if (aux1 > 15+5 && aux1 <= 15+7) {
                    Maux2[i][j] = 15;
                } else if (aux1 > 15+6 && aux1 <= 15+8) {
                    Maux2[i][j] = 19;
                } else {
                    Maux2[i][j] = 17;
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
            //Yo la creo como cola de clase y no de metodo, porque si no, se queda parado
            // y ya no hace nada , no se por que, pero asi pasa
        
        int i=0;
        int j=0;

        // Aqui va tu codigo
        for (i = 0; i<Maux2.length; i++) {
            for (j=0; j<Maux2.length; j++) {

                if( (2<i && 2<j) && i<(Maux2.length-3) && j<(Maux2.length-3)){

                    //Conservar los colores actuales del patron caballo
                    colaColores.push( Maux2[i+2][j-1] );
                    colaColores.push( Maux2[i-2][j-1] );
                    colaColores.push( Maux2[i+2][j+1] );
                    colaColores.push( Maux2[i-2][j+1] );
                    colaColores.push( Maux2[i+1][j+2] );
                    colaColores.push( Maux2[i+1][j-2] );
                    colaColores.push( Maux2[i-1][j+2] );
                    colaColores.push( Maux2[i-1][j-2] );

                    //Patrones prometedores encontrados:

                    /*24136857 */ //
                    CopiaM[i+2][j-1] = colaColores.pop(); //2
                    CopiaM[i-2][j+1] = colaColores.pop(); //4
                    CopiaM[i-1][j+2] = colaColores.pop(); //1
                    CopiaM[i+1][j-2] = colaColores.pop(); //3
                    CopiaM[i-2][j-1] = colaColores.pop(); //6
                    CopiaM[i+2][j+1] = colaColores.pop(); //8
                    CopiaM[i+1][j+2] = colaColores.pop(); //5
                    CopiaM[i-1][j-2] = colaColores.pop(); //7
                    // */

                    /*24681357 * /
                    CopiaM[i+2][j-1] = colaColores.pop(); //2
                    CopiaM[i-2][j+1] = colaColores.pop(); //4
                    CopiaM[i-2][j-1] = colaColores.pop(); //6
                    CopiaM[i+2][j+1] = colaColores.pop(); //8
                    CopiaM[i-1][j+2] = colaColores.pop(); //1
                    CopiaM[i+1][j-2] = colaColores.pop(); //3
                    CopiaM[i+1][j+2] = colaColores.pop(); //5
                    CopiaM[i-1][j-2] = colaColores.pop(); //7
                    // */

                    /*13572468  * /
                    CopiaM[i-1][j+2] = colaColores.pop(); //1
                    CopiaM[i+1][j-2] = colaColores.pop(); //3
                    CopiaM[i+1][j+2] = colaColores.pop(); //5
                    CopiaM[i-1][j-2] = colaColores.pop(); //7
                    CopiaM[i+2][j-1] = colaColores.pop(); //2
                    CopiaM[i-2][j+1] = colaColores.pop(); //4
                    CopiaM[i-2][j-1] = colaColores.pop(); //6
                    CopiaM[i+2][j+1] = colaColores.pop(); //8
                    // */
                    
                    /*13245768 * /
                    CopiaM[i-1][j+2] = colaColores.pop(); //1
                    CopiaM[i+1][j-2] = colaColores.pop(); //3
                    CopiaM[i+2][j-1] = colaColores.pop(); //2
                    CopiaM[i-2][j+1] = colaColores.pop(); //4
                    CopiaM[i+1][j+2] = colaColores.pop(); //5
                    CopiaM[i-1][j-2] = colaColores.pop(); //7
                    CopiaM[i-2][j-1] = colaColores.pop(); //6
                    CopiaM[i+2][j+1] = colaColores.pop(); //8
                    // */

                    /*57682413  * /
                    CopiaM[i+1][j+2] = colaColores.pop(); //5
                    CopiaM[i-1][j-2] = colaColores.pop(); //7
                    CopiaM[i-2][j-1] = colaColores.pop(); //6
                    CopiaM[i+2][j+1] = colaColores.pop(); //8
                    CopiaM[i+2][j-1] = colaColores.pop(); //2
                    CopiaM[i-2][j+1] = colaColores.pop(); //4
                    CopiaM[i-1][j+2] = colaColores.pop(); //1
                    CopiaM[i+1][j-2] = colaColores.pop(); //3
                    // */                    
                    
                    colaColores.push( Maux2[i-1][j-2] );
                    CopiaM[i-1][j-2] = colaColores.pop();
                }
            }
        }
        //La matriz Maux2 Contiene el estado actual de la matriz.
        //La matriz CopiaM es una matriz copia de Maux2 donde debes poner los nuevos valores
        
        for (i = 0; i < Maux2.length; i++) { // Fors que arreglan la matriz a regresar en la copia.
            for (j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = CopiaM[i][j];
            }
        }
    }

    public int[][] getAutomata2() {
        return Maux2;
    }
}