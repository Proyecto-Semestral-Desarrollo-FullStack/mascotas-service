package com.veterinaria.mascotas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UsuarioClientService {

    private final WebClient usuariosWebClient;

    public boolean existeUsuario(Long clienteId) {
        try {
            Boolean existe = usuariosWebClient.get()
                    .uri("/api/usuarios/existe/{id}", clienteId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            return Boolean.TRUE.equals(existe);
        } catch (Exception e) {
            return false;
        }
    }
}