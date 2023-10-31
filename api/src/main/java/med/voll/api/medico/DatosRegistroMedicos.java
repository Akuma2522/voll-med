package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;

//record sirve para generar un tipo de objeto inmutable que permite crear dinamicamente campos solo para llenarlos que en tiempo de compilacion genera getter y setter


public record DatosRegistroMedicos(
        // @NotBlank contiene @NotNull dentro y estas son validaciones que aseguran que no sea ni nulo ni blanco
        @NotBlank
        String nombre,
        @NotBlank
                @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
                @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotNull
        Especialidades especialidad,
        @NotNull
                @Valid
        DatosDireccion direccion) {
}
