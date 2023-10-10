import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class main {
    public static void main (String[] args){
        String guiones = "-".repeat(20);
        int menu;
        boolean salir = false;
        do{
            System.out.println(guiones);
            System.out.println("0.Salir");
            menu = libs.Leer.introduceEntero("Introduce el número del menú: ");
            System.out.println(guiones);

            switch(menu){
                case 1 -> code.pedirEmpleado.pedir();
                case 0 -> salir = true;
            }
        }while(!salir);
    }
}
