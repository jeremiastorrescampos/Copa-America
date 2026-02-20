
package Estructuras.TablaHashAbierto;

import java.util.Objects;

public class NodoHash {
    private Object elem;
    private NodoHash enlace;

    public NodoHash(Object elem, NodoHash enlace) {
        this.elem = elem;
        this.enlace = enlace;
    }

    public Object getElem() {
        return elem;
    }

    public NodoHash getEnlace() {
        return enlace;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setEnlace(NodoHash enlace) {
        this.enlace = enlace;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.elem);
        hash = 41 * hash + Objects.hashCode(this.enlace);
        return hash;
    }

    @Override
    public boolean equals(Object obj) { //Preguntar el compareTo
        return(this.elem.equals(obj));
       
    }

    @Override
    public String toString() {
        return  elem.toString();
    }
    
    
    
}
