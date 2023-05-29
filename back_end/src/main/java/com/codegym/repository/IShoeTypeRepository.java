package com.codegym.repository;

import com.codegym.model.ShoeType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShoeTypeRepository extends JpaRepository<ShoeType, Integer> {
}
