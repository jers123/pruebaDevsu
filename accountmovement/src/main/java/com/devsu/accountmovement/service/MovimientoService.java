package com.devsu.accountmovement.service;

import com.devsu.accountmovement.model.dto.ClienteDTO;
import com.devsu.accountmovement.model.dto.MovimientoDTO;
import com.devsu.accountmovement.model.dto.MovimientoEntryDTO;
import com.devsu.accountmovement.model.entity.Cuenta;
import com.devsu.accountmovement.model.entity.Movimiento;
import com.devsu.accountmovement.model.repository.ICuentaRepository;
import com.devsu.accountmovement.model.repository.IMovimientoRepository;
import com.devsu.accountmovement.utils.ReplyMessage;
import com.devsu.accountmovement.utils.mapper.CuentaMapper;
import com.devsu.accountmovement.utils.mapper.MovimientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.devsu.accountmovement.utils.Constants.CUENTA_MOVIMIENTO_ERROR;
import static com.devsu.accountmovement.utils.Constants.ERROR_DELETE;
import static com.devsu.accountmovement.utils.Constants.ERROR_UPDATE;
import static com.devsu.accountmovement.utils.Constants.FECHA_ERROR;
import static com.devsu.accountmovement.utils.Constants.NO_CONTENT;
import static com.devsu.accountmovement.utils.Constants.NO_CONTENT_ID;
import static com.devsu.accountmovement.utils.Constants.SALDO_ERROR;
import static com.devsu.accountmovement.utils.Constants.SUCCESSFULLY_CREATED_MOVIMIENTO;
import static com.devsu.accountmovement.utils.Constants.SUCCESSFULLY_DELETED_MOVIMIENTO;
import static com.devsu.accountmovement.utils.Constants.SUCCESSFULLY_UPDATED_MOVIMIENTO;
import static com.devsu.accountmovement.utils.Constants.TIPO_ERROR;
import static com.devsu.accountmovement.utils.Constants.TIPO_VALOR_ERROR;
import static com.devsu.accountmovement.utils.Constants.YES_CONTENT;
import static com.devsu.accountmovement.utils.SystemConstants.DEPOSIT;
import static com.devsu.accountmovement.utils.SystemConstants.WITHDRW;

@Service
@Validated
public class MovimientoService implements IBaseService<MovimientoEntryDTO> {

    @Autowired
    private IMovimientoRepository<Movimiento> iMovimientoRepository;

    @Autowired
    private ICuentaRepository<Cuenta> iCuentaRepository;

    @Autowired
    private MovimientoMapper mapper;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Autowired
    private ReplyMessage replyMessage;

    @Autowired
    private Operations operations;

    @Override
    public ReplyMessage getCreate(MovimientoEntryDTO entityDto) {
        replyMessage.setError(true);
        replyMessage.setObject(null);
        List<String> messages = new ArrayList<>();
        try {
            if(entityDto.getTipoMovimiento().equals(DEPOSIT) || entityDto.getTipoMovimiento().equals(WITHDRW)) {
                Cuenta cuenta = iCuentaRepository.findById(entityDto.getIdCuenta()).orElse(null);
                if(cuenta != null) {
                    boolean result = comprobateDate(entityDto, cuenta);
                    if(!result) {
                        messages.add(FECHA_ERROR);
                    }
                    Long saldo = null;
                    if((entityDto.getTipoMovimiento().equals(DEPOSIT) && entityDto.getValor() >= 0)
                            || (entityDto.getTipoMovimiento().equals(WITHDRW) && entityDto.getValor() < 0)) {
                        saldo = calculateBalance(cuenta, entityDto);
                    } else {
                        messages.add(TIPO_VALOR_ERROR);
                    }
                    if(saldo != null && result) {
                        entityDto.setSaldo(saldo);
                        Movimiento entity = mapper.createFull(entityDto, cuenta);
                        ReplyMessage rCliente = operations.getCliente(cuenta.getIdCliente());
                        if(rCliente != null && !rCliente.getError() && rCliente.getObject() != null) {
                            ClienteDTO cliente = operations.convertToClienteDTO(rCliente.getObject().toString());
                            entity = iMovimientoRepository.save(entity);
                            MovimientoDTO movimientoDto = mapper.readFull(entity, cuentaMapper.readFull(cuenta, cliente));
                            replyMessage.setHttpStatus(HttpStatus.CREATED);
                            replyMessage.setError(false);
                            messages.add(SUCCESSFULLY_CREATED_MOVIMIENTO);
                            replyMessage.setObject(movimientoDto);
                        } else {
                            messages.add(rCliente.getMessage().getFirst());
                            replyMessage.setHttpStatus(rCliente.getHttpStatus());
                        }
                    } else {
                        messages.add(SALDO_ERROR);
                        replyMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
                    }
                } else {
                    replyMessage.setHttpStatus(HttpStatus.NOT_FOUND);
                    messages.add(NO_CONTENT_ID + entityDto.getIdCuenta() + " en cuenta");
                }
            } else {
                messages.add(TIPO_ERROR);
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
            List<Movimiento> entities = iMovimientoRepository.findAllOrder();
            if (!entities.isEmpty()) {
                List<MovimientoDTO> entitiesDto = new ArrayList<>();
                ReplyMessage rCliente= new ReplyMessage();
                for (Movimiento entity : entities) {
                    rCliente = operations.getCliente(entity.getCuenta().getIdCliente());
                    if(rCliente != null && !rCliente.getError() && rCliente.getObject() != null) {
                        ClienteDTO cliente = operations.convertToClienteDTO(rCliente.getObject().toString());
                        entitiesDto.add(mapper.readFull(entity, cuentaMapper.readFull(entity.getCuenta(), cliente)));
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
            Movimiento entity = iMovimientoRepository.findById(id).orElse(null);
            if (entity != null) {
                ReplyMessage rCliente = operations.getCliente(entity.getCuenta().getIdCliente());
                if(rCliente != null && !rCliente.getError() && rCliente.getObject() != null) {
                    ClienteDTO cliente = operations.convertToClienteDTO(rCliente.getObject().toString());
                    MovimientoDTO entityDto = mapper.readFull(entity, cuentaMapper.readFull(entity.getCuenta(), cliente));
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
    public ReplyMessage getUpdate(MovimientoEntryDTO entityDto) {
        replyMessage.setError(true);
        replyMessage.setObject(null);
        List<String> messages = new ArrayList<>();
        try {
            Movimiento entity = iMovimientoRepository.findById(entityDto.getIdMovimiento()).orElse(null);
            if(entity != null) {
                if(entityDto.getTipoMovimiento().equals(DEPOSIT) || entityDto.getTipoMovimiento().equals(WITHDRW)) {
                    boolean resultAll = true;
                    if(entity.getCuenta().getIdCuenta() != entityDto.getIdCuenta()) {
                        messages.add(CUENTA_MOVIMIENTO_ERROR);
                        resultAll = false;
                        replyMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
                    } else {
                        Cuenta cuenta = iCuentaRepository.findById(entityDto.getIdCuenta()).orElse(null);
                        if(cuenta != null) {
                            Long saldo = entity.getSaldo();
                            if(entity.getCuenta().getMovimientos() != null && entity.getCuenta().getMovimientos().getLast().getIdMovimiento() == entityDto.getIdMovimiento()) {
                                boolean result = comprobateDate(entityDto, cuenta);
                                resultAll = resultAll && result;
                                if(!result) {
                                    messages.add(FECHA_ERROR);
                                }
                                if((entityDto.getTipoMovimiento().equals(DEPOSIT) && entityDto.getValor() >= 0)
                                        || (entityDto.getTipoMovimiento().equals(WITHDRW) && entityDto.getValor() < 0)) {
                                    saldo = calculateBalanceUpdate(cuenta, entityDto);
                                } else {
                                    messages.add(TIPO_VALOR_ERROR);
                                    resultAll = false;
                                }
                            } else {
                                messages.add(ERROR_UPDATE);
                                resultAll = false;
                            }

                            if(saldo != null && resultAll) {
                                entityDto.setSaldo(saldo);
                                entity = mapper.update(entityDto, entity);
                                ReplyMessage rCliente = operations.getCliente(cuenta.getIdCliente());
                                if(rCliente != null && !rCliente.getError() && rCliente.getObject() != null) {
                                    ClienteDTO cliente = operations.convertToClienteDTO(rCliente.getObject().toString());
                                    entity = iMovimientoRepository.save(entity);
                                    MovimientoDTO movimientoDto = mapper.readFull(entity, cuentaMapper.readFull(cuenta, cliente));
                                    replyMessage.setHttpStatus(HttpStatus.CREATED);
                                    replyMessage.setError(false);
                                    messages.add(SUCCESSFULLY_UPDATED_MOVIMIENTO);
                                    replyMessage.setObject(movimientoDto);
                                } else {
                                    messages.add(rCliente.getMessage().getFirst());
                                    replyMessage.setHttpStatus(rCliente.getHttpStatus());
                                }
                            } else {
                                messages.add(SALDO_ERROR);
                                replyMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
                            }
                        } else {
                            replyMessage.setHttpStatus(HttpStatus.NOT_FOUND);
                            messages.add(NO_CONTENT_ID + entityDto.getIdCuenta() + " en cuenta");
                        }
                    }
                } else {
                    messages.add(TIPO_ERROR);
                    replyMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
                }
            } else {
                replyMessage.setHttpStatus(HttpStatus.NOT_FOUND);
                messages.add(NO_CONTENT_ID + entityDto.getIdMovimiento());
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
            Movimiento entity = iMovimientoRepository.findById(id).orElse(null);
            if (entity != null) {
                List<Movimiento> temp = iMovimientoRepository.findAllByAccount(entity.getCuenta().getIdCuenta());
                if(temp.getLast().getIdMovimiento() == id) {
                    iMovimientoRepository.delete(entity);
                    replyMessage.setHttpStatus(HttpStatus.OK);
                    replyMessage.setError(false);
                    messages.add(SUCCESSFULLY_DELETED_MOVIMIENTO);
                } else {
                    replyMessage.setHttpStatus(HttpStatus.NOT_FOUND);
                    messages.add(ERROR_DELETE);
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

    private Long calculateBalance(Cuenta cuenta, MovimientoEntryDTO entityDto) {
        Long saldo = null;
        if(cuenta.getMovimientos() != null) {
            Long saldoTemp = cuenta.getMovimientos().getLast().getSaldo();
            if(entityDto.getTipoMovimiento().equals(DEPOSIT)) {
                saldo = saldoTemp + entityDto.getValor();
            } else if(entityDto.getTipoMovimiento().equals(WITHDRW)) {
                if(saldoTemp > (entityDto.getValor() * -1)) {
                    saldo = saldoTemp + entityDto.getValor();
                }
            }
        } else {
            if(entityDto.getTipoMovimiento().equals(DEPOSIT)) {
                saldo = entityDto.getValor();
            }
        }
        return saldo;
    }

    private Long calculateBalanceUpdate(Cuenta cuenta, MovimientoEntryDTO entityDto) {
        Long saldo = null;
        Long saldoTemp;
        if(cuenta.getMovimientos().size() >= 2) {
            saldoTemp = cuenta.getMovimientos().get(cuenta.getMovimientos().size() - 2).getSaldo();
            if(entityDto.getTipoMovimiento().equals(DEPOSIT)) {
                saldo = saldoTemp + entityDto.getValor();
            } else if(entityDto.getTipoMovimiento().equals(WITHDRW)) {
                if(saldoTemp > (entityDto.getValor() * -1)) {
                    saldo = saldoTemp + entityDto.getValor();
                }
            }
        } else {
            if(entityDto.getTipoMovimiento().equals(DEPOSIT) ) {
                saldo = entityDto.getValor();
            }
        }
        return saldo;
    }

    private boolean comprobateDate(MovimientoEntryDTO entityDto, Cuenta cuenta) {
        boolean result = false;
        if(cuenta.getMovimientos() != null) {
            LocalDate fecha = cuenta.getMovimientos().getLast().getFecha();
            if(fecha.isBefore(entityDto.getFecha()) || fecha.isEqual(entityDto.getFecha())) {
                result =true;
            }
        } else {
            result = true;
        }
        return result;
    }
}