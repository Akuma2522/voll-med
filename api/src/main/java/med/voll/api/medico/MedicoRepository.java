package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;
// al extender de JpaRepository hago que se vuelva un repositorio de la clase que ponga en el generic y tmbn poner el tipo de variable del ID
public interface MedicoRepository extends JpaRepository<Medico,Long> {
}
