package com.codegym.service.impl;

import com.codegym.model.ShoeType;
import com.codegym.repository.IShoeTypeRepository;
import com.codegym.service.IShoeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShoeTypeService implements IShoeTypeService {
    @Autowired
    private IShoeTypeRepository iShoeTypeRepository;



    @Override
    public List<ShoeType> findAllShoeType() {
        return iShoeTypeRepository.findAll();
    }
}
