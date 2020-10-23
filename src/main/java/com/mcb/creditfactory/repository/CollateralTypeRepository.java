package com.mcb.creditfactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcb.creditfactory.model.CollateralType;

public interface CollateralTypeRepository extends JpaRepository<CollateralType, Long> {

}
