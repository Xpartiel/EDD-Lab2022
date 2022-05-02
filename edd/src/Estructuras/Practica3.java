package edd.src.Estructuras;

import java.lang.Math;
import java.util.Random;
import java.util.Comparator;

public class Practica3 {
    
    /**
     * sumaCercana
     * 
     * Método que recibe una lista de enteros, y un entero n. Encontrará la pareja de números de la
     * lista tal que la suma de estos, sea la mas cercana a n.
     * 
     * Complejidad de tiempo de O(nlogn)
     * @param lista La lista en la cual se buscaran los numeros
     * @param N     El numero al que debe aproximarse la suma de un par de numeros
     */
    public static void sumaCercana(Lista<Integer> lista, int N){

        Lista<Integer> inviertida = lista.clone();
        inviertida.reverse();
        
        IteradorLista iteraA = lista.iteradorLista();
        IteradorLista iteraB = inviertida.iteradorLista();

        int comp1 = 0, comp2 = 0, suma = 0;
        int salv1 = 0, salv2 = 0;
        int difSalvada=( (int)(Math.pow(2, 31)-1) );
        int difTemp = 0;
        System.out.println("Se busca la suma mas cercana a "+N+" con un par de elementos de la lista:\n"+lista.toString());
        //Recorrer la lista original, elemento a elemento
        for(int i=0; i<lista.size(); i++){
            //Obtener el elemento actual
            if( iteraA.hasNext() )
                comp1 = (Integer)iteraA.next();
            //Ignorar este elemento entre los elementos con que se comparara el numero uno
            inviertida.pop();

            //Nos aseguramos que el iterador de la lista secundaria se haya reiniciado
            iteraB.start();
            //Recoorrer la lista secundaria elemento a elemento
            for (int j=0; j<inviertida.size(); j++) {
                //Obtener el elemento actual de la lista secundaria
                if( iteraB.hasNext() )
                    comp2 = (Integer)iteraB.next();

                //Obtener esta suma
                suma = comp1+comp2;
                difTemp = Math.abs( N - suma );
                //Verificar si la diferencia absoluta es menor a la previa salvada
                if( difTemp < difSalvada ){
                    //Actualizar datos guardados
                    difSalvada = difTemp;
                    salv1 = comp1;
                    salv2 = comp2;
                }

                //No hay diferencia, asi que 
                if( difSalvada==0 ){
                    System.out.println(" Se ha encontrado la suma mas cercana: "+salv1+"+"+salv2+" = "+(salv1+salv2));
                    return;
                }
            }
        }
        System.out.println(" La suma mas cercana encontrada es: "+salv1+"+"+salv2+" = "+(salv1+salv2));
    }

    static Pila<String> pilaString = new Pila<String>();

    public static void permutacionesCadena(String cadena){
        if ( cadena==null || cadena.length()<1 ) {
            throw new IllegalArgumentException("Cadena invalida, se requiere almenos un caracter");
        }
        intentoRecursivo( cadena , pilaString );
        pilaString.empty();
    }
    /**
     * 
     * @param cadena
     * @param letras
     */
    private static void intentoRecursivo(String cadena ,  Pila<String> letras){
        //Fin de la rama
        if( cadena.length()<2 ){
            letras.push( ""+cadena.charAt(0) );
            System.out.println( letras.reverse().customString() );
            if( !letras.isEmpty() )
                letras.pop();
        //Decisiones en orden por diseño backtrack
        }else{
            for (int i=0; i<cadena.length(); i++) {
                
                //Insertar la letra seleccionada a una pila, para su interpretacion final por backtracking
                letras.push( ""+cadena.charAt(i) );
                //Remover la letra tomada, a modo de avance de la variable recursiva
                intentoRecursivo( cadena.replaceFirst(""+cadena.charAt(i), "" ) , letras );
                if( !letras.isEmpty() )
                    letras.pop();
            }
        }
    }

    
    private static int[] cribaEratostenes( int primo , int suma ){
        if( suma < primo ){
            throw new IllegalArgumentException("Imposible obtener primos mayores a la suma, que sumados den la suma");
        }

        Lista<Integer> numeros = new Lista<Integer>();
        int i=2 , limite=0 , factor=0;
        for ( i=suma; 1<i; i-- )
            numeros.agregaFinal(i);

        Pila<Integer> primos = new Pila<Integer>();
        while( !numeros.isEmpty() ){
            primos.push( numeros.pop() );
            factor = primos.peek();

            limite = (suma/factor)+1;
            for ( i=2; i<limite; i++)
                numeros.delete( i*factor );
            
        }

        primos = primos.reverse();
        while( primos.peek()<=primo ){
            primos.pop();
        }
        int[] devuelto = new int[primos.size()];
        for ( i=0; i<devuelto.length; i++) {
            devuelto[i] = primos.pop();
        }
        return devuelto;
    }
    public static void primosQueSuman(int S, int P, int N){
        //cosas para imprimir en pantalla los primos
        int[] primos = cribaEratostenes(P, S);
        System.out.println("Dados P= "+P+" S= "+S+" N= "+N );
        System.out.print(P+"<");
        for (int i = 0; i < primos.length; i++) {
            System.out.print(primos[i]);
            if(i<primos.length-1){
                System.out.print(",");
            }
        }

    }
    /*
    private static void primoRecursivo(Pila<Integer> pila, int[] primosRes, int N, int suma ){//*****
        //caso base
        if( N<=pila.size() ){
            if( sumatoria(pila) != suma ){
                System.out.println(pila.toString()+" No era una solucion");
            }else{
                System.out.println(pila.toString()+" Es una solucion");
            }
            
        }else{
            int i=0;
            Lista<Integer> modificado = new Lista<Integer>();
            for ( ; i<primosRes.length; i++) {
                modificado.add( primosRes[i] );
            }
            int[] menosPrimos = new int[primosRes.length-1];
            int dif=0;
            for ( i=0; i<primosRes.length; i++) {
                //Remover el numero tomado, a modo de avance de la variable recursiva

                for (int j=0; j<primosRes.length; j++) {
                    if(i==j){
                        dif=-1;
                        continue;
                    }
                    menosPrimos[j+dif] = primosRes[j];
                }
            }
            //llamada recursiva

            //decremento
        }

    }*/

    public static void N_Reinas(int N){
        if(N==1)
            System.out.println("Respuesta trivial: [1]");
        
        if(N<4)
            throw new IllegalArgumentException("Tableros irresolubles");

        int[][] tablero = new int[N][N];
        reinasRecursivas( tablero , 0 );
    }
    static void reinasRecursivas( int[][] tablero , int hilera ){
        //Caso Base
        if ( tablero.length <= hilera ){
            muestraArreglo( tablero );
            System.out.println(" ");
        }
 
        //Caso Ramificacion
        for (int i=0; i<tablero.length; i++){
            if ( celdaValida( i , hilera , tablero )) {
                //Colocar la reina
                tablero[i][hilera] = 1;
                //Tomar un elemento para avance recursivo
                reinasRecursivas( tablero , hilera+1 );
                //Quitar la reina
                tablero[i][hilera] = 0;
            }
        }
    }
    /*Metodo auxiliar para mostrar el arreglo*/
    private static void muestraArreglo( int arreglo[][] ){
        for (int i=0; i<arreglo.length; i++) {
            for (int j = 0; j<arreglo.length; j++)
                System.out.print(arreglo[i][j]+ " ");
            System.out.println(" ");
        }
    }
    private static boolean celdaValida( int fila , int columna , int tablero[][] ){
        int i=0, j=0;
 
        //Verificar que no haya una reina atacando horizontalmente a celda
        for ( i=0; i<tablero.length; i++){
            if ( tablero[fila][i] == 1 && i!=columna)
                return false;
        }
        //Verificar que no haya una reina atacando en diagonal la celda
        return compruebaDiagonales(fila, columna, tablero);
    }
    private static boolean compruebaDiagonales( int fila , int columna , int[][] tablero ){
        for (int i=0; i<tablero.length; i++) {
            //Buscar reina atacando en diagonal \
            if( -1<(fila-i) && (fila-i)!=fila ){
                if( -1<(columna-i) && (columna-i)<tablero.length){
                    if( tablero[fila-i][columna-i]==1 )
                        return false;
                }
            }
            //Buscar reina atacando en la diagonal /
            if( (fila+i)<tablero.length && (fila+i)!=fila ){
                if( -1<(columna-i) && (columna-i)<tablero.length){
                    if( tablero[fila+i][columna-i]==1 )
                        return false;
                }
            }
        }
        return true;
    }

    /*Pruebas*/
    public static void main( String[] args) {
        Lista<Integer> lista = new Lista<Integer>();
        Random ran = new Random(1900);
        for (int i=0; i<20; i++) {
            lista.add( ran.nextInt(50) );
        }
        sumaCercana(lista, 23);
        System.out.println( "\n" );
        permutacionesCadena("ABCD");
        System.out.println( "\n" );
        N_Reinas(10);
    }
}