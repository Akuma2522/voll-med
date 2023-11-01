package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
// los Page son parecidos a las listas pero ayudan con la paginacion y permiten elegir cantidad de terminos y que pagina ver
// a traves de los queryParams que se pondrian en el url
    public Page<DatosListadoMedicos> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion){
         //return medicoRepository.findAll(paginacion).map(DatosListadoMedicos::new);
    return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedicos::new);
    }
@PutMapping
// Para indicar que es un request para actualizar es decir tipo PUT
@Transactional
// Permite cerrar la transaccion con la base de datos, esto se hace en actualizar y eliminar. Sin esto los cambios no se reflejaran
    public void actualizarMedicos(@RequestBody @Valid DatosActualizarMedicos datosActualizarMedicos)
    {
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedicos.id());
        medico.actualizarDatos(datosActualizarMedicos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    // Metodo para borrar medico en base de datos, pero las reglas de negocio indican que no se deber borrar de esta

    /*public void eliminarMedicos(
            //@PathVariable indica que esta variable sera enviada no por el cuerpo sino por el url
            @PathVariable Long id
    )
    {
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }*/
    public void eliminarMedicos(@PathVariable Long id)
    {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
    }
}
