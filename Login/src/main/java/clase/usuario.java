package clase;

public class usuario {
    
   private String nombre;
   private String apellido;
   private String DNI;
   int Celular;

    public usuario() {
    }

    public usuario(String nombre, String apellido, String DNI, String Celular) {
        this.nombre = "";
        this.apellido = "";
        this.DNI = "";
        this.Celular = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getCelular() {
        return Celular;
    }

    public void setCelular(int Celular) {
        this.Celular = Celular;
    }

   
   
   

   
}
