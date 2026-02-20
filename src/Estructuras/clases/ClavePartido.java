package Estructuras.clases;

public class ClavePartido {

    private String nombreEq1;
    private String nombreEq2;

    public ClavePartido(String nombreEq1, String nombreEq2) {
        this.nombreEq1 = nombreEq1;
        this.nombreEq2 = nombreEq2;
    }

    public int hashCode() {
    int hash;
    if (nombreEq1.compareTo(nombreEq2) < 0) {
        hash = (nombreEq1 + nombreEq2).hashCode();
    } else {
        hash = (nombreEq2 + nombreEq1).hashCode();
    }
    return hash;
}

    public String getNombreEq1() {
        return nombreEq1;
    }

    public String getNombreEq2() {
        return nombreEq2;
    }
    
    

    public boolean equals(Object elem) {
        //Implemento el equals ya que puede ser que dos Partidos tengan la misma clave hashCode pero sean distintos.
        Partido partido = (Partido) elem;

        return (this.nombreEq1.equals(partido.getNombreEq1())) && (this.nombreEq2.equals(partido.getNombreEq2()));

    }

}
