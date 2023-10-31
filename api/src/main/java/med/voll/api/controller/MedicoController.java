package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosListadoMedicos;
import med.voll.api.medico.DatosRegistroMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    //@PostMapping indica que en la pagina /medicos cuando reciba un request de tipo POST se active este metodo y sirve para salvar datos

    public DatosRegistroMedicos registrarMedico(//@RequestBody indica que este parametro sera recibido en un request
            @RequestBody @Valid DatosRegistroMedicos datosRegistroMedico)
    {
        //System.out.println("El request llego correctamente");
        medicoRepository.save(new Medico(datosRegistroMedico));
return  datosRegistroMedico;
    }
@GetMapping
    public List<DatosListadoMedicos> listadoMedicos(){
         return medicoRepository.findAll().stream().map(DatosListadoMedicos::new).toList();
    }

}
