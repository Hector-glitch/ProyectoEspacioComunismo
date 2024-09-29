public class Empleado {
    private String nombre;
    private int salari;
    private int edad;
    private String direccion;
    private String AnysExp;
    private String sexo;
    private boolean isAdmin; // Nuevo atributo para determinar si es administrador

    // Constructor modificado
    public Empleado(String nombre, int salari, int edad, String direccion, String AnysExp, String sexo, boolean isAdmin) {
        this.nombre = nombre;
        this.salari = salari;
        this.edad = edad;
        this.direccion = direccion;
        this.AnysExp = AnysExp;
        this.sexo = sexo;
        this.isAdmin = isAdmin;
    }

    // Getter para isAdmin
    public boolean isAdmin() {
        return isAdmin;
    }
}
