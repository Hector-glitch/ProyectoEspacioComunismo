public class Empleado {
    private String nombre; // Solo se mantiene el nombre
    private int salari;
    private int edad;
    private String direccion;
    private String AnysExp;
    private String sexo;
    private String numeroDeTaller; // Añadido si también es parte del empleado

    // Constructor modificado
    public Empleado(String nombre, int salari, int edad, String direccion, String AnysExp, String sexo) {
        this.nombre = nombre;
        this.salari = salari;
        this.edad = edad;
        this.direccion = direccion;
        this.AnysExp = AnysExp;
        this.sexo = sexo;
    }
}
