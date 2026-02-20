package Estructuras.clases;
public class Equipo implements Comparable<Equipo> {
    private String nombre;
    private String dt;
    private String grupo;
    private int golesEnContra;
    private int golesAfavor;
    private int puntos;

    public Equipo(String nombre){
        this.nombre = nombre.toUpperCase().trim();
    }

    public Equipo(String nombre, String dt, String grupo) {
        this.nombre = nombre.toUpperCase().trim();
        this.dt = dt;
        this.grupo = grupo;
        this.golesAfavor=0;
        this.golesEnContra=0;
        this.puntos=0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDt() {
        return dt;
    }

    public String getGrupo() {
        return grupo;
    }
    public int getPuntosGanados(){
        return puntos;
    }
    public int getGolesAFavor(){
        return golesAfavor;
    }
    public int getGolesEnContra(){
        return golesEnContra;
    }
    public void setNombre(String elNombre){
        nombre = elNombre;
    }
    public void setDT(String elDT){
        nombre = elDT;
    }

    public void setGrupo(String elGrupo){
        nombre = elGrupo;
    }


    public boolean actualizarGoles(int golesAF, int golesEnC){
        golesAfavor = golesAF + golesAfavor;
        golesEnContra = golesEnC + golesEnContra;
        return true;
    }
    public boolean actualizarPuntos(int golesAF, int golesEnC){
        if (golesAF>golesEnC) {
            puntos+=3;
        }else{
            if (golesAF==golesEnC) {
                puntos+=1;
            }
        }
        return true;
    }

    @Override
    public int compareTo(Equipo otro) {
        return this.nombre.compareTo(otro.nombre);
    }

    @Override
    public String toString() {
        return nombre + ";" + dt + ";" + grupo;
    }
}
