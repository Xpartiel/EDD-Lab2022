package edd.src.Estructuras;

import edd.src.Estructuras.PushPop.Nodo;

public class Practica2 {
    
    /**
     * Inspiración:
     * -Solucion de la torre de hanoi con 5 discos
     *      https://www.youtube.com/watch?v=Ov_qt7Ymvrk
     */
    public static void torresHanoi( int cantidadDiscos,Pila<Integer> origen , Pila<Integer> auxiliar , Pila<Integer> destino ){
        // No olvides imprimir cada paso de la solución.

        //Ya que iniciamos con pilas vacías, rellenamos nuestra pila original
        for (int i = cantidadDiscos; 0<i; i--)
            origen.push(i);
        
        //Muestro el estado inicial de las torres de Hanoi
        System.out.println("########INICIO########\n"+origen.toString() +"\n-\n"+auxiliar.toString()+"\n-\n"+destino.toString()+"\n####################\n");
        int movimientos = 0;

        //Identifico que patron debo seguir para llegar a la solucion optima
        if ( esPar( origen ) ){
            while( cantidadDiscos != destino.size() ){
                mueve( origen , auxiliar );
                ++movimientos;
                System.out.println("Movimiento "+movimientos+">>>>>>>>>>>>>>>>>>>>\n"+origen.toString() +"\n-\n"+auxiliar.toString()+"\n-\n"+destino.toString() );
                if( cantidadDiscos == destino.size() )
                    break;
                
                mueve( origen , destino );
                ++movimientos;
                System.out.println("Movimiento "+movimientos+">>>>>>>>>>>>>>>>>>>>\n"+origen.toString() +"\n-\n"+auxiliar.toString()+"\n-\n"+destino.toString() );
                if( cantidadDiscos == destino.size() )
                    break;
                
                mueve( destino , auxiliar );
                ++movimientos;
                System.out.println("Movimiento "+movimientos+">>>>>>>>>>>>>>>>>>>>\n"+origen.toString() +"\n-\n"+auxiliar.toString()+"\n-\n"+destino.toString() );
            }
        }else{
            while( cantidadDiscos != destino.size() ){
                mueve( origen , destino );
                ++movimientos;
                System.out.println("Movimiento "+movimientos+">>>>>>>>>>>>>>>>>>>>\n"+origen.toString() +"\n-\n"+auxiliar.toString()+"\n-\n"+destino.toString() );
                if( cantidadDiscos == destino.size() )
                    break;
                
                mueve( origen , auxiliar );
                ++movimientos;
                System.out.println("Movimiento "+movimientos+">>>>>>>>>>>>>>>>>>>>\n"+origen.toString() +"\n-\n"+auxiliar.toString()+"\n-\n"+destino.toString() );
                if( cantidadDiscos == destino.size() )
                    break;
                
                mueve(destino , auxiliar );
                ++movimientos;
                System.out.println("Movimiento "+movimientos+">>>>>>>>>>>>>>>>>>>>\n"+origen.toString() +"\n-\n"+auxiliar.toString()+"\n-\n"+destino.toString() );
            }
        }
        System.out.println("######## FIN #########\n"+origen.toString() +"\n-\n"+auxiliar.toString()+"\n-\n"+destino.toString()+"\n####################\n");
    }

    /**
     * esPar
     * 
     * Método auxiliar para determinar si el tamaño de la pila invocadora es par o non
     */
    static boolean esPar(Pila<Integer> pila){
        if ( ( pila.size()%2 )==0 )
            return true;
        return false;
    }

    /**
     * movPermitido
     * 
     * Metodo auxiliar para identificar si sera posible realizar un movimiento entre dos pilas,
     * evitando errores imprevistos, y haciendo cumplir las reglas de las torres de hanoi.
     * @param pila1     La pila de la que se retiraria un elemento
     * @param pila2     La pila que recibiria el elemento de la otra pila
     * @return La posibilidad de realizar el movimiento en esta direccion
     */
    private static boolean movPermitido( Pila<Integer> pila1 ,  Pila<Integer> pila2 ){
        if( pila1.isEmpty() && pila2.isEmpty() )
            return false;
        
        if ( pila1.isEmpty() )
            return false;
        if ( pila2.isEmpty() )
            return true;
        if( pila1.peek() < pila2.peek() )
            return true;
        return false;
    }

    /**
     * Metodo auxiliar que traslada la cabeza de una pila, al inicio de otra, de no ser posible, lo
     * intenta en dirección contraria
     * 
     * @param pila1 La pila de la que se retira la cabeza
     * @param pila2 La  pila que recibe la nueva cabeza
     */
    private static void mueve( Pila<Integer> pila1 , Pila<Integer> pila2 ){
        if( movPermitido( pila1 , pila2 ) ){
            pila2.push( pila1.pop() );
            return;
        }
        else if( movPermitido( pila2 , pila1 ) ){
            pila1.push( pila2.pop() );
            return;
        }
    }

    /**
     * binarioColas0
     * 
     * Implementacion de cuenta binaria, tomando cada digito como su propio nodo, y haciendo uso de
     * dos colas para aprovehcar la propiedad fifo del orden de digitos
     * @param N     Cantidad de numeros a mostrar
     */
    public static void binarioColas0( int N ){
        if ( N<0 )
            throw new IllegalArgumentException("Este metodo no está preparado para recibir no-Naturales...");
        /*
        -Puramente Dos Colas-
        Identifica la posición del nodo con el ultimo 0, marcada como "pos"
        Si pos<0
            Almacena la longitud de original en una variable "tama"
            vacía la cola original
            inserta "1" en la cola original
            inserta "0" "tama" veces en original
        Si 0<=pos
            Transfiere "pos" elementos desde la cola original a una auxiliar
            Almacena la nueva longitud de original en una variable "tama"
            vacía la cola original
            inserta "1" en la cola orignal
            inserta "0" ("tama"-1) veces en original
            actualizamos original como original = auxiliar.append( original );
        */
        int tama = 0;
        int posi = 0;
        Cola<Integer> original = new Cola<Integer>();
        Cola<Integer> auxiliar = new Cola<Integer>();
        System.out.println( "0: 0" );
        for (int i=1; i<=N; i++){
            //Divido el numero en dos mitades, tomando como referencia la posicion del ultimo 0
            posi = ultimoCero( original );
            for (int j=0; j<posi; j++)
                auxiliar.push( original.pop() );
            
            //Actualizo la segunda mitad
            tama = original.size();
            original.empty();
            original.push(1);
            // Si no había un cero, estaba llena, por lo que hay que actualizar los numeros en un
            //  espacio de digitos+1
            if ( posi<0 ) {    
                for (int j=0; j<tama; j++)
                    original.push(0);
            }
            // Si había un cero, no estaba llena, por lo que hay que actualizar los numeros en el
            //  mismo espacio de digitos
            else{
                for (int j=1; j<tama; j++)
                    original.push(0);
            }
            // Reformo la cola original, y limpio la auxiliar
            original = auxiliar.append( original );
            auxiliar.empty();
            // Muestro el numero
            System.out.println(i+": "+ cadenaBinaria( original ) );
        }
    }

    /**
     * ultimoCero
     * 
     * Metodo auxiliar para obtener el indice del ultimo nodo con cero en una cola
     * @param colita
     * @return El indice del ultimo nodo que conteniendo un cero, en la cola parametro
     *          Si no existe, se devuelve un valor negativo.
     */
    private static int ultimoCero( Cola<Integer> colita ){
        Nodo aux = colita.cabeza;
        int cero = -2;
        for (int i=0; aux != null; i++){
            if( aux.elemento.equals(0) )
                cero = i;
            aux = aux.siguiente;
        }
        return cero;
    }

    /**
     * cadenaBinaria
     * 
     * Metodo auxiliar con el que representamos la estructura en caracteres de texto
     * @return Representacion textual de la estructura
     */
    public static String cadenaBinaria( Cola colita ){
        String res = "";
        Nodo aux = colita.cabeza;
        while( aux != null ){
            res += aux.elemento.toString();
            aux = aux.siguiente;
        }
        return res;
    }

    /**
     * binarioColas1
     * 
     * Implementacion de cuenta binaria con colas, tras el tip del ayudante de laboratorio
     * @param N La cantidad de numeros a mostrar
     */
    public static void binarioColas1( int N ){
        if ( N<0 )
            throw new IllegalArgumentException("Este metodo no está preparado para recibir no-Naturales...");
        /*
            -TIP DEL AYUDANTE-
            A partir de un previo numero formado, pueden obtenerse posteriores al añadirsele un 0 y un 1,
            uno tras otro, por lo que los nuevos quedan contiguos.
            Puede usarse un auxiliar para identificar al numero que va a modificarse, y al ser previo,
            por principios inductivos, quedarán en orden los nuevos formados respecto a los ya creados

            Es ademas muy util que los datos String ya cuentan con una estructura similar a una dicola,
            con lo que me evito el problema de tener que usar una Cola de Colas de numeros que posterior
            mente mostraré
        }
        */

        //Creo e inicializo la cola
        Cola<String> binaria = new Cola<String>();
        System.out.println( "0: 0" );
        binaria.push("1");
        String temporal = "";
        for (int i=1; i<=N; i++) {
            //Guardar el numero actual
            temporal = binaria.pop();
            System.out.println(i+": "+temporal.toString() );
            //Insertar en la cola el numero con los digitos 0 y 1 añadidos
            binaria.push(temporal+"0");
            binaria.push(temporal+"1");
        }
    }

    public static void main(String[] args) {
        // Escribe aqui tu codigo para probar los metodos anteriores. 

        Pila<Integer> prueba = new Pila<Integer>();
        Pila<Integer> pruebaux = new Pila<Integer>();
        Pila<Integer> pruebadest = new Pila<Integer>();
        
        System.out.println("### TORRE DE 3 DISCOS ###\n\n");
        torresHanoi( 3 , prueba , pruebaux , pruebadest );
        prueba.empty();
        pruebaux.empty();
        pruebadest.empty();

        System.out.println("\n\n### TORRE DE 4 DISCOS ###\n\n");
        torresHanoi( 4 , prueba , pruebaux , pruebadest );
        prueba.empty();
        pruebaux.empty();
        pruebadest.empty();

        /*
        System.out.println("\n\n### CONTANDO EN BINARIO -metodo1- ###\n\n");
        binarioColas0(200);
        System.out.println("\n\n### CONTANDO EN BINARIO -metodo2- ###\n\n");
        binarioColas1(200);*/
    }

}
