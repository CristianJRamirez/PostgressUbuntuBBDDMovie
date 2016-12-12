/**
 * Created by 45858000w on 14/11/16.
 */
public class Peliculas {
    private String Id;
    private String Titulo;

    public Peliculas() {
    }

    public Peliculas(String titulo, String fechaEstreno) {
        Titulo = titulo;
        FechaEstreno = fechaEstreno;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getFechaEstreno() {
        return FechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        FechaEstreno = fechaEstreno;
    }

    private String FechaEstreno;


}
