package com.devsu.accountmovement.utils;

import static com.devsu.accountmovement.utils.SystemConstants.*;

public class Constants {

    public static final String NO_CONTENT = "No hay registros";
    public static final String NO_CONTENT_ID = "No hay registros con el id = ";
    public static final String YES_CONTENT = "Si hay registros";

    //CLIENTE
    public static String CLIENTE_MESSAGE = "";

    //CUENTA
    public static final String CLIENTE_NOT_NULL = "El cliente no puede ser nulo.";
    public static final String DELETE_CUENTA_ERROR = "No se puede eliminar la cuenta ya que tiene uno o varios movimientos asociados.";
    public static final String ESTADO_CUENTA_NOT_NULL = "El estado de la cuenta no puede ser nulo.";
    public static final String NUMERO_CUENTA_NOT_NULL = "El número de la cuenta no puede ser nulo.";
    public static final String NUMERO_CUENTA_EXISTS = "El número de la cuenta ya existe.";
    public static final String SALDO_CUENTA_NOT_NULL = "El saldo de la cuenta no puede ser nulo.";
    public static final String SALDO_INICIAL_NOT_VALID = "El salo inicial de la cuenta debe ser moyor o igual a 0 para crear la cuenta.";
    public static final String SUCCESSFULLY_CREATED_CUENTA = "Cuenta creada exitosamente.";
    public static final String SUCCESSFULLY_DELETED_CUENTA = "Cuenta eliminada exitosamente.";
    public static final String SUCCESSFULLY_DELETED_CUENTA_BY_ERROR = "Cuenta eliminada despues de ser creada debido a error en crear movimiento.";
    public static final String SUCCESSFULLY_UPDATED_CUENTA = "Cuenta actualizada exitosamente.";
    public static final String TIPO_CUENTA_NOT_BLANK = "El tipo de cuenta no puede ser vacío.";
    public static final String TIPO_CUENTA_NOT_NULL = "El tipo de cuenta no puede ser nulo.";
    public static final String TIPO_CUENTA_SIZE = "El tamaño del tipo de cuenta es mínimo de 1 y máximo de " + TIPO_CUENTA_LENGTH + ".";

    //MOVIMIENTO

    public static final String CUENTA_MOVIMIENTO_NOT_NULL = "La cuenta relacionada al movimiento no puede ser vacío.";
    public static final String CUENTA_MOVIMIENTO_ERROR = "La cuenta del movimiento no se recomienda modificar porque puede alterar el saldo de la nueva cuenta debido a varios factores entre ellos la fecha e incluso puede no hacerse los calculos del saldo correctamente.";
    public static final String ERROR_DELETE = "No se puede eliminar el movimovimiento ya que este no es el ultimo movimiento de la cuenta que tiene asociada.";
    public static final String ERROR_UPDATE = "No se puede actualizar el movimovimiento ya que este no es el ultimo movimiento de la cuenta que tiene asociada.";
    public static final String ESTADO_MOVIMIENTO_NOT_NULL = "El estado del movimiento no puede ser nulo.";
    public static final String FECHA_ERROR = "La fecha del movimiento debe ser igual o despues de la fecha del ultimo movimiento relacionado a la cuenta especificada.";
    public static final String FECHA_MOVIMIENTO_NOT_NULL = "La fecha del movimiento no puede ser vacío.";
    public static final String SALDO_ERROR = "El saldo no se pudo calcular correctamente o no existe saldo suficiente.";
    public static final String SUCCESSFULLY_CREATED_MOVIMIENTO = "Movimiento creado exitosamente.";
    public static final String SUCCESSFULLY_DELETED_MOVIMIENTO = "Movimiento eliminado exitosamente.";
    public static final String SUCCESSFULLY_UPDATED_MOVIMIENTO = "Movimiento actualizado exitosamente.";
    public static final String TIPO_ERROR = "El tipo de movimiento debe ser '" + DEPOSIT + "' o '" + WITHDRW + "'.";
    public static final String TIPO_VALOR_ERROR = "El tipo de movimiento no coincide con el valor.";
    public static final String TIPO_MOVIMIENTO_NOT_BLANK = "El tipo de movimiento no puede ser vacío.";
    public static final String TIPO_MOVIMIENTO_NOT_NULL = "El tipo de movimiento no puede ser nulo.";
    public static final String TIPO_MOVIMIENTO_SIZE = "El tamaño del tipo de movimiento es mínimo de 1 y máximo de " + TIPO_MOVIMIENTO_LENGTH + ".";
    public static final String VALOR_MOVIMIENTO_NOT_NULL = "El vlor del movimiento no puede ser vacío.";

    //HTTP ERRORS
    public static final String API_ACONNECT_ERROR = "No se pudo establece conección con la API Clientes ";
    public static final String HTTP_MESSAGE1 = "Problema de método HTTP, se esperaba la petición por medio del método ";
    public static final String HTTP_MESSAGE2 = " pero se solicitó por medio del metodo ";
}
