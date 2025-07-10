package aed;

public class Diccionario {
    //VER SI LA LISTA DE USUARIOS ES NECESARIA (ver porque tenemos que abstraer)
    private Usuario[] usuarios; // array para almacenar usuarios directamente
    private int[] posicionesEnHeap; // array para rastrear posiciones en el heap
    private int longitud;

    public Diccionario(int cantidadUsuarios) {
        this.longitud = cantidadUsuarios+1; //+1 porque voy a tener un usuario con el id n=cantidad de usuarios
        this.usuarios = new Usuario[longitud];
        this.posicionesEnHeap = new int[longitud];
    }

    public void agregar(Usuario usuario){
        this.usuarios[usuario.id_usuario()] = usuario;
        this.posicionesEnHeap[usuario.id_usuario()] = usuario.id_usuario()-1;
    }

    public Usuario obtenerUsuario(int id) {
        return usuarios[id];
    }

    public int obtenerBalance(int id) {
        return usuarios[id].balance();
    }

    public int obtenerPosicionEnHeap(int id) {
        return posicionesEnHeap[id];
    }

   public void actualizarPosicionEnHeap(int id, int nuevaPosicion) {
        posicionesEnHeap[id] = nuevaPosicion;
    }

    //ver si lo usamos
    public void actualizarBalanceDict(int id, int nuevoBalance) {
        usuarios[id].actualizarBalance(nuevoBalance);
    }

    //obtener todos los usuarios (para inicializar el heap)
    public Usuario[] obtenerTodosLosUsuarios() { //O(n)
        Usuario[] resultado = new Usuario[longitud];
        for (int i = 1; i <= longitud; i++) {
            resultado[i-1] = usuarios[i];
        }
        return resultado;
    }



}
