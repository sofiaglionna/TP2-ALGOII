package aed;

//USAR THIS. EN TODOS O NINGUNO Y VER TRANSACCIONES DE CREACION
public class Berretacoin {

    private Lista<Bloque> blockchain; //lista doblemente enlazada de los bloques de Berretacoin
    private Heap<Usuario> mayorTenedor; //heap ordenado por el balance de las personas (maxHeap)
    private Transaccion[] transaccionesOrdenadasPorID; //lista de transaccionesOrdenadasPorID !!!!DEL ULTIMO BLOQUE AGREGADO!!!! que nos pasan por parametro 
    private Diccionario diccionarioUsuarios;


    public Berretacoin(int n_usuarios){ // por el heapify la complejidad es O(n)
        blockchain = new Lista<Bloque>();
        transaccionesOrdenadasPorID = new Transaccion[n_usuarios]; //creo una lista de transacciones de tamaño n_usuarios
        //inicializamos el diccionario
        diccionarioUsuarios = new Diccionario(n_usuarios);
        //inicializamos mayor tenedor
        mayorTenedor = new Heap<Usuario>();

        //mayorTenedor.agregar(diccionarioUsuarios.obtenerUsuario(0)); // inserta el usuario con id 0 en el heap 
        
        for(int i = 1; i <= n_usuarios; i++) {
            Usuario usuarioNuevo = new Usuario(i,0);
            mayorTenedor.agregarSinOrdenar(usuarioNuevo);
        }
        mayorTenedor.heapify(); 
    }

    //tener en cuenta de no meter en ningun lado a las transacciones de creacion
    public void agregarBloque(Transaccion[] listaTrans){ //O(N_b * log(P))
    
        this.transaccionesOrdenadasPorID = listaTrans; // actualizo el observador de la lista de transacciones para que sea siempre la del ultimo bloque
        Bloque nuevoBloque = new Bloque(listaTrans); // Creo el Heap (Bloque) con la lista de transacciones
        this.blockchain.agregarAtras(nuevoBloque); // Agrego el Bloque a la blockchain
    
        //hay que actualizar mayorTenedor
        for(int i = 0; i < listaTrans.length; i++){ // O (N_b * log(P))
            Transaccion tx = listaTrans[i];

            int id_comprador = tx.id_comprador();
            int id_vendedor = tx.id_vendedor();

            //referencias a los usuarios
            Usuario comprador = diccionarioUsuarios.obtenerUsuario(id_comprador);
            Usuario vendedor = diccionarioUsuarios.obtenerUsuario(id_vendedor);

            comprador.actualizarBalance(comprador.balance() - tx.monto()); // actualizo el diccionario, restandole lo que compró el usuario
            vendedor.actualizarBalance(vendedor.balance() + tx.monto()); 

            //obtenemos las posiciones actuales
            int posComprador = diccionarioUsuarios.obtenerPosicionEnHeap(id_comprador);
            int posVendedor = diccionarioUsuarios.obtenerPosicionEnHeap(id_vendedor);            
            //REUBICAMOS O(log p)
            int nuevaPosicionComprador = mayorTenedor.reubicar(posComprador); 
            int NuevaPosicionVendedor = mayorTenedor.reubicar(posVendedor);
    
            diccionarioUsuarios.actualizarPosicionEnHeap(id_comprador,nuevaPosicionComprador);  // O(1)
            diccionarioUsuarios.actualizarPosicionEnHeap(id_vendedor,NuevaPosicionVendedor); // O(1)
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
    }

    public float montoMedioUltimoBloque(){ // O(1)
        if(blockchain.longitud()==0){
            return 0;
        }
        
        Lista<Bloque>.Nodo ultimoNodo = blockchain.getCola(); // obtengo el ultimo nodo primero
        return ultimoNodo.valor.MontoMedio();
    }

    public void hackearTx(){

        // Extraer la transacción de mayor valor del último bloque (O(log nb))
        Bloque ultimo = blockchain.obtener(blockchain.longitud() - 1); // guarda el ultimo bloque de la blockchain
        Transaccion mayorValor = ultimo.eliminarTransaccionMayor(); // guarda la transaccion de mayor valor y lo elimina del heap

        // ¿CUMPLE CON O(log nb)?
        
        // Restaurar el monto al comprador y vendedor (O(log P))        
        Usuario comprador = diccionarioUsuarios.obtenerUsuario(mayorValor.id_comprador());
        Usuario vendedor = diccionarioUsuarios.obtenerUsuario(mayorValor.id_vendedor());

        comprador.actualizarBalance(comprador.balance() + mayorValor.monto());
        vendedor.actualizarBalance(vendedor.balance() - mayorValor.monto());       
        
        // Actualizar el heap mayorTenedor

        // Implementar

    }
}

