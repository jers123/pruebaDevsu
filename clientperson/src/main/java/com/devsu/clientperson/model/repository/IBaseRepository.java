package com.devsu.clientperson.model.repository;

import com.devsu.clientperson.model.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseRepository<E extends BaseEntity> extends JpaRepository<E, Integer> {
}