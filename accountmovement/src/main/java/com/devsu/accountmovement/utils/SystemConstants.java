package com.devsu.accountmovement.utils;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class SystemConstants {
    //PATHS
    public static final String LOCAL_ORIGIN_PATH = "http://localhost:8001";
    public static final String CUENTA_PATH = "/cuentas";
    public static final String MOVIMIENTO_PATH = "/movimientos";
    public static final String REPORTE_PATH = "/reportes";

    public static final String CREATE_PATH = "/create";
    public static final String DELETE_PATH = "/delete/";
    public static final String GET_ALL_PATH = "/get-all";
    public static final String GET_ID_PATH = "/get-id/";
    public static final String UPDATE_PATH = "/update";
    public static final String CLIENT_PARAM = "cliente";
    public static final String DATE_END_PARAM = "fechaFin";
    public static final String DATE_START_PARAM = "fechaInicio";
    public static final String LOCAL_ORIGIN_CLIENTE_PATH = "http://localhost:8000/clientes" + GET_ID_PATH;
    public static final String CONTENT_TYPE = "Content-Type";


    // DATABASE
    public static final String DATABASE = "clientesDevsuDB";
    public static final String SCHEMA = "public";

    public static final String ESTADO = "estado";
    public static final String ID = "id";
    public static final String TIPO = "tipo";

    //CUENTA
    public static final String CUENTA_TABLE = "cuenta";
    public static final String CLIENTE_CUENTA = "cliente";
    public static final String NUMERO_CUENTA = "numero";
    public static final String SALDO_INICIAL = "saldo_inicial";
    public static final int TIPO_CUENTA_LENGTH = 10;
    public static final String CUENTA_ALL_QUERY = "SELECT c FROM Cuenta c ORDER BY c.idCuenta ASC";
    public static final String NUMERO_CUENTA_QUERY = "SELECT c.numeroCuenta FROM Cuenta c WHERE c.idCuenta != :" + ID + " AND c.numeroCuenta = :" + NUMERO_CUENTA;

    //MOVIMIENTO
    public static final String MOVIMIENTO_TABLE = "movimiento";
    public static final String FECHA_MOVIMIENTO = "fecha";
    public static final String SALDO_MOVIMIENTO = "saldo";
    public static final int TIPO_MOVIMIENTO_LENGTH = 10;
    public static final String VALOR_MOVIMIENTO = "valor";
    public static final String MOVIMIENTO_ALL_QUERY = "SELECT m FROM Movimiento m ORDER BY m.idMovimiento ASC";
    public static final String MOVIMIENTO_CLIENTE_QUERY = "SELECT m FROM Movimiento m WHERE m.cuenta.idCliente = :" + CLIENT_PARAM + " AND m.fecha BETWEEN :" + DATE_START_PARAM + " AND :" + DATE_END_PARAM + " ORDER BY m.cuenta.idCuenta,  m.idMovimiento ASC";
    public static final String MOVIMIENTO_CUENTA_QUERY = "SELECT m FROM Movimiento m WHERE m.cuenta.idCuenta = :" + ID + " ORDER BY m.idMovimiento ASC";

    public static final String DEPOSIT = "deposito";
    public static final String WITHDRW = "retiro";

    public static ResponseEntity<ReplyMessage> answer(ReplyMessage replyMessage) {
        return ResponseEntity
                .status(replyMessage.getHttpStatus())
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(replyMessage);
    }
}