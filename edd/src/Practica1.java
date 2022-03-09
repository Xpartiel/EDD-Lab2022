

import java.util.Iterator;

public class Practica1 {
    

    /**
     * Recorre la lista en orden con un iterador, actualizando paso a paso el valor de 
     * @param lista     La lista (previamente ordenada) donde se inserta en orden el
     *                  nuevo numero.
     * @param nuevo     El numero a insertar.
     * @return Una lista con un nuevo elemento insertado en orden
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
     * Metodo con el que los elementos de dos listas se unen en una sola
     * Agregando a la primera los elementos que no se e
     * @param lista1    La lista a modificar
     * @param lista2    La lista con la que se compara cada elemento de la primera
     */
    public static void Union( Lista<Integer> lista1 , Lista<Integer> lista2 ){
        //IteradorLista itera = lista2.iteradorLista();
        IteradorLista itera = lista1.iteradorLista();
        int aux=0;
        //for (int i=0; i<lista2.size(); i++){            
        for (int i=0; i<lista1.size(); i++){
            if( itera.hasNext() )
                aux = (Integer)itera.next();

            while( lista2.delete(aux) );
            /*if( lista1.contains( aux ) )
                continue;
            else
                lista1.add( aux );*/
        }
        if( !lista2.isEmpty() )
            lista1.append(lista2);
    }

    // Aqui va tu comentario
    public static void Interseccion(Lista<Integer> lista,Lista<Integer> lista2) {
        
        return ;
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
