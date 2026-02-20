package Estructuras.arbolGen;
public class NodoGen {
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;

    NodoGen(Object elElem,NodoGen elHijoIzquiedo, NodoGen elHermanoDerecho ){
        this.elem = elElem;
        this.hijoIzquierdo = elHijoIzquiedo;
        this.hermanoDerecho = elHermanoDerecho;
    }
    public Object getElem(){
        return this.elem;
    }
    public NodoGen getHijoIzquierdo(){
        return this.hijoIzquierdo;
    }
    public NodoGen getHermanoDerecho(){
        return this.hermanoDerecho;
    }
    public void setElem(Object elElem){
        this.elem = elElem;
    }
    public void setHijoIzquierdo(NodoGen elHijoIzquierdo){
        this.hijoIzquierdo=elHijoIzquierdo;
    }
    public void setHermanoDerecho(NodoGen elHermanoDerecho){
        this.hermanoDerecho=elHermanoDerecho;
    }
}
