package Estructuras.lineales.colaEstatica;
public class Cola{
    private Object[] arr;
    private int frente;
    private int fin;
    private static final int tamaño = 8;
    public Cola(){
        this.arr = new Object[tamaño];
        this.fin = 0;
        this.frente=0;
    }

    public boolean sacar(){
        boolean validador = false;
        if (!esVacia()) {
            this.arr[this.frente] = null;
            this.frente = (this.frente + 1) % tamaño;
            validador = true;
        }
        return validador;
    }
    
    public boolean meter(Object elemento){
        boolean exito = true;
        if (this.arr[(this.fin +1)%tamaño]==null) {
            this.arr[this.fin] = elemento;
            this.fin = (this.fin +1)%tamaño;
        }else{
            exito=false;
        }
        
        return exito;
    }
    public Object obtenerTope(){
        Object tope = "null";
        if (this.arr[this.frente]!=null) {
            tope = this.arr[this.frente];
        }
        return tope;
    }
    public boolean esVacia(){
        
        return this.fin==this.frente;
    }
    public void vaciar(){
        while (this.frente != this.fin && this.arr[this.frente]!=null ) {
            this.arr[this.frente]=null;
            this.frente = (this.frente +1)%tamaño;
        }
        //int vaciador=0;
        //while (vaciador != this.arr.length) {
        //    this.arr[vaciador]=null;
        //    vaciador++;
        //}
        //this.fin=0;
        //this.frente=0;
    }
    public Cola clone(){
        Cola clone = new Cola();
        clone.arr = this.arr.clone();
        clone.fin = this.fin;
        clone.frente = this.frente;
        return clone;
    }
    public String toString(){
        String toString="";
        int frentetemp = this.frente;
        while (frentetemp != this.fin && this.arr[frentetemp]!=null ) {
            toString +=" "+this.arr[frentetemp];
            frentetemp = (frentetemp +1)%tamaño;
        }
        return toString;
    }
    

}