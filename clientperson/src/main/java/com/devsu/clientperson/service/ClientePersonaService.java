package com.devsu.clientperson.service;

import com.devsu.clientperson.model.dto.ClienteDTO;
import com.devsu.clientperson.model.entity.Cliente;
import com.devsu.clientperson.model.repository.IClienteRepository;
import com.devsu.clientperson.utils.ReplyMessage;
import com.devsu.clientperson.utils.mapper.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.devsu.clientperson.utils.Constants.IDENTIFICACION_PERSONA_EXISTS;
import static com.devsu.clientperson.utils.Constants.NO_CONTENT;
import static com.devsu.clientperson.utils.Constants.NO_CONTENT_ID;
import static com.devsu.clientperson.utils.Constants.NOMBRE_PERSONA_EXISTS;
import static com.devsu.clientperson.utils.Constants.SUCCESSFULLY_CREATED_CLIENTE;
import static com.devsu.clientperson.utils.Constants.SUCCESSFULLY_DELETED_CLIENTE;
import static com.devsu.clientperson.utils.Constants.SUCCESSFULLY_UPDATED_CLIENTE;
import static com.devsu.clientperson.utils.Constants.TELEFONO_PERSONA_EXISTS;
import static com.devsu.clientperson.utils.Constants.YES_CONTENT;

@Service
@Validated
public class ClientePersonaService implements IBaseService<ReplyMessage, ClienteDTO> {

    @Autowired
    private IClienteRepository<Cliente> iClienteRepository;

    @Autowired
    private ReplyMessage replyMessage;

    @Autowired
    private IMapper<ClienteDTO, Cliente> clienteMapper;

    @Override
    public ReplyMessage getCreate(ClienteDTO entityDto) {
        replyMessage.setError(true);
        replyMessage.setObject(null);
        List<String> messages = new ArrayList<>();
        try {
            String nombre = iClienteRepository.searchByName(0, entityDto.getNombre());
            String identificacion = iClienteRepository.searchByIdentification(0, entityDto.getIdentificacion());
            Long telefono = iClienteRepository.searchByTelephone(0, entityDto.getTelefono());
            if (nombre != null) {
                messages.add(NOMBRE_PERSONA_EXISTS);
            }
            if (identificacion != null) {
                messages.add(IDENTIFICACION_PERSONA_EXISTS);
            }
            if (telefono != null) {
                messages.add(TELEFONO_PERSONA_EXISTS);
            }
            if (nombre == null && identificacion == null && telefono == null) {
                Cliente entity = clienteMapper.create(entityDto);
                entityDto = clienteMapper.read(iClienteRepository.save(entity));
                replyMessage.setHttpStatus(HttpStatus.CREATED);
                replyMessage.setError(false);
                messages.add(SUCCESSFULLY_CREATED_CLIENTE);
                replyMessage.setObject(entityDto);
            } else {
                replyMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            replyMessage.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            messages.add(e.getMessage());
        }
        replyMessage.setMessage(messages);
        replyMessage.setDate(LocalDateTime.now());
        return replyMessage;
    }

    @Override
    public ReplyMessage getFindAll() {
        replyMessage.setError(true);
        replyMessage.setObject(null);
        List<String> messages = new ArrayList<>();
        try {
            List<Cliente> entities = iClienteRepository.findAll();
            if (!entities.isEmpty()) {
                List<ClienteDTO> entitiesDto = new ArrayList<>();
                for (Cliente entity : entities) {
                    entitiesDto.add(clienteMapper.read(entity));
                }
                messages.add(YES_CONTENT);
                replyMessage.setObject(entitiesDto);
            } else {
                messages.add(NO_CONTENT);
            }
            replyMessage.setHttpStatus(HttpStatus.OK);
            replyMessage.setError(false);
        } catch (Exception e) {
            replyMessage.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            messages.add(e.getMessage());
        }
        replyMessage.setMessage(messages);
        replyMessage.setDate(LocalDateTime.now());
        return replyMessage;
    }

    @Override
    public ReplyMessage getFindById(Integer id) {
        replyMessage.setError(true);
        replyMessage.setObject(null);
        List<String> messages = new ArrayList<>();
        try {
            Cliente entity = iClienteRepository.findById(id).orElse(null);
            if (entity != null) {
                ClienteDTO entityDto = clienteMapper.read(entity);
                replyMessage.setHttpStatus(HttpStatus.OK);
                messages.add(YES_CONTENT);
                replyMessage.setObject(entityDto);
            } else {
                replyMessage.setHttpStatus(HttpStatus.NOT_FOUND);
                messages.add(NO_CONTENT_ID + id);
            }
            replyMessage.setError(false);
        } catch (Exception e) {
            replyMessage.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            messages.add(e.getMessage());
        }
        replyMessage.setMessage(messages);
        replyMessage.setDate(LocalDateTime.now());
        return replyMessage;
    }

    @Override
    public ReplyMessage getUpdate(ClienteDTO entityDto) {
        replyMessage.setError(true);
        replyMessage.setObject(null);
        List<String> messages = new ArrayList<>();
        try {
            Cliente entity = iClienteRepository.findById(entityDto.getIdCliente()).orElse(null);
            if(entity != null) {
                String nombre = iClienteRepository.searchByName(entityDto.getIdCliente(), entityDto.getNombre());
                String identificacion = iClienteRepository.searchByIdentification(entityDto.getIdCliente(), entityDto.getIdentificacion());
                Long telefono = iClienteRepository.searchByTelephone(entityDto.getIdCliente(), entityDto.getTelefono());
                if (nombre != null) {
                    messages.add(NOMBRE_PERSONA_EXISTS);
                }
                if (identificacion != null) {
                    messages.add(IDENTIFICACION_PERSONA_EXISTS);
                }
                if (telefono != null) {
                    messages.add(TELEFONO_PERSONA_EXISTS);
                }
                if (nombre == null && identificacion == null && telefono == null) {
                    entity = clienteMapper.update(entityDto, entity);
                    entityDto = clienteMapper.read(iClienteRepository.save(entity));
                    replyMessage.setHttpStatus(HttpStatus.CREATED);
                    replyMessage.setError(false);
                    messages.add(SUCCESSFULLY_UPDATED_CLIENTE);
                    replyMessage.setObject(entityDto);
                } else {
                    replyMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
                }
            } else {
                replyMessage.setHttpStatus(HttpStatus.NOT_FOUND);
                messages.add(NO_CONTENT_ID + entityDto.getIdCliente());
            }
        } catch (Exception e) {
            replyMessage.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            messages.add(e.getMessage());
        }
        replyMessage.setMessage(messages);
        replyMessage.setDate(LocalDateTime.now());
        return replyMessage;
    }

    @Override
    public ReplyMessage getDelete(Integer id) {
        replyMessage.setError(true);
        replyMessage.setObject(null);
        List<String> messages = new ArrayList<>();
        try {
            Cliente entity = iClienteRepository.findById(id).orElse(null);
            if (entity != null) {
                iClienteRepository.delete(entity);
                replyMessage.setHttpStatus(HttpStatus.OK);
                replyMessage.setError(false);
                messages.add(SUCCESSFULLY_DELETED_CLIENTE);
            } else {
                replyMessage.setHttpStatus(HttpStatus.NOT_FOUND);
                messages.add(NO_CONTENT_ID + id);
            }
        } catch (Exception e) {
            replyMessage.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            messages.add(e.getMessage());
        }
        replyMessage.setMessage(messages);
        replyMessage.setDate(LocalDateTime.now());
        return replyMessage;
    }
}