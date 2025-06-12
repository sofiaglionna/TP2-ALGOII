package aed;

public class Usuario implements Comparable<Usuario>{
    private int id_usuario;
    private int balance;
    
    public Usuario(int id_usuario, int balance) {
        this.id_usuario = id_usuario;
        this.balance = balance;
    }

    // public void comprar(int monto){
    //     this.balance = this.balance - monto;
    // }

    // public void vender(int monto){
    //     this.balance = this.balance + monto;
    // }

    public int id_usuario() {
        return id_usuario;
    }

    public int balance() {
        return balance;
    }

    public void actualizarBalance(int nuevoBalance){
        balance = nuevoBalance;
    }


    @Override
    public int compareTo(Usuario otra) {
        if(this.balance > otra.balance){
            return 1; // va antes si tiene mayor balance
            } 
        if (this.balance < otra.balance) {
            return -1; // va despues si tiene menor balance
            } 
        else { // caso cuando tienen mismo balance
            if(this.id_usuario > otra.id_usuario) {
                return -1; // mismo balance que el padre pero mayor id_usuario
            }
            else {
                return 1; // mismo balance que el padre pero menor id_usuario
            }     
        }
    }
}
