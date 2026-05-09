package com.veterinaria.mascotas.repository;

import com.veterinaria.mascotas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {

}