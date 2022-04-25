package edd.src.Estructuras;

public class Cola<T> extends PushPop<T>{

    /**
     * push
     * 
     * Metodo que inserta un nuevo nodo al final de la estructura
     * @param elemento  el elemento del nuevo nodo a agregar
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override
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
     * Regresa una representación en cadena de la cola.
     * 
     * @return una representación en cadena de la cola.
     */
    @Override
    public String toString() {
        if (isEmpty())
            return "";
        String a = "";
        Nodo b = cabeza;
        while (b != null) {
            a += b.elemento + ", ";
            b = b.siguiente;
        }
        return a;
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
