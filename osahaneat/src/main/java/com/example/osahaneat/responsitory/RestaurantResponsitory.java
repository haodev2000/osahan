package com.example.osahaneat.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.osahaneat.entity.Restaurant;

@Repository
public interface RestaurantResponsitory extends JpaRepository<Restaurant, Long> {

}
