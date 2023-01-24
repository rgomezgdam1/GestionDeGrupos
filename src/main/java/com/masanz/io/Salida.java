package com.masanz.io;

import com.masanz.logic.GestorDeGrupos;
import com.masanz.logic.Grupo;
import com.masanz.logic.Persona;

public class Salida {

    public static void info(String msg) {
        System.out.println(msg);
    }

    public static void warning(String msg) {
        System.out.println(msg);
    }

    public static void menuPrincipal() {
        System.out.println("=======================================================");
        System.out.println("=          G e s t i ó n   d e   G r u p o s          =");
        System.out.println("=======================================================");
        System.out.println("\t1. Operaciones de grupos");               //M_PR_GRUPOS
        System.out.println("\t2. Operaciones con personas");            //M_PR_PERSONAS
        System.out.println("\t0. Terminar");                            //M_PR_TERMINAR
        System.out.println("=======================================================");
    }

    public static void menuGrupos() {
        System.out.println("-------------------------------------------------------");
        System.out.println("-      O p e r a c i o n e s   d e   g r u p o s      -");
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\t\t1. Listado de grupos");               //M_GR_LST_GRUPO
        System.out.println("\t\t\t2. Personas de un grupo x apellido"); //M_GR_LST_APELLIDOS
        System.out.println("\t\t\t3. Personas de un grupo x puntos");   //M_GR_LST_PUNTOS
        System.out.println("\t\t\t4. Listado suspendidos");             //M_GR_LST_SUSPENS
        System.out.println("\t\t\t5. Pintar sitios");                   //M_GR_PINTAR
        System.out.println("\t\t\t0. Terminar");                        //M_GR_TERMINAR
        System.out.println("-----------------------------------------------------------");
    }

    public static void menuPersonas() {
        System.out.println("-------------------------------------------------------");
        System.out.println("-   O p e r a c i o n e s   c o n   p e r s o n a s   -");
        System.out.println("-------------------------------------------------------");
        System.out.println("\t\t\t1. Nueva persona");                   //M_PE_CREAR
        System.out.println("\t\t\t2. Buscar por apellido");             //M_PE_BUSCAR
        System.out.println("\t\t\t3. Modificar datos");                 //M_PE_ACTUALIZAR
        System.out.println("\t\t\t4. Borrar persona de un grupo");      //M_PE_BORRAR
        System.out.println("\t\t\t5. Listar persona de un grupo");      //M_PE_LISTAR
        System.out.println("\t\t\t0. Terminar");                        //M_PE_TERMINAR
        System.out.println("-----------------------------------------------------------");
    }


    public static void titGrListadoDeGrupos() {
        System.out.println("-----------------------------------------------------");
        System.out.println("1. Listado de grupos");
        System.out.println("-----------------------------------------------------");
    }

    public static void titGrPersonasPorApellido() {
        System.out.println("-----------------------------------------------------");
        System.out.println("2. Personas de un grupo x apellido");
        System.out.println("-----------------------------------------------------");
    }

    public static void titGrPersonasDeUnGrupoPorPuntos() {
        System.out.println("-----------------------------------------------------");
        System.out.println("3. Personas de un grupo x puntos");
        System.out.println("-----------------------------------------------------");
    }

    public static void titGrListadoSuspendidos() {
        System.out.println("-----------------------------------------------------");
        System.out.println("4. Listado suspendidos");
        System.out.println("-----------------------------------------------------");
    }

    public static void titGrPintarSitios() {
        System.out.println("-----------------------------------------------------");
        System.out.println("5. Pintar sitios");
        System.out.println("-----------------------------------------------------");
    }

    public static void titPeNuevaPersona() {
        System.out.println("-----------------------------------------------------");
        System.out.println("1. Nueva persona");
        System.out.println("-----------------------------------------------------");
    }

    public static void titPeBuscarPorApellido() {
        System.out.println("-----------------------------------------------------");
        System.out.println("2. Buscar por apellido");
        System.out.println("-----------------------------------------------------");
    }

    public static void titPeModificarDatos() {
        System.out.println("-----------------------------------------------------");
        System.out.println("3. Modificar datos");
        System.out.println("-----------------------------------------------------");
    }

    public static void titPeBorrarPersonaDeUnGrupo() {
        System.out.println("-----------------------------------------------------");
        System.out.println("4. Borrar persona de un grupo");
        System.out.println("-----------------------------------------------------");
    }

    public static void titPeListarPersonasDeUnGrupo() {
        System.out.println("-----------------------------------------------------");
        System.out.println("5. Listar personas de un grupo");
        System.out.println("-----------------------------------------------------");
    }

    public static void listarGrupos(Grupo[] grupos) {
        if (grupos == null || grupos[0].getTamano() == 0) {
            System.err.println("El grupo no existe");
        } else {
            for (int i = 0; i < grupos.length; i++) {
                int tmp = i + 1;
                System.out.println(("    " + tmp + ". " + grupos[i].getNombre() + " (" + grupos[i].getTamano()) + ")");
            }
        }
    }

    public static void listarPersonas(Persona[] personas) {
        for (int i = 0; i < personas.length; i++) {
            if (personas[i] == null) {
                System.out.println("No hay más personas");
            } else {
                int tmp = i + 1;
                System.out.println(tmp + ". " + personas[i].toString());
            }
        }
    }

    public static void listarPersonas(Persona[] personas, int idx) {
        for (int i = idx; i < personas.length; i++) {
            if (personas[i] == null) {
                System.out.println("La persona no existe");
            } else {
                System.out.println(idx + ". " + personas[i].toString());
            }
        }

    }

    public static void pintar(Persona[][] personas) {
        //TODO: pintar
    //GestorDeGrupos tmp = new GestorDeGrupos();
    //if (tmp.getPersonasSitios() == personas)
            for (int i = personas.length - 1; i >= 0; i--) {
                for (int j = 0; j < personas[0].length; j++) {
                    if (personas[i][j] == null){
                        System.out.print("    ");
                    }
                    else {
                    System.out.print(personas[i][j].getSiglas() + "  ");
                    }
                }
                System.out.println();
            }
        }
    }

