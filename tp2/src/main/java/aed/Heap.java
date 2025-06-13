package aed;
import java.util.ArrayList;

public class Heap<T extends Comparable<T>> { // el cmparable es para poder usar el compareto que hicimos en Transaccion (q no tire error por estar usando un tipo T genérico)
    private ArrayList<T> heap;   
 
    public Heap() {
        heap = new ArrayList<>(); // este constructor es para un heap vacio. Ver si iniciar con un tamaño desde berreta.
    }
    
    public void agregarSinOrdenar(T valor){ // agrega valores de a 1 al Heap sin ordenarlos. Cuando queremos ordenarlos usamos Heapify()
        heap.add(valor);
    }

    //ver si la usamos
    public void heapify(){
        int ultimoPadre =(heap.size() / 2) -1; // ese es el metodo para calcular el ultimo nodo padre
        for(int i = ultimoPadre; i >=0; i--) { // recorro desde el ultimo padre hasta la raiz y aplica sifDown a cada uno
            siftDown(i);
        }
    }

    public T obtener(int i) {
        return heap.get(i);
    }

    public int cardinal () {
        return heap.size();    
    }
    
    public T raiz() {
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
    //cambiar palabras (chat)
    private void siftDown(int indice) {
        while(true) {
            int i_izq = 2*indice + 1;
            int i_der = 2* indice + 2;
            int mayor = indice;
            
            if(i_izq < heap.size()) {
                T izquierda = heap.get(i_izq);
                T actual = heap.get(indice);
                if(izquierda.compareTo(actual) == 1) {
                    mayor = i_izq;
                }
            }
            if(i_der < heap.size()) {
                T derecha = heap.get(i_der);
                T mayorActual = heap.get(mayor);
                if(derecha.compareTo(mayorActual) == 1) {
                    mayor = i_der;
                }
            }
            
            if(mayor != indice) {
                T temporal = heap.get(indice);
                heap.set(indice, heap.get(mayor));
                heap.set(mayor, temporal);
                indice = mayor;
            }
            else {
                break;
            }
        }
    }
    private int siftDown_conIndice(int indice) {
        while(true) {
            int i_izq = 2* indice+1;
            int i_der = 2 *indice+2;
            int mayor = indice;
            
            if(i_izq < heap.size()) {
                T izquierda = heap.get(i_izq);
                T actual = heap.get(indice);
                if(izquierda.compareTo(actual) == 1) {
                    mayor = i_izq;
                }
            }
            if(i_der < heap.size()) {
                T derecha = heap.get(i_der);
                T mayorActual = heap.get(mayor);
                if(derecha.compareTo(mayorActual) == 1) {
                    mayor = i_der;
                }
            }
            
            // Si el mayor no es el nodo actual, intercambiar y continuar
            if(mayor != indice) {
                T temporal = heap.get(indice);
                heap.set(indice, heap.get(mayor));
                heap.set(mayor, temporal);
                indice = mayor;
            }
            else {
                return indice; // Retorna la posición final
            }
        }
    }
    private int siftUp_conIndice(int indice) { // compara con el padre
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
         return indice;
    }
        
    // //ver si lo usamos, si no borrar tambien siftUp y siftDown sin indices
    public void agregar(T otro) {
        heap.add(otro); // se agrega al final
         siftUp(heap.size()-1); // sube hasta el lugar correcto 
     }

    public int reubicar(int posicion){  // Reubica en el heap Y nos da la nueva posicion para poder actualizar el diccionario
        int nuevaPos = siftUp_conIndice(posicion);

        if (nuevaPos == posicion){
            nuevaPos = siftDown_conIndice(posicion);   
        }
        
        return nuevaPos;
    }

    public T eliminarRaiz() {
        if (heap.isEmpty()) {
            return null;// si no hay raiz que eliminar devuelve null
        }
        if (heap.size() == 1) {
            return heap.remove(0);
        }
        T raiz = heap.get(0);// obtengo la raiz
        T ultimo = heap.remove(heap.size() - 1);// elimino el ultimo elemento
        heap.set(0, ultimo);// piso el elemento
        siftDown(0); //siftDown con el indice 0 
        return raiz;
    }
}

