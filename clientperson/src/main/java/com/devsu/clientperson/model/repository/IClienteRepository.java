package com.devsu.clientperson.model.repository;

import com.devsu.clientperson.model.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import static com.devsu.clientperson.utils.SystemConstants.ID;
import static com.devsu.clientperson.utils.SystemConstants.IDENTIFICACION_CLIENTE_QUERY;
import static com.devsu.clientperson.utils.SystemConstants.IDENTIFICACION_PERSONA;
import static com.devsu.clientperson.utils.SystemConstants.NOMBRE_CLIENTE_QUERY;
import static com.devsu.clientperson.utils.SystemConstants.NOMBRE_PERSONA;
import static com.devsu.clientperson.utils.SystemConstants.TELEFONO_CLIENTE_QUERY;
import static com.devsu.clientperson.utils.SystemConstants.TELEFONO_PERSONA;

@Repository
public interface IClienteRepository<C extends Cliente> extends IBaseRepository<C> {
    @Query(value = NOMBRE_CLIENTE_QUERY)
    String searchByName(@Param(ID) Integer id, @Param(NOMBRE_PERSONA) String nombre);

    @Query(value = IDENTIFICACION_CLIENTE_QUERY)
    String searchByIdentification(@Param(ID) Integer id, @Param(IDENTIFICACION_PERSONA) String identificacion);

    @Query(value = TELEFONO_CLIENTE_QUERY)
    Long searchByTelephone(@Param(ID) Integer id, @Param(TELEFONO_PERSONA) Long telefono);
}