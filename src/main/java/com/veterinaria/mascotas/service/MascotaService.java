package com.veterinaria.mascotas.service;

import com.veterinaria.mascotas.dto.MascotaRequest;
import com.veterinaria.mascotas.dto.MascotaResponse;
import com.veterinaria.mascotas.exception.ResourceNotFoundException;
import com.veterinaria.mascotas.model.Mascota;
import com.veterinaria.mascotas.repository.MascotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final UsuarioClientService usuarioClientService;

    public MascotaResponse crear(MascotaRequest request) {
        if (!usuarioClientService.existeUsuario(request.getClienteId())) {
            throw new IllegalArgumentException(
                    "El cliente con id " + request.getClienteId() + " no existe en el sistema");
        }
        Mascota mascota = Mascota.builder()
                .nombre(request.getNombre())
                .especie(request.getEspecie())
                .raza(request.getRaza())
                .fechaNacimiento(request.getFechaNacimiento())
                .clienteId(request.getClienteId())
                .build();
        return toResponse(mascotaRepository.save(mascota));
    }

    public List<MascotaResponse> listarTodas() {
        return mascotaRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public MascotaResponse buscarPorId(Long id) {
        return toResponse(findOrThrow(id));
    }

    public List<MascotaResponse> listarPorCliente(Long clienteId) {
        return mascotaRepository.findByClienteId(clienteId).stream()
                .map(this::toResponse)
                .toList();
    }

    public MascotaResponse actualizar(Long id, MascotaRequest request) {
        Mascota mascota = findOrThrow(id);
        mascota.setNombre(request.getNombre());
        mascota.setEspecie(request.getEspecie());
        mascota.setRaza(request.getRaza());
        mascota.setFechaNacimiento(request.getFechaNacimiento());
        return toResponse(mascotaRepository.save(mascota));
    }

    public void eliminar(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Mascota no encontrada con id: " + id);
        }
        mascotaRepository.deleteById(id);
    }

    public boolean existeMascota(Long id) {
        return mascotaRepository.existsById(id);
    }

    private Mascota findOrThrow(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con id: " + id));
    }

    private MascotaResponse toResponse(Mascota m) {
        return MascotaResponse.builder()
                .id(m.getId())
                .nombre(m.getNombre())
                .especie(m.getEspecie())
                .raza(m.getRaza())
                .fechaNacimiento(m.getFechaNacimiento())
                .clienteId(m.getClienteId())
                .build();
    }
}