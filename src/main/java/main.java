import classes.Departamentos;
import classes.Empleado;
import classes.Empresa;
import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import libs.Leer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class main {
    public static void main (String[] args){
        List<Empleado> empleados = new ArrayList<>();
        Departamentos departamentos = leerDepartamentos();
        String guiones = "-".repeat(20);
        int menu;
        boolean salir = false;
        do {
            System.out.println(guiones);
            System.out.println("1. Pedir Empleados");
            System.out.println("2. Leer Departamentos");
            System.out.println("3. Asignar cada empleado Departamento");
            System.out.println("4. Leer JSON");
            System.out.println("5. Toda la información de la empresa XML o JSON");
            System.out.println("0. Salir");
            System.out.println(guiones);
            menu = libs.Leer.introduceEntero("Introduce el número del menú: ");
            System.out.println(guiones);

            switch (menu) {
                case 5 -> todaLaInformacionEmpresa(empleados, departamentos);
                case 4 -> leerNuevosEmpleadosJSON();
                case 3 -> asignarCadaEmpleadoDepartamento(empleados, departamentos);
                case 2 -> departamentos = leerDepartamentos();
                case 1 -> pedirEmpleados(empleados);
                case 0-> salir = true;
                default -> System.out.println("Ese número no esta en el menú, introduzca un número del menu.");
            }
        } while (!salir);
    }

    private static void todaLaInformacionEmpresa(List<Empleado> empleados, Departamentos departamentos) {
        String guiones = "-".repeat(20);
        int menu;
        boolean salir = false;
        do {
            System.out.println(guiones);
            System.out.println("1. Toda la información de la empresa en XML");
            System.out.println("2. Toda la información de la empresa en JSON");
            System.out.println("0. Salir");
            System.out.println(guiones);
            menu = Leer.introduceEntero("Introduzca número del tipo de archivo de la información de la empresa que desea recibir");
         switch (menu){
             case 1 -> XMLInformacionEmpresa(empleados,departamentos);
             case 2 -> JSONInformacionEmpresa(empleados,departamentos);
             case 0 -> salir = true;
             default -> System.out.println("Ese número no esta en el menú, introduzca un número del menu.");
         }
        }while(!salir);
    }

    private static void JSONInformacionEmpresa(List<Empleado> empleados, Departamentos departamentos) {

    }

    private static void XMLInformacionEmpresa(List<Empleado> empleados, Departamentos departamentos) {

    }

    private static void leerNuevosEmpleadosJSON() {
        SimpleDateFormat formateo= new SimpleDateFormat("yyyyMMdd'_'HH-mm-ss");
        Date fecha =new Date(System.currentTimeMillis());
        String fechaBuena = formateo.format(fecha);
        Path p = Path.of("src/main/resources/nuevosEmpleados.json");
        //varaible para almacenar el contenido del fichero
        Empleado[] empleadoJson;
        //leemos el contenido del Json, que es un texto
        String txtJson;
        //leempos el contenido del archivo de texto
        try{
            if(libs.CheckFiles.ficheroReadable(p)){
                txtJson = Files.readString(p);
                //creo el Gson que transforma de texto a objeto
                Gson gson = new Gson();
                empleadoJson = gson.fromJson(txtJson,Empleado[].class);
                for(Empleado empleado: empleadoJson){
                    empleado.setAntiguedad(fechaBuena);
                    System.out.println(empleado.getAntiguedad());
                }
            }else{
                System.out.println("El fichero no se puede leer");
            }
        }catch(IOException ex){
            System.out.println("El fichero no se ha podido crear");
        }
    }

    private static void asignarCadaEmpleadoDepartamento(List<Empleado> empleados, Departamentos departamentos) {
        boolean salir = false;
        for(int i = 0;i<empleados.size();i++){
            do{
                System.out.println("Lista de departamentos: ");
                for(int y=1;y<=departamentos.getDepartamentos().size();y++){
                    System.out.println("\t"+y +"."+departamentos.getDepartamentos().get(y-1).getNombre());
                }
                int departamentoElegido = libs.Leer.introduceEntero("Introduce el departamento que quieres para el empleado: " + empleados.get(i).getNombreEmpleado());
                if(departamentoElegido<=departamentos.getDepartamentos().size()){
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
        Path p = Path.of("src/main/resources/empleados.csv");
        boolean salir = true;
        String nombre, antiguedad;
        int sueldo, añoNacimiento;
        do {
            nombre = libs.Leer.introduceString("Introduce nombre del empleado");
            sueldo = libs.Leer.introduceEntero("Introduce sueldo del empleado");
            añoNacimiento = libs.Leer.introduceEntero("Introduce año de nacimiento del empleado");
            antiguedad = libs.Leer.introduceString("Introduce antigüedad del empleado");
            Empleado empleado = new Empleado(nombre, sueldo, añoNacimiento, antiguedad);
            empleados.add(empleado);
            if(libs.CheckFiles.ficheroEscribible(p)){
                try(FileWriter writer = new FileWriter(p.toFile())){
                    for(Empleado empleadoCSV : empleados){
                        String linea = empleadoCSV.getNombreEmpleado()+";"+empleadoCSV.getSueldoEmpleado()+";"+
                                empleadoCSV.getAñoNacimiento()+";"+empleadoCSV.getAntiguedad();
                        writer.write(linea + "\n");
                    }
                    System.out.println("Escritura del empleado correcta");
                }catch(IOException e){
                    System.out.println("Error en la escritura del csv");
                }
            }else{
                System.out.println("No es posible escribir en este fichero");
            }
            salir = libs.Leer.introduceBoolean("Introduce true si quiere seguir añadiendo empleados, de lo contrario introduzca false");
        } while (salir);
    }
}
