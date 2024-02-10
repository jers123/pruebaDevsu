package com.devsu.clientperson.utils.mapper;

import com.devsu.clientperson.model.dto.BaseEntityDTO;
import com.devsu.clientperson.model.entity.BaseEntity;

public interface IMapper<BED extends BaseEntityDTO, BE extends BaseEntity> {
    BE create(BED entityDto);
    BED read(BE entity);
    BE update(BED entityDto, BE entity);
}