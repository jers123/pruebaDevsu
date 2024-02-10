package com.devsu.accountmovement.model.repository;

import com.devsu.accountmovement.model.entity.Movimiento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.devsu.accountmovement.utils.SystemConstants.CLIENT_PARAM;
import static com.devsu.accountmovement.utils.SystemConstants.DATE_END_PARAM;
import static com.devsu.accountmovement.utils.SystemConstants.DATE_START_PARAM;
import static com.devsu.accountmovement.utils.SystemConstants.ID;
import static com.devsu.accountmovement.utils.SystemConstants.MOVIMIENTO_ALL_QUERY;
import static com.devsu.accountmovement.utils.SystemConstants.MOVIMIENTO_CLIENTE_QUERY;
import static com.devsu.accountmovement.utils.SystemConstants.MOVIMIENTO_CUENTA_QUERY;

@Repository
public interface IMovimientoRepository<M extends Movimiento> extends IBaseRepository<M> {
    @Query(value = MOVIMIENTO_ALL_QUERY)
    List<M> findAllOrder();

    @Query(value = MOVIMIENTO_CUENTA_QUERY)
    List<M> findAllByAccount(@Param(ID) Integer id);

    @Query(value = MOVIMIENTO_CLIENTE_QUERY)
    List<M> findAllByDateAndClient(@Param(DATE_START_PARAM) LocalDate startDate, @Param(DATE_END_PARAM) LocalDate endDate, @Param(CLIENT_PARAM) Integer id);
}