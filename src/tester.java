
import Estructuras.ArbolAVL.ArbolAVL;
public class tester {
    public static void main(String[] args) {
    ArbolAVL arbol = new ArbolAVL();
        arbol.insertar("MEXICO");
        arbol.insertar("CHILE");
        arbol.insertar("URUGUAY");
        arbol.insertar("ARGENTINA");
        arbol.insertar("CANADA");
        arbol.insertar("BOLIVIA");
        arbol.insertar("COSTA RICA");
        arbol.insertar("JAMAICA");
        arbol.insertar("COLOMBIA");
        arbol.insertar("VENEZUELA");
        arbol.insertar("BRASIL");
        arbol.insertar("ECUADOR");
        arbol.insertar("EEUU");
        arbol.insertar("PARAGUAY");
        arbol.insertar("PANAMA");
        arbol.insertar("PERU");
        System.out.println(arbol.toString());

    }
}