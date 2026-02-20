package Estructuras.arbolHeap;

public  class  ArbolHeapMin {
    private Comparable [] heap;
    private int ultimo;
    private int tamanio = 20;
    public ArbolHeapMin(){
        this.heap= new Comparable[tamanio];
        this.ultimo=0;
    }
    public boolean insertar(Comparable valor){
        boolean exito= false;
        if (heap.length>ultimo) {
            this.heap[ultimo]=valor;
            hacerSubir(ultimo);
            ultimo++;
            exito=true;
        }
        return exito;
    }
    public boolean eliminarCima(){
        boolean exito;
        if (this.ultimo==0) {
            exito = false;
        }else{
            this.heap[1]=this.heap[ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito=true;
        }
        return exito;
    }
    private void hacerBajar(int posPadre){
        int posH;
        Comparable temp=this.heap[posPadre];
        boolean salir=false;
        while (!salir) {
            posH = posPadre * 2;
            if (posH<=this.ultimo) {
                if (posH<this.ultimo) {
                    if (this.heap[posH +1].compareTo(this.heap[posH])<0) {
                        posH++;
                        
                    }
                    
                }
                if (this.heap[posH].compareTo(temp)<0) {
                    this.heap[posPadre]=this.heap[posH];
                    this.heap[posH]=temp;
                    posPadre=posH;
                    
                }else{
                    salir=true;
                }
            }else{
                salir=true;
            }
        }
    }
    private void hacerSubir(int posHijo) {
        int posPadre;
        Comparable temp = this.heap[posHijo];
        boolean salir = false;
    
        while (!salir && posHijo > 0) {
            posPadre = (posHijo - 1) / 2;
    
            if (this.heap[posPadre].compareTo(temp) > 0) {
                this.heap[posHijo] = this.heap[posPadre];
                posHijo = posPadre;
            } else {
                salir = true;
            }
        }
    
        this.heap[posHijo] = temp;
    }
    

    public Comparable recuperarSima(){
        Comparable valor="nada";
        if (!esVacia()) {
            valor=this.heap[0];
        }
        return valor;
    }
    public boolean esVacia(){
        return (ultimo==0&& heap[0]==null);
    }
    public String toString(){
        int cont =0;
        String cadena = "[ ";
        
        while (cont != ultimo) {

                cadena += ""+ this.heap[cont]+",";
            
            
            cont++;
        }
        
        return cadena;
    }
    
    
}
