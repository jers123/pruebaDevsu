package com.devsu.accountmovement.controller;

import com.devsu.accountmovement.model.dto.CuentaDTO;
import com.devsu.accountmovement.service.CuentaService;
import com.devsu.accountmovement.utils.ReplyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.devsu.accountmovement.utils.SystemConstants.CUENTA_PATH;
import static com.devsu.accountmovement.utils.SystemConstants.LOCAL_ORIGIN_PATH;
import static com.devsu.accountmovement.utils.SystemConstants.answer;

@RestController
@CrossOrigin(origins = {LOCAL_ORIGIN_PATH}
        ,methods = {RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE
}
)
@RequestMapping(path = CUENTA_PATH)
@Validated
public class CuentaController implements IBaseController<CuentaDTO> {

    @Autowired
    private CuentaService service;

    @Override
    public ResponseEntity<ReplyMessage> create(CuentaDTO entityDto) {
        try {
            return answer(service.getCreate(entityDto));
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ReplyMessage> getAll() {
        try {
            return answer(service.getFindAll());
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ReplyMessage> getById(Integer id) {
        try {
            return answer(service.getFindById(id));
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ReplyMessage> update(CuentaDTO entityDto) {
        try {
            return answer(service.getUpdate(entityDto));
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ReplyMessage> delete(Integer id) {
        try {
            return answer(service.getDelete(id));
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}