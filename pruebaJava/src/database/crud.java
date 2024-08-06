package database;

import java.util.List;

public interface crud {
    public boolean crear (Object obj);
    public List<Object> listar();
    public boolean actualizar (Object obj);
    public boolean eliminar (Object obj);
    public List<Object>buscarId(String campo,String buscar);

}
