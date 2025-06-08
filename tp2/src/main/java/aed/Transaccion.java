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
                return -1; // mismo monto que el padre pero mayor id
            }
            else {
                return 1; // mismo monto que el padre pero menor id
            }     
        }
    }

    @Override
    public boolean equals(Object otro){
        if(otro.getClass() != this.getClass()) {
            return false;
        }
        else if(otro == null) {
            return false;
        }
        else {
            Transaccion otra = (Transaccion) otro;
            if(this.id == otra.id) {
                return true;
            }
            else {
                return false;
            }
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