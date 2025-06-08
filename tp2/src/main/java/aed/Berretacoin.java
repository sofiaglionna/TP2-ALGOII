package aed;
import java.util.ArrayList;

public class Berretacoin {

    private Lista<Bloque> blockchain; //lista doblemente enlazada de los bloques de Berretacoin
    private Heap<PersonaBalance> mayorTenedor; //heap ordenado por el balance de las personas (maxHeap)
    private Transaccion[] transaccionesOrdenadasPorID; //lista de transaccionesOrdenadasPorID !!!!DEL ULTIMO BLOQUE AGREGADO!!!! que nos pasan por parametro 


    public Berretacoin(int n_usuarios){
        blockchain = new Lista<Bloque>();
        mayorTenedor = new Heap();
        transaccionesOrdenadasPorID = new Transaccion[n_usuarios]; //creo una lista de transacciones de tamaño n_usuarios

        for(int i = 0; i < n_usuarios; i++) {
            PersonaBalance pb = new PersonaBalance(i,0);
            mayorTenedor.agregar(pb);
        }
    }

    public void agregarBloque(Transaccion[] listaTrans){
        this.transaccionesOrdenadasPorID = listaTrans; // actualizo el observador de la lista de transacciones para que sea siempre la del ultimo bloque
        Bloque nuevoBloque = new Bloque(listaTrans); // Creo el Heap (Bloque) con la lista de transacciones
        this.blockchain.agregarAtras(nuevoBloque); // Agrego el Bloque a la blockchain


    }

    public Transaccion txMayorValorUltimoBloque(){
        return blockchain.getCola().valor.transaccionMayor();
    }

    public Transaccion[] txUltimoBloque(){ //hace una copia ---> O(n) = O(cant transacciones)
        Transaccion[] copia = new Transaccion[transaccionesOrdenadasPorID.length];
        for (int i = 0; i < transaccionesOrdenadasPorID.length; i++) {
            copia[i] = transaccionesOrdenadasPorID[i];
        }
        return copia;
    }
    

    public int maximoTenedor(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public int montoMedioUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public void hackearTx(){
        throw new UnsupportedOperationException("Implementar!");
    }
}
