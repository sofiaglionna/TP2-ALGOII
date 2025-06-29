package aed;
/*
*           Para hablar de las complejidades utilizaremos:
*           - P: Cantidad de usuarios en el sistema.
*           - Nb: Cantidad de transacciones en un bloque.
*/
public class Berretacoin {
    private Lista<Bloque> blockChain; // Lista doblemente enlazada de los bloques del sistema.
    private Heap<Usuario> usuariosOrdenadosPorBalance; // Un max-heap de usuarios ordenados por balance.
    private int[] handlesUsuario; // Un array de handles para poder acceder a los elementos del heap sin aumentar la complejidad.

    // Complejidad: O(P)
    // Punto 1: Inicializa al sistema de criptomonedas con usuarios numerados de 1 a n usuarios.
    public Berretacoin(int n_usuarios) {
        this.blockChain = new Lista<Bloque>();
        this.crearUsuarios(n_usuarios); // O(P)
    }

    // Complejidad: O(Nb * log (P))
    // Punto 2: Agrega un nuevo bloque con la secuencia de transacciones, que vienen ordenadas por su identificador, a la cadena de bloques.
    public void agregarBloque(Transaccion[] listaTransacciones){

        // Agregar el bloque a la blockchain:
        Bloque nuevoBloque = new Bloque(listaTransacciones); // O(Nb)
        this.blockChain.agregarAtras(nuevoBloque);

        // Actualizar el mayor tenedor si es necesario cuesta: O(Nb * log(P))
        // O(log P) en cada iteración por actualizarBalance(). O(Nb) por las iteraciones --> O (Nb * log(P))
        for (int i = 0; i < listaTransacciones.length; i++) { // Se itera Nb veces.
            Transaccion transaccion = listaTransacciones[i];
            int montoTransaccion = transaccion.monto();
            int idComprador = transaccion.id_comprador();
            int idVendedor = transaccion.id_vendedor();
            if (idComprador!=0) {
                this.actualizarBalance(idComprador, -montoTransaccion); // O(log(P))
            }
            this.actualizarBalance(idVendedor, montoTransaccion); // O(log(P))
        }
    }

    // Complejidad: O(1)
    // Punto 3: Devuelve la transacción de mayor valor del ultimo bloque (sin extraerla). En caso de empate, devuelve aquella de mayor id.
    public Transaccion txMayorValorUltimoBloque(){
        return this.obtenerUltimoBloque().mayorTransaccion(); // Obtenemos el ultimo Bloque de la blockChain y miro su mayor Transaccion (La raiz del Heap) en O(1)
    }

    // Complejidad: O(Nb)
    // Punto 4: Devuelve una copia de la secuencia de transacciones del ultimo bloque, ordenadas por su identificador.
    public Transaccion[] txUltimoBloque(){
        return this.obtenerUltimoBloque().obtenerTransaccionesOrdenadas(); // Obtenemos el ultimo Bloque de la blockChain y ordenamos las tx en O(Nb)
    }

    // Complejidad: O(1)
    // Punto 5: Devuelve al usuario que posee la mayor cantidad de $Berretacoin. En caso de empate, devuelve aquel de menor id. O(1)
    public int maximoTenedor(){
        return this.usuariosOrdenadosPorBalance.raiz().id_usuario(); // Obtenemos la raiz del heap (es de tipo Usuario) y devolvemos el id O(1)
    }

    // Complejidad: O(1)
    // Punto 6: Devuelve el monto promedio de todas las transacciones en el ultimo bloque de la cadena, sin considerar las ”transacciones de creación”.
    // En caso de que no haya transacciones, devolvemos 0.
    public float montoMedioUltimoBloque(){
        if(this.estaBlockchainVacia()){
            return 0;
        }
        return this.obtenerUltimoBloque().montoMedio(); // Obtenemos el ultimo Bloque de la blockChain y calculamos su "montoMedio" O(1)
    }

    // Complejidad: O(log(Nb) + log(P))
    // Punto 7: Extrae del ultimo bloque de la cadena la transaccion de mayor monto.
    public void hackearTx(){

        // Primero extraemos la transacción de mayor valor del último bloque:

        Bloque ultimoBloque = this.obtenerUltimoBloque();
        // Guardamos la transaccion de mayor valor y lo eliminamos del heap:
        Transaccion transaccionMayorValor = ultimoBloque.eliminarTransaccionMayor(); // O(log(Nb))

        // Luego le devolvemos el monto al comprador y vendedor:
        int montoTransaccion = transaccionMayorValor.monto();
        int idComprador = transaccionMayorValor.id_comprador();
        int idVendedor = transaccionMayorValor.id_vendedor();
        if (idComprador!=0) {
            actualizarBalance(idComprador, montoTransaccion); // O(log P)
        }
        actualizarBalance(idVendedor, -montoTransaccion); // O(log P)
    }

    // --- Métodos Auxiliares ---

    // Complejidad: O(P)
    private void crearUsuarios(int cantidad) {
        this.usuariosOrdenadosPorBalance = new Heap (cantidad + 1);
        this.handlesUsuario = new int[cantidad + 1]; // "+1" Porque los IDs arrancan desde el 1

        // Agregamos todos los usuarios sin hacer sift.
        for (int i = 1; i <= cantidad; i++) { // O(P)
            Usuario nuevoUsuario = new Usuario(i, 0);
            int handle = usuariosOrdenadosPorBalance.agregarSinOrdenar(nuevoUsuario); // O(1)
            this.handlesUsuario[i] = handle;
        }

        // Finalmente, hacemos un heapify sobre el heap para ordenar a los usuarios por su balance
        this.usuariosOrdenadosPorBalance.heapify(); // O(P)
    }

    // Complejidad: O(1)
    private Usuario obtenerUsuarioPorId(int id) {
        int handleUsuario = handlesUsuario[id];
        return this.usuariosOrdenadosPorBalance.obtener(handleUsuario); // O(1)
    }

    // Complejidad: O(log(P))
    private void actualizarBalance(int idUsuario, int cambioMonto) {
        Usuario usuarioActual = this.obtenerUsuarioPorId(idUsuario);
        int nuevoBalance = usuarioActual.balance() + cambioMonto;
        Usuario nuevoUsuario = new Usuario(idUsuario, nuevoBalance); // Evitamos aliasing!!
        int handleUsuario = handlesUsuario[idUsuario];
        this.usuariosOrdenadosPorBalance.modificar(handleUsuario, nuevoUsuario); // O(log(P))
    }

    // Complejidad: O(1)
    private Bloque obtenerUltimoBloque() {
        return this.blockChain.obtenerCola();
    }

    // Complejidad: O(1)
    private boolean estaBlockchainVacia() {
        return this.blockChain.longitud() == 0;
    }
}