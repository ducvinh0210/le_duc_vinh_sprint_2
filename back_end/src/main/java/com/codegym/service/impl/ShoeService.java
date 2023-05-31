package com.codegym.service.impl;

import com.codegym.dto.IShoeDto;
import com.codegym.repository.IShoeRepository;
import com.codegym.service.IShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeService implements IShoeService {
    @Autowired
    private IShoeRepository iShoeRepository;


    @Override
    public Page<IShoeDto> showListShoe(String nameShoe, String typeShoe, String manufacturerShoe, Integer priceStart, Integer priceEnd, Pageable pageable) {
        return iShoeRepository.showListShoe(nameShoe, typeShoe, manufacturerShoe, priceStart, priceEnd, pageable);
    }

    @Override
    public List<String> findAllManufacturerShoe() {
        return iShoeRepository.findAllManufacturerShoe();
    }

    @Override
    public IShoeDto findShoeById(Integer id) {
        return iShoeRepository.findShoeById(id);
    }

    @Override
    public Integer findByIdSize(Integer idSize, Integer idShoe) {
        return iShoeRepository.findByIdSize(idSize, idShoe);
    }
}
