package ni.edu.uam.veterinariaapi.repository;

import ni.edu.uam.veterinariaapi.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepo extends JpaRepository<Cliente, Long> {
}