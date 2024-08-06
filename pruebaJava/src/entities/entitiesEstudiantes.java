package entities;

public class entitiesEstudiantes {
        public int id_estudiantes ;
        public String nombre;
        public String apellido;
        public String email;
        public Boolean estado;
        public int id_curso;
    //constructor vacio
    public entitiesEstudiantes() {
    }
    //constructor

    public entitiesEstudiantes(int id_estudiantes,String nombre,String apellido,String email,Boolean estado, int id_curso) {
        this.id_estudiantes = id_estudiantes;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.estado = estado;
        this.id_curso = id_curso;

    }
    //get y set

    public int getId_estudiantes() {
        return id_estudiantes;
    }

    public void setId_estudiantes(int id_estudiantes) {
        this.id_estudiantes = id_estudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getId_curso() {
        return id_curso;
    }


    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }
    //toString
    @Override
    public String toString() {
        return "entitiesEstudiantes{" +
                "id_estudiantes=" + id_estudiantes +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", estado=" + estado +
                ", id_curso=" + id_curso +
                '}';
    }
}
