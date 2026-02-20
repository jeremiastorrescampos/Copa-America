package Estructuras.Grafos;

public class NodoVertice{

    private Object elem;
    private NodoVertice sigVertice;
    private NodoAdyacente primerAdy;

    public NodoVertice(Object elem, NodoVertice sigVertice) {
        this.elem = elem;
        this.sigVertice = sigVertice;

    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public NodoVertice getSigVertice() {
        return sigVertice;
    }

    public void setSigVertice(NodoVertice sigVertice) {
        this.sigVertice = sigVertice;
    }

    public NodoAdyacente getNodoAdy() {
        return primerAdy;
    }

    public void setNodoAdy(NodoAdyacente primerAdy) {
        this.primerAdy = primerAdy;
    }

  

}