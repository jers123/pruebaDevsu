package com.devsu.clientperson.controller;

import com.devsu.clientperson.model.dto.BaseEntityDTO;
import com.devsu.clientperson.utils.ReplyMessage;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.devsu.clientperson.utils.SystemConstants.CREATE_PATH;
import static com.devsu.clientperson.utils.SystemConstants.DELETE_PATH;
import static com.devsu.clientperson.utils.SystemConstants.GET_ALL_PATH;
import static com.devsu.clientperson.utils.SystemConstants.GET_ID_PATH;
import static com.devsu.clientperson.utils.SystemConstants.ID;
import static com.devsu.clientperson.utils.SystemConstants.UPDATE_PATH;

public interface IBaseController<B extends BaseEntityDTO> {
    @PostMapping(value = CREATE_PATH)
    ResponseEntity<ReplyMessage> create(@Valid @RequestBody B entityDto);

    @GetMapping(GET_ALL_PATH)
    ResponseEntity<ReplyMessage> getAll();

    @GetMapping(GET_ID_PATH + "{" + ID + "}")
    ResponseEntity<ReplyMessage> getById(@PathVariable(ID) Integer id);

    @PutMapping(UPDATE_PATH)
    ResponseEntity<ReplyMessage> update(@Valid @RequestBody B entityDto);

    @DeleteMapping(DELETE_PATH + "{" + ID + "}")
    ResponseEntity<ReplyMessage> delete(@PathVariable(ID) Integer id);
}