package ni.edu.uam.veterinariaapi.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mascotas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String especie;

    private String raza;

    private Integer edad;

    // Relación muchos a uno: varias mascotas pueden pertenecer a un mismo cliente.
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}