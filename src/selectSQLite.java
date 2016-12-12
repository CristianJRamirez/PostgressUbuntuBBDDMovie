
import java.sql.*;

/**
 * Created by dremon on 09/11/15.
 */
public class selectSQLite {


    public static void getDatos(int opcion,int id )
        {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://172.31.73.184:5432/jerry","cristian", "0510");
                c.setAutoCommit(false);
                System.out.println("Opened database SELECT successfully");

                stmt = c.createStatement();

                getOpion(opcion,stmt,id);

                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Operation done SELECT successfully");
        }

    private static ResultSet getResultSetPeliculas(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery( "SELECT * FROM Peliculas;" );
        while ( rs.next() ) {
            int id = rs.getInt("id");
            String  Titulo = rs.getString("Titulo");
            String  FechaEstreno = rs.getString("FechaEstreno");
            System.out.println( "ID = " + id );
            System.out.println( "Titulo = " + Titulo );
            System.out.println( "FechaEstreno = " + FechaEstreno );
            System.out.println();
        }
        return rs;
    }
    private static ResultSet getResultSetActores(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery( "SELECT * FROM Actores;" );
        while ( rs.next() ) {
            int id = rs.getInt("id");
            String  Titulo = rs.getString("Nombre");
            String  FechaNacimiento = rs.getString("FechaNacimiento");
            String  FechaMuerte = rs.getString("FechaMuerte");
            String  LugarNacimiento = rs.getString("LugarNacimiento");

            System.out.println( "ID = " + id );
            System.out.println( "Titulo = " + Titulo );
            System.out.println( "FechaNacimiento = " + FechaNacimiento );
            System.out.println( "FechaMuerte = " + FechaMuerte );
            System.out.println( "LugarNacimiento = " + LugarNacimiento );
            System.out.println();
        }
        return rs;
    }
    private static ResultSet getResultSetPersonajes(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery( "SELECT * FROM PeliculasActores;" );
        while ( rs.next() ) {
            int id = rs.getInt("id");
            String  IdPelicula = rs.getString("IdPelicula");
            String  IdActor = rs.getString("IdActor");
            String  Personaje = rs.getString("Personaje");

            System.out.println( "ID = " + id );
            System.out.println( "IdPelicula = " + IdPelicula );
            System.out.println( "IdActor = " + IdActor );
            System.out.println( "Personaje = " + Personaje );

            System.out.println();
        }
        return rs;
    }


    private static void getOpion(int i,Statement stmt,int id) throws SQLException {

        if (i==1) //peliculas
        {
            ResultSet rs = getResultSetPeliculas(stmt);
            rs.close();
        }
        else if (i==2) //actores
        {
            ResultSet rs = getResultSetActores(stmt);
            rs.close();
        }
        else if (i==3)//personajes
        {
            ResultSet rs = getResultSetPersonajes(stmt);
            rs.close();
        }
        else if (i==4)//Peliculas por id actor
        {
            ResultSet rs = getResultPeliculas(stmt,id);
            rs.close();
        }
        else if (i==5)//Actores por id pelicula
        {
            ResultSet rs = getResultActores(stmt,id);
            rs.close();
        }
        else //Global
        {
            ResultSet rs = getResultSetPeliculas(stmt);
            rs = getResultSetActores(stmt);
            rs = getResultSetPersonajes(stmt);
            rs.close();
        }

    }

    private static ResultSet getResultPeliculas(Statement stmt,int idactor) throws SQLException {
        ResultSet rs = stmt.executeQuery( "SELECT * FROM Peliculas WHERE ID IN (SELECT IdPelicula FROM PeliculasActores WHERE IdActor ="+idactor+");" );

        boolean existe = false;
        while ( rs.next() ) {
            existe=true;
            int id = rs.getInt("id");
            String  Titulo = rs.getString("Titulo");
            String  FechaEstreno = rs.getString("FechaEstreno");
            System.out.println( "ID = " + id );
            System.out.println( "Titulo = " + Titulo );
            System.out.println( "FechaEstreno = " + FechaEstreno );
            System.out.println();
        }

        if(!existe)
        {
            System.out.println("No tenemos la Pelicula con ese id del actor");
        }
        return rs;
    }
    private static ResultSet getResultActores(Statement stmt,int idpeliculas) throws SQLException {
        ResultSet rs = stmt.executeQuery( "SELECT * FROM Actores WHERE ID IN (SELECT IdActor FROM PeliculasActores WHERE IdPelicula ="+idpeliculas+");" );
        boolean existe = false;
        while ( rs.next() ) {
            existe=true;
            int id = rs.getInt("id");
            String  Titulo = rs.getString("Nombre");
            String  FechaNacimiento = rs.getString("FechaNacimiento");
            String  FechaMuerte = rs.getString("FechaMuerte");
            String  LugarNacimiento = rs.getString("LugarNacimiento");

            System.out.println( "ID = " + id );
            System.out.println( "Titulo = " + Titulo );
            System.out.println( "FechaNacimiento = " + FechaNacimiento );
            System.out.println( "FechaMuerte = " + FechaMuerte );
            System.out.println( "LugarNacimiento = " + LugarNacimiento );
            System.out.println();
        }

        if(!existe)
        {
            System.out.println("No tenemos la Pelicula con ese id del actor");
        }
        return rs;
    }
}


