package model;

import database.configDB;
import database.crud;
import entities.entitiesCalificaciones;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class modelCalificaciones implements crud {
    @Override
    public boolean crear(Object obj) {
        Connection con = configDB.openConexion();
        entitiesCalificaciones objCalificaciones = (entitiesCalificaciones) obj;
        boolean resultado = false;
        try{
            String sql = "INSERT INTO calificaciones(id_curso,id_estudiante,nota,descripcion_nota) VAULES (?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,objCalificaciones.id_course);
            ps.setInt(2,objCalificaciones.id_student);
            ps.setInt(3,objCalificaciones.note);
            ps.setString(1,objCalificaciones.description_note);
            resultado = ps.executeUpdate() >0;
            if(resultado)
                JOptionPane.showMessageDialog(null,"nota agregada");
        }catch (SQLException e){
            System.out.println("ERROR al crear nota: " + e.getMessage());

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
            rellenarCalificaciones(list, rs);


        }catch (SQLException e){
            System.out.println("ERROR al listar calificaciones");
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
        entitiesCalificaciones objCalificaciones = (entitiesCalificaciones) obj;
        boolean resultado = false;
        try{
            String sql ="UPDATE calificaciones SET id_curso =?,id_estudiante= ?,nota =?,descripcion_nota =? WHERE id_estudiante = ?; ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,objCalificaciones.getId_curso());
            ps.setInt(2,objCalificaciones.getId_estudiante());
            ps.setInt(2,objCalificaciones.getNota());
            ps.setString(2,objCalificaciones.getDescripcion_nota());
            resultado = ps.executeUpdate() > 0;
            if(resultado)
                JOptionPane.showMessageDialog(null,"se actualizo la nota");
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
        entitiesCalificaciones objCalificaciones = (entitiesCalificaciones) obj;
        boolean resultado = false;
        try{
            String sql = "DELETE FROM calificaciones WHERE id_calificacion = ?;";
            PreparedStatement ps  = con.prepareStatement(sql);
            ps.setInt(1,objCalificaciones.getId_calificacion());
            resultado = ps.executeUpdate()> 0;
            if(resultado)
                JOptionPane.showMessageDialog(null,"nota eliminada");
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
            String sql = "SELECT * FROM calificaciones WHERE "+ buscar+"= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            switch (campo){
                case "calificaciones.descripcion_nota":
                    ps.setString(1,buscar);
                    break;
                case ",calificaciones.id_estudiantes,calificaciones.id_calificacion":
                    ps.setInt(1,Integer.parseInt(buscar));
                    ps.setInt(2,Integer.parseInt(buscar));
                    break;
            }
            ResultSet rs = ps.executeQuery();
            rellenarCalificaciones(filtrar,rs);

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
    private void rellenarCalificaciones(List<Object>list,ResultSet rs){
        try{
            while (rs.next()){
                entitiesCalificaciones objCalificaciones= new entitiesCalificaciones();
                objCalificaciones.setId_curso(rs.getInt("calificaciones.id_curso"));
                objCalificaciones.setId_estudiante(rs.getInt("calificaciones.id_estudiante"));
                objCalificaciones.setNota(rs.getInt("calificaciones.nota"));
                objCalificaciones.setDescripcion_nota(rs.getString("calificaciones.descripcion_nota"));
                objCalificaciones.setId_calificacion(rs.getInt("calificaciones.id_calificacion"));
                list.add(objCalificaciones);
            }
        }catch (SQLException e){
            System.out.println("ERROR al rellenar "+ e.getMessage());

        }
    }

}
