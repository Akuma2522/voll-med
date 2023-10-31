package med.voll.api.controller;

import med.voll.api.medico.DatosRegistroMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@RestController anotacion que indica a spring que es un controlador de tipo REST
@RestController
//@RequestMapping indica direccion tiene que seguir
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    // @Autowired sirve para implentar automaticamente un objeto
    // no usar cuando se testea
    private MedicoRepository medicoRepository;
     @PostMapping
    //@PostMapping indica que en la pagina /medicos cuando reciba un request de tipo POST se active este metodo

    public DatosRegistroMedicos registrarMedico(//@RequestBody indica que este parametro sera recibido en un request
            @RequestBody DatosRegistroMedicos datosRegistroMedico)
    {
        //System.out.println("El request llego correctamente");
        medicoRepository.save(new Medico(datosRegistroMedico));
return  datosRegistroMedico;
    }
}
