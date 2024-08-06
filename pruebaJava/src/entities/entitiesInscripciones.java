package entities;

public class entitiesInscripciones {
    public int id_curso;
    public int id_estudiante;
    public int id_inscripcion;

    //constructor vacio

    public entitiesInscripciones() {
    }
    //constructor

    public entitiesInscripciones(int id_curso,int id_estudiante,int id_inscripcion) {
        this.id_curso = id_curso;
        this.id_estudiante = id_estudiante;
        this.id_inscripcion = id_inscripcion;
    }
    //get y set

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }
    //toString

    @Override
    public String toString() {
        return "entitiesInscripciones{" +
                "id_curso=" + id_curso +
                ", id_estudiante=" + id_estudiante +
                '}';
    }
}
