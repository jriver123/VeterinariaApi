package ni.edu.uam.veterinariaapi.servicios;

import ni.edu.uam.veterinariaapi.modelos.Cliente;
import ni.edu.uam.veterinariaapi.modelos.Mascota;
import ni.edu.uam.veterinariaapi.repository.ClienteRepo;
import ni.edu.uam.veterinariaapi.repository.MascotaRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepo mascotaRepo;
    private final ClienteRepo clienteRepo;

    public MascotaService(MascotaRepo mascotaRepo, ClienteRepo clienteRepo) {
        this.mascotaRepo = mascotaRepo;
        this.clienteRepo = clienteRepo;
    }

    // Lista todas las mascotas almacenadas en la base de datos.
    public List<Mascota> listarMascotas() {
        return mascotaRepo.findAll();
    }

    // Busca una mascota por su ID y valida que exista.
    public Mascota buscarMascotaPorId(Long id) {
        return mascotaRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mascota no encontrada"));
    }

    // Guarda una mascota asociándola primero con un cliente existente.
    public Mascota guardarMascota(Mascota mascota) {
        if (mascota.getCliente() == null || mascota.getCliente().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe enviar el id del cliente");
        }

        Cliente cliente = clienteRepo.findById(mascota.getCliente().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        mascota.setCliente(cliente);

        return mascotaRepo.save(mascota);
    }

    // Actualiza los datos de la mascota y permite cambiar el cliente asociado.
    public Mascota actualizarMascota(Long id, Mascota datosMascota) {
        Mascota mascota = buscarMascotaPorId(id);

        mascota.setNombre(datosMascota.getNombre());
        mascota.setEspecie(datosMascota.getEspecie());
        mascota.setRaza(datosMascota.getRaza());
        mascota.setEdad(datosMascota.getEdad());

        if (datosMascota.getCliente() != null && datosMascota.getCliente().getId() != null) {
            Cliente cliente = clienteRepo.findById(datosMascota.getCliente().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

            mascota.setCliente(cliente);
        }

        return mascotaRepo.save(mascota);
    }

    public void eliminarMascota(Long id) {
        Mascota mascota = buscarMascotaPorId(id);
        mascotaRepo.delete(mascota);
    }
}