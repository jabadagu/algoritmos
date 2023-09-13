package libreria.login;

import clase.usuario;

public class botones {
    private static Registro_usuario_contraseña registro;
    private static usuario Usuario[];
    private static int contador;

    public static void main(String[] args) {
        registro = new Registro_usuario_contraseña();
        Usuario = new usuario[100];
        //arreglo para agregar de 0 a 99 elementos
        //arreglo usuario
        for(int i=0; i<100; i++){
            Usuario[i] = new usuario();
        }
        contador = 0;
        
        registro.setVisible(true);
    }
    //ahora crearemos el metodo guardar y buscar
    public static void guardar(String n, String a, String d, int cel){
        //lo que hemos hecho es haber creado 4 variables
        Usuario[contador].setNombre(n);
        Usuario[contador].setApellido(a);
        Usuario[contador].getDNI();
        Usuario[contador].setCelular(cel);
        
        contador++;
    }
    public static void buscar(String bus){
        //creamos la variables buscar bus
        for (int j=0; j < Usuario.length; j++ ){
            if(Usuario[j].getNombre().equals(bus)){
                registro.cargardatos(Usuario[j]);
            }
        }
    }
    
}
