package edd.src.Estructuras;

/**
 * @author Uriel Balderas Aguilar
 * @author Luis Ernesto Hernandez Rosas
*/

import edd.src.Estructuras.IteradorLista;
import edd.src.Estructuras.Lista;

import java.util.Iterator;

public class Practica1 {
    

    /**
     * AgregaOrdenado
     * 
     * Recorre la lista en orden con un iterador, actualizando paso a paso el valor de 
     * @param lista     La lista (previamente ordenada) donde se inserta en orden el
     *                  nuevo numero.
     * @param nuevo     El numero a insertar.
     * @return Una lista con un nuevo elemento insertado en orden
     * 
     * Tiempo O(n)
     * Sabemos que esta implementacion sera O(n) en tiempo, pues no se cuenta con ciclos dentro
     * de ciclos, ni con operaciones que de alguna manera nos resulte en O-rdenes superiores a cn
     * puesto que toda operación realizada en éste algoritmo es constante, a excepción de un for,
     * que nos da (n-1) repeticiones en el Peor Caso Posible.
     * 
     * Espacio O(1)
     * Es espacio constante, pues lo unico que se crea en esta implemetacion son una cantidad constante
     * de variables de tipo primitivo, e Iteradores que reutilizamos frecuentemente. Mas no Nodos,
     * Listas, u otros Objetos(fuera de los iteradores)
     */
    public static Lista<Integer> AgregaOrdenado(Lista<Integer> lista, int nuevo) {
        if( lista.contains(nuevo) ){
            int indice = lista.indexOf(nuevo);
            lista.insert(indice, nuevo);
        }else{
            IteradorLista<Integer> itera = lista.iteradorLista();
            int primero = (Integer)itera.next();
            itera.end();
            int segundo = (Integer)itera.previous();
            //Verificar si no se tiene que agregar en uno de los extremos
            if( nuevo<primero )
                lista.agregaInicio(nuevo);
            else if( segundo<nuevo )
                lista.agregaFinal(nuevo);
            else{//Se agrega entre numeros...
                itera.start();
                int aux=0;
                //Reinicializar las variables para que sean contiguas y no separadas en extremos
                segundo = primero;
                for (int indice=0; indice<lista.size(); indice++){
                    aux = (Integer)itera.next();
                    //Se actualiza cada variable siempre que...
                    if( aux<nuevo && primero<aux )//Este valor no sea  mayor que el nuevo, pero es mayor que el considerado antes
                        primero = aux;
                    if( nuevo<aux && segundo<aux )//Este valor sea menor que el nuevo, pero es mayor que el considerado antes
                        segundo = aux;
                    //Verificar que nuevo quedo atrapado entre numeros
                    if( primero<nuevo && nuevo<segundo ){
                        lista.insert( indice, nuevo );
                        break;
                    }
                }
            }
        }
        return lista;
    }

    /**
     * Union
     * 
     * Metodo con el que los elementos de dos listas se unen en una sola
     * Agregando a la primera los elementos que no se e
     * @param lista1    La lista a modificar
     * @param lista2    La lista con la que se compara cada elemento de la primera
     * 
     * Tiempo O(n*m)
     * Dado que tenemos un while anidado en un for, tal que el for depende de n y el while de m en el
     * peor caso posible, a la hora de calcular la complejidad, se sigue la pauta de multiplicar sus
     * operaciones, y sumar el resto de operaciones que se encuentran fuera de los ciclos.
     * 
     * Podríamos mejorar el tiempo, si nos aseguraramos, desde antes de invocar al metodo, que ambas
     * listas no contienen elementos similares, o si los tenian, ya fueron descartados, con lo que solo
     * se requeriría un append para cumplir lo solicitado (2 listas en una, sin elementos repetidos, sin
     * orden necesario).
     * La otra manera de disminuir el tiempo en complejidad podría ser permitiendonos el crear una lista
     * auxiliar donde insertar los elementos de ambas listas, sin embargo, esto conlleva un costo extra en
     * espacio
     * 
     * Espacio O(n+m)
     * Dado que únicamente utilizamos una cantidad limitada de variables primitivas, y un iterador,
     * al abstenernos de crear nuevas Listas, Nodos, etc, aseguramos que el espacio depende solamente
     * de las listas en los parametros.
     */
    public static void Union( Lista<Integer> lista1 , Lista<Integer> lista2 ){
        IteradorLista itera = lista1.iteradorLista();
        int aux=0;
        for (int i=0; i<lista1.size(); i++){
            if( itera.hasNext() )
                aux = (Integer)itera.next();
            while( lista2.delete(aux) );// :3
        }
        if( !lista2.isEmpty() )
            lista1.append(lista2);
    }

    /**
     * Interseccion
     * 
     * Metodo con el que obtenemos una lista con los elementos comunes de las dos listas del parametro
     * @param lista1    Lista a modificar
     * @param lista2    Lista con que se comparará y obtendrán solo los valores en común
     * 
     * Tiempo O(n*m)
     * La complejidad en tiempo de la implementación de éste algoritmo se consdiera n*m pues
     * el clo más anidado es un for dependiente de m, que es la longitud de @param lista2, y después
     * se debe multiplicar por el ciclo en el que se anida, que es un for, dependiente de n, la longitud
     * cambiante de @param lista1.
     * Y al sumar las operaciones constantes externas a cada ciclo, concluimos que resultan en una
     * complejidad de O(n*m)
     * 
     * Podríamos mejorar el tiempo, si nos aseguraramos, desde antes de invocar al metodo, que ambas
     * listas estaban previamente ordenadas, y sin elementos repetidos( dentro una misma lista ).
     * La otra manera de disminuir el tiempo en complejidad podría ser permitiendonos el crear una lista
     * auxiliar, donde insertar los elementos en común de ambas listas, aunque esto conlleva un costo
     * extra en espacio.
     * 
     * Espacio O(n+m)
     * Ya que utilizamos 2 variables primitivas, y 2 iteradores, evitamos crear Listas u Objetos
     * auxiliares, lo que nos permitió mantener el tamaño de ambas listas.
     * Y al modificar una de las listas respecto a otra ( @param lista1 respecto @param lista2 )
     * conservamos una lista de longitud m(lista2), y una lista de longitud variable, con un máximo
     * de n elementos, por lo que al ser únicamente 2 listas y una cantidad constante de variables
     * primitivas o iteradoes, se considera que se utiliza un espacio de n+m, que se ve contenida por
     * la notación O(n+m)
     */
    public static void Interseccion( Lista<Integer> lista1 , Lista<Integer> lista2 ){
        IteradorLista itera1 = lista1.iteradorLista();
        IteradorLista itera2 = lista2.iteradorLista();

        int aux1 = (Integer)itera1.next();
        int aux2 = (Integer)itera2.next();
        
        for (int i=0; i<=lista1.size(); i++){
            for (int j = 0; j <lista2.size(); j++){
                if( aux1==aux2 ){
                    lista1.agregaFinal(aux2);
                    break;
                }
                if( itera2.hasNext() )
                    aux2 = (Integer)itera2.next();
            }
            lista1.delete(aux1);
            if( itera1.hasNext() )
                aux1 = (Integer)itera1.next();
        }
    }

    public static void main(String[] args) {
        
        Lista<Integer> primera = new Lista<Integer>();
        Lista<Integer> segunda = new Lista<Integer>();
        Lista<Integer> tercera = new Lista<Integer>();
        
        // Tests toString
        for (int i = 0; i <= 5; i++) {
            primera.add(i);
        }
        
        String test = "0 -> 1 -> 2 -> 3 -> 4 -> 5";
        if ( !primera.toString().equals(test) ) {
            System.out.println("1 El toString no funciona!");
        }
        primera = new Lista<Integer>();
        if (!primera.toString().equals("")) {
            System.out.println("2 El toString no funciona!");
        }
            
        // Tests Reverse
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();

        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.agregaInicio(i);
        }
      
        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El reverse no funciona!");    
        }
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("2 El reverse no funciona!");
        }

        // Tests Append
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            segunda.add(i);
        }
        primera.append(primera.clone());

        
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El Append no funciona!");
        }

        // Tests IndexOf
        if (primera.indexOf(0) != 0) {
            System.out.println("1 El IndexOf no funciona!");
        }
        if (primera.indexOf(1) != 1) {
            System.out.println("2 El IndexOf no funciona!");
        }
        if (primera.indexOf(10) != 10) {
            System.out.println("3 El IndexOf no funciona!");
        }

        // Tests Insert
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            
        }
        for (int i = 0; i <= 4; i++) {
            segunda.add(i);

        }
        segunda.add(6);
        for (int i = 5; i <= 10; i++) {
            segunda.add(i);

        }

        primera.insert(5, 6);
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El insert no funciona!");
        }

        // Tests Mezcla Alternada
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0) {
                primera.add(i);
            }   
        }
        primera.add(11);
        for (int i = 0; i <= 10; i++) {
            if (i % 2 != 0) {
                segunda.add(i);
            }

        }
        for (int i = 0; i <= 11; i++) {
            
                tercera.add(i);

        }


        primera.mezclaAlternada(segunda);
        if (!primera.toString().equals(tercera.toString())) {
            System.out.println("1 la mezclaAlternada no funciona!");
        }


        // Tests Agrega Ordenado
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
        }
        for (int i = 0; i <= 9; i++) {
            segunda.add(i);
        }
        segunda.add(9);
        segunda.add(10);
        
        
        tercera = AgregaOrdenado(primera,9);
        if (!tercera.toString().equals(segunda.toString())) {
            System.out.println("1 el agregaOrdenado no funciona!");
        }
        
        // Tests Union
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);
        Union(primera, segunda);

        if (!(primera.contains(1) && primera.contains(2) && primera.contains(3) && primera.size() == 3)) {
            System.out.println("1 La union no funciona!");
        }
        
        // Tests interseccion
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);

        segunda.add(2);
        Interseccion(primera, segunda);

        if (!(primera.contains(2) && primera.size() == 1)) {
            System.out.println("1 La intersección no funciona!");
        }        
    }   
}
