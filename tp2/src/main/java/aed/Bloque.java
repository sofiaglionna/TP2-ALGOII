package aed;
import java.util.ArrayList;
//* posibles metodos


//* constructor
//* agregar Transacción 
//* monto medio
//* obtener Transacción (raiz)
//* actualizar monto medio


public class Bloque {
    private Heap<Transaccion> transacciones;
    private int suma;
    private int longitud;

    public Bloque(ArrayList<Transaccion> listaTrans){ //constructor: nos pasan por parámetro en Berretacoin una lista de transacciones, y nuestro constructor de bloque nos v
        this.longitud = listaTrans.size();
        this.transacciones = new Heap<Transaccion>(); 
        this.suma = 0;
        for (int i=0; i<listaTrans.size(); i++){
            transacciones.agregar(listaTrans.get(i));
            this.suma= this.suma + (listaTrans.get(i)).monto();
        }
    }

    // ver si realmente lo vamos a usar
    public void agregarTransaccion(){
        return;
    }
    
    private float MontoMedio(){
        if(longitud == 0){
            return 0;
        }
        else return suma / longitud;
    }
    
    public Transaccion TransaccionMayor(){
        return transacciones.Raiz();
    }
}

