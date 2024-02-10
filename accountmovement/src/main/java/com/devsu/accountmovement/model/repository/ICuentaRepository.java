package com.devsu.accountmovement.model.repository;

import com.devsu.accountmovement.model.entity.Cuenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.devsu.accountmovement.utils.SystemConstants.CUENTA_ALL_QUERY;
import static com.devsu.accountmovement.utils.SystemConstants.ID;
import static com.devsu.accountmovement.utils.SystemConstants.NUMERO_CUENTA;
import static com.devsu.accountmovement.utils.SystemConstants.NUMERO_CUENTA_QUERY;

@Repository
public interface ICuentaRepository<C extends Cuenta> extends IBaseRepository<C> {
    @Query(value = CUENTA_ALL_QUERY)
    List<C> findAllOrder();

    @Query(value = NUMERO_CUENTA_QUERY)
    Integer searchByAccountNumber(@Param(ID) Integer id, @Param(NUMERO_CUENTA) Integer numeroCuenta);
}