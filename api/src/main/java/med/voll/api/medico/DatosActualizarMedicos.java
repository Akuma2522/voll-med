package med.voll.api.medico;

import med.voll.api.direccion.DatosDireccion;

public record DatosActualizarMedicos(Long id, String nombre, String documento, DatosDireccion direccion) {
}
