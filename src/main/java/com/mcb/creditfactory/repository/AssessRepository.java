package com.mcb.creditfactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mcb.creditfactory.model.Assess;

public interface AssessRepository extends JpaRepository<Assess, Long> {

    @Query(value = "SELECT id, assessed_date, assessed_value, collateral FROM assess "
            + "WHERE collateral = ?1 AND assessed_date = ( SELECT MAX(assessed_date) FROM assess WHERE collateral = ?1 )",
            nativeQuery = true)
    Assess getLastAssess(Long collateralId);
    
    //ниже пошаговый getLastAssess, для отладки
   
//    @Query(value = "SELECT MAX(assessed_date) FROM assess WHERE collateral = ?1",
//            nativeQuery = true)
//    LocalDate maxDate(Long collateralId);
//    
//    @Query(value = "SELECT id, assessed_date, assessed_value, collateral FROM assess WHERE collateral = ?1 AND assessed_date = ?2",
//            nativeQuery = true)
//    Assess getAssess(Long collateralId, LocalDate maxDate);    
}
