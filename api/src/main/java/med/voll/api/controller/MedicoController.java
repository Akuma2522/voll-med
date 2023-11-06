package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
    // el ResponseEntity tambien admite un generico para indicar que tipo de respuesta tiene que dar en este caso DatosRespuestaMedico
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(//@RequestBody indica que este parametro sera recibido en un request
                                                                @RequestBody @Valid DatosRegistroMedicos datosRegistroMedico,
                                                                // sirve para poder enviar un url generalizado
                                                                UriComponentsBuilder uriComponentsBuilder) {
        //System.out.println("El request llego correctamente");
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(),
                medico.getDocumento(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()));
        // con este URI puedo enviar el url que se usa actualmente de manera dinamica y concatenar el id para indicar que id de medico
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);

        //return 201 created
        // URL donde encontrar medico
        //GET http://localhost:8080/medicos/xx
    }

    @GetMapping
    // los Page son parecidos a las listas pero ayudan con la paginacion y permiten elegir cantidad de terminos y que pagina ver
    // a traves de los queryParams que se pondrian en el url

    public ResponseEntity<Page<DatosListadoMedicos>> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion) {
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedicos::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedicos::new));
    }

    @PutMapping
// Para indicar que es un request para actualizar es decir tipo PUT
    @Transactional
// Permite cerrar la transaccion con la base de datos, esto se hace en actualizar y eliminar. Sin esto los cambios no se reflejaran
    public ResponseEntity actualizarMedicos(@RequestBody @Valid DatosActualizarMedicos datosActualizarMedicos) {
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedicos.id());
        medico.actualizarDatos(datosActualizarMedicos);
        return ResponseEntity.ok(new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getDocumento(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento())));
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
    // Al cambiar el metodo de void a ResponseEntity podemos devolver a la pagina el tipo de respuesta que dará, ya 404 not found , 200 ok u otros.(204 es no content que es el que usaremos)
    public ResponseEntity eliminarMedicos(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico = new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getDocumento(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosMedico);
    }
}
