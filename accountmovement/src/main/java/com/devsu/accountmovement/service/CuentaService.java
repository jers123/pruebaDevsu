package com.devsu.accountmovement.service;

import com.devsu.accountmovement.model.dto.ClienteDTO;
import com.devsu.accountmovement.model.dto.CuentaClienteDTO;
import com.devsu.accountmovement.model.dto.CuentaDTO;
import com.devsu.accountmovement.model.dto.MovimientoEntryDTO;
import com.devsu.accountmovement.model.entity.Cuenta;
import com.devsu.accountmovement.model.repository.ICuentaRepository;
import com.devsu.accountmovement.utils.ReplyMessage;
import com.devsu.accountmovement.utils.mapper.CuentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.devsu.accountmovement.utils.Constants.API_ACONNECT_ERROR;
import static com.devsu.accountmovement.utils.Constants.DELETE_CUENTA_ERROR;
import static com.devsu.accountmovement.utils.Constants.NO_CONTENT;
import static com.devsu.accountmovement.utils.Constants.NO_CONTENT_ID;
import static com.devsu.accountmovement.utils.Constants.NUMERO_CUENTA_EXISTS;
import static com.devsu.accountmovement.utils.Constants.SALDO_INICIAL_NOT_VALID;
import static com.devsu.accountmovement.utils.Constants.SUCCESSFULLY_CREATED_CUENTA;
import static com.devsu.accountmovement.utils.Constants.SUCCESSFULLY_DELETED_CUENTA;
import static com.devsu.accountmovement.utils.Constants.SUCCESSFULLY_DELETED_CUENTA_BY_ERROR;
import static com.devsu.accountmovement.utils.Constants.SUCCESSFULLY_UPDATED_CUENTA;
import static com.devsu.accountmovement.utils.Constants.YES_CONTENT;
import static com.devsu.accountmovement.utils.SystemConstants.DEPOSIT;
import static com.devsu.accountmovement.utils.SystemConstants.LOCAL_ORIGIN_CLIENTE_PATH;

@Service
@Validated
public class CuentaService implements IBaseService<CuentaDTO> {

    @Autowired
    private ICuentaRepository<Cuenta> iCuentaRepository;

    @Autowired
    private ReplyMessage replyMessage;

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private ReplyMessage replyMovimientoService;

    @Autowired
    private Operations operations;

    @Autowired
    private CuentaMapper mapper;

    @Override
    public ReplyMessage getCreate(CuentaDTO entityDto) {
        replyMessage.setError(true);
        replyMessage.setObject(null);
        List<String> messages = new ArrayList<>();
        try {
            ReplyMessage rCliente = operations.getCliente(entityDto.getIdCliente());
            if(rCliente != null && !rCliente.getError() && rCliente.getObject() != null) {
                ClienteDTO cliente = operations.convertToClienteDTO(rCliente.getObject().toString());
                Integer numeroCuenta = iCuentaRepository.searchByAccountNumber(0, entityDto.getNumeroCuenta());
                if (numeroCuenta != null) {
                    messages.add(NUMERO_CUENTA_EXISTS);
                }
                if(entityDto.getSaldoInicial() < 0) {
                    messages.add(SALDO_INICIAL_NOT_VALID);
                }
                if (numeroCuenta == null && entityDto.getSaldoInicial() >= 0) {
                    Cuenta entity = mapper.create(entityDto);
                    entityDto = mapper.readFull(iCuentaRepository.save(entity), cliente);
                    MovimientoEntryDTO movimientoDto = new MovimientoEntryDTO();
                    movimientoDto.setIdCuenta(entityDto.getIdCuenta());
                    movimientoDto.setFecha(LocalDate.now());
                    movimientoDto.setTipoMovimiento(DEPOSIT);
                    movimientoDto.setValor(entityDto.getSaldoInicial());
                    movimientoDto.setEstado(true);
                    replyMovimientoService = movimientoService.getCreate(movimientoDto);
                    if (!replyMovimientoService.getError()) {
                        replyMessage.setHttpStatus(HttpStatus.CREATED);
                        replyMessage.setError(false);
                        messages.add(SUCCESSFULLY_CREATED_CUENTA);
                        replyMessage.setObject(entityDto);
                    } else {
                        iCuentaRepository.deleteById(entityDto.getIdCuenta());
                        replyMessage.setHttpStatus(HttpStatus.PRECONDITION_FAILED);
                        messages.add(SUCCESSFULLY_DELETED_CUENTA_BY_ERROR);
                    }
                    messages.addAll(replyMovimientoService.getMessage());
                } else {
                    replyMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
                }
            } else {
                messages.addAll(rCliente.getMessage());
                replyMessage.setHttpStatus(rCliente.getHttpStatus());
            }
        } catch (Exception e) {
            replyMessage.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            String error = "I/O error on GET request for \"" + LOCAL_ORIGIN_CLIENTE_PATH + entityDto.getIdCliente() + "\": Connection refused: connect";
            //System.out.println("ERROR SYSTEM = " + e.getMessage() + "\nTIPO = " + e.getCause().toString());
            if(e.getMessage().equals(error)) {
                messages.add(API_ACONNECT_ERROR + LOCAL_ORIGIN_CLIENTE_PATH + entityDto.getIdCliente());
            } else {
                messages.add(e.getMessage());
            }
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
            List<Cuenta> entities = iCuentaRepository.findAllOrder();
            if (!entities.isEmpty()) {
                List<CuentaClienteDTO> entitiesDto = new ArrayList<>();
                ReplyMessage rCliente= new ReplyMessage();
                for (Cuenta entity : entities) {
                    rCliente = operations.getCliente(entity.getIdCliente());
                    if(rCliente != null && !rCliente.getError() && rCliente.getObject() != null) {
                        ClienteDTO cliente = operations.convertToClienteDTO(rCliente.getObject().toString());
                        entitiesDto.add(mapper.readFull(entity, cliente));
                    } else {
                        break;
                    }
                }
                if(entitiesDto.size() == entities.size()) {
                    messages.add(YES_CONTENT);
                    replyMessage.setObject(entitiesDto);
                } else {
                    messages.addAll(rCliente.getMessage());
                    replyMessage.setHttpStatus(rCliente.getHttpStatus());
                }
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
            Cuenta entity = iCuentaRepository.findById(id).orElse(null);
            if (entity != null) {
                ReplyMessage rCliente = operations.getCliente(entity.getIdCliente());
                if(rCliente != null && !rCliente.getError() && rCliente.getObject() != null) {
                    ClienteDTO cliente = operations.convertToClienteDTO(rCliente.getObject().toString());
                    CuentaClienteDTO entityDto = mapper.readFull(entity, cliente);
                    replyMessage.setHttpStatus(HttpStatus.OK);
                    messages.add(YES_CONTENT);
                    replyMessage.setObject(entityDto);
                } else {
                    messages.addAll(rCliente.getMessage());
                    replyMessage.setHttpStatus(rCliente.getHttpStatus());
                }
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
    public ReplyMessage getUpdate(CuentaDTO entityDto) {
        replyMessage.setError(true);
        replyMessage.setObject(null);
        List<String> messages = new ArrayList<>();
        try {
            Cuenta entity = iCuentaRepository.findById(entityDto.getIdCuenta()).orElse(null);
            if(entity != null) {
                ReplyMessage rCliente = operations.getCliente(entityDto.getIdCliente());
                if(rCliente != null && !rCliente.getError() && rCliente.getObject() != null) {
                    ClienteDTO cliente = operations.convertToClienteDTO(rCliente.getObject().toString());
                    Integer numeroCuenta = iCuentaRepository.searchByAccountNumber(entityDto.getIdCuenta(), entityDto.getNumeroCuenta());
                    if (numeroCuenta != null) {
                        messages.add(NUMERO_CUENTA_EXISTS);
                    }
                    if(entityDto.getSaldoInicial() < 0) {
                        messages.add(SALDO_INICIAL_NOT_VALID);
                    }
                    if (numeroCuenta == null && entityDto.getSaldoInicial() >= 0) {
                        entity = mapper.update(entityDto, entity);
                        entityDto = mapper.readFull(iCuentaRepository.save(entity), cliente);
                        replyMessage.setHttpStatus(HttpStatus.CREATED);
                        replyMessage.setError(false);
                        messages.add(SUCCESSFULLY_UPDATED_CUENTA);
                        replyMessage.setObject(entityDto);
                    } else {
                        replyMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
                    }
                } else {
                    messages.addAll(rCliente.getMessage());
                    replyMessage.setHttpStatus(rCliente.getHttpStatus());
                }
            } else {
                replyMessage.setHttpStatus(HttpStatus.NOT_FOUND);
                messages.add(NO_CONTENT_ID + entityDto.getIdCuenta());
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
            Cuenta entity = iCuentaRepository.findById(id).orElse(null);
            if (entity != null) {
                if(entity.getMovimientos().isEmpty()) {
                    iCuentaRepository.delete(entity);
                    replyMessage.setHttpStatus(HttpStatus.OK);
                    replyMessage.setError(false);
                    messages.add(SUCCESSFULLY_DELETED_CUENTA);
                } else {
                    replyMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
                    messages.add(DELETE_CUENTA_ERROR);
                }
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