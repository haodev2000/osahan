package com.example.osahaneat.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.osahaneat.entity.Food;

@Repository
public interface FoodResponsitory extends JpaRepository<Food, Long> {

}
