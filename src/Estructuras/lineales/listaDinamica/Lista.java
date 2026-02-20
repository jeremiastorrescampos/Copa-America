package Estructuras.lineales.listaDinamica;
import Estructuras.lineales.Nodo.Nodo;
public class Lista {
    private Nodo cabecera;
    private  int longitud =0;
    
    public Lista(){
        cabecera = null;
    }
    public boolean insertar(Object nuevoElemento, int posicion){
        boolean exito = true;
        if (posicion<1||posicion>longitud+1) {
            exito=false;
            
        }else{if (posicion==1) {
            this.cabecera =new Nodo(nuevoElemento,cabecera);
        } else {
            int i = 1;
            Nodo aux = this.cabecera;
            while (i<posicion-1) {
                aux= aux.getEnlace();
                i++;
            }
            Nodo nuevo = new Nodo(nuevoElemento, aux.getEnlace());
            aux.setEnlace(nuevo);
        }
        longitud++;
        }
        return exito;
    }

    public boolean eliminar(int posicion){
        boolean exito = true;
        int i = 1;
        Nodo aux = this.cabecera;
        if (posicion<1||posicion>longitud+1) {
            exito = false;
        }else{
            if (posicion ==1) {
                if (this.cabecera.getEnlace()==null) {
                    this.cabecera=null;
                }else{
                    this.cabecera=this.cabecera.getEnlace();
                }

            } else {

                
                while (i<posicion-1) {
                    aux= aux.getEnlace();
                    i++;
                
                }
                Nodo aux1 = aux;
                aux1 = aux1.getEnlace();
                aux.setEnlace(aux1.getEnlace());
                longitud--;
            }
        }
        return exito;
    }
        public Object recuperar(int posicion){
        Object elem = null;
        Nodo aux = this.cabecera;
        int i = 1;

        if (posicion >= 1 && posicion <= this.longitud()) {
            while (i < posicion) {
                aux = aux.getEnlace();
                i++;
            }
            elem = aux.getElemento();
        }
        return elem;
    }
    
    public int localizar(Object elem) {
        int pos = -1;
        int i = 0;
        Nodo aux = this.cabecera;
        while (pos == -1 && aux != null) {//corta cuando encuentra un elemento en la lista igual al ingresado o se termina la lista
            if (aux.getElemento().equals(elem)) {//compara el elemento ingresado con el elemento de la pocision en la lista
                pos = i + 1;
            }
            aux = aux.getEnlace();
            i++;
        }
        return pos;
    }
    public void vaciar(){
        this.cabecera=null;
    }
    public boolean esVacia(){

        return this.cabecera == null;
    }

    public Lista Clone() {
        Lista nuevaLista = new Lista();
        nuevaLista.cabecera = clonador(this.cabecera);
        return nuevaLista;
    }

    private Nodo clonador(Nodo cabeceraCopia) {
        Nodo nuevoNodo = null;
        if (cabeceraCopia != null) {
            nuevoNodo = clonador(cabeceraCopia.getEnlace());
            nuevoNodo = new Nodo(cabeceraCopia.getElemento(), nuevoNodo);
        }
        return nuevoNodo;
    }


    public int longitud(){
        int longi = 0;
        Nodo aux = this.cabecera;
        while (aux != null) {
            aux = aux.getEnlace();
            longi++;
    }
    return longi;
}
    public String toString(){
        int num=1;
        String cadena="";
        Nodo aux = this.cabecera;
        if (aux==null) {
            cadena="la Cadena esta vacia";
        }else{
            while (aux != null) {
            
                cadena+=  aux.getElemento()+" ";
                aux = aux.getEnlace();
                num++;
            }
            
        }cadena = "[ "+cadena+"]";
        
        return cadena;
    }

    public boolean AgregarElemento(Object elem, int x){
        
        
        int cont = 1;
        this.cabecera= new Nodo(elem, cabecera);
        Nodo aux= this.cabecera;
        while (aux.getEnlace()!=null) {
            if (cont%(x+1)==0) {
                Nodo nodo= new Nodo(elem, aux.getEnlace());
                aux.setEnlace(nodo);
            }
            aux=aux.getEnlace();
            cont++;
        }
        return true;

    }
    //private void agregadorElem(Object elem, int x, int cont){
    //    if (cont <= longitud()) {
    //
    //        agregadorElem(elem, x, cont + x);
    //        insertar(elem, cont);
    //    }
    //}

    public void intercalar(Lista l2){
        Nodo aux = cabecera;
        int x=1;
        while (l2.longitud>x) {
            aux=aux.getEnlace();
            if (aux.getEnlace()==null) {
                aux.setEnlace(new Nodo(l2.recuperar(x), null));
                x++;
            }
            
            
        }
    }
}

