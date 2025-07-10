package aed;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class UsuarioTest{

        @Test
        public void nuevoUsuario(){
                Usuario u = new Usuario(1,5);
                assertEquals(1,u.id_usuario());
                assertEquals(5,u.balance());
        } 

        @Test
        public void testCompararMayorBalance() {
                Usuario u1 = new Usuario(1,25);
                Usuario u2 = new Usuario(2,50);
                assertTrue(u2.compareTo(u1) > 0); // u2 tiene el mayor Balance
                assertTrue(u1.compareTo(u2) < 0); // u1 tiene menor Balance
        }
        
        @Test 
        public void MismoMontoyDevuelveMenorId(){ //test del compareTo -> si tienen mismo monto, la prioridad es menor ID
                Usuario u1 = new Usuario (1,50);
                Usuario u2 = new Usuario (2,50);

                assertTrue(u1.compareTo(u2) > 0);
                assertTrue(u2.compareTo(u1) < 0);
        }
        

}