package aed;
import java.util.ArrayList;

public class Diccionario {

    private Usuario[] usuarios; // array para almacenar usuarios directamente
    private int[] posicionesEnHeap; // array para rastrear posiciones en el heap
    private int longitud;

    public Diccionario(int cantidadUsuarios) {
        longitud = cantidadUsuarios;
        usuarios = new Usuario[cantidadUsuarios + 1]; //+1 pq IDs van de 1 a n
        posicionesEnHeap = new int[cantidadUsuarios + 1]; // para rastrear posiciones en heap

        for (int i = 1; i <= cantidadUsuarios; i++) {
            this.usuarios[i] = new Usuario(i, 0); // Balance inicial 0
            this.posicionesEnHeap[i] = i - 1; // Posición inicial en heap (0-indexado)
        }
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

    public void actualizarBalance(int id, int nuevoBalance) {
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



    // IMPLEMENTACION ANTERIOR DE DICCIONARIO


    // private ArrayList<int[]> dict;

    // public Diccionario(){
    //     ArrayList<int[]> dict = new ArrayList<int[]>();
    // }

    // public void agregar(int id){
    //     int[] nuevaPersona = new int[2];
    //     nuevaPersona[0] = id;
    //     nuevaPersona[1] = id - 1; // Al crear la berreta la posicion de cada persona en personaBalance es su id-1
    //     dict.add(nuevaPersona); 
    // }

    // public int posicion (int id){ // buscar la posicion por el id 
    //     int res = (dict.get(id-1))[1];
    //     return res;
    // }

    // public void modificar (int id,int nuevaPosicion){ //modifica la posicion de una persona en el Heap
    //     int[] personaBuscada = dict.get(id-1);
    //     personaBuscada[1] = nuevaPosicion;
    //     dict.set(id-1,personaBuscada);
    // }