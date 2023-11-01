package med.voll.api.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
// para indicar que se usara en otra entidad sin necesariamente ser una tabla
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String calle;
    private String distrito;
    private String ciudad;
    private String numero;
    private String complemento;

    public Direccion(DatosDireccion direccion) {
        this.calle=direccion.calle();
        this.ciudad=direccion.ciudad();
        this.complemento=direccion.complemento();
        this.distrito=direccion.distrito();
        this.numero=direccion.numero();
    }


    public Direccion actualizarDatos(DatosDireccion direccion) {
        this.calle=direccion.calle();
        this.ciudad=direccion.ciudad();
        this.complemento=direccion.complemento();
        this.distrito=direccion.distrito();
        this.numero=direccion.numero();
        return this;
    }
}
