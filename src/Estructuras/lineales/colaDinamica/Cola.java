package Estructuras.lineales.colaDinamica;
import Estructuras.lineales.Nodo.Nodo;

public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola(){
        this.fin = null;
        this.frente = null;
    }
    
    public boolean ponerElemento(Object elemento){
        Nodo nuevoElemento = new Nodo(elemento, null);
        if (this.fin != null) {
            this.fin.setEnlace(nuevoElemento);
        }
        this.fin = nuevoElemento;
        if (this.frente == null) {
            this.frente = nuevoElemento;
        }
        return true;
    }
    
    public boolean sacarElemento(){
        boolean exito = false;
        if (this.frente != null) {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
            exito = true;
        }
        return exito;
    }
    
    public Object obtenerFrente(){
        Object elemento= null;
        if (this.frente != null) {
            elemento= this.frente.getElemento();
        }
        return elemento;
    }
    
    public boolean esVacia(){
        return this.frente == null;
    }
    
    public void vaciar(){
        this.frente = null;
        this.fin=null;
    }

    public Cola clone(){
        Cola nuevaCola = new Cola();
        Nodo aux = this.frente;
        while (aux!=null) {
            Nodo nodo = new Nodo(aux.getElemento(),null );
            if (nuevaCola.fin != null) {
                nuevaCola.fin.setEnlace(nodo);
            }
            nuevaCola.fin = nodo;
            if (nuevaCola.frente == null) {
                nuevaCola.frente = nodo;
            }
            aux = aux.getEnlace();
        }
        return nuevaCola;
    }

    public String toString(){
        String toString="";
        Nodo aux = this.frente;
        while (aux != null) {
            toString +=" "+aux.getElemento();
            aux = aux.getEnlace();
        }
        return toString;
    }


}
