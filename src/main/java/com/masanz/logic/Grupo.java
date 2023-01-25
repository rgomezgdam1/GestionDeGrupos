package com.masanz.logic;

import java.util.Arrays;

public class Grupo {

    private String nombre;
    private Persona[] personas;
    private int tamano;
    private int filas;
    private int columnas;

    public Grupo(String nombre, int filas, int columnas) {
        this.nombre = nombre;
        this.filas = filas;
        this.columnas = columnas;
        personas = new Persona[filas*columnas];
        tamano = 0;
    }

    /**
     * Este método no tiene sentido en la lógica de la aplicación.
     * Se ha incorporado para ayudar a debuguear los métodos.
     * Hay que tener en cuenta que las personas deberan estar metidas
     * de forma ascendente en base a los apellidos y el nombre en el array.
     * @param personas Array de de personas ordenado, puede tener nulls al final.
     * @param tamano Cantidad de personas que se deben considerar.
     */
    public Grupo setPersonasTamano(Persona[] personas, int tamano) {
        this.personas = personas;
        this.tamano = tamano;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

//    public boolean add(Persona persona) {
//        //Esto no mete a la persona de forma ordenada, puntúa cero.
//        if (tamano < personas.length) {
//            personas[tamano++] = persona;
//            getPersonasPorApellidos();
//            return true;
//        }
//        return false;
//    }

    public boolean add(Persona persona) {
        Persona[] tmp = find(persona.getApellidos());
        if(tmp != null){
            for (int i = 0; i < tmp.length; i++) {
                if (persona.getNombre() == tmp[i].getNombre()){
                    return false;
                }
            }
        }
        personas[tamano++] = persona;
        getPersonasPorApellidos();
        return true;
    }

    public int getCuantosSuspendidosHay(){
        int tmp = 0;
        for (int i = 0; i < tamano; i++) {
            if (personas[i].getPuntos() < 50){
                tmp++;
            }
        }
        return tmp;
    }

    public Persona[] getPersonasPorApellidos() {
        Persona[] copia = Arrays.copyOf(personas, tamano);
        for (int i = 1; i < copia.length; i++) {
            Persona aux = copia[i];
            int j = i - 1 ;
            while (j >= 0 && copia[j].getApellidosNombre().compareTo(aux.getApellidosNombre()) > 0){
                copia[j + 1] = copia[j];
                j--;
            }
            copia[j + 1] = aux;
        }
        System.arraycopy(copia,0,personas,0,copia.length);
        return copia;
    }

    /**
     * Algoritmo de inserción directa de una persona p en un array a que ya tiene t personas.
     * @param a array de personas, de longitud n.
     * @param t numero de personas ya insertadas, t>=0 y t<=n-1
     * @param p persona a insertar con una cantidad de puntos
     */
    public static void insercionDirectaPorPuntosCreciente(Persona[] a, int t,  Persona p) {

        int i = t;
        while (i > 0 && a[i - 1].getPuntos() > p.getPuntos()){
            a[i] = a[i - 1];
            i--;
        }
        a[i] = p;
    }

    public Persona[] getPersonasSuspendidas() {
        Persona [] tmp = new Persona[getCuantosSuspendidosHay()];
        int a = 0;
        for (int i = 0; i < tamano; i++) {
            if (personas[i].getPuntos() < 50){
                insercionDirectaPorPuntosCreciente(tmp,a,personas[i]);
                a++;
            }
        }
        return tmp;
    }

    /**
     * Ordena un array de personas utilizando el algoritmo de ordenación por selección directa
     * de puntos de forma descendente.
     * @param a array de personas, todas las celdas tienen una referencia a un objeto persona
     *          distinto de null. Al final estarán ordenados por la puntuación de más a menos.
     */
    public static void ordenacionPorSeleccionDirectaDePuntosDescendente(Persona[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int posmin = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].getPuntos() > a[posmin].getPuntos()) {
                    posmin = j;
                }
            }
            Persona aux = a[posmin];
            a[posmin] = a[i];
            a[i] = aux;
        }
    }

    public Persona[] getPersonasOrdenadasPorPuntos() {
        Persona[] a = new Persona[tamano];
        System.arraycopy(personas,0,a,0,tamano);
        ordenacionPorSeleccionDirectaDePuntosDescendente(a);
        return a;
    }

    /**
     * Devuelve el índice, de 0 a tamano, de la posición que le corresponde a un texto
     * en la ordenación alfabética de apellidos y nombre si se fuese a insertar.
     * Si el texto coicide con getApellidosNombre se debe devolver esa posición.
     * Aunque en el array se considere que hay tamano personas, de 0 a tamano-1,
     * si alfabéticamente le correspondiese después del último, se debería devolver
     * el valor tamano, no tamano-1.
     * @param txt texto cuyo indice de inserción se busca, ej "García, Aiora"
     * @return posición del array en la que se insertaría
     */
    public int busquedaDicotomica(String txt) {
            int izquierda = 0;
            int derecha = personas.length - 1;
            if (derecha > tamano){
                derecha = tamano;
            }
            int mitad = (izquierda + derecha) / 2;
            while (izquierda<= derecha) {
                mitad = (izquierda + derecha) / 2;
                if (personas[mitad] == null) {
                    return tamano;
                }
                else if (personas[mitad].getApellidosNombre().equals(txt)) {
                    return mitad;
                }
                else if (personas[mitad].getApellidosNombre().compareTo(txt) > 0) {
                    derecha = mitad - 1;
                }
                else {
                    izquierda = mitad + 1;
                }
            }
            return izquierda;
    }

    /**
     * Devuelve un array de personas cuyos apellidos empiezan como se indica
     * @param apellidos texto por el cual deben empezar los apellidos
     * @return una array con las referencias a las personas, puede ser de tamaño 0.
     */
    public Persona[] find(String apellidos) {
        int tmp = busquedaDicotomica(apellidos);
        int idx = 0;
        Persona [] aux= new Persona[tamano];
            for (int i = tmp; i < tamano; i++) {
                if (personas[i].getApellidos().startsWith(apellidos)){
                    aux[idx++] = personas[i];
                }
            }
            Persona [] copia2 = Arrays.copyOf(aux,idx);
        return copia2;
    }

    public int numeroPrimer(String apellidos) {
        return busquedaDicotomica(apellidos) + 1;
    }

    /**
     * Devuelve la persona según el índice alfabético mostrado
     * @param idx indice válido del 1 en adelante hasta tamaño incluido
     * @return persona o null si no existe
     */
    public Persona getPersona(int idx) {
        if(idx > 0 && idx <= tamano){
            return personas[idx - 1];
            }
        return null;
    }

    /**
     * Borra una persona del grupo eliminandolo del array y dejandolo ordenado y seguido con el tamano reducido
     * @param idx indice válido del 1 en adelante hasta tamaño incluido
     */
    public boolean del(int idx) {
        if (idx > tamano){
            return false;
        }
        for (int i = idx; i < tamano; i++) {
            personas[i - 1] = personas[i];
        }
        personas[tamano-- - 1] = null;
        return true;
    }

    @Override
    public String toString() {
        return "{" +
                "nombre:\"" + nombre + "\"" +
                ", tamano:" + tamano +
                ", filas:" + filas +
                ", columnas:" + columnas +
                '}';
    }

}
