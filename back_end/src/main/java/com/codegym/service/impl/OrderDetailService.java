package com.codegym.service.impl;


import com.codegym.dto.IShoeCartDto;
import com.codegym.repository.IOrderDetailRepository;
import com.codegym.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private IOrderDetailRepository iOrderDetailRepository;

    @Override
    public List<IShoeCartDto> findAllCartByUser(Integer idCustomer) {
        return iOrderDetailRepository.findCartByUser(idCustomer);
    }

    @Override
    public void addOrderDetailCart(Integer quantity, Integer customerId, Integer shoeSizeId) {
        iOrderDetailRepository.addOrderDetailCart(quantity, customerId, shoeSizeId);
    }
}
