package clases;
public class EquiposGolesAFavor implements Comparable<EquiposGolesAFavor> {
    private Equipo equipo;
    private Comparable golesAFavor;
    public EquiposGolesAFavor(Equipo elEquipo){
        this.equipo = elEquipo;
        this.golesAFavor = this.equipo.getGolesAFavor();
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public boolean equals(EquiposGolesAFavor otro){
        return this.equipo.equals(otro.equipo);
    }

    public int compareTo(EquiposGolesAFavor otro){
        int rta;
        if (this.golesAFavor == otro.golesAFavor){
            rta = (this.equipo.compareTo(otro.equipo));
        }else{
            rta = this.golesAFavor.compareTo(otro.golesAFavor);
        }
        return rta;
    }

    public String toString(){
        return this.equipo.getNombre() + "(" + this.golesAFavor + ")";
    }

}
