package com.adel.ecommerce.security.repository;

import java.util.List;

import com.adel.ecommerce.security.model.RememberMeToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<RememberMeToken, String> {
    List<RememberMeToken> findByUsername(String username);
    RememberMeToken findBySeries(String series);
}
