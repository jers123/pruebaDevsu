package com.devsu.accountmovement.utils.mapper;

import com.devsu.accountmovement.model.dto.CuentaClienteDTO;
import com.devsu.accountmovement.model.dto.MovimientoDTO;
import com.devsu.accountmovement.model.dto.MovimientoEntryDTO;
import com.devsu.accountmovement.model.entity.Cuenta;
import com.devsu.accountmovement.model.entity.Movimiento;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper implements IMapper<MovimientoEntryDTO, Movimiento, MovimientoDTO> {
    @Override
    public Movimiento create(MovimientoEntryDTO entityDto) {
        Movimiento entity = new Movimiento();
        entity.setFecha(entityDto.getFecha());
        entity.setTipoMovimiento(entityDto.getTipoMovimiento());
        entity.setValor(entityDto.getValor());
        entity.setSaldo(entityDto.getSaldo());
        entity.setEstado(entityDto.getEstado());
        return entity;
    }

    public Movimiento createFull(MovimientoEntryDTO entityDto, Cuenta cuenta) {
        Movimiento entity = create(entityDto);
        entity.setCuenta(cuenta);
        return entity;
    }

    @Override
    public MovimientoDTO read(Movimiento entity) {
        MovimientoDTO entityDto = new MovimientoDTO();
        entityDto.setIdMovimiento(entity.getIdMovimiento());
        entityDto.setFecha(entity.getFecha());
        entityDto.setTipoMovimiento(entity.getTipoMovimiento());
        entityDto.setValor(entity.getValor());
        entityDto.setSaldo(entity.getSaldo());
        entityDto.setEstado(entity.getEstado());
        return entityDto;
    }

    public MovimientoDTO readFull(Movimiento entity, CuentaClienteDTO cuenta) {
        MovimientoDTO entityDto = read(entity);
        entityDto.setCuenta(cuenta);
        return entityDto;
    }

    @Override
    public Movimiento update(MovimientoEntryDTO entityDto, Movimiento entity) {
        entity.setFecha(entityDto.getFecha());
        entity.setTipoMovimiento(entityDto.getTipoMovimiento());
        entity.setValor(entityDto.getValor());
        entity.setSaldo(entityDto.getSaldo());
        entity.setSaldo(entityDto.getSaldo());
        entity.setEstado(entityDto.getEstado());
        return entity;
    }
}