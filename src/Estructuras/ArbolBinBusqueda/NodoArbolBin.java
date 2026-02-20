package Estructuras.ArbolBinBusqueda;

public class NodoArbolBin {
    Comparable elemento;
    NodoArbolBin izquierdo;
    NodoArbolBin derecho;
    

    public NodoArbolBin(Comparable elElemento, NodoArbolBin elIzquierdo, NodoArbolBin elDerecho){
        this.elemento= elElemento;
        this.derecho= elDerecho;
        this.izquierdo=elIzquierdo;
    }
    public Object getElemento(){
        return this.elemento;
    }
    public NodoArbolBin getIzquierdo(){
        return this.izquierdo;
    }
    public NodoArbolBin getDerecho(){
        return this.derecho;
    }
    public void setElemente( Comparable elElemento){
        this.elemento=elElemento;
    }
    public void setIzquierdo(NodoArbolBin elIzquierdo){
        this.izquierdo= elIzquierdo;
    }
    public void setDerecho(NodoArbolBin elDerecho){
        this.derecho = elDerecho;
    }
}
