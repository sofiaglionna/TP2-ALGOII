package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }

    @Override
    public int compareTo(Transaccion otro) {
        if(this.monto > otro.monto){
            return 1; // va antes si tiene mayor monto
            } 
        if (this.monto < otro.monto) {
            return -1; // va despues si tiene menor monto
            } 
        else { //mismo monto
            if(this.id > otro.id) {
                return 1; // mismo monto que el padre pero mayor id
            }
            else {
                return -1; // mismo monto que el padre pero menor id
            }     
        }
    }

    @Override
    public boolean equals(Object otro){
        if(otro.getClass() != this.getClass()) {
            return false;
        }
        Transaccion otra = (Transaccion) otro;
        if(this.id == otra.id) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public int monto() {
        return monto;
    }

    public int id_comprador() {
        return id_comprador;
    }
    
    public int id_vendedor() {
        return id_vendedor;
    }
}



// Paso 1: Agregas private int posicionEnHeap;


// Paso 2: Agregar los métodos set y get:
//public void setPosicionEnHeap(int pos) {
//    this.posicionEnHeap = pos;
//}

//public int getPosicionEnHeap() {
//    return this.posicionEnHeap;
//}


// Paso 3: Actualizar el campo posicionEnHeap cada vez que el elemento se mueve en el heap. En la clase Heap<T extends Comparable<T>>, agregá un método swap.
// private void swap(int i, int j) {
//    T elemI = heap.get(i);
//    T elemJ = heap.get(j);

//    heap.set(i, elemJ);
//    heap.set(j, elemI);

    // Si T es Transaccion, actualizamos su posición
//    if (elemI instanceof Transaccion) {
//        ((Transaccion) elemI).setPosicionEnHeap(j);
//        ((Transaccion) elemJ).setPosicionEnHeap(i);
//    }
//}
// O, mejor aún, si sabés que el heap solo va a manejar Transaccion, podés tipificarlo directamente como:


//public class HeapTransaccion {
//    private ArrayList<Transaccion> heap;

//    private void swap(int i, int j) {
//        Transaccion a = heap.get(i);
//        Transaccion b = heap.get(j);

//        heap.set(i, b);
//        heap.set(j, a);

//        a.setPosicionEnHeap(j);
//        b.setPosicionEnHeap(i);
//    }
//}



// Paso 4: Al insertar, guardá la posición actual en el objeto
//Dentro del método insertar():


//public void insertar(Transaccion t) {
//    heap.add(t);
//    int pos = heap.size() - 1;
//    t.setPosicionEnHeap(pos);  // ← actualizamos el handle
//    subir(pos);
//}