package com.devsu.accountmovement.utils.mapper;

import com.devsu.accountmovement.model.dto.BaseEntityDTO;
import com.devsu.accountmovement.model.entity.BaseEntity;

public interface IMapper<ED extends BaseEntityDTO, BE extends BaseEntity, SD extends BaseEntityDTO> {
    BE create(ED entityDto);
    SD read(BE entity);
    BE update(ED entityDto, BE entity);
}
