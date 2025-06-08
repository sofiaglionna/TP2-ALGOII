package aed;
import java.util.ArrayList;

public class Berretacoin {

    private Lista<Bloque> blockchain; //lista doblemente enlazada de los bloques de Berretacoin
    private Heap<PersonaBalance> mayorTenedor; //heap ordenado por el balance de las personas (maxHeap)
    private Lista transaccionesOrdenadasPorID; //lista doblemente enlazada de transaccionesOrdenadasPorID que nos pasan por parametro 


    public Berretacoin(int n_usuarios){
        blockchain = new Lista<Bloque>();
        mayorTenedor = new Heap();
        transaccionesOrdenadasPorID = new Lista();

        for(int i = 0; i < n_usuarios; i++) {
            PersonaBalance pb = new PersonaBalance(i,0);
            mayorTenedor.agregar(pb);
        }
    }

    public void agregarBloque(Transaccion[] transacciones){
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion txMayorValorUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion[] txUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
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
