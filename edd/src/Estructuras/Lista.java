package edd.src.Estructuras;

/**
 * @author Jorge Macias Gomez
 * @author Uriel Balderas Aguilar
 * @author Luis Ernesto Hernandez Rosas
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lista<T> implements Collection<T> {

    /* CLASE NODO */
    private class Nodo {
        public T elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(T elemento){
            this.elemento = elemento;
        }
    }

    /* ITERADOR */
    private class Iterador implements IteradorLista<T> {
        public Nodo anterior;
        public Nodo siguiente; 

        public Iterador(){
            siguiente = cabeza;
        }

        @Override public boolean hasNext(){
            return siguiente != null;
        }

        @Override public T next(){
            if(!hasNext())
                throw new NoSuchElementException();
            T regresar = siguiente.elemento;
            
            this.anterior = this.siguiente ;
            this.siguiente=siguiente.siguiente;
            return regresar;

        }
        
        @Override
        public boolean hasPrevious() {
            return anterior != null;
        }
        
        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            T regresar = anterior.elemento;

            this.siguiente = this.anterior;
            this.anterior = anterior.anterior;
            return regresar;

        }

        @Override
        public void start(){
            this.anterior = null;
            this.siguiente = cabeza;
        }
        
        @Override
        public void end() {
            this.anterior = ultimo;
            this.siguiente = null;
        }
        
    }

    private Nodo cabeza;
    private Nodo ultimo;
    private int longi;
    
    /**
     * Agrega un elemento a la lista.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override
    public void add(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException("El elemento es null");
        }
        agregaFinal(elemento);
    }
    
    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            this.cabeza = this.ultimo = nuevo;
        } else {
            this.cabeza.anterior = nuevo;
            nuevo.siguiente = this.cabeza;
            this.cabeza = nuevo;
        }
        longi++;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if(cabeza == null){
            this.cabeza = this.ultimo = nuevo;
        }
        else{
            this.ultimo.siguiente = nuevo;
            nuevo.anterior = this.ultimo;
            this.ultimo = nuevo;
        }
        longi++;
    }

    private Nodo buscaElemento(T elemento){
        Nodo n = cabeza;
        while(n !=null){
            if (elemento.equals(n.elemento)) {
                return n;
            }
            n=n.siguiente;
        }
        return null;
    }

    /**
     * Elimina un elemento de la lista.
     * 
     * @param elemento el elemento a eliminar.
     */ 
    public boolean delete(T elemento){
        if(elemento == null)
            return false;
        Nodo n = buscaElemento(elemento);
        if(n==null){
            return false;
        }
        if(longi == 1){
            empty();
            return true;
        }
        if (n == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longi --;
            return true;
        }
        if (n == ultimo) {
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
            longi --;
            return true;
        }
        n.siguiente.anterior = n.anterior;
        n.anterior.siguiente = n.siguiente;
        longi --;
        return true;
    }

    /**
     * Regresa un elemento de la lista. (Ultimo)
     * y lo elimina.
     * 
     * @return El elemento a sacar.
     */
    public T pop(){
        T valor = ultimo.elemento;
        ultimo = ultimo.anterior;
        ultimo.siguiente = null;
        longi --;
        return valor;
    }

    /**
     * Regresa el número de elementos en la lista.
     * 
     * @return el número de elementos en la lista.
     */
    public int size(){
        return longi;
    }

    /**
     * Nos dice si un elemento está contenido en la lista.
     * 
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la lista.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contains(T elemento){
        if(buscaElemento(elemento) == null){
            return false;
        }
        return true;
    }

    /**
     * Vacía la lista.
     * 
     */
    public void empty(){
        cabeza = ultimo = null;
        longi = 0;
    }

    /**
     * Nos dice si la lista es vacía.
     * 
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty(){
        return longi == 0;
    }

    /**
     * Regresa una copia de la lista.
     * 
     * @return una copia de la lista.
     */
    public Lista<T> clone() {
        Lista<T> nueva = new Lista<T>();
        Nodo nodo = cabeza;
        while (nodo != null) {
            nueva.add(nodo.elemento);
            nodo = nodo.siguiente;
        }
        return nueva;
    }

    /**
     * Nos dice si la coleccion es igual a otra coleccion recibida.
     * 
     * @param coleccion la coleccion con el que hay que comparar.
     * @return <tt>true</tt> si la coleccion es igual a la coleccion recibido
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o){
        if (!(o instanceof Lista) ) {
            System.out.println("El ejemplar no es una lista");
            return false;
        }
        @SuppressWarnings("unchecked") Lista<T> lista2 = (Lista<T>)o;
        if (this.longi != lista2.longi) {
            System.out.println("Los tamaños no son iguales.");
            return false;
        }
        if(this.isEmpty() && lista2.isEmpty()){
            return true;
        }
        if( (this.isEmpty() && !lista2.isEmpty()) || (lista2.isEmpty() && !this.isEmpty())){
            return false;
        }
        Nodo aux1 = this.cabeza;
        Nodo aux2 = lista2.cabeza;
        while (aux1!= null && aux2 != null)  {
            if (!aux1.elemento.equals(aux2.elemento)) {
                return false;
            }
            aux1 = aux1.siguiente;
            aux2 = aux2.siguiente;
        }
        return true;
    }
    
    /**
     * reverse
     * 
     * Metodo que invierte el orden de la lista.
     * Tiempo O(n), Espacio O(1)
     */
    public void reverse(){
        if( !isEmpty() ){
            //Borramos nodos en orden, pero los reinsertamos al inicio.
            Iterador itera = new Iterador();
            T var = itera.next();
            int i=0;
            while( i<size() ){
                i++;
                delete( var );
                agregaInicio( var );
                if( itera.hasNext() )
                    var = itera.next();
            }
        }
    }

    /**
     * toString
     * 
     * Devuelve una representación en cadena de caracteres de la lista.
     * @return una representación en cadena de la lista, de la forma.
     *          a -> b -> c -> d
     */
    public String toString(){
        Iterador itera = new Iterador();
        String res ="";
        while( itera.hasNext() )
            res += ( itera.next() ).toString() +" -> ";
        return ( 0<res.length() )?res.substring(0, res.length()-4):res;
    }

    /**
     * append
     * 
     * Metodo con el que los elementos de la lista parametro se agregaran a la lista invocadora
     * @param lista lista a insertar en la lista invocadora.
     */
    public void append( Lista<T> lista ) {
        if( lista == null)
            throw new IllegalArgumentException("Lista nula");
        // Si los tipos de las cabezas son iguales, procede
        if( this.cabeza.elemento.getClass().equals( lista.cabeza.elemento.getClass() )){
            Iterador itera = lista.new Iterador();
            while( itera.hasNext() ){
                this.agregaFinal( itera.next() );
            }
        }else{
            throw new IllegalArgumentException("Esta lista no era valida");
        }
    }

    /**
     * indexOf
     * 
     * Regresa un entero con la posicion del elemento.
     * Solo nos importara la primera aparición del elemento
     * Empieza a contar desde 0.
     * 
     * @param elemento elemento del cual queremos conocer la posición.
     * @return entero con la posicion del elemento
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public int indexOf( T elemento ){
        if( elemento==null )
            throw new IllegalArgumentException("Elemento nulo");
        Iterador itera = new Iterador();
        int i = 0;
        //Devolver el indice en que el iterador se topa con el elemento solicitado
        while( itera.hasNext() ){
            if ( itera.next().equals(elemento) )
                return i;
            ++i;
        }
        return -1;
    }
    
    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor que cero, el elemento se agrega al inicio de la
     * lista. Si el índice es mayor o igual que el número de elementos en la
     * lista, el elemento se agrega al fina de la misma. En otro caso, después
     * de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * 
     * @param i        el índice dónde insertar el elemento. Si es menor que 0 el
     *                 elemento se agrega al inicio, y si es mayor o igual que el
     *                 número
     *                 de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void insert(int i, T elemento) {
        // Tu codigo aqui
        return ;
    }

    /**
     * mezclaAlternada
     * 
     * Metodo que modifica la lista invocadora, de modo que dos listas (una parametro y una invocadora)
     * se unan en una, alternando entre los elementos originales de cada una.
     * @param lista
     */
    public void mezclaAlternada( Lista<T> lista ){
        if( lista==null )
            throw new IllegalArgumentException("Lista nula");
        
        this.reverse();
        lista.append( this );
        this.empty();
        int tama = /*this.size() + */lista.size();
        for (int i = 0; i<tama; i++) {
            if( lista.ultimo != null ){
                this.add( lista.ultimo.elemento );
                lista.delete(lista.ultimo.elemento);
            }
            if( lista.cabeza != null){
                this.add( lista.cabeza.elemento );
                lista.delete(lista.cabeza.elemento);
            }
        }  
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
