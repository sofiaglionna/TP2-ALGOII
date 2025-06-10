package aed;

public class Usuario implements Comparable<Usuario>{
    private int id_usuario;
    private int saldo;

    public Usuario(int id_usuario, int saldo) {
        this.id_usuario = id_usuario;
        this.saldo = saldo;
    }


    @Override
    public int compareTo(Usuario otra) {
        if(this.saldo > otra.saldo){
            return 1; // va antes si tiene mayor saldo
            } 
        if (this.saldo < otra.saldo) {
            return -1; // va despues si tiene menor saldo
            } 
        else { // caso cuando tienen mismo saldo
            if(this.id_usuario > otra.id_usuario) {
                return -1; // mismo saldo que el padre pero mayor id_usuario
            }
            else {
                return 1; // mismo saldo que el padre pero menor id_usuario
            }     
        }
    }
}
