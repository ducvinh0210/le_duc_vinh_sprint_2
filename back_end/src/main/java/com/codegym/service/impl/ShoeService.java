package com.codegym.service.impl;

import com.codegym.dto.IShoeDto;
import com.codegym.repository.IShoeRepository;
import com.codegym.service.IShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShoeService implements IShoeService {
    @Autowired
    private IShoeRepository iShoeRepository;


    @Override
    public Page<IShoeDto> showListShoe(String nameShoe, String typeShoe, Integer priceStart, Integer priceEnd, Pageable pageable) {
        return iShoeRepository.showListShoe(nameShoe, typeShoe, priceStart, priceEnd, pageable);
    }
}
