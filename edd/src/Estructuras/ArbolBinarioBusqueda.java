package Estructuras;

import java.util.Comparator;
import java.util.Iterator;

import edd.src.Estructuras.*;

import java.util.NoSuchElementException;


public class ArbolBinarioBusqueda<T> extends ArbolBinario<T>{

    protected class Iterador implements Iterator<T>{
        /* Cola para recorrer los vértices en BFS. */
        private Cola<Vertice> cola;

        public Iterador(){
            cola = new Cola<Vertice>();
            if(isEmpty())
                return;
            cola.push(raiz);
        }
        
        /* Nos dice si hay un elemento siguiente. */
        public boolean hasNext(){
            return !cola.isEmpty();
        }

        @Override public T next(){
            if(!hasNext())
                throw new NoSuchElementException();

            Vertice v = cola.pop();
            if(v.izquierdo != null)
                cola.push(v.izquierdo);
            if(v.derecho != null)
                cola.push(v.derecho);
            return v.elemento;
        }
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioBusqueda() {
        super();
    }
    
    public ArbolBinarioBusqueda buildUnsorted(Lista<T> lista){
        return buildSorted( lista.mergeSort( new Comparator<T>(){
            @Override
            public int compare(T o1 , T o2 ){
                return (o1.toString()).compareTo(o2.toString());
            }
        }) );
    }
    
    public ArbolBinarioBusqueda buildSorted(Lista<T> lista){
        ArbolBinarioBusqueda<T> nuevo = new ArbolBinarioBusqueda<T>();
        buildRecursivo(lista , nuevo);
        return nuevo;
    }
    
    private void buildRecursivo( Lista<T> lista , ArbolBinarioBusqueda recursivo ){
        //Tomar el elemento del medio
        //Retirar el elemento del medio de la lista
        //Insertarlo a un nuevo arbol
        //Llamada recursiva

        //Caso Base: Lista de uno
        if( lista.size()==1 ){
            insert( recursivo.raiz , lista.pop() );
            //return recursivo;
        }

        //Caso Recursivo

        IteradorLista iterador = lista.iteradorLista();
        @SuppressWarnings("unchecked")
        Lista<T> listaIz = new Lista<T>();
        Lista<T> listaDe = new Lista<T>();
        T borrable = null;
        int iteraciones = (lista.size()/2);
        for (int i=0; i<lista.size(); i++) {
            if( iterador.hasNext() ){    
                if(i<iteraciones){
                    listaIz.agregaFinal( (T)iterador.next() );
                }
                if( i==iteraciones){
                    borrable=( (T)iterador.next() );
                }
                if( iteraciones<i ){
                    listaDe.agregaFinal( (T)iterador.next());
                }
            }
        }
        lista.delete( borrable );
        recursivo.insert( (Vertice)recursivo.raiz() , borrable );
        buildRecursivo( listaIz , recursivo );
        buildRecursivo( listaDe , recursivo );
        
    }
    
    public ArbolBinarioBusqueda convertBST(ArbolBinario<T> arbolBinario){
        return null;
        /*AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA*/
    }

    /* PLAN A */
    public boolean searchClista( Vertice root , T elem ){
        if( root == null || elem == null )
            return false;
        
        Lista<T> lista = new Lista<T>();
        arbolInorder(root , lista);
        return lista.contains(elem);
    }
    /* PLAN B */
    public boolean search(Vertice root , T elem){
        if( root == null || elem == null )
            return false;
        
        // Caso Base, igualdad
        if( root.get().toString().compareTo( elem.toString() )==0 ){
            return true;
        }else if( root.get().toString().compareTo( elem.toString() )<0 ){
            return search( root.derecho, elem );
        }else{
            return search( root.izquierdo, elem );
        }
    }

    /*Plan A*/
    private void arbolInorder( Vertice root , Lista<T> elementos ){
        if( root==null ){
            return;
        }

        if( root.hayIzquierdo() ){
            arbolInorder( (Vertice)root.izquierdo() , elementos );
        }
        elementos.add( root.get() );

        if( root.hayDerecho() ){
            arbolInorder( (Vertice)root.derecho() , elementos );
        }
    }
    public ArbolBinarioBusqueda insertClista( Vertice root , T elem ){
        Lista<T> lista = new Lista<T>();
        arbolInorder( root , lista );

        if( !lista.contains(elem) ){

            IteradorLista itera = lista.iteradorLista();
            Lista<T> mitadIz = new Lista<T>();
            Lista<T> mitadDe = new Lista<T>();
            T aux = null;
            for (int i=0; i<lista.size(); i++) {
                if( itera.hasNext() )
                    aux = (T)itera.next();
                // el elemento del vertice es menor al insertado
                if( aux.toString().compareTo( elem.toString() )<0 ){
                    mitadIz.agregaFinal( aux );
                }
                if( 0<aux.toString().compareTo( elem.toString() ) ){
                    mitadDe.agregaFinal( aux );
                }
            }
            mitadIz.agregaFinal( elem );
            mitadIz.append(mitadDe);
            mitadDe.empty();
            lista = mitadIz.clone();
            mitadIz.empty();

            return buildSorted(lista);
        }
        return this;
    }
    /* PLAN B */
    public void insert( Vertice root , T elem ){
        if( elem == null )
            throw new IllegalArgumentException("No es aceptable insertar el vacio...");

        //Si llego a un espacio vacio, rellenarlo con un nuevo vertice
        if( root == null ){
            root = new Vertice(elem);
            ++elementos;
            return;
        }

        //Si el elemento en el vertice es menor que el que quiero insertar...
        if( root.get().toString().compareTo( elem.toString() )<0 ){
            //Intenta insertar en su subarbol izquierdo
            insert( (Vertice)root.izquierdo() , elem );
        }
        //Si el elemento en el vertice es mayor que el que quiero insertar...
        else if( 0<root.get().toString().compareTo( elem.toString() ) ){
            //Intenta insertar en su subarbol derecho
            insert( (Vertice)root.derecho() , elem);
        }
    }


    private Vertice maximoSubIzquierdo( Vertice invocador ){
        Vertice suplantador = (Vertice)invocador.izquierdo();
        while( suplantador.hayDerecho() ){
            suplantador = (Vertice)suplantador.derecho();
        }
        return suplantador;
    }
    private Vertice encuentraVerticeCon( Vertice root , T elem ){
        if( root == null || elem == null )
            return null;

        // Caso Base, igualdad
        if( root.get().toString().compareTo( elem.toString() )==0 ){
            return root;
        }else if( root.get().toString().compareTo( elem.toString() )<0 ){
            return encuentraVerticeCon( root.derecho, elem );
        }else{
            return encuentraVerticeCon( root.izquierdo, elem );
        }
    }

    public boolean delete(Vertice root , T elem){
        if( searchClista( root, elem ) ){
            Vertice borrable = encuentraVerticeCon( root, elem );

            //Suplantar el vertice con el maximo del subarbol izquierdo
            if( borrable.hayIzquierdo() && borrable.hayDerecho() ){
                Vertice suplantador = maximoSubIzquierdo( borrable );
                borrable.elemento = suplantador.elemento;
                delete( (Vertice)borrable.izquierdo() , suplantador.get() );
            }
            //
            if( borrable.hayIzquierdo() && !borrable.hayDerecho() ){
                borrable.izquierdo.padre = borrable.padre;
                if( borrable.izquierdo.padre != null ){
                    borrable.izquierdo.padre.izquierdo = borrable.izquierdo;
                }
                borrable.padre = null;
            }
            if( borrable.hayDerecho() && !borrable.hayIzquierdo() ){
                borrable.derecho.padre = borrable.padre;
                if( borrable.derecho.padre != null ){
                    borrable.derecho.padre.derecho = borrable.derecho;
                }
                borrable.padre = null;
            }else{//Elimina una hoja
                borrable.padre = null;
                if( borrable.equals( (Vertice)this.raiz() ) ){
                    this.raiz = null;
                }
            }
            return true;
        }
        return false;
    }
    
    /* PLAN A */
    public String toStringClista( Vertice root ){
        Lista<T> lista = new Lista<T>();
        arbolInorder( root , lista );
        return lista.toString();
    }
    /* PLAN B */
    public String toString(Vertice root){
        if( root==null )
            return "";
        
        String s="";
        if( root.hayIzquierdo() )
            s += toString( (Vertice)root.izquierdo() );

        s+=root.get();

        if( root.hayDerecho() ){
            s+= toString( (Vertice)root.derecho() );
        }
        
        return s;
    }
    public void balance(Vertice root){
        System.out.println( "Ya estoy balanceado" );
    }

    @Override
    public boolean delete(T elemento) {
        return delete( this.raiz , elemento );
    }
    @Override
    public void add(T elemento) {
        insert( this.raiz , elemento );
    }

    /**
     * Regresa un iterador para recorrer el arbol de modo DFS Inorder.
     * @return un iterador para recorrer el arbol con DFS Inorder.
     */
    public Iterator<T> iterator() {
        return new Iterador();
    }

}