import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by dremon on 09/11/15.
 */
public class themovieDBproject {

    public String api="https://api.themoviedb.org/3/movie/[nºpelicula]?api_key=d107750ca8567c4a1c171628d0a113d5";
    public String apiReadAccesToken="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMTA3NzUwY2E4NTY3YzRhMWMxNzE2MjhkMGExMTNkNSIsInN1YiI6IjU4MjlkMjFiOTI1MTQxN2IyOTAzOTJhMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.gzlNlLCxOYMj0f_DHaGa1dkvyiGayoheu7Mg82-J9d0";
    public String salida = "";
    public String api_key = "d107750ca8567c4a1c171628d0a113d5";

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public  void getPeliculas(ArrayList<Peliculas> pelis){
        //peliculas datos
    //https://developers.themoviedb.org/3/movies/get-movie-details

        int numeropelis=0;
        int Pelis=0;
        do{

            String film = String.valueOf(Pelis);
            String peticio = "https://api.themoviedb.org/3/movie/"+film+"?api_key="+api_key;
            //System.out.println(peticio);
            try {
                salida = getHTML(peticio);
                SJSPeliculas(salida,pelis);
                numeropelis++;
            } catch (Exception e) {
                //System.out.println("La peli "+film+" no existeix, contador pelis = "+numeropelis);
            }
            Pelis++;
        }while(numeropelis<300);


    }

    public static void SJSPeliculas(String cadena, ArrayList<Peliculas> pelis){

        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
       /* System.out.print("PELICULA -> ");
        System.out.print(arra02.get("original_title")+" --- ");//titulo pelicula
        System.out.print(arra02.get("release_date")+" --- ");//fecha de estreno
        System.out.println(arra02.get("id"));//id pelicula*/

        Peliculas p = new Peliculas();
        p.setId(arra02.get("id").toString());
        p.setTitulo(arra02.get("original_title").toString());
        p.setFechaEstreno(arra02.get("release_date").toString());
        pelis.add(p);
        //insertSQLite.insertDatosPeliculas(pelis);
    }

    public  void getActores(ArrayList<Actores> actores){
        //para conseguir actores
//https:api.themoviedb.org/3/person/{person_id}?api_key=d107750ca8567c4a1c171628d0a113d5&language=en-US&append_to_response=undefined
        //https://developers.themoviedb.org/3/people/get-person-details
        int numeroActores=0;
        int Actores=600;
        do {

            String actor = String.valueOf(Actores);
            String peticio = "https://api.themoviedb.org/3/person/"+actor+"?api_key=d107750ca8567c4a1c171628d0a113d5";
            try {
                salida = getHTML(peticio);
                SJSActores(salida,actores);
                numeroActores++;
            } catch (Exception e) {
                System.out.println(e);
                //System.out.println("El actor "+actor+" no existeix, contador actores = "+numeroActores);

            }
            Actores++;
        }while(numeroActores<300);
    }

    public static void SJSActores(String cadena, ArrayList<Actores> a){

        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
       /* System.out.print("ACTORES -> ");
        System.out.print(arra02.get("name")+" --- ");
        System.out.print(arra02.get("birthday")+" --- ");
        System.out.print(arra02.get("deathday")+" --- ");
        System.out.println(arra02.get("birthday"));*/


       Actores actor = new Actores();
        actor.setId(arra02.get("id").toString());
        actor.setNombre(arra02.get("name").toString());
        actor.setLugarNacimiento(arra02.get("place_of_birth").toString());
        actor.setFechaNacimiento(arra02.get("birthday").toString());
        actor.setFechaMuerte(arra02.get("deathday").toString());
        a.add(actor);

    }



    public void getPeliculasActores(ArrayList<PeliculasActores> pa) {
        //para conseguir la vinculacion de actores y peliculas
//https://api.themoviedb.org/3/movie/nºpelicula/credits?api_key=d107750ca8567c4a1c171628d0a113d5
        //https://developers.themoviedb.org/3/movies/get-movie-credits
        int numeroPA=0;
        int PeliActo=0;
        do{
            String peliActores = String.valueOf(PeliActo);
            String peticio = "https://api.themoviedb.org/3/movie/"+peliActores+"/credits?api_key="+api_key;
            try {
                salida = getHTML(peticio);
                SJSPeliculasActores(salida,pa);
                numeroPA++;
            } catch (Exception e) {
                System.out.println(e);
                //System.out.println("La peliActor "+peliActores+" no existeix, contador Personajes = "+numeroPA);
            }
            PeliActo++;
        }while(numeroPA<300);
    }

    private void SJSPeliculasActores(String salida, ArrayList<PeliculasActores> pa) {
        PeliculasActores peliAct= new PeliculasActores();

        Object obj02 =JSONValue.parse(salida);
        JSONObject arra02=(JSONObject)obj02;

        JSONArray arra03 = (JSONArray)arra02.get("cast");
        //System.out.println("PELICULAS -ACTORES -->");
        for (int i = 0; i < arra03.size(); i++) {

            JSONObject jb= (JSONObject)arra03.get(i);
           // System.out.print(arra02.get("id")+"<-->");
           // System.out.println(jb.get("character")+"<-->"+jb.get("name")+"<-->"+jb.get("id"));

            peliAct.setID(jb.get("cast_id").toString());
            peliAct.setIdPelicula(arra02.get("id").toString());//idpelicula
            peliAct.setIdActor(jb.get("id").toString());//id actor
            peliAct.setPersonaje(jb.get("character").toString());//personaje
         //   System.out.println("PERSONAJE ----> "+peliAct.getID()+"<-->"+peliAct.getIdPelicula()+"<-->"+peliAct.getIdActor()+"<-->"+peliAct.getPersonaje());
            pa.add(peliAct);
        }

/*
        Peliculas p = new Peliculas();
        p.setId(arra02.get("id").toString());
        p.setTitulo(arra02.get("original_title").toString());
        p.setFechaEstreno(arra02.get("release_date").toString());
        pelis.add(p);
        insertSQLite.insertDatosPeliculas(pelis);*/
    }

    //complejos
    public static void SJC (String cadena, ArrayList<Actores> a){
        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
        JSONArray arra03 = (JSONArray)arra02.get("cast");

        for (int i = 0; i < arra03.size(); i++) {
            JSONObject jb= (JSONObject)arra03.get(i);
            System.out.println(jb.get("character")+"<-->"+jb.get("name"));

        }
    }
}
