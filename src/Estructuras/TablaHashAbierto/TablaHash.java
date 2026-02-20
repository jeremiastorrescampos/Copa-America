package Estructuras.TablaHashAbierto;
import Estructuras.lineales.listaDinamica.*;;
public class TablaHash {

    int TAMANIO = 23;
    private NodoHash[] tabla;
    private int cantidad = 0; //Cantidad de enlaces en las celdas.

    public TablaHash() {
        tabla = new NodoHash[TAMANIO];

    }

    public boolean insertar(Object elem) {

        int pos = Math.abs((elem.hashCode() % this.TAMANIO)); 
        //Se agrega Math.abs para evitar posiciones negativas

        NodoHash aux = tabla[pos];
        //Como la Tabla debe permitir duplicados, simplemente lo inserto.

        this.tabla[pos] = new NodoHash(elem, this.tabla[pos]);
        this.cantidad++;

        calcularPromedio();

        return true;

    }

    public Object recuperarElemento(Object elem) {

        int pos = Math.abs(elem.hashCode() % this.TAMANIO);
    NodoHash aux = tabla[pos];
    Object encontrado = null;

    while (aux != null && encontrado == null) {
        if (aux.getElem().equals(elem)) {
            encontrado = aux.getElem();
        }
        aux = aux.getEnlace();
    }
    return encontrado;
}

    public boolean pertenece(Object elem) {
        int pos = Math.abs(elem.hashCode() % this.TAMANIO);
        boolean encontrado = false;
        NodoHash aux = tabla[pos];

        while (aux != null && !encontrado) {

            if (aux.getElem().equals(elem)) {
                encontrado = true;

            }
            aux = aux.getEnlace();

        }
        return encontrado;

    }

    private void calcularPromedio() { //Este método me servirá para calcular el promedio de elementos que hay por celda. En caso de haber mayor a 3, 
        //Debo agrandar la tabla.
        int promedio = (this.cantidad / this.TAMANIO);

        if (promedio >= 3) {
            expandirTabla();

        }

    }

    private void expandirTabla() {
        int tamanioNuevo = this.TAMANIO + (this.TAMANIO / 2); //Le agrego 1/2 del tamaño.

        NodoHash[] tablaNueva = new NodoHash[tamanioNuevo]; //Creo la nueva tabla.
        NodoHash aux;
        int pos;

        for (int i = 0; i < TAMANIO; i++) {
            aux = tabla[i];

            while (aux != null) { //Reacomodo cada nodo en la celda. Como se que no están repetidos, simoplemente calculo
                //Su nueva posicioón y lo guardo.

                pos = Math.abs((aux.getElem().hashCode() % tamanioNuevo));

                tablaNueva[pos] = new NodoHash(aux.getElem(), tablaNueva[pos]);

                aux = aux.getEnlace();

            }

        }

        this.tabla = tablaNueva;
        this.TAMANIO = tamanioNuevo;

    }

    public boolean eliminar(Object elem) {

        int pos = Math.abs(elem.hashCode() % this.TAMANIO);
        boolean eliminado = false;
        NodoHash aux = tabla[pos];

        if (aux.getElem().equals(elem)) {
            this.tabla[pos] = aux.getEnlace();
            eliminado = true;

        } else {

            while (aux.getEnlace() != null && !eliminado) {

                if (aux.getEnlace().getElem().equals(elem)) {
                    aux.setEnlace(aux.getEnlace().getEnlace());
                    eliminado = true;

                } else {

                    aux = aux.getEnlace();

                }

            }
        }
        return eliminado;

    }

    public String toString() {
        String cadena = "";
        NodoHash aux;
        if (this.cantidad > 0) {
            for (int i = 0; i < TAMANIO; i++) {
                aux = tabla[i];

                cadena = cadena + " Posicion: " + i + "  \t[" + ((aux != null) ? aux.getElem().toString() : "null") + "]";

                while (aux != null && aux.getEnlace() != null) {

                    cadena = cadena + "  -> [ " + aux.getEnlace().getElem().toString() + " ]";

                    aux = aux.getEnlace();
                }

                cadena = cadena + "\n";

            }

        }

        return cadena;

    }

    public Lista listar() {

        Lista lista = new Lista();
        NodoHash aux;

        for (int i = 0; i < TAMANIO; i++) {
            aux = tabla[i];

            while (aux != null) {
                lista.insertar(aux.getElem(), lista.longitud() + 1);

                aux = aux.getEnlace();

            }

        }
        return lista;

    }

    public boolean esVacio() {

        return (this.cantidad == 0);
    }

    public void vaciar() {
        this.tabla = null;
        this.cantidad = 0;

    }
    

}