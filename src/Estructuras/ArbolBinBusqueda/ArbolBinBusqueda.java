package Estructuras.ArbolBinBusqueda;

import Estructuras.lineales.listaDinamica.Lista;

public class ArbolBinBusqueda {
    
    NodoArbolBin raiz;

    public ArbolBinBusqueda(){
        raiz=null;
    }
    public boolean insertar(Comparable elem){
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz= new NodoArbolBin(elem, null, null);
            
        }else{
            exito= insertarAux(this.raiz, elem);
        }
        return exito;
    }
    public boolean insertarAux(NodoArbolBin ra, Comparable elem){
        boolean exito = true;
        if (elem.compareTo(ra.getElemento())==0) {
            exito = false;
        }else if (elem.compareTo(ra.getElemento())<0) {
            if (ra.getIzquierdo()!=null) {
                exito= insertarAux(ra.getIzquierdo(), elem);
                
            }else{
                ra.setIzquierdo(new NodoArbolBin(elem, null, null));
            }
            
        }else{
            if (ra.getDerecho()!=null) {
                exito= insertarAux(ra.getDerecho(), elem);
                
            }else{
                ra.setDerecho(new NodoArbolBin(elem, null, null));
            }
        }
        return exito;
    }

    public boolean pertence(Comparable elem){
        boolean exito = false;
        if (this.raiz != null) {
            exito= pertenceAux(raiz, elem);
        }
        return exito;
    }
    private boolean pertenceAux(NodoArbolBin ra, Comparable elem){
        boolean exito= false;
        if (ra!=null) {
            if (elem.compareTo(ra.getElemento())==0) {
                exito = true;
            }else if (elem.compareTo(ra.getElemento())<0) {
            
                exito= pertenceAux(ra.getIzquierdo(), elem);
                
            }else{
                exito= pertenceAux(ra.getDerecho(), elem);
            }
        }
        return exito;
    }
    public boolean eliminar(Comparable elem){
        boolean exito=false;
        if (this.raiz!=null) {
            exito = eliminarAux(raiz, elem);
            
        }
        return exito;
    }
    private boolean eliminarAux(NodoArbolBin ra, Comparable elem) {
        boolean exito=false;
        if (ra.getIzquierdo()!=null) {
            if (elem.compareTo(ra.getIzquierdo().getElemento())==0) {
                ra.setIzquierdo(ra.getIzquierdo().getIzquierdo());
                exito = true;
            }
        }if (ra.getDerecho()!=null) {
            if (elem.compareTo(ra.getDerecho().getElemento())==0) {
                ra.setDerecho(ra.getDerecho().getDerecho());
                exito = true;
        }}
        if (ra.getIzquierdo()!=null) {
            if (elem.compareTo(ra.getElemento())<0 && !exito) {
                exito= eliminarAux(ra.getIzquierdo(), elem);
            }else if (ra.getDerecho()!=null) {
                if (!exito) {
                    exito= eliminarAux(ra.getDerecho(), elem);
                }
            }
        }
        
        return exito;
    }

    public boolean esVacio(){
        return this.raiz==null;
    }
    public Object maxElem(){
        Object max=null;
        if (raiz!=null) {
            max=maxElemAux(raiz);
        }
        
        return max;
    }
    private Object maxElemAux(NodoArbolBin ra){
        Object max=null;
        if (ra!=null) {
            if (ra.getDerecho()!=null) {
                max=maxElemAux(ra.getDerecho());
            }else{
                max=ra.getElemento();
            }
        }
        return max;
    }

    public Object minElem(){
        Object max=null;
        if (raiz!=null) {
            max=minElemAux(raiz);
        }
        return max;
    }
    private Object minElemAux(NodoArbolBin ra){
        Object max=null;
        if (ra!=null) {
            if (ra.getIzquierdo()!=null) {
                max=minElemAux(ra.getIzquierdo());
            }else{
                max=ra.getElemento();
            }
        }
        return max;
    }
    public Lista listarPorRango(Comparable min, Comparable max){
        Lista list=new Lista();
        listarPorRangoAux(min, max, raiz,list);
        return list;
    }
    private void listarPorRangoAux(Comparable min, Comparable max, NodoArbolBin ra, Lista list){
        if (ra!=null) {
            if (min.compareTo(ra.getElemento())<0) {
                listarPorRangoAux(min, max, ra.getIzquierdo(),list);
                
            }
            if (min.compareTo(ra.getElemento())<=0 && max.compareTo(ra.getElemento())>=0) {
                list.insertar(ra.getElemento(), list.longitud()+1);
            }
            
            if (min.compareTo(ra.getElemento())>0) {
                listarPorRangoAux(min, max, ra.getDerecho(),list);
            }

            
        }

    }

//Listado-----------------------------------------------
            public Lista listaPreorden(){
            Lista lis = new Lista();
            listaPreordenAux( lis, this.raiz);
            return lis;
        }
        private void listaPreordenAux( Lista lis, NodoArbolBin n){
        if (n!=null) {
            
            lis.insertar(n.getElemento(), lis.longitud()+1);
            listaPreordenAux(lis,n.getIzquierdo());
            listaPreordenAux(lis, n.getDerecho());
        }
        }
        public Lista listarInorden(){
            Lista lis = new Lista();
            listarInordenAux( lis, this.raiz);
            return lis;
        }
        public void listarInordenAux(Lista lis, NodoArbolBin n){
            
            if (n != null) {
                
                listarInordenAux(lis,n.getIzquierdo());
                lis.insertar(n.getElemento(), lis.longitud()+1);
                listarInordenAux(lis, n.getDerecho());
            }
        }

        public Lista listarPosOrden(){
            Lista lis = new Lista();
            listarPosOrdenAux( lis, this.raiz);
            return lis;
        }
        public void listarPosOrdenAux(Lista lis, NodoArbolBin n){
            
            if (n != null) {
                
                listarPosOrdenAux(lis,n.getIzquierdo());
                listarPosOrdenAux(lis, n.getDerecho());
                lis.insertar(n.getElemento(), lis.longitud()+1);
            }
        }
        //public ArbolBinBusqueda clonarInvertido(){
        //    ArbolBinBusqueda arbolito = new ArbolBinBusqueda();
        //    if (raiz!=null) {
        //        arbolito.raiz= clonarInvertidoAux(raiz);
        //        
        //    }
        //    return arbolito;
        //}
        //private NodoArbolBin clonarInvertidoAux(NodoArbolBin raiz){
        //    NodoArbolBin nuevo = null;
        //    Comparable aux;
        //    if (raiz!=null) {
        //        nuevo = new NodoArbolBin(raiz.getElemento(), clonarInvertidoAux(raiz.getDerecho()), clonarInvertidoAux( raiz.getIzquierdo()));
        //        
        //    }
        //    return nuevo;
        //}
}
