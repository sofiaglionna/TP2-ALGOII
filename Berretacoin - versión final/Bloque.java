package aed;

public class Bloque {

    // Heap de transacciones
    private Heap<Transaccion> transacciones;
    // Un array para guardar las transacciones ordenadas por ID (se actualiza en caso de ser necesario)
    // Lo creamos principalmente por el punto N°4, ya que el heap las desordenaba.
    private Transaccion[] transaccionesOrdenadas;
    // Suma de todos los montos de las transacciones de este bloque
    private int suma;
    // Cantidad de transacciones de este bloque
    private int longitud;

    // Complejidad: O(Nb)
    public Bloque(Transaccion[] listaTransacciones){
        this.transacciones = new Heap(listaTransacciones.length + 1);
        this.suma = 0;
        this.longitud = 0;
        // Agregamos todas las transacciones al heap (sin sift):
        for (int i = 0; i < listaTransacciones.length; i++){
            Transaccion transaccion = listaTransacciones[i];
            transacciones.agregarSinOrdenar(transaccion); // O(1) * Nb
            // Si no es una transacción de creación, la sumamos al monto:
            if (transaccion.id_comprador() != 0){
                this.suma += transaccion.monto();
                this.longitud++;
            }
        }
        transacciones.heapify(); // O(Nb)
        this.guardarTransaccionesOrdenadas(listaTransacciones); // O(Nb)
    }

    // Complejidad: O(1)
    public Transaccion mayorTransaccion(){
        return transacciones.raiz();
    }

    // Complejidad: O(1)
    public int montoMedio(){
        if(longitud == 0){
            return 0;
        }
        else return suma / longitud;
    }

    // Complejidad: O(log(Nb))
    public Transaccion eliminarTransaccionMayor(){
        Transaccion raiz = transacciones.eliminarRaiz(); // O(log(Nb))
        raiz.marcarComoEliminada(); // O(1)
        if (raiz.id_comprador() != 0) {
            this.suma = this.suma - raiz.monto();
            this.longitud--;
        }
        return raiz;
    }

    // Complejidad: O(Nb)
    public Transaccion[] obtenerTransaccionesOrdenadas() {
        // Contamos cuántas transacciones NO están eliminadas - O(Nb)
        int cantidadTransaccionesRestantes = 0;
        for (int i = 0; i < transaccionesOrdenadas.length; i++){
            if (!transaccionesOrdenadas[i].estaEliminada()){
                cantidadTransaccionesRestantes++;
            }
        }
        // Creamos un array de transacciones para devolver:
        Transaccion[] resultado = new Transaccion[cantidadTransaccionesRestantes];
        int j = 0;
        // Agregamos las transacciones que no estan eliminadas al array - O(Nb)
        for (int i = 0; i < transaccionesOrdenadas.length; i++){
            if (!transaccionesOrdenadas[i].estaEliminada()){
                resultado[j] = transaccionesOrdenadas[i];
                j++;
            }
        }
        return resultado;
    }

    // --- Métodos Auxiliares ---

    // Complejidad: O(Nb)
    private void guardarTransaccionesOrdenadas(Transaccion[] listaTransacciones) {
        this.transaccionesOrdenadas = new Transaccion[listaTransacciones.length];
        for (int i = 0; i < listaTransacciones.length; i++) {
            this.transaccionesOrdenadas[i] = listaTransacciones[i];
        }
    }
}

