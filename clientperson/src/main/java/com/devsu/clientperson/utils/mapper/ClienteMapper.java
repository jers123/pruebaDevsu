package com.devsu.clientperson.utils.mapper;

import com.devsu.clientperson.model.dto.ClienteDTO;
import com.devsu.clientperson.model.entity.Cliente;

public class ClienteMapper implements IMapper<ClienteDTO, Cliente> {

    @Override
    public Cliente create(ClienteDTO entityDto) {
        Cliente entity = new Cliente();
        entity.setNombre(entityDto.getNombre());
        entity.setGenero(entityDto.getGenero());
        entity.setEdad(entityDto.getEdad());
        entity.setIdentificacion(entityDto.getIdentificacion());
        entity.setDireccion(entityDto.getDireccion());
        entity.setTelefono(entityDto.getTelefono());
        entity.setContrasena(entityDto.getContrasena());
        entity.setEstado(entityDto.getEstado());
        return entity;
    }

    @Override
    public ClienteDTO read(Cliente entity) {
        ClienteDTO entityDto = new ClienteDTO();
        entityDto.setIdCliente(entity.getIdCliente());
        entityDto.setNombre(entity.getNombre());
        entityDto.setGenero(entity.getGenero());
        entityDto.setEdad(entity.getEdad());
        entityDto.setIdentificacion(entity.getIdentificacion());
        entityDto.setDireccion(entity.getDireccion());
        entityDto.setTelefono(entity.getTelefono());
        entityDto.setContrasena(entity.getContrasena());
        entityDto.setEstado(entity.getEstado());
        return entityDto;
    }

    @Override
    public Cliente update(ClienteDTO entityDto, Cliente entity) {
        entity.setNombre(entityDto.getNombre());
        if(entityDto.getGenero() != null) {
            entity.setGenero(entityDto.getGenero());
        }
        if(entityDto.getEdad() != null) {
            entity.setEdad(entityDto.getEdad());
        }
        entity.setDireccion(entityDto.getDireccion());
        entity.setTelefono(entityDto.getTelefono());
        entity.setContrasena(entityDto.getContrasena());
        entity.setEstado(entityDto.getEstado());
        return entity;
    }
}