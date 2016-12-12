/**
 * Created by dremon on 09/11/15.
 */

import java.sql.*;
import java.util.ArrayList;

public class insertSQLite {

    public static void Insert(String[] args) {
        {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://172.31.73.184:5432/jerry","cristian", "0510");
                c.setAutoCommit(false);
                System.out.println("Opened database successfully");

                stmt = c.createStatement();
                String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                        "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                        "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                        "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                        "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
                stmt.executeUpdate(sql);

                stmt.close();
                c.commit();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Records created successfully");
        }
    }

    public static void insertDatosActores(ArrayList<Actores> a) {
        {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://172.31.73.184:5432/jerry","cristian", "0510");
                String sql_insert= "INSERT INTO ACTORES" +
                        "(ID,Nombre,FechaNacimiento,FechaMuerte,LugarNacimiento) VALUES" +
                        " (?,?,?,?,?);";
                for (Actores act: a) {


                    PreparedStatement preparedStatement = c.prepareStatement(sql_insert);

                    preparedStatement.setString(1, act.getId());
                    preparedStatement.setString(2, act.getNombre());
                    preparedStatement.setString(3, act.getFechaNacimiento());
                    preparedStatement.setString(4, act.getFechaMuerte());
                    preparedStatement.setString(5, act.getLugarNacimiento());

                    preparedStatement.executeUpdate();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Records created Actores successfully");
    }

    public static void insertDatosPeliculas(ArrayList<Peliculas> p) {
        {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://172.31.73.184:5432/jerry","cristian", "0510");
                String sql_insert= "INSERT INTO Peliculas" +
                        "(ID,Titulo, FechaEstreno) VALUES" +
                        " (?,?,?);";

                for (Peliculas peli: p) {

                    PreparedStatement preparedStatement = c.prepareStatement(sql_insert);

                    preparedStatement.setString(1, peli.getId());
                    preparedStatement.setString(2, peli.getTitulo());
                    preparedStatement.setString(3, peli.getFechaEstreno());

                    preparedStatement.executeUpdate();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Records created Pêliculas successfully");
    }

    public static void insertDatosPeliculasActores(ArrayList<PeliculasActores> pa) {
        {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://172.31.73.184:5432/jerry","cristian", "0510");
                String sql_insert= "INSERT INTO PeliculasActores" +
                        "(ID,IdPelicula, IdActor,Personaje) VALUES" +
                        " (?,?,?,?);";
                for (PeliculasActores peliActor: pa) {

                    PreparedStatement preparedStatement = c.prepareStatement(sql_insert);

                    preparedStatement.setString(1, peliActor.getID());
                    preparedStatement.setString(2, peliActor.getIdPelicula());
                    preparedStatement.setString(3, peliActor.getIdActor());
                    preparedStatement.setString(4, peliActor.getPersonaje());

                    preparedStatement.executeUpdate();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Records created PêliculasActores successfully");
    }

    /*
     c.setAutoCommit(false);
                System.out.println("Opened database Actores successfully");

                stmt = c.createStatement();
                for (int i = 0; i < a.size(); i++) {


                    String sql = "insert into Actores (ID,Nombre, FechaNacimiento,FechaMuerte,LugarNacimiento) " +
                            "values (" + a.get(i).getId() + "," + a.get(i).getNombre() + ", " + a.get(i).getFechaNacimiento() + ", " + a.get(i).getFechaMuerte() + ", " + a.get(i).getLugarNacimiento() + ");";
                    stmt.executeUpdate(sql);
                }
                stmt.close();
                c.commit();
                c.close();
     */
}
