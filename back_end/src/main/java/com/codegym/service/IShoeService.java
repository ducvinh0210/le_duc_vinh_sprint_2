package com.codegym.service;

import com.codegym.dto.IShoeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface IShoeService {
    Page<IShoeDto>showListShoe(String nameShoe, String typeShoe, Integer priceStart, Integer priceEnd, Pageable pageable);
}
