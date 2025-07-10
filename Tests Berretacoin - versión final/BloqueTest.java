package aed;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class BloqueTest{
        private Bloque bloque;
        private Transaccion[] transacciones;
        private Transaccion[] transacciones2;
        private Transaccion[] transacciones3;
        private Transaccion[] soloTransaccionDeCreacion;
        private Transaccion[] transacciones4;

    @BeforeEach
    void setUp() {
        transacciones = new Transaccion[] {
            new Transaccion(0, 0, 2, 1), // 2 -> $1
            new Transaccion(1, 2, 3, 1), // 3 -> $1
            new Transaccion(2, 3, 4, 1) // 4 -> $1
        };

        transacciones2 = new Transaccion[] {
            new Transaccion(0, 0, 4, 1), // 4 -> $2
            new Transaccion(1, 4, 1, 2), // 1 -> $2
            new Transaccion(2, 1, 2, 1)  // 1 -> $1 , 2 -> $1
        };

        transacciones3 = new Transaccion[] {
            new Transaccion(0, 0, 1, 1), // 1 -> $2, 2 -> $1 
            new Transaccion(1, 1, 2, 2), // 2 -> $3 
            new Transaccion(2, 2, 3, 3), // 3 -> $3 
            new Transaccion(3, 3, 1, 2), // 1 -> $2, 3 -> $1 
            new Transaccion(4, 1, 2, 1), // 1 -> $1, 2 -> $1, 3 -> $1 
            new Transaccion(5, 2, 3, 1)  // 1 -> $1, 3 -> $2 
        };
        
        soloTransaccionDeCreacion = new Transaccion[] { //solo UNA transaccion; la de creación
            new Transaccion (1,0,1,1)
        };

        transacciones4  = new Transaccion[] {
            new Transaccion(0, 1, 2, 10),
            new Transaccion(1, 2, 3, 20),
            new Transaccion(2, 3, 4, 30)
        };
    }  
    
        @Test
        public void bloqueVacio() {
                Transaccion[] vacio = new Transaccion[0];
                Bloque bloque = new Bloque(vacio);
                assertEquals(0, bloque.montoMedio());
        }

        @Test
        public void bloqueConSoloTransaccionDeCreacion() {
                Bloque bloque = new Bloque(soloTransaccionDeCreacion);
                assertEquals(0,bloque.montoMedio());
                assertEquals(soloTransaccionDeCreacion[0], bloque.mayorTransaccion());
        }

        @Test
        public void mayorTransaccion(){
                Bloque bloque = new Bloque(transacciones);
                Transaccion mayor = bloque.mayorTransaccion();
                assertEquals(1, bloque.montoMedio());
                assertEquals(2, mayor.id());
                assertEquals(3, mayor.id_comprador());
                assertEquals(4, mayor.id_vendedor());
                assertEquals(1, mayor.monto());
        }
        
        @Test
        public void mayorTransaccion2(){
                Bloque bloque = new Bloque(transacciones2);
                Transaccion mayor = bloque.mayorTransaccion();
                assertEquals(3/2, bloque.montoMedio());
                assertEquals(1, mayor.id());
                assertEquals(4, mayor.id_comprador());
                assertEquals(1, mayor.id_vendedor());
                assertEquals(2, mayor.monto());
        }

        //miramos que despues de sacar la mayor tx, se reordene:

        @Test
        public void eliminarMayorTransaccion(){
                Bloque bloque = new Bloque(transacciones3);
                bloque.eliminarTransaccionMayor();
                Transaccion nuevaMayor = bloque.mayorTransaccion(); 
                assertEquals(3,nuevaMayor.id()); 
                assertEquals(3, nuevaMayor.id_comprador());
                assertEquals(1, nuevaMayor.id_vendedor());
                assertEquals(2, nuevaMayor.monto());
        }

        @Test
        public void eliminarTransaccionActualizaPromedio() {
                Bloque bloque = new Bloque(transacciones4);
                assertEquals(20, bloque.montoMedio());
                bloque.eliminarTransaccionMayor();
                assertEquals(15, bloque.montoMedio()); 
                bloque.eliminarTransaccionMayor();
                assertEquals(10, bloque.montoMedio());
        }


}