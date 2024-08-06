package model;

import database.configDB;
import database.crud;
import entities.entitiesCalificaciones;
import entities.entitiesInscripciones;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class modelInscripciones implements crud {
    @Override
    public boolean crear(Object obj) {
        Connection con = configDB.openConexion();
        entitiesInscripciones objIncripciones = (entitiesInscripciones) obj;
        boolean resultado = false;
        try{
            String sql = "INSERT INTO inscripciones(id_curso,id_estudiante,id_inscripcion) VAULES (?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,objIncripciones.id_curso);
            ps.setInt(2,objIncripciones.id_estudiante);
            ps.setInt(3,objIncripciones.id_inscripcion);

            resultado = ps.executeUpdate() >0;
            if(resultado)
                JOptionPane.showMessageDialog(null,"inscripcion agregada");
        }catch (SQLException e){
            System.out.println("ERROR al crear inscripcion: " + e.getMessage());

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
            String sql ="SELECT * FROM inscripciones;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rellenarInscripciones(list, rs);

        }catch (SQLException e){
            System.out.println("ERROR al listar Inscripciones");
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
        entitiesInscripciones objInscripciones = (entitiesInscripciones) obj;
        boolean resultado = false;
        try{
            String sql ="UPDATE inscripciones SET id_curso =?,id_estudiante= ?,id_inscripcion =? WHERE id_inscripcion = ?; ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,objInscripciones.getId_curso());
            ps.setInt(2,objInscripciones.getId_estudiante());
            ps.setInt(2,objInscripciones.getId_inscripcion());
            resultado = ps.executeUpdate() > 0;
            if(resultado)
                JOptionPane.showMessageDialog(null,"se la inscripcion la nota");
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
        entitiesInscripciones objInscripciones = (entitiesInscripciones) obj;
        boolean resultado = false;
        try{
            String sql = "DELETE FROM inscripciones WHERE id_inscripcion = ?;";
            PreparedStatement ps  = con.prepareStatement(sql);
            ps.setInt(1,objInscripciones.getId_inscripcion());
            resultado = ps.executeUpdate()> 0;
            if(resultado)
                JOptionPane.showMessageDialog(null,"inscripcion eliminada");
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
            String sql = "SELECT * FROM inscripciones WHERE "+ buscar+"= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            switch (campo){
                case ",inscripcion.id_inscripcion":
                    ps.setInt(1,Integer.parseInt(buscar));
                    break;
            }
            ResultSet rs = ps.executeQuery();
            rellenarInscripciones(filtrar,rs);

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
    private void rellenarInscripciones(List<Object>list,ResultSet rs){
        try{
            while (rs.next()){
                entitiesInscripciones objInscripciones= new entitiesInscripciones();
                objInscripciones.setId_curso(rs.getInt("inscripciones.id_curso"));
                objInscripciones.setId_estudiante(rs.getInt("inscripciones.id_estudiante"));
                objInscripciones.setId_inscripcion(rs.getInt("incripciones.id_inscripciones"));
                list.add(objInscripciones);
            }
        }catch (SQLException e){
            System.out.println("ERROR al rellenar "+ e.getMessage());

        }
    }
}
