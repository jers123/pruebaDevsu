package com.devsu.clientperson.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.devsu.clientperson.utils.SystemConstants.CLIENTE_TABLE;
import static com.devsu.clientperson.utils.SystemConstants.CONTRASENA_CLIENTE;
import static com.devsu.clientperson.utils.SystemConstants.CONTRASENA_CLIENTE_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.DATABASE;
import static com.devsu.clientperson.utils.SystemConstants.ESTADO_CLIENTE;
import static com.devsu.clientperson.utils.SystemConstants.ID;
import static com.devsu.clientperson.utils.SystemConstants.IDENTIFICACION_PERSONA;
import static com.devsu.clientperson.utils.SystemConstants.NOMBRE_PERSONA;
import static com.devsu.clientperson.utils.SystemConstants.SCHEMA;
import static com.devsu.clientperson.utils.SystemConstants.TELEFONO_PERSONA;

@Entity
@Table(name = CLIENTE_TABLE, catalog = DATABASE, schema = SCHEMA,
        uniqueConstraints = {
                @UniqueConstraint(name = CLIENTE_TABLE + "_" + NOMBRE_PERSONA + "_uk", columnNames = {NOMBRE_PERSONA}),
                @UniqueConstraint(name = CLIENTE_TABLE + "_" + IDENTIFICACION_PERSONA + "_uk", columnNames = {IDENTIFICACION_PERSONA}),
                @UniqueConstraint(name = CLIENTE_TABLE + "_" + TELEFONO_PERSONA + "_uk", columnNames = {TELEFONO_PERSONA})
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Cliente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CLIENTE_TABLE + "_" + ID)
    private Integer idCliente;

    @Column(name = CONTRASENA_CLIENTE, nullable = false, length = CONTRASENA_CLIENTE_LENGTH)
    private String contrasena;

    @Column(name = ESTADO_CLIENTE, nullable = false)
    private Boolean estado;
}