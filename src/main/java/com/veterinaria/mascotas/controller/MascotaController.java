package com.veterinaria.mascotas.controller;

import com.veterinaria.mascotas.dto.MascotaRequest;
import com.veterinaria.mascotas.dto.MascotaResponse;
import com.veterinaria.mascotas.service.MascotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaController {

    private final MascotaService mascotaService;

    @PostMapping
    public ResponseEntity<MascotaResponse> crear(@Valid @RequestBody MascotaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<MascotaResponse>> listar() {
        return ResponseEntity.ok(mascotaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.buscarPorId(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<MascotaResponse>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(mascotaService.listarPorCliente(clienteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaResponse> actualizar(@PathVariable Long id,
                                                      @Valid @RequestBody MascotaRequest request) {
        return ResponseEntity.ok(mascotaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mascotaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/existe/{id}")
    public ResponseEntity<Boolean> existeMascota(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.existeMascota(id));
    }
}