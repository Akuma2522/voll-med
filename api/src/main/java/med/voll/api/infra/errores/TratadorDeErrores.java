package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    //@ExceptionHandler sirve para indicar que cuando se genere un tipo de excepcion llame este metodo
    // como parametro se le indica que tipo de excepcion tiene que ocurrir para llamar al metodo
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404()
    {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e)
    {
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new ).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error)
        {
         this(error.getField(),error.getDefaultMessage());
        }
    }
}
