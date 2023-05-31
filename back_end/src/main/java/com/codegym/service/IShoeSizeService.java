package com.codegym.service;

import com.codegym.dto.IShoeSizeDto;

import java.util.List;

public interface IShoeSizeService {
    List<IShoeSizeDto> findSizeByShoe(Integer id);



}
