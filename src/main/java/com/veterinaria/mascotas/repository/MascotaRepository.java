package com.veterinaria.mascotas.repository;

import com.veterinaria.mascotas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByClienteId(Long clienteId);
    boolean existsByIdAndClienteId(Long id, Long clienteId);
}