package com.devsu.accountmovement.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.devsu.accountmovement.utils.Constants.CLIENTE_NOT_NULL;
import static com.devsu.accountmovement.utils.Constants.ESTADO_CUENTA_NOT_NULL;
import static com.devsu.accountmovement.utils.Constants.NUMERO_CUENTA_NOT_NULL;
import static com.devsu.accountmovement.utils.Constants.SALDO_CUENTA_NOT_NULL;
import static com.devsu.accountmovement.utils.Constants.TIPO_CUENTA_NOT_BLANK;
import static com.devsu.accountmovement.utils.Constants.TIPO_CUENTA_NOT_NULL;
import static com.devsu.accountmovement.utils.Constants.TIPO_CUENTA_SIZE;
import static com.devsu.accountmovement.utils.SystemConstants.TIPO_CUENTA_LENGTH;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CuentaDTO extends BaseEntityDTO {
    private Integer idCuenta;

    @NotNull(message = CLIENTE_NOT_NULL)
    private Integer idCliente;

    @NotNull(message = NUMERO_CUENTA_NOT_NULL)
    private Integer numeroCuenta;

    @NotNull(message = TIPO_CUENTA_NOT_NULL)
    @NotBlank(message = TIPO_CUENTA_NOT_BLANK)
    @Size(min = 1, max = TIPO_CUENTA_LENGTH, message = TIPO_CUENTA_SIZE)
    private String tipoCuenta;

    @NotNull(message = SALDO_CUENTA_NOT_NULL)
    private Long saldoInicial;

    @NotNull(message = ESTADO_CUENTA_NOT_NULL)
    private Boolean estado;
}