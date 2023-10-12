package classes;

public class Empleado {
    public String nombre;
    public int sueldo;
    public int añoNacimiento;
    public int antiguedad;
    public Departamento departamento;

    public Empleado() {
    }

    public Empleado(String nombre, int sueldo, int añoNacimiento, int antiguedad) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.añoNacimiento = añoNacimiento;
        this.antiguedad = antiguedad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public int getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(int añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}
