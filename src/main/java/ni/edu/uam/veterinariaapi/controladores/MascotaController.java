package ni.edu.uam.veterinariaapi.controladores;

import ni.edu.uam.veterinariaapi.modelos.Mascota;
import ni.edu.uam.veterinariaapi.servicios.MascotaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    // Endpoint para consultar todas las mascotas registradas.
    @GetMapping
    public List<Mascota> listarMascotas() {
        return mascotaService.listarMascotas();
    }

    @GetMapping("/{id}")
    public Mascota buscarMascotaPorId(@PathVariable Long id) {
        return mascotaService.buscarMascotaPorId(id);
    }

    // Endpoint para registrar una mascota asociada a un cliente.
    @PostMapping
    public Mascota guardarMascota(@RequestBody Mascota mascota) {
        return mascotaService.guardarMascota(mascota);
    }

    @PutMapping("/{id}")
    public Mascota actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        return mascotaService.actualizarMascota(id, mascota);
    }

    @DeleteMapping("/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return "Mascota eliminada correctamente";
    }
}