package aed;


public class Berretacoin {

    private Lista<Bloque> blockchain; //lista doblemente enlazada de los bloques de Berretacoin
    private Heap<Usuario> mayorTenedor; //heap ordenado por el balance de las personas (maxHeap)
    private Transaccion[] transaccionesOrdenadasPorID; //lista de transaccionesOrdenadasPorID !!!!DEL ULTIMO BLOQUE AGREGADO!!!! que nos pasan por parametro 


    public Berretacoin(int n_usuarios){ // por el hepify la complejidad es O(n)
        blockchain = new Lista<Bloque>();
        mayorTenedor = new Heap<Usuario>();
        transaccionesOrdenadasPorID = new Transaccion[n_usuarios]; //creo una lista de transacciones de tamaño n_usuarios

        for(int i = 0; i < n_usuarios; i++) {
            Usuario usuarioNuevo = new Usuario(i,0);
            mayorTenedor.agregar(usuarioNuevo);
        }
    }

    public void agregarBloque(Transaccion[] listaTrans){ //O(N_b * log(P))

        this.transaccionesOrdenadasPorID = listaTrans; // actualizo el observador de la lista de transacciones para que sea siempre la del ultimo bloque
        Bloque nuevoBloque = new Bloque(listaTrans); // Creo el Heap (Bloque) con la lista de transacciones
        this.blockchain.agregarAtras(nuevoBloque); // Agrego el Bloque a la blockchain


        for(int i = 0; i < listaTrans.length; i++){
            // para maximoTenedor
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
            return 0;}
        
        Lista<Bloque>.Nodo ultimoNodo = blockchain.getCola(); // obtengo el ultimo nodo primero
        return ultimoNodo.valor.MontoMedio();
    }

    public void hackearTx(){
        throw new UnsupportedOperationException("Implementar!");
    }
}




        //Bloque ultimoBloque = ultimoNodo.valor; // tomo el valor guardado en el ultimo bloque
        //return   ultimoBloque.MontoMedio(); // calcula y devuelve el monto Medio 