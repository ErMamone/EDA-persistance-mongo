package com.eda.persistencia.services;

import com.eda.persistencia.model.FacturaReadModel;
import com.eda.persistencia.repositories.FacturaRepository;
import io.micrometer.observation.ObservationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FacturaService {

    private final FacturaRepository repository;


    public List<FacturaReadModel> findAll() {
        return repository.findAll();
    }

    public Optional<FacturaReadModel> findById(String id) {
        return repository.findById(id);
    }

    public List<FacturaReadModel> findByClienteId(String clienteId) {
        return repository.findByClienteId(clienteId);
    }
}
