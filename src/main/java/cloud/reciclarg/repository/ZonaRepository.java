package cloud.reciclarg.repository;

import cloud.reciclarg.model.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Long> {

    public Zona findByNombre(String nombre);
}
