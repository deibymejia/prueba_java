package entities;

public class entitiesCalificaciones {
    public int id_course;
    public int id_student;
    public int note;
    public String description_note;
    public int id_calificacion;

    //constructor vacio

    public entitiesCalificaciones() {
    }
    //constructor

    public entitiesCalificaciones(int id_curso,int id_estudiante,int nota,String descripcion_nota,int id_calificacion) {
        this.id_course = id_curso;
        this.id_student = id_estudiante;
        this.note = nota;
        this.description_note = descripcion_nota;
        this.id_calificacion =id_calificacion;
    }

    public int getId_curso() {
        return id_course;
    }

    public void setId_curso(int id_curso) {
        this.id_course = id_curso;
    }

    public int getId_estudiante() {
        return id_student;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_student = id_estudiante;
    }

    public int getNota() {
        return note;
    }

    public void setNota(int nota) {
        this.note = nota;
    }

    public String getDescripcion_nota() {
        return description_note;
    }

    public void setDescripcion_nota(String descripcion_nota) {
        this.description_note = descripcion_nota;
    }

    public int getId_calificacion() {
        return id_calificacion;
    }

    public void setId_calificacion(int id_calificacion) {
        this.id_calificacion = id_calificacion;
    }

    @Override
    public String toString() {
        return "entitiesCalificaciones{" +
                "id_curso=" + id_course +
                ", id_estudiante=" + id_student +
                ", nota=" + note +
                ", descripcion_nota='" + description_note + '\'' +
                ", id_calificacion=" + id_calificacion +
                '}';
    }
}
