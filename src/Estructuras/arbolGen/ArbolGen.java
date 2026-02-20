package Estructuras.arbolGen;
import Estructuras.lineales.listaDinamica.Lista;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen(){
        this.raiz=null;
    }
    //public boolean insertar(Object elemPadre,Object elemNew){
    //    boolean validador =true;
    //    if (raiz !=null) {
    //        validador=insertarAux(elemPadre,elemNew,raiz);
    //
    //    }else{
    //        raiz=new NodoGen(elemNew, null, null);
    //    }
    //    return validador;
    //}
    //private boolean insertarAux(Object elemPadre, Object elemNew, NodoGen raizAux){
    //    boolean validador = false;
    //    if (raizAux!=null) {
    //        System.out.println(raizAux.getElem());
    //        if(raizAux.getElem().equals(elemPadre)){
    //            validador=true;
    //
    //            if (raizAux.getHijoIzquierdo()==null) {
    //                raizAux.setHijoIzquierdo(new NodoGen(elemNew, null, null));
    //            }else{
    //                raizAux=raizAux.getHijoIzquierdo();
    //                while (raizAux!=null) {
    //                    if (raizAux.getHermanoDerecho()==null) {
    //                        raizAux.setHermanoDerecho(new NodoGen(elemNew, null, null));
    //                        raizAux=raizAux.getHermanoDerecho();
    //                    }
    //                    raizAux=raizAux.getHermanoDerecho();
    //            }}
    //        }
    //        if (!validador) {
    //            if (raizAux.getHijoIzquierdo()!=null) {
    //                validador=insertarAux(elemPadre, elemNew, raizAux.getHijoIzquierdo());
    //            }
    //
    //        }
    //        if (!validador) {
    //            if (raizAux.getHermanoDerecho()!=null) {
    //                validador=insertarAux(elemPadre, elemNew, raizAux.getHermanoDerecho());
    //            }
    //
    //        }
    //
            
    //    }
    //    return validador;
    //}
    public boolean insertar(Object elemPadre,Object newElem){
        NodoGen nodoPadre=obtenerNodo(elemPadre, raiz);
        boolean validador=true;

        if (raiz==null) {
            raiz= new NodoGen(newElem, null, null);
        }else if (nodoPadre!=null) {
            nodoPadre.setHijoIzquierdo(new NodoGen(newElem,null , nodoPadre.getHijoIzquierdo() ));
        }else{
            validador=false;
        }

        return validador;
    }
    private NodoGen obtenerNodo(Object elemPadre, NodoGen raiz){
        NodoGen newNodo=null;
        while (raiz!=null && newNodo==null) {
            if (raiz.getElem().equals(elemPadre)) {
                newNodo=raiz;
            }else{
                newNodo=obtenerNodo(elemPadre, raiz.getHijoIzquierdo());
                raiz=raiz.getHermanoDerecho();
            }
        }
        return newNodo;
    }
    
    public boolean pertence(Object elem){
        boolean validador = false;
        if (obtenerNodo(elem, raiz)!=null) {
            validador=true;
        }
        return validador;
    }
    public Lista ansestro(Object elem){
        Lista listElem=new Lista();
        crearListaAnsestro(listElem,elem,this.raiz);
        return listElem;
    }
    private boolean crearListaAnsestro(Lista listElem, Object elem, NodoGen raiz){
        boolean verificador = false;
        while (raiz!=null && verificador != true ) {
            
            if (raiz.getElem().equals(elem)) {
                verificador=true;
            }else{
                verificador=crearListaAnsestro(listElem, elem, raiz.getHijoIzquierdo());
                if (verificador) {
                    if (raiz!=null) {
                        
                        listElem.insertar(raiz.getElem(), 1);
                    }
                }
                raiz=raiz.getHermanoDerecho();
            }
    }
    return verificador;
    }
    public boolean esVacia(){
        return this.raiz==null;
    }
    public int altura(){
        int valor = -1;
        int comparador=0;
        
        valor=encontrarAltura(raiz, valor,comparador);
        
        
        
    
        return valor;
    }
    private int encontrarAltura(NodoGen raiz, int valor,int comparador){
        if (raiz!=null) {
            valor++;
        }
        while (raiz!=null) {
                comparador=encontrarAltura(raiz.getHijoIzquierdo(), valor,comparador);
                raiz=raiz.getHermanoDerecho();
                if (raiz==null) {
                    if (valor>comparador) {
                        comparador=valor;
                    }
                }
        }
        return comparador;
    }
    public int nivel(Object valor){
        int lvl=0;
        int [] l= new int[1];
        calcularNivel(valor, l, raiz);
        lvl = l[0];
        return lvl;
    }
    private boolean calcularNivel(Object valor, int [] lvl, NodoGen raiz){
        boolean verificador = false;
        while (raiz != null && verificador!=true) {
            verificador=calcularNivel(valor, lvl, raiz.getHijoIzquierdo());
            if (raiz.getElem().equals(valor)) {
                verificador=true;
                lvl[0]++;

            }else if(verificador){
                lvl[0]++;
            }
            raiz= raiz.getHermanoDerecho();
            
        }
        return verificador;

    }
    public Object padre(Object elem){
        
        Object valor=buscarpadre(elem, raiz);
        return valor;
    }
    public Object buscarpadre(Object elem, NodoGen raiz) {
        Object valor = null;
    
        if (raiz != null) {
            NodoGen hijo = raiz.getHijoIzquierdo();

            while (hijo != null && valor == null) {
                if (hijo.getElem().equals(elem)) {
                    valor = raiz.getElem();
                } else {
                    hijo = hijo.getHermanoDerecho();
                }
            }
    
            if (valor == null && raiz.getHijoIzquierdo() != null) {
                valor = buscarpadre(elem, raiz.getHijoIzquierdo());
            }
            if (valor == null && raiz.getHermanoDerecho() != null) {
                valor = buscarpadre(elem, raiz.getHermanoDerecho());
            }
        }
    
        return valor;
    }



    public ArbolGen clone(){
        ArbolGen arbolito=new ArbolGen();
        arbolito.raiz=clonador(raiz);
        return arbolito;
    }
    private NodoGen clonador(NodoGen raiz){
        NodoGen clone=null;
        if (raiz!=null) {
            
                clone = new NodoGen(raiz.getElem(),clonador(raiz.getHijoIzquierdo()), clonador(raiz.getHermanoDerecho()));
        
        }

        
        return clone;
    }
    public boolean insertarPorPosicion(Object newElm, int posicion){
        NodoGen arr[]=new NodoGen[1];
        insertadorPosicional(arr, posicion,this.raiz);
        return true;
    }
    private int insertadorPosicional(NodoGen[] arrNodo, int posicion,NodoGen raiz){
        int cont=0;
        if (raiz!= null) {
            NodoGen hijo = raiz.getHijoIzquierdo();

            while (hijo != null) {
            
        }
        
    }return cont;
}
    public void vaciar(){
        raiz=null;
    }
        public String toString(){
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen raiz){
        String cad = "";
        if (raiz != null) {
            cad += raiz.getElem().toString()+"->";
            NodoGen hijo= raiz.getHijoIzquierdo();
            while (hijo!=null) {
                cad+= hijo.getElem().toString()+",";
                hijo=hijo.getHermanoDerecho();
            }
            hijo= raiz.getHijoIzquierdo();
            while (hijo!=null) {
                cad += "\n"+toStringAux(hijo);
                hijo=hijo.getHermanoDerecho();
            }
            
        }
        return cad;
    }
    //public boolean verificarCamino(Lista camino){
    //    boolean validador = false;
    //    if (this.raiz!=null&& !camino.esVacia()) {
    //        validador= verificarCaminoAux(camino,this.raiz);
    // 
    //    }
    //    return validador;
    //}
    //private boolean verificarCaminoAux(Lista camino, NodoGen ra){
    //    boolean validador = false;
    //    NodoGen hijo=null;
    //    if (ra!=null) {
    //        if (ra.getElem().equals(camino.recuperar(1))) {
    //            camino.eliminar(1);
    //            hijo= ra.getHijoIzquierdo();
    //            while (hijo!=null && !validador) {
    //                if (hijo.getElem().equals(camino.recuperar(1))) {
    //                    validador=true;
    //                    if (!camino.esVacia()) {
    //                        validador=verificarCaminoAux(camino, hijo);
    //                    }
    //                }
    //                hijo=hijo.getHermanoDerecho();
    //            }
    //        }
    //    }
    //    return validador;
    //}
    public boolean verificarCamino(Lista camino){
        boolean verificador = false;
        if (!camino.esVacia()&&this.raiz!=null) {
            verificador= verificarCaminoAux(camino, this.raiz);
        }
        return verificador;
    }
    private boolean verificarCaminoAux(Lista camino, NodoGen raiz){
        boolean verificador = false;

        if (raiz!=null) {
            if (camino.recuperar(1).equals(raiz.getElem())) {
                camino.eliminar(1);
                if (camino.esVacia()) {
                    verificador=true;
                }
            }
            if (!verificador && raiz.getHermanoDerecho()!=null) {
                verificador=verificarCaminoAux(camino, raiz.getHermanoDerecho());
            }
            if (!verificador && raiz.getHijoIzquierdo()!=null) {
                verificador=verificarCaminoAux(camino, raiz.getHijoIzquierdo());
            }
        }
        return verificador;
    }

}
