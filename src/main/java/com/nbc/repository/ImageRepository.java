package com.nbc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nbc.entity.ImageClass;

@Repository
public interface ImageRepository extends JpaRepository<ImageClass, Long> {
	
}
