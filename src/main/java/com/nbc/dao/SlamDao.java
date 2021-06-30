package com.nbc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nbc.entity.ImageClass;

@Repository
public interface SlamDao extends JpaRepository<ImageClass, Long> {

}
