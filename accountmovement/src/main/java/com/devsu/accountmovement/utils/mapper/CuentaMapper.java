package com.devsu.accountmovement.utils.mapper;

import com.devsu.accountmovement.model.dto.ClienteDTO;
import com.devsu.accountmovement.model.dto.CuentaClienteDTO;
import com.devsu.accountmovement.model.dto.CuentaDTO;
import com.devsu.accountmovement.model.entity.Cuenta;
import org.springframework.stereotype.Component;

@Component
public class CuentaMapper implements IMapper<CuentaDTO, Cuenta, CuentaClienteDTO> {
    @Override
    public Cuenta create(CuentaDTO entityDto) {
        Cuenta entity = new Cuenta();
        entity.setIdCliente(entityDto.getIdCliente());
        entity.setNumeroCuenta(entityDto.getNumeroCuenta());
        entity.setTipoCuenta(entityDto.getTipoCuenta());
        entity.setSaldoInicial(entityDto.getSaldoInicial());
        entity.setEstado(entityDto.getEstado());
        return entity;
    }

    @Override
    public CuentaClienteDTO read(Cuenta entity) {
        CuentaClienteDTO entityDto = new CuentaClienteDTO();
        entityDto.setIdCuenta(entity.getIdCuenta());
        entityDto.setIdCliente(entity.getIdCliente());
        entityDto.setNumeroCuenta(entity.getNumeroCuenta());
        entityDto.setTipoCuenta(entity.getTipoCuenta());
        entityDto.setSaldoInicial(entity.getSaldoInicial());
        entityDto.setEstado(entity.getEstado());
        return entityDto;
    }

    public CuentaClienteDTO readFull(Cuenta entity, ClienteDTO cliente) {
        CuentaClienteDTO entityDto = read(entity);
        entityDto.setCliente(cliente);
        return entityDto;
    }

    @Override
    public Cuenta update(CuentaDTO entityDto, Cuenta entity) {
        entity.setIdCliente(entityDto.getIdCliente());
        entity.setNumeroCuenta(entityDto.getNumeroCuenta());
        entity.setTipoCuenta(entityDto.getTipoCuenta());
        entity.setEstado(entityDto.getEstado());
        return entity;
    }
}