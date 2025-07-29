package com.eda.persistencia.controllers;

import com.eda.persistencia.model.FacturaReadModel;
import com.eda.persistencia.services.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<FacturaReadModel>> listarTodas() {
        var lista = facturaService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaReadModel> buscarPorId(@PathVariable String id) {
        return facturaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<FacturaReadModel>> buscarPorCliente(@PathVariable String clienteId) {
        return ResponseEntity.ok(facturaService.findByClienteId(clienteId));
    }
}
