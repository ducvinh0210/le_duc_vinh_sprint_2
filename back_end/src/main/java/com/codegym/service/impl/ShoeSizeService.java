package com.codegym.service.impl;


import com.codegym.dto.IShoeSizeDto;
import com.codegym.repository.IShoeRepository;
import com.codegym.repository.IShoeSizeRepository;
import com.codegym.service.IShoeSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeSizeService implements IShoeSizeService {

    @Autowired
    private IShoeSizeRepository iShoeSizeRepository;

    @Override
    public List<IShoeSizeDto> findSizeByShoe(Integer id) {
        return iShoeSizeRepository.findAllSizeByShoe(id);
    }
}
