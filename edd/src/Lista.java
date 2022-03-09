

import java.util.Iterator;
import java.util.NoSuchElementException;
// iterador
//next

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

        @Override
        public boolean hasNext(){
            return siguiente != null;
        }

        @Override
        public T next(){
            if( !hasNext() )
                throw new NoSuchElementException("Nada al frente...");
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
        public T previous(){
            if ( !hasPrevious() )
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
        public void end(){
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
        if( elemento == null )
            throw new IllegalArgumentException("El elemento es null");
        agregaFinal(elemento);
    }
    
    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio( T elemento ){
        if ( elemento == null )
            throw new IllegalArgumentException("El elemento es null");
        Nodo nuevo = new Nodo( elemento );
        if ( cabeza == null ){
            this.cabeza = this.ultimo = nuevo;
        }else{
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
    public void agregaFinal( T elemento ){
        if (elemento == null)
            throw new IllegalArgumentException("El elemento es null");
        Nodo nuevo = new Nodo(elemento);
        if(cabeza == null){
            this.cabeza = this.ultimo = nuevo;
        }else{
            this.ultimo.siguiente = nuevo;
            nuevo.anterior = this.ultimo;
            this.ultimo = nuevo;
        }
        longi++;
    }

    private Nodo buscaElemento(T elemento){
        Nodo n = cabeza;
        while(n != null){
            if ( elemento.equals(n.elemento) ) {
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
        if( n==null )
            return false;
        if( longi == 1 ){
            empty();
            return true;
        }
        if ( n == cabeza ) {
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
        if( buscaElemento(elemento) == null )
            return false;
        return true;
    }

    /**
     * Vacía la lista.
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
    public Lista<T> clone(){
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
    public boolean equals(Collection<T> coleccion){
        // lo vemos en clase
        if(coleccion instanceof Lista) {
            return true;
        }
        return false;
    }
    
    /**
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
     * Regresa una representación en cadena de la coleccion.
     * 
     * @return una representación en cadena de la coleccion.
     * a -> b -> c -> d
     */
    public String toString(){
        Iterador itera = new Iterador();
        String res ="";
        while( itera.hasNext() )
            res += ( itera.next() ).toString() +" -> ";
        return ( 0<res.length() )?res.substring(0, res.length()-4):res;
    }

    /**
     * Junta dos listas siempre y cuando sean del mismo tipo.
     * 
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
    public void insert( int i, T elemento ) {
        if( elemento == null )
            throw new IllegalArgumentException("Elemento nulo");
        if( i <= 0 )
            this.agregaInicio(elemento);
        else if( longi <= i )
            this.agregaFinal(elemento);
        else{
            Nodo aux = cabeza;
            for(int j=1; j<i && aux.siguiente!=null; j++)
                aux = aux.siguiente;
            
            Nodo nuevo = new Nodo(elemento);
            nuevo.anterior = aux;
            nuevo.siguiente = aux.siguiente;
            aux.siguiente.anterior = nuevo;
            aux.siguiente = nuevo;

            ++longi;
        }
    }

    // Tu comentario
    public void mezclaAlternada( Lista<T> lista ){
        if( lista==null )
            throw new IllegalArgumentException("Lista nula");
        /*
            saca el reverso
            append a la lista parametro
            vaciar lista original
            añadir al final de la original el primer elemento de la lista parametro
            añadir al final de la original el ultimo elemento de la lista parametro
            borrar cabeza y cola de lista parametro

            1 2 3 4 5 
            6 7 8 9 10

            5 4 3 2 1
            6 7 8 9 10

            6 7 8 9 10 5 4 3 2 1

            1 6 2 7 3 8 4 9 5 10
            -
        */
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



    public static void main(String[] args) {
        /*
        Lista<Integer> primera = new Lista<Integer>();
        Lista<Integer> segunda = new Lista<Integer>();
        Lista<String> tercera = new Lista<String>();

        for (int i = 0; i <= 10; i++){
            primera.add(i);
            segunda.add(i);
            tercera.add( ((Integer)(i)).toString() );
        }
            
        System.out.println( primera.toString() );
        primera.reverse();
        System.out.println( primera.toString() );
        
        primera.append(segunda);
        System.out.println( primera.toString() );

        System.out.println( primera.indexOf(1) );
        System.out.println( primera.indexOf(10) );
        System.out.println( primera.indexOf(20) );

        segunda.insert(5, 99 );
        System.out.println( segunda.toString() );

        segunda.insert(0, 88 );
        System.out.println( segunda.toString() );

        segunda.insert(256, 777 );
        System.out.println( segunda.toString() );
        */

        Lista<Integer> pares = new Lista<Integer>();
        Lista<Integer> nones = new Lista<Integer>();
        for (int i = 0; i <= 10; i++){
            if( i%2==0 )
                pares.add(i);
            else
                nones.add(i);
        }
        System.out.println( pares.toString() );
        System.out.println( nones.toString() +"\n");
        pares.mezclaAlternada(nones);
        System.out.println( pares.toString() );

        
    }
}
