/**
 * Created by 45858000w on 14/11/16.
 */
public class Actores {
    private String Id;
    private String Nombre,FechaNacimiento,FechaMuerte,LugarNacimiento;

    public Actores() {
    }

    public Actores(String nombre, String fechaNacimiento, String fechaMuerte, String lugarNacimiento) {
        Nombre = nombre;
        FechaNacimiento = fechaNacimiento;
        FechaMuerte = fechaMuerte;
        LugarNacimiento = lugarNacimiento;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getFechaMuerte() {
        return FechaMuerte;
    }

    public void setFechaMuerte(String fechaMuerte) {
        FechaMuerte = fechaMuerte;
    }

    public String getLugarNacimiento() {
        return LugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        LugarNacimiento = lugarNacimiento;
    }
}
