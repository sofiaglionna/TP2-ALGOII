package aed;
import java.util.ArrayList;

public class Heap {
    private ArrayList<Transaccion> heap;    

    public Heap() {
        heap = new ArrayList<>();
    }

    public int cardinal () {
        return heap.size();    // SE PUEDE DEVOLVER UN INT SIN IMPLEMENTAR LA CLASE???? int ya es primitivo? en transaccion usamos int
    }
    public Transaccion transaccionMayorMonto() {
        return heap.get(0); // devuelve la raiz del heap, donde la clase transaccion ya lo "ordena" con mayor monto y menor id
    }

    private void siftUp(int indice) { // compara con el padre
        while(indice >= 1) {
            Transaccion actual = heap.get(indice);
            Transaccion padre = heap.get((indice - 1) / 2);
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
    
    public void agregar(Transaccion otro) {
        heap.add(otro); // se agrega al final
        siftUp(heap.size()-1); // sube hasta el lugar correcto 
    }

    private void siftDown(int indice) {
        while(true) {
            int indice_hijo_izq = 2 * indice + 1;
            int indice_hijo_der = 2 * indice + 2;
            int menor = indice;
            if(indice_hijo_izq < heap.size()) { 
                Transaccion izquierda = heap.get(indice_hijo_izq);
                Transaccion actual = heap.get(indice);
                if(izquierda.compareTo(actual) == 1) {
                    menor = indice_hijo_izq;
                }
            }
            if(indice_hijo_der < heap.size()) { 
                Transaccion derecha = heap.get(indice_hijo_der);
                Transaccion actual = heap.get(indice);
                if(derecha.compareTo(actual) == 1) {
                    menor = indice_hijo_der;
                }
            }
            if(menor != indice) {
                Transaccion temporal = heap.get(indice);
                heap.set(indice, heap.get(menor));
                heap.set(menor, temporal);
                indice = menor;
            }
            else {
                break;
            }
        }
    }
 

    public Transaccion eliminarRaiz() {
        if (heap.isEmpty()) {
        return null;
    }
        Transaccion raiz = heap.get(0);

        if (heap.size() == 1) {
        return heap.remove(0);
    }
        Transaccion ultimo = heap.remove(heap.size() - 1);
        heap.set(0, ultimo);
        siftDown(0);

    return raiz;
    }
}
