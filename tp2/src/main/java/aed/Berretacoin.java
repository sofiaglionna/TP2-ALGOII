package aed;


public class Berretacoin {

    private Lista<Bloque> blockchain; //lista doblemente enlazada de los bloques de Berretacoin
    private Heap<Usuario> mayorTenedor; //heap ordenado por el balance de las personas (maxHeap)
    private Transaccion[] transaccionesOrdenadasPorID; //lista de transaccionesOrdenadasPorID !!!!DEL ULTIMO BLOQUE AGREGADO!!!! que nos pasan por parametro 
    private Diccionario diccionarioUsuarios;


    public Berretacoin(int n_usuarios){ // por el hepify la complejidad es O(n)
        blockchain = new Lista<Bloque>();
        mayorTenedor = new Heap<Usuario>();
        transaccionesOrdenadasPorID = new Transaccion[n_usuarios]; //creo una lista de transacciones de tamaño n_usuarios

        for(int i = 1; i <= n_usuarios; i++) {
            Usuario usuarioNuevo = new Usuario(i,0);
            mayorTenedor.agregarSinOrdenar(usuarioNuevo);
        }
        mayorTenedor.heapify();
        //agregar tambien al diccionario
    }

    //tener en cuenta de no meter en ningun lado a las transacciones de creacion
    public void agregarBloque(Transaccion[] listaTrans){ //O(N_b * log(P))

        this.transaccionesOrdenadasPorID = listaTrans; // actualizo el observador de la lista de transacciones para que sea siempre la del ultimo bloque
        Bloque nuevoBloque = new Bloque(listaTrans); // Creo el Heap (Bloque) con la lista de transacciones
        this.blockchain.agregarAtras(nuevoBloque); // Agrego el Bloque a la blockchain


        for(int i = 0; i < listaTrans.length; i++){
            Transaccion tx = listaTrans[i];
        } 

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
    

    public int maximoTenedor(){ // O(1)
        return mayorTenedor.raiz().id_usuario(); // primero obtengo la raiz del Heap (o sea la transaccion que tiene mayor monto y despues menor id del heap Mayor Tenedor)
    }                                           // el compareTo de usuario ya lo ordena

    public float montoMedioUltimoBloque(){ // O(1)
        if(blockchain.longitud()==0){
            return 0;
        }
        
        Lista<Bloque>.Nodo ultimoNodo = blockchain.getCola(); // obtengo el ultimo nodo primero
        return ultimoNodo.valor.MontoMedio();
    }

    public void hackearTx(){
        throw new UnsupportedOperationException("Implementar!");

        //Esta función tiene que:

        // Extraer la transacción de mayor valor del último bloque (O(log nb))
        // Restaurar el monto al comprador y vendedor (O(log P))
        // Actualizar el heap mayorTenedor

    }
}

