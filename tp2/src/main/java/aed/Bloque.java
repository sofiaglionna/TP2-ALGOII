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

    public Bloque(Transaccion[] listaTrans){ //constructor: nos pasan por parámetro en Berretacoin una lista de transacciones, y nuestro constructor de bloque nos v
        this.longitud = listaTrans.length;
        this.transacciones = new Heap<Transaccion>(); 
        this.suma = 0;
        for (int i=0; i<listaTrans.length; i++){ //hay que cambiarlo
            transacciones.agregar(listaTrans[i]);
            this.suma= this.suma + (listaTrans[i]).monto();
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
    
    public Transaccion transaccionMayor(){
        return transacciones.Raiz();
    }
}

