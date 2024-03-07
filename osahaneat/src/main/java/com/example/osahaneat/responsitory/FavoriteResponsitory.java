package com.example.osahaneat.responsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.osahaneat.entity.Favorite;
import com.example.osahaneat.entity.key.KeyFavorite;

@Repository
public interface FavoriteResponsitory extends JpaRepository<Favorite, KeyFavorite> {

}
