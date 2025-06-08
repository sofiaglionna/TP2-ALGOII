package aed;
import java.util.ArrayList;

public class Heap<T extends Comparable<T>> { // el cmparable es para poder usar el compareto que hicimos en Transaccion (q no tire error por estar usando un tipo T genérico)
    private ArrayList<T> heap;   
 
    public Heap() {
        heap = new ArrayList<>();
    }

    public int cardinal () {
        return heap.size();    
    }

    public T Raiz() {
        return heap.get(0); // devuelve la raiz del heap, donde la clase tran,saccion ya lo "ordena" con mayor monto y menor id
    }

    private void siftUp(int indice) { // compara con el padre
        while(indice >= 1) {
            T actual = heap.get(indice);
            T padre = heap.get((indice - 1) / 2);
            if(actual.compareTo(padre) == 1) { // si el actual es "mayor" al padre los intercambia.
                heap.set((indice - 1) / 2, actual);
                heap.set(indice, padre);
                indice = (indice - 1) / 2;
            }
            else {
                break;
            }
        }
    }
    
    public void agregar(T otro) {
        heap.add(otro); // se agrega al final
        siftUp(heap.size()-1); // sube hasta el lugar correcto 
    }

    private void siftDown(int indice) {
        while(true) {
            int indice_hijo_izq = 2 * indice + 1;
            int indice_hijo_der = 2 * indice + 2;
            int menor = indice;
            if(indice_hijo_izq < heap.size()) { 
                T izquierda = heap.get(indice_hijo_izq);
                T actual = heap.get(indice);
                if(izquierda.compareTo(actual) == 1) {
                    menor = indice_hijo_izq;
                }
            }
            if(indice_hijo_der < heap.size()) { 
                T derecha = heap.get(indice_hijo_der);
                T actual = heap.get(indice);
                if(derecha.compareTo(actual) == 1) {
                    menor = indice_hijo_der;
                }
            }
            if(menor != indice) {
                T temporal = heap.get(indice);
                heap.set(indice, heap.get(menor));
                heap.set(menor, temporal);
                indice = menor;
            }
            else {
                break;
            }
        }
    }
 

    public T eliminarRaiz() {
        if (heap.isEmpty()) {
        return null;// si no hay raiz que eliminar devuelve null
    }
        T raiz = heap.get(0);// obtengo la raiz

        if (heap.size() == 1) {
        return heap.remove(0);
    }
        T ultimo = heap.remove(heap.size() - 1);// elimino el ultimo elemento
        heap.set(0, ultimo);// piso el elemento
        siftDown(0); //siftDown con el indice 0 

    return raiz;
    }
}
