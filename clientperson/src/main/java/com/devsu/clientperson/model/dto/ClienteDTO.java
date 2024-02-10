package com.devsu.clientperson.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.devsu.clientperson.utils.Constants.CONTRASENA_CLIENTE_NOT_BLANK;
import static com.devsu.clientperson.utils.Constants.CONTRASENA_CLIENTE_NOT_NULL;
import static com.devsu.clientperson.utils.Constants.CONTRASENA_CLIENTE_SIZE;
import static com.devsu.clientperson.utils.Constants.DIRECCION_PERSONA_NOT_BLANK;
import static com.devsu.clientperson.utils.Constants.DIRECCION_PERSONA_NOT_NULL;
import static com.devsu.clientperson.utils.Constants.DIRECCION_PERSONA_SIZE;
import static com.devsu.clientperson.utils.Constants.EDAD_PERSONA_SIZE;
import static com.devsu.clientperson.utils.Constants.ESTADO_CLIENTE_NOT_NULL;
import static com.devsu.clientperson.utils.Constants.GENERO_PERSONA_SIZE;
import static com.devsu.clientperson.utils.Constants.IDENTIFICACION_PERSONA_NOT_BLANK;
import static com.devsu.clientperson.utils.Constants.IDENTIFICACION_PERSONA_NOT_NULL;
import static com.devsu.clientperson.utils.Constants.IDENTIFICACION_PERSONA_SIZE;
import static com.devsu.clientperson.utils.Constants.NOMBRE_PERSONA_NOT_BLANK;
import static com.devsu.clientperson.utils.Constants.NOMBRE_PERSONA_NOT_NULL;
import static com.devsu.clientperson.utils.Constants.NOMBRE_PERSONA_SIZE;
import static com.devsu.clientperson.utils.Constants.TELEFONO_PERSONA_NOT_NULL;
import static com.devsu.clientperson.utils.Constants.TELEFONO_PERSONA_SIZE;
import static com.devsu.clientperson.utils.SystemConstants.CONTRASENA_CLIENTE_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.DIRECCION_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.EDAD_PERSONA_VALUE;
import static com.devsu.clientperson.utils.SystemConstants.GENERO_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.IDENTIFICACION_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.NOMBRE_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.TELEFONO_PERSONA_VALUE;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ClienteDTO extends BaseEntityDTO {
    private Integer idCliente;

    @NotNull(message = NOMBRE_PERSONA_NOT_NULL)
    @NotBlank(message = NOMBRE_PERSONA_NOT_BLANK)
    @Size(min = 1, max = NOMBRE_PERSONA_LENGTH, message = NOMBRE_PERSONA_SIZE)
    private String nombre;

    @Size(max = GENERO_PERSONA_LENGTH, message = GENERO_PERSONA_SIZE)
    private String genero;

    @Max(value = EDAD_PERSONA_VALUE, message = EDAD_PERSONA_SIZE)
    private Integer edad;

    @NotNull(message = IDENTIFICACION_PERSONA_NOT_NULL)
    @NotBlank(message = IDENTIFICACION_PERSONA_NOT_BLANK)
    @Size(min = 1, max = IDENTIFICACION_PERSONA_LENGTH, message = IDENTIFICACION_PERSONA_SIZE)
    private String identificacion;

    @NotNull(message = DIRECCION_PERSONA_NOT_NULL)
    @NotBlank(message = DIRECCION_PERSONA_NOT_BLANK)
    @Size(min = 1, max = DIRECCION_PERSONA_LENGTH, message = DIRECCION_PERSONA_SIZE)
    private String direccion;

    @NotNull(message = TELEFONO_PERSONA_NOT_NULL)
    @Min(value = 1, message = TELEFONO_PERSONA_SIZE)
    @Max(value = TELEFONO_PERSONA_VALUE, message = TELEFONO_PERSONA_SIZE)
    private Long telefono;

    @NotNull(message = CONTRASENA_CLIENTE_NOT_NULL)
    @NotBlank(message = CONTRASENA_CLIENTE_NOT_BLANK)
    @Size(min = 1, max = CONTRASENA_CLIENTE_LENGTH, message = CONTRASENA_CLIENTE_SIZE)
    private String contrasena;

    @NotNull(message = ESTADO_CLIENTE_NOT_NULL)
    private Boolean estado;
}