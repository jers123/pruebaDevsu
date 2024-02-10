package com.devsu.clientperson.utils;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class SystemConstants {
    //PATHS
    public static final String LOCAL_ORIGIN_PATH = "http://localhost:8000";
    public static final String CLIENTES_PATH = "/clientes";

    public static final String CREATE_PATH = "/create";
    public static final String DELETE_PATH = "/delete/";
    public static final String GET_ALL_PATH = "/get-all";
    public static final String GET_ID_PATH = "/get-id/";
    public static final String UPDATE_PATH = "/update";
    public static final String CONTENT_TYPE = "Content-Type";

    // DATABASE
    public static final String DATABASE = "clientesDevsuDB";
    public static final String SCHEMA = "public";

    public static final String ID = "id";

    //PERSONA
    public static final String DIRECCION_PERSONA = "direccion";
    public static final int DIRECCION_PERSONA_LENGTH = 100;
    public static final String EDAD_PERSONA = "edad";
    public static final int EDAD_PERSONA_LENGTH = 3;
    public static final int EDAD_PERSONA_VALUE = 150;
    public static final String GENERO_PERSONA = "genero";
    public static final int GENERO_PERSONA_LENGTH = 10;
    public static final String IDENTIFICACION_PERSONA = "identificacion";
    public static final int IDENTIFICACION_PERSONA_LENGTH = 20;
    public static final String NOMBRE_PERSONA = "nombre";
    public static final int NOMBRE_PERSONA_LENGTH = 50;
    public static final String TELEFONO_PERSONA = "telefono";
    public static final int TELEFONO_PERSONA_LENGTH = 12;
    public static final long TELEFONO_PERSONA_VALUE = 999999999999L;

    //CLIENTE
    public static final String CLIENTE_TABLE = "cliente";
    public static final String CONTRASENA_CLIENTE = "contrasena";
    public static final int CONTRASENA_CLIENTE_LENGTH = 100;
    public static final String ESTADO_CLIENTE = "estado";
    public static final String IDENTIFICACION_CLIENTE_QUERY = "SELECT c.identificacion FROM Cliente c WHERE c.idCliente != :" + ID + " AND c." + IDENTIFICACION_PERSONA + " = :" + IDENTIFICACION_PERSONA;
    public static final String NOMBRE_CLIENTE_QUERY = "SELECT c.nombre FROM Cliente c WHERE c.idCliente != :" + ID + " AND c." + NOMBRE_PERSONA + " = :" + NOMBRE_PERSONA;
    public static final String TELEFONO_CLIENTE_QUERY = "SELECT c.telefono FROM Cliente c WHERE c.idCliente != :" + ID + " AND c." + TELEFONO_PERSONA + " = :" + TELEFONO_PERSONA;

    public static ResponseEntity<ReplyMessage> answer(ReplyMessage replyMessage) {
        return ResponseEntity
                .status(replyMessage.getHttpStatus())
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(replyMessage);
    }

    /*@Autowired
    private static Environment environment;
    private static Integer getPort() {
        return Integer.parseInt(environment.getProperty("server.port"));
    }*/
}