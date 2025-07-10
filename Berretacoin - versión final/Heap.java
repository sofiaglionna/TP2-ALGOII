package aed;
import java.util.ArrayList;

public class Heap<T extends Comparable<T>> { // CLASE HEAP ES GENERAL //
    private ArrayList<T> heap;
    private ArrayList<Integer> posiciones; // Para handles - mapea handle -> posición
    private ArrayList<Integer> handles;    // Para handles - mapea posición -> handle
    private int siguienteHandle;

    // Complejidad : O(1)
    public Heap(int capacidadInicial) {
        this.heap = new ArrayList<>(capacidadInicial);
        this.posiciones = new ArrayList<>(capacidadInicial);
        this.handles = new ArrayList<>(capacidadInicial);
        this.siguienteHandle = 0;
    }
    
    // Complejidad : O(n)
    public void heapify() {
        for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    // Agregar elemento SIN mantener propiedad heap: O(1)
    public int agregarSinOrdenar(T elemento) {
        int handle = siguienteHandle++;
        int posicion = heap.size();

        heap.add(elemento);
        handles.add(handle);

        while (posiciones.size() <= handle) {
            posiciones.add(-1); // -1 indica handle invalido
        }
        
        posiciones.set(handle, posicion);

        // NO HACEMOS siftUp aca
        return handle;
    }

    // Complejidad: O(1)
    public T raiz() {
        return heap.get(0);
    }

    // Complejidad: O(log n)
    public T eliminarRaiz() {

        if (heap.size() == 1) {
            T elemento = heap.remove(0);
            int handle = handles.remove(0);
            posiciones.set(handle, -1); // Marcar como inválido
            return elemento;
        }

        T max = heap.get(0);
        T ultimo = heap.remove(heap.size() - 1);
        int handleMax = handles.get(0);
        int handleUltimo = handles.remove(handles.size() - 1);

        // Mover ultimo elemento a la raíz
        heap.set(0, ultimo);
        handles.set(0, handleUltimo);
        posiciones.set(handleUltimo, 0);
        posiciones.set(handleMax, -1); // Marcar como invalido

        siftDown(0);
        return max;
    }

    // Obtener elemento por handle : O(1)
    public T obtener(int handle) {
        return heap.get(posiciones.get(handle));
    }

    // Modificar elemento por handle : O(log n)
    public void modificar(int handle, T nuevoValor) {
        int pos = posiciones.get(handle);
        T valorAnterior = heap.get(pos);
        heap.set(pos, nuevoValor);

        // Decidir si hacer siftUp o siftDown:
        if (nuevoValor.compareTo(valorAnterior) > 0){
            siftUp(pos);
        } else if (nuevoValor.compareTo(valorAnterior) < 0){
            siftDown(pos);
        }
    }

    // SiftUp con actualización de handles
    private void siftUp(int indice) {
        while (indice > 0) {
            int indicePadre = (indice - 1) / 2;
            if (heap.get(indice).compareTo(heap.get(indicePadre)) <= 0){
                break;
            }
            intercambiar(indice, indicePadre);
            indice = indicePadre;
        }
    }

    // SiftDown con actualización de handles
    private void siftDown(int indice) {
        while (true) {
            int izq = 2 * indice + 1;
            int der = 2 * indice + 2;
            int mayor = indice;
            
            if (izq < heap.size() && heap.get(izq).compareTo(heap.get(mayor)) > 0){
                mayor = izq; }
            if (der < heap.size() && heap.get(der).compareTo(heap.get(mayor)) > 0){
                mayor = der; }
            if (mayor == indice) {
                break;}
            intercambiar(indice, mayor);
            indice = mayor;
        }
    }

    // Intercambiar elementos actualizando handles
    private void intercambiar(int i, int j) {
        
        // Intercambiar elementos
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        
        // Intercambiar handles
        int handleI = handles.get(i);
        int handleJ = handles.get(j);
        handles.set(i, handleJ);
        handles.set(j, handleI);

        // Actualizar posiciones
        posiciones.set(handleI, j);
        posiciones.set(handleJ, i);
    }
}