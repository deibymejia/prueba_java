package model;

import database.configDB;
import database.crud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.entitiesEstudiantes;

import javax.swing.*;


public class modelEstudiantes implements crud{
    @Override
    public boolean crear(Object obj) {
        Connection con = configDB.openConexion();
        entitiesEstudiantes objEstudiante = (entitiesEstudiantes) obj;
        boolean resultado = false;
        try{
            String sql = "INSERT INTO estudiante(id_estudiantes,nombre,apellido,email,estado,id_curso ) VAULES (?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,objEstudiante.id_estudiantes);
            ps.setString(1,objEstudiante.nombre);
            ps.setString(1,objEstudiante.apellido);
            ps.setString(1,objEstudiante.email);
            ps.setBoolean(1,objEstudiante.estado);
            ps.setInt(1,objEstudiante.id_curso);
            resultado = ps.executeUpdate() >0;
            if(resultado)
                JOptionPane.showMessageDialog(null,"estudiante creado");



        }catch (SQLException e){
            System.out.println("ERROR al crear estudiante: " + e.getMessage());

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
            String sql ="SELECT * FROM estudiantes;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rellenarEstudiante(list, rs);


        }catch (SQLException e){
            System.out.println("ERROR al listar estudiantes");
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
        entitiesEstudiantes objEstudiante = (entitiesEstudiantes) obj;
        boolean resultado = false;
        try{
            String sql ="UPDATE estudiantes SET nombre =? ,apellido = ?,email = ?, estado = ?,id_curso =? WHERE id_estudiantes = ?; ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,objEstudiante.getNombre());
            ps.setString(2,objEstudiante.getApellido());
            ps.setString(3,objEstudiante.getEmail());
            ps.setBoolean(4,objEstudiante.getEstado());
            ps.setInt(5,objEstudiante.getId_curso());
            resultado = ps.executeUpdate() > 0;
            if(resultado)
                JOptionPane.showMessageDialog(null,"se actualizo estudiante");
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
        entitiesEstudiantes objEstudiante = (entitiesEstudiantes) obj;
        boolean resultado = false;
        try{
            String sql = "DELETE FROM  estudiantes WHERE id_estudiantes = ?;";
            PreparedStatement ps  = con.prepareStatement(sql);
            ps.setInt(1,objEstudiante.getId_estudiantes());
            resultado = ps.executeUpdate()> 0;
            if(resultado)
                JOptionPane.showMessageDialog(null,"estudiante eliminado");
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
            String sql = "SELECT * FROM estudiantes WHERE "+ buscar+"= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            switch (campo){
                case "estudiantes.nombre","estudiantes.apellido","estudiantes.email","estudiantes.estado":
                    ps.setString(1,buscar);
                    break;
                case "estudiantes.id_estudiante","estudiantes.id_curso":
                    ps.setInt(1,Integer.parseInt(buscar));
                    break;
            }
            ResultSet rs = ps.executeQuery();
            rellenarEstudiante(filtrar,rs);

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
    private void rellenarEstudiante(List<Object>list,ResultSet rs){
        try{
            while (rs.next()){
                entitiesEstudiantes objEstudiante = new entitiesEstudiantes();
                objEstudiante.setId_estudiantes(rs.getInt("estudiantes.id_estudiantes"));
                objEstudiante.setNombre(rs.getString("estudiantes.nombre"));
                objEstudiante.setApellido(rs.getString("estudiantes.apellido"));
                objEstudiante.setEmail(rs.getString("estudiantes.email"));
                objEstudiante.setEstado(rs.getBoolean("estudiantes.estado"));
                objEstudiante.setId_curso(rs.getInt("estudiantes.id_curso"));
                list.add(objEstudiante);
            }
        }catch (SQLException e){
            System.out.println("ERROR al rellenar "+ e.getMessage());

        }
    }
}
