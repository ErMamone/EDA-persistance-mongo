package com.eda.persistencia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "facturas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FacturaReadModel {

    @Id
    private String id;

    private String clienteId;

    private String vendedorId;

    private LocalDateTime fecha;

    private double total;

    private List<ItemReadModel> items;
}
