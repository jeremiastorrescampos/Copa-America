/**
 * Integrantes del grupo:
 * Torres Jeremias  Fai-4057
 * Torres Ilde Andre Fai-3916
 * Pereira Marisol Fai-4672
 */
package Estructuras.lineales.pilaEstaticas;
public class Pila{
    private Object[] arreglo;
    private int tope;
    private final int tamaño = 10;

    public Pila(){
        this.arreglo = new Object[tamaño];
        this.tope = -1;
    }
    public boolean apilar(Object nuevoElem){
        boolean exito;
        if (this.tope+1>=this.tamaño) {
            exito = false;
        }else{
            this.tope++;
            this.arreglo[tope]=nuevoElem;
            exito = true;
        }
        return exito;

    }

    public boolean desapilar(){
        boolean exito = false;
        if (tope>-1) {
            this.arreglo[tope]=null;
            this.tope--;
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope(){
        Object element;
        if (tope!=-1) {
            element = arreglo[tope];
        }else{
            element = "*";
        }
        return element;
    }
    public boolean esVacia(){
        boolean validador=false;
        if (this.tope<0) {
            validador=true;
        }
        return validador;
    }
    public void vaciar(){
        
        while (this.tope >-1) {
            arreglo[this.tope]=null;
            this.tope--;
            
        }
        this.tope --;
    }
    public Pila Clone(){
        Pila nuevapila = new Pila();
        nuevapila.arreglo = this.arreglo.clone();
        nuevapila.tope = this.tope;
        return nuevapila;
    }

    public String toString (){
        String datos;

        if (0<= tope){
            datos= "[";
            for (int i = 0 ; i< this.tamaño; i++){
                if(arreglo[i]!=null){
                    datos =datos+"" + arreglo[i] ;
                if (arreglo[i]!=null &&i< this.tope){
                    datos =datos+ ",";
                }
                }
                
                else{
                    datos =datos+ " *";
                }
                
            }
            datos= datos+" ]";
            
        }
        else{
            datos= "[ ]";
        }
        return datos  ;
    }
    public int numtope(){
        return this.tope;
    }


}
