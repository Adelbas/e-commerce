package com.adel.ecommerce.repository;

import com.adel.ecommerce.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
