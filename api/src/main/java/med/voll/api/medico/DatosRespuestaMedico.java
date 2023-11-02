package med.voll.api.medico;

import med.voll.api.direccion.DatosDireccion;

public record DatosRespuestaMedico(Long id,
                                   String nombre,
                                   String documento,
                                   String email,
                                   String telefono,
                                   Especialidades especialidad,
                                   DatosDireccion direccion)
{
}
