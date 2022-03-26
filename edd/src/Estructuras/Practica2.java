package edd.src.Estructuras;

public class Practica2 {
    
    public static void torresHanoi( int cantidadDiscos,Pila<Integer> origen , Pila<Integer> auxiliar , Pila<Integer> destino ){
        // No olvides imprimir cada paso de la solución.
        if (origen.isEmpty())
            System.out.println("Torre vacia LOL");
        
        int aux = origen.size();

        while( aux != destino.size() ){
            
            if(origen.esPar()){
                mueve(origen,auxiliar);
            }else{
                mueve(origen,destino);
            }
        }
    }

    private static boolean movPermitido( Pila<Integer> pila1 ,  Pila<Integer> pila2 ){
        if( pila1.peek() < pila2.peek() )
            return true;
        return false;
    }

    /**
     * Metodo auxiliar que traslada la cabeza de una pila, al inicio de otra
     * @param pila1 La pila de la que se retira la cabeza
     * @param pila2 La  pila que recibe la nueva cabeza
     */
    private static void mueve( Pila<Integer> pila1 , Pila<Integer> pila2 ){
        if( movPermitido(pila1, pila2) )
            pila2.push( pila1.pop() );
    }

    /**
     * Método auxiliar para determinar si el tamaño de la pila invocadora es par o non
     */
    boolean esPar(){
        if ( (this.size()%2)==0 )
            return true;
        return false;
    }

    public static void binarioColas( int N ){

    }

    public static void main(String[] args) {
        Pila<Integer> prueba = new Pila<Integer>();
        for (int i = 10; i != 0; i--) {
            prueba.push(i);
        }

        // Escribe aqui tu codigo para probar los metodos anteriores. 
        // No olvides comentar tu codigo y escribir tu nombre en el readme. 
    }

}
