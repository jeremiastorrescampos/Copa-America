package Estructuras.ArbolBin;

public class NodoArbol {
    Object elemento;
    NodoArbol izquierdo;
    NodoArbol derecho;
    

    public NodoArbol(Object elElemento, NodoArbol elIzquierdo, NodoArbol elDerecho){
        this.elemento= elElemento;
        this.derecho= elDerecho;
        this.izquierdo=elIzquierdo;
    }
    public Object getElemento(){
        return this.elemento;
    }
    public NodoArbol getIzquierdo(){
        return this.izquierdo;
    }
    public NodoArbol getDerecho(){
        return this.derecho;
    }
    public void setElemente( Object elElemento){
        this.elemento=elElemento;
    }
    public void setIzquierdo(NodoArbol elIzquierdo){
        this.izquierdo= elIzquierdo;
    }
    public void setDerecho(NodoArbol elDerecho){
        this.derecho = elDerecho;
    }
}
