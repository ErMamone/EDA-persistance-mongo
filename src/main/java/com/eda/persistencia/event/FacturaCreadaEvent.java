package com.eda.persistencia.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FacturaCreadaEvent implements Serializable {

    private Long facturaId;

    private String clienteId;

    private String vendedorId;

    private String publishDateTime;

    private List<ItemPayload> items;

    private double total;
}