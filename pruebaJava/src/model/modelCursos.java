package model;

import database.configDB;
import database.crud;
import entities.entitiesCursos;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class modelCursos implements crud {
    @Override
    public boolean crear(Object obj) {
        Connection con = configDB.openConexion();
        entitiesCursos objCursos = (entitiesCursos) obj;
        boolean resultado = false;
        try{
            String sql = "INSERT INTO cursos(id_cursos,nombre,cantidad_estudiantes) VAULES (?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,objCursos.id_course);
            ps.setString(2,objCursos.name);
            ps.setInt(3,objCursos.student_quantity);
            resultado = ps.executeUpdate() >0;
            if(resultado)
                JOptionPane.showMessageDialog(null,"curso creado");



        }catch (SQLException e){
            System.out.println("ERROR al crear Curso: " + e.getMessage());

        }
        finally {
            try{
                if(con != null){
                    con.close();
                    System.out.println("conecion cerrada");
                }
            }catch (SQLException e){
                System.out.println("ERROR al cerar conexion" + e.getMessage());
            }
        }

        return resultado;
    }

    @Override
    public List<Object> listar() {
        Connection con = configDB.openConexion();
        List<Object> list = new ArrayList<>();
        try{
            String sql ="SELECT * FROM cursos;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rellenarCursos(list, rs);


        }catch (SQLException e){
            System.out.println("ERROR al listar cursos ");
        }
        finally {
            try{
                if(con != null){
                    con.close();
                    System.out.println("conecion cerrada");
                }
            }catch (SQLException e){
                System.out.println("ERROR al cerar conexion" + e.getMessage());
            }
        }
        return list;
    }

    @Override
    public boolean actualizar(Object obj) {
        Connection con = configDB.openConexion();
        entitiesCursos objCursos = (entitiesCursos) obj;
        boolean resultado = false;
        try{
            String sql ="UPDATE cursos SET nombre =?,cantidad_estudiantes=? WHERE id_curso = ?; ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,objCursos.getNombre());
            ps.setInt(2,objCursos.getCantidad_estudiante());

            resultado = ps.executeUpdate() > 0;
            if(resultado)
                JOptionPane.showMessageDialog(null,"se actualizo el curso");
        }catch (SQLException e){
            System.out.println("ERROR al actualizar "+ e.getMessage());
        }
        finally {
            try{
                if(con != null){
                    con.close();
                    System.out.println("conecion cerrada");
                }
            }catch (SQLException e){
                System.out.println("ERROR al cerar conexion" + e.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public boolean eliminar(Object obj) {
        Connection con = configDB.openConexion();
        entitiesCursos objCursos = (entitiesCursos) obj;
        boolean resultado = false;
        try{
            String sql = "DELETE FROM cursos WHERE id_curso = ?;";
            PreparedStatement ps  = con.prepareStatement(sql);
            ps.setInt(1,objCursos.getId_curso());
            resultado = ps.executeUpdate()> 0;
            if(resultado)
                JOptionPane.showMessageDialog(null,"curso eliminado");
        }catch (SQLException e){
            System.out.println("ERROR al eliminar " + e.getMessage());
        }
        finally {
            try{
                if(con != null){
                    con.close();
                    System.out.println("conecion cerrada");
                }
            }catch (SQLException e){
                System.out.println("ERROR al cerar conexion" + e.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public List<Object> buscarId(String campo, String buscar) {
        Connection con = configDB.openConexion();
        List<Object> filtrar = new ArrayList<>();
        try{
            String sql = "SELECT * FROM cursos WHERE "+ buscar+"= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            switch (campo){
                case "curso.nombre":
                    ps.setString(1,buscar);
                    break;
                case "cursos.id_curso":
                    ps.setInt(1,Integer.parseInt(buscar));
                    break;
            }
            ResultSet rs = ps.executeQuery();
            rellenarCursos(filtrar,rs);

        }catch (SQLException e){
            System.out.println("ERROR al buscar " + e.getMessage());
        }
        finally {
            try{
                if(con != null){
                    con.close();
                    System.out.println("conecion cerrada");
                }
            }catch (SQLException e){
                System.out.println("ERROR al cerar conexion" + e.getMessage());
            }
        }


        return filtrar;
    }
    private void rellenarCursos(List<Object>list,ResultSet rs){
        try{
            while (rs.next()){
                entitiesCursos objCursos = new entitiesCursos();
                objCursos.setId_curso(rs.getInt("cursos.id_curso"));
                objCursos.setNombre(rs.getString("cursos.nombre"));
                objCursos.setCantidad_estudiante(rs.getInt("cursos.cantidad_estudiantes"));

                list.add(objCursos);
            }
        }catch (SQLException e){
            System.out.println("ERROR al rellenar "+ e.getMessage());

        }
    }

}
