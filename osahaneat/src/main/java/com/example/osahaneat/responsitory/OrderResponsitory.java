package com.example.osahaneat.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.osahaneat.entity.Orders;

@Repository
public interface OrderResponsitory extends JpaRepository<Orders, Long> {

}
