package edd.src.Estructuras;

public class Pila<T> extends PushPop<T>{
    
    /**
     * push
     * 
     * Metodo que insertará un nuevo nodo en el tope de la estructura
     * @param elemento a insertar en el nuevo nodo
     */
    public void push(T elemento){
        if(elemento == null)
            throw new IllegalArgumentException("Elemento de validez nula...");
        
        Nodo aux = new Nodo(elemento);
        if( isEmpty() ){
            this.cabeza = ultimo = aux;
            longi++;
            return ;
        }
        aux.siguiente = cabeza;
        cabeza = aux;
        longi ++;

    }

    /**
     * clone
     * 
     * Regresa un clon de la estructura que invoca el metodo.
     * 
     * @return un clon de la estructura.
     */
    public Pila<T> clone(){
        Pila<T> aux = new Pila<T>();
        if (this.isEmpty())
            return aux;
        aux = ( reverse() ).reverse();
        return aux;
    }

    /**
     * reverse
     * 
     * Metodo auxiliar que crea y devuelve la estructura invocada, en orden inverso.
     * 
     * @return un clon con el orden de la estructura invertido.
     */
    public Pila<T> reverse(){
        Pila<T> nueva = new Pila<T>();
        if (this.isEmpty())
            return nueva;

        Nodo n = this.cabeza;
        do{
            nueva.push( n.elemento );
            n = n.siguiente;
        }while( n != null );
        return nueva;
    }

    public String toString(){
        /*
        if (this.isEmpty())
            return "";
        String regreso = this.cabeza.elemento.toString();
        */
        String res = "";
        Nodo n = this.cabeza;
        while ( n != null){
            res += n.elemento.toString() + ", ";
            n = n.siguiente;
        }
        return (res.length()<2)? res : res.substring(0,res.length()-2) ;
    }


}
