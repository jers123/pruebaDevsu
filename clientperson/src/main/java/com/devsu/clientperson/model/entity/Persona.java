package com.devsu.clientperson.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import static com.devsu.clientperson.utils.SystemConstants.DIRECCION_PERSONA;
import static com.devsu.clientperson.utils.SystemConstants.DIRECCION_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.EDAD_PERSONA;
import static com.devsu.clientperson.utils.SystemConstants.EDAD_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.GENERO_PERSONA;
import static com.devsu.clientperson.utils.SystemConstants.GENERO_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.IDENTIFICACION_PERSONA;
import static com.devsu.clientperson.utils.SystemConstants.IDENTIFICACION_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.NOMBRE_PERSONA;
import static com.devsu.clientperson.utils.SystemConstants.NOMBRE_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.TELEFONO_PERSONA;
import static com.devsu.clientperson.utils.SystemConstants.TELEFONO_PERSONA_LENGTH;

@Data
@MappedSuperclass
public class Persona extends BaseEntity {
    @Column(name = NOMBRE_PERSONA, nullable = false, length = NOMBRE_PERSONA_LENGTH, unique = true)
    private String nombre;

    @Column(name = GENERO_PERSONA, length = GENERO_PERSONA_LENGTH)
    private String genero;

    @Column(name = EDAD_PERSONA, length = EDAD_PERSONA_LENGTH)
    private Integer edad;

    @Column(name = IDENTIFICACION_PERSONA, nullable = false, length = IDENTIFICACION_PERSONA_LENGTH, unique = true)
    private String identificacion;

    @Column(name = DIRECCION_PERSONA, nullable = false, length = DIRECCION_PERSONA_LENGTH)
    private String direccion;

    @Column(name = TELEFONO_PERSONA, nullable = false, length = TELEFONO_PERSONA_LENGTH, unique = true)
    private Long telefono;
}