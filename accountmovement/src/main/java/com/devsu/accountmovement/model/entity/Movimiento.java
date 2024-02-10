package com.devsu.accountmovement.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

import static com.devsu.accountmovement.utils.SystemConstants.CUENTA_TABLE;
import static com.devsu.accountmovement.utils.SystemConstants.DATABASE;
import static com.devsu.accountmovement.utils.SystemConstants.FECHA_MOVIMIENTO;
import static com.devsu.accountmovement.utils.SystemConstants.ID;
import static com.devsu.accountmovement.utils.SystemConstants.MOVIMIENTO_TABLE;
import static com.devsu.accountmovement.utils.SystemConstants.SALDO_MOVIMIENTO;
import static com.devsu.accountmovement.utils.SystemConstants.SCHEMA;
import static com.devsu.accountmovement.utils.SystemConstants.TIPO;
import static com.devsu.accountmovement.utils.SystemConstants.TIPO_MOVIMIENTO_LENGTH;
import static com.devsu.accountmovement.utils.SystemConstants.VALOR_MOVIMIENTO;

@Entity
@Table(name = MOVIMIENTO_TABLE, catalog = DATABASE, schema = SCHEMA)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Movimiento extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = MOVIMIENTO_TABLE + "_" + ID)
    private Integer idMovimiento;

    @JoinColumn(name = CUENTA_TABLE + "_" + ID, referencedColumnName = CUENTA_TABLE + "_" + ID, nullable = false,
            foreignKey = @ForeignKey(name = MOVIMIENTO_TABLE + "_" + CUENTA_TABLE + "_" + ID + "_fk"))
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuenta cuenta;

    @Column(name = FECHA_MOVIMIENTO, nullable = false)
    private LocalDate fecha;

    @Column(name = TIPO + "_" + MOVIMIENTO_TABLE, nullable = false, length = TIPO_MOVIMIENTO_LENGTH)
    private String tipoMovimiento;

    @Column(name = VALOR_MOVIMIENTO, nullable = false)
    private Long valor;

    @Column(name = SALDO_MOVIMIENTO, nullable = false)
    private Long saldo;
}