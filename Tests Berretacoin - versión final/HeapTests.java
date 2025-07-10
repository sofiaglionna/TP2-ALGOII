package aed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class HeapTests {
    private Heap<Integer> heap;
    
    // Decidimos usar enteros a la hora de testear Heap para facilitar el entendimiento de los propios tests.

    @BeforeEach
    void setUp() {
        heap =  new Heap(1);
    }

    @Test
    public void agregarUnElemento() {
        int handle = heap.agregarSinOrdenar(10);
        assertEquals(0, handle);
        assertEquals(10, heap.raiz());
        assertEquals(10, heap.obtener(handle));
    }

    @Test
    public void agregarVariosElementos() {
        int handle1 = heap.agregarSinOrdenar(5);
        int handle2 = heap.agregarSinOrdenar(10);
        int handle3 = heap.agregarSinOrdenar(3);

        assertEquals(0, handle1);
        assertEquals(1, handle2);
        assertEquals(2, handle3);
        heap.heapify();
        
        assertEquals(10, heap.raiz());

        assertEquals(5, heap.obtener(handle1));
        assertEquals(10, heap.obtener(handle2));
        assertEquals(3, heap.obtener(handle3));
    }

    @Test
    public void maxHeap() {
        heap.agregarSinOrdenar(4);
        heap.agregarSinOrdenar(5);
        heap.agregarSinOrdenar(3);
        heap.agregarSinOrdenar(10);
        heap.agregarSinOrdenar(2);

        heap.heapify();
        // El maximo tiene que ser la raiz
        assertEquals(10, heap.raiz());
    }

    @Test
    public void eliminarRaiz() {
        heap.agregarSinOrdenar(5);
        heap.agregarSinOrdenar(10);
        heap.agregarSinOrdenar(3);
        heap.agregarSinOrdenar(8);

        heap.heapify();

        int mayorValor = heap.eliminarRaiz();

        assertEquals(10, mayorValor);
        assertEquals(8, heap.raiz());
    }


    @Test
    public void eliminarTodosLosElementos() {
        heap.agregarSinOrdenar(1);
        heap.agregarSinOrdenar(5);
        heap.agregarSinOrdenar(3);

        heap.heapify();

        int primeraRaiz = heap.eliminarRaiz();
        int segundaRaiz = heap.eliminarRaiz();
        int terceraRaiz = heap.eliminarRaiz();

        assertEquals(5, primeraRaiz);
        assertEquals(3, segundaRaiz);
        assertEquals(1, terceraRaiz);
    }

    @Test
    public void modificarSinCambio() {
        int handle = heap.agregarSinOrdenar(10);
        heap.agregarSinOrdenar(5);

        heap.modificar(handle, 10); // Mismo valor
        assertEquals(10, heap.raiz());
        assertEquals(10, heap.obtener(handle));
    }

    @Test
    public void agregarSinSift() {
        int handle1 = heap.agregarSinOrdenar(1);
        int handle2 = heap.agregarSinOrdenar(10);
        int handle3 = heap.agregarSinOrdenar(5);

        assertEquals(0, handle1);
        assertEquals(1, handle2);
        assertEquals(2, handle3);
        assertEquals(1, heap.raiz());

        heap.heapify();
        assertEquals(10, heap.raiz());
    }

    @Test
    public void heapifyCompleto() {
        heap.agregarSinOrdenar(3);
        heap.agregarSinOrdenar(1);
        heap.agregarSinOrdenar(15);
        heap.agregarSinOrdenar(5);
        heap.agregarSinOrdenar(4);
        heap.agregarSinOrdenar(12);
        heap.agregarSinOrdenar(10);

        heap.heapify();
        Integer primeraRaiz = heap.eliminarRaiz();
        Integer segundaRaiz = heap.eliminarRaiz();
        Integer terceraRaiz = heap.eliminarRaiz();

        assertEquals(15, primeraRaiz);
        assertEquals(12, segundaRaiz);
        assertEquals(10, terceraRaiz);
    }

    @Test
    public void handlesConsistentes() {
        int handle1 = heap.agregarSinOrdenar(20);
        int handle2 = heap.agregarSinOrdenar(10);
        int handle3 = heap.agregarSinOrdenar(30);

        heap.heapify();

        heap.modificar(handle2, 25);
        heap.eliminarRaiz();

        assertEquals(25, heap.obtener(handle2));
        assertEquals(20, heap.obtener(handle1));
    }

    @Test
    public void elementosIguales() {
        heap.agregarSinOrdenar(10);
        heap.agregarSinOrdenar(10);
        heap.agregarSinOrdenar(10);

        assertEquals(10, heap.raiz());

        int primeraRaiz = heap.eliminarRaiz();
        assertEquals(10, primeraRaiz);
        assertEquals(10, heap.raiz());
    }
}