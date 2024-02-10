package com.devsu.accountmovement.service;

import com.devsu.accountmovement.model.dto.ClienteDTO;
import com.devsu.accountmovement.model.dto.MovimientoDTO;
import com.devsu.accountmovement.model.dto.MovimientoEntryDTO;
import com.devsu.accountmovement.model.dto.ReporteDTO;
import com.devsu.accountmovement.model.entity.Cuenta;
import com.devsu.accountmovement.model.entity.Movimiento;
import com.devsu.accountmovement.model.repository.ICuentaRepository;
import com.devsu.accountmovement.model.repository.IMovimientoRepository;
import com.devsu.accountmovement.utils.ReplyMessage;
import com.devsu.accountmovement.utils.mapper.CuentaMapper;
import com.devsu.accountmovement.utils.mapper.MovimientoMapper;
import com.devsu.accountmovement.utils.mapper.ReporteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class ReporteService {

    @Autowired
    private IMovimientoRepository<Movimiento> iMovimientoRepository;

    @Autowired
    private ReporteMapper mapper;

    @Autowired
    private ReplyMessage replyMessage;

    @Autowired
    private Operations operations;

    @Transactional(readOnly = true)
    public ReplyMessage getAccountStatusByDateAndClient(LocalDate startDate, LocalDate endDate, Integer idCliente) {
        replyMessage.setError(true);
        replyMessage.setObject(null);
        List<String> messages = new ArrayList<>();
        try {
            List<Movimiento> entities = iMovimientoRepository.findAllByDateAndClient(startDate, endDate, idCliente);
            if (!entities.isEmpty()) {
                List<ReporteDTO> entitiesDto = new ArrayList<>();
                ReplyMessage rCliente= new ReplyMessage();
                for (Movimiento entity : entities) {
                    rCliente = operations.getCliente(entity.getCuenta().getIdCliente());
                    if(rCliente != null && !rCliente.getError() && rCliente.getObject() != null) {
                        ClienteDTO cliente = operations.convertToClienteDTO(rCliente.getObject().toString());
                        entitiesDto.add(mapper.read(entity, cliente));
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
}