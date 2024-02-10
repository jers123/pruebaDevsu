package com.devsu.accountmovement.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

import static com.devsu.accountmovement.utils.Constants.CUENTA_MOVIMIENTO_NOT_NULL;
import static com.devsu.accountmovement.utils.Constants.ESTADO_MOVIMIENTO_NOT_NULL;
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
public class MovimientoEntryDTO extends BaseEntityDTO {
    private Integer idMovimiento;

    @NotNull(message = CUENTA_MOVIMIENTO_NOT_NULL)
    private Integer idCuenta;

    @NotNull(message = FECHA_MOVIMIENTO_NOT_NULL)
    private LocalDate fecha;

    @NotNull(message = TIPO_MOVIMIENTO_NOT_NULL)
    @NotBlank(message = TIPO_MOVIMIENTO_NOT_BLANK)
    @Size(min = 1, max = TIPO_MOVIMIENTO_LENGTH, message = TIPO_MOVIMIENTO_SIZE)
    private String tipoMovimiento;

    @NotNull(message = VALOR_MOVIMIENTO_NOT_NULL)
    private Long valor;

    private Long saldo;

    @NotNull(message = ESTADO_MOVIMIENTO_NOT_NULL)
    private Boolean estado;
}