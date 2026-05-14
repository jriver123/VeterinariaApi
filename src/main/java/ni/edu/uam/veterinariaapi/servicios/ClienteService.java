package ni.edu.uam.veterinariaapi.servicios;

import ni.edu.uam.veterinariaapi.modelos.Cliente;
import ni.edu.uam.veterinariaapi.repository.ClienteRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepo clienteRepo;

    public ClienteService(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    // Obtiene todos los clientes registrados en la base de datos.
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    // Busca un cliente por su ID y lanza un error si no existe.
    public Cliente buscarClientePorId(Long id) {
        return clienteRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    // Actualiza los datos principales de un cliente existente.
    public Cliente actualizarCliente(Long id, Cliente datosCliente) {
        Cliente cliente = buscarClientePorId(id);

        cliente.setNombre(datosCliente.getNombre());
        cliente.setApellido(datosCliente.getApellido());
        cliente.setCorreo(datosCliente.getCorreo());
        cliente.setTelefono(datosCliente.getTelefono());

        return clienteRepo.save(cliente);
    }

    public void eliminarCliente(Long id) {
        Cliente cliente = buscarClientePorId(id);
        clienteRepo.delete(cliente);
    }
}