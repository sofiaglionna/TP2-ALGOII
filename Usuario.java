package aed;

public class Usuario implements Comparable<Usuario>{
    private int id_usuario;
    private int balance;
    
    public Usuario(int id_usuario, int balance) {
        this.id_usuario = id_usuario;
        this.balance = balance;
    }

    public int id_usuario() {
        return id_usuario;
    }

    public int balance() {
        return balance;
    }

    @Override
    public int compareTo(Usuario otro) {
        // Comparar por balance (mayor balance = mayor prioridad)
        if (this.balance() > otro.balance()) {
            return 1; // Este usuario es "mayor"
        } else if (this.balance() < otro.balance()) {
            return -1; // Este usuario es "menor"
        } else {
            // En caso de empate, usar ID como desempate (menor ID = mayor prioridad)
            if (this.id_usuario() < otro.id_usuario()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
