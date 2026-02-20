package Estructuras.lineales.Nodo;
public class Nodo {
    private Object element;
    private Nodo enlace;

    public Nodo( Object elem, Nodo enlace){
        this.element = elem;
        this.enlace = enlace;
    }
    public void setElemento(Object elem){
        this.element = elem;
    }
    public void setEnlace(Nodo enlace){
        this.enlace = enlace;
    }
    public Object getElemento(){
        return this.element;

    }
    public Nodo getEnlace(){
        return this.enlace;
    }
}
