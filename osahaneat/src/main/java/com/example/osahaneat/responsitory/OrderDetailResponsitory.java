package com.example.osahaneat.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.osahaneat.entity.OrderDetail;
import com.example.osahaneat.entity.key.KeyOrderDetail;

@Repository
public interface OrderDetailResponsitory extends JpaRepository<OrderDetail, KeyOrderDetail> {

}
