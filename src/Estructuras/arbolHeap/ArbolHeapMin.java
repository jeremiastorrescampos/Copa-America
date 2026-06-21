package Estructuras.arbolHeap;


public class ArbolHeapMin {
    private int tamanio = 20;
    private Comparable[] heap;
    private int ultimo;

    public ArbolHeapMin(){
        this.heap = new Comparable[tamanio];
        this.ultimo = 0;
    }

    public boolean insertar(Comparable elemento){
        boolean verificador = false;
        if(this.heap.length-1 > ultimo){
            this.heap[ultimo+1] = elemento;
            this.ultimo++;
            if (ultimo != 1){
                hacerSubir(ultimo);
            }
            verificador = true;
        }
        return verificador;
    }

    public boolean hacerSubir(int posicion){
        int posP;
        Comparable temp = this.heap[posicion];
        boolean salir = false;
        while (!salir) {
            posP = posicion/2;
            if (posP > 0 && this.heap[posP].compareTo(temp) > 0){
                this.heap[posicion] = this.heap[posP];
                this.heap[posP] = temp;
                posicion = posP;
            }else{
                salir = true;
            }
        }
        return salir;
    }

    public boolean eliminarCima(){
        boolean exito;
        if (this.ultimo == 0){
            exito = false;
        }else{
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private void hacerBajar(int posicion){
        boolean detener = false;
        int posH;
        Comparable temp = this.heap[posicion];
        while (!detener) {
            posH = posicion * 2;
            if (posH <= this.ultimo){
                if (posH < this.ultimo){
                    if(this.heap[posH + 1].compareTo(this.heap[posH]) < 0){
                        posH++;
                    }
                }
                if(this.heap[posH].compareTo(temp) < 0){
                    this.heap[posicion] = this.heap[posH];
                    this.heap[posH] = temp;
                    posicion = posH;
                }else{
                    detener = true;
                }
            }else{
                detener = true;
            }
        }
    }

    public String toString(){
        String cadena = "[";
        int i = 1;
        while (i <= ultimo) {
            if (i == ultimo){
                cadena += this.heap[i];
            }else{
                cadena += this.heap[i] + "/";
            }
            i++;
        }
        return cadena += "]";
    }

    public void vaciar(){
        this.ultimo = 0;
        this.heap = new Comparable[tamanio];
    }

    public boolean esVacia(){
        return ultimo == 0;
    }

    public ArbolHeapMin clone(){
        ArbolHeapMin clonador = new ArbolHeapMin();
        clonador.ultimo = this.ultimo;
        for (int i = 1; i <= this.ultimo;i++){
            clonador.heap[i] = this.heap[i];
        }
        return clonador;
    }

    public Comparable recuperarCima(){
        return this.heap[1];
   }

}
