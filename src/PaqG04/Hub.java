package PaqG04;

import java.io.Serializable;
import java.util.Objects;

public class Hub implements Serializable {
    Contenedor[][] contenedores;        // CREAMOS LA MATRIZ DE CONTENEDORES

    // CONSTRUCTOR QUE RECIBE POR PARÁMETROS FILAS Y COLUMNAS Y CREA LA MATRIZ
    public Hub(int filas, int columnas){
        contenedores = new Contenedor[filas][columnas];
    }

    public Contenedor getContenedor(int fila, int columna){
        if(fila<0 || fila> contenedores.length || columna<0 || columna> contenedores[0].length){
            return null;
        }
        return contenedores[fila][columna];
    }

    // METODO toString EN EL QUE MOSTRAMOS LA MATRIZ CON "L" DONDE NO HAY CONTENEDOR Y "O" EN CASO DE QUE HAYA CONTENEDOR
    public String toString(){
        String resultado="";
        for(int f=0;f<contenedores.length;f++){
            for(int c=0;c<contenedores[f].length;c++){
                if(contenedores[f][c]==null){
                    resultado+="L ";
                }else{
                    resultado+="O ";
                }
            }
            resultado+="\n";
        }
        return resultado;
    }

    // METODO QUE APILA EL CONTENEDOR EN LA MATRIZ UNA VEZ CREADO
    public boolean apilarContenedor(Contenedor contenedor) {
        if (contenedor.prioridad == 1) {                            // CONTENEDORES CON PRIORIDAD 1, COLUMNA 1 DE LA MATRIZ
            for (int f = contenedores.length-1; f >= 0; f--) {      // RECORREMOS LA MATRIZ DE MANERA QUE "FISICAMENTE" LOS CONTENEDORES SE COLOQUEN DESDE EL SUELO HACIA ARRIBA
                if (contenedores[f][0] == null) {
                    contenedores[f][0] = contenedor;
                    return true;
                }
            }
            return false;
        }
        if (contenedor.prioridad == 2) {
            for (int f = contenedores.length-1; f >= 0; f--) {      // CONTENEDORES CON PRIORIDAD 2, COLUMNA 2 DE LA MATRIZ
                if (contenedores[f][1] == null) {
                    contenedores[f][1] = contenedor;
                    return true;
                }
            }
            return false;
        }
        for (int c = 2; c < contenedores[0].length; c++) {
            for (int f = contenedores.length - 1; f >= 0; f--) {
                if (contenedores[f][c] == null) {
                    contenedores[f][c] = contenedor;
                    return true;
                }
            }
        }
        return false;
    }

    // METODO PARA DESAPILAR
    public boolean desapilar(int columna) {
        if (columna < 0 || columna > contenedores[0].length) {      // ESTAMOS FUERA DE LA MATRIZ
            return false;
        }
        for (int f = 0; f < contenedores.length; f++) {
            if (contenedores [f][columna] != null) {                // EN CASO DE QUE HAYA, LO ELIMINAMOS Y LO PONEMOS NULL
                contenedores[f][columna] = null;
                return true;
            }
        }
        return false;
    }

    // METODO PARA MOSTRAR DATOS DE LOS CONTENEDORES
    public String mostrarDatos(int id){
        String resultado="";
        for(int f=0;f< contenedores.length; f++){
            for (int c = 0; c < contenedores[f].length; c++) {
                if(contenedores[f][c]!=null){
                    if(contenedores[f][c].id==id){                  // EN CASO DE QUE LA id COINCIDA CON LA PASSADA POR PARÁMETROS, MOSTRAMOS TODOS LOS DATOS DEL CONTENEDOR
                        resultado+="Id: "+contenedores[f][c].id+"\n";
                        resultado+="Peso: "+contenedores[f][c].peso+"\n";
                        resultado+="Pais: "+contenedores[f][c].pais+"\n";
                        resultado+="Control de aduanas: "+contenedores[f][c].aduanas+"\n";
                        resultado+="Prioridad: "+contenedores[f][c].prioridad+"\n";
                        resultado+="Descripción: "+contenedores[f][c].descripcion+"\n";
                        resultado+="Empresa que lo envía: "+contenedores[f][c].empresaEnvia+"\n";
                        resultado+="Empresa que lo recibe: "+contenedores[f][c].empresaRecibe+"\n";
                        return resultado;
                    }
                }
            }
        }
        return "0";
    }
    public String mostrarDatos2 (Contenedor contenedor){
        String resultado = "";
            for (int c = 0; c < contenedores[10].length; c++) {
                if(contenedores[10][c]!=null){                      // SI HAY CONTENEDOR, MUESTRO
                    resultado += "Id: "+contenedores[10][c].id+"\n";
                    resultado += "Peso: "+contenedores[10][c].peso+"\n";
                    resultado += "Control de aduanas: "+contenedores[10][c].aduanas+"\n";
                    resultado += "Empresa que lo envía: "+contenedores[10][c].empresaEnvia+"\n";
                }
            }
            return resultado;
    }

    // METODO QUE CALCULA CUÁNTOS CONTENEDORES TENEMOS DE CADA PAIS (EL PAIS SE PASA POR PARÁMETROS)
    public int calcularContenedoresDeterminadoPais(String pais){
        int cont=0;
        for(int f=0;f< contenedores.length; f++){
            for (int c = 0; c < contenedores[f].length; c++) {
                if(contenedores[f][c]!=null) {
                    if (Objects.equals(contenedores[f][c].pais, pais)) {
                        cont++;
                    }
                }
            }
        }
        return cont;
    }
}