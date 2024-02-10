package com.devsu.accountmovement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ReporteDTO extends BaseEntityDTO {
    private Integer idCliente;
    private String cliente;
    private Integer idCuenta;
    private String tipoCuenta;
    private Integer numeroCuenta;
    private Long saldoInicial;
    private Boolean estado;
    private Integer idMovimiento;
    private LocalDate fecha;
    private String tipoMovimiento;
    private Long valor;
    private Long saldoDisponible;
}