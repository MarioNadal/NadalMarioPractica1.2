package classes;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Empleado {
    public String nombreEmpleado;
    public int sueldoEmpleado;
    public int añoNacimientoEmpleado;
    public String antiguedadEmpleado;
    public Departamento departamentoEmpleado;

    public Empleado() {
    }

    public Empleado(String nombreEmpleado, int sueldoEmpleado, int añoNacimiento, String antiguedad) {
        this.nombreEmpleado = nombreEmpleado;
        this.sueldoEmpleado = sueldoEmpleado;
        this.añoNacimientoEmpleado = añoNacimiento;
        this.antiguedadEmpleado = antiguedad;
    }

    public Departamento getDepartamento() {
        return departamentoEmpleado;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamentoEmpleado = departamento;
    }
    @XmlElement
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
    @XmlElement
    public int getSueldoEmpleado() {
        return sueldoEmpleado;
    }

    public void setSueldoEmpleado(int sueldoEmpleado) {
        this.sueldoEmpleado = sueldoEmpleado;
    }
    @XmlElement
    public int getAñoNacimiento() {
        return añoNacimientoEmpleado;
    }

    public void setAñoNacimiento(int añoNacimiento) {
        this.añoNacimientoEmpleado = añoNacimiento;
    }
    @XmlElement
    public String getAntiguedad() {
        return antiguedadEmpleado;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedadEmpleado = antiguedad;
    }
}
