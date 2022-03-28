package edd.src.Estructuras;

public class Cola<T> extends PushPop<T>{

    /**
     * push
     * 
     * Metodo que inserta un nuevo nodo al final de la estructura
     * @param elemento  el elemento del nuevo nodo a agregar
     */
    public void push(T elemento){
        if( elemento==null )
            throw new IllegalArgumentException("Elemento de validez nula...");
        
        Nodo aux = new Nodo(elemento);
        if( isEmpty() ){
            cabeza = ultimo = aux;
            longi++;
            return;
        }
        ultimo.siguiente = aux;
        ultimo = aux;
        longi++;
    }

    /**
     * clone
     * 
     * Metodo con el cual se construye y devuelve una copia exacta de la estructura con
     * que se invoca al metodo
     * 
     * @return un clon de la estructura invocadora.
     */
    public Cola<T> clone(){
        Cola<T> nueva = new Cola<T>();
        if( isEmpty() )
            return nueva;
        
        Nodo aux = this.cabeza;
        do {
            nueva.push( aux.elemento );
            aux = aux.siguiente;
        } while ( aux != null );
        return nueva;
    }

    /**
     * toString
     * 
     * Metodo con el que representamos la estructura en caracteres de texto
     * @return Representacion textual de la estructura
     */
    public String toString(){
        String res = "";
        Nodo aux = this.cabeza;
        do {
            res += aux.elemento.toString() + ", ";
            aux = aux.siguiente;
        }while( aux != null );
        return (res.length()<2)? res : res.substring(0,res.length()-2) ;
    }

    /**
     * append
     * 
     * Metodo auxiliar con el que dos colas son unidas en una tercera.
     * @return La cola invocadora, unida a la cola parametro
     */
    public Cola<T> append( Cola<T> colita ){
        Cola<T> inicial = this.clone();
        Nodo aux = colita.cabeza;
        while( aux != null ){
            inicial.push( aux.elemento );
            aux = aux.siguiente;
        }
        return inicial;
    }
}
