/**
 * Created by 45858000w on 14/11/16.
 */
public class PeliculasActores {
    private String ID,IdPelicula,IdActor;
    private String Personaje;

    public PeliculasActores() {
    }

    public PeliculasActores(String idPelicula, String idActor, String personaje) {
        IdPelicula = idPelicula;
        IdActor = idActor;
        Personaje = personaje;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIdPelicula() {
        return IdPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        IdPelicula = idPelicula;
    }

    public String getIdActor() {
        return IdActor;
    }

    public void setIdActor(String idActor) {
        IdActor = idActor;
    }

    public String getPersonaje() {
        return Personaje;
    }

    public void setPersonaje(String personaje) {
        Personaje = personaje;
    }
}
