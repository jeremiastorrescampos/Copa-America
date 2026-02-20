/**
 * Integrantes del grupo:
 * Torres Jeremias  Fai-4057
 * Torres Ilde Andre Fai-3916
 * Pereira Marisol Fai-4672
 */
package Estructuras.lineales.pilaDinamicas;

import Estructuras.lineales.Nodo.Nodo;

public class Pila {

    private Nodo tope;

    public Pila(){
        this.tope=null;
    }
    public boolean apilar(Object nuevoElemento){
        Nodo nuevo = new Nodo(nuevoElemento, this.tope);
        this.tope = nuevo;
        return true;
    }
    public boolean desapilar(){
        boolean validador = false;
        if (this.tope != null) {
            this.tope=this.tope.getEnlace();
            validador = true;
            
        }
        
        

        return validador;
    }
    public Object obtenerTope(){
        Object elem;
        if (esVacia()) {
            elem = null;
        }else{
            elem = this.tope.getElemento();
        }
        return elem;
    }
    public boolean vaciar(){
        this.tope=null;
        return true;
    }
    public boolean esVacia(){
        return this.tope == null;
    }

    public Pila Clone() {
        Pila nuevaPila = new Pila();
        nuevaPila.tope = clonador(this.tope);
        return nuevaPila;
    }

    private Nodo clonador(Nodo topeCopia) {
        Nodo nuevoNodo = null;
        if (topeCopia != null) {
            nuevoNodo = clonador(topeCopia.getEnlace());
            nuevoNodo = new Nodo(topeCopia.getElemento(), nuevoNodo);
        }
        return nuevoNodo;
    }
    
    public String toString(){
        String s = "";
        if (this.tope == null) {
            s = "[ ]";
        }else{
            Nodo aux = this.tope;
            s = "[";
            while (aux != null) {
                s += aux.getElemento().toString();
                aux=aux.getEnlace();
                if (aux != null) {
                    s+=",";
                }
            }
            s +="]";
            
        }
        return s;
    }


}