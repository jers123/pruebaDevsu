package com.devsu.accountmovement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CuentaClienteDTO extends CuentaDTO {
    private ClienteDTO cliente;
}