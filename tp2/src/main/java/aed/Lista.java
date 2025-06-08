package aed;

public class Lista<T> {

    private int longitud;
    private Nodo cabeza;
    private Nodo cola;

    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;
        Nodo (T v){ 
            valor = v;
        }
    }

    public Lista() {
        this.cabeza = null;
        this.cola = null;
        this.longitud = 0;
    }

    //metodos getters
    
    public int longitud() {
        return longitud;
    }

    public Nodo getCabeza(){
        return this.cabeza;
    }

    public Nodo getCola(){
        return this.cola;
    }

    //

    public void agregarAdelante(T elem) {
       
        if (longitud == 0){
            Nodo primerNodo = new Nodo(elem);
            primerNodo.valor = elem;
            primerNodo.siguiente = null;
            primerNodo.anterior = null;
            this.cabeza = primerNodo;
            this.cola = primerNodo;
        }
        else{
            Nodo nuevoNodo = new Nodo(elem);
            nuevoNodo.valor = elem;
            nuevoNodo.siguiente = this.cabeza;
            nuevoNodo.anterior = null; 
            this.cabeza.anterior = nuevoNodo; 
            this.cabeza = nuevoNodo; 
        }
        longitud++;
    }
    
        public void agregarAtras(T elem) {
       
        if (longitud == 0){
            Nodo primerNodo = new Nodo(elem);
            primerNodo.valor = elem;
            primerNodo.siguiente = null;
            primerNodo.anterior = null;
            this.cabeza = primerNodo;
            this.cola = primerNodo;
        }
        else{
            Nodo nuevoNodo = new Nodo(elem);
            nuevoNodo.valor = elem;
            nuevoNodo.siguiente = null;
            nuevoNodo.anterior = this.cola; 
            this.cola.siguiente = nuevoNodo; 
            this.cola = nuevoNodo; 
        }
        longitud++;
    }

    public T obtener(int i) {
        Nodo nodoParaRecorrer = cabeza;
        for (int j = 0; j < i; j++){
            nodoParaRecorrer = nodoParaRecorrer.siguiente;
        }
        return nodoParaRecorrer.valor;
    }

    public void eliminar(int i) {
        Nodo nodoParaRecorrer = cabeza;
        for (int j = 0; j < i; j++){
            nodoParaRecorrer = nodoParaRecorrer.siguiente;
        }

        if (nodoParaRecorrer.anterior != null){
            nodoParaRecorrer.anterior.siguiente = nodoParaRecorrer.siguiente;
        } else {
            cabeza = nodoParaRecorrer.siguiente;
        }

        if (nodoParaRecorrer.siguiente != null){
            nodoParaRecorrer.siguiente.anterior = nodoParaRecorrer.anterior;            
        } else {
            cola = nodoParaRecorrer.anterior;
        }
        longitud--;
    }



}
