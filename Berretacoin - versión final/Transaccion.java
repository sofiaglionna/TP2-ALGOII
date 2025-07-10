package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;
    private boolean estaEliminada;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
        this.estaEliminada = false;
    }

    public int id() {
        return this.id;
    }

    public void marcarComoEliminada() {
        this.estaEliminada = true;
    }

    public boolean estaEliminada() {
        return this.estaEliminada;
    }

    @Override
    public int compareTo(Transaccion otra) {
        // Comparar por monto (mayor monto = mayor prioridad)
        if(this.monto > otra.monto){
            return 1; // Esta transacción es "mayor"
        }
        if (this.monto < otra.monto) {
            return -1; // Esta transacción es "menor"
        }
        else {
            // En caso de empate en monto, usar ID como desempate (mayor ID = mayor prioridad)
            if(this.id > otra.id) {
                return 1;
            }
            else if(this.id < otra.id) {
                return -1;
            }
            else {
                return 0; // Mismo monto e ID = iguales
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