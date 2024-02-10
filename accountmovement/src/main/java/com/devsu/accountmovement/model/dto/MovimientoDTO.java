package com.devsu.accountmovement.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

import static com.devsu.accountmovement.utils.Constants.CUENTA_MOVIMIENTO_NOT_NULL;
import static com.devsu.accountmovement.utils.Constants.FECHA_MOVIMIENTO_NOT_NULL;
import static com.devsu.accountmovement.utils.Constants.TIPO_MOVIMIENTO_NOT_BLANK;
import static com.devsu.accountmovement.utils.Constants.TIPO_MOVIMIENTO_NOT_NULL;
import static com.devsu.accountmovement.utils.Constants.TIPO_MOVIMIENTO_SIZE;
import static com.devsu.accountmovement.utils.Constants.VALOR_MOVIMIENTO_NOT_NULL;
import static com.devsu.accountmovement.utils.SystemConstants.TIPO_MOVIMIENTO_LENGTH;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MovimientoDTO extends BaseEntityDTO {
    private Integer idMovimiento;
    private CuentaDTO cuenta;
    private LocalDate fecha;
    private String tipoMovimiento;
    private Long valor;
    private Long saldo;
    private Boolean estado;
}