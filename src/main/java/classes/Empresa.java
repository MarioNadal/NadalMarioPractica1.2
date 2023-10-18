package classes;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class Empresa {
    private Departamentos departamentos;
    private List<Empleado> empleados;

    public Empresa() {
    }

    public Empresa(List<Empleado> empleados, Departamentos departamentos) {
        this.empleados = empleados;
        this.departamentos =  departamentos;
    }

    @XmlElement
    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }
    @XmlElement
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}
