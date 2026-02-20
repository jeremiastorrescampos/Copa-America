package Estructuras.Grafos;
import Estructuras.lineales.listaDinamica.*;
import Estructuras.lineales.colaDinamica.*;;

public class GrafoEtiquetado {

    private NodoVertice inicio;

    public GrafoEtiquetado() {

        this.inicio = null;
    }

    public boolean insertarVertice(Object nuevoVertice) {
        boolean insertado = false;
        NodoVertice aux = ubicarVertice(nuevoVertice);

        if (aux == null) {
            this.inicio = new NodoVertice(nuevoVertice, this.inicio);
            insertado = true;

        }
        return insertado;

    }

    private NodoVertice ubicarVertice(Object elem) {
        //Busco el vértice, en caso de que no esté lo puedo insertar.

        NodoVertice aux = this.inicio;

        while (aux != null && !aux.getElem().equals(elem)) {
            aux = aux.getSigVertice();

        }
        return aux;

    }

    public Object recuperarElemento(Object elem) {
        Object elemAux = null;

        NodoVertice aux = ubicarVertice(elem);

        if (aux != null) {
            elemAux = aux.getElem();

        }

        return elemAux;

    }

    public boolean vacio() {

        return (this.inicio == null);

    }

    public boolean existeVertice(Object elem) {

        NodoVertice aux = ubicarVertice(elem);

        return (aux != null);

    }

    public boolean insertarArco(Object origen, Object destino, Object etiqueta) {
        boolean insertado = false;

        NodoVertice aux = this.inicio;
        NodoVertice nodoOrigen = null, nodoDestino = null;

        while (aux != null && (nodoOrigen == null || nodoDestino == null)) {
            //Primero, busco el nodo origen y el nodo destino.

            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;

            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;

            }

            aux = aux.getSigVertice();

        }

        if (nodoOrigen != null && nodoDestino != null) { //En caso de que encuentre los dos nodos, inserto el arco.
            if (nodoOrigen.getElem().equals(nodoDestino.getElem())) {
                //Si el nodo origen es igual al destino, inserta solo un arco
                insertarAdyacente(nodoOrigen, nodoDestino, etiqueta);

            } else {

                insertarAdyacente(nodoOrigen, nodoDestino, etiqueta);
                insertarAdyacente(nodoDestino, nodoOrigen, etiqueta);
            }
            insertado = true;

        }

        return insertado;

    }

    private void insertarAdyacente(NodoVertice nodo, NodoVertice nodoArco, Object etiqueta) {

        NodoAdyacente nuevoNodo = new NodoAdyacente(nodoArco, nodo.getNodoAdy(), etiqueta);

        nodo.setNodoAdy(nuevoNodo);

    }

    public boolean eliminarVertice(Object elem) {
        //Al eliminar un vértice, debo eliminar también todos los arcos de sus nodos adyacentes.
        boolean eliminado = false;
        NodoVertice aux = this.inicio;
        Lista listaAdyacentes = new Lista();

        if (aux != null && aux.getElem().equals(elem)) { //Si es el primer vértice...
            obtenerYEliminarAdyacentes(aux, listaAdyacentes, elem);

            this.inicio = aux.getSigVertice();

            eliminado = true;

        } else {
            while (aux != null && aux.getSigVertice() != null && !eliminado) { //Si no, recorre hasta encontrar el vértice.

                if (aux.getSigVertice().getElem().equals(elem)) {

                    obtenerYEliminarAdyacentes(aux.getSigVertice(), listaAdyacentes, elem);

                    aux.setSigVertice(aux.getSigVertice().getSigVertice());

                    eliminado = true;

                }

                aux = aux.getSigVertice();

            }

        }

        return eliminado;

    }

    private void obtenerYEliminarAdyacentes(NodoVertice nodo, Lista lista, Object elem) {
        //Este método guarda todos los adyacentes en una lista, y después recorre a cada uno de ellos eliminando los arcos.

        NodoAdyacente aux = nodo.getNodoAdy();
        //Guardo todos los nodos adyacentes a los cuales eliminarle el enlace.
        while (aux != null) {

            lista.insertar(aux.getVertice(), lista.longitud() + 1);
            aux = aux.getSigAdy();

        }

        if (!lista.esVacia()) {
            eliminarArcos(lista, elem);

        }

    }

    private void eliminarArcos(Lista lista, Object elem) {
        boolean eliminado = false;

        NodoVertice aux = (NodoVertice) lista.recuperar(1);
        NodoAdyacente nodoAux;
        //Recupero la primera posicion de la lista (El primer nodo adyacente)
        //y en la lista de adyacentes de este, busco el vertice a eliminar. 
        //Repito con cada elemento de la lista.

        while (aux != null && !lista.esVacia()) {

            nodoAux = aux.getNodoAdy();

            if (nodoAux != null && nodoAux.getVertice().getElem().equals(elem)) {
                aux.setNodoAdy(nodoAux.getSigAdy());

            } else {
                while (nodoAux != null && nodoAux.getSigAdy() != null && !eliminado) {

                    if (nodoAux.getSigAdy().getVertice().getElem().equals(elem)) { //Si el siguiente nodo adyacente, equivale a "elem"..

                        nodoAux.setSigAdy(nodoAux.getSigAdy().getSigAdy()); //Le asigno el siguiente adyacente del siguiente.
                        eliminado = true;

                    }
                    nodoAux = nodoAux.getSigAdy();

                }

            }

            lista.eliminar(1);
            if (!lista.esVacia()) {
                aux = (NodoVertice) lista.recuperar(1);
                eliminado = false;

            }

        }

    }

    public boolean eliminarArco(Object origen, Object destino) {
        NodoVertice nodoOrigen = null, nodoDestino = null, aux = this.inicio;
        boolean eliminado = false;

        while ((aux != null) && (nodoOrigen == null || nodoDestino == null)) {
            //Busco el nodoOrigen y el nodoDestino.

            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;

            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;

            }

            aux = aux.getSigVertice();

        }

        if (nodoOrigen != null && nodoDestino != null) {

            if (nodoOrigen.getElem().equals(nodoDestino.getElem())) { //En caso de que sean iguales, solo se elimina un arco.
                eliminarArco(nodoOrigen, nodoDestino);

            } else { //Si no, se eliminan los arcos de ambos nodos.

                eliminarArcoAux(nodoOrigen, nodoDestino);
                eliminarArcoAux(nodoDestino, nodoOrigen);
            }
            eliminado = true;

        }
        return eliminado;

    }

    private void eliminarArcoAux(NodoVertice nodoOrigen, NodoVertice elem) {
        //En este módulo elimino un solo arco.
        NodoAdyacente aux = nodoOrigen.getNodoAdy();

        boolean eliminado = false;

        if (aux != null && aux.getVertice().getElem().equals(elem.getElem())) {
            //Si es el primero, lo elimino al comienzo.
            nodoOrigen.setNodoAdy(aux.getSigAdy());

        } else {

            while (aux != null && aux.getSigAdy() != null && !eliminado) {

                if (aux.getSigAdy().getVertice().getElem().equals(elem.getElem())) {

                    aux.setSigAdy(aux.getSigAdy().getSigAdy());
                    eliminado = true;

                }
                aux = aux.getSigAdy();

            }
        }

    }

    public boolean existeArco(Object origen, Object destino) {
        NodoVertice aux = this.inicio, nodoDestino = null, nodoOrigen = null;
        boolean encontrado = false;
        //Busco los dos vértices.
        while (aux != null && (nodoDestino == null || nodoOrigen == null)) {
            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;

            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;

            }

            aux = aux.getSigVertice();

        }

        if (nodoOrigen != null && nodoDestino != null) {
            
            encontrado = existeArcoAux(nodoOrigen, nodoDestino.getElem());

        }
        return encontrado;

    }

    public boolean existeArcoAux(NodoVertice nodo, Object elem) {
        NodoAdyacente nodoAux = nodo.getNodoAdy();
        boolean encontrado = false;

        while (nodoAux != null && !encontrado) {

            if (nodoAux.getVertice().getElem().equals(elem)) {
                encontrado = true;

            }
            nodoAux = nodoAux.getSigAdy();
        }

        return encontrado;

    }

    public boolean existeCamino(Object origen, Object destino) {
        NodoVertice aux = this.inicio, nodoOrigen = null, nodoDestino = null;

        boolean existeCamino = false;

        while (aux != null && (nodoOrigen == null || nodoDestino == null)) {
            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;

            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;

            }

            aux = aux.getSigVertice();

        }

        if (nodoOrigen != null && nodoDestino != null) {

            if (nodoOrigen.getElem().equals(nodoDestino.getElem())) {
                existeCamino = true;

            } else {
                Lista visitados = new Lista();

                existeCamino = existeCaminoAux(nodoOrigen, nodoDestino, visitados);

            }

        }
        return existeCamino;

    }

    private boolean existeCaminoAux(NodoVertice nodo, NodoVertice destino, Lista visitados) {
        boolean existeCamino = false;

        if (nodo != null) {

            if (nodo.getElem().equals(destino.getElem())) {
                existeCamino = true;

            } else {
                visitados.insertar(nodo.getElem(), visitados.longitud() + 1);
                NodoAdyacente nodoAux = nodo.getNodoAdy();

                while (!existeCamino && nodoAux != null) {

                    if (visitados.localizar(nodoAux.getVertice().getElem()) < 0) {
                        existeCamino = existeCaminoAux(nodoAux.getVertice(), destino, visitados);

                    }
                    nodoAux = nodoAux.getSigAdy();

                }

            }

        }
        return existeCamino;

    }

    public Lista caminoMenosPeso(Object origen, Object destino) {
        NodoVertice aux = this.inicio, nodoOrigen = null, nodoDestino = null;
        Lista lista = new Lista();
        Lista camino = new Lista();
        Lista caminoMenosPeso = new Lista();
        int peso[] = new int[1];
        int menorPeso[] = new int[1];

        while (aux != null && (nodoOrigen == null || nodoDestino == null)) {

            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;

            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;

            }

            aux = aux.getSigVertice();
        }

        if (nodoOrigen != null && nodoDestino != null) {
            menorPeso[0] = -1;
            lista = caminoMenosPesoAux(nodoOrigen, nodoDestino, camino, caminoMenosPeso, peso, menorPeso);

        }
        return lista;

    }

    private Lista caminoMenosPesoAux(NodoVertice nodo, NodoVertice destino, Lista caminoActual, Lista caminoMenosPeso, int[] peso, int[] menorPeso) {
        //Este modulo es igual que el modulo "caminoMasCorto, solo que agrego el peso, menor peso, y evaluo cuando llega al destino si el peso
        //es menor.

        if (nodo != null) {
            caminoActual.insertar(nodo.getElem(), caminoActual.longitud() + 1);

            NodoAdyacente nodoAux = nodo.getNodoAdy();
            int pesoArista = (int) nodoAux.getEtiqueta();

            while (nodoAux != null) {

                if (caminoActual.localizar(nodoAux.getVertice().getElem()) < 0) {
                    peso[0] +=pesoArista;

                }

                if (nodoAux.getVertice().getElem().equals(destino.getElem())) {
                    //Si llego al destino, inserto el elemento.

                    if ((peso[0] < menorPeso[0]) || (menorPeso[0] == -1)) {

                        caminoMenosPeso = caminoActual.Clone();

                        caminoMenosPeso.insertar(destino.getElem(), caminoActual.longitud() + 1);

                        menorPeso[0] = peso[0];
                        System.out.println("Menor peso actualizado " + menorPeso[0]);

                    }
                    peso[0] -= pesoArista;

                } else {

                    if ((caminoActual.localizar(nodoAux.getVertice().getElem()) < 0)) { //Si el nodo no está visitado,
                        //Busco con ese nodo al destino.

                        caminoMenosPeso = caminoMenosPesoAux(nodoAux.getVertice(), destino, caminoActual, caminoMenosPeso, peso, menorPeso);
                        caminoActual.eliminar(caminoActual.longitud()); //Elimino el nodo adyacente ara seguir recorriendo.
                        peso[0] -=pesoArista;

                    }

                }
                nodoAux = nodoAux.getSigAdy();

            }

        }

        return caminoMenosPeso;
    }

    //Camino con menos peso evitando una ciudad 
    public Lista caminoMenosPesoEvitandoUnVertice(Object origen, Object destino, Object estorbo ) {
        NodoVertice aux = this.inicio, nodoOrigen = null, nodoDestino = null, nodoEstorbo = null;
        Lista lista = new Lista();
        Lista camino = new Lista();
        Lista caminoMenosPeso = new Lista();
        int peso[] = new int[1];
        int menorPeso[] = new int[1];

        while (aux != null && (nodoOrigen == null || nodoDestino == null||nodoEstorbo==null)) {

            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;

            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;

            }
            if (aux.getElem().equals(estorbo)) {
                nodoEstorbo = aux;

            }

            aux = aux.getSigVertice();
        }

        if (nodoOrigen != null && nodoDestino != null) {
            menorPeso[0] = -1;
            lista = caminoMenosPesoEvitandoUnVerticeAux(nodoOrigen, nodoDestino, camino, caminoMenosPeso, peso, menorPeso,nodoEstorbo);

        }
        return lista;

    }

    private Lista caminoMenosPesoEvitandoUnVerticeAux(NodoVertice nodo, NodoVertice destino, Lista caminoActual, Lista caminoMenosPeso, int[] peso, int[] menorPeso,NodoVertice estorbo) {
        //Este modulo es una modificacion del vertice Camino con menos peso 

        if (nodo != null) {
            caminoActual.insertar(nodo.getElem(), caminoActual.longitud() + 1);

            NodoAdyacente nodoAux = nodo.getNodoAdy();
            int pesoArista = (int) nodoAux.getEtiqueta();

            while (nodoAux != null) {

                if (caminoActual.localizar(nodoAux.getVertice().getElem()) < 0 && nodoAux.getVertice() != estorbo) {
                    peso[0] +=pesoArista;

                }

                if (nodoAux.getVertice().getElem().equals(destino.getElem())) {
                    //Si llego al destino, inserto el elemento.

                    if ((peso[0] < menorPeso[0]) || (menorPeso[0] == -1)) {

                        caminoMenosPeso = caminoActual.Clone();

                        caminoMenosPeso.insertar(destino.getElem(), caminoActual.longitud() + 1);

                        menorPeso[0] = peso[0];
                        System.out.println("Menor peso actualizado " + menorPeso[0]);

                    }
                    peso[0] -= pesoArista;

                } else {

                    if ((caminoActual.localizar(nodoAux.getVertice().getElem()) < 0)&& nodoAux.getVertice() != estorbo) { //Si el nodo no está visitado,
                        //Busco con ese nodo al destino.

                        caminoMenosPeso = caminoMenosPesoEvitandoUnVerticeAux(nodoAux.getVertice(), destino, caminoActual, caminoMenosPeso, peso, menorPeso, estorbo);
                        caminoActual.eliminar(caminoActual.longitud()); //Elimino el nodo adyacente ara seguir recorriendo.
                        peso[0] -=pesoArista;

                    }

                }
                nodoAux = nodoAux.getSigAdy();

            }

        }

        return caminoMenosPeso;
    }



    public Lista caminoMasCorto(Object origen, Object destino) {
        NodoVertice aux = this.inicio, nodoOrigen = null, nodoDestino = null;
        Lista lista = new Lista();
        Lista camino = new Lista();
        Lista caminoMasCorto = new Lista();

        while (aux != null && (nodoOrigen == null || nodoDestino == null)) {

            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;

            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;

            }

            aux = aux.getSigVertice();
        }

        if (nodoOrigen != null && nodoDestino != null) {

            if (nodoOrigen.getElem().equals(nodoDestino.getElem())) {
                lista.insertar(nodoOrigen, 1);

            } else {

                lista = caminoMasCortoAux(nodoOrigen, nodoDestino, camino, caminoMasCorto);

            }

        }
        return lista;

    }

    private Lista caminoMasCortoAux(NodoVertice nodo, NodoVertice destino, Lista caminoActual, Lista caminoMasCorto) {

        if (nodo != null) {
            caminoActual.insertar(nodo.getElem(), caminoActual.longitud() + 1);

            NodoAdyacente nodoAux = nodo.getNodoAdy();

            while (nodoAux != null) {

                if (nodoAux.getVertice().getElem().equals(destino.getElem())) {
                    //Si llego al destino, inserto el elemento.

                    if (caminoMasCorto.esVacia() || (caminoActual.longitud() < caminoMasCorto.longitud())) {

                        caminoMasCorto = caminoActual.Clone();

                        caminoMasCorto.insertar(destino.getElem(), caminoActual.longitud() + 1);

                    }

                } else {

                    if (caminoActual.localizar(nodoAux.getVertice().getElem()) < 0) { //Si el nodo no está visitado,
                        //Busco con ese nodo al destino.

                        caminoMasCorto = caminoMasCortoAux(nodoAux.getVertice(), destino, caminoActual, caminoMasCorto);
                        caminoActual.eliminar(caminoActual.longitud()); //Elimino el nodo adyacente ara seguir recorriendo.

                    }

                }
                nodoAux = nodoAux.getSigAdy();

            }

        }

        return caminoMasCorto;
    }

    public String toString() {
        String cadena = "";
        NodoVertice nodo = this.inicio;
        if (nodo != null) {
            cadena = "";
            while (nodo != null) {
                NodoAdyacente adyacente = nodo.getNodoAdy();
                cadena += nodo.getElem().toString() + ":";
                while (adyacente != null) {
                    cadena += "->[" + adyacente.getVertice().getElem().toString() + "," + adyacente.getEtiqueta().toString() + "]";
                    adyacente = adyacente.getSigAdy();
                }
                cadena += "\n";
                nodo = nodo.getSigVertice();
            }
        }
        return cadena;
    }

    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();

        NodoVertice aux = this.inicio;

        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                listarEnProfundidadAux(aux, visitados);

            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVertice nodo, Lista vis) {

        if (nodo != null) {
            vis.insertar(nodo.getElem(), vis.longitud() + 1);
            NodoAdyacente adyacente = nodo.getNodoAdy();

            while (adyacente != null) {
                if (vis.localizar(adyacente.getVertice().getElem()) < 0) {

                    listarEnProfundidadAux(adyacente.getVertice(), vis);

                }

                adyacente = adyacente.getSigAdy();

            }

        }

    }

    public Lista caminoMasLargo(Object origen, Object destino) {
        NodoVertice aux = this.inicio, nodoOrigen = null, nodoDestino = null;

        Lista camino = new Lista();
        Lista caminoMasLargo = new Lista();

        while (aux != null && (nodoOrigen == null || nodoDestino == null)) {

            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;

            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;

            }

            aux = aux.getSigVertice();
        }

        if (nodoOrigen != null && nodoDestino != null) {

            if (nodoOrigen.getElem().equals(nodoDestino.getElem())) {
                caminoMasLargo.insertar(nodoOrigen, 1);

            } else {

                caminoMasLargo = caminoMasLargoAux(nodoOrigen, nodoDestino, camino, caminoMasLargo);

            }

        }
        return caminoMasLargo;

    }

    private Lista caminoMasLargoAux(NodoVertice nodo, NodoVertice destino, Lista caminoActual, Lista caminoMasLargo) {

        if (nodo != null) {
            caminoActual.insertar(nodo.getElem(), caminoActual.longitud() + 1);

            NodoAdyacente nodoAux = nodo.getNodoAdy();

            while (nodoAux != null) {

                if (nodoAux.getVertice().getElem().equals(destino.getElem())) {
                    //Si llego al destino, inserto el elemento.

                    if (caminoMasLargo.esVacia() || (caminoActual.longitud() > caminoMasLargo.longitud())) {

                        caminoMasLargo = caminoActual.Clone();
                        caminoMasLargo.insertar(nodoAux.getVertice().getElem(), caminoActual.longitud() + 1);

                    }

                } else {
                    //Inserto el nodo en vistados, y en "camino".

                    if (caminoActual.localizar(nodoAux.getVertice().getElem()) < 0) { //Si el nodo no está visitado,
                        //Busco con ese nodo al destino.

                        caminoMasLargo = caminoMasLargoAux(nodoAux.getVertice(), destino, caminoActual, caminoMasLargo);
                        caminoActual.eliminar(caminoActual.longitud()); //Elimino el nodo adyacente ara seguir recorriendo.

                    }

                }
                nodoAux = nodoAux.getSigAdy();

            }

        }

        return caminoMasLargo;
    }

    public Lista listarEnAnchura() {
        Lista visitados = new Lista();

        NodoVertice aux = this.inicio;

        while (aux != null) {

            if (visitados.localizar(aux.getElem()) < 0) {
                listarEnAnchuraAux(aux, visitados);

            }
            aux = aux.getSigVertice();

        }

        return visitados;

    }

    private void listarEnAnchuraAux(NodoVertice nodo, Lista visitados) {
        Cola cola = new Cola();
        NodoVertice nodoAux;
        NodoAdyacente nodoAdy;

        visitados.insertar(nodo.getElem(), visitados.longitud() + 1);
        cola.ponerElemento(nodo);

        while (!cola.esVacia()) {
            nodoAux = (NodoVertice) cola.obtenerFrente();
            cola.sacarElemento();
            nodoAdy = nodoAux.getNodoAdy();

            while (nodoAdy != null) {
                if (visitados.localizar(nodoAdy.getVertice().getElem()) < 0) {
                    visitados.insertar(nodoAdy.getVertice().getElem(), visitados.longitud() + 1);
                    cola.ponerElemento(nodoAdy.getVertice());

                }

                nodoAdy = nodoAdy.getSigAdy();

            }

        }

    }










    //VISITAR TODOS LOS CAMINOS 
    

    public Lista todosLosCaminos(Object origen, Object destino) {
        NodoVertice aux = this.inicio, nodoOrigen = null, nodoDestino = null;

        Lista camino = new Lista();
        Lista todosLosCaminos = new Lista();

        while (aux != null && (nodoOrigen == null || nodoDestino == null)) {

            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;

            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;

            }

            aux = aux.getSigVertice();
        }

        if (nodoOrigen != null && nodoDestino != null) {

            if (nodoOrigen.getElem().equals(nodoDestino.getElem())) {
                todosLosCaminos.insertar(nodoOrigen, 1);

            } else {

                todosCaminosAux(nodoOrigen, nodoDestino, camino, todosLosCaminos);

            }

        }
        return todosLosCaminos;

    }

    private void todosCaminosAux(NodoVertice nodoOrigen, NodoVertice nodoDestino, Lista camino, Lista listadoCaminos) {
        if (nodoOrigen != null) {
            camino.insertar(nodoOrigen.getElem(), camino.longitud() + 1);
            if (nodoOrigen.getElem().equals(nodoDestino.getElem())) {
                Lista lista = camino.Clone();
                listadoCaminos.insertar(lista, listadoCaminos.longitud() + 1);
            } else {
                NodoAdyacente siguiente = nodoOrigen.getNodoAdy();
                while (siguiente != null) {
                    if (camino.localizar(siguiente.getVertice().getElem()) < 0) {
                        todosCaminosAux(siguiente.getVertice(), nodoDestino, camino, listadoCaminos);
                        camino.eliminar(camino.longitud());
                    }
                    siguiente = siguiente.getSigAdy();
                }
            }
        }
    }
 


}