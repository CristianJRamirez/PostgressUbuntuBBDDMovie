/**
 * Created by dremon on 09/11/15.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class createSQLite {


    public  void createTablas() {
        //Connection -> para gestionar connexiones
        Connection c = null;

        //Statement -> fromata instrucciones sql
        Statement stmt = null;
        try {

            //driver -> Ctrl + Alt + shift + S -> añadir la libreria
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://172.31.73.184:5432/jerry","cristian", "0510");
            System.out.println("Opened database Crear Tablas successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE if not exists Peliculas " +
                    "(ID VARCHAR(7) PRIMARY KEY     NOT NULL," +
                    " Titulo           TEXT    NOT NULL, " +
                    " FechaEstreno            VARCHAR(50))";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE if not exists Actores " +
                    "(ID VARCHAR(7) PRIMARY KEY     NOT NULL," +
                    " Nombre           TEXT    NOT NULL, " +
                    " FechaNacimiento            VARCHAR(50), " +
                    " FechaMuerte        VARCHAR(50), " +
                    " LugarNacimiento         VARCHAR(300))";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE if not exists PeliculasActores " +
                    "(ID VARCHAR(7)      NOT NULL," +
                    " IdPelicula           VARCHAR(7) NOT NULL, " +
                    " IdActor            VARCHAR(7) NOT NULL, " +
                    " Personaje        VARCHAR(150));";//"," +
                    //" PRIMARY KEY (IdPelicula, IdActor))";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

}



