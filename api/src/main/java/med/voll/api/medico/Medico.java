package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.DatosDireccion;
import med.voll.api.direccion.Direccion;
//@Table para indicar a JPA el nombre de la tabla en la base de datos a la que estara vinculada
@Table(name = "medicos")
//@Entity para indicar que es una entidad de JPA y como llamarla
@Entity(name = "Medico")
@Getter
//con lombok genera automaticamente los getter
@NoArgsConstructor
// con lombok genera constructor sin argumentos
@AllArgsConstructor
// con lombok genera constructor con todos los argumentos
@EqualsAndHashCode(of="id")
// con lombok generar hashcode y equals
public class Medico {
    //@Id para indicar que es el id o llave primaria
    @Id
    //@GeneratedValue para indicar que sera un valor autogenerado con el tipo de estrategia IDENTITY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    // @Enumerated para indicar que se trabaja con un enum y seran datos de tipo String
    private Especialidades especialidad;
    @Embedded
    //Con esta anotación, indicamos que el campo o la propiedad de una entidad es una instancia de una clase que puede ser integrable. Es decir, para que funcione, el campo que hayamos anotado como @Embedded, debe corresponderse con una clase que tenga la anotación @Embeddable.
    private Direccion direccion;
    private boolean activo;

    public Medico(DatosRegistroMedicos datosRegistroMedicos)
    {   this.nombre=datosRegistroMedicos.nombre();
        this.documento=datosRegistroMedicos.documento();
        this.email=datosRegistroMedicos.email();
        this.telefono=datosRegistroMedicos.telefono();
        this.especialidad=datosRegistroMedicos.especialidad();
        this.direccion=new Direccion(datosRegistroMedicos.direccion());
        this.activo=true;
    }

    public void actualizarDatos(DatosActualizarMedicos datosActualizarMedicos) {
        if (datosActualizarMedicos.nombre()!=null){
        this.nombre=datosActualizarMedicos.nombre();}
        if (datosActualizarMedicos.documento()!=null){
        this.documento=datosActualizarMedicos.documento();}
        if (datosActualizarMedicos.direccion()!=null){
        this.direccion=direccion.actualizarDatos(datosActualizarMedicos.direccion());}
    }

    public void desactivarMedico() {
        this.activo=false;
    }
}
