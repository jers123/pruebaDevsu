package com.devsu.accountmovement.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import static com.devsu.accountmovement.utils.SystemConstants.CLIENTE_CUENTA;
import static com.devsu.accountmovement.utils.SystemConstants.CUENTA_TABLE;
import static com.devsu.accountmovement.utils.SystemConstants.DATABASE;
import static com.devsu.accountmovement.utils.SystemConstants.ID;
import static com.devsu.accountmovement.utils.SystemConstants.NUMERO_CUENTA;
import static com.devsu.accountmovement.utils.SystemConstants.SALDO_INICIAL;
import static com.devsu.accountmovement.utils.SystemConstants.SCHEMA;
import static com.devsu.accountmovement.utils.SystemConstants.TIPO;
import static com.devsu.accountmovement.utils.SystemConstants.TIPO_CUENTA_LENGTH;

@Entity
@Table(name = CUENTA_TABLE, catalog = DATABASE, schema = SCHEMA,
        uniqueConstraints = {
                @UniqueConstraint(name = CUENTA_TABLE + "_" + NUMERO_CUENTA + "_" +CUENTA_TABLE + "_uk", columnNames = {NUMERO_CUENTA + "_" +CUENTA_TABLE})
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Cuenta extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CUENTA_TABLE + "_" + ID)
    private Integer idCuenta;

    @Column(name = CLIENTE_CUENTA + "_" + ID, nullable = false)
    private Integer idCliente;

    @Column(name = NUMERO_CUENTA + "_" + CUENTA_TABLE, nullable = false, unique = true)
    private Integer numeroCuenta;

    @Column(name = TIPO + "_" + CUENTA_TABLE, nullable = false, length = TIPO_CUENTA_LENGTH)
    private String tipoCuenta;

    @Column(name = SALDO_INICIAL, nullable = false)
    private Long saldoInicial;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta", fetch = FetchType.LAZY)
    private List<Movimiento> movimientos;
}