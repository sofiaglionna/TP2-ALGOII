package aed;
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
        this.transacciones = new Heap<Transaccion>(); 
        this.suma = 0;
        for (int i=0; i<listaTrans.length; i++){
            transacciones.agregarSinOrdenar(listaTrans[i]);
            if (listaTrans[i].id_comprador()!=0){
                this.suma= this.suma + (listaTrans[i]).monto();
                this.longitud += 1;
            }
        }
        transacciones.heapify();
    }

    public Transaccion devolver(int i) {
        return transacciones.obtener(i);
    }

    public int longitud() {
        return longitud;
    }

    // ver si realmente lo vamos a usar
    public void agregarTransaccion(){
        return;
    }
    
    public float MontoMedio(){
        if(longitud == 0){
            return 0;
        }
        else return suma / longitud;
    }
    
    public Transaccion transaccionMayor(){
        return transacciones.raiz();
    }

    public Transaccion eliminarTransaccionMayor(){
        return transacciones.eliminarRaiz();
    }
}

