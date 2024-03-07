package com.example.osahaneat.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.osahaneat.entity.Category;

@Repository
public interface CategoryResponsitory extends JpaRepository<Category, Long> {

}
