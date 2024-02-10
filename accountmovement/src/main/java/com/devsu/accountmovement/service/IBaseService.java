package com.devsu.accountmovement.service;

import com.devsu.accountmovement.model.dto.BaseEntityDTO;
import com.devsu.accountmovement.utils.ReplyMessage;
import org.springframework.transaction.annotation.Transactional;

public interface IBaseService<BE extends BaseEntityDTO> {
    @Transactional()
    ReplyMessage getCreate(BE entityDto);

    @Transactional(readOnly = true)
    ReplyMessage getFindAll();

    @Transactional(readOnly = true)
    ReplyMessage getFindById(Integer id);

    @Transactional()
    ReplyMessage getUpdate(BE entityDto);

    @Transactional()
    ReplyMessage getDelete(Integer id);
}