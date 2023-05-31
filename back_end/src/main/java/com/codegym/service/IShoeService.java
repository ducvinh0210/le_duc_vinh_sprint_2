package com.codegym.service;

import com.codegym.dto.IShoeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IShoeService {
    Page<IShoeDto> showListShoe(String nameShoe, String typeShoe, String manufacturerShoe, Integer priceStart, Integer priceEnd, Pageable pageable);

    List<String> findAllManufacturerShoe();

    IShoeDto findShoeById(Integer id);

    Integer findByIdSize(Integer idSize, Integer idShoe);


}
