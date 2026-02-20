package Estructuras.ArbolAVL;

public class NodoAVL {
    private Comparable elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable e, NodoAVL izq, NodoAVL der){
        this.elem = e;
        this.izquierdo = izq;
        this.derecho = der;
        recalcularAltura();
    }

    public NodoAVL(Comparable elemento){
        this.altura = 0;
        this.izquierdo = null;
        this.derecho = null;
        this.elem = elemento;
    }

    public void recalcularAltura(){
        int nIzq, nDer;
        if(this.izquierdo == null){
            nIzq = -1;
        }else{
            nIzq = this.izquierdo.altura;
        }
        if(this.derecho == null){
            nDer = -1;
        }else{
            nDer = this.derecho.altura;
        }

        if (nIzq >= nDer){
            this.altura = nIzq + 1;
        }else{
            this.altura = nDer + 1;
        }
    }

    public int getAltura() {
        return altura;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public Comparable getElem() {
        return elem;
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }
    
    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }



}
