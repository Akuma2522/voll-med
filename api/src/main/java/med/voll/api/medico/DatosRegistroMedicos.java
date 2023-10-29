package med.voll.api.medico;

import med.voll.api.direccion.DatosDireccion;

//record sirve para generar un tipo de objeto inmutable que permite crear dinamicamente campos solo para llenarlos que en tiempo de compilacion genera getter y setter


public record DatosRegistroMedicos(String nombre, String email, String documento, Especialidades especialidad, DatosDireccion direccion) {
}
