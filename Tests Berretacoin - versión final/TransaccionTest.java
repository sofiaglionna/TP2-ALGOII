package aed;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class TransaccionTest{

        @Test 
        public void testConstructor(){
                Transaccion t = new Transaccion(1,10,20,50);
                assertEquals(1,t.id());
                assertEquals(10,t.id_comprador());
                assertEquals(20,t.id_vendedor());
                assertEquals(50,t.monto());
        }

        @Test 
        public void tesComparaMayorMonto(){ // compara los montos 
                Transaccion t1 = new Transaccion(1,10,20,50);
                Transaccion t2 = new Transaccion(2,11,21,51);

                assertTrue(t1.compareTo(t2) < 0);
                assertTrue(t2.compareTo(t1) > 0);
        }

        @Test 
        public void testComparaMismoMontoMayorId(){ //test del compareTo -> si tienen mismo monto, la prioridad es mayor ID
                Transaccion t1 = new Transaccion(1,10,20,50);
                Transaccion t2 = new Transaccion(2,10,20,50);

                assertTrue(t1.compareTo(t2) < 0);
                assertTrue(t2.compareTo(t1) > 0);
        }
}
    