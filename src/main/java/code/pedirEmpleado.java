package code;

import java.util.Scanner;

public class pedirEmpleado {
    public static void pedir(){
        boolean salir = false;
        do{
            System.out.println("Introduce nombre del empleado");
            System.out.println("Introduce sueldo del empleado");
            System.out.println("Introduce año de nacimiento del empleado");
            System.out.println("Introduce antigüedad del empleado");
        }while(!salir);
    }
}
