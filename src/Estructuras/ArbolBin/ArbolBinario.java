package Estructuras.ArbolBin;
import Estructuras.lineales.listaDinamica.Lista;
public class ArbolBinario{
    NodoArbol raiz;

    public ArbolBinario(){
        this.raiz = null;
    }
    public boolean insertar(Object newElem, Object padre, char lugar){
        boolean exito = true;
        if (this.raiz==null) {
            this.raiz = new NodoArbol(newElem, null, null);
            
        }else{
            NodoArbol nPadre = obtenerNodo(this.raiz, padre);
            if (nPadre != null) {
                if (lugar == 'I' && nPadre.getIzquierdo()==null) {
                    nPadre.setIzquierdo(new NodoArbol(newElem, null, null));
                    
                }else if (lugar == 'D' && nPadre.getDerecho()==null) {
                    nPadre.setDerecho(new NodoArbol(newElem, null, null));
                }else{
                    exito = false;
                }
                
            }else{
                exito = false;
            }
        }
        return exito;
    }
    private NodoArbol obtenerNodo(NodoArbol n, Object buscado){
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElemento().equals(buscado)) {
                resultado=n;
            }else{
                
            resultado = obtenerNodo(n.getIzquierdo(), buscado);
            if (resultado==null) {
                resultado = obtenerNodo(n.getDerecho(), buscado);
                
            }
            }
        }
        return resultado;
    }
public boolean esVacio(){
    return this.raiz == null;
}
    public Object padre(Object elemento){
        Object padree=null;
        padree=buscarPapa(this.raiz, elemento);

        return padree;
    }
    private Object buscarPapa(NodoArbol nodo, Object elem){
        Object element=null;
        if (nodo!=null) {
            
                if (((nodo.getIzquierdo()!=null&&nodo.getIzquierdo().getElemento().equals(elem))||(nodo.getDerecho()!=null&&nodo.getDerecho().getElemento().equals(elem)))) {
                    element= nodo.getElemento();
                }else{
                    if ( nodo.getIzquierdo()!=null) {
                        element=buscarPapa(nodo.getIzquierdo(), elem);
                    }
                
                
                if (element==null) {
                    if (nodo.derecho!=null) {
                        element=buscarPapa(nodo.getDerecho(), elem);
                    }
                    
                }
                    
                    
            }
            
        }
        
        
        
        return element;

    }

    //listar arbol
        public Lista listaPreorden(){
            Lista lis = new Lista();
            listaPreordenAux( lis, this.raiz);
            return lis;
        }
        private void listaPreordenAux( Lista lis, NodoArbol n){
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
        public void listarInordenAux(Lista lis, NodoArbol n){
            
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
        public void listarPosOrdenAux(Lista lis, NodoArbol n){
            
            if (n != null) {
                
                listarPosOrdenAux(lis,n.getIzquierdo());
                listarPosOrdenAux(lis, n.getDerecho());
                lis.insertar(n.getElemento(), lis.longitud()+1);
            }
        }





        //-----------------------------------------------------------------------
        public int nivel(Object elem){
            int lvl = 0;
            lvl=obtenerlvl(elem, this.raiz);
            return lvl;
        }
        private int obtenerlvl(Object elem, NodoArbol nodo) {
            int lvl = -1;
            if (nodo != null) {
                if (nodo.elemento.equals(elem)) {
                    lvl = 0;
                } else {
                    lvl = obtenerlvl(elem, nodo.izquierdo);
                    if (lvl ==-1) {
                        lvl = obtenerlvl(elem, nodo.derecho);
                    }
                    if (lvl != -1) {
                        lvl += 1;
                    }
                }
            }
            return lvl;
        }
        public void vacia(){
            this.raiz=null;

        }
        public ArbolBinario clone(){
            ArbolBinario clon=new ArbolBinario();
            clon.raiz=clonador(this.raiz);
            return clon;
        }
        private NodoArbol clonador(NodoArbol cRaiz){
            NodoArbol nodo = null;
            if (cRaiz!=null) {
                nodo = new NodoArbol(cRaiz.getElemento(),clonador(cRaiz.izquierdo), clonador(cRaiz.derecho));

            }
            return nodo;
        }
        
        public boolean insertarPosicion(Object newElem, int pospadre,char lugar){

            
            NodoArbol nPadre;

            boolean exito = true;
        if (this.raiz==null) {
            this.raiz = new NodoArbol(newElem, null, null);
            
        }else{
            NodoArbol arr[]=new NodoArbol[1];
            
            
            buscarNodo(pospadre, this.raiz,arr);
            nPadre=arr[0];

            
            if (nPadre != null) {
                System.out.println(nPadre.elemento);
                if (lugar == 'I' && nPadre.getIzquierdo()==null) {
                    
                    nPadre.setIzquierdo(new NodoArbol(newElem,null , null));
                    
                }else if (lugar == 'D'&& nPadre.getDerecho()==null ) {
                    nPadre.setDerecho(new NodoArbol(newElem, null, null));
                }else{
                    exito = false;
                }
                
            }else{
                exito = false;
            }
        }
            
            return exito;
        }


        public String toString(){
            String cadena=" ";
            cadena=toStrinaux(this.raiz);
            return cadena;
        }
        private int buscarNodo(int pos, NodoArbol r, NodoArbol [] arr){
            if (r !=null) {
                
                if (pos==0&& arr[0]==null) {
                    arr [0]=r;
                }else{
                    if (r.getIzquierdo()!=null) {
                        pos=buscarNodo(pos-1,r.getIzquierdo(),arr );
                        
                        }
                            if (pos!=0) {
                                if (r.derecho!=null) {
                                    pos=buscarNodo(pos-1,r.getDerecho(),arr);
                                    
                                    }
                            }

                }
                
                
                
            
                
            
        }
            return pos;
        }


        private String toStrinaux(NodoArbol nodo){
            String cadena= " ";
            if (nodo !=null) {
                cadena +=" El padre es: [ "+ nodo.elemento+ " ] \n";
                if (nodo.izquierdo!=null) {
                    cadena+=" su hijo izquiedo es: "+ nodo.izquierdo.elemento+ "       || ";
                }else{
                    cadena+=" su hijo izquiendo es: null   || ";
                }
                if (nodo.derecho!=null)
                {
                    cadena += " su hijo derecho es: "+nodo.derecho.elemento+ " \n";
                }else{
                    cadena+=" su hijo izquiendo es: null \n";
                }
                
                cadena+=toStrinaux(nodo.izquierdo);
                cadena+=toStrinaux(nodo.derecho);
            }
            return cadena;
        }

        public boolean equals(ArbolBinario otro){
            boolean valor = false;
            valor = comparador(otro.raiz,this.raiz);
            return valor;
        }
        private boolean comparador(NodoArbol otro, NodoArbol original){
            boolean valor = true;
                if (otro.getElemento()==original.getElemento()) {
                    if (otro.getIzquierdo()!=null&& original.getIzquierdo()!=null) {
                        valor = comparador(otro.getIzquierdo(), original.getIzquierdo());
                    }else{
                        if (otro.getIzquierdo()!=original.getIzquierdo()) {
                            valor=false;
                        }
                    }

                }
                if (valor!=false) {
                    if (otro.getDerecho()!=null&& original.getDerecho()!=null) {
                        valor = comparador(otro.getDerecho(), original.getDerecho());
                    }else if(otro.getDerecho()!=original.getDerecho()){
                        valor=false;
                    }
                }else{
                    valor=false;
                }


            return valor;
        }

}
