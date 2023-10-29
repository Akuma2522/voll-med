package med.voll.api.controller;

import med.voll.api.medico.DatosRegistroMedicos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@RestController anotacion que indica a spring que es un controlador de tipo REST
@RestController
//@RequestMapping indica direccion tiene que seguir
@RequestMapping("/medicos")
public class MedicoController {
    //@PostMapping indica que en la pagina /medicos cuando reciba un request de tipo POST se active este metodo
    @PostMapping
    public DatosRegistroMedicos registrarMedico(//@RequestBody indica que este parametro sera recibido en un request
            @RequestBody DatosRegistroMedicos datosRegistroMedico)
    {
        //System.out.println("El request llego correctamente");
        System.out.println(datosRegistroMedico);
return  datosRegistroMedico;
    }
}
