
package Estructuras.Grafos;

public class NodoAdyacente { //Ver el tema del compareTo en los dos nodos
    
    private NodoVertice vertice;
    private NodoAdyacente sigAdy;
    private Object etiqueta;

    public NodoAdyacente(NodoVertice vertice, NodoAdyacente sigAdy, Object etiqueta) {
        this.vertice = vertice;
        this.sigAdy = sigAdy;
        this.etiqueta = etiqueta;
    }

    public NodoVertice getVertice() {
        return vertice;
    }

    public void setVertice(NodoVertice vertice) {
        this.vertice = vertice;
    }

    public NodoAdyacente getSigAdy() {
        return sigAdy;
    }

    public void setSigAdy(NodoAdyacente sigAdy) {
        this.sigAdy = sigAdy;
    }

    public Object getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Object etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    public String toString(){
        
        return (this.vertice.toString() + ";"+  this.etiqueta);
        
        
        
    }
    
    public boolean equals(Object elem){
        
        return (this.vertice.equals(elem));
    }


    
}