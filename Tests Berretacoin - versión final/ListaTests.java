package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ListaTests {

        @Test
        public void nuevaListaEstaVacia() {
            Lista<Integer> lista = new Lista<>();

            assertEquals(0, lista.longitud());
        }

        @Test
        public void agregarUnElementoAdelante() {
            Lista<Integer> lista = new Lista<>();

            lista.agregarAdelante(42);

            assertEquals(1, lista.longitud());
            assertEquals(42, lista.obtener(0));
        }

        @Test
        public void agregarUnElementoAtras() {
            Lista<Boolean> lista = new Lista<>();

            lista.agregarAtras(true);

            assertEquals(1, lista.longitud());
            assertEquals(true, lista.obtener(0));
        }
        
        @Test
        public void agregarVariosElementosSoloAdelante() {
            Lista<Float> lista = new Lista<>();

            lista.agregarAdelante(42.0f);
            lista.agregarAdelante(41.0f);
            lista.agregarAdelante(40.0f);
            lista.agregarAdelante(39.0f);

            assertEquals(4, lista.longitud());
            assertEquals(39.0f, lista.obtener(0));
            assertEquals(40.0f, lista.obtener(1));
            assertEquals(41.0f, lista.obtener(2));
            //Obtener cola funciona al agregar adelante?
            assertEquals(42.0f, lista.obtenerCola());
        }

        @Test
        public void agregarVariosElementosSoloAtras() {
            Lista<Character> lista = new Lista<>();

            lista.agregarAtras('2');
            lista.agregarAtras('3');
            lista.agregarAtras('4');
            lista.agregarAtras('5');

            assertEquals(4, lista.longitud());
            assertEquals('2', lista.obtener(0));
            assertEquals('3', lista.obtener(1));
            assertEquals('4', lista.obtener(2));
            //Obtener cola funciona al agregar atras?
            assertEquals('5', lista.obtenerCola()); 
        }

        @Test
        public void agregarVariosElementosAdelanteYAtras() {
            Lista<Integer> lista = new Lista<>();

            lista.agregarAdelante(42);
            lista.agregarAdelante(41);
            lista.agregarAtras(43);
            lista.agregarAdelante(40);
            lista.agregarAtras(44);

            assertEquals(5, lista.longitud());
            assertEquals(40, lista.obtener(0));
            assertEquals(41, lista.obtener(1));
            assertEquals(42, lista.obtener(2));
            assertEquals(43, lista.obtener(3));
            assertEquals(44, lista.obtener(4)); 
        }

        @Test
        public void eliminarElementos() {
            Lista<Integer> lista = new Lista<>();

            lista.agregarAtras(42);
            lista.agregarAtras(43);
            lista.agregarAtras(44);
            lista.agregarAtras(45);

            lista.eliminar(1);

            assertEquals(3, lista.longitud());
            assertEquals(42, lista.obtener(0));
            assertEquals(44, lista.obtener(1));
            assertEquals(45, lista.obtener(2));

            lista.eliminar(2);

            assertEquals(2, lista.longitud());
            assertEquals(42, lista.obtener(0));
            assertEquals(44, lista.obtener(1));

            lista.eliminar(0);
            lista.eliminar(0);

            assertEquals(0, lista.longitud());
        }

        @Test
        public void eliminarExtremos(){ //Vemos si funciona obtenerCola al eliminar el ultimo elemento
            Lista<Integer> lista = new Lista<>();

            lista.agregarAdelante(43);
            lista.agregarAtras(44);
            lista.agregarAdelante(42);
            lista.agregarAtras(45);

            lista.eliminar(0);

            assertEquals(3, lista.longitud());
            assertEquals(43, lista.obtener(0));
            assertEquals(44, lista.obtener(1));
            assertEquals(45, lista.obtenerCola());

            lista.eliminar(2);

            assertEquals(2, lista.longitud());
            assertEquals(43, lista.obtener(0));
            assertEquals(44, lista.obtenerCola());

            //ObtenerCola funciona cuando hay un solo elemento?
            lista.eliminar(1);

            assertEquals(1, lista.longitud());
            assertEquals(43, lista.obtenerCola());

            lista.eliminar(0);
            assertEquals(0, lista.longitud());
        }

    }
