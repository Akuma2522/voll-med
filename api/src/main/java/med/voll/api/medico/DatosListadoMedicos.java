package med.voll.api.medico;

public record DatosListadoMedicos(String nombre, Especialidades especialidad, String documento, String email) {
public DatosListadoMedicos(Medico medico)
{
    this(medico.getNombre(),medico.getEspecialidad(), medico.getDocumento(), medico.getEmail());
}
}
