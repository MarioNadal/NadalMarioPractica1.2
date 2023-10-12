import classes.Departamentos;
import classes.Empleado;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main (String[] args){
        List<Empleado> empleados = new ArrayList<>();
        Departamentos departamentos = leerDepartamentos();
        pedirEmpleados(empleados);
        asignarCadaEmpleadoDepartamento(empleados, departamentos);
    }

    private static void asignarCadaEmpleadoDepartamento(List<Empleado> empleados, Departamentos departamentos) {
        boolean salir = false;
        for(int i = 0;i<empleados.size();i++){
            do{
                System.out.println("Lista de departamentos: ");
                for(int y=1;y<=departamentos.getDepartamentos().size();y++){
                    System.out.println("\t"+y +"."+departamentos.getDepartamentos().get(y-1).getNombre());
                }
                int departamentoElegido = libs.Leer.introduceEntero("Introduce el departamento que quieres para el empleado: " + empleados.get(i).getNombre());
                if(departamentoElegido<departamentos.getDepartamentos().size()){
                    empleados.get(i).setDepartamento(departamentos.getDepartamentos().get(departamentoElegido-1));
                    salir = true;
                }else{
                    System.out.println("El número introducido no corresponde con un departamento");
                }
            }while(!salir);
        }
    }

    private static Departamentos leerDepartamentos() {
        Departamentos departamentos = new Departamentos();
        //Leeremos el archivo departamentos.xml con JAXB para pasarlo a una clase
        //Archivo a leer
        Path p = Path.of("src/main/resources/departamentos.xml");
        if(libs.CheckFiles.ficheroReadable(p)){
            //Creo las variables donde almacenar en mi código la información
            //Un contexto se utiliza cuando en nuestro código hemos cargado datos persistentes
            JAXBContext contexto = null;
            try{
                contexto = JAXBContext.newInstance(Departamentos.class);
                //Para pasar el codigo de XML -- Unmarshaller
                Unmarshaller unmarshaller = contexto.createUnmarshaller();
                departamentos = (Departamentos) unmarshaller.unmarshal(p.toFile());
            }catch(JAXBException ex){
                System.out.println("El javaBean no sirve para este XML.");
            }
        }
        return departamentos;
    }

    private static void pedirEmpleados(List<Empleado> empleados) {
        boolean salir = true;
        String nombre;
        int sueldo, añoNacimiento, antiguedad;
        do {
            nombre = libs.Leer.introduceString("Introduce nombre del empleado");
            sueldo = libs.Leer.introduceEntero("Introduce sueldo del empleado");
            añoNacimiento = libs.Leer.introduceEntero("Introduce año de nacimiento del empleado");
            antiguedad = libs.Leer.introduceEntero("Introduce antigüedad del empleado");
            Empleado empleado = new Empleado(nombre, sueldo, añoNacimiento, antiguedad);
            empleados.add(empleado);
            salir = libs.Leer.introduceBoolean("Introduce true si quiere seguir añadiendo empleados, de lo contrario introduzca false");
        } while (salir);
    }
}
