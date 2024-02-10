package com.devsu.clientperson.service;

import com.devsu.clientperson.model.dto.BaseEntityDTO;
import com.devsu.clientperson.utils.ReplyMessage;
import org.springframework.transaction.annotation.Transactional;

public interface IBaseService<R extends ReplyMessage, BE extends BaseEntityDTO> {
    @Transactional()
    R getCreate(BE entityDto);

    @Transactional(readOnly = true)
    R getFindAll();

    @Transactional(readOnly = true)
    R getFindById(Integer id);

    @Transactional()
    R getUpdate(BE entityDto);

    @Transactional()
    R getDelete(Integer id);
}