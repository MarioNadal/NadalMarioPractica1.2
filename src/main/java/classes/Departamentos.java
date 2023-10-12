package classes;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "departamentos")
public class Departamentos {
    private List<Departamento> departamentos;
    @XmlElement(name = "departamento")
    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }


    public Departamentos() {
        this.departamentos = departamentos;
    }
}
