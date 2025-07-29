package com.eda.persistencia.repositories;

import com.eda.persistencia.model.FacturaReadModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FacturaRepository extends MongoRepository<FacturaReadModel, String> {
    List<FacturaReadModel> findByClienteId(String clienteId);
}
