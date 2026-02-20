package Estructuras.clases;

public class Partido {

    private ClavePartido clavePartido;
    private String nombreEq1;
    private String nombreEq2;
    private String instancia;
    private String ciudad;
    private String estadio;
    private int golesEq1;
    private int golesEq2;

    public Partido(String nombreEq1, String nombreEq2) {
        this.nombreEq1 = nombreEq1.toUpperCase();
        this.nombreEq2 = nombreEq2.toUpperCase();
          this.clavePartido = new ClavePartido(nombreEq1, nombreEq2);
    }
    

    public Partido(String nombreEq1, String nombreEq2, String instancia, String ciudad, String estadio, int golesEq1, int golesEq2) {
        this.nombreEq1 = nombreEq1.toUpperCase();
        this.nombreEq2 = nombreEq2.toUpperCase();
        this.instancia = instancia.toUpperCase();
        this.ciudad = ciudad.toUpperCase();
        this.estadio = estadio.toUpperCase();
        this.golesEq1 = golesEq1;
        this.golesEq2 = golesEq2;
        //Creo la clave cuándo creo la instancia "equipo".
        this.clavePartido = new ClavePartido(nombreEq1, nombreEq2);

    }

    public String getNombreEq1() {
        return nombreEq1;
    }

    public String getNombreEq2() {
        return nombreEq2;
    }

    public String getInstancia() {
        return instancia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEstadio() {
        return estadio;
    }

    public int getGolesEq1() {
        return golesEq1;
    }

    public int getGolesEq2() {
        return golesEq2;
    }

    public void setInstancia(String instancia) {
        this.instancia = instancia.toUpperCase();
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad.toUpperCase();
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio.toUpperCase();
    }

    public void setGolesEq1(int golesEq1) {
        this.golesEq1 = golesEq1;
    }

    public void setGolesEq2(int golesEq2) {
        this.golesEq2 = golesEq2;
    }
    
    public String toStringExtenso(){
        
        return "Equipo1: " + nombreEq1 + " Equipo 2: " + nombreEq2 + " Instancia: " + instancia + "\n Ciudad: " + ciudad + " Estadio: " + estadio + " Goles: " + golesEq1 + ";" + golesEq2;
        
        
        
    }
    
    public String toString(){
        
        return (nombreEq1 + " : " + nombreEq2 +" "+ golesEq1 +":" + golesEq2 );
        
        
        
        
    }
    
    public boolean equals(Object elem){
        
        
        return (this.clavePartido.equals(((Partido) elem)));
    }
    
    public int hashCode(){
        return this.clavePartido.hashCode();
    }

}