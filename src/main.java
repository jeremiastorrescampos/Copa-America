import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import Estructuras.ArbolAVL.ArbolAVL;
import Estructuras.clases.*;
import Estructuras.lineales.listaDinamica.Lista;
import Estructuras.Grafos.*;
import Estructuras.TablaHashAbierto.TablaHash;

public class main {
    private static File obj = new File("../src/log.txt");
    private static ArbolAVL arbolEquipos= cargarEquipos();
    private static GrafoEtiquetado mapa = cargarCiudades();
    private static TablaHash tablaPartidos = cargarPartidos();
    private static Scanner sc = new Scanner(System.in);
    
    private static LocalDateTime now = LocalDateTime.now();
    public static void main(String[] args){
        
        //System.out.println(arbolEquipos.toString());
        //System.out.println(mapa.toString());
        //System.out.println(tablaPartidos.toString());
        //System.out.println(mapa.listarEnAnchura());
        //System.out.println(mapa.todosLosCaminos(new Ciudad("AUSTIN"), new Ciudad("HOUSTON")));
        
        //consultaPartido();
        //consultarTodosLosCaminos();
        resetLog();
        log("APP: inicio");
        runMenu();
        log("APP: fin");
        
    }
    private static void runMenu() {
    int op;
    do {
        System.out.println("\n=== COPA AMÉRICA 2024 — MENÚ PRINCIPAL ===");
        System.out.println("1) Ciudades: Alta / Baja / Modificación");
        System.out.println("2) Equipos: Alta / Baja / Modificación");
        System.out.println("3) Partidos: Alta");
        System.out.println("4) Consulta sobre equipos");
        System.out.println("5) Consulta sobre partidos");
        System.out.println("6) Consultas sobre viajes");
        System.out.println("7) Listar equipos por goles a favor (consulta puntual)");
        System.out.println("8) Mostrar sistema (debug: grafo, AVL, mapeo)");
        System.out.println("0) Salir");
        op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1: menuCiudades(); break;
            case 2: menuEquipos(); break;
            case 3:
                log("Menu: AltaPartido");
                try { agregarPartido(); } catch (IOException e) { System.out.println("Error de log: " + e.getMessage()); }
                break;
            case 4:
                log("Menu: ConsultaEquipos");
                try { consultarSobreEquipo(); } catch (IOException e) { System.out.println("Error de log: " + e.getMessage()); }
                break;
            case 5:
                log("Menu: ConsultaPartidos");
                try { consultaPartido(); } catch (IOException e) { System.out.println("Error de log: " + e.getMessage()); }
                break;
            case 6: menuViajes(); break;
            case 7:
                log("Menu: GolesAFavor");
                // Si ya tenés implementado el punto 7 en otro método, llamalo acá.
                // Por ahora muestro un placeholder para que compile sin tocar tu lógica.
                System.out.println("Funcionalidad 7: mostrar equipos ordenados por goles a favor (implementá tu estructura auxiliar).");
                break;
            case 8:
                log("Menu: MostrarSistema");
                mostrarSistema();
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    } while (op != 0);
}

// ======================= SUBMENÚS =======================
private static void menuCiudades() {
    int op;
    do {
        System.out.println("\n--- Ciudades ---");
        System.out.println("1) Alta de ciudad");
        System.out.println("2) Baja de ciudad");
        System.out.println("3) Modificar ciudad");
        System.out.println("0) Volver");
        op = sc.nextInt();
        sc.nextLine();
        switch (op) {
            case 1: log("Ciudades: Alta"); altaDeCiudad(); break;
            case 2: log("Ciudades: Baja"); bajaDeCiudad(); break;
            case 3: log("Ciudades: Modificar"); modificarCiudad(); break;
            case 0: break;
            default: System.out.println("Opción no válida.");
        }
    } while (op != 0);
}

private static void menuEquipos() {
    int op;
    do {
        System.out.println("\n--- Equipos ---");
        System.out.println("1) Alta de equipo");
        System.out.println("2) Baja de equipo");
        System.out.println("3) Modificar equipo");
        System.out.println("0) Volver");
        op = sc.nextInt();
        sc.nextLine();
        switch (op) {
            case 1:
                log("Equipos: Alta");
                try { altaEquipo(); } catch (IOException e) { System.out.println("Error de log: " + e.getMessage()); }
                break;
            case 2: log("Equipos: Baja"); bajaEquipo(); break;
            case 3:
                log("Equipos: Modificar");
                try { modificarEquipo(); } catch (IOException e) { System.out.println("Error de log: " + e.getMessage()); }
                break;
            case 0: break;
            default: System.out.println("Opción no válida.");
        }
    } while (op != 0);
}

private static void menuViajes() {
    int op;
    do {
        System.out.println("\n--- Viajes ---");
        System.out.println("1) Camino con mínima cantidad de ciudades (A → B)");
        System.out.println("2) Camino de menor tiempo (A → B)");
        System.out.println("3) Camino más corto en minutos evitando ciudad C (A → B sin C)");
        System.out.println("4) Todos los caminos (A → B) y filtros (ciudad/alojamiento)");
        System.out.println("0) Volver");
        op = sc.nextInt();
        sc.nextLine();
        switch (op) {
            case 1:
                log("Viajes: CaminoMasCorto (por cantidad de ciudades)");
                consultarCaminoMasCorto();
                break;
            case 2:
                log("Viajes: CaminoMasRapido (por tiempo)");
                consultarCaminoMasRapido();
                break;
            case 3:
                log("Viajes: CaminoRapidoEvitandoCiudad");
                consultarCaminoMasRapidoEvitandoCiudad();
                break;
            case 4:
                log("Viajes: TodosLosCaminos + filtros");
                consultarTodosLosCaminos();
                break;
            case 0: break;
            default: System.out.println("Opción no válida.");
        }
    } while (op != 0);
}

// ======================= UTILIDAD: Mostrar Sistema (debug) =======================
private static void mostrarSistema() {
    // Solo imprime; no cambia tu lógica. Si preferís, podés comentar/ajustar.
    System.out.println("\n--- AVL de Equipos ---");
    System.out.println(arbolEquipos.toString());

    System.out.println("\n--- Grafo de Ciudades ---");
    System.out.println(mapa.toString());

    System.out.println("\n--- Tabla de Partidos ---");
    System.out.println(tablaPartidos.toString());
}
    //--------------------------ALTA / BAJA / MODIFICACION DE CIUDADES--------------------------

    public static void altaDeCiudad(){
    String nombreCiudad,cede,alo,respuesta;
    boolean esCede;
    boolean alojamiento,exito;
    Ciudad ciudad;

    System.out.println("Por favor ingrese los siguiente datos de la ciudad que desea agregar");
    System.out.println("Nombre de la Ciudad: ");
    nombreCiudad = sc.nextLine();
    System.out.println("Es alojamiento: S/N");
    alo= sc.nextLine().toUpperCase().trim();
    System.out.println("Es cede: S/N");
    cede = sc.nextLine().toUpperCase().trim();

    log("AltaCiudad: inicio nombre=" + nombreCiudad + " alo=" + alo + " cede=" + cede);

    // if: la ciudad NO existe aún
    if (!mapa.existeVertice(new Ciudad(nombreCiudad))) {
        // if: valor válido S/N para alojamiento
        if (alo.equals("S") || alo.equals("N")) {
            alojamiento = alo.equals("S");
            // if: valor válido S/N para cede
            if ( (cede.equals("S")||cede.equals("N"))) {
                esCede = cede.equals("S");
                ciudad = new Ciudad(nombreCiudad, alojamiento, esCede);
                exito= mapa.insertarVertice(ciudad);
                respuesta= "Insertando ciudad.." + ((exito) ? "Se a insertado con exito." : "No se a podido insertar correctamente");
                log("AltaCiudad: " + (exito ? "OK " : "FAIL ") + ciudad.toString());
            }else{
                respuesta="Los datos ingresados no son correctos";
                log("AltaCiudad: FAIL dato 'cede' invalido=" + cede);
            }
        }else{
            respuesta="Los datos ingresados no son correctos";
            log("AltaCiudad: FAIL dato 'alojamiento' invalido=" + alo);
        }
    }else{
        respuesta="Disculpe no se pude agregar es ciudad, la misma ya se escuentra cargada";
        log("AltaCiudad: WARN duplicada nombre=" + nombreCiudad);
    }
    System.out.println(respuesta);
}

    public static void bajaDeCiudad(){
    String nombreCiudad;

    System.out.println("Ingrese el nombre de la ciudad que quiere dar de Baja");
    nombreCiudad = sc.nextLine().toUpperCase().trim();
    log("BajaCiudad: inicio nombre=" + nombreCiudad);

    if (mapa.eliminarVertice(new Ciudad(nombreCiudad))) {
        System.out.println("La ciudad se a dado de baja exitosamente");
        log("BajaCiudad: OK " + nombreCiudad);
    }else{
        System.out.println("La ciudad ingresada no existe");
        log("BajaCiudad: WARN no existe " + nombreCiudad);
    }
}

    public static void modificarCiudad(){
    String nombreCiudad,nuevoNombre,opcion;
    Ciudad ciudad;
    System.out.println("Ingrese la ciudad que desea modificar");
    nombreCiudad = sc.nextLine();
    log("ModCiudad: inicio buscar=" + nombreCiudad);

    ciudad = (Ciudad) mapa.recuperarElemento(new Ciudad(nombreCiudad));
    if (ciudad != null) {
        System.out.println("Ingrese los elementos que quiere modificar");

        System.out.println("Nombre: S/N");
        opcion = sc.nextLine();
        if (opcion.equals("S")||opcion.equals("N")) {
            if (nombreCiudad.equals("S")) { // (lógica original intacta)
                System.out.println("Ingrese el nuevo nombre");
                nuevoNombre = sc.nextLine();
                if (mapa.existeVertice(new Ciudad(nuevoNombre))) {
                   ciudad.setNombre(nuevoNombre);
                   System.out.println("Se a modificado el nombre con exito");
                   log("ModCiudad: OK nombre -> " + nuevoNombre);
                }else{
                    System.out.println("No se puede poner un nombre de una ciudad ya existente");
                    log("ModCiudad: FAIL nombre duplicado=" + nuevoNombre);
                }
            }
        }else{
            System.out.println("No se ingreso correctamente el dato");
            log("ModCiudad: FAIL opcion nombre invalida=" + opcion);
        }

        System.out.println("Alojamiento: S/N");
        opcion = sc.nextLine();
        if (opcion.equals("S")||opcion.equals("N")) {
            if (nombreCiudad.equals("S")) { // (lógica original intacta)
                ciudad.cambiarAlojamiento();
                System.out.println("Se a modificado el alojamiento con exito");
                log("ModCiudad: OK alojamiento=" + ciudad.getAlojamiento());
            }
        }else{
            System.out.println("No se ingreso correctamente el dato");
            log("ModCiudad: FAIL opcion alojamiento invalida=" + opcion);
        }

        System.out.println("es Cede: S/N");
        opcion = sc.nextLine();
        if (opcion.equals("S")||opcion.equals("N")) {
            if (nombreCiudad.equals("S")) { // (lógica original intacta)
                ciudad.cambiarEsCede();
                System.out.println("Se a modificado la sede con exito");
                log("ModCiudad: OK esCede=" + ciudad.getEsSede());
            }
        }else{
            System.out.println("No se ingreso correctamente el dato");
            log("ModCiudad: FAIL opcion sede invalida=" + opcion);
        }
    }else{
        System.out.println("Ciudad: "+ ciudad); 
        log("ModCiudad: FAIL no encontrada=" + nombreCiudad);
    }
}

    public static void altaDeCamino(){
    String ciudadOrigen,ciudadDestino;
    float distancia;
    Ciudad ciudadOri,ciudadDest;
    System.out.println("Ingrese la ciudad origen: ");
    ciudadOrigen = sc.nextLine();
    ciudadOri = (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadOrigen));
    log("AltaCamino: inicio origen=" + ciudadOrigen);

    if (ciudadOri!=null) {
        System.out.println("Ingrese la ciudad destino: ");
        ciudadDestino= sc.nextLine();
        ciudadDest = (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadDestino));
        if (ciudadOri!=null) { 
            System.out.println("Ingrese el tiempo que tarda: ");
            distancia= sc.nextFloat();
            sc.nextLine();
            log("AltaCamino: datos destino=" + ciudadDestino + " tiempo=" + distancia);

            if (mapa.existeArco(ciudadOri, ciudadDest)) { 
               boolean ok = mapa.insertarArco(ciudadOri, ciudadDest, distancia);
               System.out.println(ok ? "Camino insertado" : "No se pudo insertar el camino");
               log("AltaCamino: " + (ok ? "OK" : "FAIL insert"));
            }else{
                System.out.println("El camino ya existe");
                log("AltaCamino: WARN ya existe");
            }
        } else {
            System.out.println("La ciudad destino no existe"); // mensaje faltante
            log("AltaCamino: FAIL destino no existe=" + ciudadDestino);
        }
    } else {
        System.out.println("La ciudad origen no existe"); // mensaje faltante
        log("AltaCamino: FAIL origen no existe=" + ciudadOrigen);
    }
}

    public static void bajaDeCamino(){
    String ciudadOrigen,ciudadDestino;
    Ciudad ciudadOri,ciudadDest;
    System.out.println("Ingrese la ciudad origen: ");
    ciudadOrigen = sc.nextLine();
    ciudadOri = (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadOrigen));
    log("BajaCamino: inicio origen=" + ciudadOrigen);

    if (ciudadOri!=null) {
        System.out.println("Ingrese la ciudad destino: ");
        ciudadDestino= sc.nextLine();
        ciudadDest = (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadDestino));
        if (ciudadOri!=null) {
            if (!mapa.existeArco(ciudadOri, ciudadDest)) { 
               boolean ok = mapa.eliminarArco(ciudadOri, ciudadDest);
               System.out.println(ok ? "Camino eliminado" : "No se pudo eliminar el camino");
               log("BajaCamino: " + (ok ? "OK" : "FAIL delete"));
            }else{
                System.out.println("El camino no Existe");
                log("BajaCamino: WARN no existe");
            }
        } else {
            System.out.println("La ciudad destino no existe");
            log("BajaCamino: FAIL destino no existe=" + ciudadDestino);
        }
    } else {
        System.out.println("La ciudad origen no existe");
        log("BajaCamino: FAIL origen no existe=" + ciudadOrigen);
    }
}

   
        //--------------------------ALTA / BAJA / MODIFICACION DE EQUIPOS--------------------------

    public static void altaEquipo() throws IOException{
    String nombreEquipo,dt,grupoInicial;
    boolean resultado=true;
    Equipo equipo;

    System.out.println("Ingrese el nombre del equipo");
    nombreEquipo = sc.nextLine().trim();

    System.out.println("Ingrese el nombre del director tecnico");
    dt = sc.nextLine();
    System.out.println("Ingrese el grupo inicial [A,B,C,D]");
    grupoInicial = sc.nextLine().trim();
    log("AltaEquipo: inicio nombre=" + nombreEquipo + " dt=" + dt + " grupo=" + grupoInicial);

    if (grupoInicial.equals("A")||grupoInicial.equals("B")||grupoInicial.equals("C")||grupoInicial.equals("D")) {
        equipo=new Equipo(nombreEquipo, dt, grupoInicial);
        resultado= arbolEquipos.insertar(equipo);

        if (resultado) {
            write("Se a insertado el equipo: "+equipo.toString() );
            log("AltaEquipo: OK " + equipo.toString());
        }
        System.out.println("El Equipo "+ ((resultado) ? "Se a insetado con exito" : "No se a podido insertar"));
        write("El equipo ya exite"); // (tu línea original la dejo)
        if (!resultado) log("AltaEquipo: FAIL ya existe");
    }else{
        System.out.println("Los datos ingresados no son validos");
        write("El grupo: "+ grupoInicial+" no existe");
        log("AltaEquipo: FAIL grupo invalido=" + grupoInicial);
    }
}

    public static void bajaEquipo(){
    String equipo;
    boolean resultado;
    System.out.println("Ingrese el nombre del equipo que desea dar de baja");
    equipo = sc.nextLine();
    log("BajaEquipo: inicio nombre=" + equipo);

    resultado=arbolEquipos.eliminar(new Equipo(equipo));
    System.out.println("El Equipo "+ ((resultado) ? "Se a dado de baja con exito" : "No existe"));
    log("BajaEquipo: " + (resultado ? "OK" : "WARN no existe"));
}

    public static void modificarEquipo() throws IOException{
    String nombreEquipo,dt;
    int opcion;
    Equipo elEquipo;
    System.out.println("Ingrese el nombre del equipo que quiere modificar");
    nombreEquipo = sc.nextLine();
    elEquipo = (Equipo) arbolEquipos.buscarElemento(new Equipo(nombreEquipo));
    log("ModEquipo: inicio buscar=" + nombreEquipo);

    if (elEquipo!=null) {
        System.out.println("Ingrese lo que desea modificar: 1-Director tecnico");
        opcion = sc.nextInt();
        if (opcion == 1) {
            write("Se a escojido la opcion 1, cambio de nombre del dt");
            System.out.println("Ingrese el nombre del director tecnico");
            sc.nextLine();
            dt = sc.nextLine();
            elEquipo.setDT(dt);
            System.out.println("Se a realizado el cambio con exito");
            write("El nuevo directo tecnico es: "+ dt);
            log("ModEquipo: OK DT=" + dt);
        } else {
            log("ModEquipo: sin cambios opcion=" + opcion);
        }
    }else{
        System.out.println("El equipo no existe");
        log("ModEquipo: FAIL no existe " + nombreEquipo);
    }
}

    //--------------------------ALTA DE Partidos--------------------------
    public static void agregarPartido() throws IOException{
    String equipo1,equipo2,fase,ciudad,estadio,cadena;
    int goles1,goles2;
    Equipo eq1,eq2;
    Ciudad laCiudad;
    boolean exito;

    System.out.println("Ingrese el nombre del equipo 1: ");
    System.out.print(">");
    equipo1 = sc.nextLine();
    eq1 = (Equipo) arbolEquipos.buscarElemento(new Equipo(equipo1));
    cadena = "Se a ingresado en el alta de partidos pero no se realizo ninguna accion";
    log("AltaPartido: inicio eq1=" + equipo1);

    if (eq1!=null) {
        System.out.println("Ingrese el nombre del equipo 2: ");
        System.out.print(">");
        equipo2 = sc.nextLine();
        eq2 = (Equipo) arbolEquipos.buscarElemento(new Equipo(equipo2));
        if (eq2!=null) {
            System.out.println("Ingrese la ciudad en la que se realizo: ");
            System.out.print(">");
            ciudad =sc.nextLine();
            laCiudad = (Ciudad)mapa.recuperarElemento(new Ciudad(ciudad));
            if (laCiudad!=null && laCiudad.getEsSede() == true) {
                System.out.println("Ingrese el estadio en el que se jugo ");
                System.out.print(">");
                estadio = sc.nextLine();
                System.out.println("Ingrese los goles que marco el equipo 1: ");
                System.out.print(">");
                goles1= sc.nextInt();
                sc.nextLine();
                System.out.println( "Ingrese los goles que marco el equipo 2: ");
                System.out.print(">");
                goles2= sc.nextInt();
                sc.nextLine();

                if (goles1>=0 && goles2 >=0) {

                    System.out.println("Ingrese la fase en la que se jugo este partido: ");
                    System.out.print(">");
                    fase=sc.nextLine().toUpperCase().trim();

                    Partido partido = new Partido(equipo1, equipo2, fase, ciudad, estadio, goles1, goles2);
                    exito= tablaPartidos.insertar(partido);

                    if (exito) {
                        System.out.println("Se a cargado el equipo correctamente");
                        cadena="Se cargo el partido "+partido.toStringExtenso();
                        log("AltaPartido: OK " + partido.toString());
                        if (fase.equals("GRUPOS")) {
                            eq1.actualizarGoles(goles1, goles2);
                            eq2.actualizarGoles(goles2, goles1);
                        }
                    }else{
                        System.out.println("El partido que intentas ingresar ya exite");
                        cadena="Se a ingresado en el alta de partidos pero no se realizo ninguna accion";
                        log("AltaPartido: FAIL duplicado " + equipo1 + " vs " + equipo2);
                    }

                }else{
                    System.out.println("No es posible ingresar goles negativos");
                    log("AltaPartido: FAIL goles negativos");
                }
            }else{
                System.out.println("La ciudad ingresada no corresponde a ninguna sede");
                log("AltaPartido: FAIL ciudad no sede=" + ciudad);
            }
        }else{
            System.out.println("El equipo ingresado no existe");
            log("AltaPartido: FAIL eq2 no encontrado=" + equipo2);
        }
    }else{
        System.out.println("El equipo ingresado no exites");
        log("AltaPartido: FAIL eq1 no encontrado=" + equipo1);
    }
    write(cadena);
}

    public static void consultarSobreEquipo() throws IOException{
    int opcion;
    String cadena;

    cadena = "Se consulto por equipos y no se realizo ninguna accion";
    System.out.println("Opcones");
    System.out.println("Ver datos del equipo : 1");
    System.out.println("Listar rango : 2");
    opcion = sc.nextInt();
    sc.nextLine();
    log("ConsultaEquipo: inicio opcion=" + opcion);

    switch (opcion) {
        case 1:
            String pais;
            Equipo equipo;
            System.out.println("Ingrese el nombre del pais");
            System.out.print(">");
            pais = sc.nextLine().trim();
            equipo = (Equipo)arbolEquipos.buscarElemento(new Equipo(pais));
            if (equipo!=null) {
                cadena = "Se consultaron los datos del equipo "+ equipo.getNombre();
                System.out.println(">" + equipo.getNombre() + " con " + equipo.getPuntosGanados() + 
                " puntos ganados. Goles a favor: " + equipo.getGolesAFavor() + " | Goles en contra: " + equipo.getGolesEnContra()
                + " | Diferencia de goles: " + (equipo.getGolesAFavor() - equipo.getGolesEnContra()));
                log("ConsultaEquipo: OK datos " + equipo.getNombre());
            }else{
                System.out.println("No se a encontrado el pais ingresado");
                log("ConsultaEquipo: WARN no encontrado " + pais);
            }
            break;
        case 2:
            String min,max,respuesta;
            System.out.println("Ingrese la cadena min");
            min = sc.nextLine();
            System.out.println("Ingrese la cadena max");
            max = sc.nextLine();
            respuesta = arbolEquipos.listarPorRango(new Equipo(min), new Equipo(max)).toString();
            System.out.println( respuesta + "\n");
            log("ConsultaEquipo: OK rango [" + min + "," + max + "]");
            break;
        case 0:
            log("ConsultaEquipo: sin accion");
            break;
        default:
            log("ConsultaEquipo: opcion invalida=" + opcion);
            break;
    }
    write(cadena);
}

    public static void consultaPartido() throws IOException {
    String equipo1, equipo2, cadena;
    Partido partido;
    Equipo eqaux;

    System.out.println("Ingrese los nombres de los equipos que jugaron");
    System.out.println("Equipo 1: ");
    equipo1 = sc.nextLine();
    log("ConsultaPartido: inicio eq1=" + equipo1);

    eqaux = (Equipo)arbolEquipos.buscarElemento(new Equipo(equipo1));
    if (eqaux!=null) {
        System.out.println("Equipo 2: ");
        equipo2 = sc.nextLine();
        eqaux = (Equipo)arbolEquipos.buscarElemento(new Equipo(equipo2));
        if (eqaux !=null) {
            String equipoMenor = (equipo1.compareTo(equipo2) < 0) ? equipo1 : equipo2;
            String equipoMayor = (equipo1.compareTo(equipo2) < 0) ? equipo2 : equipo1;

            partido = (Partido) tablaPartidos.recuperarElemento(new Partido(equipoMenor, equipoMayor));

            if (partido == null) {
                cadena="No a se encontro el partido buscado " + " " + now;
                log("ConsultaPartido: NO ENCONTRADO " + equipo1 + " vs " + equipo2);
            } else {
                cadena= "Se encontro el partido " + equipo1 + " " + equipo2 + " " + now;
                log("ConsultaPartido: OK " + equipo1 + " vs " + equipo2);
            }

           System.out.println("Buscando partido.." + ((partido == null) ? "El partido no pudo encontrarse." : "\nPartido: \n" + partido.toStringExtenso())); 

        } else {
            cadena="No se encontro el equipo " + equipo2 + " " + now;
            System.out.println("El equipo ingresado no exite");
            log("ConsultaPartido: FAIL eq2 no encontrado=" + equipo2);
        }

    } else {
        cadena="No se encontro el equipo " + equipo1 + " " + now;
        System.out.println("El equipo ingresado no exite");
        log("ConsultaPartido: FAIL eq1 no encontrado=" + equipo1);
    }
    write(cadena);
}

    public static void consultarCaminoMasCorto(){
    String ciudadPartida, ciudadDestino;
    Ciudad ciudadOri,ciudadDest;
    System.out.println("Ingrese la ciudad de partida");
    ciudadPartida=sc.nextLine();
    ciudadOri= (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadPartida));
    log("CaminoMasCorto: inicio origen=" + ciudadPartida);

    if (ciudadOri!=null) {
        System.out.println("Ingrese la ciudad destino");
        ciudadDestino=sc.nextLine();
        ciudadDest= (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadDestino));
        if (ciudadDest!=null) {
            System.out.println(mapa.caminoMasCorto(ciudadOri, ciudadDest).toString());
            log("CaminoMasCorto: OK " + ciudadPartida + " -> " + ciudadDestino);
        } else {
            System.out.println("La ciudad destino no existe");
            log("CaminoMasCorto: FAIL destino no existe=" + ciudadDestino);
        }
    } else {
        System.out.println("La ciudad origen no existe");
        log("CaminoMasCorto: FAIL origen no existe=" + ciudadPartida);
    }
}

    public static void consultarCaminoMasRapido(){
    String ciudadPartida, ciudadDestino;
    Ciudad ciudadOri,ciudadDest;
    System.out.println("Ingrese la ciudad de partida");
    ciudadPartida=sc.nextLine();
    ciudadOri= (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadPartida));
    log("CaminoMasRapido: inicio origen=" + ciudadPartida);

    if (ciudadOri!=null) {
        System.out.println("Ingrese la ciudad destino");
        ciudadDestino=sc.nextLine();
        ciudadDest= (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadDestino));
        if (ciudadDest!=null) {
            System.out.println(mapa.caminoMenosPeso(ciudadOri, ciudadDest).toString());
            log("CaminoMasRapido: OK " + ciudadPartida + " -> " + ciudadDestino);
        } else {
            System.out.println("La ciudad destino no existe");
            log("CaminoMasRapido: FAIL destino no existe=" + ciudadDestino);
        }
    } else {
        System.out.println("La ciudad origen no existe");
        log("CaminoMasRapido: FAIL origen no existe=" + ciudadPartida);
    }
}

    public static void consultarCaminoMasRapidoEvitandoCiudad(){
    String ciudadPartida, ciudadDestino, ciudadEstorbo;
    Ciudad ciudadOri,ciudadDest,ciudadEst;
    System.out.println("Ingrese la ciudad de partida");
    ciudadPartida=sc.nextLine();
    ciudadOri= (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadPartida));
    log("CaminoRapidoEvitando: inicio origen=" + ciudadPartida);

    if (ciudadOri!=null) {
        System.out.println("Ingrese la ciudad destino");
        ciudadDestino=sc.nextLine();
        ciudadDest= (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadDestino));
        if (ciudadDest!=null) {
            System.out.println("Ingrese la ciudad que quiere evitar ");
            ciudadEstorbo = sc.nextLine();
            ciudadEst = (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadEstorbo));
            if (ciudadEst!=null) {
                System.out.println(mapa.caminoMenosPesoEvitandoUnVertice(ciudadOri, ciudadDest,ciudadEst).toString());
                log("CaminoRapidoEvitando: OK evitando=" + ciudadEstorbo);
            } else {
                System.out.println("La ciudad a evitar no existe");
                log("CaminoRapidoEvitando: FAIL evitar no existe=" + ciudadEstorbo);
            }
        } else {
            System.out.println("La ciudad destino no existe");
            log("CaminoRapidoEvitando: FAIL destino no existe=" + ciudadDestino);
        }
    } else {
        System.out.println("La ciudad origen no existe");
        log("CaminoRapidoEvitando: FAIL origen no existe=" + ciudadPartida);
    }
}

    public static void consultarTodosLosCaminos(){
    String ciudadPartida, ciudadDestino,ciudadDePaso;
    Ciudad ciudadOri,ciudadDest,ciudadDePa;
    Lista todosLosCaminos;
    int eleccion; 
    System.out.println("Ingrese la ciudad de partida");
    ciudadPartida=sc.nextLine();
    ciudadOri= (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadPartida));
    log("TodosLosCaminos: inicio origen=" + ciudadPartida);

    if (ciudadOri!=null) {
        System.out.println("Ingrese la ciudad destino");
        ciudadDestino=sc.nextLine();
        ciudadDest= (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadDestino));
        if (ciudadDest!=null) {
            todosLosCaminos = mapa.todosLosCaminos(ciudadOri, ciudadDest);
            System.out.println("Quiere que se filtren por: \n 1:Ciudad \n 2:Alojamiento");
            eleccion = sc.nextInt();
            sc.nextLine();

            switch (eleccion) {
                case 1:
                    System.out.println("Ingrese la ciudad por la que desea pasar ");
                    ciudadDePaso= sc.nextLine();
                    ciudadDePa= (Ciudad)mapa.recuperarElemento(new Ciudad(ciudadDePaso));
                    if (ciudadDePa!=null) {
                        System.out.println(filtroPorCiudad(todosLosCaminos, ciudadDePa).toString());
                        log("TodosLosCaminos: filtro ciudad=" + ciudadDePaso);
                    } else {
                        System.out.println("La ciudad ingresada para filtrar no existe");
                        log("TodosLosCaminos: FAIL filtro ciudad no existe=" + ciudadDePaso);
                    }
                    break;
                case 2:
                    System.out.println(filtroPorAlojamiento(todosLosCaminos));
                    log("TodosLosCaminos: filtro alojamiento");
                    break;
                default:
                    log("TodosLosCaminos: sin filtro opcion=" + eleccion);
                    break;
            }
        } else {
            System.out.println("La ciudad destino no existe");
            log("TodosLosCaminos: FAIL destino no existe=" + ciudadDestino);
        }
    } else {
        System.out.println("La ciudad origen no existe");
        log("TodosLosCaminos: FAIL origen no existe=" + ciudadPartida);
    }
}
    private static Lista filtroPorAlojamiento(Lista todosLosCaminos) {
    log("FiltroAlojamiento: inicio totalCaminos=" + todosLosCaminos.longitud());
    Lista caminosFiltrados = new Lista();

    for (int i = 0; i < todosLosCaminos.longitud(); i++) { // (base 1-based en recuperar)
        Lista camino = (Lista) todosLosCaminos.recuperar(i + 1);

        boolean alojamiento = false;
        int j = 0; // 0..len-1, recupero en j+1

        // while: corto cuando encuentro una ciudad con alojamiento (sin cambiar lógica)
        while (!alojamiento && j < camino.longitud()) {
            Ciudad ciudad = (Ciudad) camino.recuperar(j + 1);
            alojamiento = ciudad.getAlojamiento();
            if (alojamiento) {
                caminosFiltrados.insertar(camino, caminosFiltrados.longitud() + 1);
            }
            j++;
        }
    }
    log("FiltroAlojamiento: fin result=" + caminosFiltrados.longitud());
    return caminosFiltrados;
}

    private static Lista filtroPorCiudad(Lista todosLosCaminos, Ciudad laciudad) {
    log("FiltroCiudad: inicio ciudad=" + laciudad.getNombre() + " totalCaminos=" + todosLosCaminos.longitud());
    Lista caminosFiltrados = new Lista();
    Ciudad ciudad= null;

    for (int i = 0; i < todosLosCaminos.longitud(); i++) {
        Lista camino = (Lista) todosLosCaminos.recuperar(i + 1);
        int j = 0;
        ciudad = null;

        // while: corto cuando encuentro esa ciudad (misma instancia) en el camino
        while (!(laciudad==ciudad) && j < camino.longitud()) { // (lógica original intacta)
            ciudad = (Ciudad) camino.recuperar(j + 1); 
            if (laciudad==ciudad) {
                caminosFiltrados.insertar(camino, caminosFiltrados.longitud() + 1);
            }
            j++;
        }
    }
    log("FiltroCiudad: fin result=" + caminosFiltrados.longitud());
    return caminosFiltrados;
}


    //--------------------------              CARGA DE DATOS          --------------------------

    public static ArbolAVL cargarEquipos() {
    log("CargarEquipos: inicio");
    ArbolAVL arbolEquipos = new ArbolAVL();
    String cadena;
    FileReader archivo;
    BufferedReader lector;
    Equipo equipo;

    try {
        archivo = new FileReader("../ArchivoEquipos.txt");
        lector = new BufferedReader(archivo);

        while ((cadena = lector.readLine()) != null) {
            equipo = crearEquipo(cadena);
            arbolEquipos.insertar(equipo);
        }
        log("CargarEquipos: OK");
    } catch (Exception e) {
        System.out.println("Error:" + e.getMessage());
        log("CargarEquipos: FAIL " + e.getMessage());
    }
    return arbolEquipos;
}

    public static Equipo crearEquipo(String cadena) {
    // formato esperado: nombre;dt;grupo
    String[] atributos = cadena.split(";");
    Equipo equipo = new Equipo(atributos[0], atributos[1], atributos[2]);
    log("CrearEquipo: OK " + equipo.toString());
    return equipo;
}

    public static GrafoEtiquetado cargarCiudades() {
    log("CargarCiudades: inicio");
    GrafoEtiquetado grafoCiudades = new GrafoEtiquetado();
    String cadena;
    FileReader archivo;
    BufferedReader lector;
    Ciudad ciudad;

    try {
        archivo = new FileReader("../ArchivoCiudades.txt");
        lector = new BufferedReader(archivo);
        // Primero cargo todas las ciudades
        while ((cadena = lector.readLine()) != null) {
            ciudad = crearCiudad(cadena);
            grafoCiudades.insertarVertice(ciudad);
        }
        // Ahora cargo las rutas entre ciudades
        archivo = new FileReader("../ArchivoRutas.txt");
        lector = new BufferedReader(archivo);
        while ((cadena = lector.readLine()) != null) {
            String[] atributos = cadena.split(";");
            // Cargo con new Ciudad para poder ubicar la ciudad
            grafoCiudades.insertarArco(new Ciudad(atributos[0]), new Ciudad(atributos[1]), Integer.parseInt(atributos[2]));
        }
        log("CargarCiudades: OK");
    } catch (Exception e) {
        System.out.println("Error:" + e.getMessage());
        log("CargarCiudades: FAIL " + e.getMessage());
    }
    return grafoCiudades;
}

    public static Ciudad crearCiudad(String cadena) {
    // formato esperado: nombre;alojamiento;esSede
    String[] atributos = cadena.split(";");
    Ciudad ciudad = new Ciudad(atributos[0], Boolean.parseBoolean(atributos[1]), Boolean.parseBoolean(atributos[2]));
    log("CrearCiudad: OK " + ciudad.getNombre() + " alo=" + atributos[1] + " sede=" + atributos[2]);
    return ciudad;
}

    public static TablaHash cargarPartidos(){
    log("CargarPartidos: inicio");
    TablaHash partidos = new TablaHash();
    String cadena;
    FileReader archivo;
    BufferedReader lector;
    Equipo equipo1;
    Equipo equipo2;
    Partido partido;

    try {
        archivo = new FileReader("../ArchivoPartidos.txt");
        lector = new BufferedReader(archivo);

        while ((cadena = lector.readLine()) != null) {
            partido = crearPartido(cadena);
            equipo1 = (Equipo) arbolEquipos.buscarElemento(new Equipo(partido.getNombreEq1()));
            equipo2 = (Equipo) arbolEquipos.buscarElemento(new Equipo(partido.getNombreEq2()));

            // Actualizaciones según tu lógica original
            equipo1.actualizarGoles(partido.getGolesEq1(), partido.getGolesEq2());
            equipo2.actualizarGoles(partido.getGolesEq2(), partido.getGolesEq1());

            if (partido.getInstancia().equals("GRUPO")) {
                equipo1.actualizarPuntos(partido.getGolesEq1(), partido.getGolesEq2());
                equipo2.actualizarPuntos(partido.getGolesEq2(), partido.getGolesEq1());
            }

            partidos.insertar(partido);
        }
        log("CargarPartidos: OK");
    } catch (Exception e) {
        System.out.println("Error:" + e.getMessage());
        log("CargarPartidos: FAIL " + e.getMessage());
    }
    return partidos;
}

    public static Partido crearPartido(String cadena){
    // formato esperado: eq1;eq2;fase;ciudad;estadio;g1;g2
    String[] atributos = cadena.split(";");
    Partido partido = new Partido(
        atributos[0], atributos[1], atributos[2],
        atributos[3], atributos[4],
        Integer.parseInt(atributos[5]),
        Integer.parseInt(atributos[6])
    );
    log("CrearPartido: OK " + partido.toString());
    return partido;
}

    public static void write(String cadena) throws IOException {

        try (FileWriter fout = new FileWriter(obj, true)) {
            if (obj.canWrite()) {
                fout.write(cadena + "\n");
            }
        }
    }
    // --- LOG helper: usa write(...) internamente, sin cambiar firmas ---
    private static void log(String msg) {
    try {
        write("[" + LocalDateTime.now() + "] " + msg);
    } catch (IOException e) {
        System.err.println("LOG ERROR: " + e.getMessage());
    }
}
// Deja el log vacío (0 bytes). Usa el mismo 'obj' que ya tenés.
    private static void resetLog() {
    try (FileWriter fw = new FileWriter(obj, /* append = */ false)) {
        // opcional: fw.write("=== LOG reseteado ===\n");
    } catch (IOException e) {
        System.err.println("No pude resetear el log: " + e.getMessage());
    }
}



    

}