package Estructuras.clases;

public class Ciudad {
    private String nombre;
    private Boolean alojamiento;
    private Boolean esSede;

// Constructor
    public Ciudad(String nombre){
        this.nombre = nombre;
    }
    public Ciudad(String nombre, Boolean alojamiento, Boolean esSede) {
        this.nombre = nombre.toUpperCase();
        this.alojamiento = alojamiento;
        this.esSede = esSede;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Boolean alojamiento) {
        this.alojamiento = alojamiento;
    }

    public Boolean getEsSede() {
        return esSede;
    }

    public void setEsSede(Boolean esCede) {
        this.esSede = esCede;
    }

    public boolean cambiarEsCede(){
        esSede = !esSede;
        return true;
    }
    public boolean cambiarAlojamiento(){
        alojamiento=!alojamiento;
        return true;
    }

     public String toStringExtenso(){
        
        return ("Nombre: " + nombre + " Alojamiento: " + alojamiento + " Sede: " + esSede);
    }

    public String toString() {
        return nombre;
    }

    public boolean equals(Object elem) {
        Ciudad ciudad = (Ciudad) elem;

        return (this.nombre.equals(ciudad.getNombre()));
    }
}