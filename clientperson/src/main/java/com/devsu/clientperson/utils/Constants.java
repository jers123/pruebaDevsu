package com.devsu.clientperson.utils;

import static com.devsu.clientperson.utils.SystemConstants.CONTRASENA_CLIENTE_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.DIRECCION_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.EDAD_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.GENERO_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.IDENTIFICACION_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.NOMBRE_PERSONA_LENGTH;
import static com.devsu.clientperson.utils.SystemConstants.TELEFONO_PERSONA_LENGTH;

public class Constants {

    public static final String NO_CONTENT = "No hay registros";
    public static final String NO_CONTENT_ID = "No hay registro de cliente con el id = ";
    public static final String YES_CONTENT = "Si hay registros";

    //CLIENTE
    public static final String CONTRASENA_CLIENTE_NOT_BLANK = "La contraseña del cliente no puede ser vacío.";
    public static final String CONTRASENA_CLIENTE_NOT_NULL = "La contraseña del cliente no puede ser nulo.";
    public static final String CONTRASENA_CLIENTE_SIZE = "El tamaño de la contraseña del cliente es mínimo de 1 y máximo de " + CONTRASENA_CLIENTE_LENGTH + ".";
    public static final String ESTADO_CLIENTE_NOT_NULL = "El estado del cliente no puede ser nulo.";
    public static final String SUCCESSFULLY_CREATED_CLIENTE = "Cliente creado exitosamente.";
    public static final String SUCCESSFULLY_DELETED_CLIENTE = "Cliente eliminado exitosamente.";
    public static final String SUCCESSFULLY_UPDATED_CLIENTE = "Cliente actualizado exitosamente.";


    //PERSONA
    public static final String DIRECCION_PERSONA_NOT_BLANK = "La dirección del cliente no puede ser vacío.";
    public static final String DIRECCION_PERSONA_NOT_NULL = "La dirección del cliente no puede ser nulo.";
    public static final String DIRECCION_PERSONA_SIZE = "El tamaño de la dirección del cliente es mínimo de 1 y máximo de " + DIRECCION_PERSONA_LENGTH + ".";
    public static final String EDAD_PERSONA_SIZE = "El tamaño de la edad del cliente es máximo de " + EDAD_PERSONA_LENGTH + ".";
    public static final String GENERO_PERSONA_SIZE = "El tamaño del género del cliente es máximo de " + GENERO_PERSONA_LENGTH + ".";
    public static final String IDENTIFICACION_PERSONA_NOT_BLANK = "La identificación del cliente no puede ser vacío.";
    public static final String IDENTIFICACION_PERSONA_NOT_NULL = "La identificación del cliente no puede ser nulo.";
    public static final String IDENTIFICACION_PERSONA_EXISTS = "La identificación del cliente ya existe.";
    public static final String IDENTIFICACION_PERSONA_SIZE = "El tamaño de la identificación del cliente es mínimo de 1 y máximo de " + IDENTIFICACION_PERSONA_LENGTH + ".";
    public static final String NOMBRE_PERSONA_NOT_BLANK = "El nombre del cliente no puede ser vacío.";
    public static final String NOMBRE_PERSONA_NOT_NULL = "El nombre del cliente no puede ser nulo.";
    public static final String NOMBRE_PERSONA_SIZE = "El tamaño del nombre del cliente es mínimo de 1 y máximo de " + NOMBRE_PERSONA_LENGTH + ".";
    public static final String NOMBRE_PERSONA_EXISTS = "El nombre del cliente ya existe.";
    public static final String TELEFONO_PERSONA_EXISTS = "El teléfono del cliente ya existe.";
    public static final String TELEFONO_PERSONA_NOT_NULL = "El teléfono del cliente no puede ser nulo.";
    public static final String TELEFONO_PERSONA_SIZE = "El tamaño del teléfono del cliente es mínimo de 1 y máximo de " + TELEFONO_PERSONA_LENGTH + ".";

    //HTTP ERRORS
    public static final String HTTP_MESSAGE1 = "Problema de método HTTP, se esperaba la petición por medio del método ";
    public static final String HTTP_MESSAGE2 = " pero se solicitó por medio del metodo ";
}