package com.devsu.clientperson.controller;

import com.devsu.clientperson.model.dto.ClienteDTO;
import com.devsu.clientperson.service.ClientePersonaService;
import com.devsu.clientperson.utils.ReplyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.devsu.clientperson.utils.SystemConstants.CLIENTES_PATH;
import static com.devsu.clientperson.utils.SystemConstants.LOCAL_ORIGIN_PATH;
import static com.devsu.clientperson.utils.SystemConstants.answer;

@RestController
@CrossOrigin(origins = {LOCAL_ORIGIN_PATH,
        "*"
    }
    ,methods = {RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE
}
)
@RequestMapping(path = CLIENTES_PATH)
@Validated
public class ClientePersonaController implements IBaseController<ClienteDTO> {

    @Autowired
    private ClientePersonaService clientePersonaService;

    @Autowired
    private ReplyMessage replyMessage;

    @Override
    public ResponseEntity<ReplyMessage> create(ClienteDTO entityDto) {
        try {
            replyMessage = clientePersonaService.getCreate(entityDto);
            return answer(replyMessage);
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ReplyMessage> getAll() {
        try {
            replyMessage = clientePersonaService.getFindAll();
            return answer(replyMessage);
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ReplyMessage> getById(Integer id) {
        try {
            replyMessage = clientePersonaService.getFindById(id);
            return answer(replyMessage);
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ReplyMessage> update(ClienteDTO entityDto) {
        try {
            replyMessage = clientePersonaService.getUpdate(entityDto);
            return answer(replyMessage);
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ReplyMessage> delete(Integer id) {
        try {
            replyMessage = clientePersonaService.getDelete(id);
            return answer(replyMessage);
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}