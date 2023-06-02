package com.codegym.service;

import com.codegym.dto.IShoeSizeDto;
import com.codegym.dto.Quantity;

import java.util.List;

public interface IShoeSizeService {
    List<IShoeSizeDto> findSizeByShoe(Integer id);

    void updateQuantityShoeSize(Integer quantity, Integer id);

    List<Quantity> findAllShoeSize(Integer customerId);


}
