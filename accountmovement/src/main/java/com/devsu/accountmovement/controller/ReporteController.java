package com.devsu.accountmovement.controller;

import com.devsu.accountmovement.model.dto.MovimientoEntryDTO;
import com.devsu.accountmovement.service.MovimientoService;
import com.devsu.accountmovement.service.ReporteService;
import com.devsu.accountmovement.utils.ReplyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.devsu.accountmovement.utils.SystemConstants.CLIENT_PARAM;
import static com.devsu.accountmovement.utils.SystemConstants.DATE_END_PARAM;
import static com.devsu.accountmovement.utils.SystemConstants.DATE_START_PARAM;
import static com.devsu.accountmovement.utils.SystemConstants.LOCAL_ORIGIN_PATH;
import static com.devsu.accountmovement.utils.SystemConstants.REPORTE_PATH;
import static com.devsu.accountmovement.utils.SystemConstants.answer;

@RestController
@CrossOrigin(origins = {LOCAL_ORIGIN_PATH, "*"}
        ,methods = {RequestMethod.GET}
)
@RequestMapping(path = REPORTE_PATH)
@Validated
public class ReporteController {

    @Autowired
    private ReporteService service;

    @GetMapping()
    public ResponseEntity<ReplyMessage> getAccountStatusByDateAndClient(
            @RequestParam(DATE_START_PARAM) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(DATE_END_PARAM) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(CLIENT_PARAM) Integer idCliente) {
        try {
            return answer(service.getAccountStatusByDateAndClient(startDate, endDate, idCliente));
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}