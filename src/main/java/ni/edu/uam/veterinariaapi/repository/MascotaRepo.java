package ni.edu.uam.veterinariaapi.repository;

import ni.edu.uam.veterinariaapi.modelos.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepo extends JpaRepository<Mascota, Long> {
}