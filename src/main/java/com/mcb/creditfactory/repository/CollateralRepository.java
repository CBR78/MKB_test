package com.mcb.creditfactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcb.creditfactory.model.Collateral;

public interface CollateralRepository extends JpaRepository<Collateral, Long> {

}
