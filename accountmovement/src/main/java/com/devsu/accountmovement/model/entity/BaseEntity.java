package com.devsu.accountmovement.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;

import static com.devsu.accountmovement.utils.SystemConstants.ESTADO;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Column(name = ESTADO, nullable = false)
    private Boolean estado;
}