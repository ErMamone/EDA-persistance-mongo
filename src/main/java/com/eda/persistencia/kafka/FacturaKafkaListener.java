package com.eda.persistencia.kafka;

import com.eda.persistencia.event.FacturaCreadaEvent;
import com.eda.persistencia.model.FacturaReadModel;
import com.eda.persistencia.model.ItemReadModel;
import com.eda.persistencia.repositories.FacturaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Log4j2
public class FacturaKafkaListener {

    private final ObjectMapper mapper;
    private final FacturaRepository repository;

    @KafkaListener(topics = "${kafka.topic.facturas-creadas}", groupId = "read-model-group")
    public void consumir(String mensaje) {
        try {
            var evento = mapper.readValue(mensaje, FacturaCreadaEvent.class);

            var items = evento.getItems().stream()
                    .map(i -> ItemReadModel.builder()
                            .producto(i.getProducto())
                            .cantidad(i.getCantidad())
                            .precio(i.getPrecio())
                            .build())
                    .toList();

            log.info(evento.toString());
            var entity = FacturaReadModel.builder()
                    .id(String.valueOf(evento.getFacturaId()))
                    .clienteId(evento.getClienteId())
                    .vendedorId(evento.getVendedorId())
                    .fecha(LocalDateTime.parse(evento.getPublishDateTime(), DateTimeFormatter.ISO_DATE_TIME))
                    .total(evento.getTotal())
                    .items(items)
                    .build();

            entity = repository.save(entity);
            log.info("Factura persistida en Mongo: " + entity.getId());

        } catch (Exception e) {
            log.error("Error procesando mensaje: " + e.getMessage());
        }
    }
}

