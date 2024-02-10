package com.devsu.accountmovement.utils.mapper;

import com.devsu.accountmovement.model.dto.ClienteDTO;
import com.devsu.accountmovement.model.dto.ReporteDTO;
import com.devsu.accountmovement.model.entity.Movimiento;
import org.springframework.stereotype.Component;

@Component
public class ReporteMapper {
    public ReporteDTO read(Movimiento entity, ClienteDTO cliente) {
        ReporteDTO entityDto = new ReporteDTO();
        entityDto.setIdCliente(cliente.getIdCliente());
        entityDto.setCliente(cliente.getNombre());
        entityDto.setIdCuenta(entity.getCuenta().getIdCuenta());
        entityDto.setTipoCuenta(entity.getCuenta().getTipoCuenta());
        entityDto.setNumeroCuenta(entity.getCuenta().getNumeroCuenta());
        entityDto.setSaldoInicial(entity.getCuenta().getSaldoInicial());
        entityDto.setEstado(entity.getCuenta().getEstado());
        entityDto.setIdMovimiento(entity.getIdMovimiento());
        entityDto.setFecha(entity.getFecha());
        entityDto.setTipoMovimiento(entity.getTipoMovimiento());
        entityDto.setValor(entity.getValor());
        entityDto.setSaldoDisponible(entity.getSaldo());
        return entityDto;
    }
}