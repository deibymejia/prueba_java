package entities;

public class entitiesCursos {
    public int id_course;
    public String name;
    public int student_quantity;
    //constructor vacio
    public entitiesCursos() {
    }
    //constructor

    public entitiesCursos(int id_curso, String nombre ,int cantidad_estudiante) {
        this.id_course = id_curso;
        this.name = nombre;
        this.student_quantity = cantidad_estudiante;

    }
    //get y set

    public int getId_curso() {
        return id_course;
    }

    public void setId_curso(int id_curso) {
        this.id_course = id_curso;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public int getCantidad_estudiante() {
        return student_quantity;
    }

    public void setCantidad_estudiante(int cantidad_estudiante) {
        this.student_quantity = cantidad_estudiante;
    }
    //toString

    @Override
    public String toString() {
        return "entitiesCursos{" +
                "id_curso=" + id_course +
                ", nombre='" + name + '\'' +
                ", cantidad_estudiante=" + student_quantity +
                '}';
    }

}
