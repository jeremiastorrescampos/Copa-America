package Estructuras.ArbolAVL;

import java.util.List;

import Estructuras.lineales.listaDinamica.*;


public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL(){
        this.raiz = null;
    }

    public boolean pertenece(Comparable elem){
        boolean exito = false;
        if(this.raiz != null){
            exito = perteneceAux(this.raiz, elem);
        }
        return exito;
    }

    private boolean perteneceAux(NodoAVL n, Comparable elem){
        boolean exito = false;
        if (n != null){
            if (n.getElem().equals(elem)){
                exito = true;
            }else{
                if (n.getElem().compareTo(elem) > 0){
                    exito = perteneceAux(n.getIzquierdo(), elem);
                }else{
                    exito = perteneceAux(n.getDerecho(), elem);
                }
            }
        }
        return exito;
    }

    public boolean insertar(Comparable elemento){
        boolean exito = true; 
        if (this.raiz == null){
            this.raiz = new NodoAVL(elemento);
        }else{
            exito = insertarAux(this.raiz, elemento);
            if (exito){
                this.raiz = realizarBalanceAvl(this.raiz);
                // this.raiz.recalcularAltura();
            }
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL n, Comparable elemento){
        boolean exito = true;
        if ((elemento.compareTo(n.getElem()) == 0)){
            exito = false;
        }else{
            if (elemento.compareTo(n.getElem()) < 0){
                if (n.getIzquierdo() != null){
                    /* Una vez que devuelve true, analiza con el padre para ver si se necesita algun balance... */
                    exito = insertarAux(n.getIzquierdo(), elemento);
                    if (exito){
                        n.setIzquierdo(realizarBalanceAvl(n.getIzquierdo()));
                        // n.recalcularAltura();
                    }
                }else{
                    n.setIzquierdo(new NodoAVL(elemento));
                    n.recalcularAltura();
                    exito = true;
                }
            }else{
                if (n.getDerecho() != null){
                    exito = insertarAux(n.getDerecho(), elemento);
                    /* Verificacion, si true se hace un balance desde el padre al hijo */
                    if (exito){
                        n.setDerecho(realizarBalanceAvl(n.getDerecho()));
                        // n.recalcularAltura();
                    }
                }else{
                    n.setDerecho(new NodoAVL(elemento));
                    n.recalcularAltura();
                    exito = true;
                }
            }
        }
        return exito;
    }

    /* Se analiza el balance del nodo dado y se verifica si se necesita otra rotacion */
    private NodoAVL realizarBalanceAvl(NodoAVL n){
        n.recalcularAltura();
        NodoAVL nuevaRaiz = n;
        if (obtenerBalance(n) == 2){
            if (obtenerBalance(n.getIzquierdo()) >= 0){
                nuevaRaiz = rotacionSimpleDerecha(n);
            }else{
                n.setIzquierdo(rotacionSimpleIzquierda(n.getIzquierdo()));  
                nuevaRaiz = rotacionSimpleDerecha(n);
            }
        }
        if (obtenerBalance(n) == -2){
            if (obtenerBalance(n.getDerecho()) <= 0){
                nuevaRaiz = rotacionSimpleIzquierda(n);
            }else{
                n.setDerecho(rotacionSimpleDerecha(n.getDerecho()));
                nuevaRaiz = rotacionSimpleIzquierda(n);
            }
        }
        n.recalcularAltura();
        return nuevaRaiz;
    }

    /* Se obtiuene el balance del nodo con sus hijos */
    private int obtenerBalance(NodoAVL n){
        int balance;
        if (n.getIzquierdo() == null){
            balance = -1;
        }else{
            balance = n.getIzquierdo().getAltura();
        }
        if (n.getDerecho() == null){
            balance = balance - (-1);
        }else{
            balance = balance - (n.getDerecho().getAltura());
        }
        return balance;
    }

    /* Rotaciones */

    private NodoAVL rotacionSimpleDerecha(NodoAVL pivote){
        NodoAVL h = pivote.getIzquierdo();
        NodoAVL temp = h.getDerecho();
        h.setDerecho(pivote);
        pivote.setIzquierdo(temp);
        pivote.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVL rotacionSimpleIzquierda(NodoAVL pivote){
        NodoAVL h = pivote.getDerecho();
        NodoAVL temp = h.getIzquierdo();
        h.setIzquierdo(pivote);
        pivote.setDerecho(temp);
        pivote.recalcularAltura();
        h.recalcularAltura();
        return h;
    }
    /* ToString */

    public String toString(){
        String cad = "Arbol vacio!";
        if (this.raiz != null){
            cad = toStringAux(this.raiz);
        }
        return cad;
    }

    private String toStringAux(NodoAVL nodo){
        String cad = "";
        if (nodo != null){
            cad += nodo.getElem() + " |"+ nodo.getAltura() +"| -> I: ";
            if (nodo.getIzquierdo() == null){
                cad+= "null" + " D: ";
            }else{
                cad += nodo.getIzquierdo().getElem() + " D: ";
            }
            if(nodo.getDerecho() == null){
                cad += "null" + "\n";
            }else{
                cad += nodo.getDerecho().getElem() + "\n";
            }
            cad += toStringAux(nodo.getIzquierdo());
            cad += toStringAux(nodo.getDerecho());
        }
        return cad;
    }

    public Lista listarInorden(){
        Lista lis = new Lista();
        if (this.raiz != null){
            listarInordenAux(this.raiz, lis);
        }
        return lis;
    }
    
    public Lista listarDeMayorAMenor(){
        Lista lis = new Lista();
        listarDeMayorAMenorAux(this.raiz, lis);
        return lis;
    }

    private void listarDeMayorAMenorAux(NodoAVL n, Lista lis){
        if (n != null){
            listarDeMayorAMenorAux(n.getDerecho(),lis);
            lis.insertar(n.getElem(), lis.longitud()+1);
            listarDeMayorAMenorAux(n.getIzquierdo(),lis);
        }
    }

    private void listarInordenAux(NodoAVL n, Lista lis){
        if (n != null){
            listarInordenAux(n.getIzquierdo(), lis);
            lis.insertar(n.getElem(), lis.longitud()+1);
            listarInordenAux(n.getDerecho(), lis);
        }
    }

    /* Algoritmo de eliminar */

    public boolean eliminar(Comparable elemento){
        boolean exito = false;
        if (this.raiz != null){
            if (this.raiz.getElem().compareTo(elemento) == 0){
                exito = true;
                if (this.raiz.getIzquierdo() == null && this.raiz.getDerecho() == null){
                    this.raiz = null;
                }else{
                    borrarNodo(this.raiz);
                }
            }else{
                exito = eliminarAux(this.raiz, elemento);
                this.raiz = realizarBalanceAvl(this.raiz);
                // this.raiz.recalcularAltura();
            }
        }
        return exito;
    }

    private boolean eliminarAux(NodoAVL n,Comparable elemento){
        boolean exito = false;
        if (n != null){
            if(elemento.compareTo(n.getElem()) < 0){
                if (n.getIzquierdo() != null){
                    /* Caso hijo izquierdo */
                    if (n.getIzquierdo().getElem().compareTo(elemento) == 0){
                        exito = true;
                        NodoAVL hijo = n.getIzquierdo();
                        /* Caso  sin hijos*/
                        if (hijo.getIzquierdo() == null && hijo.getDerecho() == null){
                            n.setIzquierdo(null);
                            n.recalcularAltura();
                        }else{
                            /* Modulo de los otros dos casos */
                            borrarNodo(hijo);
                            n.setIzquierdo(realizarBalanceAvl(hijo));
                            // n.recalcularAltura();
                        }
                    }else{
                        /* Sigue buscando si no encontro */
                        exito = eliminarAux(n.getIzquierdo(), elemento);
                        n.setIzquierdo(realizarBalanceAvl(n.getIzquierdo()));
                        // n.recalcularAltura();
                    }
                }
            }else{
                if (n.getDerecho() != null){
                    /* Caso hijo derecho */
                    if (n.getDerecho().getElem().compareTo(elemento) == 0){
                        exito = true;
                        NodoAVL hijo = n.getDerecho();
                        /*Caso dos hijos */
                        if (hijo.getIzquierdo() == null && hijo.getDerecho() == null){
                            n.setDerecho(null);
                            n.recalcularAltura();
                        }else{
                            /* otros dos casos */
                            borrarNodo(hijo);
                            n.setDerecho(realizarBalanceAvl(hijo));
                            // n.recalcularAltura();
                        }
                    }else{
                        /* sigue buscando si no encontro */
                        exito = eliminarAux(n.getDerecho(), elemento);
                        n.setDerecho(realizarBalanceAvl(n.getDerecho()));
                        // n.recalcularAltura();
                    }
                }
            }
        }
        return exito;
    }
    
    private void borrarNodo(NodoAVL n){
        /* Caso de los dos hijos */
        if (n.getDerecho() != null && n.getIzquierdo() != null){
            if (n.getIzquierdo().getDerecho() != null){
                n.setElem(buscarCandidato(n.getIzquierdo()));
            }else{
                n.setElem(n.getIzquierdo().getElem());
                n.setIzquierdo(n.getIzquierdo().getIzquierdo());
            }
        }else{
            /* Caso de de un solo hijo */
            if (n.getIzquierdo() != null){
                n.setElem(n.getIzquierdo().getElem());
                n.setIzquierdo(n.getIzquierdo().getIzquierdo());
            }else{
                n.setElem(n.getDerecho().getElem());
                n.setDerecho(n.getDerecho().getDerecho());
            }
        }
        n.recalcularAltura();
    }

    /* Modulo de los dos hijos */
    private Comparable  buscarCandidato(NodoAVL n){
        Comparable candidato = null;
        if (n != null){
            if (n.getDerecho().getDerecho() == null){
                candidato = n.getDerecho().getElem();
                n.setDerecho(n.getDerecho().getDerecho());
            }else{
                candidato = buscarCandidato(n.getDerecho());
                n.setDerecho(n.getDerecho());
            }
            n.recalcularAltura();
        }
        return candidato;
    }

    /* Para la clase EquipoFutbol */

    /* - - - - -  */

    public Comparable buscarElemento(Comparable elem){
        NodoAVL nodoBuscado = null;
        Comparable buscado = null;
        if (this.raiz != null){
           nodoBuscado = buscarElementoAux(this.raiz, elem);
           if (nodoBuscado != null){
            buscado = nodoBuscado.getElem();
           } 
        }
        return buscado;
    }

    private NodoAVL buscarElementoAux(NodoAVL n, Comparable elem){
        NodoAVL buscado = null;
        if (n != null){
            if (n.getElem().compareTo(elem) == 0){
                buscado = n;
            }else{
                if (n.getElem().compareTo(elem) > 0){
                    buscado = buscarElementoAux(n.getIzquierdo(), elem);
                }else{
                    buscado = buscarElementoAux(n.getDerecho(), elem);
                }
            }
        }
        return buscado;
    }

    /* Es el listar por rango del ejercicio 7 del tp final */
    public Lista listarPorRango(Comparable min, Comparable max){
        Lista lis = new Lista();
        if (min.compareTo(max) > 0){
            Comparable aux = max;
            max = min;
            min = aux;
        }
        if (this.raiz != null){
            listarPorRangoAux(this.raiz, min, max, lis);
        }
        return lis;
    }

    private void listarPorRangoAux(NodoAVL n, Comparable min, Comparable max, Lista lis){
        if (n != null){
            // System.out.println(min.compareTo(n.getElem()));
            // System.out.println(max.compareTo(n.getElem()));
            if (min.compareTo(n.getElem()) <= 0 && max.compareTo(n.getElem()) >= 0){
                lis.insertar(n.getElem(), lis.longitud() + 1);
            }
            if (min.compareTo(n.getElem()) <= 0){
                listarPorRangoAux(n.getIzquierdo(), min, max, lis);
            }
            if (max.compareTo(n.getElem()) >= 0){
                listarPorRangoAux(n.getDerecho(), min, max, lis);
            }
        }
    }

    /* - - - - -  */


}